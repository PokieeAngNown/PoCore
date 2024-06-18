package cn.postudio.pocore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static cn.postudio.pocore.PlayerDataManager.PlayerDataFolderPath;

public class InitializePlayerData implements Listener {

    @EventHandler
    public void onPlayerFirstAttendServer(PlayerJoinEvent e){
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        File file = new File(PlayerDataFolderPath, uuid + ".yml");
        if (file.exists()){
            e.setJoinMessage("A player join our server!");
        }else{
            PlayerDataManager.createPlayerData(player);
            try {
                PlayerDataManager.writePlayerData(player, "Name", player.getName());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.setJoinMessage("A new player join our server!");
        }
    }
}
