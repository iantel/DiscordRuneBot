import com.github.alphahelix00.discordinator.d4j.commands.CommandD4J;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RequestBuffer;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Ian on 2016-08-10.
 */
public class InfoRuneCommand extends CommandD4J{

    InfoRuneCommand(){
        super("!",
                "info",
                "Gives detailed stat information of a specified runeword",
                "!info runeword",
                Collections.singletonList("info"),
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
                StringBuilder response = new StringBuilder();

                if (args.size() != 1) {
                    msgBuilder.withContent(event.getMessage().getAuthor().mention()
                            + "\n ```Invalid query length.").build();
                    return;
                }

                String runeword = args.get(0);
                if (RuneWordLibrary.weaponMap.containsKey(runeword)){
                    RuneWeapon runeWeapon = RuneWordLibrary.weaponMap.get(runeword);
                    response.append(event.getMessage().getAuthor().mention() + "\n```"
                            + runeword + " \n"
                            + runeWeapon.runecombo + " \n"
                            + runeWeapon.sockets + " " + runeWeapon.weaponType + "\n "
                            + runeWeapon.weaponStats + "```");
                } else {
                    response.append("No runeword found matching that name.");
                }

                msgBuilder.withContent(response.toString()).build();
            } catch (DiscordException | MissingPermissionsException e) {
                e.printStackTrace();
            }
        });
        return Optional.empty();
    }
}
