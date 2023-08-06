//name: Sankeerthikan Nimalathas
//Student ID: 501161043

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections; 
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args) throws IOException
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();

		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		//for each song and audiobook print loading song or loading audiobook
		for(int i=0;i<store.contents.size();i++)
		{
			if(store.contents.get(i) instanceof Song) // if song
			{
				System.out.println("Loading Song"); // print loading song
			}
			else if(store.contents.get(i) instanceof AudioBook)  // if audiobook
			{
				System.out.println("Loading AudioBook"); // print loading audiobook
			}


		}
		System.out.print(">"); // print prompt

		// Process keyboard actions
		while (scanner.hasNextLine())  // while there is a line to read from the keyboard
		{
			String action = scanner.nextLine(); // read the line

			if (action == null || action.equals(""))  // if the line is empty
			{
				System.out.print("\n>"); // print prompt
				continue; // skip to next iteration of while loop
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll();
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs();
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{//•	DOWNLOAD: Modify the download action so that it takes two store indices instead of one store index as parameters – i.e. a fromIndex and a toIndex. That is, you should now be able to download a range of songs/books etc. from the store (e.g. from song 2 to song 6, inclusive). If some of the songs are already in the library, then and error message for each of these songs should be printed (see the video)

			try{
				System.out.print("From Store Content #: ");  // download a range of songs
				int fromIndex = scanner.nextInt()-1; 
				System.out.print("To Store Content #: "); // download a range of songs
				int toIndex = scanner.nextInt()-1;
				scanner.nextLine(); 
				
				mylibrary.download(fromIndex, toIndex, store);  // download a range of songs
			} catch (Exception e) {
				System.out.println("Invalid input");
			}					
			}
			//•	DOWNLOADA: Create a new download action that takes an artist string as parameter and downloads all audio content with this artist name (use author for audio books) from the store. Make use of the artist map for full marks in the Map part. 
			else if (action.equalsIgnoreCase("DOWNLOADA")) 
			{
				try{
					System.out.print("Artist: "); // download all songs by an artist
					String artist = scanner.nextLine(); 
					mylibrary.downloada(artist, store);
				} catch (Exception e) {
					System.out.println("Invalid input");
				}
	
			}
			//•	DOWNLOADG: Create a new download action that takes a genre string as parameter and downloads all songs in this genre from the store. Make use of the genre map. 
			else if (action.equalsIgnoreCase("DOWNLOADG")) 
			{
				try{
					System.out.print("Genre: ");  // download all songs in a genre
					String genre = scanner.nextLine();
					mylibrary.downloadg(genre, store);
				} catch (Exception e) {
					System.out.println("Invalid input");
				}
			}
			//•	SEARCH: Add an action called SEARCH to myAudioUI as well as necessary code to class AudioContentStore that searches the store for an audio content with the specified title. That is, the user types SEARCH then is prompted to enter a title string. If the audio content with this title is found in the store then print the index of this content and the info for this content. A separate part of this new functionality is to use a Map in class AudioContentStore that maps a title (string) to an integer value. The integer value represents an index into the contents array list. See Requirement 3 below for details on the Map part of the assignment. The SEARCH functionality can be done without a Map but you will not receive full marks for this part.  
			else if (action.equalsIgnoreCase("SEARCH")) 
			{
				try{
					System.out.print("Title: "); // search for a title
					String title = scanner.nextLine();
					mylibrary.search(title, store); 
				} catch (Exception e) {
					System.out.println("Invalid input");
				}

			}
				
			//•	SEARCHA: Add an action called SEARCHA to myAudioUI as well as necessary code to class AudioContentStore that searches the store for an audio content with the specified artist name. That is, the user types SEARCHA then is prompted to enter an artist string. If the audio content with this artist name is found in the store then print the indices and info of all audio content with this artist (use author string for audio books). A separate part of this new functionality is to use a Map in class AudioContentStore that maps an artist string to an array list of integer. The integers in the array list represent indices into the contents array list. See Requirement 3 below for details on the Map part of the assignment. The SEARCHA functionality can be done without a Map but you will not receive marks for the Map part.  
			else if (action.equalsIgnoreCase("SEARCHA")) 
			{
				try{
					System.out.print("Artist: "); // ask for artist
					String artist = scanner.nextLine(); // get artist
					mylibrary.searcha(artist, store); // search for artist
				} catch (Exception e) { 
					System.out.println("Invalid input"); // if invalid input
				}

			}
			//•	SEARCHG: Add an action called SEARCHG to myAudioUI as well as necessary code to class AudioContentStore that searches the store for a song with the specified genre (“POP” “ROCK” etc). That is, the user types SEARCHG then is prompted to enter a genre string. If the song with this genre is found in the store then print the indices and info of all songs with this genre. A separate part of this new functionality is to use a Map in class AudioContentStore that maps a genre string to an array list of integer. The integers in the array list represent indices into the contents array list. See Requirement 3 below for details on the Map part of the assignment. The SEARCHG functionality can be done without a Map but you will not receive marks for the Map part.  
			else if (action.equalsIgnoreCase("SEARCHG")) 
			{
				try{
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]:  "); // Prompt user to enter genre
					String genre = scanner.nextLine(); // Read user input for genre
					mylibrary.searchg(genre, store);  // Search for genre
				} catch (Exception e) { // If user input is invalid
					System.out.println("Invalid input"); // Print error message
				}

			}
		
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				// Prompt user to enter song number
				System.out.print("Song Number: ");

				// Read user input for song number
				if (scanner.hasNextInt()) {
					int index = scanner.nextInt();
					scanner.nextLine();// Read the rest of the line
					boolean songExists = mylibrary.playSong(index);
					if (!songExists) { // If song doesn't exist in library
						System.out.println("Song Not Found"); // Print error message
					}
				} else {
					System.out.println("Invalid index");
				}

			}

			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				System.out.print("Audio Book Number: ");

			// Print error message if the book doesn't exist in the library
			if (scanner.hasNextInt()) {
				// Read user input for book number
				int index = scanner.nextInt();
				// Read the rest of the line
				scanner.nextLine();
				// Check if book exists
				boolean bookExists = mylibrary.printAudioBookTOC(index);
				if (!bookExists) {// If book doesn't exist in library
					System.out.println("Book Not Found in Library"); // Print error message
				}
			} else {
				System.out.println("Invalid index");// Print error message
			}
				
			}
			// Similar to playsong above except for audiobook
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				System.out.print("Audio Book Number: ");// Prompt user to enter book number
    if (scanner.hasNextInt()) {// Read user input for book number
        int index = scanner.nextInt();// Read the rest of the line
        scanner.nextLine();// Prompt user to enter chapter number
        System.out.print("Chapter: ");// Read user input for chapter number
        if (scanner.hasNextInt()) {// Read user input for book number
            int chapter = scanner.nextInt() ; // Read the rest of the line
            scanner.nextLine();// Check if book exists
            boolean bookExists = mylibrary.playAudioBook(index, chapter);// Print error message
            if (!bookExists) {// If book doesn't exist in library
                System.out.println("Book Not Found in Library");// Print error message
            }
        } else {
            System.out.println("Invalid chapter");// Print error message
        }
    } else {
        System.out.println("Invalid index");// Print error message
    
    }
				
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				

			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) //
			{
				System.out.print("Playlist Title: ");// Prompt user to enter playlist title
				String playlistTitle = scanner.nextLine();// Print the title of the playlist
				System.out.println(playlistTitle);// Play all the audio content
				boolean isPlayed = mylibrary.playPlaylist(playlistTitle);// Print error message
				if (!isPlayed) {// If playlist doesn't exist in library
					throw new IllegalArgumentException("Playlist Not Found in Library");// Print error message


			}

			}
			
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				System.out.print("Playlist Title: ");// Prompt user to enter playlist title
				String playlistTitle = scanner.nextLine();// Prompt user to enter content number
				System.out.print("Content Number: ");// Read user input for content number
				// print the title of the playlist
				if (scanner.hasNextInt()) {
					int index = scanner.nextInt() - 1; // subtract 1 to adjust for 0-indexing
					scanner.nextLine();// Read the rest of the line
					System.out.println(playlistTitle);// Play the audio content
					boolean isPlayed = mylibrary.playPlaylist(playlistTitle, index);// Print error message
					if (!isPlayed) {// If playlist doesn't exist in library
						throw new IllegalArgumentException("Playlist Not Found in Library");// Print error message
					}
				} else {
					System.out.println("Invalid index");// Print error message
				}
							
				
			
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) // Delete a song from the library
			{
				System.out.print("Library Song #: ");// Prompt user to enter song number
				if (scanner.hasNextInt()) {// Read user input for song number
					int index = scanner.nextInt();// Read the rest of the line
					scanner.nextLine();// Delete the song from the library
					boolean isDeleted = mylibrary.deleteSong(index ) ;// Print error message
					if (!isDeleted) {// If song doesn't exist in library
						System.out.println("Song not found at index " + index);// Print error message
					}
				} else {
					System.out.println("Invalid index");// Print error message
				}
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) // Make a playlist
			{
				System.out.print("Playlist Title: ");// Prompt user to enter playlist title
				String playlistTitle = scanner.nextLine();// Make a playlist
				boolean isMade = mylibrary.makePlaylist(playlistTitle);// Print error message
				if (isMade) {// If playlist doesn't exist in library
					System.out.print("");// print new line
				} else {
					System.out.println("Playlist " + playlistTitle + " Already Exists");// Print error message
				}
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				System.out.print("Playlist Title: ");// Prompt user to enter playlist title
				String playlistTitle = scanner.nextLine();// Print the playlist content
				boolean isPrinted = mylibrary.printPlaylist(playlistTitle);// Print error message
				if (!isPrinted) {// If playlist doesn't exist in library
					System.out.println("Playlist not found: " + playlistTitle);// Print error message
    }
			}
		
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) // add content to playlist
			{
				System.out.print("Playlist Title: ");// Prompt user to enter playlist title
				String playlistTitle = scanner.nextLine();// Prompt user to enter content type
			
				System.out.print("Content Type [SONG,AUDIOBOOK]: ");// Prompt user to enter content number
				String contentType = scanner.nextLine().toUpperCase(); // Read user input for content number, it is not case sensitive
			
				System.out.print("Library Content #: "); // Read user input for content number
				int contentIndex = scanner.nextInt(); // Read the rest of the line
				scanner.nextLine(); // Consume the newline character
			
				boolean isAdded = mylibrary.addContentToPlaylist(contentType, contentIndex, playlistTitle);
				//check if the playlist contains that title, if true then print true
				if (isAdded) {
					System.out.println();
				} else {
					throw new IllegalArgumentException("Playlist Not Found in Library");
				}
			
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) // delete content from playlist
			{
			{System.out.print("Playlist Title: ");// Prompt user to enter playlist title
			String playlistTitle = scanner.nextLine(); // Read user input for playlist title
		
			System.out.print("Playlist Content #: "); // Prompt user to enter content number
			if (scanner.hasNextInt()) {  // Read user input for content number
				int index = scanner.nextInt(); // Read the rest of the line
				scanner.nextLine(); // Consume the newline character
		
				boolean deleted = mylibrary.delContentFromPlaylist(index, playlistTitle); // Delete the content from the playlist
				if (deleted) { // If the content is deleted
					System.out.println(); // Print new line
				} else {
					throw new IllegalArgumentException("Playlist Not Found in Library"); // Print error messageSO
				}
			} else {
				System.out.println("Invalid index"); // Print error message
			}	
		}
	}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}

			System.out.print("\n>");
		}
		scanner.close();
	}


}
