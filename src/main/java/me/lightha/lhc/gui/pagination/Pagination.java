package me.lightha.lhc.gui.pagination;

import me.lightha.lhc.gui.Menu;

import java.util.List;

public class Pagination implements IPagination {

    @Override
    public int getMaxPage(List<?> offers, List<?> free) {
        return (int) Math.ceil((double) offers.size() / (double) free.size());
    }

    @Override
    public int getCurrentPage(Menu menu) {
        Object page = menu.getData("page");
        return page instanceof Integer ? (int) page : 1;
    }

    @Override
    public boolean hasNextPage(Menu menu, List<?> offers, List<?> free) {
        int currentPage = getCurrentPage(menu);
        int maxPage = getMaxPage(offers, free);
        return currentPage < maxPage;
    }

    @Override
    public boolean hasPreviousPage(Menu menu) {
        return getCurrentPage(menu) > 1;
    }
}
