package crystal.comand;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import server.world.Spawn;

public class SetSpawn implements CommandExecutor{
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] arg) {
		
			Player player = (Player)sender;
			boolean ok =  Spawn.NewSpawn(player);
			return ok;
	}
}
