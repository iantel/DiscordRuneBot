import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MentionEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * Created by Ian on 8/9/2016.
 */
public class MentionEventListener implements IListener<MentionEvent> {


    public void handle(MentionEvent mentionEvent) {
        IMessage command = mentionEvent.getMessage();
        try {
            if (command.getContent().contains("gtfo") && command.getAuthor().getID().equals("167476540458663937")){

                RuneBot.mFamChannel.sendMessage("Signing off, RIP Harambe");
                mentionEvent.getClient().logout();
                System.exit(-1);

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
