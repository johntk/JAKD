package model;

import java.util.ArrayList;

public class CD {


	private ArrayList<Song> songList = new ArrayList<Song>();
	
	public CD(ArrayList<Song> songList)
	{
		this.songList = songList;	
	}
	public ArrayList<Song> getSongList() {
		return songList;
	}

	public void setSongList(ArrayList<Song> songList) {
		this.songList = songList;
	}
}
