package project.mediaplayer.model;

/**
 * <p>The purposes of {@link CurrentPlaylist}:
 *  <ul>
 *      <li> {@link CurrentPlaylist} is a playlist which Media Player read songs from it and play. </li>
 *      <li> The media player only read songs from this playlist. </li>
 *      <li> {@link MainPlaylist} is used for storage songs after add songs file from a directory (reserve songs). </li>
 *      <li> When switch to Favorite button on UI, {@link CurrentPlaylist} will clear all songs,
 *          then adds all songs from {@link FavoritePlaylist}.</li>
 *  </ul>
 * </p>
 */
public class CurrentPlaylist extends Playlists {

    public CurrentPlaylist(int playlistType) {
        super(playlistType);
    }

//    public void playInCurrentPlaylist(int playingState) {
//        for (Song song: super.songs
//             ) {
//            SongPlayer songPlayer = new SongPlayer(song.getSongPath(), playingState);
//            songPlayer.play();
//        }
//    }


    /**
     * Use songID was got from selected listView item
     * and return index of song has this songID in CurrentPlaylist
     */
    public int getSongIndexWithSongID(int songID) {
        int result = 0;
        for (int i = 0; i < this.songs.size(); i++) {
            Song song = this.songs.get(i);

            if (song.getSongID() == songID) {
                result = i;
                break;
            }
        }
        return result;
    }

}
