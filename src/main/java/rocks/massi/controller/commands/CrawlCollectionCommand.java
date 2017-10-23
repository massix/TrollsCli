package rocks.massi.controller.commands;

import feign.Response;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

public class CrawlCollectionCommand extends Command {
    private static final int SC_ACCEPTED = 202;

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

        Response response = connector.crawlCollection(nick);
        if (response.status() == SC_ACCEPTED) {
            System.out.println("Started (or continued) crawling the collection of user " + nick);
            System.out.println("Queue ID: " + response.headers().get("Location"));
        }
        else {
            System.err.println("User " + nick + " not found on server.");
        }
    }

    @Override
    public String getName() {
        return "crawl-collection";
    }

    @Override
    public String help() {
        return "crawl-collection [nick]\tStarts crawling the collection for the user.";
    }
}
