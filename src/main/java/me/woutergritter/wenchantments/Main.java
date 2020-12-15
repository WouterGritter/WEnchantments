package me.woutergritter.wenchantments;

import me.woutergritter.wenchantments.customenchant.EnchantmentManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance; // Main is a singleton

    // -- Managers -- //
    private EnchantmentManager enchantmentManager;

    @Override
    public void onEnable() {
        instance = this;

        // Managers
        enchantmentManager = new EnchantmentManager();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public EnchantmentManager getEnchantmentManager() {
        return enchantmentManager;
    }

    public static Main instance() {
        return instance;
    }
}
