package model;

import java.net.URL;

public class KioskSong
{
	private String title;
	private String id;
	private String length;
	
	private URL filePath;
	
/*
 * Constructor creates a song object which gets added to the songList array in KioskPRoductDisplay
 */
	public KioskSong(String id, String name, String length, String artist, String album, int songNum)
	{
		title = name;
		this.id = id;
		this.length = length;
		filePath = getClass().getResource("/resources/kioskFiles/songs/"+artist+" - "+album+"/"+songNum+".wav");
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
	public URL getFilePath()
	{
		return filePath;
	}
}
