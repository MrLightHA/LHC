package me.lightha.lhc.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface MenuItem {
    ItemStack item();

    void item(ItemStack itemStack);

    Consumer<InventoryClickEvent> onClick();

    void onClick(Consumer<InventoryClickEvent> consumer);

    void setData(String key, Object data);

    Object getData(String key);

    <T> T getData(String key, T def);
}
