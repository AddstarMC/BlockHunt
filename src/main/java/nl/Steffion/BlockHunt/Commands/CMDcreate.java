package nl.Steffion.BlockHunt.Commands;

import java.util.ArrayList;

import nl.Steffion.BlockHunt.*;
import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.Arena.ArenaState;
import nl.Steffion.BlockHunt.MemoryStorage;
import nl.Steffion.BlockHunt.Managers.MessageManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDcreate extends DefaultCMD {

	@Override
	public boolean execute(Player player, Command cmd, String label, String[] args) {
		if (player != null) {
			if (args.length <= 1) {
				MessageManager.sendFMessage(player, ConfigC.error_notEnoughArguments, "syntax-" + BlockHunt.CMDcreate.usage);
			} else {
				if (MemoryStorage.pos1.get(player) != null && MemoryStorage.pos2.get(player) != null) {
					if (MemoryStorage.pos1.get(player).getWorld().equals(MemoryStorage.pos2.get(player).getWorld())) {
						Arena arena = new Arena(args[1], MemoryStorage.pos1.get(player), MemoryStorage.pos2.get(player), 12, 3, 1, 50, 20, 300, 30, 45, false, false, false, false, false, new ArrayList<>(), null, null, null,
								null, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 10, 50, 8, new ArrayList<>(), ArenaState.WAITING,
								0, new ArrayList<>(), Bukkit.getScoreboardManager().getNewScoreboard());
						MemoryStorage.arenas.getFile().set(args[1], arena);
						MemoryStorage.arenas.save();
						MemoryStorage.signs.load();

						MemoryStorage.arenaList.add(arena);
						ScoreboardHandler.createScoreboard(arena);

						MessageManager.sendFMessage(player, ConfigC.normal_createCreatedArena, "name-" + args[1]);
					} else {
						MessageManager.sendFMessage(player, ConfigC.error_createNotSameWorld);
					}
				} else {
					MessageManager.sendFMessage(player, ConfigC.error_createSelectionFirst);
				}
			}
		} else {
			MessageManager.sendFMessage(player, ConfigC.error_onlyIngame);
		}
		return true;
	}
}
