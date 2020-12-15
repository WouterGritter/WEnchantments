package me.woutergritter.wenchantments;

import me.woutergritter.wenchantments.customenchant.EnchantmentManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WEnchantments extends JavaPlugin {
    private static WEnchantments instance; // Main is a singleton

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

    public static EnchantmentManager getEnchantmentManager() {
        return instance.enchantmentManager;
    }

    public static WEnchantments instance() {
        return instance;
    }
}
