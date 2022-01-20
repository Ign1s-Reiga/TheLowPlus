package dev.reiga.thelowplus.command;

import dev.reiga.thelowplus.TheLowPlus;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.List;

public class CommandNCount implements ICommand {
    @Override
    public String getCommandName() {
        return "ncount";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/ncount (<y/n>)";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("ncount", "nc");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0)
            TheLowPlus.instance.isCount = !TheLowPlus.instance.isCount;
        else
            setMode(sender, args[0]);
        sender.addChatMessage(new ChatComponentText("countMode: " + TheLowPlus.instance.isCount));
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    private void setMode(ICommandSender sender, String mode) {
        if ((mode.equals("y") && TheLowPlus.instance.isCount) || (mode.equals("n") && !TheLowPlus.instance.isCount))
            sender.addChatMessage(new ChatComponentText("Now Mode is already true."));
        else
            TheLowPlus.instance.isCount = mode.equals("y");
    }
}
