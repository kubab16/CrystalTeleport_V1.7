package crystal.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import server.configuration.EconomyConfig;

public class CrystalOption {
	
	public static ItemStack CreateHomeOption(String name, String WordName, int id) {
		ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE);
		ItemMeta meta = item.getItemMeta();
		
		
		meta.setDisplayName(ChatColor.GOLD+"#"+name);
		meta.setLocalizedName("Teleport");
		
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN+"Świat: "+ WordName);
		meta.setLore(lore);
		
		
		meta.setCustomModelData(id);
		
		item.setItemMeta(meta);
		
		return item;
	} //Create home option
	
	public static ItemStack CreateSpawnOption() {
		ItemStack item = new ItemStack(Material.ENDER_PEARL); // Item set ENDER PEARL
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.GOLD+"Spawn"); //Item set name
		meta.setLocalizedName("Teleport"); //Item set tag
		
		meta.setLocalizedName("Teleport");
		
		meta.setCustomModelData(0);
		
		item.setItemMeta(meta);
		
		return item;
		
	} //Create spawn option
	
	public static ItemStack submit(double prinse,String name)
	{
		ItemStack item = new ItemStack(Material.LIME_WOOL); // Item set ENDER PEARL
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.GREEN+name); //Item set name
		meta.setLocalizedName("SetHome"); //Item set tag
		
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GREEN+EconomyConfig.SUBMIT.toString());
		lore.add(ChatColor.RED + "$" + prinse);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack cancel()
	{
		ItemStack item = new ItemStack(Material.RED_WOOL); // Item set ENDER PEARL
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.GREEN+EconomyConfig.CALNEL.toString()); //Item set name
		meta.setLocalizedName("SetHome"); //Item set tag
		

		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack None()
	{
		ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("None");
		meta.setLocalizedName("Teleport");
		
		
		item.setItemMeta(meta);
		return item;
	} //Create none item to menu
}
