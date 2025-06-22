package me.lightha.lhc.gui;

import lombok.Data;

import java.util.Map;

@Data
public abstract class MenuParent {
    private final String menuTitle;
    private final Map<Integer, String> inventoryItemSlotsMap;
    private final Map<String, InteractiveItem> itemMap;

    public MenuParent(String menuTitle, Map<Integer, String> inventoryItemSlotsMap, Map<String, InteractiveItem> itemMap) {
        this.menuTitle = menuTitle;
        this.inventoryItemSlotsMap = inventoryItemSlotsMap;
        this.itemMap = itemMap;
    }

    public abstract void open();
}
