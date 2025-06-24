package me.lightha.lhc.gui.impl;

import me.lightha.lhc.gui.Menu;
import me.lightha.lhc.gui.MenuItem;
import me.lightha.lhc.gui.MenuListener;
import me.lightha.lhc.gui.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class MenuManagerImpl implements MenuManager {
    private Map<Inventory, Menu> menus = new ConcurrentHashMap<>();
    private Sound clickSound;

    public MenuManagerImpl(Plugin plugin){
        new MenuListener(plugin, menus);
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            menus.forEach((inventory, menu) -> {
                if (menu.removeOnClose() && inventory.getViewers().size() == 0) {
                    menus.remove(inventory);
                    inventory.clear();
                }
            });
        }, 600, 600);
    }

    @Override
    public Menu createMenu() {
        Menu menu = new MenuImpl();
        menus.put(menu.getInventory(), menu);
        return menu;
    }

    @Override
    public Menu createMenu(String name) {
        Menu menu = new MenuImpl(name);
        menus.put(menu.getInventory(), menu);
        return menu;
    }

    @Override
    public Menu createMenu(int size) {
        Menu menu = new MenuImpl(size);
        menus.put(menu.getInventory(), menu);
        return menu;
    }

    @Override
    public Menu createMenu(InventoryType type) {
        Menu menu = new MenuImpl(type);
        menus.put(menu.getInventory(), menu);
        return menu;
    }

    @Override
    public Menu createMenu(String name, int size) {
        Menu menu = new MenuImpl(size, name);
        menus.put(menu.getInventory(), menu);
        return menu;
    }

    @Override
    public Menu createMenu(String name, InventoryType type) {
        Menu menu = new MenuImpl(type, name);
        menus.put(menu.getInventory(), menu);
        return menu;
    }

    @Override
    public MenuItem createItem(ItemStack item) {

        return new MenuItemImpl(item);
    }

    @Override
    public MenuItem createItem(ItemStack item, Consumer<InventoryClickEvent> onClick) {

        return new MenuItemImpl(item, onClick);
    }

    @Override
    public void onDisable() {
        menus.keySet().forEach(inv -> new ArrayList<>(inv.getViewers()).forEach(HumanEntity::closeInventory));
    }
}
