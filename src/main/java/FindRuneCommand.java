import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import java.util.AbstractMap;
import java.util.List;

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
            String response = "";
            String query = event.getMessage().toString().split(" ",2)[1];
            List<AbstractMap.SimpleEntry<String, RuneWeapon>> weapons = RuneWordLibrary.findWeapon(query);
            if (weapons.isEmpty()){
                response = event.getMessage().getAuthor().mention() + " ``` \n" + "no runewords found matching that query ```";
            }
            else{
                response += event.getMessage().getAuthor().mention() + " ``` \n" + "Found:\n";
                for (AbstractMap.SimpleEntry<String, RuneWeapon> found: weapons){
                    response += found.getKey() + ": " + found.getValue().sockets + " " + found.getValue().weaponType + ", "
                            + found.getValue().runecombo + "\n";
                }
                response += "Use !info command for detailed stats\n" +" ``` ";
            }
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
