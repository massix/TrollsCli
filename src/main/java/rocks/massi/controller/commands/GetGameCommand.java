package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.Game;

public class GetGameCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        int gameId = parseArgsForInt(args);

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
