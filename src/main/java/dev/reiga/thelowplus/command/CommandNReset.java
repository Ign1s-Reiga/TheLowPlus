package dev.reiga.thelowplus.command;

import dev.reiga.thelowplus.TheLowPlus;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandNReset implements ICommand {
    @Override
    public String getCommandName() {
        return "nreset";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/nreset";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("nreset", "nr");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        TheLowPlus.instance.count = new ArrayList<>();
        TheLowPlus.instance.titleViewCount = 0;
        sender.addChatMessage(new ChatComponentText("Reset TitleCount."));
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
}
