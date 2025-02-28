package offshoregroundsamplingfrontend.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.inject.Inject;
import offshoregroundsamplingfrontend.dialog.AddEditSampleDialog;
import offshoregroundsamplingfrontend.model.Sample;

public class View extends ViewPart {
	public static final String ID = "Tutorial.view";
	private static final String API_URL = "http://localhost:8080/api/samples";
	private static final Gson gson = new Gson();

	@Inject
	IWorkbench workbench;

	private TableViewer viewer;

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(1, false));

		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createColumns();
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		fetchSamplesFromAPI();

		Composite buttonBar = new Composite(parent, SWT.NONE);
		buttonBar.setLayout(new RowLayout());

		Button addButton = new Button(buttonBar, SWT.PUSH);
		addButton.setText("Add Sample");
		addButton.addListener(SWT.Selection, e -> openAddSampleDialog(null));

		Button editButton = new Button(buttonBar, SWT.PUSH);
		editButton.setText("Edit Sample");
		editButton.addListener(SWT.Selection, e -> editSelectedSample());

		Button deleteButton = new Button(buttonBar, SWT.PUSH);
		deleteButton.setText("Delete Sample");
		deleteButton.addListener(SWT.Selection, e -> deleteSelectedSample());
		
		Button statsButton = new Button(buttonBar, SWT.PUSH);
		statsButton.setText("View Statistics");
		statsButton.addListener(SWT.Selection, e -> fetchStatistics());
		
//		Button graphButton = new Button(buttonBar, SWT.PUSH);
//		graphButton.setText("Show Graph");
//		graphButton.addListener(SWT.Selection, e -> showGraph());

		
		viewer.refresh();
	}

	private void openAddSampleDialog(Sample sample) {
		AddEditSampleDialog dialog = new AddEditSampleDialog(getSite().getShell(), sample);
		if (dialog.open() == Window.OK) {
			addSampleToAPI(dialog.getSample());
		}
	}

	private void editSelectedSample() {
		IStructuredSelection selection = viewer.getStructuredSelection();
		if (!selection.isEmpty()) {
			Sample selectedSample = (Sample) selection.getFirstElement();
			AddEditSampleDialog dialog = new AddEditSampleDialog(getSite().getShell(), selectedSample);
			if (dialog.open() == Window.OK) {
				//String selectedLocation = dialog.getSelectedLocation() != null ? dialog.getSelectedLocation() :dialog.getSample().getLocation() ;
				Sample newSample = dialog.getSample();
				//newSample.setLocation(selectedLocation);
				updateSampleToAPI(newSample);
			}
		}
	}

	private void deleteSelectedSample() {
		IStructuredSelection selection = viewer.getStructuredSelection();
		if (!selection.isEmpty()) {
			Sample selectedSample = (Sample) selection.getFirstElement();
			try {
				//URL url = new URL(API_URL + "/" + selectedSample.getSampleId());
				URL url = URI.create(API_URL + "/" + selectedSample.getSampleId()).toURL();
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("DELETE");
				conn.setRequestProperty("Accept", "application/json");
				conn.getResponseCode();
				fetchSamplesFromAPI(); // Refresh the table after deletion
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void addSampleToAPI(Sample sample) {
		try {
			//URL url = new URL(API_URL);
			URL url = URI.create(API_URL).toURL();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			String jsonInputString = gson.toJson(sample);
			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			conn.getResponseCode();
			fetchSamplesFromAPI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateSampleToAPI(Sample sample) {
		try {
			//URL url = new URL(API_URL + "/" + sample.getSampleId());
			URL url = URI.create(API_URL + "/" + sample.getSampleId()).toURL();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			String jsonInputString = gson.toJson(sample);
			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			conn.getResponseCode();
			fetchSamplesFromAPI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createColumns() {
		String[] titles = { "Sample ID", "Location", "Date", "Unit Weight", "Water Content", "Shear Strength" };
		int[] bounds = { 100, 150, 150, 150, 150, 150 };

		for (int i = 0; i < titles.length; i++) {
			final int columnIndex = i; // Capture i in a final variable
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setResizable(true);
			column.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					Sample sample = (Sample) element;
					switch (columnIndex) {
					case 0:
						return String.valueOf(sample.getSampleId());
					case 1:
						return sample.getLocation();
					case 2:
						return sample.getDate();
					case 3:
						return String.valueOf(sample.getUnitWeight());
					case 4:
						return String.valueOf(sample.getWaterContent());
					case 5:
						return String.valueOf(sample.getShearStrength());
					default:
						return "";
					}
				}
			});
		}
	}

	private void fetchSamplesFromAPI() {
		try {
			URL url = URI.create(API_URL).toURL();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String response = br.lines().collect(Collectors.joining());
			br.close();

			List<Sample> samples = gson.fromJson(response, new TypeToken<List<Sample>>() {
			}.getType());
			viewer.setInput(samples);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fetchStatistics() {
	    try {
	        URL url = URI.create("http://localhost:8080/api/statistics").toURL();
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");

	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String response = br.lines().collect(Collectors.joining());
	        br.close();

	        MessageDialog.openInformation(viewer.getControl().getShell(), "Statistics", response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
//	private void showGraph() {
//	    XYSeries series = new XYSeries("Unit Weight vs Water Content");
//
//	    // Fetch data from the table
//	    List<Sample> samples = (List<Sample>) viewer.getInput();
//	    for (Sample sample : samples) {
//	        series.add(sample.getWaterContent(), sample.getUnitWeight());
//	    }
//
//	    XYSeriesCollection dataset = new XYSeriesCollection(series);
//	    JFreeChart chart = ChartFactory.createXYLineChart(
//	            "Unit Weight vs Water Content",
//	            "Water Content (%)",
//	            "Unit Weight (kN/m³)",
//	            dataset,
//	            PlotOrientation.VERTICAL,
//	            true, true, false);
//
//	    JFrame frame = new JFrame("Sample Chart");
//	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	    frame.getContentPane().add(new ChartPanel(chart));
//	    frame.pack();
//	    frame.setVisible(true);
//	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}