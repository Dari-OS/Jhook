package me.dari_os.jhooks;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DiscordJhook {
    private String username = null;
    private URL webhookURL;
    private String message = "Hello world";
    private String avatar_url = null;
    public DiscordJhook(URL webhookURL) throws IllegalArgumentException{
        if(webhookURL != null ) this.webhookURL = webhookURL;
        else throw new IllegalArgumentException("Webhook-URL can´t be null!");
    }
    public DiscordJhook(URL webhookURL, String username) {
        this(webhookURL);
        this.username = username;
    }
    public DiscordJhook(URL webhookURL, String username, String message) {
        this(webhookURL, username);
        if(message != null ) this.message = message;
        else throw new IllegalArgumentException("Webhook message can´t be null!");
    }

    public DiscordJhook(URL webhookURL, String username, String message, String avatar_url) {
        this(webhookURL, username, message);
        this.avatar_url = avatar_url;
    }

    /**
     * Sends the webhook-message with given parameters like username, avatar-url.....
     * If username/avatar-url is null, the webhook is going to use the default webhook username/avatar (set in the webhook settings)
     * @return returns the response code of the connection,
     */
    public int sendRequest() {
        int responseCode = -1;
        try {
            HttpURLConnection connection = (HttpURLConnection) webhookURL.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            String jsonPayload = "{\"content\": \"" + message + "\"";
            if (avatar_url != null) jsonPayload += ", \"avatar_url\": \"" + avatar_url + "\"";
            if (username != null) jsonPayload += ", \"username\": \"" + username + "\"";
            jsonPayload += "}";
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonPayload.getBytes());
            outputStream.flush();
            outputStream.close();
            responseCode = connection.getResponseCode();
            connection.disconnect();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return responseCode;
    }

    /**
     * Sets the webhook. May throw an IllegalArgumentException, when the url is null!
     * @param webhookURL the url for the webhook
     */
    public void setWebhookURL(URL webhookURL) {
        if(webhookURL != null ) this.webhookURL = webhookURL;
        else throw new IllegalArgumentException("Webhook-URL can´t be null!");
    }

    /**
     * May be null
     * @return returns the text that is set in the webhook-message
     */
    public String getMessage() {
        return message;
    }

    /**
     * sets the text in the webhook-message.
     * @param message the message that the webhook-message is going to contain
     */
    public void setMessage(String message) {
        if(message != null ) this.message = message;
        else throw new IllegalArgumentException("Webhook message can´t be null!");
    }
    /**
     * May be null
     * @return returns the avatar url of the set webhook-message.
     */
    public String getAvatar_url() {
        return avatar_url;
    }

    /**
     * May be null
     * @return returns the username of the set webhook-message.
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets the username for the webhook. If the username is null the webhooks default username is going to be used
     * @param username the username that the webhook-message is going to have.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * sets the Avatar URL for the webhook. If the avatar-url is null the webhooks default username is going to be used
     * @param avatar_url the avatar_url that the webhook-message is going to have.
     */
    public void setAvatar_url(String avatar_url) {
       this.avatar_url = avatar_url;
    }
}
