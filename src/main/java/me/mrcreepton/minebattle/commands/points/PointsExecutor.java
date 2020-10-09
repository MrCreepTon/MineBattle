package me.mrcreepton.minebattle.commands.points;

import me.mrcreepton.minebattle.commands.points.arguments.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PointsExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.isOp())
        {
            if (args.length > 0)
            {
                switch (args[0].toLowerCase())
                {
                    case "help":
                        new Help(sender, args);
                        break;
                    case "set":
                        new Set(sender, args);
                        break;
                    case "clear":
                        new Clear(sender, args);
                        break;
                    case "clearall":
                        new ClearAll(sender, args);
                        break;
                    case "remove":
                        new Remove(sender, args);
                        break;
                    case "add":
                        new Add(sender, args);
                        break;

                }
            }
        }

        return true;
    }
}
