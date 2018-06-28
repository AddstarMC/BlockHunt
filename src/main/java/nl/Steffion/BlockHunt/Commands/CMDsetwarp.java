package nl.Steffion.BlockHunt.Commands;

import nl.Steffion.BlockHunt.*;
import nl.Steffion.BlockHunt.MemoryStorage;
import nl.Steffion.BlockHunt.Managers.MessageManager;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDsetwarp extends DefaultCMD {

	@Override
	public boolean execute(Player player, Command cmd, String label, String[] args) {
		if (player != null) {
			if (args.length <= 2) {
				MessageManager.sendFMessage(player, ConfigC.error_notEnoughArguments, "syntax-" + BlockHunt.CMDsetwarp.usage);
			} else {
				String arenaname = args[2];
				String warpname = args[1];

				Arena arena = null;
				for (Arena arena2 : MemoryStorage.arenaList) {
					if (arena2.arenaName.equalsIgnoreCase(arenaname)) {
						arena = arena2;
					}
				}
				if (arena != null) {
					Location loc = player.getLocation();
					if (warpname.equalsIgnoreCase("lobby")) {
						arena.lobbyWarp = loc;
						save(arena);
						MessageManager.sendFMessage(player, ConfigC.normal_setwarpWarpSet, "warp-" + warpname);
					} else if (warpname.equalsIgnoreCase("hiders")) {
						arena.hidersWarp = loc;
						save(arena);
						MessageManager.sendFMessage(player, ConfigC.normal_setwarpWarpSet, "warp-" + warpname);
					} else if (warpname.equalsIgnoreCase("seekers")) {
						arena.seekersWarp = loc;
						save(arena);
						MessageManager.sendFMessage(player, ConfigC.normal_setwarpWarpSet, "warp-" + warpname);
					} else if (warpname.equalsIgnoreCase("spawn")) {
						arena.spawnWarp = loc;
						save(arena);
						MessageManager.sendFMessage(player, ConfigC.normal_setwarpWarpSet, "warp-" + warpname);
					} else {
						MessageManager.sendFMessage(player, ConfigC.error_setwarpWarpNotFound, "warp-" + warpname);
					}
				} else {
					MessageManager.sendFMessage(player, ConfigC.error_noArena, "name-" + arenaname);
				}
			}
		} else {
			MessageManager.sendFMessage(player, ConfigC.error_onlyIngame);
		}
		return true;
	}

	public void save(Arena arena) {
		MemoryStorage.arenas.getFile().set(arena.arenaName, arena);
		MemoryStorage.arenas.save();
		ArenaHandler.loadArenas();
	}
}
