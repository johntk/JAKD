package model;

import java.util.ArrayList;

public class CD {

	//Collection of songs to create an album in the CD product
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
