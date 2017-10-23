package rocks.massi.controller.services;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import rocks.massi.controller.data.Game;
import rocks.massi.controller.data.Queue;
import rocks.massi.controller.data.User;

import java.util.List;

public interface TrollsServer {
    @RequestLine("GET /v1/users/get/{id}")
    User getUser(@Param("id") final String nick);

    @RequestLine("GET /v1/users/get")
    List<User> getAllUsers();

    @RequestLine("POST /v1/users/add")
    @Headers({"Content-Type: application/json"})
    User addUser(final User user);

    @RequestLine("DELETE /v1/users/remove/{nick}")
    User removeUser(@Param("nick") final String nick);

    @RequestLine("POST /v1/crawler/users/{nick}")
    User crawlUser(@Param("nick") final String nick);

    @RequestLine("POST /v1/crawler/collection/{nick}")
    Response crawlCollection(@Param("nick") final String nick);

    @RequestLine("GET /v1/crawler/queue/{id}")
    Queue getQueue(@Param("id") final int id);

    @RequestLine("GET /v1/crawler/queues")
    List<Queue> getQueues();

    @RequestLine("DELETE /v1/crawler/queues")
    List<Queue> purgeCompletedQueues();

    @RequestLine("GET /v1/games/get/{id}")
    Game getGame(@Param("id") final int id);
}
