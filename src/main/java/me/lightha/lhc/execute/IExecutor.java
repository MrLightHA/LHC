package me.lightha.lhc.execute;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.BiConsumer;

public interface IExecutor {
    Executor onEmptyArgs(BiConsumer<CommandSender, String[]> handler);

    Executor onNoPermission(BiConsumer<CommandSender, String[]> handler);

    Executor onUnknownSubcommand(BiConsumer<CommandSender, String[]> handler);

    Executor register(String name, String description, SubCommandExecutor executor, SubCommandCompleter completer);

    Executor register(String name, String description, String permission, SubCommandExecutor executor, SubCommandCompleter completer);

    void bind(JavaPlugin plugin);

}
