package server.services;

import com.google.gson.Gson;
import message.Message;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import server.messengerserverapi.IMessengerApi;

public class QueryPostMessage extends ServerResource {

    private static IMessengerApi restApi = IMessengerApi.getInstance();

    @Post
    public void postMessage(String message) {
        restApi.postMessage(new Gson().fromJson(message, Message.class));
    }
}
