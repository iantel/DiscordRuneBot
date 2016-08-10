import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

/**
 * Created by Ian on 2016-08-10.
 */

public interface RuneCommand {


    void execute(MessageReceivedEvent event);

}
