import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

/**
 * Created by Ian on 2016-08-10.
 */
public class InfoRuneCommand implements RuneCommand {

    private String runeword;

    public InfoRuneCommand(String [] args){
        StringBuilder runewords = new StringBuilder();
        for (int i = 1; i < args.length; i++){
            runewords.append(args[i] + " ");
        }
        runewords.trimToSize();
        this.runeword = runewords.toString().trim();
    }


    @Override
    public void execute(MessageReceivedEvent event) {

        try {
            String response = event.getMessage().getAuthor().mention() + "\n```"
                    + this.runeword + " "
                    + "received command.\n ```";
            event.getClient().getChannelByID(RuneBot.token).sendMessage(response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    static boolean isValid(String[] command) {
        return command.length > 1;
    }
}
