import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.LinkedList;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;


public class Mp3Player {

   LinkedList <String> songList = new LinkedList<>();
    int songIndex = 0;
    private AdvancedPlayer mp3Player;
    private Thread playerThread;    
           
            


    public String getSongList() 
    {
        return songList.toString();
    }

    public void setSongList(LinkedList<String> songList) 
    {
        this.songList = songList;
    }

    public void pushSongList(String song) 
    {
        this.songList.add(song);
    }

    public int getSongIndex()
    {
        return songIndex;
    }

    public void setSongIndex(int songIndex)
    {
        this.songIndex = songIndex;
    }

    public void pingList() 
    {
        songList.get(songIndex);
        System.out.println("Now playing: " + songList.get(songIndex));
        
    }
    
    public Mp3Player() 
    {
        // TODO: Implement MP3 playback logic
    }

    public void play(int filePathlocal) 
    {
        if (playerThread != null && playerThread.isAlive()) 
        {
            stop();
        }

        String filePath = songList.get(filePathlocal);
        System.out.println("Playing file: " + filePath);
    
        try 
        {
            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            this.mp3Player = new AdvancedPlayer(bis);

            mp3Player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    // When a song finishes, move to the next song in the list
                    songIndex++;
                    if (songIndex >= songList.size()) {
                        songIndex = 0; // Loop back to the first song
                        System.out.println("Ping!");
                    }
                    play(songIndex); // Play the next song
                    System.out.println("Index Number " + songIndex);
                }
            });

            playerThread = new Thread(() -> {
                try {
                    mp3Player.play();
                }
                 catch (Exception e) {
                    e.printStackTrace();
                }

            });
            playerThread.start();
            
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    

    public void stop() 
    {
        if (mp3Player != null)
        {
            mp3Player.close();
        }
        
    }
  




}
