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
	private String digi_id;

	
	//CD
	private String albumName;
	private String publisher;
	private CD album;
	private String artist;
	private String cd_id;
	private String artist_id;


	//DVD
	private String dvd_name;
	private String studio;
	private String dvd_id;
	
	//Game
	private String game_name;
	private String platform;
	private String game_id;




	//CD product
	public DigiProduct(String prod_id, String digi_id, String cd_id,  String artist_id, String prod_type, String albumName, double costPrice, double sellPrice, 
			int current_stock, String age_rating, String genre, String publisher, double length, String artist, CD album)
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
		this.digi_id = digi_id;
		this.cd_id = cd_id;
		this.artist_id = artist_id;
		this.artist = artist;
	}

	
	//DVD
	public DigiProduct(String prod_id, String digi_id, String dvd_id,  String prod_type, String dvd_Name, double costPrice, double sellPrice, 
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
		this.digi_id = digi_id;
		this.dvd_id = dvd_id;
	}

	//Game
	public DigiProduct(String prod_id, String digi_id, String game_id, String prod_type, String game_Name, double costPrice, double sellPrice, 
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
		this.digi_id = digi_id;
		this.game_id = game_id;
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
	public String getDigi_id() {
		return digi_id;
	}

	public void setDigi_id(String digi_id) {
		this.digi_id = digi_id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}


	public String getArtist_id() {
		return artist_id;
	}

	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}

	public String getDvd_id() {
		return dvd_id;
	}

	public void setDvd_id(String dvd_id) {
		this.dvd_id = dvd_id;
	}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}

}
	
	
	

		