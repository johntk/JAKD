package model;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.UnsupportedAudioFileException;

public class KioskAudioPlayer
{
	private Clip clip;
	private AudioInputStream aiStream;
	private FloatControl volume;
	private URL filePath;

	/*
	 * Audio player takes a URL from the selected song object
	 */
	public void play(URL fp) throws UnsupportedAudioFileException, IOException
	{
		filePath = fp;
		aiStream = AudioSystem.getAudioInputStream(filePath);

		try
		{
			Line.Info linfo = new Line.Info(Clip.class);
		    Line line = AudioSystem.getLine(linfo);
		    clip = (Clip) line;
		    clip.open(aiStream);
		    clip.start();
		    volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void stop()
	{
		clip.stop();
		clip.close();
	}
	/*
	 * Set volume method receives a float number from the KioskProductView class
	 * The float number is taken from a JSlider value
	 */
	public void setVolume(float x)
	{
		volume.setValue(x);
	}
	public AudioInputStream getAudioStream()
	{
		return aiStream;
	}
	public void nullFile()
	{
		filePath =null;
	}
	public URL getFilePath()
	{
		return filePath;
	}

}