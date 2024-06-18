package cn.postudio.pocore;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class PluginMessageManager {

    public static @NotNull FileConfiguration getLang(@NotNull Plugin plugin){
        File file = new File(plugin.getDataFolder().getPath(), "language.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void sendMessageToPlayer(Plugin plugin, String key, @NotNull Player player){
        String message = Objects.requireNonNull(getLang(plugin).getString(key));
        message = PlaceholderAPI.setPlaceholders(player, message);
        player.sendMessage(message);
    }
}
