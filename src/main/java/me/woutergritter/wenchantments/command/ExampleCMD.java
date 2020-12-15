package me.woutergritter.wenchantments.command;

import me.woutergritter.wenchantments.command.internal.CommandContext;
import me.woutergritter.wenchantments.command.internal.WCommand;

public class ExampleCMD extends WCommand {
    public ExampleCMD() {
        super("example");
    }

    @Override
    public void execute(CommandContext ctx) {
        ctx.send("output");
    }
}
