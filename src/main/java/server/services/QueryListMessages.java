package server.services;

import com.google.gson.Gson;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import server.messengerserverapi.IMessengerApi;

public class QueryListMessages extends ServerResource {
    private static IMessengerApi restApi = IMessengerApi.getInstance();

    @Post
    public String listMessages(String topic) {
        return new Gson().toJson(restApi.listMessages(topic));
    }
}
