package model;

import java.util.ArrayList;

public class Product {

	
	//prod table
	private String prod_id;
	private String prod_type;
	private int current_stock;

	//digi prod
	private String genre;
	private String age_rating;
	
	//CD
	private String albumName;
	private double costPrice;
	private double sellPrice;
	private String publisher;
	private double length;
	private CD album;

	
	public CD getAlbum() {
		return album;
	}




	public void setAlbum(CD album) {
		this.album = album;
	}




	//CD product
	public Product(String prod_id,  String prod_type, String albumName, double costPrice, double sellPrice, 
			int current_stock, String age_rating, String genre, String publisher,double length, CD album)
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

}
	
	
	

		