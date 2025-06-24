package me.lightha.lhc.gui;

import lombok.Data;
import me.lightha.lhc.api.IMenuManager;
import me.lightha.lhc.api.annotations.Paginated;
import me.lightha.lhc.gui.impl.InteractiveItem;
import me.lightha.lhc.gui.pagination.Pagination;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.function.Consumer;

@Data
public abstract class MenuParent implements IMenuManager {
    private final String menuTitle;
    private final Map<Integer, String> inventoryItemSlotsMap;
    private final Map<String, InteractiveItem> itemMap;
    private Pagination pagination;
    private Menu menu;

    public MenuParent(String menuTitle, Map<Integer, String> inventoryItemSlotsMap, Map<String, InteractiveItem> itemMap) {
        this.menuTitle = menuTitle;
        this.inventoryItemSlotsMap = inventoryItemSlotsMap;
        this.itemMap = itemMap;

        Class<?> clazz = this.getClass();
        if (clazz.isAnnotationPresent(Paginated.class)) {
            this.pagination = new Pagination();
        }
    }

    public void open(Player player) {
        if (this.menu != null) this.menu.open(player);
    }

    public void createMenu(Consumer<Menu> consumer) {
        Menu menu = menuManager.createMenu(getMenuTitle());
        consumer.accept(menu);

        this.menu = menu;
    }


    public abstract void create(Object... objects);

    public abstract void fill(Object... objects);
}
