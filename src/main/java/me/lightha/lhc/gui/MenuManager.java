package me.lightha.lhc.gui;

import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface MenuManager {
    Menu createMenu();

    Menu createMenu(String name);

    Menu createMenu(int size);

    Menu createMenu(InventoryType type);

    Menu createMenu(String name, int size);

    Menu createMenu(String name, InventoryType type);

    MenuItem createItem(ItemStack item);

    MenuItem createItem(ItemStack item, Consumer<InventoryClickEvent> onClick);


    void onDisable();
}
