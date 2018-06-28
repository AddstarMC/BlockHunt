package nl.Steffion.BlockHunt;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import nl.Steffion.BlockHunt.Managers.MessageManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SolidBlockHandler {

	@SuppressWarnings("deprecation")
	public static void makePlayerUnsolid(Player player) {
		ItemStack block = player.getInventory().getItem(8);
		if (block == null) {
			player.sendMessage(ChatColor.RED + "Unable to hide you because your inventory block is missing!");
			System.out.println("[BlockHunt] ERROR: " + player.getName() + " could not be hidden because their inventory block was missing!");
			return;
		}
		
		Block pBlock = player.getLocation().getBlock();

		if (MemoryStorage.hiddenLoc.get(player) != null) {
			pBlock = MemoryStorage.hiddenLoc.get(player).getBlock();
		}

		block.setAmount(5);
		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (!pl.equals(player)) {
				if (MemoryStorage.hiddenLocWater.get(player) != null) {
					if (MemoryStorage.hiddenLocWater.get(player)) {
						pl.sendBlockChange(pBlock.getLocation(), Material.STATIONARY_WATER, (byte) 0);
					} else {
						pl.sendBlockChange(pBlock.getLocation(), Material.AIR, (byte) 0);
					}
				} else {
					pl.sendBlockChange(pBlock.getLocation(), Material.AIR, (byte) 0);
				}

				MemoryStorage.hiddenLocWater.remove(player);
			}
		}

		player.playSound(player.getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
		block.removeEnchantment(Enchantment.DURABILITY);

		for (Player playerShow : Bukkit.getOnlinePlayers()) {
			playerShow.showPlayer(player);
		}

		MiscDisguise disguise = new MiscDisguise(DisguiseType.FALLING_BLOCK, block.getTypeId(), block.getDurability());
		DisguiseAPI.disguiseToAll(player, disguise);

		MessageManager.sendFMessage(player, ConfigC.normal_ingameNoMoreSolid);
	}
}
