package tables.parking;

import java.sql.ResultSet;
import database.ResultSetToBean;

public class Parking {
	private int id;
	private String adres;
	private int pojemnosc;
	private int stan;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public int getPojemnosc() {
		return pojemnosc;
	}

	public void setPojemnosc(int pojemnosc) {
		this.pojemnosc = pojemnosc;
	}

	public int getStan() {
		return stan;
	}

	public void setStan(int stan) {
		this.stan = stan;
	}

	public Parking(int id, String adres, int pojem, int stan) {
		super();
		this.id = id;
		this.adres = adres;
		this.stan = stan;
	}

	public Parking(){
		
	}
	
	public String toString() {
		return String.format("Parking(%d, %s, %d, %d)", id, adres, pojemnosc, stan); 
	}
	
	public final static ResultSetToBean<Parking> parkingConverter = new ResultSetToBean<Parking>() {
		public Parking convert(ResultSet rs) throws Exception {
			Parking e = new Parking();
			e.setId(rs.getInt("ID_Parking"));
			e.setAdres(rs.getString("Adres"));
			e.setPojemnosc(rs.getInt("Pojemnosc"));
			e.setStan(rs.getInt("Stan"));
			return e;
		}
	};
}
