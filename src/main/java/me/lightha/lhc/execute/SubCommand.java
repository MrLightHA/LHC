package me.lightha.lhc.execute;
import lombok.Getter;
import org.bukkit.entity.Player;
import java.util.List;

@Getter
public final class SubCommand implements ISubCommand {
    private final String name;
    private final String description;
    private final SubCommandExecutor executor;
    private final SubCommandCompleter completer;
    private String permission;

    public SubCommand(String name, String description, SubCommandExecutor executor, SubCommandCompleter completer) {
        this.name = name.toLowerCase();
        this.description = description;
        this.executor = executor;
        this.completer = completer;
    }

    public SubCommand(String name, String description, SubCommandExecutor executor, SubCommandCompleter completer, String permission) {
        this.name = name.toLowerCase();
        this.description = description;
        this.executor = executor;
        this.completer = completer;
        this.permission = permission;
    }

    public void execute(Player player, String... args) {
        executor.execute(player, args);
    }

    public List<String> complete(Player player, String... args) {
        return completer.complete(player, args);
    }
}
