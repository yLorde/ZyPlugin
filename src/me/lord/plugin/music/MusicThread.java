package me.lord.plugin.music;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import me.lord.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MusicThread implements Runnable{
    private SongPlayer songPlayer;
    private Song[] loadedSongs = new Song[1];
    private int currentSong = 0;

    public MusicThread(File songFolder) {
        this.loadSongs(songFolder);
    }

    public void run() {
        if (!this.songPlayer.isPlaying()) {
            this.nextSong();
            Iterator var2 = Bukkit.getOnlinePlayers().iterator();

            while(var2.hasNext()) {
                Player player = (Player)var2.next();
                this.songPlayer.addPlayer(player);
            }

            this.songPlayer.setPlaying(true);
        }

    }

    public Song getCurrentSong() {
        return this.getSongPlayer().getSong();
    }

    public SongPlayer getSongPlayer() {
        return this.songPlayer;
    }

    private void loadSongs(File songFolder) {
        File[] files = songFolder.listFiles();
        List<Song> songs = new ArrayList<>();
        Main.getInstance().getLogger().info("Loading songs from " + songFolder.getPath());
        File[] var7 = files;
        int var6 = files.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            File file = var7[var5];
            Song song = NBSDecoder.parse(file);
            songs.add(song);
        }

        Main.getInstance().getLogger().info("Loaded " + songs.size() + " songs!");
        this.loadedSongs = (Song[])songs.toArray(this.loadedSongs);
        this.songPlayer = new RadioSongPlayer(this.loadedSongs[0]);
        this.songPlayer.setPlaying(true);
    }

    public void nextSong(int times) {
        this.currentSong += times;
        if (this.currentSong >= this.loadedSongs.length) {
            this.currentSong = 0;
        }

        this.songPlayer = new RadioSongPlayer(this.loadedSongs[this.currentSong]);
    }

    private void nextSong() {
        this.nextSong(1);
    }

    public boolean trySetSong(String songName) {
        Song[] var5;
        int var4 = (var5 = this.loadedSongs).length;

        Song song;
        int var3;
        for(var3 = 0; var3 < var4; ++var3) {
            song = var5[var3];
            if (song.getTitle().equalsIgnoreCase(songName)) {
                this.songPlayer.setPlaying(false);
                this.songPlayer = new RadioSongPlayer(song);
                this.songPlayer.setPlaying(true);
                return true;
            }
        }

        if (!songName.endsWith(".nbs")) {
            songName = songName + ".nbs";
        }

        var4 = (var5 = this.loadedSongs).length;

        for(var3 = 0; var3 < var4; ++var3) {
            song = var5[var3];
            if (song.getPath().getName().equalsIgnoreCase(songName)) {
                this.songPlayer.setPlaying(false);
                this.songPlayer = new RadioSongPlayer(song);
                this.songPlayer.setPlaying(true);
                return true;
            }
        }

        return false;
    }

    public Song[] getSongs() {
        return this.loadedSongs;
    }
}
