package model;

import java.util.ArrayList;

public class Song {

	
			private String song_id;
			private String song_name;
			private String song_length;
	
			private ArrayList<Song> songList = new ArrayList<Song>();
			
			
	
			public Song(String song_name, String song_length)
			{
				this.song_length = song_length;
				this.song_name = song_name;
			}
			
	
}
