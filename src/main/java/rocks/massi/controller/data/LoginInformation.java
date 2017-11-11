package rocks.massi.controller.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginInformation {
    private final String email;
    private final String password;
}
