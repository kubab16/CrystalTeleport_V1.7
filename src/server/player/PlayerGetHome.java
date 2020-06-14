package server.player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import crystal.menu.CrystalOption;
import server.configuration.LocalConfiguration;
import server.database.Database;

public class PlayerGetHome {

	private static  List<ItemStack> homes = new ArrayList<ItemStack>();

	public static List<ItemStack> getHome(Player player)
	{
		if (Database.isClosed())
		{
			Database.reconnect();
		}
		ResultSet results = Database.executeQuery("SELECT id_home,name,x,y,z,world FROM "+ LocalConfiguration.DBPrefix.toString() + "home WHERE uuid='"+
				player.getUniqueId().toString()+"'");
		try {
			while (results.next())
			 {
				ItemStack item = CrystalOption.CreateHomeOption(results.getString("name"),
						Bukkit.getServer().getWorld(UUID.fromString(results.getString("world"))).getName()
						, results.getInt("id_home"));
				homes.add(item);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return homes;
	}
}
