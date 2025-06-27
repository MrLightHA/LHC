package me.lightha.lhc.execute;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.function.BiConsumer;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Executor implements TabExecutor, IExecutor {

    private final String label;
    private final Sender allowedSender;
    private final Map<String, SubCommand> subs = new LinkedHashMap<>();
    private BiConsumer<CommandSender, String[]> noArgsHandler;
    private BiConsumer<CommandSender, String[]> noPermissionHandler;
    private BiConsumer<CommandSender, String[]> notFiendSubCommand;

    public static Executor create(String label, Sender allowedSender) {
        return new Executor(label.toLowerCase(), allowedSender);
    }

    @Override
    public Executor onEmptyArgs(BiConsumer<CommandSender, String[]> handler) {
        this.noArgsHandler = handler;
        return this;
    }

    @Override
    public Executor onNoPermission(BiConsumer<CommandSender, String[]> handler) {
        this.noPermissionHandler = handler;
        return this;
    }

    @Override
    public Executor onUnknownSubcommand(BiConsumer<CommandSender, String[]> handler) {
        this.notFiendSubCommand = handler;
        return this;
    }

    @Override
    public Executor register(String name, String description, SubCommandExecutor executor, SubCommandCompleter completer) {
        subs.put(name.toLowerCase(), new SubCommand(name, description, executor, completer));
        return this;
    }

    @Override
    public Executor register(String name, String description, String permission, SubCommandExecutor executor, SubCommandCompleter completer) {
        subs.put(name.toLowerCase(), new SubCommand(name, description, executor, completer, permission));
        return this;
    }

    @Override
    public void bind(JavaPlugin plugin) {
        PluginCommand cmd = plugin.getCommand(label);
        if (cmd == null) {
            plugin.getLogger().severe(String.format("Команда «%s» не указана в plugin.yml", label));
            return;
        }
        cmd.setExecutor(this);
        cmd.setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConsoleCommandSender console = sender instanceof ConsoleCommandSender cc ? cc : null;
        Player player  = sender instanceof Player p ? p  : null;

        if (!allowedSender.allows(console, player)) {
            if (this.noPermissionHandler != null) this.noPermissionHandler.accept(sender, args);
            return true;
        }
        if (args.length == 0) {
            if (this.noArgsHandler != null) this.noArgsHandler.accept(sender, args);
            return true;
        }

        SubCommand sub = subs.get(args[0].toLowerCase());
        if (sub == null) {
            if (this.notFiendSubCommand != null) this.notFiendSubCommand.accept(sender, args);
            return true;
        }

        try {
            if (sub.getPermission() != null){
                if (sender.hasPermission(sub.getPermission())) {
                    sub.execute(player, Arrays.copyOfRange(args, 1, args.length));
                } else {
                    if (this.noPermissionHandler != null) this.noPermissionHandler.accept(sender, args);
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Player player = sender instanceof Player p ? p : null;

        if (args.length == 1) {
            String prefix = args[0].toLowerCase();
            return subs.keySet().stream()
                    .filter(k -> k.startsWith(prefix))
                    .sorted()
                    .toList();
        }

        SubCommand sub = subs.get(args[0].toLowerCase());
        if (sub != null) {
            try {
                return sub.complete(player, Arrays.copyOfRange(args, 1, args.length));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return Collections.emptyList();
    }
}
