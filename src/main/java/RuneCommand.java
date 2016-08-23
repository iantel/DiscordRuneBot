import com.github.alphahelix00.discordinator.d4j.DiscordinatorModule;
import com.github.alphahelix00.discordinator.d4j.handler.CommandHandlerD4J;
import com.github.alphahelix00.ordinator.Ordinator;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

/**
 * Created by Ian on 2016-08-10.
 */

class RuneCommand implements IModule {

    @Override
    public boolean enable(IDiscordClient iDiscordClient){
        iDiscordClient.getModuleLoader().loadModule(new DiscordinatorModule());
        CommandHandlerD4J handlerD4J = new CommandHandlerD4J(Ordinator.getCommandRegistry());
        handlerD4J.registerCommand(new FindRuneCommand());
        return true;
    }

    @Override
    public void disable() {
        //do nothing
    }

    @Override
    public String getName() {
        return "RuneBot";
    }

    @Override
    public String getAuthor() {
        return "Ian Tran";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getMinimumDiscord4JVersion() {
        return "2.5.2";
    }
}
