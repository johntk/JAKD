package kioskScreens;

import java.util.ArrayList;

public class SongList
{
	ArrayList<Song> songList = new ArrayList<Song>();
	
	public void addSong(Song s)
	{
		songList.add(s);
	}
	public ArrayList<Song> getSongList()
	{
		return songList;
	}
}
