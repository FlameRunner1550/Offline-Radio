import java.util.*;


public class Mp3Player {

   LinkedList <String> songList = new LinkedList<>();

    public String getSongList() {
        return songList.toString();
    }

    public void setSongList(LinkedList<String> songList) {
        this.songList = songList;
    }

    public void pingList() {
        System.out.println("Current Song List: " + songList);
    }
    
    public Mp3Player() {
        // TODO: Implement MP3 playback logic
    }

    public void play(String filePath) {
        // TODO: Play MP3 file
    }

    public void stop() {
        // TODO: Stop playback
    }
  




}
