import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
	
	private String ConAddress	=	"jdbc:mysql://localhost/db_final_project_jp?user=root&password=";
	private Statement stmt		=	null;
	private ResultSet rs		=	null;
	private Connection conn		=	null;
	
	int lastID;
	
	public Database() throws Exception, SQLException{
		
		/*
			Method Database
			Konstruktor : Melakukan koneksi ke MySQL dan basis data
			Menerima masukan berupa string alamat koneksi ke MySQL dan basis data
		*/
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection(ConAddress);
			conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
		}catch(SQLException e){
			throw e;
		}
	}
	
	public void createQuery(String Query)throws Exception, SQLException{
		/*
			Method createQuery
			Mengeksekusi query tanpa mengubah isi data
			Menerima masukan harga string query
		*/
		
		try{
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(Query);
			
			if(stmt.execute(Query)){
				rs = stmt.executeQuery(Query);
			}
		}catch(SQLException es){
			throw es;
		}
	}
	
	public void createUpdate(String Query)throws Exception, SQLException{
		/* 
			Method createQuery
			mengeksekusi query yg mengubah isi data
			menerima masukan berupa string query 
		*/
		
		try{
			stmt = conn.createStatement();
			
			int hasil = stmt.executeUpdate(Query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()){
				lastID = rs.getInt(1);
			}
		}catch(SQLException es){
			throw es;
		}
	}
	
	public ResultSet getResult() throws Exception{
		/* 
			Memberikan hasil query 
		*/
		
		ResultSet Temp = null;
		try{
			return rs;
		}catch(Exception ex){
			return Temp;
		}
	}
		
	public void closeResult()throws SQLException, Exception{
		/* 
			Menutup hubungan eksekusi query
		*/
		
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException sqlEx){
				rs = null;
				throw sqlEx;
			}
		}
		
		if(stmt != null){
			try{
				stmt.close();
			}catch(SQLException sqlEx){
				stmt = null;
				throw sqlEx;
			}
		}
	}

	public void closeConnection() throws SQLException, Exception{
		/* 
		 	Menutup hubungan mySQL & Database 
		*/
		
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException sqlEx){
				conn = null;
			}
		}
	}
	
	public int getLastID(){
		return this.lastID;
	}
	
	public void setLastID(int lastID){
		this.lastID = lastID;
	}
	
}
