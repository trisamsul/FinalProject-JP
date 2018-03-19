
public class Books {
	
	private String title;
	private String author;
	private String year;
	private String shelf;
	
	public void Books(String title, String author, String year, String shelf){
		setTitle(title);
		setAuthor(author);
		setYear(year);
		setShelf(shelf);
	}
	
	public void Books() {
		
	}
	
	//Set Book Title
	public void setTitle(String title) {
		this.title = title;
	}
	
	//Set Book Author
	public void setAuthor(String author) {
		this.author = author;
	}
	
	//Set Book Year
	public void setYear(String year) {
		this.year = year;
	}
	
	//Set Book shelf
	public void setShelf(String shelf) {
		this.shelf = shelf;
	}
	
	//Get Book Title
	public String getTitle() {
		return this.title;
	}
	
	//Get Book Author
	public String getAuthor() {
		return this.author;
	}
	
	//Get Book Year
	public String getYear() {
		return this.year;
	}
	
	//Get Book shelf
	public String getShelf() {
		return this.shelf;
	}
	
}
