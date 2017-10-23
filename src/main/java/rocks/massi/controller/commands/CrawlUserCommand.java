package rocks.massi.controller.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.data.User;

public class CrawlUserCommand extends Command {

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        // Parse CLI
        CommandLine commandLine = new DefaultParser().parse(options, args);
        String nick;
        if (commandLine.getArgList().isEmpty()) {
            throw new MissingArgumentException("Missing argument [nick]");
        }
        else {
            nick = String.join(" ", commandLine.getArgList());
        }

        User user = connector.crawlUser(nick);
        if (user != null) {
            System.out.println("Crawled user        : " + user.getBggNick());
            System.out.println("List of owned games : {" + user.getGames() + "}");
            System.out.println("List of wanted games: {" + user.getWants() + "}");
        }
        else {
            System.err.println("User " + nick + " not found on Database.");
        }
    }

    @Override
    public String getName() {
        return "crawl-user";
    }

    @Override
    public String help() {
        return "crawl-user [nick]\tCrawls a single user";
    }
}
