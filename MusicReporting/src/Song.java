//Armando JImenez

public class Song
{
	// Data members
	private String album;
	private String artist;
	private String songTitle;
	private int position;
	private int minutes;
	private int seconds;

	public Song(String artist, String album, String songTitle, int position, int minutes, int seconds)
	{
		this.album = album;
		this.artist = artist;
		this.songTitle = songTitle;
		this.position = position;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public Song(Song songToCopy)
	{
		this.album = songToCopy.album;
		this.artist = songToCopy.artist;
		this.songTitle = songToCopy.songTitle;
		this.position = songToCopy.position;
		this.minutes = songToCopy.minutes;
		this.seconds = songToCopy.seconds;
	}

	public String getAlbum()
	{
		return album;
	}

	public void setAlbum(String album)
	{
		this.album = album;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public String getSongTitle()
	{
		return songTitle;
	}

	public void setSongTitle(String songTitle)
	{
		this.songTitle = songTitle;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public void setMinutes(int minutes)
	{
		this.minutes = minutes;
	}

	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
	}

	public String getDuration()
	{
		String setTime = minutes + ":" + seconds;

		if (seconds < 10)
		{
			return minutes + ":0" + seconds;
		} 
		
		else
		{
			return setTime;
		}
	}

	public String toString()
	{
		return "Song: " + songTitle + "\nAlbum: " + album + "\nArtist: " + artist + "\nPosition: " + position
				+ "\nDuration: " + getDuration();
	}

}
