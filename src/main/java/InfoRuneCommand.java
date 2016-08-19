import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

/**
 * Created by Ian on 2016-08-10.
 */
public class InfoRuneCommand implements RuneCommand {

    private String runeword;

    InfoRuneCommand(String [] args){
        StringBuilder runewords = new StringBuilder();
        for (int i = 1; i < args.length; i++){
            runewords.append(args[i] + " ");
        }
        runewords.trimToSize();
        this.runeword = runewords.toString().toUpperCase().trim();
    }


    @Override
    public void execute(MessageReceivedEvent event) {

        try {
            String response;
            if (RuneWordLibrary.weaponMap.containsKey(this.runeword)){
                System.out.println(runeword);
                RuneWeapon runeWeapon = RuneWordLibrary.weaponMap.get(this.runeword);
                response = event.getMessage().getAuthor().mention() + "\n```"
                        + this.runeword + " \n"
                        + runeWeapon.runecombo + " \n"
                        + runeWeapon.sockets + " " + runeWeapon.weaponType + "\n "
                        + runeWeapon.weaponStats + "```";
                event.getClient().getChannelByID(RuneBot.token).sendMessage(response);
            }
            else{
                response = event.getMessage().getAuthor().mention()
                        + "``` No runeword found matching that name. ```";
                event.getClient().getChannelByID(RuneBot.token).sendMessage(response);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    static boolean isValid(String[] command) {
        return command.length > 1;
    }
}
