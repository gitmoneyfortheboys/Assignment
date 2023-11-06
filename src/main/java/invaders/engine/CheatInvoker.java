package invaders.engine;

import invaders.commands.CheatCommand;

public class CheatInvoker {
    private CheatCommand cheatCommand;

    public void setCommand(CheatCommand cheatCommand) {
        this.cheatCommand = cheatCommand;
    }

    public void executeCommand() {
        if (cheatCommand != null) {
            cheatCommand.execute();
        }
    }
}