package model;

public class Song {

	
	private String song_id;
	private String song_name;
	private String song_length;
	

	
	public Song(String song_id, String song_name, String song_length)
	{
		this.song_length = song_length;
		this.song_name = song_name;
		this.song_id = song_id;
	}

	public String getSong_id() {
		return song_id;
	}

	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}

	public String getSong_length() {
		return song_length;
	}

	public void setSong_length(String song_length) {
		this.song_length = song_length;
	}
}
