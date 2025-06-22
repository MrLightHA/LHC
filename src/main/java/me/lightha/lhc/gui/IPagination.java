package me.lightha.lhc.gui;

import org.bukkit.entity.Player;

import java.util.List;

public interface IPagination {

    int getMaxPage(List<?> offers, List<?> free);

    int getCurrentPage(Menu menu);

    boolean hasNextPage(Menu menu, Player player);

    boolean hasPreviousPage(Menu menu);

    void showContent(int page, Menu menu);
}
