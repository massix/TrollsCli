package rocks.massi.controller.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.Game;

public class GetGameCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        // Parse CLI
        CommandLine commandLine = new DefaultParser().parse(options, args);
        int gameId;
        if (commandLine.getArgList().isEmpty()) {
            throw new MissingArgumentException("Missing argument [nick]");
        }
        else {
            gameId = Integer.valueOf(commandLine.getArgList().get(0));
        }

        Game game = connector.getGame(gameId);
        if (game != null) {
            System.out.println("ID    : " + game.getId());
            System.out.println("Name  : " + game.getName());
            System.out.println("Rank  : " + game.getRank());
        }
        else {
            System.err.println("Game with id " + gameId + " doesn't exist.");
        }
    }

    @Override
    public String getName() {
        return "get-game";
    }

    @Override
    public String help() {
        return "get-game [id]\tGets game with the given ID";
    }
}
