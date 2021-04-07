package ezEstimatingPackageFX;

public class Vehicle {
	
	private String vin, year, make, model, style, license, state, finishType, color;
	private String mileageIn, mileageOut;
	
	public Vehicle(String newVin, String newYear, String newMake, String newModel, String newStyle, String newLicense, String newState, String newMileageIn, String newMileageOut, String newFinishType, String newColor)
	{
		vin = newVin;
		year = newYear;
		make = newMake;
		model = newModel;
		style = newStyle;
		license = newLicense;
		state = newState;
		mileageIn = newMileageIn;
		mileageOut = newMileageOut;
		finishType = newFinishType;
		color = newColor;
		
		
	}
	
	public String getVin() {
		return vin;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getStyle() {
		return style;
	}
	
	public String getLicense() {
		return license;
	}
	
	public String getState() {
		return state;
	}
	
	public String getFinishType() {
		return finishType;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getMileageIn() {
		return mileageIn;
	}
	
	public String getMileageOut() {
		return mileageOut;
	}
}
