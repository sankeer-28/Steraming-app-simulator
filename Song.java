//name: Sankeerthikan Nimalathas
//Student ID: 501161043

/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
public class Song extends AudioContent // implement the Comparable interface
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre;  // Genre of the song
	private String lyrics; // Lyrics of the song
	
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Song instance variables. 
		super(title, year, id, type, audioFile, length); // call the constructor in the superclass
		this.artist = artist;  // set the artist
		this.composer = composer;  // set the composer
		this.genre = genre; // set the genre
		this.lyrics = lyrics; // set the lyrics
		super.setId(id); // set the id

	}
	
	public Song(String title, int year, String id, String typename2, StringBuilder lyrics2, int length, String artist2,
            String composer2, String genre2) {
    }

    public Song(String id, String title, int year, int length, String artist2, String composer2, String genre2,
			String string) {
	}

	public Song(String title, int year, String id, String typename2, String lyrics2, int length, String artist2,
			String composer2, String genre2, String lyrics3) {
	}

	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre 
	public void printInfo()
	{
		super.printInfo(); // call the printInfo() method in the superclass
		System.out.println("Artist: " + artist + " Composer: " + composer + " Genre: " + genre); // print the artist, composer, and genre
	}
	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{
		String audioFilePath = super.getAudioFile(); // get the audio file path
    if (audioFilePath != null) { // check if the audio file path is not null
        super.play(); // call the play() method in the superclass
    } else {
        System.out.println("Error: Audio file not found"); // print error message if the audio file is not found
	
		
    }
	}
	
	public String getComposer()
	{
		return composer;
	}
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other)
	{
		if (other instanceof Song) // check if the other object is a Song
		{
			Song otherSong = (Song) other; // cast the other object to a Song
			if (super.equals(otherSong) && this.getArtist().equals(otherSong.getArtist()) && this.getComposer().equals(otherSong.getComposer())) // check if the other song has the same artist and composer
			{
				return true;
			}
		}
		return false;
	
	}
	
	
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other)
	{
		return this.getTitle().compareTo(other.getTitle());
	}
}
