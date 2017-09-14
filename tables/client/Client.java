package tables.client;

import java.sql.ResultSet;
import database.ResultSetToBean;

public class Client {

	private int id_klient;
	private String imie;
	private String nazwisko;
	private String nr_dowodu;
	private String adres;
	private int nr_telefonu;

	public Client(int id, String name, String surname, String nr_id, String adresWstaw, int nr_tel){
		id_klient = id;
		imie = name;
		nazwisko = surname;
		nr_dowodu = nr_id;
		adres = adresWstaw;
		nr_telefonu = nr_tel;
	}
	
	public Client(){
	}
	
	public String toString() {
		return String.format("Klient(%d, %s, %s, %s, %s, %d)", id_klient, imie, nazwisko, nr_dowodu, adres, nr_telefonu); 
	}
	
	public final static ResultSetToBean<Client> clientConverter = new ResultSetToBean<Client>() {
		public Client convert(ResultSet rs) throws Exception {
			Client e = new Client();
			e.setId_klient(rs.getInt("id_klient"));
			e.setImie(rs.getString("imie"));
			e.setNazwisko(rs.getString("nazwisko"));
			e.setNr_dowodu(rs.getString("nr_dowodu"));
			e.setAdres(rs.getString("adres"));
			e.setNr_telefonu(rs.getInt("nr_telefonu"));
			return e;
		}
	};
	
	public int getId_klient() {
		return id_klient;
	}
	public void setId_klient(int id_klient) {
		this.id_klient = id_klient;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getNr_dowodu() {
		return nr_dowodu;
	}
	public void setNr_dowodu(String nr_dowodu) {
		this.nr_dowodu = nr_dowodu;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public int getNr_telefonu() {
		return nr_telefonu;
	}
	public void setNr_telefonu(int nr_telefonu) {
		this.nr_telefonu = nr_telefonu;
	}
}
