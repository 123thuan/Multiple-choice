package game;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import javax.swing.JComboBox;

import com.mysql.jdbc.Statement;


public class Database {
	static String url = "jdbc:mysql://localhost:3306/tracnghiem?characterEncoding=utf8";
	static String username = "root";
	static String password = "";
	static Connection connection;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement pstmt;
	
	public Database()
	{
		try{
			connection = DriverManager.getConnection(url, username, password);
			stmt = (Statement) connection.createStatement();
			
		}catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		//return connection;
		
		
	}
	
	public ResultSet select(String sql)
	{
		try {
			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public Boolean insertCategory(String sql, String name)
	{
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			//stmt.executeQuery(sql);
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public int insertQuestion(String sql, String question, int category_id)
	{
		try {
			pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, question);
			pstmt.setInt(2, category_id);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				return rs.getInt(1);
			}
			
			return 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public Boolean insertAnswer(String answer, int question_id, Boolean isCorrect)
	{
		String sql1 = "INSERT INTO Answer(name,question_id) VALUES(?,?)";
		String sql2 = "INSERT INTO Question_Answer(question_id,answer_id) VALUES(?,?)";
		try {
			pstmt = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, answer);
			pstmt.setInt(2, question_id);
			pstmt.executeUpdate();
			if(isCorrect)
			{
				rs = pstmt.getGeneratedKeys();
				if(rs.next())
				{
					pstmt = connection.prepareStatement(sql2);
					pstmt.setInt(1, question_id);
					pstmt.setInt(2, rs.getInt(1));
					pstmt.execute();
				}
			}
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public int getIDofQuestion(String q)
	{
		try {
			String sql = "SELECT id from Question WHERE name = '"+q+"'";
			int a = 0;
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
			    a = rs.getInt("id");
			}
			System.out.print(sql);
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getIDofAnswer(String a)
	{
		try {
			String sql = "SELECT id from Answer WHERE name = '"+a+"'";
			rs = stmt.executeQuery(sql);
			int id = 0;
			while (rs.next()) {
			    id = rs.getInt("id");
			}
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void fillComboxBox(JComboBox cbx, ResultSet rs, TreeMap categoryMap)
	{
		
		try {
			while(rs.next())
			{
				String name = rs.getString("name");
				cbx.addItem(rs.getString("name"));
				categoryMap.put(name, rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
