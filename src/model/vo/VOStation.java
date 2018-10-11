package model.vo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Representation of a station object
 */
public class VOStation{

	private int id;
	private String name;
	private String city;
	private double latitude;
	private double longitude;
	private int dpcapacity;
	private LocalDateTime online_date;


	public VOStation(int pid, String pname, String pcity, double platitude, double plongitude, int pdpcapacity, String ponline_date) throws Exception
	{
		id = pid;
		name = pname;
		city = pcity;
		latitude = platitude;
		longitude = plongitude;
		dpcapacity = pdpcapacity;
		online_date =  (new SimpleDateFormat ("MM/dd/yyyy kk:mm").parse(ponline_date)).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public int getdpCapacity() {
		return dpcapacity;
	}
	public LocalDateTime getonline_date() {
		return online_date;
	}
}
