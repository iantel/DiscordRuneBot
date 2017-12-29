import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;

/**
 * Created by Ian on 8/9/2016.
 */
public class RuneBot {

    static IDiscordClient client;

    static IChannel mFamChannel;

    static String token = "";

    public static void main(String[] args) throws Exception {
        RuneWordLibrary.build("C:\\Users\\Ian\\Downloads\\OrigRuneWords.shtml");
        RuneWordLibrary.build("C:\\Users\\Ian\\Downloads\\110RuneWords.shtml");
        RuneWordLibrary.build("C:\\Users\\Ian\\Downloads\\111RuneWords.shtml");

        client = getClient("");
        client.login();
        client.getDispatcher().registerListener(new InitChannelListener());
        client.getDispatcher().registerListener(new MentionEventListener());
        new RuneCommandHandler().enable(RuneBot.client);
    }

    private static IDiscordClient getClient(String token) throws DiscordException {
        return new ClientBuilder().withToken(token).build();
    }
}
