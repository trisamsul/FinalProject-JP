import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DbBooks extends Database {
	
	public DbBooks() throws Exception, SQLException{
		
		super();
		
	}
	
	//prosedur mengambil semua data buku
	public void selectAllBooks(){
        
		try{
            String query = "SELECT * FROM tb_books";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
		
    }
	
	//prosedur mengambil semua data buku
	public void searchBooks(String keyword){
        
		try{
            String query = "SELECT * FROM tb_books WHERE books_title LIKE '%"+keyword+"%' OR books_author LIKE '%"+keyword+"%'";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
		
    }
	
	//prosedur mengambil data buku by id 
	public void selectBooksById(int id){
        
		try{
            String query = "SELECT * FROM tb_books WHERE books_id='" +id+ "'";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
        
    }
	
	public void insertBooks(String title, String author, String year, String shelf) {
		
		try{
			String query = "INSERT INTO tb_books(books_title, books_author, books_year, books_shelf) VALUES('"+title+"','"+author+"','"+year+"','"+shelf+"')";
			createUpdate(query);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
	}
	
	public void deleteBooks(int id){
		
		try{
			String query = "DELETE FROM tb_books WHERE books_id='"+id+"'";
			createUpdate(query);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
	}
	
	public void updateBooks(int id, String title, String author, String year, String shelf){
		
		try{
			String query = "UPDATE tb_books SET books_title='"+title+"', books_author='"+author+"', books_year='"+year+"', books_shelf='"+shelf+"' WHERE books_id='"+id+"'";
			createUpdate(query);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
	}
	
}
