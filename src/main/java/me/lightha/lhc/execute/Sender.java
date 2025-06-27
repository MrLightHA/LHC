package me.lightha.lhc.execute;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public enum Sender {
    PLAYER,
    CONSOLE,
    ALL;

    public boolean allows(ConsoleCommandSender console, Player player) {
        switch (this) {
            case ALL: return true;
            case PLAYER: return player != null;
            case CONSOLE: return console != null && player == null;
            default: return false;
        }
    }
}
