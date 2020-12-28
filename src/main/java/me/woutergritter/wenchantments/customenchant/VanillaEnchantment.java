package me.woutergritter.wenchantments.customenchant;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.enchantments.Enchantment.*;

public class VanillaEnchantment extends CustomEnchantment {
    private final Enchantment vanillaEnchantment;

    public VanillaEnchantment(Enchantment vanillaEnchantment) {
        super(vanillaEnchantment.getName(), getDisplayName(vanillaEnchantment));

        this.vanillaEnchantment = vanillaEnchantment;
    }

    public Enchantment getVanillaEnchantment() {
        return vanillaEnchantment;
    }

    @Override
    public boolean apply(ItemStack item, int level) {
        if(item == null) return false;

        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return false;

        itemMeta.removeEnchant(vanillaEnchantment);
        itemMeta.addEnchant(vanillaEnchantment, level, true);

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(itemMeta);

        super.apply(item, level);
        return true;
    }

    @Override
    public boolean remove(ItemStack item) {
        if(item == null) return false;

        int prevLevel = item.removeEnchantment(vanillaEnchantment);

        super.remove(item);
        return prevLevel > 0;
    }

    private static String getDisplayName(Enchantment enchantment) {
        if (PROTECTION_ENVIRONMENTAL.equals(enchantment)) {
            return "Protection";
        } else if (PROTECTION_FIRE.equals(enchantment)) {
            return "Fire Protection";
        } else if (PROTECTION_FALL.equals(enchantment)) {
            return "Feather Falling";
        } else if (PROTECTION_EXPLOSIONS.equals(enchantment)) {
            return "Blast Protection";
        } else if (PROTECTION_PROJECTILE.equals(enchantment)) {
            return "Projectile Protection";
        } else if (OXYGEN.equals(enchantment)) {
            return "Respiration";
        } else if (WATER_WORKER.equals(enchantment)) {
            return "Aqua Affinity";
        } else if (THORNS.equals(enchantment)) {
            return "Thorns";
        } else if (DEPTH_STRIDER.equals(enchantment)) {
            return "Depth Strider";
        } else if (DAMAGE_ALL.equals(enchantment)) {
            return "Sharpness";
        } else if (DAMAGE_UNDEAD.equals(enchantment)) {
            return "Smite";
        } else if (DAMAGE_ARTHROPODS.equals(enchantment)) {
            return "Bane of Arthropods";
        } else if (KNOCKBACK.equals(enchantment)) {
            return "Knockback";
        } else if (FIRE_ASPECT.equals(enchantment)) {
            return "Fire Aspect";
        } else if (LOOT_BONUS_MOBS.equals(enchantment)) {
            return "Looting";
        } else if (DIG_SPEED.equals(enchantment)) {
            return "Efficiency";
        } else if (SILK_TOUCH.equals(enchantment)) {
            return "Silk Touch";
        } else if (DURABILITY.equals(enchantment)) {
            return "Unbreaking";
        } else if (LOOT_BONUS_BLOCKS.equals(enchantment)) {
            return "Fortune";
        } else if (ARROW_DAMAGE.equals(enchantment)) {
            return "Power";
        } else if (ARROW_KNOCKBACK.equals(enchantment)) {
            return "Punch";
        } else if (ARROW_FIRE.equals(enchantment)) {
            return "Flame";
        } else if (ARROW_INFINITE.equals(enchantment)) {
            return "Infinity";
        } else if (LUCK.equals(enchantment)) {
            return "Luck of the Sea";
        } else if (LURE.equals(enchantment)) {
            return "Lure";
        }

        return "Unknown enchantment";
    }
}
