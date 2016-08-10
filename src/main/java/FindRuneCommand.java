import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

/**
 * Created by Ian on 2016-08-10.
 */
public class FindRuneCommand implements RuneCommand {

    private int mSockets;
    private String mGearType;

    public FindRuneCommand(String [] args){
        try{
            mSockets = Integer.parseInt(args[1]);
            mGearType = args[2];
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        try {
            String response = event.getMessage().getAuthor().mention() + "\n``` "
                    + this.mSockets + " "
                    + this.mGearType + " "
                    + " received command.\n ```";
            event.getClient().getChannelByID(RuneBot.token).sendMessage(response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    static boolean isValid(String[] command) {
        try{
            int sockets = Integer.parseInt(command[1]);
            return command.length == 3 && sockets <= 6 && sockets >= 2;
        } catch (Exception e){
            return false;
        }
    }
}
