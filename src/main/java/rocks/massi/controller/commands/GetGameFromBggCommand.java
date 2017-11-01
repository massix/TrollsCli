package rocks.massi.controller.commands;

import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.bgg.Boardgames;

public class GetGameFromBggCommand extends Command {
    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        final int gameId = parseArgsForInt(args);
        Boardgames boardgames = bggXmlApi.getGameForId(gameId);
        Boardgames.Boardgame boardgame = boardgames.getBoardgame().get(0);
        System.out.printf("%-10d %-5d ", boardgame.getId(), boardgame.getYearPublished());
        System.out.print(boardgame.getAlternativeNames().toString() + " ");
        System.out.println(boardgame.getDesigners().toString());
        System.out.println(boardgame.getDescription());
        boardgame.getHonors().forEach(honor -> System.out.printf("Honor: %s\n", honor));
        System.out.println("Thumbnail: " + boardgame.getThumbnail());
    }

    @Override
    public String getName() {
        return "get-game-bgg";
    }

    @Override
    public String help() {
        return "get-game-bgg [id]\tLooks for a game on BGG";
    }
}
