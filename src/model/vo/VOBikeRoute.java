package model.vo;

public class VOBikeRoute {

	private String bikeRouteType;
	private String coordinates;
	private String referenceStreet;
	private String limitStreet1;
	private String limitStreet2;
	private double length;
	public VOBikeRoute(String pBikeRouteType, String pCoordinates, String pReferenceStreet, String pLimitStreet1, String pLimitStreet2, double pLength)
	{
		setBikeRouteType(pBikeRouteType);
		setCoordinates(pCoordinates);
		setReferenceStreet(pReferenceStreet);
		setLimitStreet1(pLimitStreet1);
		setLimitStreet2(pLimitStreet2);
		setLength(pLength);
	}
	public String getBikeRouteType() {
		return bikeRouteType;
	}
	public void setBikeRouteType(String bikeRouteType) {
		this.bikeRouteType = bikeRouteType;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public String getReferenceStreet() {
		return referenceStreet;
	}
	public void setReferenceStreet(String referenceStreet) {
		this.referenceStreet = referenceStreet;
	}
	public String getLimitStreet1() {
		return limitStreet1;
	}
	public void setLimitStreet1(String limitStreet1) {
		this.limitStreet1 = limitStreet1;
	}
	public String getLimitStreet2() {
		return limitStreet2;
	}
	public void setLimitStreet2(String limitStreet2) {
		this.limitStreet2 = limitStreet2;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}


}
