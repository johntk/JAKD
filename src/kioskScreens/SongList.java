package kioskScreens;

import java.io.File;
import java.util.ArrayList;

import db.DBconnection;

public class SongList
{
	DBconnection db;
	ArrayList <Song> songList = new ArrayList<Song>();
	
	public SongList()
	{
		
	}
}

class Song
{
	private String filePath = "src/resources/kioskFiles/songs/";
	private String artistAlbum;
	private File file;
	
	public Song()
	{
		
	}
}
