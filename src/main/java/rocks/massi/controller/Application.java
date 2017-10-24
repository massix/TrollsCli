package rocks.massi.controller;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.commands.*;
import rocks.massi.controller.configuration.ServerConfiguration;
import rocks.massi.controller.router.CommandNotFoundException;
import rocks.massi.controller.router.CommandRouter;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    private static Options options;
    private static CommandRouter router;

    public static void main(String[] args) throws ParseException {
        options = new Options();
        options.addRequiredOption("s", "server", true, "Set server");
        options.addOption("p", "proxy", true, "Set proxy");

        // Parse CLI
        CommandLine cli = new DefaultParser().parse(options, args);
        ServerConfiguration.getInstance().setServerAddress(cli.getOptionValue("s"));

        if (cli.hasOption("p"))
            ServerConfiguration.getInstance().setProxy(cli.getOptionValue("p"));

        // Register commands in Router
        router = new CommandRouter();
        router.add(new GetUsersCommand());
        router.add(new GetUserCommand());
        router.add(new AddUserCommand());
        router.add(new RemoveUserCommand());
        router.add(new GetGameCommand());
        router.add(new SearchGameCommand());
        router.add(new CrawlUserCommand());
        router.add(new CrawlCollectionCommand());
        router.add(new GetCollectionCommand());
        router.add(new GetQueueCommand());
        router.add(new GetQueuesCommand());
        router.add(new PurgeCompletedQueuesCommand());
        router.add(new HelpCommand());
        router.add(new ExitCommand());

        System.out.println("Available commands");
        router.help();

        String command = "";
        Scanner in = new Scanner(System.in);

        while (! command.equals("exit")) {
            try {
                System.out.print("\ncmd> ");
                command = in.nextLine();
                String[] split = command.split(" ");
                router.route(split[0], Arrays.copyOfRange(split, 1, split.length));
            }
            catch (CommandNotFoundException ex) {
                System.err.println(ex.getMessage());
            }
            catch (NoSuchElementException exc) {
                break;
            }
        }
    }
}
