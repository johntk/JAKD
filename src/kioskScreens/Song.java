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
	

	public Song(String id, String name, String length, String artist, String album, int songNum)
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
	
	public AudioInputStream getAudioStream() throws UnsupportedAudioFileException, IOException
	{
		aiStream = AudioSystem.getAudioInputStream(filePath);
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
