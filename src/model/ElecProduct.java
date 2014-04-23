package model;

public class ElecProduct {

	
	//prod table
	private String prod_id;
	private String prod_type;
	private int current_stock;
	private double costPrice;
	private double sellPrice;

	//Elec prod
	private String manufacturer;
	private String model;
	private String colour;
	private String elec_id;
	
	//Headphone
	private String overEar;
	private String mic;
	private String iphoneComp;
	private String headphone_id;
	
	//SoundDock
	private String wireless;
	private String digiRadio;
	private int pwrOut;
	private String sd_id;
	
	//Console
	private int storageSize;
	private String wifi;
	private int numPad;
	private String console_id;
	

	//Headphone product
	public ElecProduct(String prod_id, String elec_id, String headphone_id,  String prod_type, String model, double costPrice, double sellPrice, 
			int current_stock, String mic, String overEar, String iphoneComp, String manufacturer, String colour)
	{
		this.prod_id = prod_id;
		this.prod_type = prod_type;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.current_stock = current_stock;
		this.manufacturer = manufacturer;
		this.model = model;
		this.colour = colour;
		this.overEar = overEar;
		this.mic = mic;
		this.iphoneComp = iphoneComp;
		this.headphone_id = headphone_id;
		this.elec_id = elec_id;
		
	}
	
	//SoundDock product
	public ElecProduct(String prod_id, String elec_id, String sd_id, String prod_type, String model, double costPrice, double sellPrice, 
			int current_stock, String digiRadio, String wireless, int pwrOut, String manufacturer, String colour)
	{
		this.prod_id = prod_id;
		this.prod_type = prod_type;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.current_stock = current_stock;
		this.manufacturer = manufacturer;
		this.model = model;
		this.colour = colour;
		this.digiRadio = digiRadio;
		this.wireless = wireless;
		this.pwrOut = pwrOut;
		this.elec_id = elec_id;
		this.sd_id = sd_id;
	}
	
			
	//Console product
	public ElecProduct(String prod_id, String elec_id, String console_id, String prod_type, String model, double costPrice, double sellPrice, 
			int current_stock, int storageSize, String wifi, int numPad, String manufacturer, String colour)
	{
		this.prod_id = prod_id;
		this.prod_type = prod_type;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.current_stock = current_stock;
		this.manufacturer = manufacturer;
		this.model = model;
		this.colour = colour;
		this.storageSize = storageSize;
		this.wifi = wifi;
		this.numPad = numPad;
		this.elec_id = elec_id;
		this.console_id = console_id;
		
	}

	
	
	public String getElec_id() {
		return elec_id;
	}

	public void setElec_id(String elec_id) {
		this.elec_id = elec_id;
	}

	public String getHeadphone_id() {
		return headphone_id;
	}

	public void setHeadphone_id(String headphone_id) {
		this.headphone_id = headphone_id;
	}

	public String getSd_id() {
		return sd_id;
	}

	public void setSd_id(String sd_id) {
		this.sd_id = sd_id;
	}

	public String getConsole_id() {
		return console_id;
	}

	public void setConsole_id(String console_id) {
		this.console_id = console_id;
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

	public String getOverEar() {
		return overEar;
	}

	public void setOverEar(String overEar) {
		this.overEar = overEar;
	}

	public String getMic() {
		return mic;
	}

	public void setMic(String mic) {
		this.mic = mic;
	}

	public String getIphoneComp() {
		return iphoneComp;
	}

	public void setIphoneComp(String iphoneComp) {
		this.iphoneComp = iphoneComp;
	}

	public String getWireless() {
		return wireless;
	}

	public void setWireless(String wireless) {
		this.wireless = wireless;
	}

	public String getDigiRadio() {
		return digiRadio;
	}

	public void setDigiRadio(String digiRadio) {
		this.digiRadio = digiRadio;
	}

	public int getPwrOut() {
		return pwrOut;
	}

	public void setPwrOut(int pwrOut) {
		this.pwrOut = pwrOut;
	}

	public int getStorageSize() {
		return storageSize;
	}

	public void setStorageSize(int storageSize) {
		this.storageSize = storageSize;
	}

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public int getNumPad() {
		return numPad;
	}

	public void setNumPad(int numPad) {
		this.numPad = numPad;
	}
	
}
