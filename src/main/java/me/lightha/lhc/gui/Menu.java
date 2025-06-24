package me.lightha.lhc.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

import java.util.function.Consumer;

public interface Menu {
    int getSize();

    String getName();

    MenuItem getItem(int slot);

    void setItem(int slot, MenuItem menuItem);

    void removeOnClose(boolean removeOnClose);

    boolean removeOnClose();

    Inventory getInventory();

    void onClick(Consumer<InventoryClickEvent> onClick);

    Consumer<InventoryClickEvent> onClick();

    void onDrag(Consumer<InventoryDragEvent> onDrag);

    Consumer<InventoryDragEvent> onDrag();

    void onClose(Consumer<InventoryCloseEvent> onClose);

    Consumer<InventoryCloseEvent> onClose();

    void open(Player player);

    void refresh();

    void setData(String key, Object data);

    Object getData(String key);

    <T> T getData(String key, T def);
}
