package me.lightha.lhc.gui.pagination;

import me.lightha.lhc.gui.Menu;
import org.bukkit.entity.Player;

import java.util.List;

public interface IPagination {

    int getMaxPage(List<?> offers, List<?> free);

    int getCurrentPage(Menu menu);

    boolean hasNextPage(Menu menu, List<?> offers, List<?> free);

    boolean hasPreviousPage(Menu menu);
}
