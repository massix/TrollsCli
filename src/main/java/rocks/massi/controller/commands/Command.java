package rocks.massi.controller.commands;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.configuration.ServerConfiguration;
import rocks.massi.controller.services.TrollsServer;

public abstract class Command {
    protected TrollsServer connector;
    Options options;

    public Command() {
        connector = Feign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder())
                .target(TrollsServer.class, ServerConfiguration.getInstance().getServerAddress());

        options = new Options();
    }

    public abstract void run(String[] args) throws ParseException, HelpRequestedException;
    public abstract String getName();
    public abstract String help();
}
