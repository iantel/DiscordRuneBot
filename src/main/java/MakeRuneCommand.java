import com.github.alphahelix00.discordinator.d4j.commands.CommandD4J;
import com.github.alphahelix00.ordinator.commands.Command;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ian on 2016-08-10.
 */
class MakeRuneCommand extends CommandD4J{

    public MakeRuneCommand() {
        super("!",
                "make",
                "Makes possible runewords based on user given runes",
                "!make rune1 rune2 [Optional] rune3 rune4 rune5 rune6",
                Collections.singletonList("make"),
                true,
                true,
                false,
                new HashMap<>(),
                new HashMap<>(),
                EnumSet.of(Permissions.READ_MESSAGES, Permissions.SEND_MESSAGES),
                false,
                true,
                false,
                false);
    }

    @Override
    public Optional execute(List<String> args, MessageReceivedEvent event, MessageBuilder msgBuilder) throws IllegalAccessException, InvocationTargetException {
        RequestBuffer.request(() -> {
            try {
                StringBuilder mRuneQuery = new StringBuilder();
                for (String rune : args) {
                    mRuneQuery.append(rune + " ");
                }
                Map<String, RuneWeapon> matches = getPossibleRuneWeapons(mRuneQuery.toString().trim());
                StringBuilder runeList = new StringBuilder();
                for (String name : matches.keySet()) {
                    runeList.append(name);
                    runeList.append(": ");
                    runeList.append(matches.get(name).runecombo);
                    runeList.append("\n");
                }
                String response = event.getMessage().getAuthor().mention() + "\n``` \n"
                        + "Runewords that contain those runes: \n"
                        + runeList.toString().trim() + "\n"
                        + "Use !info for detailed stats. \n"
                        + "```";
                msgBuilder.withContent(response).build();
            } catch (DiscordException | MissingPermissionsException e){
                e.printStackTrace();
            }
        });
        return Optional.empty();
    }

    private Map<String, RuneWeapon> getPossibleRuneWeapons(String args){
        Map <String, RuneWeapon> possibleWeapons = new HashMap<>();
        List<String []> possibleWords = createPossibleRunewords(args);
        System.out.println(possibleWords.size());
        for (String weaponName : RuneWordLibrary.weaponMap.keySet()){
            for (String [] possibleWord : possibleWords){
                if (matchesRuneWord(weaponName, possibleWord)){
                    possibleWeapons.put(weaponName, RuneWordLibrary.weaponMap.get(weaponName));
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
        System.out.println(possibleRunes.stream().filter(runes -> runes.length >= 2).collect(Collectors.toList()).size());
        return possibleRunes.stream().filter(runes -> runes.length >= 2).collect(Collectors.toList());
    }


    private boolean matchesRuneWord (String weaponName, String [] possibleRuneCombo){
        int matchCount = 0;
        for (String rune : possibleRuneCombo){
            for (String runeUnit : RuneWordLibrary.weaponMap.get(weaponName).runecombo.split(" \\+ ")){
                if (rune.equalsIgnoreCase(runeUnit)){
                    matchCount++;
                }
            }
        }
        return matchCount >= 2;
    }
}
