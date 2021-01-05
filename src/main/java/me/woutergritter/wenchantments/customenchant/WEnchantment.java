package me.woutergritter.wenchantments.customenchant;

import me.woutergritter.wenchantments.WEnchantments;
import org.bukkit.inventory.ItemStack;

public interface WEnchantment {
    /**
     * @return The internal SCREAMING_CAMEL_CASE name of this enchantment
     */
    String getName();

    /**
     * @return The visible display name of this enchantment
     */
    String getDisplayName();

    /**
     * @param item The item to apply the enchantment to
     * @param level The level to apply
     * @return Whether the operation was successful or not
     */
    boolean apply(ItemStack item, int level);

    /**
     * @param item The item to remove this enchantment from
     * @return Whether the operation was successful or not
     */
    boolean remove(ItemStack item);

    /**
     * @param item The item to get the level of
     * @return The level of the enchantment on the item, or 0 if the item does not have this enchantment
     */
    int getLevel(ItemStack item);

    /**
     * @param item The item to check the presence of this enchantment of
     * @return Whether or not the item has this enchantment
     */
    default boolean has(ItemStack item) {
        return getLevel(item) > 0;
    }

    /**
     * Registers this enchantment.
     * Does exactly the same as: <code>WEnchantments.getEnchantmentManager().register(this)</code>
     */
    default void register() {
        WEnchantments.getEnchantmentManager().register(this);
    }
}
