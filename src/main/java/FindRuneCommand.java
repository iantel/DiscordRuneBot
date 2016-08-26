import com.github.alphahelix00.discordinator.d4j.commands.CommandD4J;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Ian on 2016-08-10.
 */
class FindRuneCommand extends CommandD4J {


    FindRuneCommand(){
        super("!",
                "find",
                "Finds possible runewords based on weapon type and socket count",
                "!find socket_count weapon_type",
                Collections.singletonList("find"),
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
    public Optional execute(List<String> args, MessageReceivedEvent messageReceivedEvent, MessageBuilder messageBuilder) throws IllegalAccessException, InvocationTargetException {
        RequestBuffer.request(() -> {
            try {
                StringBuilder response = new StringBuilder();

                if (args.size() != 2) {
                    messageBuilder.withContent(messageReceivedEvent.getMessage().getAuthor().mention()
                            + "``` Invalid query length. ```").build();
                    return;
                }
                //Generate query
                String query = args.get(0) + " " + args.get(1);

                //Append any found weapons to the entry list
                List<AbstractMap.SimpleEntry<String, RuneWeapon>> weapons = RuneWordLibrary.findWeapon(query);

                //Generate response based on found weapons
                if (weapons.isEmpty()) {
                    response.append(messageReceivedEvent.getMessage().getAuthor().mention()
                            + " ``` no runewords found matching that query ```");
                } else {
                    response.append(messageReceivedEvent.getMessage().getAuthor().mention()
                            + " ``` \n" + "Found:\n");
                    for (AbstractMap.SimpleEntry<String, RuneWeapon> found : weapons) {
                        response.append(found.getKey()
                                + ": "
                                + found.getValue().sockets
                                + " "
                                + found.getValue().weaponType + ", "
                                + found.getValue().runecombo + "\n");
                    }
                    response.append("Use !info command for detailed stats\n" + " ``` ");
                }
                //send response to DiscordChat
                messageBuilder.withContent(response.toString()).build();
            }
             catch (DiscordException | MissingPermissionsException e) {
                e.printStackTrace();
            }
        });
        return Optional.empty();
    }
}
