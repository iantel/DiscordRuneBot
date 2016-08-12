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

    public static String token = "189425246791663616";

    public static void main(String[] args) throws Exception {
        RuneWordLibrary.build("C:\\Users\\Ian\\Downloads\\OrigRuneWords.shtml");
        RuneWordLibrary.build("C:\\Users\\Ian\\Downloads\\110RuneWords.shtml");
        RuneWordLibrary.build("C:\\Users\\Ian\\Downloads\\111RuneWords.shtml");
        client = getClient("MjEyNjIzMjUzMzg0OTg2NjI2.CovvYw.oygp_6OKWv6j1flSzm8yehQlGWw");
        client.login();
        client.getDispatcher().registerListener(new InitChannelListener());
        client.getDispatcher().registerListener(new MentionEventListener());
        client.getDispatcher().registerListener(new RuneCommandListener());
    }

    private static IDiscordClient getClient (String token) throws DiscordException{
        return new ClientBuilder().withToken(token).build();
    }
}
