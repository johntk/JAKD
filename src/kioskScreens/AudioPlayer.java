package kioskScreens;

import java.io.File;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;

public class AudioPlayer
{
	private Player player;

	public void play(File f)
	{
		try
		{
			player = Manager.createPlayer(new MediaLocator(f.toURI().toURL()));
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
	}
}
