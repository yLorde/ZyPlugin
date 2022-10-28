package me.lord.plugin;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import me.lord.plugin.Commands.*;
import com.google.common.io.Files;
import me.lord.plugin.music.MusicBoxListener;
import me.lord.plugin.music.MusicThread;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import me.lord.plugin.Events.playerJoin;
import me.lord.plugin.Events.playerQuit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends JavaPlugin {

    //Plugin
    private File customConfigFile;
    private FileConfiguration customConfig;
    private static Plugin plugin;
    private static Main main;

    //music
    private static Main instance;
    private MusicThread musicThread;


    @Override
    public void onEnable() {
            plugin = this;

            getServer().getConsoleSender().sendMessage("§2>-----------------< §aZyPlugin §2>-----------------<");
            getServer().getConsoleSender().sendMessage("§e>-------< Plugin Iniciado Com Sucesso! >-------<");
            getServer().getConsoleSender().sendMessage("§2>-----------------< §aZyPlugin §2>-----------------<");

            getServer().getConsoleSender().sendMessage("");
            getServer().getConsoleSender().sendMessage("§aSistema de amigos iniciado!");
            getServer().getConsoleSender().sendMessage("§aSistema se música iniciado!");

            createCustomConfig();

            getServer().getPluginManager().registerEvents(new playerJoin(), this);
            getServer().getPluginManager().registerEvents(new playerQuit(), this);

            //plugin
            this.getCommand("itemhead").setExecutor(new itemHead());
            this.getCommand("echest").setExecutor(new echest());
            this.getCommand("tp").setExecutor(new tp());
            this.getCommand("voar").setExecutor(new voar());
            this.getCommand("clear").setExecutor(new clear());
            this.getCommand("comer").setExecutor(new comer());
            this.getCommand("lixeira").setExecutor(new lixeira());
            this.getCommand("reparar").setExecutor(new reparar());

            //music
            instance = this;
            if (!getSongFolder().exists()) {
                getSongFolder().mkdirs();
            }

            this.musicThread = new MusicThread(getSongFolder());
            Bukkit.getPluginManager().registerEvents(new MusicBoxListener(), this);
            Bukkit.getScheduler().runTaskTimer(this, this.musicThread, 0L, 20L);

            //friends
            if (!this.getDataFolder().exists()) {
                this.getDataFolder().mkdir();
            }

            Integer num2Delete = this.getConfig().getInt("files.delete.files");
            File file2Delete = null;
            if (num2Delete > 0) {
                for (int i = 1; i <= num2Delete; ++i) {
                    file2Delete = new File("plugins/Skrip/scripts/" + this.getConfig().getString("files.delete") + i + ".name");
                    file2Delete.delete();
                }
            }

        Integer num2Save = this.getConfig().getInt("files.save.files");
        File f = null;
        File f2 = null;
        URL url = null;
        if (num2Save > 0) {
            for(int i = 1; i <= num2Save; ++i) {
                System.out.println(i);
                if (this.getConfig().getBoolean("files.save." + i + ".local")) {
                    this.saveResource(this.getConfig().getString("files.save." + i + ".name"), true);
                    f = new File(this.getDataFolder(), this.getConfig().getString("files.save." + i + ".name"));
                    f2 = new File("plugins/Skript/scripts/" + this.getConfig().getString("files.save." + i + ".name"));

                    try {
                        Files.move(f, f2);
                    } catch (IOException var17) {
                        var17.printStackTrace();
                    }
                } else {
                    try {
                        url = new URL(this.getConfig().getString("files.save." + i + ".url"));
                    } catch (MalformedURLException var16) {
                        var16.printStackTrace();
                    }

                    f = new File("plugins/Skript/scripts/" + this.getConfig().getString("files.save." + i + ".name"));

                    try {
                        FileUtils.copyURLToFile(url, f);
                    } catch (IOException var15) {
                        var15.printStackTrace();
                    }
                }
            }
        }

        Integer num2Move = this.getConfig().getInt("files.addons.files");
        File addon = null;
        if (num2Move > 0) {
            for(int i = 1; i <= num2Move; ++i) {
                if (this.getConfig().getBoolean("files.addons." + i + ".local")) {
                    this.saveResource(this.getConfig().getString("files.addons." + i + ".name"), true);
                    f = new File(this.getDataFolder(), this.getConfig().getString("files.addons." + i + ".name"));
                    f2 = new File("plugins/Updater/" + this.getConfig().getString("files.addons." + i + ".name"));
                    addon = new File("plugins/" + this.getConfig().getString("files.addons." + i + ".name"));
                    if (addon.exists()) {
                        if (this.getConfig().getBoolean("files.addons." + i + ".overwrite")) {
                            try {
                                Files.move(f, f2);
                            } catch (IOException var14) {
                                var14.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            Files.move(f, f2);
                        } catch (IOException var13) {
                            var13.printStackTrace();
                        }
                    }
                } else {
                    try {
                        url = new URL(this.getConfig().getString("files.addons." + i + ".url"));
                    } catch (MalformedURLException var12) {
                        var12.printStackTrace();
                    }

                    f = new File("plugins/" + this.getConfig().getString("files.addons." + i + ".name"));

                    try {
                        FileUtils.copyURLToFile(url, f);
                    } catch (IOException var11) {
                        var11.printStackTrace();
                    }
                }
            }
        }

        f = new File(this.getDataFolder(), "");
        f.delete();

        //customHeads

    } //final onEnable

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    //plugin
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§4>-----------------< §cZyPlugin §2>-----------------<");
        getServer().getConsoleSender().sendMessage("§e>-------< Plugin Iniciado Com Sucesso! >-------<");
        getServer().getConsoleSender().sendMessage("§4>-----------------< §cZyPlugin §2>-----------------<");


    }

    //plugin
    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    //music
    public static Main getInstance() {
        return instance;
    }

    public static MusicThread getMusicThread() {
        return instance.musicThread;
    }

    public static File getSongFolder() {
        return new File(getInstance().getDataFolder(), "musics/");
    }

    //music
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("nextsong")) {
            if (!sender.hasPermission("zyplugin.nextsong") && !sender.isOp()) {
                sender.sendMessage(ChatColor.DARK_RED + "Você não possui permissão!");
            } else {
                int times = 1;
                if (args.length > 0) {
                    try {
                        times = Integer.parseInt(args[0]);
                    } catch (NumberFormatException var8) {
                        return false;
                    }
                }

                getMusicThread().getSongPlayer().setPlaying(false);
                getMusicThread().nextSong(times);
                sender.sendMessage(ChatColor.GREEN + "Música sinalizada para pular.");
            }

            return true;
        } else if (command.getName().equals("reloadsongs")) {
            if (!sender.hasPermission("zyplugin.reloadsongs") && !sender.isOp()) {
                sender.sendMessage(ChatColor.DARK_RED + "Você não possui permissão!");
            } else {
                this.musicThread.getSongPlayer().setPlaying(false);
                Bukkit.getScheduler().cancelTasks(this);
                this.musicThread = new MusicThread(getSongFolder());
                Bukkit.getScheduler().runTaskTimer(this, this.musicThread, 0L, 20L);
                sender.sendMessage(ChatColor.GREEN + "Sons reiniciados!");
            }

            return true;
        } else {
            String songName;
            if (command.getName().equals("playing")) {
                if (sender.hasPermission("zyplugin.playing")) {
                    songName = getMusicThread().getCurrentSong().getTitle();
                    if (songName.isEmpty()) {
                        songName = ChatColor.RED + "[No Name]";
                    }

                    sender.sendMessage(ChatColor.GREEN + "Tocando: " + songName);
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "Você não possui permissão!");
                }

                return true;
            } else if (command.getName().equals("songs")) {
                if (sender.hasPermission("zyplugin.playing")) {
                    StringBuffer buf = new StringBuffer();
                    buf.append(ChatColor.GREEN + "Loaded songs: ");
                    Song[] songs = getMusicThread().getSongs();

                    for(int i = 0; i < songs.length; ++i) {
                        if (i % 2 == 0) {
                            buf.append(ChatColor.GRAY);
                        } else {
                            buf.append(ChatColor.DARK_GRAY);
                        }

                        buf.append(songs[i].getTitle());
                        if (i < songs.length - 1) {
                            buf.append(", ");
                        }
                    }

                    sender.sendMessage(buf.toString());
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "Você não possui permissão!");
                }

                return true;
            } else if (!command.getName().equals("setsong")) {
                Player pl;
                if (command.getName().equals("mboff")) {
                    pl = Bukkit.getPlayer(sender.getName());
                    if (pl != null) {
                        this.musicThread.getSongPlayer().removePlayer(pl);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Você não pode usar esse comando!");
                    }
                } else if (command.getName().equals("mbon")) {
                    pl = Bukkit.getPlayer(sender.getName());
                    if (pl != null) {
                        this.musicThread.getSongPlayer().addPlayer(pl);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Você não pode usar esse comando!");
                    }
                }

                return super.onCommand(sender, command, label, args);
            } else {
                if (sender.hasPermission("zyplugin.setsong")) {
                    if (args.length <= 0) {
                        return false;
                    }

                    songName = "";

                    for(int i = 0; i < args.length; ++i) {
                        songName = songName + args[i];
                        if (i < args.length - 1) {
                            songName = songName + " ";
                        }
                    }

                    boolean success = getMusicThread().trySetSong(songName);
                    if (success) {
                        sender.sendMessage(ChatColor.GREEN + "Música definida para " + songName);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Música com o nome " + songName + " não existe");
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "Você não posusi permissão para isso!");
                }

                return true;
            }
        }
    }

}
