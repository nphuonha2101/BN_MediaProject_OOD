package project.mediaplayer.model;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;
import java.util.ArrayList;


public class SongPlayer implements Runnable {

//    private CurrentPlaylist currentPlaylist = new CurrentPlaylist(Playlists.CURRENT_PLAYLIST);

    private ArrayList<String> playingList = new ArrayList<>();
    // return playing index of current playlist
    // if select song from list view => playingIndex = selected item from list view index
    // else (start from first song of playlist => playingIndex = 0;
    private int playingIndex = 0;
//
//    public static void main(String[] args) {
//
//    }

    private String path;
    private boolean playState;
    private int songControl;
    private boolean isComplete;
//    private long songDurationMs;

    private Player player;

    private AdvancedPlayer advancedPlayer;


    // Constant for Playstate
    public final boolean PLAY = true;
    public final boolean STOP = false;

    public final int NEXT_SONG = 1;
    public final int PREVIOUS_SONG = -1;

    public SongPlayer() {
//        this.path = path;
//        this.playState = playState;
    }


//    public void playMusic(String path) {
//        File file = new File(path);
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public void musicPlayer() {
////        while (playingIndex < currentPlaylist.songs.size()) {
//            String songPath = currentPlaylist.songs.get(playingIndex).getSongPath();
//        System.out.println(songPath);
//            File file = new File(songPath);
//
//            advancedPlayer.stop();
//            // catch exception for FileInputStream
//            try {
//                FileInputStream fileInputStream = new FileInputStream(file);
////              BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//
//                // catch exception for Player
//                try {
//                    advancedPlayer = new AdvancedPlayer(fileInputStream);
//
//                    advancedPlayer.play();
//
////                    new Thread(() -> {
////                        try {
//////                            // switch play state
//////                            if (playState) {
//////                                advancedPlayer.play();
//////                            } else {
//////                                advancedPlayer.stop();
//////                            }
////                            advancedPlayer.play();
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                        }
////                    }).start();
//
//                } catch (JavaLayerException e) {
//                    throw new RuntimeException(e);
//                }
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//            playingIndex++;
//            System.out.println(playingIndex);
////        }
//    }

    // GETTER METHODS

    public boolean getPlayState() {
        return playState;
    }

    public int getPlayingIndex() {
        return playingIndex;
    }


    /////////////////////////////

    // SETTER METHODS
    public void setPlayState(boolean playState) {
        this.playState = playState;
    }

    public void setPlayingIndex(int playingIndex) {
        this.playingIndex = playingIndex;
    }

    /////////////////////////////

    public void play(int playState, String songPath) {


        // catch file input stream exception
        try {
         while (playingIndex < playingList.size()){

                File file = new File(songPath);
                System.out.println(playingIndex);
                FileInputStream fileInputStream = new FileInputStream(file);
//                BufferedInputStream bis = new BufferedInputStream(fileInputStream);

                // catch the player exception
                try {
                    player = new Player(fileInputStream);

                    // switch play state -> play
                    switch (playState) {
                        case 1:
                            player.play();
                            break;
                        case 2:
                            player.close();
                            break;
                        case 3:
                            break;
                    }

                    if (player.isComplete()) {
                        playingIndex++;
                    }


                    isComplete = player.isComplete();

                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void stop(String oldSongPath) {
        if (path != null) {
            File file = new File(oldSongPath);

            // catch file input stream exception
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fileInputStream);

                // catch the player exception
                try {
                    player = new Player(bis);
                    player.close();


                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {

    }

//    @Override
//    protected Void call() throws Exception {
//
//
//
//                File file = new File(path);
//
//                // catch file input stream exception
//                try {
//                    FileInputStream fileInputStream = new FileInputStream(file);
//                    BufferedInputStream bis = new BufferedInputStream(fileInputStream);
//
//                    // catch the player exception
//                    try {
//                        Player player = new Player(bis);
//
//                        // switch play state -> play
//                        switch (playState) {
//                            case 1:
//                                player.play();
//                                break;
//                            case 2:
//                                player.close();
//                                break;
//                            case 3:
//                                break;
//                        }
//
//                        isComplete = player.isComplete();
//
//                    } catch (JavaLayerException e) {
//                        throw new RuntimeException(e);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//        return null;
//    }

//    public void play() {
//    }

//    public Clip play() {
//        try {
//            File musicPath = new File(path);
//            if (musicPath.exists()) {
//                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioInputStream);
//                songDurationMs = clip.getMicrosecondLength();
//                switch (playState) {
//                    case 1: {
//                        clip.start();
//                        break;
//                    }
//                    case 2: {
//                        clip.stop();
//                        break;
//                    }
//                    case 3:
//                        break;
//                }
//                return clip;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//    public Clip PlayMusic(String location) {
//        try {
//
//            File musicPath = new File(location);
//                clip.start();
//                return clip;
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        return null;
//    }

    public void addSongToPlayingList() {
        playingList.clear();
        CurrentPlaylist currentPlaylist = new CurrentPlaylist(Playlists.CURRENT_PLAYLIST);
        for (Song song:currentPlaylist.getSongs()) {
            playingList.add(song.getSongPath());
        }
    }

}
