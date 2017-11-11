package rocks.massi.controller.data.trolls;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private String bggNick;
    private String forumNick;
    private String password;
    private String email;
}
