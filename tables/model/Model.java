package tables.model;

import java.sql.ResultSet;
import database.ResultSetToBean;

public class Model {
	private int ID_Model;
	private String Wytwórca;
	private String Nazwa_Modelu;
	private int Rok;
	private int ID_Typ;
	
	public Model(int id, String wytworca, String nazwaModelu, int rok, int typID) {
		ID_Model = id;
		Wytwórca = wytworca;
		Nazwa_Modelu = nazwaModelu;
		Rok = rok;
		ID_Typ = typID;
	}
	
	public Model(){
		
	}
	
	public int getID_Model() {
		return ID_Model;
	}
	public void setID_Model(int iD_Model) {
		ID_Model = iD_Model;
	}
	public String getWytwórca() {
		return Wytwórca;
	}
	public void setWytwórca(String wytwórca) {
		Wytwórca = wytwórca;
	}
	public String getNazwa_Modelu() {
		return Nazwa_Modelu;
	}
	public void setNazwa_Modelu(String nazwa_Modelu) {
		Nazwa_Modelu = nazwa_Modelu;
	}
	public int getRok() {
		return Rok;
	}
	public void setRok(int rok) {
		Rok = rok;
	}
	public int getID_Typ() {
		return ID_Typ;
	}
	public void setID_Typ(int iD_Typ) {
		ID_Typ = iD_Typ;
	}
	
	
	public String toString() {
		return String.format("Model(%d, %s, %s, %d, %d)", ID_Model, Wytwórca, Nazwa_Modelu, Rok, ID_Typ); 
	}
	
	public final static ResultSetToBean<Model> modelConverter = new ResultSetToBean<Model>() {
		public Model convert(ResultSet rs) throws Exception {
			Model e = new Model();
			e.setID_Model(rs.getInt("ID_Model"));
			e.setWytwórca(rs.getString("Wytworca"));
			e.setNazwa_Modelu(rs.getString("Nazwa_Modelu"));
			e.setRok(rs.getInt("Rok"));
			e.setID_Typ(rs.getInt("ID_Typ"));
			return e;
		}
	};
}
