package kioskScreens;

import java.io.File;

import javax.media.GainControl;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;

public class AudioPlayer
{
	private File f;
	private Player player;

	public void play(File f)
	{
		this.f = f;
		try
		{
			player = Manager.createPlayer(new MediaLocator(f.toURI().toURL()));
			player.realize();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		player.start();
	}
	public void stop()
	{
		player.stop();
		player.close();
	}
	public void setVolume(float x)
    {
        GainControl gc=player.getGainControl();
        gc.setLevel(x);
    }
	public File getFile()
	{
		return this.f;
	}
	public void nullFile()
	{
		this.f =null;
	}
	public int getState()
	{
		return player.getState();
	}
	
}
