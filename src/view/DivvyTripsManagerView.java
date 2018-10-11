package view;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

import controller.Controller;
import model.data_structures.IQueue;
import model.data_structures.IDoublyLinkedList;
import model.logic.DivvyTripsManager;
import model.vo.VOBike;
import model.vo.VOStation;
import model.vo.VOTrip;

public class DivvyTripsManagerView {

	public static void main(String[] args){

		Scanner linea = new Scanner(System.in);
		boolean fin = false; 
		Controller controlador = new Controller();
		while(!fin)
		{
			//Muestra cual fuente de datos va a cargar
			printMenu();

			int option = linea.nextInt();
			switch(option)
			{

			case 1:  //Carga de datos 1C
				String dataTrips = "";  // ruta del archivo de Trips
				String dataStations = ""; // ruta del archivo de Stations
				boolean reiniciarDatos = false;
				printMenuCargar();
				int tamanoDatos = linea.nextInt();
				switch (tamanoDatos)
				{
				case 1:
					dataTrips = DivvyTripsManager.TRIPS_Q1;
					dataStations = DivvyTripsManager.STATIONS_Q1_Q2;
					break;
				case 2:
					dataTrips = DivvyTripsManager.TRIPS_Q2;
					dataStations = DivvyTripsManager.STATIONS_Q1_Q2;
					break;
				case 3:
					dataTrips = DivvyTripsManager.TRIPS_Q3;
					dataStations = DivvyTripsManager.STATIONS_Q3_Q4;
					break;
				case 4:
					dataTrips = DivvyTripsManager.TRIPS_Q4;
					dataStations = DivvyTripsManager.STATIONS_Q3_Q4;
					break;
				case 5: // Opcion para reiniciar los datos del sistema. Conjunto vacio de trips y de estaciones.
					dataTrips = "";
					dataStations = "";
					reiniciarDatos = true;
					break;
				}

				if (!reiniciarDatos)
				{
					System.out.println("Trips x cargar al sistema: " + dataTrips);
					System.out.println("Stations x cargar al sistema: " + dataStations);
				}

				//Memoria y tiempo
				long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime = System.currentTimeMillis();

				//Metodo 1C
				Controller.C1cargar(dataTrips, dataStations);

				//Tiempo en cargar
				long endTime = System.currentTimeMillis();
				long duration = endTime - startTime;

				//Memoria usada
				long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");
				break;


			case 2: //Req 1A

				//Fecha Inicial
				System.out.println("Ingrese la fecha inicial (Ej : 28/3/2017)");
				String fechaInicialReq1A = linea.next();

				//Hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00)");
				String horaInicialReq1A = linea.next();

				// Datos Fecha/Hora inicial
				LocalDateTime localDateInicio = convertirFecha_Hora_LDT(fechaInicialReq1A, horaInicialReq1A);

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 29/3/2017)");
				String fechaFinalReq1A = linea.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 14:25:30)");
				String horaFinalReq1A = linea.next();

				// Datos Fecha/Hora final
				LocalDateTime localDateFin = convertirFecha_Hora_LDT(fechaFinalReq1A, horaFinalReq1A);

				//Metodo 1A
				IQueue<VOTrip> colaDeViajes = Controller.A1(localDateInicio, localDateFin);
				for(VOTrip v : colaDeViajes)
				{   // Mostrar un viaje en una misma linea
					System.out.print("Id trip: " + v.getid() + ", ");
					System.out.print("Id bicicleta: "+ v.getBikeid() + ", ");
					System.out.print("Fecha de inicio: "+ v.getStart_time() + ", ");
					System.out.println("Fecha de fin: "+ v.getEnd_time());
					System.out.println("-----");
				}
				break;

			case 3: //Req 2A
				//Fecha Inicial
				System.out.println("Ingrese la fecha inicial (Ej : 28/3/2017)");
				String fechaInicialReq2A = linea.next();

				//Hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00)");
				String horaInicialReq2A = linea.next();

				// Datos Fecha/Hora inicial
				LocalDateTime localDateInicio2A = convertirFecha_Hora_LDT(fechaInicialReq2A, horaInicialReq2A);

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 29/3/2017)");
				String fechaFinalReq2A = linea.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 14:25:30)");
				String horaFinalReq2A = linea.next();

				// Datos Fecha/Hora final
				LocalDateTime localDateFin2A = convertirFecha_Hora_LDT(fechaFinalReq2A, horaFinalReq2A);

				//Metodo 2A
				IDoublyLinkedList<VOBike> bicicletasOrdenadas2A = Controller.A2(localDateInicio2A, localDateFin2A);
				for(VOBike b : bicicletasOrdenadas2A)
				{
					System.out.print("Bicicleta Id: " + b.getId() + ", ");
					System.out.print("Total de viajes: "+ b.getNumberOfTrips() + ", ");
					System.out.println("Total distancia recorrida: "+ b.getTotalDistance());
					System.out.println("-----");
				}
				break;

			case 4: //Req 3A
				//Id de la bicicleta
				System.out.println("Ingrese el id de la Bicleta: ");
				int idBicicleta3A = Integer.parseInt(linea.next());


				//Fecha Inicial
				System.out.println("Ingrese la fecha inicial (Ej : 28/3/2017)");
				String fechaInicialReq3A = linea.next();

				//Hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00)");
				String horaInicialReq3A = linea.next();

				// Datos Fecha/Hora inicial
				LocalDateTime localDateInicio3A = convertirFecha_Hora_LDT(fechaInicialReq3A, horaInicialReq3A);

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 29/3/2017)");
				String fechaFinalReq3A = linea.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 14:25:30)");
				String horaFinalReq3A = linea.next();

				// Datos Fecha/Hora final
				LocalDateTime localDateFin3A = convertirFecha_Hora_LDT(fechaFinalReq3A, horaFinalReq3A);

				//Metodo 3A
				IDoublyLinkedList<VOTrip> viajesPorBicicleta = controlador.A3(idBicicleta3A, localDateInicio3A, localDateFin3A);
				for(VOTrip v : viajesPorBicicleta)
				{
					System.out.print("Trip id: "+ v.getid() + ", ");
					System.out.print("Fecha Inicio: "+ v.getStart_time() + ", ");
					System.out.println("Fecha Fin: "+ v.getEnd_time());
					System.out.println("-----");
				}
				break;

			case 5: //Req 4A
				//Id estacion final:
				System.out.println("Ingrese id de la estacion final: ");
				int idEstacionFinal4A = Integer.parseInt(linea.next());

				//Fecha Inicial
				System.out.println("Ingrese la fecha inicial (Ej : 28/3/2017)");
				String fechaInicialReq4A = linea.next();

				//Hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00)");
				String horaInicialReq4A = linea.next();

				// Datos Fecha/Hora inicial
				LocalDateTime localDateInicio4A = convertirFecha_Hora_LDT(fechaInicialReq4A, horaInicialReq4A);

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 29/3/2017)");
				String fechaFinalReq4A = linea.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 14:25:30)");
				String horaFinalReq4A = linea.next();

				// Datos Fecha/Hora final
				LocalDateTime localDateFin4A = convertirFecha_Hora_LDT(fechaFinalReq4A, horaFinalReq4A);

				//Metodo 4A
				IDoublyLinkedList<VOTrip> viajesEstacionFinal = controlador.A4(idEstacionFinal4A, localDateInicio4A, localDateFin4A);
				for(VOTrip v : viajesEstacionFinal)
				{
					System.out.print("Trip ID: "+ v.getid() + ", ");
					System.out.print("Bike ID: "+ v.getBikeid() + ", ");
					System.out.println("Fecha terminacion: " + v.getEnd_time());
					System.out.println("-----");
				}
				break;

			case 6: //Req 1B
				//Fecha Inicial
				System.out.println("Ingrese la fecha inicial (Ej : 28/3/2017)");
				String fechaInicialReq1B = linea.next();

				//Hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00)");
				String horaInicialReq1B = linea.next();

				// Datos Fecha/Hora inicial
				LocalDateTime localDateInicio1B = convertirFecha_Hora_LDT(fechaInicialReq1B, horaInicialReq1B);

				//Req 1B
				IQueue<VOStation> estacionesFechaInicio = controlador.B1(localDateInicio1B);
				for(VOStation s : estacionesFechaInicio)
				{
					System.out.print("Estacion id: "+ s.getId() + ", ");
					System.out.print("Estacion Nombre: "+ s.getName() + ", ");
					System.out.println("Fecha de Inicio Operaciones: "+ s.getonline_date());
					System.out.println("-----");
				}
				break;

			case 7: //Req 2B
				//Fecha Inicial
				System.out.println("Ingrese la fecha inicial (Ej : 28/3/2017)");
				String fechaInicialReq2B = linea.next();

				//Hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00)");
				String horaInicialReq2B = linea.next();

				// Datos Fecha/Hora inicial
				LocalDateTime localDateInicio2B = convertirFecha_Hora_LDT(fechaInicialReq2B, horaInicialReq2B);

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 29/3/2017)");
				String fechaFinalReq2B = linea.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 14:25:30)");
				String horaFinalReq2B = linea.next();

				// Datos Fecha/Hora final
				LocalDateTime localDateFin2B = convertirFecha_Hora_LDT(fechaFinalReq2B, horaFinalReq2B);

				//Metodo 2B
				IDoublyLinkedList<VOBike> bicicletasOrdenadasPorDistancia = controlador.B2(localDateInicio2B, localDateFin2B);
				for(VOBike b : bicicletasOrdenadasPorDistancia)
				{
					System.out.print("Bicicleta Id: "+ b.getId() + ", ");
					System.out.print("Distancia Total: " + b.getTotalDistance() + " km, ");
					System.out.println("Viajes Totales: " + b.getNumberOfTrips());
					System.out.println("-----");
				}
				break;

			case 8: //Req 3B
				System.out.println("Ingrese bicicleta Id:");
				int bicicletaId3B = Integer.parseInt(linea.next());

				System.out.println("Ingrese tiempo maximo");
				int tiempoMaximo3B = Integer.parseInt(linea.next());

				System.out.println("Ingrese genero");
				String genero3B = linea.next();

				IDoublyLinkedList<VOTrip> viajesporBicicletaDuracion = controlador.B3(bicicletaId3B, tiempoMaximo3B, genero3B);
				for(VOTrip t : viajesporBicicletaDuracion)
				{
					System.out.print("Trip Id: "+ t.getid() + ", ");
					System.out.print("Fecha inicial: "+ t.getStart_time() + ", ");
					System.out.print("Fecha final: "+ t.getEnd_time() + ", ");
					System.out.println("Duracion viaje: "+ t.getTripDuration());
					System.out.println("-----");
				}
				break;

			case 9: //Req 4B
				System.out.println("Ingrese identificador estacion: ");
				int estacionInicioId = Integer.parseInt(linea.next());

				//Fecha Inicial
				System.out.println("Ingrese la fecha inicial (Ej : 28/3/2017)");
				String fechaInicialReq4B = linea.next();

				//Hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00)");
				String horaInicialReq4B = linea.next();

				// Datos Fecha/Hora inicial
				LocalDateTime localDateInicio4B = convertirFecha_Hora_LDT(fechaInicialReq4B, horaInicialReq4B);

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 29/3/2017)");
				String fechaFinalReq4B = linea.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 14:25:30)");
				String horaFinalReq4B = linea.next();

				// Datos Fecha/Hora final
				LocalDateTime localDateFin4B = convertirFecha_Hora_LDT(fechaFinalReq4B, horaFinalReq4B);

				IDoublyLinkedList<VOTrip> ViajesporEstacionInicial = controlador.B4(estacionInicioId, localDateInicio4B, localDateFin4B);
				for(VOTrip t : ViajesporEstacionInicial)
				{
					System.out.print("Trip Id: "+ t.getid() + ", ");
					System.out.print("Bike Id: "+t.getBikeid() + ", ");
					System.out.println("Fecha Inicio: " + t.getStart_time());
					System.out.println("-----");
				}
				break;

			case 10: //Req 2C
				System.out.println("Ingrese identificador bicicleta: ");
				int bicicletaId = Integer.parseInt(linea.next());

				//Fecha Inicial
				System.out.println("Ingrese la fecha inicial (Ej : 28/3/2017)");
				String fechaInicialReq2C = linea.next();

				//Hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00)");
				String horaInicialReq2C = linea.next();

				// Datos Fecha/Hora inicial
				LocalDateTime localDateInicio2C = convertirFecha_Hora_LDT(fechaInicialReq2C, horaInicialReq2C);

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 2017-02-01)");
				String fechaFinalReq2C = linea.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 14:25:30)");
				String horaFinalReq2C = linea.next();

				// Datos Fecha/Hora final
				LocalDateTime localDateFin2C = convertirFecha_Hora_LDT(fechaFinalReq2C, horaFinalReq2C);

				// Metodo
				IQueue<VOTrip> viajesValidados2C = controlador.C2ViajesValidadosBicicleta(bicicletaId, localDateInicio2C, localDateFin2C);
				if(viajesValidados2C == null)
				{
					System.out.println("No hay viajes en las fechas ingresadas para esta bicicleta");
					break;
				}
				while(!viajesValidados2C.isEmpty())
				{
					VOTrip valido = viajesValidados2C.dequeue();
					System.out.print("Tiempo de terminacion: " + valido.getEnd_time() + ", ");
					System.out.print("Estacion de terminacion: " + valido.getToStationId() + ", ");
					VOTrip invalido = viajesValidados2C.dequeue();
					System.out.print("Tiempo de inicio: "+ invalido.getStart_time() + ", ");
					System.out.println("Estacion de inicio: "+ invalido.getFromStationId());
					System.out.println("-----");
				}
				break;

			case 11: //Req 3C
				System.out.println("Ingrese numero de bicicletas: ");
				int numeroBicicletas3C = Integer.parseInt(linea.next());

				IDoublyLinkedList<VOBike> topBicicletas = controlador.C3BicicletasMasUsadas(numeroBicicletas3C);
				for(VOBike b : topBicicletas)
				{
					System.out.print("Bike Id: "+ b.getId() + ", ");
					System.out.println("Duracion total del viaje: " + b.getTotalDuration() + " km");
				}
				break;

			case 12: //Req 4C
				System.out.println("Ingrese Id de estacion: ");
				int idEstacion4C = Integer.parseInt(linea.next());

				//Fecha Inicial
				System.out.println("Ingrese la fecha inicial (Ej : 28/3/2017)");
				String fechaInicialReq4C = linea.next();

				//Hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00)");
				String horaInicialReq4C = linea.next();

				// Datos Fecha/Hora inicial
				LocalDateTime localDateInicio4C = convertirFecha_Hora_LDT(fechaInicialReq4C, horaInicialReq4C);

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 2017-02-01)");
				String fechaFinalReq4C = linea.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 14:25:30)");
				String horaFinalReq4C = linea.next();

				// Datos Fecha/Hora final
				LocalDateTime localDateFin4C = convertirFecha_Hora_LDT(fechaFinalReq4C, horaFinalReq4C);

				// Metodo
				IDoublyLinkedList<VOTrip> viajesDeEstacion4C = controlador.C4ViajesEstacion(idEstacion4C, localDateInicio4C, localDateFin4C);
				for( VOTrip t : viajesDeEstacion4C)
				{
					System.out.print("Trip Id: "+ t.getid() + ", ");
					System.out.print("Bike Id: "+ t.getBikeid() + ", ");
					if(idEstacion4C == t.getFromStationId())
					{
						System.out.print("Viaje de salida -> Hora de salida: " + t.getStart_time() + ", ");
					}
					if(idEstacion4C == t.getToStationId())
					{
						System.out.print("Viaje de llegada -> Hora de llegada: " + t.getEnd_time());

					}

					//TODO Completar: Informar si el viaje inicia en la estacion y su tiempo de inicio (t.getStartTime()) o 
					//TODO Completar: Informar si el viaje termina en la estacion y su tiempo de final (t.getStopTime()) 
					System.out.println("-----");
				}
				break;

			case 13: //Salir
				fin = true;
				linea.close();
				break;
			}
		}
	}

	private static void printMenu()
	{
		System.out.println("---------ISIS 1206 - Estructuras de Datos----------");
		System.out.println("-------------------- Proyecto 1   - 2018-2 ----------------------");
		System.out.println("Iniciar la Fuente de Datos a Consultar :");
		System.out.println("1. Actualizar la informacion del sistema con una fuente de datos (2017-Q1, 2017-Q2, 2017-Q3, 2017-Q4)");

		System.out.println("\nParte A:\n");
		System.out.println("2. Obtener la cola con todos los viajes de una bicicleta en rango de fecha (1A)");
		System.out.println("3. Obtener las bicicletas ordenadas de mayor a menor por el numero de viajes realizados (2A)");
		System.out.println("4. Obtener los viajes de una bicicleta en un rango de fecha dado (3A)");
		System.out.println("5. Obtener viajes que terminaron en una estacion (4A)");

		System.out.println("\nParte B:\n");
		System.out.println("6. Obtener Cola con las estaciones que comenzaron despues de una fecha (1B)");
		System.out.println("7. Bicicletas ordenadas por distancia total recorrida en un rango de fecha (2B)");
		System.out.println("8. Viajes de una bicicleta con duracion menor a una dada en un rango de fecha (3B)");
		System.out.println("9. Viajes que iniciaron en una estacion en un rango dado. (4B)");


		System.out.println("\nParte C:\n");
		System.out.println("10. Validar viajes de una bicicleta (2C)");
		System.out.println("11. Bicicletas mas usadas segun duracion de viaje (3C)");
		System.out.println("12. Viajes que iniciaron en una estacion. (4C)");
		System.out.println("13. Salir");
		System.out.println("Ingrese el numero de la opcion seleccionada y presione <Enter> para confirmar: (e.g., 1):");

	}

	private static void printMenuCargar()
	{
		System.out.println("-- Que fuente de datos desea agregar a los datos del sistema (carga incremental)?");
		System.out.println("-- 1. 2017-Q1");
		System.out.println("-- 2. 2017-Q2");
		System.out.println("-- 3. 2017-Q3");
		System.out.println("-- 4. 2017-Q4");
		System.out.println("-- 5. Reiniciar datos del sistema");		
		System.out.println("-- Ingrese el numero de la fuente a cargar y presione <Enter> para confirmar: (e.g., 1)");
	}

	/**
	 * Convertir fecha y hora a un objeto LocalDateTime
	 * @param fecha fecha en formato dd/mm/aaaa con dd para dia, mm para mes y aaaa para agno
	 * @param hora hora en formato hh:mm:ss con hh para hora, mm para minutos y ss para segundos
	 * @return objeto LDT con fecha y hora integrados
	 */
	private static LocalDateTime convertirFecha_Hora_LDT(String fecha, String hora)
	{
		String[] datosFecha = fecha.split("/");
		String[] datosHora = hora.split(":");

		int agno = Integer.parseInt(datosFecha[2]);
		int mes = Integer.parseInt(datosFecha[1]);
		int dia = Integer.parseInt(datosFecha[0]);
		int horas = Integer.parseInt(datosHora[0]);
		int minutos = Integer.parseInt(datosHora[1]);
		int segundos = Integer.parseInt(datosHora[2]);

		return LocalDateTime.of(agno, mes, dia, horas, minutos, segundos);
	}

}
