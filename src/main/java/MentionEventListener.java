import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MentionEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * Created by Ian on 8/9/2016.
 */
public class MentionEventListener implements IListener<MentionEvent> {


    public void handle(MentionEvent mentionEvent) {
        try {
            if (mentionEvent.getMessage().getContent().contains("gtfo")){
                if (mentionEvent.getMessage().getAuthor().getName().equals("iantel")) {
                    RuneBot.mFamChannel.sendMessage("Signing off, RIP Harambe");
                    mentionEvent.getClient().logout();
                    System.exit(-1);
                }
            }
            else {
                RuneBot.mFamChannel.sendMessage("You called?");
            }
        } catch (MissingPermissionsException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        } catch (DiscordException e) {
            e.printStackTrace();
        }
    }
}
