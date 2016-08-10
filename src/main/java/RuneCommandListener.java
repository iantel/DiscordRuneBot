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

            if (message.startsWith("!")) {
                String [] command = message.split(" ");
                switch (command[0]){
                    case "!find":
                        if (FindRuneCommand.isValid(command))
                            new FindRuneCommand(command).execute(event);
                        break;
                    case "!info":
                        if (InfoRuneCommand.isValid(command))
                            new InfoRuneCommand(command).execute(event);
                        break;
                    case "!make":
                        break;
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
