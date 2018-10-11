package api;

import java.time.LocalDateTime;


import model.data_structures.IDoublyLinkedList;
import model.data_structures.IQueue;
import model.vo.VOBike;
import model.vo.VOStation;
import model.vo.VOTrip;

/**
 * Basic API for testing the functionality of the STS manager
 */
public interface IDivvyTripsManager {
	/**
     * Generar una cola con todos los viajes en un periodo de tiempo dado ordenados en orden cronologico por su fecha
     * inicial.
     * @param fechaInicial Fecha inicial del periodo de consulta
     * @param fechaFinal Fecha final del periodo de consulta
     * @return Cola con los viajes ordenados
     */
	IQueue <VOTrip> getQueueInTime1A (LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);

    /**
     * Mostrar las bicicletas ordenadas de mayor a menor por el numero de viajes realizados en el periodo de consulta
     * @param fechaInicial Fecha inicial del periodo de consulta
     * @param fechaFinal Fecha final del periodo de consulta
     * @return Lista con las bicicletas ordenadas
     */
	IDoublyLinkedList<VOBike> bikesOrderedNumberTripsInTime2A (LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);

    /**
     * Mostrar todos los viajes (en orden cronologico) realizados por la bicicleta con el identificador dado en el
     * periodo de tiempo establecido
     * @param bikeId El identificador de la bicicleta de la bicicleta
     * @param fechaInicial Fecha inicial del periodo de consulta
     * @param fechaFinal Fecha final del periodo de consulta
     * @return Lista con los viajes ordenados
     */
	IDoublyLinkedList<VOTrip> getBikeTripsInTime3A (int bicycleId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);
    /**
     * Mostrar los viajes (ordenados cronologicamente)que terminaron en la estacion con el identificador dado en el
     * periodo de tiempo de consulta
     * @param endStationId El identificador de la estacion final
     * @param fechaInicial Fecha inicial del periodo de consulta
     * @param fechaFinal Fecha final del periodo de consulta
     * @return Lista con los viajes ordenados
     */
	IDoublyLinkedList<VOTrip> getTripsEndedInStationInTime4A (int stationId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);
    /**
     * Generar una cola con las estaciones que comenzaron su operacion despues de la fecha dada. Estas estaciones deben
     * tener al menos un viaje despues de la fecha dada y ninguno antes.
     * @param fechaComienzo Fecha en la que las estaciones comenzaron su operacion
     * @return Cola con las estaciones que cumplen el requisito
     */
	IQueue <VOStation> getQueueStationsStartedInTime1B (LocalDateTime startLocalDateTime);

    /**
     * Mostrar las bicicletas ordenadas de mayor a menor por la distancia total de sus viajes en el periodo de consulta
     * @param fechaInicial Fecha inicial del periodo de consulta
     * @param fechaFinal Fecha final del periodo de consulta
     * @return Lista con las bicicletas ordenadas
     */
	IDoublyLinkedList<VOBike> bikesOrderedTotalDistanceInTime2B (LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);

    /**
     * Mostrar los viajes (ordenados cronologicamente)realizados por una bicicleta con el identificador dado que tengan
     * una duracion menor al valor de tiempo maximo dado y que hayan sido realizados por una persona del genero dado
     * @param bikeId El identificador de la bicicleta
     * @param tiempoMaximo La duracion maxima de los viajes
     * @param genero El genero de la persona que realizo los viajes
     * @return Lista con los viajes ordenados
     */
	IDoublyLinkedList<VOTrip> getBikeTripsOfGenderAndDuration3B (String genre, int duration);

    /**
     * Mostrar los viajes (ordenados cronologicamente)que iniciaron en la estacion con el identificador dado en el
     * periodo de tiempo de consulta
     * @param startStationId El identificador de la estacion inicial
     * @param fechaInicial Fecha inicial del periodo de consulta
     * @param fechaFinal Fecha final del periodo de consulta
     * @return Lista con los viajes ordenados
     */
	IDoublyLinkedList<VOTrip> getTripsStartedInStationInTime4B (int stationId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);
	/**
	 * Actualizar la informacion del sistema con los datos seleccionados por el usuario y generar/actualizar las estructuras de datos necesarias.
	 * Caso Especial: si rutaTrips y rutaStations son la cadena vacia (""), los datos del sistema deben reiniciarse con un conjunto de trips y de estaciones vacios.
	 * @param rutaTrips ruta del archivo de trips que se va a utilizar
	 * @param rutaStations ruta del archivo de stations que se va a utilizar
	 */
	void loadC1(String tripsFile, String stationsFile);

	/**
	 * Method to load the Divvy trips of the STS
	 * @param tripsFile - path to the file 
	 */
	void loadTrips(String tripsFile);
		
	/**
	 * Method to load the Divvy Stations of the STS
	 * @param stationsFile - path to the file 
	 */
	void loadStations(String stationsFile);
	
	/**
	 * Method to load the Divvy Stations of the STS
	 * @param stationsFile - path to the file 
	 */
	void loadBikeRoutes(String routesFile);
	
	
    /**
     * Revisar si todos los viajes de una bicicleta son validos, de tal forma que si un viaje termina en una estacion el siguiente debe iniciar en la misma estacion. 
     * @param bikeId el Id de la bicicleta
     * @param fechaInicial fecha inicial de consulta
     * @param fechaFinal fecha final de consulta
     * @return debe retornar una cola de inconstencias que incluya tiempo de terminacion y estacion de terminacion del viaje validado y tiempo de inicio y estacion de inicio del viaje inconsistente
     * Si no hay viajes inconsistentes debe retornar que todos los viajes son consistentes.
     */
	IQueue<VOTrip> getQueueOfInconsistencies2C (int bicycleId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);
   
    /**
     * Retorna las topBicicletas mas utilizadas de acuerdo con la duracion total de viajes.
     * @param topBicicletas el numero de bicicletas que se quieren evaluar.
     * @return Lista de bicicletas que mas se usaron.
     */
	IDoublyLinkedList<VOBike> getXMostUsedBikes3C (int number);
    
    /**
     * Viajes que iniciaron y terminaron en una estacion dada por su id en una fecha de inicio y fin dada
     * @param StationId id de la estacion
     * @param fechaInicial fecha de inicio de consulta
     * @param fechaFinal fecha de fin de la consulta
     * @return Lista de viajes que iniciaron o terminaron en una estacion en un rango de fechas
     */
	IDoublyLinkedList<VOTrip> getTripsEndedOrStartedInStationInTime4C (int stationId, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime);

}

