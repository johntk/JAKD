package kioskScreens;

import java.io.File;

public class Song
{
	private String title;
	private String filePath = "src/resources/kioskFiles/songs/";
	private File file;
	
	public Song(String id, String name, String length, String artist, String album)
	{
		title = name;
		file = new File(filePath+artist+" - "+album+"/"+name+".wav");
	}
}
