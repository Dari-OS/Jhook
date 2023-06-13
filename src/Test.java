import me.dari_os.jhooks.DiscordJhook;

import java.net.URL;

public class Test {
    public static void main(String[] args) {
        try {
            DiscordJhook dWk = new DiscordJhook(new URL("<YOUR WEBHOOK URL>"));
            dWk.setMessage("Hello, World!");
            dWk.setUsername("Test-webhook");
            dWk.setAvatar_url(null);
            dWk.sendRequest();
        } catch (Exception e) {
            System.out.println("OH NO ERROR!");
        }

    }
}
