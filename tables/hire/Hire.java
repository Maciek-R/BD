package tables.hire;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import database.DBManager;
import database.Query;
import database.ResultSetToBean;
import tables.car.Car;
import tables.client.Client;

public class Hire {
	
	private int id_klient;
	private String nr_rejestracyjny;
	private Date od;
	private Date doKiedy;
	private float koszt;
	private int id_status;
	
	public int getId_klient() {
		return id_klient;
	}

	public void setId_klient(int id_klient) {
		this.id_klient = id_klient;
	}

	public Date getOd() {
		return od;
	}

	public void setOd(Date od) {
		this.od = od;
	}

	public Date getDoKiedy() {
		return doKiedy;
	}

	public void setDoKiedy(Date doKiedy) {
		this.doKiedy = doKiedy;
	}

	public float getKoszt() {
		return koszt;
	}

	public void setKoszt(float koszt) {
		this.koszt = koszt;
	}

	public int getId_status() {
		return id_status;
	}

	public void setId_status(int id_status) {
		this.id_status = id_status;
	}

	public static database.ResultSetToBean<Hire> getWypozyczenieconverter() {
		return wypozyczenieConverter;
	}
	
	public void setNr_rejestracyjny(String nr_rejestracyjny) {
		this.nr_rejestracyjny = nr_rejestracyjny;
	}

	public Hire(int klient, String rejestracja, Date from, Date to, float cost, int status){
		id_klient = klient;
		nr_rejestracyjny = rejestracja;
		od = from;
		doKiedy = to;
		koszt = cost;
		id_status = status;
	}
	
	public Hire(){	
	}
	
	public String toString() {
		return String.format("Wypozyczenie(%d, %s, %s, %s, %.2f, %d)",id_klient, nr_rejestracyjny, od, doKiedy, koszt, id_status); 
	}
	
	public final static ResultSetToBean<Hire> wypozyczenieConverter = new ResultSetToBean<Hire>() {
		public Hire convert(ResultSet rs) throws Exception {
			Hire e = new Hire();
			e.setId_klient(rs.getInt("ID_KLIENT"));
			e.setNr_rejestracyjny(rs.getString("NR_REJESTRACYJNY"));
			e.setOd(rs.getDate("OD"));
			e.setDoKiedy(rs.getDate("DO"));
			e.setKoszt(rs.getFloat("KOSZT"));
			e.setId_status(rs.getInt("ID_STATUS"));
			return e;
		}
	};

	public boolean validate() {
		List<Car> samochody = DBManager
				.run(new Query() {
					public void prepareQuery(PreparedStatement ps)
							throws Exception {
						ps.setString(1, nr_rejestracyjny);
					}
				}, Car.carConverter,
						"select Nr_Rejestracyjny, Przebieg, ID_Model, Rocznik, ID_Parking from Samochod where Nr_Rejestracyjny = ?");
		
		System.out.println(nr_rejestracyjny);
		
		if(samochody.isEmpty())
		{
			System.out.println("Wypozyczenie: nie istnieje taka rejestracja");
			return false;
		}
		List<Client> klient = DBManager.run(new Query() {
			public void prepareQuery(PreparedStatement ps)
					throws Exception {
				ps.setInt(1, id_klient);		
			}
		}, Client.clientConverter,
				"select ID_Klient, Imie, Nazwisko, Nr_Dowodu, Adres, Nr_Telefonu from Klient where ID_Klient = ?");
		
		if(klient.isEmpty())
		{
			System.out.println("Wypozyczenie: nie istnieje taki klient");
			return false;
		}
		
		List<Hire> poz = DBManager.run(new Query() {
			public void prepareQuery(PreparedStatement ps)
					throws Exception {
				ps.setString(1, nr_rejestracyjny);
				
			}
		}, Hire.wypozyczenieConverter,
				"select Nr_Rejestracyjny, ID_Klient, od, do, Koszt, ID_Status from Wypozyczenie where nr_rejestracyjny = ?");
		
		if(!poz.isEmpty())
		{
			System.out.println("Wypozyczenie: istnieje takie wypozyczenie ");
			return false;
		}
		return true;
	}
}
