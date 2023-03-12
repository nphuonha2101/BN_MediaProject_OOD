package project.mediaplayer.model;

public class Song {


    private String songName;
//    private String singer;
//    private String genre;

    private boolean isFavorite;
//    private Time songDuration;
    private String songPath;

    public Song(String songName, boolean isFavorite, String songPath) {
        this.songName = songName;

//        this.singer = singer;
//        this.genre = genre;
        this.isFavorite = isFavorite;
//        this.songDuration = songDuration;
        this.songPath = songPath;
    }



    // GETTER METHODS
    public String getSongName() {
        return songName;
    }
//
//    public String getSinger() {
//        return singer;
//    }
//
//    public String getGenre() {
//        return genre;
//    }

    public boolean isFavorite() {
        return this.isFavorite;
    }

//    public Time getSongDuration() {
//        return this.songDuration;
//    }

    public String getSongPath() {
        return this.songPath;
    }


    //////////////////////////////////////////


    // SETTER METHODS

    public void setSongName(String songName) {
        this.songName = songName;
    }

//    public void setSinger(String singer) {
//        this.singer = singer;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

//    public void setSongDuration(Time songDuration) {
//        this.songDuration = songDuration;
//    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    //////////////////////////////////////////


    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", isFavorite=" + isFavorite +
                ", songPath='" + songPath + '\'' +
                '}';
    }
}
