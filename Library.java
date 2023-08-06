//name: Sankeerthikan Nimalathas
//Student ID: 501161043

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
  //private ArrayList<Podcast> 	podcasts;
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	
	
	
	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  //podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		if (songs.isEmpty()) { // Check if songs list is empty and print "No Songs" if it is empty and return from the method 
			System.out.println("No Songs"); // Print "No Songs"
			return;// Return from the method
		}
		for (int i = 0; i < songs.size(); i++) { 
			int index = i + 1; // Print the index number of the song in the list
			System.out.print("" + index + ". "); // Print the index number of the song in the list
			songs.get(i).printInfo(); // Call the printInfo() method of the song
			System.out.println(); // Print a new line after each song 
		}
	}
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		if (audiobooks.isEmpty()) { // Check if audiobooks list is empty and print "No AudioBooks" if it is empty and return from the method
			System.out.println("No AudioBooks"); // Print "No AudioBooks"
			return;// Return from the method
		}
		for (int i = 0; i < audiobooks.size(); i++) {   // Print the index number of the audiobook in the list and call the printInfo() method of the audiobook 
			int index = i + 1; 
			System.out.print("" + index + ". "); 
			audiobooks.get(i).printInfo();
			System.out.println();
		}
	}
  
	public void printInfo() {  // Print the name of all songs in the songs array list
		// Code to print playlist information
		for (int i = 0; i < playlists.size(); i++) { 
			Playlist playlist = playlists.get(i);  // Get the playlist at index i
			System.out.println("Playlist: " + playlist.getTitle()); // Print the title of the playlist
			playlist.printContents(); // Print the contents of the playlist
		}	
	}
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()  
	{
		if (playlists.isEmpty()) { // Check if playlists list is empty 
			return;// Return from the method
		}
		for (int i = 0; i < playlists.size(); i++) { // Print the index number of the playlist in the list and the title of the playlist
			int index = i + 1;
			System.out.print("" + index + ". "); 
			System.out.println(playlists.get(i).getTitle()); 
		}
	}
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList<String> artists = new ArrayList<String>();   // Create a new (empty) array list of string
    	for (int i = 0; i < songs.size(); i++) {   // Go through the songs array list and add the artist name to the new arraylist only if it is not already there
        String artist = songs.get(i).getArtist(); 
        if (!artists.contains(artist)) { // Check if the artist is already in the list
            artists.add(artist); 
        }
   		 }for (int i = 0; i < artists.size(); i++) { 
        	System.out.println((i+1) + ". " + artists.get(i));   // Print the index number of the artist in the list and the name of the artist
    	}
	}
	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public boolean deleteSong(int index ) 
	{
		// Check if index is valid
		if (index < 0 || index > songs.size()) {  
			throw new IllegalArgumentException("Invalid index"); // Throw an IllegalArgumentException if the index is invalid
			
		}
		// Remove the song from the songs list
		Song removedSong = songs.remove(index - 1);  
		// Remove the song from all playlists that contain it
		for (Playlist playlist : playlists) { 
			if (playlist.getContent().contains(removedSong)) {  // Check if the playlist contains the song that is being removed from the library 
				playlist.deleteContent(playlist.getContent().indexOf(removedSong));
			}
		}return true;   // Return true if the song is removed from the library and all playlists that contain it 
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()  
	{ 
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator());  // Sort songs by year using the SongYearComparator class 
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {    
			return s1.getYear() - s2.getYear();  
		}
	}	
	// Sort songs by length
	public void sortSongsByLength() {
		Collections.sort(songs, new SongLengthComparator());  // Sort songs by length using the SongLengthComparator class 
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song> {  
		public int compare(Song s1, Song s2) {  
			return s1.getLength() - s2.getLength();
		}
	}
	// Sort songs by title 
	public void sortSongsByName() {
		Collections.sort(songs, (s1, s2) -> s1.getTitle().compareTo(s2.getTitle()));  // Sort songs by title using lambda expression 
    }
	/*
	 * Play Content
	 */
	// Play song from songs list
	public boolean playSong(int index)  
	{  
		if (index < 1 || index > songs.size()) {  // Check if index is valid  
			throw new IllegalArgumentException("Invalid index");  // Throw an IllegalArgumentException if index is invalid
		}
		Song songToPlay = songs.get(index - 1);  // Get the song at the specified index 
		System.out.println("Title: " + songToPlay.getTitle() + " Id: " + songToPlay.getId() + " Year: " + songToPlay.getYear() + " Type: " + songToPlay.getType()+ " Length: " + songToPlay.getLength() );
		System.out.println("Artist: " + songToPlay.getArtist() + " Composer: " + songToPlay.getComposer() + " Genre: " + songToPlay.getGenre());
		System.out.println();
		System.out.println(songToPlay.getLyrics());  // Print the lyrics of the song
		return true;   
	}
	// Play a chapter of an audio book from list of audiobooks
	public boolean playAudioBook(int index, int chapter)  
	{
		try{
			if (index < 1 || index > audiobooks.size()) {  // Check if index is valid 
				throw new IllegalArgumentException("Invalid index");  // Throw an IllegalArgumentException if index is invalid
			}
			AudioBook book = audiobooks.get(index - 1);  // Get the audiobook at the specified index
			ArrayList<String> chapterTitles = book.getChapterTitles();  // Get the chapter titles and chapters of the audiobook
			ArrayList<String> chapters = book.getChapters();  
			if (chapter < 1 || chapter > chapterTitles.size()) {  // Check if the chapter number is valid
				throw new IllegalArgumentException("Invalid chapter number");  // Throw an IllegalArgumentException if chapter number is invalid
			} else {
				System.out.println("Title: " + book.getTitle() + " Id: " + book.getId() + " Year: " + book.getYear() + " Type: " + book.getType()+ " Length: " + book.getLength() );
				System.out.println("Chapter: " + chapterTitles.get(chapter - 1));  // Print the chapter title
				System.out.println();
				System.out.println(chapters.get(chapter - 1));  // Print the chapter
				return true;
			}
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			return false;
		}
	
	}
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public boolean printAudioBookTOC(int index)  
	{
		try{
			if (index < 1 || index > audiobooks.size()) {  // Check if index is valid 
				throw new IllegalArgumentException("Audiobook not found");  // Throw an IllegalArgumentException if index is invalid
			}
			AudioBook audbook = audiobooks.get(index - 1);  // Get the audiobook at the specified index
			audbook.printTOC();  // Call the printTOC() method of the audiobook
			return true;  
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
   /* Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public boolean makePlaylist(String title)  
	{  
		try {
			if (title == null || title.equals("")) {  // Check if the title is null or empty
				throw new IllegalArgumentException("Invalid title");  // Throw an IllegalArgumentException if title is invalid
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return false;
		}
			for (int i = 0; i < playlists.size(); i++) {  // Check if a playlist with the same title already exists
			if (playlists.get(i).getTitle().equals(title)) {  // If it does, return false
				throw new IllegalArgumentException("Playlist already exists");  // Throw an IllegalArgumentException if playlist already exists
				
			}
		}
		Playlist newPlaylist = new Playlist(title);  // Create a new playlist and add it to the list of playlists
		playlists.add(newPlaylist);  // Add the playlist to the list of playlists
		return true;  
	}
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public boolean printPlaylist(String title)  
	{
		try{
			if (title == null || title.equals("")) {  // Check if the title is null or empty
				throw new IllegalArgumentException("Invalid title");  // Throw an IllegalArgumentException if title is invalid
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return false;
		}
		for (int i = 0; i < playlists.size(); i++) {  // Check if a playlist with the same title already exists
			if (playlists.get(i).getTitle().equals(title)) {  // If it does, print the contents of the playlist
				playlists.get(i).printContents();  // Call the printContents() method of the playlist
				return true;   
			}
		}
		throw new  IllegalArgumentException("Playlist not found");  // Throw an IllegalArgumentException if playlist is not found
		
	}
	// Play all content in a playlist
	public boolean playPlaylist(String playlistTitle)   // Play all content in a playlist
	{
		for (int i = 0; i < playlists.size(); i++) {    // Check if a playlist with the same title already exists
			if (playlists.get(i).getTitle().equals(playlistTitle)) {   // If it does, play all the content in the playlist  
				playlists.get(i).playAll(); // Call the playAll() method of the playlist
				return true; 
			}
		}
		return false;
	}
	// Play a specific song/audiobook in a playlist  
	public boolean playPlaylist(String playlistTitle, int indexInPL) 
	{
		for (int i = 0; i < playlists.size(); i++) {   // Check if a playlist with the same title already exists
			if (playlists.get(i).getTitle().equals(playlistTitle)) {  
				ArrayList<AudioContent> contents = playlists.get(i).getContent();  // Get the content of the playlist
				if (indexInPL >= 0 && indexInPL < contents.size()) {  // Check if the index is valid
					AudioContent content = contents.get(indexInPL);  // Get the content at the specified index
					content.play();  // Call the play() method of the content
					return true;  
				} else {
					System.out.println("Invalid index for playlist: " + playlistTitle);   // Print an error message
					return false;  
				}
			}
		}
		System.out.println("Playlist not found: " + playlistTitle);  // Print an error message
		return false;
	}
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public boolean addContentToPlaylist(String type, int index, String playlistTitle) 
	{
		if (type.equals(Song.TYPENAME)) {  // Check if the type is a song
			if (index < 1 || index > songs.size()) {  // Check if the index is valid
				throw new IllegalArgumentException("Song not found");  // Throw an IllegalArgumentException if index is invalid		
			}
			Song songToAdd = songs.get(index - 1);  // Get the song at the specified index
			for (int i = 0; i < playlists.size(); i++) {   
				if (playlists.get(i).getTitle().equals(playlistTitle)) {  // Check if a playlist with the same title already exists
					playlists.get(i).addContent(songToAdd);  // Add the song to the playlist
					return true;
				}
			}
			throw new IllegalArgumentException("Playlist not found");  // Throw an IllegalArgumentException if playlist is not found
		} else if (type.equals(AudioBook.TYPENAME)) {  // Check if the type is an audiobook
			if (index < 1 || index > audiobooks.size()) {  // Check if the index is valid
				throw new IllegalArgumentException("Audiobook not found");  // Throw an IllegalArgumentException if index is invalid	
			}
			AudioBook audioBookToAdd = audiobooks.get(index - 1);  // Get the audiobook at the specified index
			for (int i = 0; i < playlists.size(); i++) {
				if (playlists.get(i).getTitle().equals(playlistTitle)) {  // Check if a playlist with the same title already exists
					playlists.get(i).addContent(audioBookToAdd);  // Add the audiobook to the playlist
					return true;
				}
			}
			throw new IllegalArgumentException("Playlist not found");  // Throw an IllegalArgumentException if playlist is not found
		} else {
			throw new IllegalArgumentException("Invalid type");  // Throw an IllegalArgumentException if type is invalid	
		}
	}
  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public boolean delContentFromPlaylist(int index, String title)
	{
		for (int i = 0; i < playlists.size(); i++) {  // Check if a playlist with the same title already exists
			if (playlists.get(i).getTitle().equals(title)) {
				if (index < 0 || index > playlists.get(i).getContent().size()) {  // Check if the index is valid
					throw new IllegalArgumentException("Invalid index");  // Throw an IllegalArgumentException if index is invalid
					
				}
				playlists.get(i).deleteContent(index );  // Delete the content at the specified index
				return true;// Return true if the content is deleted
			}
		}
		throw new IllegalArgumentException("Playlist not found");  // Throw an IllegalArgumentException if playlist is not found
	}
	public void download(int fromIndex, int toIndex, AudioContentStore store) {
		//print out the type and title of the content and "added to library"if the song is already in the library, print out type, title and "already in library"
		try {
			if (fromIndex < 0 || toIndex >= store.contents.size()) {
				throw new IndexOutOfBoundsException("Invalid index range"); // Throw an IndexOutOfBoundsException if the index is invalid
			}
			if (fromIndex > toIndex) { // Throw an IllegalArgumentException if fromIndex is greater than toIndex
				throw new IllegalArgumentException("fromIndex must be less than or equal to toIndex");
			}
			for (int i = fromIndex; i <= toIndex; i++) { // Loop through the content in the store 
				AudioContent content = store.getContent(i); // Get the content at the specified index
				if (content instanceof Song) { // Check if the content is a song
					if (songs.contains((Song) content)) { // Check if the song is already in the library
						System.out.println("Song " + content.getTitle() + " already downloaded"); // Print an error message
					} else {
						songs.add((Song) content); // Add the song to the library
						System.out.println("SONG " + content.getTitle() + " Added to Library"); // Print a message
					}
				} else if (content instanceof AudioBook) { // Check if the content is an audiobook
					if (audiobooks.contains((AudioBook) content)) { // Check if the audiobook is already in the library
						System.out.println("AudioBook " + content.getTitle() + " already downloaded"); // Print an error message
					} else {
						audiobooks.add((AudioBook) content); // Add the audiobook to the library
						System.out.println("AUDIOBOOK " + content.getTitle() + " Added to Library"); // Print a message
					}
				}
			}
		} catch (Exception e) { // Catch any exceptions
			System.out.println(e.getMessage()); // Print the error message
		}
	}
    public void downloada(String artist, AudioContentStore store) { // Download all songs/audiobooks by the given artist
		
		try {
			boolean artistFound = false; // Set artistFound to false
			for (int i = 0; i < store.contents.size(); i++) { // Loop through the content in the store
				AudioContent content = store.getContent(i); // Get the content at the specified index
				if (content instanceof Song) { // Check if the content is a song
					if (((Song) content).getArtist().equals(artist)) { // Check if the song is by the given artist
						artistFound = true; // Set artistFound to true
						if (songs.contains((Song) content)) { // Check if the song is already in the library
							System.out.println("Song " + content.getTitle() + " already downloaded"); // Print an error message
						} else {
							songs.add((Song) content); // Add the song to the library
							System.out.println("SONG " + content.getTitle() + " Added to Library"); // Print a message
						}
					}
				} else if (content instanceof AudioBook) { // Check if the content is an audiobook
					if (((AudioBook) content).getAuthor().equals(artist)) { // Check if the audiobook is by the given artist
						artistFound = true; // Set artistFound to true
						if (audiobooks.contains((AudioBook) content)) { // Check if the audiobook is already in the library
							System.out.println("AudioBook " + content.getTitle() + " already downloaded"); // Print an error message
						} else {
							audiobooks.add((AudioBook) content); // Add the audiobook to the library
							System.out.println("AUDIOBOOK " + content.getTitle() + " Added to Library"); // Print a message
						}
					}
				}
			}
			if (!artistFound) { // Check if the artist was found
				throw new Exception("Artist not found " + artist); // Throw an Exception if the artist was not found
			}
		} catch (Exception e) { // Catch any exceptions
			System.out.println(e.getMessage()); // Print the error message
		}
	}
	//•	SEARCH: Add an action called SEARCH to myAudioUI as well as necessary code to class AudioContentStore that searches the store for an audio content with the specified title. That is, the user types SEARCH then is prompted to enter a title string. If the audio content with this title is found in the store then print the index of this content and the info for this content. A separate part of this new functionality is to use a Map in class AudioContentStore that maps a title (string) to an integer value. The integer value represents an index into the contents array list. See Requirement 3 below for details on the Map part of the assignment. The SEARCH functionality can be done without a Map but you will not receive full marks for this part.  

	public void search(String title, AudioContentStore store) { // Search for an audio content with the given title
		
		try{
			int index = store.getContentMap().get(title); // Get the index of the content with the given title
			System.out.print(index+". "); // Print the index
			store.getContent(index-1).printInfo(); // Print the info for the content
		}catch(Exception e){ // Catch any exceptions
			System.out.println("No matches for "+ title); // Print an error message
		}
	}
	//•	SEARCHA: Add an action called SEARCHA to myAudioUI as well as necessary code to class AudioContentStore that searches the store for an audio content with the specified artist name. That is, the user types SEARCHA then is prompted to enter an artist string. If the audio content with this artist name is found in the store then print the indices and info of all audio content with this artist (use author string for audio books). A separate part of this new functionality is to use a Map in class AudioContentStore that maps an artist string to an array list of integer. The integers in the array list represent indices into the contents array list. See Requirement 3 below for details on the Map part of the assignment. The SEARCHA functionality can be done without a Map but you will not receive marks for the Map part.  

	public void searcha(String artist, AudioContentStore store) { // Search for an audio content with the given artist name
		try{ // Try to get the index of the content with the given artist name
			ArrayList<Integer> index = store.getArtistMap().get(artist); 	
			for(int i = 0; i < index.size(); i++){ // Loop through the indices
				System.out.print(index.get(i)+". "); // Print the index
				store.getContent(index.get(i)-1).printInfo(); // Print the info for the content
				System.out.println(); // Print the info for the content
			}
		}catch(Exception e){ // Catch any exceptions
			System.out.println("No matches for "+ artist); // Print an error message
		}
	}
	//•	SEARCHG: Add an action called SEARCHG to myAudioUI as well as necessary code to class AudioContentStore that searches the store for a song with the specified genre (“POP” “ROCK” etc). That is, the user types SEARCHG then is prompted to enter a genre string. If the song with this genre is found in the store then print the indices and info of all songs with this genre. A separate part of this new functionality is to use a Map in class AudioContentStore that maps a genre string to an array list of integer. The integers in the array list represent indices into the contents array list. See Requirement 3 below for details on the Map part of the assignment. The SEARCHG functionality can be done without a Map but you will not receive marks for the Map part.  
	public void searchg(String genre, AudioContentStore store) {
		//use genreMap
		try {
			ArrayList<Integer> index = store.getGenremap().get(genre); // Get the index of the content with the given genre
			for (int i = 0; i < index.size(); i++) { // Loop through the indices
				System.out.print(index.get(i) + ". "); // Print the index
				store.getContent(index.get(i) - 1).printInfo(); // Print the info for the content
				System.out.println(); // Print the info for the content 
			}
		} catch (Exception e) {
			System.out.println("No matches for " + genre); // Print an error message
		}
	}
	//•	DOWNLOADG: Create a new download action that takes a genre string as parameter and downloads all songs in this genre from the store. Make use of the genre map. 
	public void downloadg(String genre, AudioContentStore store) { // Download all songs with the given genre
		try {
			ArrayList<Integer> index = store.getGenremap().get(genre); // Get the index of the content with the given genre
			for (int i = 0; i < index.size(); i++) { // Loop through the indices
				AudioContent content = store.getContent(index.get(i) - 1); // Get the content at the given index
				if (content instanceof Song) { // Check if the content is a song
					if (songs.contains((Song) content)) { // Check if the song is already downloaded
						System.out.println("Song " + content.getTitle() + " already downloaded"); // Print an error message
					} else {
						songs.add((Song) content);
						System.out.println("SONG " + content.getTitle() + " Added to Library"); // Print a message
					}
				} else if (content instanceof AudioBook) { // Check if the content is an audiobook
					if (audiobooks.contains((AudioBook) content)) { // Check if the audiobook is already downloaded
						System.out.println("AudioBook " + content.getTitle() + " already downloaded"); // Print an error message
					} else {
						audiobooks.add((AudioBook) content); // Add the audiobook to the library
						System.out.println("AUDIOBOOK" + content.getTitle() + " Added to Library"); // Print a message
					}
				}
			}
		} catch (Exception e) { // Catch any exceptions
			System.out.println(e.getMessage()); // Print the error message
		}
	}
}

