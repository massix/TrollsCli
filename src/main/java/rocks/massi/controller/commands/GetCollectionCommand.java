package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.Game;

import java.util.List;

public class GetCollectionCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        String user = parseArgsForString(args);
        List<Game> games = connector.getCollection(user);
        System.out.println(String.format("%-10s %-60s %s", "id", "name", "rank"));
        System.out.println("+----------------------------------------------------------------------------+");
        games.forEach(game -> System.out.println(String.format("%-10d %-60s %d",
                game.getId(),
                game.getName().substring(0, Math.min(58, game.getName().length())),
                game.getRank())));
    }

    @Override
    public String getName() {
        return "get-collection";
    }

    @Override
    public String help() {
        return "get-collection [user]\tGets the collection for the User.";
    }
}
