package offshoregroundsamplingfrontend.dialog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import offshoregroundsamplingfrontend.model.Sample;

public class AddEditSampleDialog extends Dialog {
	private Text txtDate, txtUnitWeight, txtWaterContent, txtShearStrength;
	private Combo comboLocationCombo;
	private String selectedLocation;
	private Sample sample;
	private static final String LOCATION_API_URL = "http://localhost:8080/api/locations/names";
	private static final Gson gson = new Gson();

	public AddEditSampleDialog(Shell parentShell) {
		super(parentShell);
	}

	public AddEditSampleDialog(Shell parentShell, Sample sample) {
		super(parentShell);
		this.sample = sample;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(sample == null ? "Add Sample" : "Edit Sample");
		shell.setSize(500, 350); // Ensure proper width
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(3, false));

		new Label(container, SWT.NONE).setText("Location:");
		comboLocationCombo = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		comboLocationCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		new Label(container, SWT.NONE).setText(""); 
		
		fetchLocations(sample);

		new Label(container, SWT.NONE).setText("Date:");
		txtDate = new Text(container, SWT.BORDER);
		txtDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtDate.setText(sample != null ? sample.getDate() : "");
		new Label(container, SWT.NONE).setText(""); 

		new Label(container, SWT.NONE).setText("Unit Weight:");
		txtUnitWeight = new Text(container, SWT.BORDER);
		txtUnitWeight.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtUnitWeight.setText(sample != null ? String.valueOf(sample.getUnitWeight()) : "");
		new Label(container, SWT.NONE).setText("kN/m3"); 
		
		new Label(container, SWT.NONE).setText("Water Content:");
		txtWaterContent = new Text(container, SWT.BORDER);
		txtWaterContent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtWaterContent.setText(sample != null ? String.valueOf(sample.getWaterContent()) : "");
		new Label(container, SWT.NONE).setText("%");
        
		new Label(container, SWT.NONE).setText("Shear Strength:");
		txtShearStrength = new Text(container, SWT.BORDER);
		txtShearStrength.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtShearStrength.setText(sample != null ? String.valueOf(sample.getShearStrength()) : "");
		new Label(container, SWT.NONE).setText("kPa");
		
		if (sample != null) {
			comboLocationCombo.setText(sample.getLocation()); // Keep previous selection for editing
		}

		comboLocationCombo.addListener(SWT.Selection, e -> {
			selectedLocation = comboLocationCombo.getText();
		});

		return container;
	}

	public String getSelectedLocation() {
		return selectedLocation != null ? selectedLocation : comboLocationCombo.getText();
	}

	@Override
	protected void okPressed() {

		double unitWeight = Double.parseDouble(txtUnitWeight.getText());
		double waterContent = Double.parseDouble(txtWaterContent.getText());
		double shearStrength = Double.parseDouble(txtShearStrength.getText());

		if (waterContent < 5 || waterContent > 150 || unitWeight < 12 || unitWeight > 26 || shearStrength < 2
				|| shearStrength > 1000) {
			MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_ERROR | SWT.OK);
			messageBox.setText("Invalid Input");
			messageBox.setMessage(
					"Please enter valid values: \nWater content (5-150%), \nUnit weight (12-26 kN/m3), \nShear strength (2-1000 kPa)");
			messageBox.open();
			return;
		}

		sample = new Sample(sample != null ? sample.getSampleId() : 0, comboLocationCombo.getText(), txtDate.getText(),
				Double.parseDouble(txtUnitWeight.getText()), Double.parseDouble(txtWaterContent.getText()),
				Double.parseDouble(txtShearStrength.getText()));
		super.okPressed();
	}

	private void fetchLocations(Sample sample) {
		try {
			URL url = URI.create(LOCATION_API_URL).toURL();
			//URL url = new URL(LOCATION_API_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String response = br.lines().collect(Collectors.joining());
			br.close();

			List<String> locations = gson.fromJson(response, new TypeToken<List<String>>() {
			}.getType());
			comboLocationCombo.setItems(locations.toArray(new String[0]));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

}