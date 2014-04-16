package model;

public class DigiProduct {

	
	//prod table
	private String prod_id;
	private String prod_type;
	private int current_stock;
	private double costPrice;
	private double sellPrice;

	//Digi prod
	private String genre;
	private String age_rating;
	private double length;
	
	//CD
	private String albumName;
	private String publisher;
	private CD album;

	//DVD
	private String dvd_name;
	private String studio;
	
	//Game
	private String game_name;
	private String platform;




	//CD product
	public DigiProduct(String prod_id,  String prod_type, String albumName, double costPrice, double sellPrice, 
			int current_stock, String age_rating, String genre, String publisher, double length, CD album)
	{
		this.prod_id = prod_id;
		this.prod_type = prod_type;
		this.albumName = albumName;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.current_stock = current_stock;
		this.age_rating = age_rating;
		this.genre = genre;
		this.publisher = publisher;
		this.length = length;
		this.album = album;
	}

	//DVD
	public DigiProduct(String prod_id,  String prod_type, String dvd_Name, double costPrice, double sellPrice, 
			int current_stock, String age_rating, String genre, String studio, double length)
	{
		this.prod_id = prod_id;
		this.prod_type = prod_type;
		this.dvd_name = dvd_Name;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.current_stock = current_stock;
		this.age_rating = age_rating;
		this.genre = genre;
		this.studio = studio;
		this.length = length;
	}

	//Game
	public DigiProduct(String prod_id,  String prod_type, String game_Name, double costPrice, double sellPrice, 
			int current_stock, String age_rating, String genre, String studio, String platform)
	{
		this.prod_id = prod_id;
		this.prod_type = prod_type;
		this.game_name = game_Name;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.current_stock = current_stock;
		this.age_rating = age_rating;
		this.genre = genre;
		this.studio = studio;
		this.platform = platform;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDvd_name() {
		return dvd_name;
	}

	public void setDvd_name(String dvd_name) {
		this.dvd_name = dvd_name;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
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

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public CD getAlbum() {
		return album;
	}

	public void setAlbum(CD album) {
		this.album = album;
	}
}
	
	
	

		