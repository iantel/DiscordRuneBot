import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

/**
 * Created by Ian on 2016-08-10.
 */
public class RuneCommandListener implements IListener<MessageReceivedEvent> {


    private final String key = "!";

    @Override
    public void handle(MessageReceivedEvent event) {
        try{
            String message = event.getMessage().getContent();
            String author = event.getMessage().getAuthor().getName();
            if (message.startsWith("!")) {

                String [] command = message.split(" ");
                System.out.println("Command registered");
                
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
