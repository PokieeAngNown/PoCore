package cn.postudio.pocore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

import static cn.postudio.pocore.PlayerDataManager.PlayerDataFolderPath;

public final class PoCore extends JavaPlugin {
    private final Player author = Bukkit.getPlayer(UUID.fromString("8b900ed4-36e1-4de4-99b2-ac10409177d6"));

    public static Plugin getPlugin(){
        return Bukkit.getPluginManager().getPlugin("PoCore");
    }

    public @NotNull PluginLogger getPluginLogger(){
        return (PluginLogger) getLogger();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        regListener();
        checkDepend();
        initializePlugin();
        getPluginLogger().info("Plugin has been enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getPluginLogger().info("Plugin has been disabled");
    }

    private void checkDepend(){
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getPluginLogger().info("The PlaceholderAPI has check in!");
        }
    }
    private void regListener(){
        getServer().getPluginManager().registerEvents(new InitializePlayerData(), this);
    }

    private void initializePlugin(){
        if (!new File(getPlugin().getDataFolder(), "config.yml").exists()){
            saveResource("config.yml", false);
        }
        File file = new File(PlayerDataFolderPath);
        if(file.mkdir()){
            getPluginLogger().info("PlayerData folder create successful!");
        }
    }
}
