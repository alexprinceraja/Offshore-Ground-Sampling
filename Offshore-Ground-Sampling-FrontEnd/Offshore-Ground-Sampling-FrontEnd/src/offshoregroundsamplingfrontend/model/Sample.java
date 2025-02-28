package offshoregroundsamplingfrontend.model;

public class Sample {
    private int sampleId;
    private String location;
    private String date;
    private double unitWeight;
    private double waterContent;
    private double shearStrength;

    public Sample(int sampleId, String location, String date, double unitWeight, double waterContent, double shearStrength) {
        this.sampleId = sampleId;
        this.location = location;
        this.date = date;
        this.unitWeight = unitWeight;
        this.waterContent = waterContent;
        this.shearStrength = shearStrength;
    }

    public int getSampleId() { return sampleId; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public double getUnitWeight() { return unitWeight; }
    public double getWaterContent() { return waterContent; }
    public double getShearStrength() { return shearStrength; }

	public void setLocation(String location) {
		this.location = location;
	}
}