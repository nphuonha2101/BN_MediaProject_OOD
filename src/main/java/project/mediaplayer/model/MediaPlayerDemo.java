package project.mediaplayer.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class MediaPlayerDemo {

    private MediaPlayer mediaPlayer;
    private ArrayList<String> musicList = new ArrayList<>();
    private int currentMusicIndex = 0;
    private boolean isPlay = true;
    private CurrentPlaylist currentPlaylist = new CurrentPlaylist(Playlists.CURRENT_PLAYLIST);

    public boolean getIsPlay() {
        return this.isPlay;
    }
    public void setIsPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }
    public void playListMusic(String folderPath) {
        // add music files from the specified folder to the musicList
        File musicFolder = new File(folderPath);
        File[] musicFiles = musicFolder.listFiles();
        for (File musicFile : musicFiles) {
            if (musicFile.isFile()) {
                musicList.add(musicFile.toURI().toString());
            }
        }

        // create a new MediaPlayer
        mediaPlayer = new MediaPlayer(new Media(musicList.get(currentMusicIndex)));

        // set an OnEndOfMedia event handler to play the next music file
        mediaPlayer.setOnEndOfMedia(this::playNextMusic);

        // play the first music file
        if (isPlay) {
            mediaPlayer.play();
        } else {
            mediaPlayer.pause();
        }

    }

//    public void play() {
//        playListMusic();
//        mediaPlayer.play();
//    }
//    public void pauseMusic() {
//        mediaPlayer.pause();
//    }

    private void playNextMusic() {
        currentMusicIndex++;
        if (currentMusicIndex >= musicList.size()) {
            currentMusicIndex = 0; // repeat from the beginning
        }
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(new Media(musicList.get(currentMusicIndex)));
        mediaPlayer.setOnEndOfMedia(this::playNextMusic);
        mediaPlayer.play();
    }
}