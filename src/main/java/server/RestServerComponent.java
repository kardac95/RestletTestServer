package server;

import org.restlet.data.Protocol;

import org.restlet.Component;
import server.messengerserverapi.IMessengerApi;
import server.services.*;

public class RestServerComponent extends Component {


    public static void main(String[] args) throws Exception {
        new RestServerComponent(args[0], Integer.parseInt(args[1])).start();
    }

    public RestServerComponent(String address, int port) {
        IMessengerApi restApi = IMessengerApi.getInstance();
        getServers().add(Protocol.HTTP, address,port);
        getServers().stream().forEach(s -> {
            System.out.println(s.getAddress() + ":" + s.getPort());
            restApi.addToListNodes(s.getAddress() + ":" + s.getPort());
        });
        getDefaultHost().attach("/postMessage/", QueryPostMessage.class);
        getDefaultHost().attach("/listMessages/", QueryListMessages.class);
        getDefaultHost().attach("/listMessagesWithTimestamp/", QueryListMessageWithTimestamps.class);
        getDefaultHost().attach("/retrieveMessage/", QueryRetrieveMessage.class);
        getDefaultHost().attach("/listTopics/", QueryListTopic.class);
        getDefaultHost().attach("/subscribe/", QuerySubscribe.class);
        getDefaultHost().attach("/unsubscribe/", QueryUnsubscribe.class);
        getDefaultHost().attach("/listSubscribers/", QueryListSubscribers.class);
        getDefaultHost().attach("/listNodes/", QueryListNodes.class);



    }
}
