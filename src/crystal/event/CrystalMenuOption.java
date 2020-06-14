package crystal.event;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import crystal.economy.PriseHome;
import crystal.main.CrystalTeleport;
import crystal.menu.CrystalOption;
import crystal.teleport.CreateCrystalTeleport;
import crystal.teleport.Teleport;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import server.configuration.EconomyConfig;
import server.player.PlayerHome;
import server.player.addHome;

public class CrystalMenuOption implements Listener{
	public String prefix = (ChatColor.GREEN + "TUTORIAL>> ");

	@EventHandler
	public void InvenClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();

		if (open == null) {
			return;
		}
			
			
		if (item == null || !item.hasItemMeta()) {
			return;
		}

		if ( event.getCurrentItem().getItemMeta().getLocalizedName().equals("Teleport") ) {
			event.setCancelled(true);
			if (item.getItemMeta().hasCustomModelData())
			{
				Teleport.TeleportPlayer(item.getItemMeta().getCustomModelData(), player);
				player.getInventory().removeItem(CreateCrystalTeleport.crystal());
				player.closeInventory();
			}
		}
		if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("SetHome") )
		{
			event.setCancelled(true);
			if(item.isSimilar(CrystalOption.cancel()))
			{
				player.closeInventory();
			}
			else
			{
				Economy economy = CrystalTeleport.economy;
				double withdraw_amount = PriseHome.getPrise(player);
                //if the method returns an economyresponse, set the method equal to a reference for one
                //so that you can use it for information on the transaction
                EconomyResponse response = economy.withdrawPlayer(player, withdraw_amount);

				if (response.transactionSuccess()) {
					player.sendMessage(ChatColor.GREEN +"Balance: "+ ChatColor.RED + economy.format(response.balance));
					Location location = player.getLocation();
					double x = location.getX();
					double y = location.getBlockY();
					double z = location.getZ();
					String word = location.getWorld().getUID().toString();
					String name = item.getItemMeta().getDisplayName().replace(ChatColor.GREEN+"","");
					PlayerHome NewHome =new PlayerHome( 0 , name , x, y, z, word);
					@SuppressWarnings("unused")
					addHome home = new addHome(player,NewHome);
					player.closeInventory();
				}
				else
				{
					player.sendMessage(ChatColor.RED+EconomyConfig.NOMONEY.toString());
				}
			}
		}		
	}
	
}