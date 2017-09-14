package tables.model;

import java.sql.PreparedStatement;
import java.util.List;

import database.DBManager;
import database.Query;
import database.Task;

public class ModelDAO {


	public static void insert(final int id, final String wytworca, final String nazwaModelu, final int rok, final int typID) {
		Model model = ModelDAO.findById(id);
		if (model == null) {
			boolean result = DBManager.run(new Task<Boolean>() {
				public Boolean execute(PreparedStatement ps) throws Exception {
					ps.setInt(1, id);
					ps.setString(2, wytworca);
					ps.setString(3, nazwaModelu);
					ps.setInt(4, rok);
					ps.setInt(5, typID);
					return ps.executeUpdate() > 0;
				}
			}, "insert into Model values(?, ?, ?, ?, ?)");
			System.out.println("rekodr pomyslnie dodany");
		} else {
			System.out.println("model o podanym id juz istnieje");
		}
	}
	
	public static void delete(final int id) {
		Model model = ModelDAO.findById(id);
		if (model != null) {
			boolean result = DBManager.run(new Task<Boolean>() {
				public Boolean execute(PreparedStatement ps) throws Exception {
					ps.setInt(1, id);
					;
					return ps.executeUpdate() > 0;
				}
			}, "delete from Model where ID_Model = ?");
			System.out.println("rekord pomyslnie usuniety");
		} else {
			System.out.println("model o podanym id nie istnieje");
		}
	}
	
	public static Model findById(int id){
		String ID = Integer.toString(id);
		List<Model> modele = DBManager.run(new Query() {
			public void prepareQuery(PreparedStatement ps)
					throws Exception {
				//ps.setInt(1, 2);
			}
		}, Model.modelConverter,
				"select ID_Model, Wytworca, Nazwa_Modelu, Rok, ID_Typ from Model where ID_Model="+ID);
		if(modele.size() == 0 ) {
			return null;
		}
		return modele.get(0);
	}
	
	public static void findAll() {
		List<Model> modele = DBManager.run(new Query() {
			public void prepareQuery(PreparedStatement ps)
					throws Exception {
				//ps.setInt(1, 2);
			}
		}, Model.modelConverter,
				"select ID_Model, Wytworca, Nazwa_Modelu, Rok, ID_Typ from Model");
		
		for (Model e: modele) {
			System.out.println(e);
		}
	}
}
