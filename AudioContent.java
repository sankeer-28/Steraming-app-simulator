//name: Sankeerthikan Nimalathas
//Student ID: 501161043

/*
 *  Audio Content contains information common to all types of audio (e.g. songs, audiobooks etc)
 */

abstract public class AudioContent
{
	String title;			
	private int year; 				// year published
	private String id;				// id
	private String type;			// 
	private String audioFile; // file containing the audio content - e.g. a song.
	private int length; 			// minutes

	
	public AudioContent()
	{
		this.title = "";
		this.year = 0;
		this.id = "";
		this.type = "AUDIOCONTENT";
		this.audioFile = "";
		this.length = 0;
	}
	
	public AudioContent(String title, int year, String id, String type, String audioFile, int length)
	{
		this.title = title; // set the title
		this.year = year; // set the year
		this.id = id; // set the id
		this.type = type; // set the type
		this.audioFile = audioFile; // set the audio file
		this.length = length; // set the length
	}

	// Subclasses define their type (e.g. "Song") 
	abstract public String getType();

	// Print Information 
  public void printInfo()
  {
    System.out.println("Title: " + title + " Id: " + id + " Year: " + year + " Type: " + type + " Length: " + length);	  
  }
	
  // Play the content via the audio file
	public void play() 
	{
		this.printInfo();
		// Simulate playing of the audio file. For example, for a song this would be printing the lyrics
		System.out.println("\n" + audioFile); // print the lyrics
		System.out.println(); // new line to create a blank line after the lyrics
	}
	
	// Two AudioContent objects are equal if they have the same title and id
	public boolean equals(Object other)
	{
		if (other == null) // check if the other object is null
			return false; 
		if (this == other) // check if the other object is the same as this object
			return true;
		if (this.getClass() != other.getClass()) // check if the other object is of the same class
			return false;
		AudioContent otherContent = (AudioContent) other; // cast the other object to AudioContent
		return (this.title.equals(otherContent.title) && this.id.equals(otherContent.id)); // check if the titles and ids are equal
		
	}
  
	public String getAudioFile()
	{
		return this.audioFile;
	}

	public void setAudioFile(String file)
	{
		this.audioFile = file;
	}

	public int getLength()
	{
		return this.length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getYear()
	{
		return this.year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

    public char[] getInfo() {
       try {
			return (this.title + " " + this.year + " " + this.id + " " + this.type + " " + this.audioFile + " " + this.length).toCharArray();
		} catch (Exception e) {
			return null;
		}
    }

	

}
