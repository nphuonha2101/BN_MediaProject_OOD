package project.mediaplayer.model;

public class Song {

    private final int songID;
    private String songName;
//    private String singer;
//    private String genre;

    private boolean isFavorite;
    //    private Time songDuration;
    private String songPath;

    public Song(int songID, String songName, boolean isFavorite, String songPath) {
        this.songID = songID;
        this.songName = songName;

//        this.singer = singer;
//        this.genre = genre;
        this.isFavorite = isFavorite;
//        this.songDuration = songDuration;
        this.songPath = songPath;
    }


    // GETTER METHODS

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


    //////////////////////////////////////////


    // SETTER METHODS

    public void setSongName(String songName) {
        this.songName = songName;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    //////////////////////////////////////////


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
