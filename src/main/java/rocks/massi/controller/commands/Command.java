package rocks.massi.controller.commands;

import feign.Feign;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import lombok.SneakyThrows;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClientBuilder;
import rocks.massi.controller.configuration.ServerConfiguration;
import rocks.massi.controller.services.BGGXmlApi;
import rocks.massi.controller.services.TrollsServer;

import java.net.URI;

public abstract class Command {
    protected TrollsServer connector;
    protected BGGXmlApi bggXmlApi;

    Options options;
    public static String FORMAT = "%-45s %s %n";

    @SneakyThrows
    public Command() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        if (ServerConfiguration.getInstance().getProxy() != null) {
            URI proxy = new URI(ServerConfiguration.getInstance().getProxy());
            builder.setProxy(new HttpHost(proxy.getHost(), proxy.getPort()));
        }

        connector = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .client(new ApacheHttpClient(builder.build()))
                .target(TrollsServer.class, ServerConfiguration.getInstance().getServerAddress());

        JAXBContextFactory jaxbContextFactory = new JAXBContextFactory.Builder().build();
        bggXmlApi = Feign.builder()
                .decoder(new JAXBDecoder(jaxbContextFactory))
                .client(new ApacheHttpClient(builder.build()))
                .target(BGGXmlApi.class, "https://www.boardgamegeek.com/xmlapi/");

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
