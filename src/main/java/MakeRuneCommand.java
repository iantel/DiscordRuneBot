import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ian on 2016-08-10.
 */
class MakeRuneCommand implements RuneCommand {

    private String mRuneQuery;

    static boolean isValid(String[] command){
        return command.length >= 1;
    }


    MakeRuneCommand (String [] args){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1 ; i < args.length; i++){
            stringBuilder.append(args[i] + " ");
        }
        mRuneQuery = stringBuilder.toString().trim().toUpperCase();
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        Map <String, RuneWeapon> matches = getPossibleRuneWeapons(mRuneQuery);
        StringBuilder runeList = new StringBuilder();
        for (String name : matches.keySet()){
            runeList.append(name);
            runeList.append(": ");
            runeList.append(matches.get(name).runecombo);
            runeList.append("\n");
        }
        String response = event.getMessage().getAuthor().mention() + "\n``` \n"
                + "Runewords that contain those runes: \n"
                + runeList.toString().trim() + "\n"
                + "```";
        try {
            event.getClient().getChannelByID(RuneBot.token).sendMessage(response);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private Map<String, RuneWeapon> getPossibleRuneWeapons(String args){
        Map <String, RuneWeapon> possibleWeapons = new HashMap<>();
        List<String []> possibleWords = this.createPossibleRunewords(args);
        for (String s : RuneWordLibrary.weaponMap.keySet()){
            for (String [] possibleWord : possibleWords){
                if (matches(s, possibleWord)){
                    possibleWeapons.put(s, RuneWordLibrary.weaponMap.get(s));
                }
            }
        }
        return possibleWeapons;
    }

    private List<String[]> createPossibleRunewords(String args){
        String [] s = args.split(" ");
        ArrayList<String[]> possibleRunes = new ArrayList<>();
        for(int i = 0; i < s.length; i++)
        {
            for(int j = 1; j <= s.length - i; j++)
            {
                possibleRunes.add(Arrays.copyOfRange(s,i,j+i));
            }
        }
        return possibleRunes.stream().filter(runes -> runes.length >= 2).collect(Collectors.toList());
    }


    private boolean matches (String weaponName, String [] possibleRuneCombo){
        int matchCount = 0;
        for (String rune : possibleRuneCombo){
            for (String runeUnit : RuneWordLibrary.weaponMap.get(weaponName).runecombo.split(" \\+ ")){
                if (rune.equals(runeUnit)){
                    matchCount ++;
                }
            }
        }
        return matchCount >= 2;
    }

}
