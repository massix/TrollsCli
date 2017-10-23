package rocks.massi.controller.router;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.commands.Command;
import rocks.massi.controller.commands.HelpRequestedException;
import rocks.massi.controller.commands.MissingArgumentException;

import java.util.LinkedList;

public class CommandRouter extends LinkedList<Command> {

    public void route(String command, String[] args) {
        final boolean[] found = {false};
        forEach(cmd -> {
            if (cmd.getName().equals(command)) {
                found[0] = true;
                try {
                    cmd.run(args);
                }
                catch (MissingArgumentException | ParseException ex) {
                    System.err.println(ex.getMessage());
                } catch (HelpRequestedException e) {
                    help();
                }
            }
        });

        if (!found[0]) throw new CommandNotFoundException(
                String.format("Can't find command '%s', sorry.", command));
    }

    public void help() {
        forEach(cmd -> System.out.println(cmd.help()));
    }
}
