package message;

public class Message {
    private String id;
    private long timestamp;
    private String sender;
    private String topic;
    private String content;
    private byte[] attachments;

    public Message(String id, String sender, String topic, String content, byte[] attatchments) {
        this.id = id;
        this.sender = sender;
        this.topic = topic;
        this.content = content;
        this.attachments = attatchments;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getAttachments() {
        return attachments;
    }

    public void setAttachments(byte[] attachments) {
        this.attachments = attachments;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
