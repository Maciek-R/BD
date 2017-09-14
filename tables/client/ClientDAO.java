package tables.client;

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

public class ClientDAO {

	public static void insert(final int id_klient, final String imie, final String nazwisko, final String nr_dowodu, final String adres, final int nr_telefonu){
		if(ClientDAO.findById(id_klient) == null) {
		boolean result = DBManager.run(new Task<Boolean>() {
			public Boolean execute(PreparedStatement ps) throws Exception {
				ps.setInt(1, id_klient);
				ps.setString(2, imie);
				ps.setString(3, nazwisko);
				ps.setString(4, nr_dowodu);
				ps.setString(5, adres);
				ps.setInt(6, nr_telefonu);
				return ps.executeUpdate() > 0;
			}
		}, "insert into Klient values(?, ?, ?, ?, ?, ?)");
			System.out.println("Klient dodany pomyslnie");
		} else {
			System.out.println("Klient o danym id juz istnije");
		}
	}
	
	public static void delete(final int id){
		Client klient = findById(id);
		if(klient != null) {
		boolean result = DBManager.run(new Task<Boolean>() {
			public Boolean execute(PreparedStatement ps) throws Exception {
				ps.setInt(1, id);;
					return ps.executeUpdate() > 0;
				}
			}, "delete from Klient where id_klient = ?");
			System.out.println("Rekord pomyslnie usuniety");
		} else {
			System.out.println("Klient o danym id nie istnieje, rekord nie zostal usuniety");
		}
		
	}
	
	public static Client findById(int id) {
		String ID = Integer.toString(id);
		List<Client> klienci = DBManager
				.run(new Query() {
					public void prepareQuery(PreparedStatement ps)
							throws Exception {
						//ps.setInt(1, 2);
					}
				}, Client.clientConverter,
						"select ID_Klient, Imie, Nazwisko, Nr_Dowodu, Adres, Nr_Telefonu from Klient where ID_Klient="+ID);
		if(klienci.size() == 0 ) {
			return null;
		}
		return klienci.get(0);
	}
	
	public static void findAll(){
		List<Client> klienci = DBManager
		.run(new Query() {
			public void prepareQuery(PreparedStatement ps)
					throws Exception {
				//ps.setInt(1, 2);
			}
		}, Client.clientConverter,
				"select ID_Klient, Imie, Nazwisko, Nr_Dowodu, Adres, Nr_Telefonu from Klient");
	
		saveToFile(klienci);
		/*for (Klient e: klienci) {
			System.out.println(e);
		}*/
	}
	
	public static void update(final int id_klient, final String imie, final String nazwisko, final String Nr_dowodu, final String adres, final int nr_tel){
		Client klient = ClientDAO.findById(id_klient);
		if(klient != null) {
		boolean result = DBManager.run(new Task<Boolean>() {
			public Boolean execute(PreparedStatement ps) throws Exception {
				ps.setString(1, imie);
				ps.setString(2, nazwisko);
				ps.setString(3, Nr_dowodu);
				ps.setString(4, adres);
				ps.setInt(5, nr_tel);
				ps.setInt(6, id_klient );
				return ps.executeUpdate() > 0;
			}
		}, "update Klient set imie = ?, nazwisko = ?, Nr_dowodu = ?, adres = ?, Nr_telefonu = ? where id_klient = ?");
		System.out.println("rekord zostal zaktualizowany pomyslenie");
		} else {
			System.out.println("rekord o danym id nie istnieje");
		}
	}
	private static void saveToFile(List<Client> clients) {
		for (Client e: clients) {
			System.out.println(e);
		}
		
		BufferedWriter bw;
		try {
			
			SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd-kk_mm_ss");
			String data = simpleDateHere.format(new Date());
			String s = new String("Raporty/Klienci/"+data+".txt");
	        
			FileWriter fw = new FileWriter(new File(s), true);
			bw = new BufferedWriter(fw);
			for (Client e: clients) {
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
