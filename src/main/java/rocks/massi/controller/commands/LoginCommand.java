package rocks.massi.controller.commands;

import feign.Response;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import rocks.massi.controller.authorization.JWTToken;
import rocks.massi.controller.data.LoginInformation;

public class LoginCommand extends Command {
    public LoginCommand() {
        super();

        options.addRequiredOption("e", "email", true, "email for the user");
        options.addRequiredOption("p", "password", true, "password for the user");

    }

    @Override
    public void run(String[] args) throws ParseException, HelpRequestedException {
        CommandLine cli = new DefaultParser().parse(options, args);
        Response response = connector.login(new LoginInformation(cli.getOptionValue("e"), cli.getOptionValue("p")));
        System.out.println("Code: " + response.status());
        if (response.headers().containsKey("Authorization")) {
            String token = (String) response.headers().get("Authorization").toArray()[0];
            token = token.replace("Bearer ", "");
            JWTToken.getInstance().setToken(token);
            System.out.println("Got authorization header: " + JWTToken.getInstance().getToken());
        }

    }

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public String help() {
        return "login -e email -p password\tLogins to the system and stores the JWT token.";
    }
}
