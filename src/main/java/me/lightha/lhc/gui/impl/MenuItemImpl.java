package me.lightha.lhc.gui.impl;

import me.lightha.lhc.gui.MenuItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MenuItemImpl implements MenuItem {
    private ItemStack item;
    private Consumer<InventoryClickEvent> consumer;
    private Map<String, Object> data = new HashMap<>();

    public MenuItemImpl(ItemStack item){
        this.item = item;
        this.consumer = player -> {};
    }

    public MenuItemImpl(ItemStack item, Consumer<InventoryClickEvent> onClick){
        this.item = item;
        this.consumer = onClick;
    }

    @Override
    public ItemStack item() {
        return item;
    }

    @Override
    public void item(ItemStack item) {
        this.item = item;
    }

    @Override
    public Consumer<InventoryClickEvent> onClick() {
        return consumer;
    }

    @Override
    public void onClick(Consumer<InventoryClickEvent> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void setData(String key, Object data) {
        this.data.put(key, data);
    }

    @Override
    public Object getData(String key) {
        return data.get(key);
    }

    @Override
    public <T> T getData(String key, T type) {
        return ((T) data.get(key));
    }
}
