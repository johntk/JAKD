package kioskScreens;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;

public class AudioPlayer
{
	private Clip clip;
	private AudioInputStream aiStream;
	private FloatControl volume;

	public void play(AudioInputStream ai)
	{
		aiStream = ai;
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
	public void setVolume(float x)
	{
		volume.setValue(x);
	}
	public AudioInputStream getFile()
	{
		return aiStream;
	}
	public void nullFile()
	{
		this.aiStream =null;
	}

}
