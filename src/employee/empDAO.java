package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class empDAO {
	Connection conn = null;
	public Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			conn = DriverManager.getConnection(url,"hr","hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<Employee> getEmpList() {
		conn = getConnect();
		List<Employee> list = new ArrayList<>();
		try {
			String sql = "select employee_id, first_name, email, salary from employees";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Employee emp = new Employee(
						rs.getInt("employee_id"),rs.getString("first_name"),
						rs.getNString("email"), rs.getInt("salary")
				);
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
