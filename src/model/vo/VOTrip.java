package model.vo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Representation of a Trip object
 */
public class VOTrip{

	public final static String MALE = "Male";
	public final static String FEMALE = "Female";
	public final static String UNKNOWN = "Unknown";

	private int id;
	private LocalDateTime start_time;
	private LocalDateTime end_time;
	private int bikeid;
	private int tripduration;
	private int from_station_id;
	private String from_station_name;
	private int to_station_id;
	private String to_station_name;
	private String usertype;
	private String gender;
	private String birthyear;

	public VOTrip(int trip_id, String pstart_time, String pend_time, int bikeid2,int tripduration2, int from_station_id2, String pfrom_station_name, int to_station_id2, String pto_station_name, String pusertype, String pgender, String pbirthyear )throws Exception
	{
		id = trip_id;
		start_time = (new SimpleDateFormat ("MM/dd/yyyy kk:mm").parse(pstart_time)).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		end_time = (new SimpleDateFormat ("MM/dd/yyyy kk:mm").parse(pend_time)).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		bikeid = bikeid2;
		tripduration = tripduration2;
		from_station_id = from_station_id2;
		from_station_name = pfrom_station_name;
		to_station_id = to_station_id2;
		to_station_name = pto_station_name;
		usertype = pusertype;
		gender = pgender;
		birthyear = pbirthyear;

	}
	public int getid()
	{
		return id;
	}
	public LocalDateTime getEnd_time()
	{
		return end_time;
	}
	public LocalDateTime getStart_time()
	{
		return start_time;
	}
	public int getBikeid()
	{
		return bikeid;
	}
	public int getTripDuration()
	{
		return tripduration;
	}
	public int getFromStationId()
	{
		return from_station_id;
	}
	public String getFromStationName()
	{
		return from_station_name;
	}
	public int getToStationId()
	{
		return to_station_id;
	}
	public String getToStationName()
	{
		return to_station_name;
	}
	public String getUsertype()
	{
		return usertype;
	}
	public String getGender()
	{
		return gender;
	}
	public String getBirthyear()
	{
		return birthyear;
	}
}
