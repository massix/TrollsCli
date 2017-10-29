package rocks.massi.controller;

import org.apache.commons.cli.*;
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
        options.addOption("s", "server", true, "Set server (required parameter)");
        options.addOption("p", "proxy", true, "Set proxy");
        options.addOption("c", "command", true, "Run a single command and leave");
        options.addOption("h", "help", false, "Print help and leave");

        // Parse CLI
        CommandLine cli = new DefaultParser().parse(options, args);
        ServerConfiguration.getInstance().setServerAddress(cli.getOptionValue("s"));

        String singleCommand = "";

        if (cli.hasOption("p")) {
            ServerConfiguration.getInstance().setProxy(cli.getOptionValue("p"));
        }

        if (cli.hasOption("c")) {
            singleCommand = cli.getOptionValue("c");
        }

        if (cli.hasOption("h") || ! cli.hasOption("s")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("controller", options);
            System.exit(0);
        }

        // Register commands in Router
        router = new CommandRouter();
        router.add(new CreateDatabaseCommand());
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
        router.add(new GetCacheCommand());
        router.add(new GetVersionCommand());
        router.add(new UpdateAllUsersCommand());
        router.add(new HelpCommand());
        router.add(new ExitCommand());

        if (singleCommand.isEmpty()) {
            System.out.println("Available commands");
            router.help();
        }
        else {
            String[] commands = singleCommand.trim().split(";");

            for (String command : commands) {
                if (!command.isEmpty()) {
                    String[] split = command.trim().split(" ");
                    router.route(split[0], Arrays.copyOfRange(split, 1, split.length));
                }
            }

            System.exit(0);
        }

        String command = "";
        Scanner in = new Scanner(System.in);

        while (! command.equals("exit")) {
            try {
                System.out.print("\ncmd> ");
                command = in.nextLine();
                String[] split = command.trim().split(" ");
                router.route(split[0], Arrays.copyOfRange(split, 1, split.length));
            }
            catch (CommandNotFoundException ex) {
                System.err.println(ex.getMessage());
            }
            catch (NoSuchElementException exc) {
                break;
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
