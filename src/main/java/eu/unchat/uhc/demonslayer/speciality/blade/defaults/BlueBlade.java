package eu.unchat.uhc.demonslayer.speciality.blade.defaults;

import eu.unchat.uhc.demonslayer.speciality.blade.IBlade;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.function.Consumer;

public final class BlueBlade implements IBlade {
    @Override
    public ItemStack getDisplay() {
        return new ItemBuilder(Material.INK_SACK)
                .name("&9&lLame bleue")
                .lore("&rVous octroie un livre &9&lDepth Strider 1&r.")
                .asItemStack();
    }

    @Override
    public Consumer<IProfile> apply() {
        return (profile -> {
            profile.getPlayer().ifPresent(player -> {
                ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                meta.addStoredEnchant(Enchantment.DEPTH_STRIDER, 1, false);
                item.setItemMeta(meta);
                player.getInventory().addItem(item);
            });
        });
    }
}
