package controller;

import java.time.LocalDateTime;

import model.data_structures.IQueue;
import model.data_structures.IDoublyLinkedList;
import model.logic.DivvyTripsManager;
import model.vo.VOBike;
import model.vo.VOStation;
import model.vo.VOTrip;


public class Controller {

	/**
	 * Reference to the services manager
	 */
	private static DivvyTripsManager manager = new DivvyTripsManager();


	public static void loadQ1() {
		manager.loadTrips("./data/Divvy_VOTrips_2017_Q1.csv");
	}
	public static void loadQ2() {
		manager.loadTrips("./data/Divvy_VOTrips_2017_Q2.csv");
	}
	public static void loadQ3() {
		manager.loadTrips("./data/Divvy_VOTrips_2017_Q3.csv");
	}
	public static void loadQ4() {
		manager.loadTrips("./data/Divvy_VOTrips_2017_Q4.csv");
	}
	public static int getNumberVOTrips() {
		return manager.getNumberOfTrips();
	}
	public static int getNumberVOStations() {
		return manager.getNumberOfStations();
	}
	public static int getNumberVOBikeRoutes() {
		return manager.getNumberOfBikeRoutes();
	}
	public static void loadQ1Q2() {
		manager.loadStations("./data/Divvy_VOStations_2017_Q1Q2.csv");
	}
	public static void loadQ3Q4() {
		manager.loadStations("./data/Divvy_VOStations_2017_Q3Q4.csv");
	}
	public static void loadJSON() {
		manager.loadBikeRoutes("./data/VOBikeRoutes.JSON");
	}
	public static IQueue<VOTrip> A1(LocalDateTime startDate, LocalDateTime endDate){
		return manager.getQueueInTime1A(startDate, endDate);
	}

	public static IDoublyLinkedList<VOBike> A2(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime){
		return manager.bikesOrderedNumberTripsInTime2A(startLocalDateTime, endLocalDateTime);
	}

	public IDoublyLinkedList<VOTrip> A3(int bicycleId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
		return manager.getBikeTripsInTime3A(bicycleId, startLocalDateTime, endLocalDateTime);
	}

	public IDoublyLinkedList<VOTrip> A4(int stationId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
		return manager.getTripsEndedInStationInTime4A(stationId, startLocalDateTime, endLocalDateTime);
	}

	public IQueue<VOStation> B1(LocalDateTime startLocalDateTime) {
		return manager.getQueueStationsStartedInTime1B(startLocalDateTime);
	}

	public IDoublyLinkedList<VOBike> B2(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
		return manager.bikesOrderedTotalDistanceInTime2B(startLocalDateTime, endLocalDateTime);
	}

	public IDoublyLinkedList<VOTrip> B3(int VOBikeId, int duration, String genre) {
		return manager.getBikeTripsOfGenderAndDuration3B(genre, duration);
	}

	public IDoublyLinkedList<VOTrip> B4(int stationId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
		return manager.getTripsStartedInStationInTime4B(stationId, startLocalDateTime, endLocalDateTime);
	}
	public static void C1cargar(String dataVOTrips, String dataVOStations){
		manager.loadC1(dataVOTrips, dataVOStations);
	}
	public IQueue<VOTrip> C2ViajesValidadosBicicleta(int bicycleId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime){
		return manager.getQueueOfInconsistencies2C(bicycleId, startLocalDateTime, endLocalDateTime);
	}
	public  IDoublyLinkedList<VOBike> C3BicicletasMasUsadas(int number){
		return manager.getXMostUsedBikes3C(number);
	}
	public IDoublyLinkedList<VOTrip> C4ViajesEstacion(int stationId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime){
		return manager.getTripsStartedInStationInTime4B(stationId, startLocalDateTime, endLocalDateTime);
	}

}