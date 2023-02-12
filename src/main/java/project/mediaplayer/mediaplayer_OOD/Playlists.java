package project.mediaplayer.mediaplayer_OOD;

import java.util.*;

public abstract class Playlists {
    private String playlistName;
    private boolean isFavoritePlaylist;
    private ArrayList<Song> songs;
    private String dateCreation;
    private Time playlistDuration;

    public static final String FAVORITE_PLAYLIST = "FAVORITE_PLAYLIST";


    public Playlists(String playlistName, boolean isFavoritePlaylist, ArrayList<Song> songs, String dateCreation, Time playlistDuration) {
        this.playlistName = playlistName;
        this.isFavoritePlaylist = isFavoritePlaylist;
        this.songs = songs;
        this.dateCreation = dateCreation;
        this.playlistDuration = playlistDuration;
    }


    // generate shuffle number without repeat
//    public static ArrayList<Integer> shuffleNumber() {
//        ArrayList<Integer> result = new ArrayList<Integer>();
//        Random rd = new Random();
//        while (result.size() <= 10) {
//            int number = rd.nextInt(0, 10);
//            if (!result.contains(number)) {
//                result.add(number);
//                System.out.print(number);
//            }
//            else {
////                System.out.println(result);
//            }
//        }
//        return result;
//    }
    public static int number(int totalNumber) {
//        Random rd = new Random();
//        int number = rd.nextInt(0, 10);
//        if (!result.contains(number)) {
//            result.add(number);
//        }
//        return result;
        Random rd = new Random();
        int number = rd.nextInt(0, totalNumber);
        return number;
    }
    public static ArrayList<Integer> shuffleNumber(ArrayList<Integer> result, int count, int totalNumber) {
        if (count == 0) {
            return result;
        } else {
            int number = number(totalNumber);
            if (!result.contains(number)) {
                result.add(number);
                return  shuffleNumber(result, count - 1, totalNumber);
            } else {
                return shuffleNumber(result, count, totalNumber);
            }
        }
    }
//    public static ArrayList<Integer> shuffleNumber() {
//        ArrayList<Integer> result = new ArrayList<Integer>();
//        Random rd = new Random();
//        while (result.size() <= 10) {
//            int number = rd.nextInt(0, 10);
//            if (!result.contains(number)) {
//                result.add(number);
//                System.out.print(number);
//            }
//            else {
////                System.out.println(result);
//            }
//        }
//        return result;
//    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
//        System.out.println(arrayList);
        System.out.println(shuffleNumber(arrayList, 10, 10));
    }

}
