package kioskScreens;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Song
{
	private String title;
	private String id;
	private String length;
	private URL filePath;
	private AudioInputStream aiStream;
	

	public Song(String id, String name, String length, String artist, String album, int songNum) throws UnsupportedAudioFileException, IOException
	{
		title = name;
		this.id = id;
		this.length = length;
		filePath = getClass().getResource("/resources/kioskFiles/songs/"+artist+" - "+album+"/"+songNum+".wav");
		aiStream = AudioSystem.getAudioInputStream(filePath);
	}
	
	public String getSongID()
	{
		return id;
	}
	
	public AudioInputStream getFile()
	{
		return aiStream;
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
