//name: Sankeerthikan Nimalathas
//Student ID: 501161043

import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title; // the title of the playlist
	private ArrayList<AudioContent> contents; // an array list of AudioContent

	
	public Playlist(String title)
	{
		this.title = title; // set the title to the title passed in
		contents = new ArrayList<>(); // create a new array list of AudioContent
	}
	public String getTitle()
	{
		return title;	 // return the title
	}
	public void setTitle(String title)
	{
		this.title = title; // set the title to the title passed in
	}

	public void addContent(AudioContent content)
	{
		for (int i = 0; i < contents.size(); i++) { // check if the content is already in the playlist
			if (contents.get(i).equals(content)) {// if it is, return and do not add it again to the playlist
				return;	 // return to the caller
			}
		}
		contents.add(content); // add the content to the playlist
	}
	
	public ArrayList<AudioContent> getContent()
	{
		
		return contents; // return the contents array list
	} 

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents; // set the contents array list to the contents array list passed in
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	
	public void printContents()
	{
    for (int i = 0; i < contents.size(); i++) { // loop through the contents array list 
        int index = i + 1;// index starts at 1
        System.out.print("" + index + ". "); // print the index
        contents.get(i).printInfo(); // print the info of the audio content object
        System.out.println(); // print a new line
	}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		for (int i = 0; i < contents.size(); i++) // loop through the contents array list
		{
			contents.get(i).play(); // play the audio content object
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		if (index < 1 || index > contents.size()) // check if the index is in the correct range
			return; 
		contents.get(index - 1).play(); // play the audio content object

	}
	
	public boolean contains(int index)
	{
		if (index < 1 || index > contents.size()) // check if the index is in the correct range 
			return false;
		return true;
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		if (other == null) // check if the other object is null
			return false; 
		if (this == other) // check if the other object is the same object
			return true;
		if (this.getClass() != other.getClass()) // check if the other object is of the same class
			return false;
		Playlist otherPlaylist = (Playlist) other; // cast the other object to Playlist
		return this.title.equals(otherPlaylist.title); // check if the titles are equal
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (contains(index)) { // check if the index is in the correct range
			contents.remove(index - 1); // remove the audio content object from the array list
		}

		
		
		
	}
	
	
}
