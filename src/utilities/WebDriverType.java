package utilities;

public enum WebDriverType {

	 
	
	CHROME ("CHROME") ,
	EDGE ("EDGE") ,
	FIREFOX ("FIREFOX"),
	IE ("IE"),
	OPERA ("OPERA"),
	SAFARI ("SAFARI");

	private String driverName ;
	
	WebDriverType(String driverName) {
		this.driverName = driverName;
	}
	
	public String getDriverName() {
		return this.driverName;
	}
	
	
	
}
