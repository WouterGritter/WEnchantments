package me.woutergritter.wenchantments.customenchant;

import me.woutergritter.wenchantments.WEnchantments;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomEnchantment implements WEnchantment {
    private static final String LORE_PREFIX = ChatColor.RED.toString() + ChatColor.WHITE.toString() + ChatColor.BLUE.toString() + ChatColor.GRAY.toString();
    private static final Enchantment DUMMY_ENCHANT = Enchantment.PROTECTION_ENVIRONMENTAL;

    private final String name;
    private final String displayName;
    private final String enchantmentPrefix;

    public CustomEnchantment(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;

        this.enchantmentPrefix = LORE_PREFIX + displayName + " ";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean apply(ItemStack item, int level) {
        if(item == null) return false;

        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return false;

        // Remove if the item already had the enchantment!
        remove(item);

        List<String> lore = itemMeta.hasLore() ? new ArrayList<>(itemMeta.getLore()) : new ArrayList<>();
        lore.add(enchantmentPrefix + level);

        itemMeta.setLore(lore);

        if(!itemMeta.hasEnchants()) {
            itemMeta.addEnchant(DUMMY_ENCHANT, 0, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        item.setItemMeta(itemMeta);
        return true;
    }

    @Override
    public boolean remove(ItemStack item) {
        if(item == null) return false;

        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null || !itemMeta.hasLore()) return false;

        List<String> lore = itemMeta.hasLore() ? new ArrayList<>(itemMeta.getLore()) : new ArrayList<>();
        boolean removed = false;
        for(int i = 0; i < lore.size(); i++) {
            if(lore.get(i).startsWith(enchantmentPrefix)) {
                lore.remove(i--);
                removed = true;
            }
        }

        if(removed) {
            itemMeta.setLore(lore);

            // TODO: Remove the DUMMY_ENCHANT if needed..

            item.setItemMeta(itemMeta);
        }

        return removed;
    }

    @Override
    public int getLevel(ItemStack item) {
        if(item == null) return 0;

        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null || !itemMeta.hasLore()) return 0;

        String enchantmentLore = null;
        for(String lore : itemMeta.getLore()) {
            if(lore.startsWith(enchantmentPrefix)) {
                enchantmentLore = lore;
                break;
            }
        }

        if(enchantmentLore == null || enchantmentLore.length() <= enchantmentPrefix.length()) return 0;

        String levelStr = enchantmentLore.substring(enchantmentPrefix.length());
        try{
            return Integer.parseInt(levelStr);
        }catch(NumberFormatException ignored) {}

        return 0;
    }

    public void register() {
        WEnchantments.getEnchantmentManager().register(this);
    }

    public static boolean isCustomEnchantLore(String lore) {
        return lore.startsWith(LORE_PREFIX);
    }
}
