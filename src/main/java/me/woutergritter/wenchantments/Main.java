package me.woutergritter.wenchantments;

import me.woutergritter.wenchantments.command.ExampleCMD;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance; // Main is a singleton

    // -- Managers -- //

    @Override
    public void onEnable() {
        instance = this;

        // Managers

        // Commands
        new ExampleCMD().register();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Main instance() {
        return instance;
    }
}
