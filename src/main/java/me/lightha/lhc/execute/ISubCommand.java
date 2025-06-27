package me.lightha.lhc.execute;

import org.bukkit.entity.Player;

import java.util.List;

public interface ISubCommand {

    void execute(Player player, String... args);

    List<String> complete(Player player, String... args);
}
