package rocks.massi.controller.commands;

import feign.Feign;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClientBuilder;
import rocks.massi.controller.configuration.ServerConfiguration;
import rocks.massi.controller.services.TrollsServer;

public abstract class Command {
    protected TrollsServer connector;
    Options options;
    public static String FORMAT = "%-45s%s%n";

    public Command() {
        HttpClientBuilder builder = HttpClientBuilder.create();

        if (ServerConfiguration.getInstance().getProxy() != null) {
            String proxy = ServerConfiguration.getInstance().getProxy();
            String host = proxy;
            int port = 80;
            if (proxy.contains(":")) {
                String[] hostAndPort = proxy.split(":");
                host = hostAndPort[0];
                port = Integer.valueOf(hostAndPort[1]);
            }

            builder.setProxy(new HttpHost(host, port));
        }

        connector = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .client(new ApacheHttpClient(builder.build()))
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
