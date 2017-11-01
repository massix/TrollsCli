package rocks.massi.controller.data.trolls;

import lombok.Data;

@Data
public class ServerInformation {
    private String version;
    private String artifact;
    private String timestamp;
}
