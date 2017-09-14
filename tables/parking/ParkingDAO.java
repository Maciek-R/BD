package tables.parking;

import java.sql.PreparedStatement;
import java.util.List;

import database.DBManager;
import database.Query;
import database.Task;
import tables.car.CarDAO;

public class ParkingDAO {


	public static void insert(final int id, final String adres, final int pojemnosc, final float stan) {
		Parking parking = findById(id);
		if (parking == null) {
			boolean result = DBManager.run(new Task<Boolean>() {
				public Boolean execute(PreparedStatement ps) throws Exception {
					ps.setInt(1, id);
					ps.setString(2, adres);
					ps.setInt(3, pojemnosc);
					ps.setFloat(4, stan);
					return ps.executeUpdate() > 0;
				}
			}, "insert into Parking values(?, ?, ?, ?)");
			System.out.println("rekord zostal stworzony");
		} else {
			System.out.println("parking o podanym id juz istnieje");
		}
	}

	public static void update(final int id, final String adres, final int pojemnosc, final float stan){
		Parking parking = findById(id);
		if (parking != null) {
			boolean result = DBManager.run(new Task<Boolean>() {
				public Boolean execute(PreparedStatement ps) throws Exception {
					ps.setFloat(1, stan);
					ps.setString(2, adres);
					ps.setInt(3, pojemnosc);
					ps.setInt(4, id);
					return ps.executeUpdate() > 0;
				}
			}, "update Parking set Stan = ?, adres = ?, Pojemnosc = ? where ID_Parking = ?");
			System.out.println("Rekord zostal zaktualizowany");
		} else {
			System.out.println("Parking o podanym id nie istnieje");
		}
	}
	
	public static void delete(final int id){
		int counter = CarDAO.getNumberOfCarsInParking(id);
		Parking parking = findById(id);
		if(counter !=0) {
			System.out.println("Na parkingu znajduja sie samochody, nie mozna go usunac");
			return;
		}
		if (parking != null) {
			boolean result = DBManager.run(new Task<Boolean>() {
				public Boolean execute(PreparedStatement ps) throws Exception {
					ps.setInt(1, id);
					;
					return ps.executeUpdate() > 0;
				}
			}, "delete from Parking where ID_Parking = ?");
			System.out.println("rekord pomyslnie usuniety");
		} else {
			System.out.println("Parking o danym Id nie istnieje, rekord nie zostal usuniety");
		}
	}
	
	public static Parking findById(final int id){
		String ID = Integer.toString(id);
		List<Parking> parkingi = DBManager
				.run(new Query() {
					public void prepareQuery(PreparedStatement ps)
							throws Exception {
						//ps.setInt(1, 2);
					}
				}, Parking.parkingConverter,
						"select ID_Parking, Adres, Pojemnosc, Stan from Parking where ID_Parking="+ID);
		if(parkingi.size() == 0) {
			return null;
		}
		
		return parkingi.get(0);
	}
	
	public static void findAll(){
		List<Parking> parkingi = DBManager
		.run(new Query() {
			public void prepareQuery(PreparedStatement ps)
					throws Exception {
				//ps.setInt(1, 2);
			}
		}, Parking.parkingConverter,
				"select ID_Parking, Adres, Pojemnosc, Stan from Parking");
	
		for (Parking e: parkingi) {
			System.out.println(e);
		}
	}
}
