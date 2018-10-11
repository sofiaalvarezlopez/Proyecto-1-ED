package model.vo;

public class VOBike{

	private int id;
	private int numberOfTrips;
	private double totalDuration;
	private double totalDistance;
	public VOBike(int pId, int pNumberOfTrips, double pTotalDuration , double pTotalDistance)
	{
		id = pId;
		numberOfTrips = pNumberOfTrips;
		totalDuration = pTotalDuration;
		totalDistance = pTotalDistance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumberOfTrips() {
		return numberOfTrips;
	}
	public void setNumberOfTrips(int numberOfTrips) {
		this.numberOfTrips = numberOfTrips;
	}
	public double getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(double totalDistance) {
		this.totalDistance = totalDistance;
	}
	public double getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(double totalDuration) {
		this.totalDistance = totalDuration;
	}

}
