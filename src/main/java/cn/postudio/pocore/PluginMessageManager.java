package cn.postudio.pocore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class PluginMessageManager {

    public static @NotNull FileConfiguration getLang(@NotNull Plugin plugin){
        File file = new File(plugin.getDataFolder().getPath(), "language.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public static @NotNull String handleMessageType(Plugin plugin, String key){
        FileConfiguration cfg = getLang(plugin);
        String unhandledMessage = cfg.getString(key);
        String handledMessage;
        assert unhandledMessage != null;
        if (unhandledMessage.startsWith("[") && unhandledMessage.endsWith("]")){
               handledMessage = unhandledMessage.substring(1, unhandledMessage.length() -1);
               handledMessage = handledMessage.replaceAll(",", "\n");
               return handledMessage;
        }else{
            return unhandledMessage;
        }
    }
}
