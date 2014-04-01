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
	//head_phones
	private String headphone_id;
	private String over_ear;
	private String mic;
	private String iphone_compatible;
	private double hp_cost_price;
	private double hp_sale_price;
	//sound_dock
	private String sd_id;
	private String wireless;
	private int power_output;
	private String digital_radio;
	private double sd_cost_price;
	private double sd_sale_price;
	//console
	private String console_id;
	private String wifi;
	private int storage_size;
	private int num_controllers;
	private double con_cost_price;
	private double con_sale_price;
	//digi prod
	private String digi_id;
	private String genre;
	private String age_rating;
	//CD
	private String cd_id;
	private String album_name;
	private double album_length;
	private String record_company;
	private double cd_cost_price;
	private double cd_sale_price;
	//DVD
	private String dvd_id;
	private String dvd_name;
	private double dvd_length;
	private String studio;
	private double dvd_cost_price;
	private double dvd_sale_price;
	//Game
	private String game_id;
	private String platform;
	private double game_name;
	private String company;
	private double game_cost_price;
	private double game_sale_price;
	//Song
	private String song_id;
	private String song_name;
	private String song_length;
	//Artist
	private String artist_id;
	private String artist_name;
	
	
	public Product(int prod_id, String prod_type, int current_stock,
			String elec_id, String manufacturer, String model, String colour,
			String headphone_id, String over_ear, String mic,
			String iphone_compatible, double hp_cost_price,
			double hp_sale_price, String sd_id, String wireless,
			int power_output, String digital_radio, double sd_cost_price,
			double sd_sale_price, String console_id, String wifi,
			int storage_size, int num_controllers, double con_cost_price,
			double con_sale_price, String digi_id, String genre,
			String age_rating, String cd_id, String album_name,
			double album_length, String record_company, double cd_cost_price,
			double cd_sale_price, String dvd_id, String dvd_name,
			double dvd_length, String studio, double dvd_cost_price,
			double dvd_sale_price, String game_id, String platform,
			double game_name, String company, double game_cost_price,
			double game_sale_price, String song_id, String song_name,
			String song_length, String artist_id, String artist_name) {
		
		this.prod_id = prod_id;
		this.prod_type = prod_type;
		this.current_stock = current_stock;
		this.elec_id = elec_id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.colour = colour;
		this.headphone_id = headphone_id;
		this.over_ear = over_ear;
		this.mic = mic;
		this.iphone_compatible = iphone_compatible;
		this.hp_cost_price = hp_cost_price;
		this.hp_sale_price = hp_sale_price;
		this.sd_id = sd_id;
		this.wireless = wireless;
		this.power_output = power_output;
		this.digital_radio = digital_radio;
		this.sd_cost_price = sd_cost_price;
		this.sd_sale_price = sd_sale_price;
		this.console_id = console_id;
		this.wifi = wifi;
		this.storage_size = storage_size;
		this.num_controllers = num_controllers;
		this.con_cost_price = con_cost_price;
		this.con_sale_price = con_sale_price;
		this.digi_id = digi_id;
		this.genre = genre;
		this.age_rating = age_rating;
		this.cd_id = cd_id;
		this.album_name = album_name;
		this.album_length = album_length;
		this.record_company = record_company;
		this.cd_cost_price = cd_cost_price;
		this.cd_sale_price = cd_sale_price;
		this.dvd_id = dvd_id;
		this.dvd_name = dvd_name;
		this.dvd_length = dvd_length;
		this.studio = studio;
		this.dvd_cost_price = dvd_cost_price;
		this.dvd_sale_price = dvd_sale_price;
		this.game_id = game_id;
		this.platform = platform;
		this.game_name = game_name;
		this.company = company;
		this.game_cost_price = game_cost_price;
		this.game_sale_price = game_sale_price;
		this.song_id = song_id;
		this.song_name = song_name;
		this.song_length = song_length;
		this.artist_id = artist_id;
		this.artist_name = artist_name;
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


	public String getElec_id() {
		return elec_id;
	}


	public void setElec_id(String elec_id) {
		this.elec_id = elec_id;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getColour() {
		return colour;
	}


	public void setColour(String colour) {
		this.colour = colour;
	}


	public String getHeadphone_id() {
		return headphone_id;
	}


	public void setHeadphone_id(String headphone_id) {
		this.headphone_id = headphone_id;
	}


	public String getOver_ear() {
		return over_ear;
	}


	public void setOver_ear(String over_ear) {
		this.over_ear = over_ear;
	}


	public String getMic() {
		return mic;
	}


	public void setMic(String mic) {
		this.mic = mic;
	}


	public String getIphone_compatible() {
		return iphone_compatible;
	}


	public void setIphone_compatible(String iphone_compatible) {
		this.iphone_compatible = iphone_compatible;
	}


	public double getHp_cost_price() {
		return hp_cost_price;
	}


	public void setHp_cost_price(double hp_cost_price) {
		this.hp_cost_price = hp_cost_price;
	}


	public double getHp_sale_price() {
		return hp_sale_price;
	}


	public void setHp_sale_price(double hp_sale_price) {
		this.hp_sale_price = hp_sale_price;
	}


	public String getSd_id() {
		return sd_id;
	}


	public void setSd_id(String sd_id) {
		this.sd_id = sd_id;
	}


	public String getWireless() {
		return wireless;
	}


	public void setWireless(String wireless) {
		this.wireless = wireless;
	}


	public int getPower_output() {
		return power_output;
	}


	public void setPower_output(int power_output) {
		this.power_output = power_output;
	}


	public String getDigital_radio() {
		return digital_radio;
	}


	public void setDigital_radio(String digital_radio) {
		this.digital_radio = digital_radio;
	}


	public double getSd_cost_price() {
		return sd_cost_price;
	}


	public void setSd_cost_price(double sd_cost_price) {
		this.sd_cost_price = sd_cost_price;
	}


	public double getSd_sale_price() {
		return sd_sale_price;
	}


	public void setSd_sale_price(double sd_sale_price) {
		this.sd_sale_price = sd_sale_price;
	}


	public String getConsole_id() {
		return console_id;
	}


	public void setConsole_id(String console_id) {
		this.console_id = console_id;
	}


	public String getWifi() {
		return wifi;
	}


	public void setWifi(String wifi) {
		this.wifi = wifi;
	}


	public int getStorage_size() {
		return storage_size;
	}


	public void setStorage_size(int storage_size) {
		this.storage_size = storage_size;
	}


	public int getNum_controllers() {
		return num_controllers;
	}


	public void setNum_controllers(int num_controllers) {
		this.num_controllers = num_controllers;
	}


	public double getCon_cost_price() {
		return con_cost_price;
	}


	public void setCon_cost_price(double con_cost_price) {
		this.con_cost_price = con_cost_price;
	}


	public double getCon_sale_price() {
		return con_sale_price;
	}


	public void setCon_sale_price(double con_sale_price) {
		this.con_sale_price = con_sale_price;
	}


	public String getDigi_id() {
		return digi_id;
	}


	public void setDigi_id(String digi_id) {
		this.digi_id = digi_id;
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


	public String getCd_id() {
		return cd_id;
	}


	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}


	public String getAlbum_name() {
		return album_name;
	}


	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}


	public double getAlbum_length() {
		return album_length;
	}


	public void setAlbum_length(double album_length) {
		this.album_length = album_length;
	}


	public String getRecord_company() {
		return record_company;
	}


	public void setRecord_company(String record_company) {
		this.record_company = record_company;
	}


	public double getCd_cost_price() {
		return cd_cost_price;
	}


	public void setCd_cost_price(double cd_cost_price) {
		this.cd_cost_price = cd_cost_price;
	}


	public double getCd_sale_price() {
		return cd_sale_price;
	}


	public void setCd_sale_price(double cd_sale_price) {
		this.cd_sale_price = cd_sale_price;
	}


	public String getDvd_id() {
		return dvd_id;
	}


	public void setDvd_id(String dvd_id) {
		this.dvd_id = dvd_id;
	}


	public String getDvd_name() {
		return dvd_name;
	}


	public void setDvd_name(String dvd_name) {
		this.dvd_name = dvd_name;
	}


	public double getDvd_length() {
		return dvd_length;
	}


	public void setDvd_length(double dvd_length) {
		this.dvd_length = dvd_length;
	}


	public String getStudio() {
		return studio;
	}


	public void setStudio(String studio) {
		this.studio = studio;
	}


	public double getDvd_cost_price() {
		return dvd_cost_price;
	}


	public void setDvd_cost_price(double dvd_cost_price) {
		this.dvd_cost_price = dvd_cost_price;
	}


	public double getDvd_sale_price() {
		return dvd_sale_price;
	}


	public void setDvd_sale_price(double dvd_sale_price) {
		this.dvd_sale_price = dvd_sale_price;
	}


	public String getGame_id() {
		return game_id;
	}


	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}


	public String getPlatform() {
		return platform;
	}


	public void setPlatform(String platform) {
		this.platform = platform;
	}


	public double getGame_name() {
		return game_name;
	}


	public void setGame_name(double game_name) {
		this.game_name = game_name;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public double getGame_cost_price() {
		return game_cost_price;
	}


	public void setGame_cost_price(double game_cost_price) {
		this.game_cost_price = game_cost_price;
	}


	public double getGame_sale_price() {
		return game_sale_price;
	}


	public void setGame_sale_price(double game_sale_price) {
		this.game_sale_price = game_sale_price;
	}


	public String getSong_id() {
		return song_id;
	}


	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}


	public String getSong_name() {
		return song_name;
	}


	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}


	public String getSong_length() {
		return song_length;
	}


	public void setSong_length(String song_length) {
		this.song_length = song_length;
	}


	public String getArtist_id() {
		return artist_id;
	}


	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}


	public String getArtist_name() {
		return artist_name;
	}


	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}


	
	
	

}