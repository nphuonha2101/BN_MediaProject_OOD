package project.mediaplayer.model;

public class Song {
    private final int songID;
    private String songName;
    private boolean isFavorite;
    private String songPath;

    //-----------------------CONSTRUCTOR------------------------//
    public Song(int songID, String songName, boolean isFavorite, String songPath) {
        this.songID = songID;
        this.songName = songName;
        this.isFavorite = isFavorite;
        this.songPath = songPath;
    }


    //-----------------------GETTERS AND SETTERS------------------------//
    public int getSongID() {
        return this.songID;
    }

    public String getSongName() {
        return songName;
    }

    public boolean isFavorite() {
        return this.isFavorite;
    }

    public String getSongPath() {
        return this.songPath;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    //-----------------------TO STRING METHOD------------------------//
    @Override
    public String toString() {
        return "Song{" +
                "songID=" + songID +
                ", songName=" + songName +
                ", isFavorite=" + isFavorite +
                ", songPath='" + songPath +
                '}';
    }
}
