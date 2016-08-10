import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;

/**
 * Created by Ian on 8/9/2016.
 */
public class RuneBot {

    public static IDiscordClient client;

    public static IChannel mFamChannel;

    public static void main(String[] args) throws Exception {
        client = getClient("MjEyNjIzMjUzMzg0OTg2NjI2.CovvYw.oygp_6OKWv6j1flSzm8yehQlGWw");
        client.login();
        client.getDispatcher().registerListener(new InitChannelListener());
        client.getDispatcher().registerListener(new MentionEventListener());
    }

    private static IDiscordClient getClient (String token) throws DiscordException{
        return new ClientBuilder().withToken(token).build();
    }
}
