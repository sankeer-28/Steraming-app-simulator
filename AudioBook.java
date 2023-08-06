//name: Sankeerthikan Nimalathas
//Student ID: 501161043
import java.util.ArrayList;
/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	
	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional AudioBook instance variables. 
		super(title, year, id, type, audioFile, length);
		this.author = author;
		this.narrator = narrator;
		this.chapterTitles = chapterTitles;
		this.chapters = chapters;
		super.setId(id);

	}
	
	public AudioBook(String title, int year, String id, String typename2, String string, int length, String author2,
            String narrator2, String string2) {
    }

    public AudioBook(String title, int year, String id, String typename2, int length, String narrator2,
            String narrator3, String[] chaptertitles2) {
    }

    public String getType()
	{
		return TYPENAME;
	}

  // Print information about the audiobook. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	// see the video
	public void printInfo()
	{
		super.printInfo(); 
		System.out.println("Author: " + author + " Narrator: " + narrator); 
		
	}
	
  // Play the audiobook by setting the audioFile to the current chapter title (from chapterTitles array list) 
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	public void play()
	{
		super.setAudioFile(chapterTitles.get(currentChapter) + " " + chapters.get(currentChapter));
		super.play();

	}
	
	// Print the table of contents of the book - i.e. the list of chapter titles
	// See the video
	public void printTOC()
	{
		for (int i = 0; i < chapterTitles.size(); i++) { // loop through the chapter titles
			
			System.out.println("Chapter " + (i + 1) + ". " + chapterTitles.get(i)); // print the chapter number and title
			System.out.println(""); // print a blank line
		}
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter) 
	{
		currentChapter = chapter - 1 ; // set the current chapter to the chapter number - 1 (to account for 0 index)

	}
	
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{ 
		if (other instanceof AudioBook)    // check if the other object is an instance of AudioBook
		{
			AudioBook otherBook = (AudioBook) other;   // cast the other object to AudioBook
			if (super.equals(otherBook) && author.equals(otherBook.author) && narrator.equals(otherBook.narrator))  // check if the author and narrator are equal 
			{
				return true; // return true if they are equal
			}
		}
		return false; // return false if they are not equal
	}
	
	public int getNumberOfChapters() 
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}

}
