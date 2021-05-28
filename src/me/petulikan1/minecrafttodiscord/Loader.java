package me.petulikan1.minecrafttodiscord;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Loader extends JavaPlugin {
    public static File ff,ffo;
    public static FileConfiguration fc;
    @Override
    public void onEnable() {
        config();
        Bukkit.getPluginManager().registerEvents(new DiscordMessage(),this);
    }
    private static void config(){
        ffo = new File("plugins/MinecraftToDiscord/");
        if (!ffo.mkdirs()) {
            try {
                ffo.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ff = new File(ffo, "config.yml");
        if (!ff.exists()) {
            try {
                ff.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fc=YamlConfiguration.loadConfiguration(ff);
        fc.addDefault("Webhook","PUT_HERE_WEB_HOOK");
        fc.addDefault("Format","%player% » %message%");
        fc.options().copyDefaults(true).copyHeader(true);
        saveCfg();
    }
    private static void saveCfg(){
        try {
            fc.save(ff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
