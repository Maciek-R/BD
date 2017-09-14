package tables.car;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import database.DBManager;
import database.Query;
import database.Task;
import tables.parking.Parking;
import tables.parking.ParkingDAO;

public class CarDAO {

	public static void insert(final String Nr_Rejestracyjny, final int Przebieg, final int ID_Model, final int Rocznik, final int ID_Parking) {
		boolean result = DBManager.run(new Task<Boolean>() {
			public Boolean execute(PreparedStatement ps) throws Exception {
				ps.setString(1, Nr_Rejestracyjny );
				ps.setInt(2, Przebieg);
				ps.setInt(3, ID_Model);
				ps.setInt(4, Rocznik);
				ps.setInt(5, ID_Parking);
				return ps.executeUpdate() > 0;
			}
		}, "insert into Samochod values(?, ?, ?, ?, ?)");
	}
	
	public static void delete(final String id) {
		boolean result = DBManager.run(new Task<Boolean>() {
			public Boolean execute(PreparedStatement ps) throws Exception {
				ps.setString(1, id);
				return ps.executeUpdate() > 0;
			}
		}, "delete from Samochod where Nr_Rejestracyjny = ?");	
	}
	
	public static void getFreeCars() {
		List<Car> cars = DBManager
		.run(new Query() {
			public void prepareQuery(PreparedStatement ps)
					throws Exception {
				//ps.setInt(1, 2);
			}
		}, Car.carConverter,
				"select Nr_Rejestracyjny, Przebieg, ID_Model, Rocznik, ID_Parking from Samochod where ID_Parking=0");

		for (Car e: cars) {
			System.out.println(e);
		}
		
		saveToFile(cars, "Wypozyczenia");
	}
	
	public static void addCar(final String Nr_Rejestracyjny, final int Przebieg, final int ID_Model, final int Rocznik, final int ID_Parking) {
		Parking parking = ParkingDAO.findById(ID_Parking);
		System.out.println(parking.getStan());
		
		if(carWithIdExist(Nr_Rejestracyjny)){
			System.out.println("Samochod z taka rejestracaja istnieje, rekord nie zostal dodany");
			return;
		} 
		if(parking.getPojemnosc() <= parking.getStan()){
			System.out.println("Na tym parkingu nie ma miejsca, rekord nie zostal dodany");
			return;
		}
			System.out.println("idzie dalej");
			CarDAO.insert(Nr_Rejestracyjny, Przebieg, ID_Model, Rocznik, ID_Parking);
			
			ParkingDAO.update(parking.getId(), parking.getAdres(), parking.getPojemnosc(), parking.getStan()+1);
			
			System.out.println("Rekord dodany pomyslnie");
	}
	
	public static void deleteCar(final String id) {
		Car samochod = getCarByRegisterNumber(id);
		if(samochod == null) {
			System.out.println("Samochod o danej rejestracji nie istnieje, rekord nie zostal usuniety");
			return;
		}
		Parking parking = ParkingDAO.findById(samochod.getID_Parking());
		CarDAO.delete(id);
		ParkingDAO.update(parking.getId(), parking.getAdres(), parking.getPojemnosc(), parking.getStan()+1);
		System.out.println("rekord zostal usuniety");
	}
	
	public static int getNumberOfCarsInParking(int parkingID) {
		String id = Integer.toString(parkingID);
		Parking parking = ParkingDAO.findById(parkingID);
		if (parking != null) {
			List<Car> samochod = DBManager.run(new Query() {
				public void prepareQuery(PreparedStatement ps) throws Exception {
					// ps.setInt(1, 2);
				}
			}, Car.carConverter,
					"select Nr_Rejestracyjny, Przebieg, ID_Model, Rocznik, ID_Parking from Samochod where ID_Parking="
							+ id);
			return samochod.size();
		} else {
			return 0;
		}
	}
	
	public static void getCarsFromParking(int parkingID) {
		String id = Integer.toString(parkingID);
		Parking parking = ParkingDAO.findById(parkingID);
		if (parking != null) {
			List<Car> samochod = DBManager.run(new Query() {
				public void prepareQuery(PreparedStatement ps) throws Exception {
					// ps.setInt(1, 2);
				}
			}, Car.carConverter,
					"select Nr_Rejestracyjny, Przebieg, ID_Model, Rocznik, ID_Parking from Samochod where ID_Parking="+ id);
			
			
			saveToFile(samochod, "SamochodyNaDanymParkingu");
		}
	}
	
	private static void saveToFile(List<Car> samochod, String name) {
		
		for (Car e: samochod) {
			System.out.println(e);
		}
		
		BufferedWriter bw;
		try {
			
			SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd-kk_mm_ss");
			String data = simpleDateHere.format(new Date());
			String s = new String("Raporty/"+name+"/"+data+".txt");
	        
			FileWriter fw = new FileWriter(new File(s), true);
			bw = new BufferedWriter(fw);
			for (Car e: samochod) {
				String ss = new String(e.toString());
				bw.append(ss);
				bw.newLine();
			}
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Car getCarByRegisterNumber(String nr_rejestracyjny){
		List<Car> samochody = DBManager
				.run(new Query() {
					public void prepareQuery(PreparedStatement ps)
							throws Exception {
						//ps.setInt(1, 2);
					}
				}, Car.carConverter,
						"select Nr_Rejestracyjny, Przebieg, ID_Model, Rocznik, ID_Parking from Samochod where Nr_Rejestracyjny="+new String("'"+nr_rejestracyjny+"'"));
		if(samochody.size() == 0 ) {
			return null;
		}
		return samochody.get(0);
	}
	
	public static void getAllCars() {
		List<Car> samochod = DBManager
		.run(new Query() {
			public void prepareQuery(PreparedStatement ps)
					throws Exception {
				//ps.setInt(1, 2);
			}
		}, Car.carConverter,
				"select Nr_Rejestracyjny, Przebieg, ID_Model, Rocznik, ID_Parking from Samochod");

		for (Car e: samochod) {
			System.out.println(e);
		}
	}
	
	
	public static boolean carWithIdExist(String nr_rejestracyjny) {
		List<Car> samochod = DBManager
				.run(new Query() {
					public void prepareQuery(PreparedStatement ps)
							throws Exception {
						//ps.setInt(1, 2);
					}
				}, Car.carConverter,
						"select Nr_Rejestracyjny, Przebieg, ID_Model, Rocznik, ID_Parking from Samochod where Nr_Rejestracyjny="+new String("'"+nr_rejestracyjny+"'"));
		if(samochod.size() != 0) return true;
		return false;
	}
	
	public static void updateCar(final String Nr_Rejestracyjny, final int Przebieg, final int ID_Model, final int Rocznik, final int ID_Parking){
		Car samochod = CarDAO.getCarByRegisterNumber(Nr_Rejestracyjny);
		if(samochod != null) {
		boolean result = DBManager.run(new Task<Boolean>() {
			public Boolean execute(PreparedStatement ps) throws Exception {
				ps.setInt(1, Przebieg);
				ps.setInt(2, ID_Model);
				ps.setInt(3, Rocznik);
				ps.setInt(4, ID_Parking);
				ps.setString(5, Nr_Rejestracyjny );
				return ps.executeUpdate() > 0;
			}
		}, "update Samochod set Przebieg = ?, ID_Model = ?, Rocznik = ?, ID_Parking = ? where Nr_Rejestracyjny = ?");
		System.out.println("rekord zostal zaktualizowany pomyslenie");
		} else {
			System.out.println("rekord o danym id nie istnieje");
		}
	}
}
