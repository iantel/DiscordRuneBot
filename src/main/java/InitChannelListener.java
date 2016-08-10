import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;

/**
 * Created by Ian on 8/9/2016.
 */
public class InitChannelListener implements IListener<ReadyEvent> {

    public void handle(ReadyEvent readyEvent) {
        RuneBot.mFamChannel = RuneBot.client.getChannelByID("212962523585052672");
    }

}
