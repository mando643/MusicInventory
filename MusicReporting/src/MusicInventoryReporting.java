//Armando Jimenez

import java.util.Scanner;

public class MusicInventoryReporting
{

	public static void main(String[] args)
	{
		Song theSong;
		MusicCollection collection = new MusicCollection();

		collection.printInventory();
		collection.printArtistsInCollection();
		collection.endRpt();

		Scanner sc = new Scanner(System.in);
		char again;
		String songTitle;

		do
		{
			System.out.print("Please enter the song you'd like to look up: ");
			songTitle = sc.nextLine();
			theSong = collection.binarySearch(songTitle);

			if (theSong == null)
			{
				System.out.println("\nThere is no song named '" + songTitle + "' in this collection");
			}
			else
			{
				System.out.println("\n'" + songTitle + "' is in the collection.  It was performed by " + theSong
						.getArtist() + " on the " + theSong.getAlbum() + " album.  Here are the specifics:");
				System.out.println(theSong.toString());
			}
			System.out.println();
			System.out.print("Would you like to look up another song? (y or n) ");
			again = sc.nextLine().charAt(0);
		} 
		while (again == 'y');
		sc.close();
	}

}	
		
		
		
		
		
	

