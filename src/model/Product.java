package model;

public class Product {

	
	//prod table
	private int prod_id;
	private String prod_type;
	private int current_stock;
	//elec prod
	private String elec_id;
	private String manufacturer;
	private String model;
	private String colour;
	
	
	//digi prod
	private String digi_id;
	private String genre;
	private String age_rating;
	private CD cd;
	private DVD dvd;
	private Game game;
	
	
	
	
	
	//CD product
	public Product(int prod_id,  String prod_type, int current_stock, String genre, String age_rating, CD cd)
	{
		this.prod_id = prod_id;
		this.prod_type = prod_type;
		this.current_stock = current_stock;
		this.genre = genre;
		this.age_rating = age_rating;
		this.cd = cd;
	}
	

	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	public int getCurrent_stock() {
		return current_stock;
	}
	public void setCurrent_stock(int current_stock) {
		this.current_stock = current_stock;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAge_rating() {
		return age_rating;
	}
	public void setAge_rating(String age_rating) {
		this.age_rating = age_rating;
	}
	public CD getCd() {
		return cd;
	}
	public void setCd(CD cd) {
		this.cd = cd;
	}









	public Product(int prod_id,  String prod_type, int current_stock, String manufacturer, String model, String colour, Headphones hp)
	{
		this.prod_id = prod_id;
		this.prod_type = prod_type;
		this.current_stock = current_stock;
		this.manufacturer = manufacturer;
		this.model = model;
		this.colour = colour;
		
	}
	public Product(int prod_id,  String prod_type, int current_stock, String manufacturer, String model, String colour, SoundDock dock)
	{
		
	}
	public Product(int prod_id,  String prod_type, int current_stock, String manufacturer, String model, String colour, Console console)
	{
		
	}
}
	
	
	

		