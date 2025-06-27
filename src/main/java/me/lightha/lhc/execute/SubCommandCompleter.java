package me.lightha.lhc.execute;

import org.bukkit.command.CommandSender;

import java.util.List;

@FunctionalInterface
public interface SubCommandCompleter {
    List<String> complete(CommandSender commandSender, String[] args);
}
