package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.trolls.Game;

import java.text.Normalizer;
import java.util.List;

public class SearchGameCommand extends Command {
    private String normalize(final String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFC).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        final String gameName = normalize(parseArgsForString(args));
        List<Game> games = connector.getAllGames();

        System.out.println(String.format("%-10s %-60s %s", "id", "name", "rank"));
        System.out.println("-----------------------------------------------------------------------------");
        games.forEach(game -> {
            String normalizedGameName = normalize(game.getName());
            if (normalizedGameName.contains(gameName)) {
                System.out.println(String.format("%-10d %-60s %d",
                        game.getId(),
                        game.getName().substring(0, Math.min(58, game.getName().length())),
                        game.getRank()));
            }
        });
    }

    @Override
    public String getName() {
        return "search-game";
    }

    @Override
    public String help() {
        return "search-game [game]\tLooks for a game with a given name.";
    }
}
