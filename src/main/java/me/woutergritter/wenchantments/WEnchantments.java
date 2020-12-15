package me.woutergritter.wenchantments;

import me.woutergritter.wenchantments.customenchant.EnchantmentManager;
import me.woutergritter.wenchantments.customenchant.potionenchant.PotionEnchantManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WEnchantments extends JavaPlugin {
    private static WEnchantments instance; // Main is a singleton

    // -- Managers -- //
    private EnchantmentManager enchantmentManager;
    private PotionEnchantManager potionEnchantManager;

    @Override
    public void onEnable() {
        instance = this;

        // Managers
        enchantmentManager = new EnchantmentManager();
        potionEnchantManager = new PotionEnchantManager();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static EnchantmentManager getEnchantmentManager() {
        return instance.enchantmentManager;
    }

    public static PotionEnchantManager getPotionEnchantManager() {
        return instance.potionEnchantManager;
    }

    public static WEnchantments instance() {
        return instance;
    }
}
