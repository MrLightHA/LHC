package me.lightha.lhc.api;

import me.lightha.lhc.LHC;
import me.lightha.lhc.gui.MenuManager;

public interface IMenuManager {
    MenuManager menuManager = LHC.instance.getMenuManager();
}
