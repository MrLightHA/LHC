package me.lightha.lhc.gui.impl;

import me.lightha.lhc.LHC;
import me.lightha.lhc.gui.Menu;
import me.lightha.lhc.gui.MenuItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class MenuImpl implements Menu {
    private Inventory inventory;
    private Map<Integer, MenuItem> items = new HashMap<>();
    private boolean removeOnClose;
    private Consumer<InventoryClickEvent> onClick = e -> {};
    private Consumer<InventoryDragEvent> onDrag = e -> {};
    private Consumer<InventoryCloseEvent> onClose = e -> {};
    private Map<String, Object> data = new HashMap<>();

    private String invName = null;

    public MenuImpl(){
        this.inventory = Bukkit.createInventory(null, 9);
    }

    public MenuImpl(int size){
        this.inventory = Bukkit.createInventory(null, size);
    }

    public MenuImpl(String name){
        this.invName = name;
        this.inventory = Bukkit.createInventory(null, 9, name);
    }

    public MenuImpl(InventoryType type){
        this.inventory = Bukkit.createInventory(null, type);

    }

    public MenuImpl(int size, String name){
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    public MenuImpl(InventoryType type, String name){
        this.inventory = Bukkit.createInventory(null, type, name);
    }

    @Override
    public int getSize() {
        return inventory.getSize();
    }

    @Override
    public String getName() {
        if (invName != null) {
            return invName;
        }
        return inventory.getType().getDefaultTitle();
    }

    @Override
    public MenuItem getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public void setItem(int slot, MenuItem menuItem) {
        if (menuItem == null){
            inventory.setItem(slot, null);
            items.remove(slot);
            return;
        }

        inventory.setItem(slot, menuItem.item());
        items.put(slot, menuItem);
    }

    @Override
    public void removeOnClose(boolean removeOnClose) {
        this.removeOnClose = removeOnClose;
    }

    @Override
    public boolean removeOnClose() {
        return removeOnClose;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void onClick(Consumer<InventoryClickEvent> onClick) {
        this.onClick = onClick;
    }

    @Override
    public Consumer<InventoryClickEvent> onClick() {
        return onClick;
    }

    @Override
    public void onDrag(Consumer<InventoryDragEvent> onDrag) {
        this.onDrag = onDrag;
    }

    @Override
    public Consumer<InventoryDragEvent> onDrag() {
        return onDrag;
    }

    @Override
    public void onClose(Consumer<InventoryCloseEvent> onClose) {
        this.onClose = onClose;
    }

    @Override
    public Consumer<InventoryCloseEvent> onClose() {
        return onClose;
    }

    @Override
    public void open(Player player) {
        Bukkit.getScheduler().runTaskLater(LHC.instance, () -> {
            player.openInventory(inventory);
        }, 1L);
    }

    @Override
    public void refresh() {
        inventory.clear();
        items.forEach((slot, item) -> inventory.setItem(slot, item.item()));
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
    public <T> T getData(String key, T def) {
        return (T) Optional.ofNullable(data.get(key))
                .orElse(def);
    }
}
