package cswhite2000.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import org.bukkit.command.CommandSender;

public class TestCommands {

    @Command(
            aliases = {"testcommand"},
            desc = "I do things",
            max = 0,
            min = 0,
            anyFlags = true,
            usage = "[message...]"
    )
    @CommandPermissions("test.permission.node")
    public static void globalChannelCommand(final CommandContext arguments, final CommandSender sender) throws CommandException {
        sender.sendMessage("I am a message");
    }

}
