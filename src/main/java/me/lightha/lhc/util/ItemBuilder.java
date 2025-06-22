package me.lightha.lhc.util;

import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemBuilder {
    private ItemStack item;

    public ItemBuilder(ItemStack item, boolean pure) {
        if (item == null){
            this.item = new ItemStack(Material.AIR);
        }else {
            this.item = pure ? item : item.clone();
        }
    }

    public ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
    }

    public ItemMeta meta(){
        return item.getItemMeta();
    }

    public ItemBuilder meta(ItemMeta meta){
        item.setItemMeta(meta);
        return this;
    }

    public String name(){
        return meta().hasDisplayName() ? meta().getDisplayName() : "";
    }

    public ItemBuilder name(String name){
        ItemMeta meta = meta();

        if (meta == null) return this;

        meta.setDisplayName(name);
        meta(meta);
        return this;
    }

    public List<String> lore(){
        return (meta() == null || meta().getLore() == null) ? new ArrayList<>() : meta().getLore();
    }

    public ItemBuilder lore(List<String> lore){
        ItemMeta meta = meta();

        if (meta == null) return this;

        meta.setLore(lore);
        meta(meta);

        return this;
    }

    public ItemBuilder addLore(String... lore){
        ItemMeta meta = meta();

        if (meta == null) return this;

        List<String> old = lore();
        for (String line : lore) {
            old.add(line);
        }
        meta.setLore(old);
        meta(meta);

        return this;
    }

    public ItemBuilder amount(int amount){
        item.setAmount(amount);
        return this;
    }

    public int amount(){
        return item.getAmount();
    }

    public ItemBuilder glow(boolean glow) {
        ItemMeta meta = meta();

        if (meta == null || !glow) return this;

        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta(meta);

        return this;
    }

    public ItemBuilder setLeatherColor(Color color) {
        if (color == null || !(meta() instanceof LeatherArmorMeta)) return this;

        LeatherArmorMeta meta = (LeatherArmorMeta) meta();
        meta.setColor(color);
        meta(meta);

        return this;
    }

    public ItemBuilder setPotionColor(Color color) {
        if (color == null || !(meta() instanceof PotionMeta)) return this;

        PotionMeta meta = (PotionMeta) meta();
        meta.setColor(color);
        meta(meta);

        return this;
    }

    public ItemBuilder hideFlags() {
        ItemMeta meta = meta();
        if (meta == null) return this;

        meta.addItemFlags(ItemFlag.values());
        meta(meta);

        return this;
    }

    public ItemBuilder flag(ItemFlag... flags) {
        ItemMeta meta = meta();
        if (meta == null) return this;

        meta.addItemFlags(flags);
        meta(meta);

        return this;
    }

    public ItemBuilder flag(Collection<ItemFlag> flags) {
        ItemMeta meta = meta();
        if (meta == null) return this;

        flags.forEach(meta::addItemFlags);
        meta(meta);

        return this;
    }

    public ItemBuilder setOwner(OfflinePlayer player) {
        ItemMeta meta = meta();
        if (!(meta instanceof SkullMeta)) return this;

        SkullMeta skullMeta = (SkullMeta) meta;
        skullMeta.setOwningPlayer(player);
        meta(meta);

        return this;
    }

    public ItemBuilder setHeadBase64(String base64) {

        if (base64 == null) {
            return this;
        }

        UUID hashAsId = new UUID(base64.hashCode(), base64.hashCode());
        item = Bukkit.getUnsafe().modifyItemStack(item,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + base64 + "\"}]}}}"
        );

        return this;
    }

    public ItemBuilder setDamage(Integer damage) {
        ItemMeta meta = meta();
        if (meta instanceof Damageable damageable) {
            damageable.setDamage(damage);
            meta((ItemMeta) damageable);
        }
        return this;
    }

    public ItemStack build(){
        return new ItemStack(item);
    }

    public static ItemBuilder fromConfig(ConfigurationSection section) {
        Material material = Material.valueOf(section.getString("material", "AIR"));
        String name = ChatUtils.parse(section.getString("name", ""));
        List<String> lore = ChatUtils.parse(section.getStringList("lore"));
        Color leatherColor = null;
        if (section.contains("armor-color")) {
            int r = section.getInt("armor-color.r");
            int g = section.getInt("armor-color.g");
            int b = section.getInt("armor-color.b");

            leatherColor = Color.fromRGB(r, g, b);
        }

        Color color = null;
        if (section.contains("color")) {
            int r = section.getInt("color.r");
            int g = section.getInt("color.g");
            int b = section.getInt("color.b");

            color = Color.fromRGB(r, g, b);
        }

        int damage = 0;

        if (section.contains("damage")) {
            damage = section.getInt("damage");
        }

        int amount = 1;
        if (section.contains("amount")) {
            amount = section.getInt("amount");
        }

        String base64 = null;
        if (section.contains("skill-meta") && material.toString().equals("PLAYER_HEAD")) {
            base64 = section.getString("skill-meta");
        }

        List<ItemFlag> flags = section.getStringList("flags").stream().map(ItemFlag::valueOf).toList();

        boolean glow = section.getBoolean("glow", false);

        return new ItemBuilder(material)
                .setHeadBase64(base64)
                .name(name)
                .lore(lore)
                .flag(flags)
                .glow(glow)
                .setLeatherColor(leatherColor)
                .setPotionColor(color)
                .amount(amount)
                .setDamage(damage);
    }
}
