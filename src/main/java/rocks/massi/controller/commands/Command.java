package rocks.massi.controller.commands;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.configuration.ServerConfiguration;
import rocks.massi.controller.services.TrollsServer;

public abstract class Command {
    protected TrollsServer connector;
    Options options;
    public static String FORMAT = "%-45s%s%n";

    public Command() {
        connector = Feign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder())
                .target(TrollsServer.class, ServerConfiguration.getInstance().getServerAddress());

        options = new Options();
    }

    public abstract void run(String[] args) throws ParseException, HelpRequestedException;
    public abstract String getName();
    public abstract String help();

    String parseArgsForString(String[] args) throws ParseException {
        // Parse CLI
        CommandLine commandLine = new DefaultParser().parse(options, args);
        String string;
        if (commandLine.getArgList().isEmpty()) {
            throw new MissingArgumentException("Missing argument [nick]");
        }
        else {
            string = String.join(" ", commandLine.getArgList());
        }

        return string;
    }

    int parseArgsForInt(String[] args) throws ParseException {
        // Parse CLI
        CommandLine commandLine = new DefaultParser().parse(options, args);
        int ret;
        if (commandLine.getArgList().isEmpty()) {
            throw new MissingArgumentException("Missing argument [nick]");
        } else {
            ret = Integer.valueOf(commandLine.getArgList().get(0));
        }

        return ret;
    }
}
