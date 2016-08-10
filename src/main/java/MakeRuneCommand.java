import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

/**
 * Created by Ian on 2016-08-10.
 */
public class MakeRuneCommand implements RuneCommand {

    String [] runes = new String[6];

    public MakeRuneCommand (String [] args){

        System.arraycopy(args,1,runes,0,args.length-1);

    }

    @Override
    public void execute(MessageReceivedEvent event) {
        StringBuilder runeword = new StringBuilder();
        for (int i = 0; i < runes.length; i++){
            if (runes[i] != null)
                runeword.append(runes[i] + " ");
        }
        try{
            String response = event.getMessage().getAuthor().mention() + "\n``` "
                    + runeword.toString().trim() + " "
                    + "command received.\n ```";
            event.getClient().getChannelByID(RuneBot.token).sendMessage(response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean isValid(String [] command){
        return command.length <= 6 && command.length >= 1;
    }
}
