package me.lightha.lhc.gui.pagination;

import me.lightha.lhc.gui.Menu;
import org.bukkit.entity.Player;

import java.util.List;

public interface IPagination {

    /**
     @param offers обьекты
     @param free количество свободных слотов

     @return Максимальное количество страниц
     **/
    int getMaxPage(List<?> offers, List<?> free);

    /**
     @param menu меню

     @return Активная страница
     **/
    int getCurrentPage(Menu menu);

    /**
     @param menu меню
     @param offers обьекты
     @param free свободные слоты

     @return Есть ли след страница?
     **/
    boolean hasNextPage(Menu menu, List<?> offers, List<?> free);

    /**
     @param menu меню

     @return Есть ли пред страница?
     **/
    boolean hasPreviousPage(Menu menu);
}
