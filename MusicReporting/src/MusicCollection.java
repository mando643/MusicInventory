//Armando Jimenez

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicCollection
{
	public static final int MAX_LENGTH = 300;

	private Song[] songs;
	private int numSongs;
	private PrintWriter rpt;
	private Song[] sortedArray;

	public MusicCollection()
	{
		songs = new Song[MAX_LENGTH];
		numSongs = 0;
		rpt = null;
		
		loadArray();
		sortMusicCollection();
		
		try
		{
			rpt = new PrintWriter(new FileWriter("rpt.txt"));
		} 
		
		catch (IOException e)
		{
			System.err.println("Output report file could not be created. Program will now end.");
			System.exit(-2);
		}
	}

	private void loadArray()
	{
		Scanner inF = null;
		String lineFromFile;
		String[] splitLine = new String[6];
		try
		{
			inF = new Scanner(new File("musicCSV.txt"));
		} 
		
		catch (FileNotFoundException e)
		{
			System.err.println("musicCSV.txt could not be opened. Program will now end");
			System.exit(-1);
		}
		
		int position = 0;
		int minutes = 0;
		int seconds = 0;
		
		while (inF.hasNext())
		{
			lineFromFile = inF.nextLine();
			splitLine = lineFromFile.split(",");
			try
			{
				position = Integer.parseInt(splitLine[3]);

			} 
			
			catch (NumberFormatException e)
			{
				System.out.println("Position filled not numeric for song " + splitLine[3]
						+ "and position will be changed to 0");
				position = 0;
			}
			
			try
			{
				minutes = Integer.parseInt(splitLine[4]);

			} 
			
			catch (NumberFormatException e)
			{
				System.out.println("Minutes filled not numeric for song " + splitLine[4]
						+ "and minutes will be changed to 0");
				minutes = 0;
			}
			
			try
			{
				seconds = Integer.parseInt(splitLine[5]);

			}
			
			catch (NumberFormatException e)
			{
				System.out.println("Seconds filled not numeric for song " + splitLine[5]
						+ "and seconds will be changed to 0");
				seconds = 0;
			}
			songs[numSongs] = new Song(splitLine[0], splitLine[1], splitLine[2], position, minutes, seconds);
			numSongs++;
		}

	}

	public void printInventory()
	{

		rpt.println("Artist Name          Album Title                  Song Title             Position  Duration");
		rpt.println("*******************  ***************************  *********************  ********  ********");

		for (int i = 0; i < numSongs; i++)
		{
			rpt.printf("%-20s %-28s %-29s %-14d %-8s\n", songs[i].getArtist(), songs[i].getAlbum(), songs[i]
					.getSongTitle(), songs[i].getPosition(), songs[i].getDuration());
		}

	}

	public void printAlbumsInCollection()
	{
		String[] albums = new String[numSongs];
		boolean found;
		int numAlbum = 0;
		int a;
		for (int b = 0; b < numSongs; b++)
		{
			a = 0;
			found = false;
			
			while (!found && a < numAlbum)
			{
				if (songs[b].getAlbum().equals(albums[a]))
				{
					found = true;
				}
				
				else
				{
					a++;
				}
			}
			
			if (!found)
			{
				albums[numAlbum] = songs[b].getAlbum();
				numAlbum++;
			}
		}
		
		if (numAlbum > 0)
		{
			rpt.println("\nHere are the albums in the music collection");
			for (a = 0; a < numAlbum; a++)
				rpt.println("  " + albums[a]);
		}
	}

	public void printAlbumsForArtist(String anArtist)
	{
		String[] artistAlbum = new String[numSongs];
		boolean found;
		int numArtist = 0;
		int a;

		for (int b = 0; b < numArtist; b++)
		{
			if (songs[b].getArtist().equalsIgnoreCase(anArtist))
			{
				a = 0;
				found = false;
				
				while (!found && a < numSongs)
				{
					if (songs[b].getArtist().equals(artistAlbum[a]))
					{
						found = true;
					} 
					
					else
					{
						a++;
					}
				}
				
				if (!found)
				{
					artistAlbum[numArtist] = songs[b].getAlbum();
					numSongs++;
				}
			}
		}
		
		if (numArtist > 0)
		{
			rpt.println("\nHere are the albums by " + anArtist + " in this collection:\n");
			for (a = 0; a < numArtist; a++)
				rpt.println(artistAlbum[a]);
		} 
		
		else
		{
			rpt.println("There are no albums by " + anArtist + " in this collection:\n");
		}
	}
	
	public void endRpt()
	{
		rpt.close();
	}

	public Song findASong(String songtitle)
	{
		for (int i = 0; i < numSongs; i++)
		{

			if (songtitle.equals(songs[i].getSongTitle()))
			{
				rpt.println(songs[i].getSongTitle());
				rpt.close();
				return new Song(songs[i]);
			}
		}
		return null;
	}

	private void sortMusicCollection()
	{
		int minX, currX, setX;
		Song tempSong;

		for (setX = 0; setX < numSongs - 1; setX++)
		{
			minX = setX;
			for (currX = setX + 1; currX < numSongs; currX++)
			{
				if (songs[currX].getArtist().compareToIgnoreCase(songs[minX].getArtist()) < 0)
				{
					minX = currX;
				} 
				
				else if (songs[currX].getArtist().equalsIgnoreCase(songs[minX].getArtist()) && songs[currX].getAlbum()
						.compareToIgnoreCase(songs[minX].getAlbum()) < 0)
				{
					minX = currX;
				} 
				
				else if (songs[currX].getArtist().equalsIgnoreCase(songs[minX].getArtist()) && songs[currX].getAlbum()
						.equalsIgnoreCase(songs[minX].getAlbum()) && songs[currX].getPosition() < songs[minX].getPosition())
				{
					minX = currX;
				}

				if (minX != setX)
				{
					tempSong = songs[setX];
					songs[setX] = songs[minX];
					songs[minX] = tempSong;
				}
			}
		}

	}

	public void printArtistsInCollection()
	{
		ArrayList<String> artistCollection = new ArrayList<String>();

		for (int i = 0; i < numSongs; i++)
		{

			if (artistCollection.contains(songs[i].getArtist()) == false)
			{
				artistCollection.add(songs[i].getArtist());
			}
		}

		artistCollection.sort(null);

		rpt.println("\nHere are the artist in the collection:");

		for (String artist : artistCollection)
			rpt.println(" " + artist);
	}

	public Song binarySearch(String songTitle)
	{
		int first = 0;
		int last = numSongs - 1;
		int mid;
		
		sortInSongOrder();

		while (first <= last)
		{
			mid = (first + last) / 2;
			if (sortedArray[mid].getSongTitle().equalsIgnoreCase(songTitle))
			{
				return new Song(sortedArray[mid]);
			}

			else if (sortedArray[mid].getSongTitle().compareToIgnoreCase(songTitle) < 0)
			{
				first = mid + 1;
			} else
			{
				last = mid - 1;
			}
		}
		return null;

	}

	private void sortInSongOrder()
	{

		sortedArray = new Song[numSongs];

		for (int i = 0; i < numSongs; i++)
			sortedArray[i] = songs[i];

		int minx, currx, setx;
		Song tempSong;
		for (setx = 0; setx < numSongs - 1; setx++)
		{
			minx = setx;
			for (currx = setx + 1; currx < numSongs; currx++)
				if (sortedArray[currx].getSongTitle().compareTo(sortedArray[minx].getSongTitle()) < 0)
					minx = currx;
			if (setx != minx)
			{
				tempSong = sortedArray[minx];
				sortedArray[minx] = sortedArray[setx];
				sortedArray[setx] = tempSong;
			}
		}
	}
}
