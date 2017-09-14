package tables.car;

import java.sql.ResultSet;
import database.ResultSetToBean;

public class Car {
	private String Nr_Rejestracyjny;
	private int Przebieg;
	private int ID_Model;
	private int Rocznik;
	private int ID_Parking;
	
	public String getNr_Rejestracyjny() {
		return Nr_Rejestracyjny;
	}

	public void setNr_Rejestracyjny(String nr_Rejestracyjny) {
		Nr_Rejestracyjny = nr_Rejestracyjny;
	}

	public int getPrzebieg() {
		return Przebieg;
	}

	public void setPrzebieg(int przebieg) {
		Przebieg = przebieg;
	}

	public int getID_Model() {
		return ID_Model;
	}

	public void setID_Model(int iD_Model) {
		ID_Model = iD_Model;
	}

	public int getRocznik() {
		return Rocznik;
	}

	public void setRocznik(int rocznik) {
		Rocznik = rocznik;
	}

	public int getID_Parking() {
		return ID_Parking;
	}

	public void setID_Parking(int iD_Parking) {
		ID_Parking = iD_Parking;
	}
	
	public Car(){
	}
	
	public String toString() {
		return String.format("Samochod(%s, %d, %d, %d, %d)", Nr_Rejestracyjny, Przebieg, ID_Model, Rocznik, ID_Parking); 
	}
	
	public Car(String nrRejestr, int przebieg, int modelID, int rocznik, int ParkingID) {
		Nr_Rejestracyjny = nrRejestr;
		Przebieg = przebieg;
		ID_Model = modelID;
		Rocznik = rocznik;
		ID_Parking = ParkingID;
	}
	
	public final static ResultSetToBean<Car> carConverter = new ResultSetToBean<Car>() {
		public Car convert(ResultSet rs) throws Exception {
			Car e = new Car();
			e.setNr_Rejestracyjny(rs.getString("Nr_Rejestracyjny"));
			e.setPrzebieg(rs.getInt("Przebieg"));
			e.setID_Model(rs.getInt("ID_Model"));
			e.setRocznik(rs.getInt("Rocznik"));
			e.setID_Parking(rs.getInt("ID_Parking"));
			return e;
		}
	};
	
	private boolean Validate() {
		if(Nr_Rejestracyjny.length() != 6){
			System.out.println("Samochod: numer rejestracyjny jest nieprawidlowy");
			return false;
		}
		if(Przebieg > 1000000) {
			System.out.println("Samochod: przebieg jest nieprawidlowy");
			return false;
		}
		if(ID_Model > 100  && ID_Model < 0) {
			System.out.println("Samochod: ID Modelu jest nieprawidlowy");
			return false;
		}
		if(Rocznik > 2017  && ID_Model < 1950) {
			System.out.println("Samochod: Rocznik jest nieprawidlowy");
			return false;
		}
		if(ID_Parking < 0  && ID_Parking > 100) {
			System.out.println("Samochod: ID parkingu jest nieprawidlowy");
			return false;
		}
		return true;
	}
}
