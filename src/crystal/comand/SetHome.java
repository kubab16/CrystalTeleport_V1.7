package crystal.comand;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import crystal.economy.PriseHome;
import crystal.menu.SubmitSetHome;
import server.configuration.EconomyConfig;
import server.configuration.LocalConfiguration;
import server.database.Database;

public class SetHome implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] arg) {
		int count = 0;
		Player player = (Player)sender;
		ResultSet result;
		try {
			result = Database.CreateStatement().executeQuery("SELECT * FROM " + LocalConfiguration.DBPrefix.toString() + "player WHERE UUID=\""+player.getUniqueId().toString()+"\"");
			if (result.next() && result.getRow() > 0)
			{
				count = result.getInt("home");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		if (arg.length == 1 && count < EconomyConfig.LIMIT.toInteger())
		{
			

			SubmitSetHome.SetHome( player, PriseHome.getPrise(player) ,arg[0]);
			return true;
		}
		else
			return false;
	}

}
