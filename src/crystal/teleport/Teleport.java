package crystal.teleport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import server.configuration.LocalConfiguration;
import server.database.Database;
import server.world.Spawn;

public class Teleport {
	public static boolean TeleportPlayer(int id,Player player)
	{
		ResultSet results = Database.executeQuery("SELECT name,x,y,z,world FROM "+ LocalConfiguration.DBPrefix.toString() + "home WHERE id_home="+id);
		try {
			if (results.next())
			{
				double x = results.getDouble("x");
				double y = results.getDouble("y");
				double z = results.getDouble("z");
				World world = Bukkit.getServer().getWorld(UUID.fromString(results.getString("world")));
				Location PlayerLocation = new Location(world,x,y,z);
				player.teleport(PlayerLocation);
				player.sendMessage("Teleport do " + results.getString("name"));
				return true;
			}
			else
			{
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		
	}
	public static boolean TeleportPlayer(Player player)
	{
		Location spawn = Spawn.getSpawn(player);
		player.teleport(spawn);
		return true;
		
	}
}
