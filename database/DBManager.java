package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
	private final static String url = "jdbc:oracle:thin:@//localhost:1521/xe";
	private final static String username = "hr";
	private final static String password = "hr";

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver not found.");
			System.exit(1);
		}
	}

	public static <R> R run(Task<R> task, String sql) {
		R result = null;
		try (Connection conn = DriverManager.getConnection(url, username, password)){
			conn.setAutoCommit(false);
			
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				result = task.execute(stmt);
				conn.commit();
				return result;
			} catch (Exception ex) {
				System.err.println("Cannot execute a statement : "
						+ ex.getMessage());
				conn.rollback();
				throw new RuntimeException(ex);
			} finally {
				if (stmt != null)
					stmt.close();
			}
		} catch (Exception ex) {
			System.err.println("Cannot open a connection : " + ex.getMessage());
			throw new RuntimeException(ex);
		}
	}

	public static <R, B> List<B> run(Query query, ResultSetToBean<B> converter,
			String sql) {
		try (Connection conn = DriverManager.getConnection(url, username, password)){

			List<B> list = new ArrayList<B>();
			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(sql);
				query.prepareQuery(stmt);
				ResultSet rs = stmt.executeQuery();
				try {
					while (rs.next()) {
						list.add(converter.convert(rs));
					}
					return list;
				} catch (Exception ex) {
					System.err.println("Cannot convert bean : "
							+ ex.getMessage());
					throw new RuntimeException(ex);
				} finally {
					rs.close();
				}
			} catch (Exception ex) {
				System.err.println("Cannot execute a statement : "
						+ ex.getMessage());
				throw new RuntimeException(ex);
			} finally {
				if (stmt != null)
					stmt.close();
			}
		} catch (Exception ex) {
			System.err.println("Cannot open a connection : " + ex.getMessage());
			throw new RuntimeException(ex);
		}
	}
}
