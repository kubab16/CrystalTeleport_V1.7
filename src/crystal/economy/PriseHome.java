package crystal.economy;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import server.configuration.EconomyConfig;
import server.configuration.LocalConfiguration;
import server.database.Database;

public class PriseHome {
	static double percentage = EconomyConfig.NEXTPRISE.toInteger()/100.0;
	static double FirstPrise = EconomyConfig.FIRSTPRISE.toInteger();
	/**
	 * @author kubab16
	 * @param count
	 * @return double price next set home
	 */
	public static double getPrise(int count) {
		if(EconomyConfig.TYPE.toBoolean())
		{
			return (FirstPrise * Math.pow((percentage+100), count-1));
		}	
		else
		{
			return round((FirstPrise * Math.pow((percentage+100), count-1)),2);
		}
	}
	public static double getPrise(Player player) {
		ResultSet result;
		try {
			result = Database.CreateStatement().executeQuery("SELECT * FROM " + LocalConfiguration.DBPrefix.toString() + "player WHERE UUID=\""+player.getUniqueId().toString()+"\"");
			if (result.next() && result.getRow() > 0)
			{
				int count = result.getInt("home");
				if(count == 0)
				{
					return FirstPrise;
				}
				if(EconomyConfig.TYPE.toBoolean())
				{
					return round((FirstPrise * Math.pow((percentage+1), count-1)),2);
				}	
				else
				{
					return round((FirstPrise * Math.pow((percentage+1), count-1)), 0);
				}
			}
			else
			{
				Database.CreateStatement().executeUpdate("INSERT INTO "+ LocalConfiguration.DBPrefix.toString() + "player (UUID,PremiumHome,Home) VALUES( \""+player.getUniqueId().toString()+"\", NULL,NULL)");
				return FirstPrise;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
