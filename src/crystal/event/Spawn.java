package crystal.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import crystal.teleport.CreateCrystalTeleport;
import crystal.teleport.Teleport;

public class Spawn implements Listener{		
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = player.getInventory().getItemInMainHand();
		if ( item.isSimilar( CreateCrystalTeleport.spawn() ) ) 
		if ( action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR))
		{
			event.setCancelled(true);
			Teleport.TeleportPlayer(player);
			player.getInventory().removeItem(CreateCrystalTeleport.spawn());
						
		}			
	}
}
