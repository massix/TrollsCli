package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.bgg.Boardgames;

public class SearchGamesOnBgg extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        String query = parseArgsForString(args);
        Boardgames boardgames = bggXmlApi.search(query);
        boardgames.getBoardgame().forEach(b -> System.out.printf(
                "%-10d %s\n", b.getId(), b.getAlternativeNames().get(0)));
    }

    @Override
    public String getName() {
        return "search-game-bgg";
    }

    @Override
    public String help() {
        return "search-game-bgg [string]\tLooks for a game on BGG.";
    }
}
