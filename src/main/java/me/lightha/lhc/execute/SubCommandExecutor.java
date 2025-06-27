package me.lightha.lhc.execute;

import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface SubCommandExecutor {
    void execute(CommandSender commandSender, String[] args);
}