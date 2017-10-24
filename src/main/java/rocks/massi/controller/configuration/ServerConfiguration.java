package rocks.massi.controller.configuration;

import lombok.Data;

@Data
public class ServerConfiguration {
    private static ServerConfiguration instance;
    private String serverAddress;
    private String proxy;

    private ServerConfiguration() {}

    public static ServerConfiguration getInstance() {
        if (instance == null) instance = new ServerConfiguration();
        return instance;
    }

}
