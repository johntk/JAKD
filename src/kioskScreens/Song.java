package kioskScreens;

import java.io.File;
import java.util.ArrayList;

public class Song
{
	private String title;
	private String id;
	private String length;
	private String filePath = "src/resources/kioskFiles/songs/";
	private File file;
	

	public Song(String id, String name, String length, String artist, String album)
	{
		title = name;
		this.id = id;
		this.length = length;
		file = new File(filePath+artist+" - "+album+"/"+name+".wav");
	}
	
	public String getSongID()
	{
		return id;
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
