package kioskScreens;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Song
{
	private String title;
	private String id;
	private String length;
	private String filePath;
	private File file;
	

	public Song(String id, String name, String length, String artist, String album, int songNum) throws URISyntaxException, MalformedURLException
	{
		filePath = "/resources/kioskFiles/songs/";
		title = name;
		this.id = id;
		this.length = length;
		file = new File(this.getClass().getResource(filePath+artist+" - "+album+"/"+songNum+".wav").toURI());
	}
	
	public String getSongID()
	{
		return id;
	}
	
	public File getFile()
	{
		return file;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getLength()
	{
		return length;
	}
}
