package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import api.IDivvyTripsManager;
import model.vo.VOBike;
import model.vo.VOBikeRoute;
import model.vo.VOStation;
import model.vo.VOTrip;
import utils.Sort;
import model.data_structures.IDoublyLinkedList;
import model.data_structures.IQueue;
import model.data_structures.DoublyLinkedList;
import model.data_structures.ListQueue;
import model.data_structures.ListStack;
import model.data_structures.Node;


public class DivvyTripsManager implements IDivvyTripsManager {

	//Ruta del archivo de datos 2017-Q1
	public static final String TRIPS_Q1 = "./data/Divvy_Trips_2017_Q1.csv";

	//Ruta del archivo de trips 2017-Q2
	public static final String TRIPS_Q2 = "./data/Divvy_Trips_2017_Q2.csv";

	//Ruta del archivo de trips 2017-Q3
	public static final String TRIPS_Q3 = "./data/Divvy_Trips_2017_Q3.csv";

	//Ruta del archivo de trips 2017-Q4	
	public static final String TRIPS_Q4 = "./data/Divvy_Trips_2017_Q4.csv";

	//Ruta del archivo de trips 2017-Q1-Q4
	//TODO ?
	public static final String TRIPS_Q1_Q4 = "Ruta Trips 2017-Q1-Q4 en directorio data";

	//Ruta del archivo de stations 2017-Q1-Q2	
	public static final String STATIONS_Q1_Q2 = "./data/Divvy_Stations_2017_Q1Q2.csv";

	//Ruta del archivo de stations 2017-Q3-Q4
	public static final String STATIONS_Q3_Q4 = "./data/Divvy_Stations_2017_Q3Q4.csv";

	//Ruta del archivo JSON
	// TODO Cambiar archivo por nuevo
	public static final String JSON = "./data/bikeRoutes.JSON";

	private int numberOfTrips;
	private int numberOfStations;
	private int numberOfBikeRoutes;

	private DoublyLinkedList <VOStation> stationList;
	private DoublyLinkedList <VOTrip> tripList;
	private DoublyLinkedList <VOBikeRoute> bikeRoutesList;

	public int getNumberOfTrips() {
		return numberOfTrips;
	}
	public int getNumberOfStations() {
		return numberOfStations;
	}
	public int getNumberOfBikeRoutes() {
		return numberOfBikeRoutes;
	}
	public DoublyLinkedList<VOStation> getStationList()
	{
		return stationList;
	}
	public DoublyLinkedList<VOTrip> getTripList()
	{
		return tripList;
	}
	public DoublyLinkedList<VOBikeRoute> getBikeRouteList()
	{
		return bikeRoutesList;
	}

	public void loadStations (String stationsFile) {

		if(stationList == null)
		{
			stationList = new DoublyLinkedList<VOStation>();
		}
		try
		{
			FileReader fr =  new FileReader(stationsFile);
			BufferedReader br = new BufferedReader(fr);
			br.readLine();
			String data = br.readLine();
			while(data != null)
			{
				data = data.replace('"', ' ');
				String[] datos = data.split(",");
				int id = Integer.parseInt(datos[0].trim());
				String name = datos[1].trim();
				String city =  datos[2].trim();
				double latitude =  Double.parseDouble(datos[3].trim());
				double longitude =  Double.parseDouble(datos[4].trim());
				int dpcapacity =  Integer.parseInt(datos[5].trim());
				String online_LocalDateTime =  datos[6].trim();
				data = br.readLine();
				stationList.addAtBeginning(new VOStation(id, name, city, latitude, longitude, dpcapacity, online_LocalDateTime));
				numberOfStations++;
			}
			br.close();
			fr.close();
		}
		catch(Exception e)
		{
			System.out.println("hubo una excepci�n cargando las estaciones");
			System.out.println(e.getMessage());
		}

	}

	public void loadTrips (String tripsFile) {

		if(tripList == null)
		{
			tripList= new DoublyLinkedList<>();
		}
		try
		{
			FileReader fr =  new FileReader(tripsFile);
			BufferedReader br = new BufferedReader(fr);
			br.readLine();
			String data = br.readLine();
			while(data != null)
			{
				data = data.replace('"', ' ');
				String[] datos = data.split(",");
				int trip_id = Integer.parseInt(datos[0].trim());
				String start_time = datos[1].trim();
				String end_time = datos[2].trim();
				int bikeid = Integer.parseInt(datos[3].trim());
				int tripduration = Integer.parseInt(datos[4].trim());
				int from_station_id = Integer.parseInt(datos[5].trim());
				String from_station_name = datos[6].trim();
				int to_station_id = Integer.parseInt(datos[7].trim());
				String to_station_name = datos[8].trim();
				String usertype = datos[9].trim();
				String gender;
				String birthyear;

				if(datos.length == 10)
				{
					gender = VOTrip.UNKNOWN;
					birthyear = "";
				}
				else if(datos.length == 11)
				{
					gender = datos[10].trim();
					birthyear ="";
				}
				else
				{
					gender = datos[10].trim();
					birthyear = datos[11].trim();
				}
				tripList.addAtBeginning(new VOTrip(trip_id, start_time, end_time, bikeid, tripduration, from_station_id, from_station_name, to_station_id, to_station_name, usertype, gender, birthyear));
				numberOfTrips++;
				data = br.readLine();
			}
			br.close();
			fr.close();

		}
		catch(Exception e)
		{
			System.out.println("hubo una excepci�n cargando los viajes");
			System.out.println(e.getMessage());
		}		
	}

	@Override
	public void loadBikeRoutes(String routesFile) {
		try
		{
			bikeRoutesList = new DoublyLinkedList <VOBikeRoute>();
			File file= new File (routesFile);
			JsonParser p = new JsonParser();
			JsonObject object = (JsonObject) p.parse(new FileReader(file));
			JsonArray bikeRoutes = object.getAsJsonArray("data");
			for (int i = 0; i < bikeRoutes.size(); i++)
			{
				JsonArray bikeRoute = (JsonArray) bikeRoutes.get(i);

				String bikeRouteType = bikeRoute.get(8).toString().replaceAll("\"", "");
				String coordinates = bikeRoute.get(9).toString().replaceAll("\"", "");		
				String referenceStreet = bikeRoute.get(11).toString().replaceAll("\"", "");
				String limitStreet1 = bikeRoute.get(12).toString().replaceAll("\"", "");
				String limitStreet2 = bikeRoute.get(13).toString().replaceAll("\"", "");
				double length = Double.parseDouble(bikeRoute.get(15).toString().replaceAll("\"", ""));
				VOBikeRoute created = new VOBikeRoute(bikeRouteType, coordinates, referenceStreet, limitStreet1, limitStreet2, length);
				bikeRoutesList.addAtBeginning(created);
				numberOfBikeRoutes++;
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

	}



	public IQueue<VOTrip> getQueueInTime1A(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
		IQueue<VOTrip> q =new ListQueue<>();
		if(endLocalDateTime.isBefore(startLocalDateTime))
		{
			System.out.println("La fecha final no puede ser anterior a la fecha inicial");
		}
		else
		{
			Node<VOTrip> n = tripList.getNode(0);
			if(n != null)
			{
				VOTrip t = n.getElement();
				while(n != null && t.getStart_time().isBefore(endLocalDateTime))
				{
					if(t.getStart_time().isAfter(startLocalDateTime) && t.getEnd_time().isBefore(endLocalDateTime))
					{
						q.enqueue(t);	
					}
					n = n.getNext();
					t = n.getElement();
				}
			}
		}
		return q;
	}

	@Override
	public IDoublyLinkedList<VOBike> bikesOrderedNumberTripsInTime2A(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
		IQueue <VOTrip> q = getQueueInTime1A(startLocalDateTime, endLocalDateTime);
		IDoublyLinkedList<VOBike> bikeList = new DoublyLinkedList<VOBike>();
		Iterator<VOTrip> i = q.iterator();
		while(i.hasNext())
		{
			VOTrip one = i.next();
			VOBike first = buscarBicicleta(one.getBikeid(), bikeList);
			VOStation start = buscarEstacion(one.getFromStationId());
			double lat1 = start.getLatitude();
			double long1 = start.getLongitude();
			VOStation end = buscarEstacion(one.getToStationId());
			double lat2 = end.getLatitude();
			double long2 = end.getLongitude();
			if(first != null)
			{
				first.setNumberOfTrips(first.getNumberOfTrips() + 1);
				first.setTotalDistance(first.getTotalDistance() + calculateDistance(lat1, long1, lat2, long2));
				first.setTotalDuration(first.getTotalDuration() + one.getTripDuration());
			}
			else
			{

				first = new VOBike(one.getBikeid(), 1, one.getTripDuration(), calculateDistance(lat1, long1, lat2, long2));
				bikeList.addAtBeginning(first);
			}
		}
		VOBike[] arreglo = new VOBike[bikeList.size()];
		for (int j = 0; j < arreglo.length; j++) 
		{
			arreglo[j] = bikeList.getElement(j);
		}
		Sort<VOBike> sort = new Sort<VOBike>();
		BikesComparatorNumberOfTrips bct = new BikesComparatorNumberOfTrips();
		sort.quickSort(arreglo, bct);
		DoublyLinkedList<VOBike> ret = new DoublyLinkedList<VOBike>();
		for (int k = 0; k < arreglo.length; k++) 
		{
			ret.addAtBeginning(arreglo[k]);
		}
		return ret;
	}

	private VOStation buscarEstacion(int id)
	{
		VOStation element = null;
		if (stationList != null)
		{
			boolean found = false;
			if(stationList.getElement(0).getId() == id)
			{
				found = true;
				element = stationList.getElement(0);
			}
			else
			{
				Iterator<VOStation> i = stationList.iterator();
				while(i.hasNext() && !found)
				{
					VOStation b = i.next();
					if(b.getId() == id)
					{
						found = true;
						element = b;
					}

				}
			}

		}
		return element;
	}
	//	private	double calculateDistance (double lat1, double lon1, double	longitudReferencia, double latitudReferencia)
	//	{
	//		final 	int	R = 	6371*1000; // Radius of the earth in meters
	//		double	latDistance = Math.toRadians(latitudReferencia-lat1);
	//		double	lonDistance = Math.toRadians(longitudReferencia-lon1);
	//		double	a = Math.sin(latDistance/2)	 * Math.sin(latDistance/2)	 + Math.cos(Math.toRadians(lat1))*	Math.cos(Math.toRadians(latitudReferencia))	 * Math.sin(lonDistance/2)	 * Math.sin(lonDistance/2);
	//		double c = 	2 * Math.atan2(Math.sqrt(a),	Math.sqrt(1-a));
	//		double 	distance =	R * c;
	//		return	distance;
	//	}


	private static double calculateDistance(double startLat, double startLong,
			double endLat, double endLong)
	{
		final int earthRadius = 6371;

		double dLat  = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat   = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return earthRadius * c; // <-- d
	}

	private static double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}


	private VOBike buscarBicicleta(int bikeid, IDoublyLinkedList<VOBike> bikeList ) {
		VOBike element = null;
		if (bikeList!= null)
		{
			boolean found = false;
			if(bikeList.getElement(0) != null && bikeList.getElement(0).getId() == bikeid)
			{
				found = true;
				element = bikeList.getElement(0);
			}
			else
			{
				Iterator<VOBike> i = bikeList.iterator();
				while(i!= null && i.hasNext() && !found)
				{
					VOBike b = i.next();
					if(b.getId() == bikeid)
					{
						found = true;
						element = b;
					}

				}
			}

		}
		return element;
	}
	@Override
	public IDoublyLinkedList<VOTrip> getBikeTripsInTime3A(int bicycleId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
		IDoublyLinkedList<VOTrip> q =new DoublyLinkedList<VOTrip>();
		if(endLocalDateTime.isBefore(startLocalDateTime))
		{
			System.out.println("La fecha final no puede ser anterior a la fecha inicial");
		}
		else
		{
			Node<VOTrip> n = tripList.getNode(0);
			ListStack<VOTrip> stack = new ListStack<VOTrip>();
			VOTrip t = n.getElement();
			while(n != null && t.getStart_time().isBefore(endLocalDateTime) )
			{
				if(t.getStart_time().isAfter(startLocalDateTime) && t.getEnd_time().isBefore(endLocalDateTime))
				{
					if (bicycleId == t.getBikeid()) 
					{
						stack.push(t);
					}	
				}
				n = n.getNext();
				if(n!= null)
					t = n.getElement();
			}
			while(stack.getTopStack() != null)
			{
				q.addAtBeginning(stack.pop());
			}
		}
		return q;
	}

	@Override
	public IDoublyLinkedList<VOTrip> getTripsEndedInStationInTime4A(int stationId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
		// TODO Auto-generated method stub
		IDoublyLinkedList<VOTrip> q =new DoublyLinkedList<VOTrip>();
		if(endLocalDateTime.isBefore(startLocalDateTime))
		{
			System.out.println("La fecha final no puede estar despues de la fecha inicial");
		}
		else
		{
			Node<VOTrip> n = tripList.getNode(0);
			ListStack<VOTrip> stack = new ListStack<>();
			VOTrip t = n.getElement();
			while(n != null && t.getStart_time().isBefore(endLocalDateTime) )
			{
				if(t.getStart_time().isAfter(startLocalDateTime) && t.getEnd_time().isBefore(endLocalDateTime))
				{
					if (stationId == t.getToStationId()) {
						stack.push(t);
					}	
				}
				n = n.getNext();
				if(n != null)
					t = n.getElement();
			}
			while(stack.getTopStack() != null)
			{
				q.addAtBeginning(stack.pop());
			}
		}
		return q;
	}


	@Override
	public IQueue<VOStation> getQueueStationsStartedInTime1B(LocalDateTime startLocalDateTime) 
	{
		IQueue<VOStation> q =new ListQueue<>();
		Node<VOStation> n = stationList.getNode(0);
		if(n != null)
		{
			VOStation st = n.getElement();
			while(n != null)
			{
				if(st.getonline_date().isAfter(startLocalDateTime))
				{
					q.enqueue(st);	
				}
				n = n.getNext();
				if(n != null)
					st = n.getElement();
			}
		}
		return q;
	}

	@Override
	public IDoublyLinkedList<VOBike> bikesOrderedTotalDistanceInTime2B(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime)
	{
		IQueue <VOTrip> q = getQueueInTime1A(startLocalDateTime, endLocalDateTime);
		IDoublyLinkedList<VOBike> bikeList = new DoublyLinkedList<VOBike>();
		Iterator<VOTrip> i = q.iterator();
		while(i.hasNext())
		{
			VOTrip one = i.next();
			VOBike first = buscarBicicleta(one.getBikeid(), bikeList);
			VOStation start = buscarEstacion(one.getFromStationId());
			double lat1 = start.getLatitude();
			double long1 = start.getLongitude();
			VOStation end = buscarEstacion(one.getToStationId());
			double lat2 = end.getLatitude();
			double long2 = end.getLongitude();
			if(first != null)
			{
				first.setNumberOfTrips(first.getNumberOfTrips() + 1);
				first.setTotalDistance(first.getTotalDistance() + calculateDistance(lat1, long1, lat2, long2));
			}
			else
			{

				first = new VOBike(one.getBikeid(), 1, 0, calculateDistance(lat1, long1, lat2, long2));
				bikeList.addAtBeginning(first);
			}
		}
		VOBike[] arreglo = new VOBike[bikeList.size()];
		for (int j = 0; j < arreglo.length; j++) 
		{
			arreglo[j] = bikeList.getElement(j);
		}
		Sort<VOBike> sort = new Sort<VOBike>();
		BikesComparatorDistance bcd = new BikesComparatorDistance();
		sort.quickSort(arreglo, bcd);
		DoublyLinkedList<VOBike> ret = new DoublyLinkedList<VOBike>();
		for (int k = 0; k < arreglo.length; k++) 
		{
			ret.addAtBeginning(arreglo[k]);
		}
		return ret;

	}

	@Override
	public IDoublyLinkedList<VOTrip> getBikeTripsOfGenderAndDuration3B(String genre, int duration) 
	{
		IDoublyLinkedList<VOTrip> list = new DoublyLinkedList<VOTrip>();
		ListStack<VOTrip> stack = new ListStack<VOTrip>();
		Node<VOTrip> n = tripList.getNode(0);
		VOTrip t = n.getElement();
		while(n != null )
		{
			if(t.getTripDuration() < duration && t.getGender().equalsIgnoreCase(genre))
			{
				stack.push(t);
			}
			n = n.getNext();
			if(n != null)
				t = n.getElement();
			// TODO Auto-generated method stub
		}
		while(stack.getTopStack() != null)
		{
			list.addAtBeginning(stack.pop());
		}
		return list;
	}


	@Override
	public IDoublyLinkedList<VOTrip> getTripsStartedInStationInTime4B(int stationId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
		// TODO Auto-generated method stub
		IDoublyLinkedList<VOTrip> q =new DoublyLinkedList<VOTrip>();
		if(endLocalDateTime.isBefore(startLocalDateTime))
		{
			System.out.println("La fecha final no puede estar despues de la fecha inicial");
		}
		else
		{
			Node<VOTrip> n = tripList.getNode(0);
			ListStack<VOTrip> stack = new ListStack<>();
			VOTrip t = n.getElement();
			while(n != null && t.getStart_time().isBefore(endLocalDateTime) )
			{
				if(t.getStart_time().isAfter(startLocalDateTime) && t.getEnd_time().isBefore(endLocalDateTime))
				{
					if (stationId == t.getFromStationId()) 
					{
						stack.push(t);
					}	
				}
				n = n.getNext();
				if(n != null)
					t = n.getElement();
			}
			while(stack.getTopStack() != null)
			{
				q.addAtBeginning(stack.pop());
			}
		}
		return q;
	}

	@Override
	public void loadC1(String tripsFile, String stationsFile) {

		if(!tripsFile.equals("")&& !stationsFile.equals(""))
		{
			loadTrips(tripsFile);
			System.out.println("Total trips cargados en el sistema:" + numberOfTrips);
			loadStations(stationsFile);
			System.out.println("Total estaciones cargadas en el sistema:" + numberOfStations);
		}
		else
		{
			stationList = new DoublyLinkedList<VOStation>();
			tripList = new DoublyLinkedList<VOTrip>();
			System.out.println("Se han reiniciado las listas");
		}

	}
	@Override
	public IQueue<VOTrip> getQueueOfInconsistencies2C(int bicycleId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime)
	{
		IDoublyLinkedList<VOTrip> list = getBikeTripsInTime3A(bicycleId, startLocalDateTime, endLocalDateTime);
		ListQueue<VOTrip> inconsistencias = new ListQueue<VOTrip>();
		ListStack<VOTrip> consistencias = new ListStack<VOTrip>();
		if(list.size() == 0)
		{
			return null;
		}
		Node<VOTrip> node = list.getNode(0);
		VOTrip element = node.getElement();
		while(node != null)
		{
			if(consistencias.isEmpty())
			{
				consistencias.push(element);
			}
			else
			{
				if(consistencias.getTopStack().getElement().getToStationId() == element.getFromStationId())
				{
					consistencias.push(element);
				}
				else
				{
					inconsistencias.enqueue(consistencias.pop());
					inconsistencias.enqueue(element);
					consistencias = new ListStack<>();
				}
			}
			node = node.getNext();
			if(node != null)
				element = node.getElement();
		}
		return inconsistencias;
	}

	@Override
	public IDoublyLinkedList<VOBike> getXMostUsedBikes3C(int number) {
		// TODO Auto-generated method stub
		IDoublyLinkedList<VOBike> bikeList = new DoublyLinkedList<VOBike>();
		Iterator<VOTrip> i = tripList.iterator();
		while(i.hasNext())
		{
			VOTrip one = i.next();
			VOBike first = buscarBicicleta(one.getBikeid(), bikeList);

			if(first != null)
			{
				first.setTotalDuration(first.getTotalDuration() + one.getTripDuration());
			}
			else
			{

				first = new VOBike(one.getBikeid(), 0, one.getTripDuration(), 0);
				bikeList.addAtBeginning(first);
			}
		}
		VOBike[] arreglo = new VOBike[bikeList.size()];
		for (int j = 0; j < arreglo.length; j++) 
		{
			arreglo[j] = bikeList.getElement(j);
		}
		Sort<VOBike> sort = new Sort<VOBike>();
		BikesComparatorDuration bcd = new BikesComparatorDuration();
		sort.quickSort(arreglo, bcd);
		DoublyLinkedList<VOBike> ret = new DoublyLinkedList<VOBike>();
		for (int k = 0; k < number; k++) 
		{
			ret.addAtBeginning(arreglo[k]);
		}
		return ret;
	}

	@Override
	public IDoublyLinkedList<VOTrip> getTripsEndedOrStartedInStationInTime4C(int stationId, LocalDateTime startLocalDateTime,
			LocalDateTime endLocalDateTime) {
		// retorna la concatenacion de 4A y 4B
		// TODO Auto-generated method stub
		IDoublyLinkedList<VOTrip> ended = getTripsEndedInStationInTime4A(stationId, startLocalDateTime, endLocalDateTime);
		IDoublyLinkedList<VOTrip> started = getTripsStartedInStationInTime4B(stationId, startLocalDateTime, endLocalDateTime);
		VOTrip[] end = new VOTrip[ended.size()];
		VOTrip[] start = new VOTrip[started.size()];
		for (int i = 0; i < ended.size(); i++) 
		{
			end[i] = ended.getElement(i);
		}
		for (int i = 0; i < started.size(); i++) 
		{
			start[i] = started.getElement(i);
		}
		TripsComparatorStartEndDate cse = new TripsComparatorStartEndDate();
		int i = 0, j = 0;
		VOTrip[] last = new VOTrip[start.length + end.length];
		int contador = 0;
		while(i < start.length && j < end.length)
		{
			if(cse.compare(start[i], end[j]) < 0)
			{
				last[contador] = start[i];
				contador++;
				i++;
			}
			else
			{
				last[contador] = end[j];
				contador++;
				j++;	
			}
		}
		if(start.length > contador)
		{
			for(int k = contador; k< start.length; k++)
			{
				last[contador] = start[k];
				contador++;
			}
		}
		else
		{
			for(int k = contador; k< end.length; k++)
			{
				last[contador] = end[k];
				contador++;
			}
		}
		IDoublyLinkedList<VOTrip> ultimate = new DoublyLinkedList<VOTrip>();
		for(int w = last.length; w > 0; w--)
		{
			ultimate.addAtBeginning(last[w]);
		}
		return ultimate;
	}









}
