package client;

import com.google.gson.Gson;
import org.restlet.resource.ClientResource;
import message.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws Exception {
        ClientResource postMessage = new ClientResource("http://localhost:"+args[0]+"/postMessage/");


        //post stuff
        Message mess = new Message(
                "client0",
                "kalle4",
                "KallesFanClub",
                "Tycker du också att kalle är bäst",
                "Here are some bytes".getBytes());
        postMessage.post(new Gson().toJson(mess));

        mess = new Message(
                "client1",
                "kalle3",
                "KallesFanClub",
                "I Agree",
                "Here are some other bytes".getBytes());
        postMessage.post(new Gson().toJson(mess));

        mess = new Message(
                "client2",
                "kalle2",
                "KallesFanClub",
                "Me two",
                "Not like tease bytes".getBytes());
        postMessage.post(new Gson().toJson(mess));

        mess = new Message(
                "client3",
                "kalle1",
                "SomeOtherFanClub",
                "Me two",
                "Best bytes here".getBytes());
        postMessage.post(new Gson().toJson(mess));

        //fetching all post from topic kalleFanClub
        ClientResource listMessages = new ClientResource("http://localhost:"+args[0]+"/listMessages/");
        listMessages.post("KallesFanClub").write(System.out);
        System.out.println("\n");

        ClientResource listMessagesWithTimestamp = new ClientResource("http://localhost:"+args[0]+"/listMessagesWithTimestamp/");
        listMessagesWithTimestamp.post("KallesFanClub").write(System.out);
        System.out.println("\n");

        ClientResource fetchMessage = new ClientResource("http://localhost:"+args[0]+"/retrieveMessage/");
        fetchMessage.post("Hej, kalle3 här").write(System.out);
        System.out.println("\n");

        fetchMessage.post("Hej jag heter kalle").write(System.out);
        System.out.println("\n");
        System.out.println("listTopics should print [\"SomeOtherFanClub\",\"KallesFanClub\"]:");
        ClientResource listTopics = new ClientResource("http://localhost:"+args[0]+"/listTopics/");
        listTopics.get().write(System.out);
        System.out.println("\n");

        ClientResource subscribe = new ClientResource("http://localhost:"+args[0]+"/subscribe/");
        System.out.println("subscribe should print true:");
        subscribe.put(new Gson().toJson(new String[]{"kalle", "KallesFanClub"})).write(System.out);
        System.out.println("\n");
        System.out.println("subscribe should print true:");
        subscribe.put(new Gson().toJson(new String[]{"Jakob", "SomeOtherFanClub"})).write(System.out);
        System.out.println("\n");
        System.out.println("subscribe should print false:");
        subscribe.put(new Gson().toJson(new String[]{"kalle", "blabla"})).write(System.out);
        System.out.println("\n");

        ClientResource unsubscribe = new ClientResource("http://localhost:"+args[0]+"/subscribe/");

        System.out.println("unsubscribe should print true:");
        unsubscribe.put(new Gson().toJson(new String[]{"kalle", "KallesFanClub"})).write(System.out);
        System.out.println("\n");

        System.out.println("unsubscribe should print false:");
        unsubscribe.put(new Gson().toJson(new String[]{"kalle", "blabla"})).write(System.out);
        System.out.println("\n");

        ClientResource listSubscribers = new ClientResource("http://localhost:"+args[0]+"/listSubscribers/");
        listSubscribers.setQueryValue("topic", "KallesFanClub");
        listSubscribers.get().write(System.out);
        System.out.println("\n");

        listSubscribers.setQueryValue("topic", "SomeOtherFanClub");
        listSubscribers.get().write(System.out);
        System.out.println("\n");

        ClientResource listNodes = new ClientResource("http://localhost:"+args[0]+"/listNodes/");
        listNodes.get().write(System.out);
        System.out.println("\n");
    }
}
