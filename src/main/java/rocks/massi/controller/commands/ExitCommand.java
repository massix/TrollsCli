package rocks.massi.controller.commands;

public class ExitCommand extends Command {
    @Override
    public void run(String[] args) {

    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String help() {
        return "exit\tExits the CLI.";
    }
}
