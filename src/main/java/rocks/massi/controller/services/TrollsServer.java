package rocks.massi.controller.services;

import feign.*;
import rocks.massi.controller.authorization.JWTToken;
import rocks.massi.controller.data.LoginInformation;
import rocks.massi.controller.data.trolls.*;

import java.util.HashMap;
import java.util.List;

public interface TrollsServer {
    @RequestLine("GET /v1/users/get/{id}")
    User getUser(@Param("id") final String nick);

    @RequestLine("GET /v1/users/get")
    List<User> getAllUsers();

    @RequestLine("POST /v1/users/add")
    @Headers({"Content-Type: application/json"})
    User addUser(final User user);

    @RequestLine("POST /v1/users/login")
    @Headers({"Content-Type: application/json"})
    Response login(final LoginInformation loginInformation);

    @RequestLine("DELETE /v1/users/remove/{nick}")
    User removeUser(@Param("nick") final String nick);

    @RequestLine("POST /v1/crawler/collection/{nick}")
    Response crawlCollection(@HeaderMap HashMap<String, String> headers, @Param("nick") final String nick);

    @RequestLine("GET /v1/crawler/queue/{id}")
    Queue getQueue(@HeaderMap HashMap<String, String> headers, @Param("id") final int id);

    @RequestLine("GET /v1/crawler/queues")
    List<Queue> getQueues(@HeaderMap HashMap<String, String> headers);

    @RequestLine("DELETE /v1/crawler/queues")
    List<Queue> purgeCompletedQueues(@HeaderMap HashMap<String, String> headers);

    @RequestLine("GET /v1/games/get/{id}")
    Game getGame(@Param("id") final int id);

    @RequestLine("GET /v1/games/get")
    List<Game> getAllGames();

    @RequestLine("GET /v1/collection/get/{user}")
    List<Game> getCollection(@Param("user") final String user);

    @RequestLine("GET /v1/server/information")
    ServerInformation getVersion();

    @RequestLine("GET /v1/cache/get")
    Cache getCache();
}
