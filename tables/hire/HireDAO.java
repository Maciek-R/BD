package tables.hire;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.List;

import database.DBManager;
import database.Query;
import database.Task;
import tables.car.Car;
import tables.car.CarDAO;

public class HireDAO {


	public static void insert(final String Nr_Rejestracyjny, final int id_klienta, final Date start, final Date koniec, final float kosztWyp, final int status) {
		Hire wyp = new Hire(id_klienta, Nr_Rejestracyjny, start, koniec, kosztWyp, status);
		
		boolean test = wyp.validate();
		if(!test){
			
			System.out.println("Nie ma");
			
		}
		else{ 
			
		
		boolean result = DBManager.run(new Task<Boolean>() {
			public Boolean execute(PreparedStatement ps) throws Exception {
				ps.setInt(1, id_klienta);
				ps.setString(2, Nr_Rejestracyjny );
				ps.setDate(3, start);
				ps.setDate(4, koniec);
				ps.setFloat(5, kosztWyp);
				ps.setInt(6, status);
				return ps.executeUpdate() > 0;
			}
		}, "insert into Wypozyczenie values(?, ?, ?, ?, ?, ?)");
		
		Car s = CarDAO.getCarByRegisterNumber(Nr_Rejestracyjny);
		CarDAO.updateCar(Nr_Rejestracyjny, s.getPrzebieg(), s.getID_Model(), s.getRocznik(), 0);
		}
	}
	
	public static void delete(final String rejestracja) {
		if (findById(rejestracja) != null) {
			boolean result = DBManager.run(new Task<Boolean>() {
				public Boolean execute(PreparedStatement ps) throws Exception {
					ps.setString(1, rejestracja);
					return ps.executeUpdate() > 0;
				}
			}, "delete from Wypozyczenie where Nr_Rejestracyjny = ?");
			System.out.println("rekord usuniety");
		} else {
			System.out.println("rekord o podanym id nie istnieje");
		}
	}
	
	public static Hire findById(String id) {
		List<Hire> wypozyczenie = DBManager
				.run(new Query() {
					public void prepareQuery(PreparedStatement ps)
							throws Exception {
						//ps.setInt(1, 2);
					}
				}, Hire.wypozyczenieConverter,
						"select ID_Klient, Nr_Rejestracyjny, od, do, koszt, ID_Status from Wypozyczenie where nr_rejestracyjny="+id);
		if(wypozyczenie.size() == 0 ) {
			return null;
		}
		return wypozyczenie.get(0);
	}
	
	public static void findAll() {
		List<Hire> wypozyczenie = DBManager
		.run(new Query() {
			public void prepareQuery(PreparedStatement ps)
					throws Exception {
			}
		}, Hire.wypozyczenieConverter,
				"select ID_Klient, Nr_Rejestracyjny, od, do, koszt, ID_Status from Wypozyczenie");
	}

	private static void saveToFile(List<Hire> hires) {
		// TODO Auto-generated method stub
		
		for (Hire e: hires) {
			System.out.println(e);
		}
		
		BufferedWriter bw;
		try {
			
			SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd-kk_mm_ss");
			String data = simpleDateHere.format(new java.util.Date());
			String s = new String("Raporty/Wypozyczenia/"+data+".txt");
	        
			FileWriter fw = new FileWriter(new File(s), true);
			bw = new BufferedWriter(fw);
			for (Hire e: hires) {
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
}
