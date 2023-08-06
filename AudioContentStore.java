//name: Sankeerthikan Nimalathas
//Student ID: 501161043

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;


// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore 
{

	private Map<String, Integer> contentmap; // map of content id to index in contents array list
	private Map<String,ArrayList<Integer>> artistmap;//artist map
	private Map<String,ArrayList<Integer>> genremap;//genre map
	ArrayList<AudioContent> contents; // an array list of AudioContent

	public void listAll() // list all contents
	{
		for (int i = 0; i < contents.size(); i++)  // loop through contents
			{
				int index = i + 1; // index of content
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
	}

	private ArrayList<AudioContent> read() throws IOException
			{
				ArrayList<AudioContent>ui=new ArrayList<AudioContent>();
				Scanner in=new Scanner(new File("store.txt"));//read file
				while(in.hasNextLine()) //while there is a line to read from file 
				{
					if(in.nextLine().equals("AUDIOBOOK"))//if audiobook
					{
						String id=in.nextLine();//read info for audiobook
						String title=in.nextLine();// read the title
						int year=in.nextInt(); // read the year
						int length=in.nextInt(); // read the length
						in.nextLine(); // read the newline character
						String author=in.nextLine(); // read the author
						String narrator=in.nextLine(); // read the narrator
						int chapters=in.nextInt();//read number of chapters
						in.nextLine(); // read the newline character
						ArrayList<String> chaptertitles=new ArrayList<>(); // array list of chapter titles
						int index=0; // index of the chapter
						while(index<chapters)//add chapter titles
						{
							chaptertitles.add(in.nextLine()); // read the chapter title
							index++; // increment the index
						}
						ArrayList<String> chapterinfo=new ArrayList<>(); // array list of chapter contents
						while(in.hasNextInt())//add chapter info
						{
							int lin=in.nextInt(); // read the number of lines in the chapter
							in.nextLine(); // read the newline character
							String content=""; // content of the chapter
							for(int i=0;i<lin;i++) // read the lines of the chapter
							{
								content+=in.nextLine()+"\r\n"; // read the line
							}
							chapterinfo.add(content); // add the content to the array list
						}
						//add audiobook to list of contents
						ui.add(new AudioBook(title, year, id, AudioBook.TYPENAME, "", length, author, narrator, chaptertitles, chapterinfo));

					}
					//if song
					else 
					{
						String id=in.nextLine();//read info for song
						String title=in.nextLine(); // read the title
						int year=in.nextInt(); // read the year
						int length=in.nextInt(); // read the length
						in.nextLine(); // read the newline character
						String artist=in.nextLine(); // read the artist
						String composer=in.nextLine(); // read the composer
						Song.Genre genre=Song.Genre.valueOf(in.nextLine()); // read the genre
						int lines=in.nextInt(); // read the number of lines of lyrics
						in.nextLine(); // read  the newline character
						String lyrics=""; // lyrics of the song
						for(int i=0;i<lines;i++)//add lyrics to string
						{
							lyrics+=in.nextLine()+"\r"; // read the lyrics
						}
						ui.add(new Song(title, year, id, Song.TYPENAME, lyrics, length, artist, composer, genre, lyrics));//add song to list of contents
					}
				}
				return ui;	//return list of contents
			}
			// constructor to initialize the contents array list
		public AudioContentStore()
		{
			try{
				contents=read();//read contents from file
			}
			catch(IOException e)
			{
				System.out.println("File not found");// if file not found print error
			}
			contentmap=new TreeMap<String, Integer>();//content map
			for(int i=0;i<contents.size();i++) //loop through contents
			{
				contentmap.put(contents.get(i).getTitle(),(i+1));// add title and index to map
			}
			artistmap=new TreeMap<String,ArrayList<Integer>>();//artist map
			for(int i=0;i<contents.size();i++) //loop through contents
			{

				if(contents.get(i).getType().equals("AUDIOBOOK"))//if type is audiobook
				{
					AudioBook sng= (AudioBook)contents.get(i);
					if(!artistmap.containsKey(sng.getAuthor()))//check if author in map
					{
						ArrayList<Integer> books=new ArrayList<>(); //books to add to map
						for(int j=0;j<contents.size();j++) //loop through contents
						{
							if(contents.get(j).getType().equals("AUDIOBOOK")) //if type is audiobook
							{
								AudioBook flip= (AudioBook)contents.get(j);
								if(flip.getAuthor()==sng.getAuthor())//check author of audiobook
								{
									books.add((j+1)); //add audiobook to list
								}
							}
						}
						artistmap.put(sng.getAuthor(), books);//add author and books to map
					}

				}
				else if(contents.get(i).getType().equals("SONG"))//if type is song
				{
					Song sng= (Song)contents.get(i);
					if(!artistmap.containsKey(sng.getArtist()))//check if artist in map
					{
						ArrayList<Integer> songs=new ArrayList<>();//songs to add to map
						for(int j=0;j<contents.size();j++) //loop through contents
						{
							if(contents.get(j).getType().equals("SONG")) //if type is song
							{
								Song flip= (Song)contents.get(j);
								if(flip.getArtist().equals(sng.getArtist()))//check artist of song
								{
									songs.add((j+1)); //add song to list
								}
							}
						}
						artistmap.put(sng.getArtist(), songs);//add artist and songs to map
					}
				}
			}
			genremap=new TreeMap<String,ArrayList<Integer>>();//genre map
			for(int i=0;i<contents.size();i++) //loop through contents
			{ 
				if(contents.get(i).getType().equals("SONG"))//if type is song
			{
					Song sng= (Song)contents.get(i);
					if(!artistmap.containsKey(sng.getGenre().name()))//check if genre in map
					{
						ArrayList<Integer> songs=new ArrayList<>(); //songs to add to map
						for(int j=0;j<contents.size();j++) //loop through contents
						{
							if(contents.get(j).getType().equals("SONG")) //if type is song
							{
								Song flip= (Song)contents.get(j); 
								if(flip.getGenre()==sng.getGenre()) //check genre of song
								{
									songs.add((j+1)); //add song to list
								}
							}
						}
						genremap.put(sng.getGenre().name(), songs);//add genre and songs to map
					}
				}
			}
		}

		public Map<String,ArrayList<Integer>> getArtistMap()// return artist map
		{
			return artistmap;
		}
		public Map<String, Integer> getContentMap()// return content map
		{
			return contentmap;
		}
		public Map<String,ArrayList<Integer>> getGenremap()// return genre map
		{
			return genremap;
			
		}public AudioContent getContent(int index) // return content at index
		{
			return contents.get(index); 

		}
}
