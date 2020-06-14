package crystal.menu;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import crystal.main.CrystalTeleport;
import server.player.PlayerGetHome;

public class CustomInventory implements Listener{
	
	public  void newInventory(Player player) {
		List<ItemStack> home = PlayerGetHome.getHome(player);
		int size = 9;
		if(home.size() <= 54)
		{
		if (home.size()%9 !=0)
		{
			size = ((int)(home.size()/9)+1)*9;
		}
		else
		{
			size = home.size();
		}
		if (size%9 != 0 | size == 0 )
		{
			size = 9;
		}
		Inventory items = CrystalTeleport.server.createInventory(null, size, ChatColor.DARK_GREEN + "Crystal Teleport");
		int i = 0;
		if (home != null)
			for (ItemStack userHome : home)
			{
				
				items.setItem(i, userHome);//Create item in menu
				i++;
			}
		home.clear();
		if (i % 9 != 0 || i == 0)
			for (int s = 9 -( i % 9 ) ; s > 0 ; s--)
			{
				items.setItem(i, CrystalOption.None());
				i++;
			}
		
		
		player.openInventory(items);
		}
	}
}
