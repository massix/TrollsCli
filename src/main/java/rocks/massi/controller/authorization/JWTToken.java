package rocks.massi.controller.authorization;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class JWTToken {
    private JWTToken() {}

    private static JWTToken instance;

    @Getter
    @Setter
    private String token;

    public static JWTToken getInstance() {
        if (instance == null) {
            instance = new JWTToken();
        }

        return instance;
    }

    public HashMap<String, String> getHeadersMap() {
        HashMap<String, String> ret = new HashMap<>();
        ret.put("Authorization", "Bearer " + getToken());
        return ret;
    }
}
