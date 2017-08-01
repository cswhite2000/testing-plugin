package cswhite2000;

import com.sk89q.bukkit.util.BukkitCommandsManager;
import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.CommandsManager;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.WrappedCommandException;
import cswhite2000.commands.TestCommands;
import cswhite2000.listeners.TestListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Iterator;

public class TestPlugin extends JavaPlugin {

    /** The commands manager. */
    private CommandsManager commands;
    /** The commands' registration. */
    private CommandsManagerRegistration commandsRegistration;

    @SuppressWarnings("unchecked")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        try {
            this.commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
        } catch (MissingNestedCommandException e) {
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (CommandUsageException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            sender.sendMessage(ChatColor.RED + "Usage: " + e.getUsage());
        } catch (WrappedCommandException e) {
            sender.sendMessage(ChatColor.RED + "An unknown error has occurred. Please notify an administrator.");
            e.printStackTrace();
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }

        return true;
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        this.commandsRegistration = null;
        this.commands = null;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new TestListener(), this);

        this.commands = new BukkitCommandsManager();
        this.commandsRegistration = new CommandsManagerRegistration(this, this.commands);
        this.commandsRegistration.register(TestCommands.class);
    }
}
