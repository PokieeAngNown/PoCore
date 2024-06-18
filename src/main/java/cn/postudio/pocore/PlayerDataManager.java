package cn.postudio.pocore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataManager {

    public final static String PlayerDataFolderPath = PoCore.getPlugin().getDataFolder().getPath() + "/PlayerData/";

    private static File file;
    private static FileConfiguration cfg;
    private static UUID uuid;


    public static synchronized boolean createPlayerData(@NotNull Player player){
        uuid = player.getUniqueId();
        file = new File(PlayerDataFolderPath, uuid + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);

        try{
            return file.createNewFile();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized FileConfiguration getPlayerData(@NotNull Player player){
        uuid = player.getUniqueId();
        file = new File(PlayerDataFolderPath, uuid + ".yml");
        if (file.exists()){
            cfg = YamlConfiguration.loadConfiguration(file);
        }else{
            cfg = null;
        }
        return cfg;
    }

    public static synchronized void writePlayerData(@NotNull Player player, String path, Object value) throws IOException {
        uuid = player.getUniqueId();
        file = new File(PlayerDataFolderPath, uuid + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(path, value);
        cfg.save(file);
    }
}
