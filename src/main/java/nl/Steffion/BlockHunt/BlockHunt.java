package nl.Steffion.BlockHunt;

import java.util.ArrayList;
import java.util.List;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import nl.Steffion.BlockHunt.Arena.ArenaState;
import nl.Steffion.BlockHunt.PermissionsC.Permissions;
import nl.Steffion.BlockHunt.Commands.CMDcreate;
import nl.Steffion.BlockHunt.Commands.CMDhelp;
import nl.Steffion.BlockHunt.Commands.CMDinfo;
import nl.Steffion.BlockHunt.Commands.CMDjoin;
import nl.Steffion.BlockHunt.Commands.CMDleave;
import nl.Steffion.BlockHunt.Commands.CMDlist;
import nl.Steffion.BlockHunt.Commands.CMDnotfound;
import nl.Steffion.BlockHunt.Commands.CMDreload;
import nl.Steffion.BlockHunt.Commands.CMDremove;
import nl.Steffion.BlockHunt.Commands.CMDset;
import nl.Steffion.BlockHunt.Commands.CMDsetwarp;
import nl.Steffion.BlockHunt.Commands.CMDshop;
import nl.Steffion.BlockHunt.Commands.CMDstart;
import nl.Steffion.BlockHunt.Commands.CMDtokens;
import nl.Steffion.BlockHunt.Commands.CMDwand;
import nl.Steffion.BlockHunt.Listeners.OnBlockBreakEvent;
import nl.Steffion.BlockHunt.Listeners.OnBlockPlaceEvent;
import nl.Steffion.BlockHunt.Listeners.OnEntityDamageByEntityEvent;
import nl.Steffion.BlockHunt.Listeners.OnEntityDamageEvent;
import nl.Steffion.BlockHunt.Listeners.OnFoodLevelChangeEvent;
import nl.Steffion.BlockHunt.Listeners.OnInventoryClickEvent;
import nl.Steffion.BlockHunt.Listeners.OnInventoryCloseEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerDropItemEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerInteractEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerMoveEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerQuitEvent;
import nl.Steffion.BlockHunt.Listeners.OnSignChangeEvent;
import nl.Steffion.BlockHunt.Managers.CommandManager;
import nl.Steffion.BlockHunt.Managers.ConfigManager;
import nl.Steffion.BlockHunt.Managers.MessageManager;
import nl.Steffion.BlockHunt.Managers.PermissionsManager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockHunt extends JavaPlugin implements Listener {
	/**
	 * Steffion's Engine - Made by Steffion.
	 *
	 * You're allowed to use this engine for own usage, you're not allowed to
	 * republish the engine. Using this for your own plugin is allowed when a
	 * credit is placed somewhere in the plugin.
	 *
	 * Thanks for your cooperate!
	 *
	 * @author Steffion
	 */

	public static PluginDescriptionFile pdfFile;
	public static BlockHunt plugin;

	@SuppressWarnings("serial")
	public static List<String> BlockHuntCMD = new ArrayList<String>() {
		{
			add("info");
			add("help");
			add("reload");
			add("join");
			add("leave");
			add("list");
			add("shop");
			add("start");
			add("wand");
			add("create");
			add("set");
			add("setwarp");
			add("remove");
			add("tokens");
		}
	};

	public static CommandManager CMD;
	public static CommandManager CMDinfo;
	public static CommandManager CMDhelp;
	public static CommandManager CMDreload;
	public static CommandManager CMDjoin;
	public static CommandManager CMDleave;
	public static CommandManager CMDlist;
	public static CommandManager CMDshop;
	public static CommandManager CMDstart;
	public static CommandManager CMDwand;
	public static CommandManager CMDcreate;
	public static CommandManager CMDset;
	public static CommandManager CMDsetwarp;
	public static CommandManager CMDremove;
	public static CommandManager CMDtokens;

	@SuppressWarnings("deprecation")
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);

		getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(), this);
		getServer().getPluginManager().registerEvents(new OnBlockPlaceEvent(), this);
		getServer().getPluginManager().registerEvents(new OnEntityDamageByEntityEvent(), this);
		getServer().getPluginManager().registerEvents(new OnEntityDamageEvent(), this);
		getServer().getPluginManager().registerEvents(new OnFoodLevelChangeEvent(), this);
		getServer().getPluginManager().registerEvents(new OnInventoryClickEvent(), this);
		getServer().getPluginManager().registerEvents(new OnInventoryCloseEvent(), this);

		// Removed - This is handled by WorldGuard now.
		// getServer().getPluginManager().registerEvents(
		// new OnPlayerCommandPreprocessEvent(), this);

		getServer().getPluginManager().registerEvents(new OnPlayerDropItemEvent(), this);
		getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(), this);
		getServer().getPluginManager().registerEvents(new OnPlayerMoveEvent(), this);
		getServer().getPluginManager().registerEvents(new OnPlayerQuitEvent(), this);
		getServer().getPluginManager().registerEvents(new OnSignChangeEvent(), this);

		ConfigurationSerialization.registerClass(Location.class, "BlockHuntLocation");
		ConfigurationSerialization.registerClass(Arena.class, "BlockHuntArena");

		pdfFile = getDescription();
		plugin = this;

		ConfigManager.newFiles();

		CMD = new CommandManager("BlockHunt", "BlockHunt", null, null, Permissions.info, ConfigC.help_info, (Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_info), BlockHuntCMD,
				new CMDinfo(), null);
		CMDinfo = new CommandManager("BlockHunt INFO", "BlockHunt", "info", "i", Permissions.info, ConfigC.help_info, (Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_info),
				BlockHuntCMD, new CMDinfo(), "/BlockHunt [info|i]");
		CMDhelp = new CommandManager("BlockHunt HELP", "BlockHunt", "help", "h", Permissions.help, ConfigC.help_help, (Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_help),
				BlockHuntCMD, new CMDhelp(), "/BlockHunt <help|h> [page number]");
		CMDreload = new CommandManager("BlockHunt RELOAD", "BlockHunt", "reload", "r", Permissions.reload, ConfigC.help_reload,
				(Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_reload), BlockHuntCMD, new CMDreload(), "/BlockHunt <reload|r>");
		CMDjoin = new CommandManager("BlockHunt JOIN", "BlockHunt", "join", "j", Permissions.join, ConfigC.help_join, (Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_join),
				BlockHuntCMD, new CMDjoin(), "/BlockHunt <join|j> <arenaname>");
		CMDleave = new CommandManager("BlockHunt LEAVE", "BlockHunt", "leave", "l", Permissions.leave, ConfigC.help_leave,
				(Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_leave), BlockHuntCMD, new CMDleave(), "/BlockHunt <leave|l>");
		CMDlist = new CommandManager("BlockHunt LIST", "BlockHunt", "list", "li", Permissions.list, ConfigC.help_list, (Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_list),
				BlockHuntCMD, new CMDlist(), "/BlockHunt <list|li>");
		CMDshop = new CommandManager("BlockHunt SHOP", "BlockHunt", "shop", "sh", Permissions.shop, ConfigC.help_shop, (Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_shop),
				BlockHuntCMD, new CMDshop(), "/BlockHunt <shop|sh>");
		CMDstart = new CommandManager("BlockHunt START", "BlockHunt", "start", "go", Permissions.start, ConfigC.help_start,
				(Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_start), BlockHuntCMD, new CMDstart(), "/BlockHunt <start|go> <arenaname>");
		CMDwand = new CommandManager("BlockHunt WAND", "BlockHunt", "wand", "w", Permissions.create, ConfigC.help_wand, (Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_wand),
				BlockHuntCMD, new CMDwand(), "/BlockHunt <wand|w>");
		CMDcreate = new CommandManager("BlockHunt CREATE", "BlockHunt", "create", "c", Permissions.create, ConfigC.help_create,
				(Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_create), BlockHuntCMD, new CMDcreate(), "/BlockHunt <create|c> <arenaname>");
		CMDset = new CommandManager("BlockHunt SET", "BlockHunt", "set", "s", Permissions.set, ConfigC.help_set, (Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_set),
				BlockHuntCMD, new CMDset(), "/BlockHunt <set|s> <arenaname>");
		CMDsetwarp = new CommandManager("BlockHunt SETWARP", "BlockHunt", "setwarp", "sw", Permissions.setwarp, ConfigC.help_setwarp,
				(Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_setwarp), BlockHuntCMD, new CMDsetwarp(),
				"/BlockHunt <setwarp|sw> <lobby|hiders|seekers|spawn> <arenaname>");
		CMDremove = new CommandManager("BlockHunt REMOVE", "BlockHunt", "remove", "delete", Permissions.remove, ConfigC.help_remove,
				(Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_remove), BlockHuntCMD, new CMDremove(), "/BlockHunt <remove|delete> <arenaname>");
		CMDtokens = new CommandManager("BlockHunt TOKENS", "BlockHunt", "tokens", "t", Permissions.tokens, ConfigC.help_tokens,
				(Boolean) MemoryStorage.config.get(ConfigC.commandEnabled_tokens), BlockHuntCMD, new CMDtokens(), "/BlockHunt <tokens|t> <set|add|take> <playername> <amount>");

		if (!getServer().getPluginManager().isPluginEnabled("LibsDisguises")) {
			MessageManager.broadcastFMessage(ConfigC.error_libsDisguisesNotInstalled);
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
		}

		if (!getServer().getPluginManager().isPluginEnabled("ProtocolLib")) {
			MessageManager.broadcastFMessage(ConfigC.error_protocolLibNotInstalled);
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
		}

		ArenaHandler.loadArenas();

		MessageManager.sendFMessage(null, ConfigC.log_enabledPlugin, "name-" + BlockHunt.pdfFile.getName(), "version-" + BlockHunt.pdfFile.getVersion(), "autors-"
				+ BlockHunt.pdfFile.getAuthors().get(0));

		// Welcome to the massive game loop!!
		getServer().getScheduler().runTaskTimer(this, () -> {
            for (Arena arena : MemoryStorage.arenaList) {
                if (arena.gameState == ArenaState.WAITING) {
                    if (arena.playersInArena.size() >= arena.minPlayers) {
                        arena.gameState = ArenaState.STARTING;
                        arena.timer = arena.timeInLobbyUntilStart;
                        ArenaHandler.sendFMessage(arena, ConfigC.normal_lobbyArenaIsStarting, "1-" + arena.timeInLobbyUntilStart);
                    }
                } else if (arena.gameState == ArenaState.STARTING) {
                    arena.timer = arena.timer - 1;
                    if (arena.timer > 0) {
                        if (arena.timer == 60) {
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_lobbyArenaIsStarting, "1-60");
                        } else if (arena.timer == 30) {
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_lobbyArenaIsStarting, "1-30");
                        } else if (arena.timer == 10) {
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_lobbyArenaIsStarting, "1-10");
                        } else if (arena.timer == 5) {
                            arena.lobbyWarp.getWorld().playSound(arena.lobbyWarp, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_lobbyArenaIsStarting, "1-5");
                        } else if (arena.timer == 4) {
                            arena.lobbyWarp.getWorld().playSound(arena.lobbyWarp, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_lobbyArenaIsStarting, "1-4");
                        } else if (arena.timer == 3) {
                            arena.lobbyWarp.getWorld().playSound(arena.lobbyWarp, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_lobbyArenaIsStarting, "1-3");
                        } else if (arena.timer == 2) {
                            arena.lobbyWarp.getWorld().playSound(arena.lobbyWarp, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_lobbyArenaIsStarting, "1-2");
                        } else if (arena.timer == 1) {
                            arena.lobbyWarp.getWorld().playSound(arena.lobbyWarp, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_lobbyArenaIsStarting, "1-1");
                        }
                    } else {
                        arena.gameState = ArenaState.INGAME;
                        arena.timer = arena.gameTime;
                        ArenaHandler.sendFMessage(arena, ConfigC.normal_lobbyArenaStarted, "secs-" + arena.waitingTimeSeeker);

                        for (int i = arena.amountSeekersOnStart; i > 0; i = i - 1) {
                            boolean loop = true;
                            Player seeker = arena.playersInArena.get(MemoryStorage.random.nextInt(arena.playersInArena.size()));

                            for (Player playerCheck : arena.playersInArena) {
                                if (MemoryStorage.choosenSeeker.get(playerCheck) != null) {
                                    if (MemoryStorage.choosenSeeker.get(playerCheck)) {
                                        seeker = playerCheck;
                                        MemoryStorage.choosenSeeker.remove(playerCheck);
                                    } else {
                                        if (seeker.equals(playerCheck)) {
                                            i = i + 1;
                                            loop = false;
                                        }
                                    }
                                }
                            }

                            if (loop) {
                                if (!arena.seekers.contains(seeker)) {
                                    ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameSeekerChoosen, "seeker-" + seeker.getName());
                                    arena.seekers.add(seeker);
                                    seeker.teleport(arena.seekersWarp);
                                    seeker.getInventory().clear();
                                    seeker.updateInventory();
                                    seeker.setWalkSpeed(0.3F);
                                    MemoryStorage.seekertime.put(seeker, arena.waitingTimeSeeker);
                                } else {
                                    i = i + 1;
                                }
                            }
                        }

                        for (Player arenaPlayer : arena.playersInArena) {
                            if (!arena.seekers.contains(arenaPlayer)) {
                                arenaPlayer.getInventory().clear();
                                arenaPlayer.updateInventory();
                                ItemStack block = arena.disguiseBlocks.get(MemoryStorage.random.nextInt(arena.disguiseBlocks.size()));

                                if (MemoryStorage.choosenBlock.get(arenaPlayer) != null) {
                                    block = MemoryStorage.choosenBlock.get(arenaPlayer);
                                    MemoryStorage.choosenBlock.remove(arenaPlayer);
                                }

                                MiscDisguise disguise = new MiscDisguise(DisguiseType.FALLING_BLOCK, block.getTypeId(), block.getDurability());
                                DisguiseAPI.disguiseToAll(arenaPlayer, disguise);

                                arenaPlayer.teleport(arena.hidersWarp);

                                ItemStack blockCount = new ItemStack(block.getType(), 5);
                                blockCount.setDurability(block.getDurability());
                                arenaPlayer.getInventory().setItem(8, blockCount);
                                arenaPlayer.getInventory().setHelmet(new ItemStack(block));
                                MemoryStorage.pBlock.put(arenaPlayer, block);

                                if (block.getDurability() != 0) {
                                    MessageManager.sendFMessage(arenaPlayer, ConfigC.normal_ingameBlock,
                                            "block-" + block.getType().name().replaceAll("_", "").replaceAll("BLOCK", "").toLowerCase() + ":" + block.getDurability());
                                } else {
                                    MessageManager.sendFMessage(arenaPlayer, ConfigC.normal_ingameBlock,
                                            "block-" + block.getType().name().replaceAll("_", "").replaceAll("BLOCK", "").toLowerCase());
                                }
                            }
                        }
                    }
                }

                for (Player player : arena.seekers) {
                    if (player.getInventory().getItem(0) == null || player.getInventory().getItem(0).getType() != Material.DIAMOND_SWORD) {
                        ItemStack i = new ItemStack(Material.DIAMOND_SWORD, 1);
                        i.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
                        player.getInventory().setItem(0, i);
                        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));
                        ItemStack infBow = new ItemStack(Material.BOW, 1);
                        infBow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                        player.getInventory().setItem(1, infBow);
                        player.getInventory().setItem(2, new ItemStack(Material.ARROW, 1));
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                    }

                    if (MemoryStorage.seekertime.get(player) != null) {
                        MemoryStorage.seekertime.put(player, MemoryStorage.seekertime.get(player) - 1);
                        if (MemoryStorage.seekertime.get(player) <= 0) {
                            player.teleport(arena.hidersWarp);
                            MemoryStorage.seekertime.remove(player);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameSeekerSpawned, "playername-" + player.getName());
                        }
                    }
                }

                if (arena.gameState == ArenaState.INGAME) {
                    arena.timer = arena.timer - 1;
                    if (arena.timer > 0) {
                        if (arena.timer == arena.gameTime - arena.timeUntilHidersSword) {
                            ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
                            sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
                            for (Player arenaPlayer : arena.playersInArena) {
                                if (!arena.seekers.contains(arenaPlayer)) {
                                    arenaPlayer.getInventory().addItem(sword);
                                    MessageManager.sendFMessage(arenaPlayer, ConfigC.normal_ingameGivenSword);
                                }
                            }
                        }

                        // blockAnnouncer code.
                        if ((arena.blockAnnouncerTime > 0) && (arena.timer == arena.blockAnnouncerTime)) {
                            ArrayList<String> remainingBlocks = new ArrayList<>();
                            for (Player arenaPlayer : arena.playersInArena) {
                                if (!arena.seekers.contains(arenaPlayer)) {
                                    String block = arenaPlayer.getInventory().getItem(8).getType().name();
                                    block = WordUtils.capitalizeFully(block.replace("_", " "));
                                    if (!remainingBlocks.contains(block)) { //Don't print double up block names.
                                        remainingBlocks.add(block);
                                    }
                                }
                            }
                            String blocklist = StringUtils.join(remainingBlocks, ", ");
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameBlocksLeft, "1-" + blocklist);
                        }

                        if (arena.timer == 190) {
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameArenaEnd, "1-190");
                        } else if (arena.timer == 60) {
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameArenaEnd, "1-60");
                        } else if (arena.timer == 30) {
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameArenaEnd, "1-30");
                        } else if (arena.timer == 10) {
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameArenaEnd, "1-10");
                        } else if (arena.timer == 5) {
                            arena.lobbyWarp.getWorld().playSound(arena.lobbyWarp, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameArenaEnd, "1-5");
                        } else if (arena.timer == 4) {
                            arena.lobbyWarp.getWorld().playSound(arena.lobbyWarp, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameArenaEnd, "1-4");
                        } else if (arena.timer == 3) {
                            arena.lobbyWarp.getWorld().playSound(arena.lobbyWarp, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameArenaEnd, "1-3");
                        } else if (arena.timer == 2) {
                            arena.lobbyWarp.getWorld().playSound(arena.lobbyWarp, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameArenaEnd, "1-2");
                        } else if (arena.timer == 1) {
                            arena.lobbyWarp.getWorld().playSound(arena.lobbyWarp, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
                            ArenaHandler.sendFMessage(arena, ConfigC.normal_ingameArenaEnd, "1-1");
                        }
                    } else {
                        ArenaHandler.hidersWin(arena);
                        return;
                    }

                    for (Player player : arena.playersInArena) {
                        if (!arena.seekers.contains(player)) {
                            Location pLoc = player.getLocation();
                            Location moveLoc = MemoryStorage.moveLoc.get(player);
                            ItemStack block = player.getInventory().getItem(8);

                            if (block == null) {
                                if (MemoryStorage.pBlock.get(player) != null) {
                                    block = MemoryStorage.pBlock.get(player);
                                    player.getInventory().setItem(8, block);
                                    player.updateInventory();
                                }
                            }

                            if (moveLoc != null) {
                                if (moveLoc.getX() == pLoc.getX() && moveLoc.getY() == pLoc.getY() && moveLoc.getZ() == pLoc.getZ()) {
                                    if (block.getAmount() > 1) {
                                        block.setAmount(block.getAmount() - 1);
                                    } else {
                                        Block pBlock = player.getLocation().getBlock();
                                        if (pBlock.getType().equals(Material.AIR) || pBlock.getType().equals(Material.WATER)
                                                || pBlock.getType().equals(Material.STATIONARY_WATER)) {
                                            if (pBlock.getType().equals(Material.WATER) || pBlock.getType().equals(Material.STATIONARY_WATER)) {
                                                MemoryStorage.hiddenLocWater.put(player, true);
                                            } else {
                                                MemoryStorage.hiddenLocWater.put(player, false);
                                            }
                                            if (DisguiseAPI.isDisguised(player)) {
                                                DisguiseAPI.undisguiseToAll(player);
                                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                                    if (!pl.equals(player)) {
                                                        pl.hidePlayer(player);
                                                        pl.sendBlockChange(pBlock.getLocation(), block.getType(), (byte) block.getDurability());
                                                    }
                                                }

                                                block.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                                                player.playSound(pLoc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                                                MemoryStorage.hiddenLoc.put(player, moveLoc);
                                                if (block.getDurability() != 0) {
                                                    MessageManager.sendFMessage(
                                                            player,
                                                            ConfigC.normal_ingameNowSolid,
                                                            "block-" + block.getType().name().replaceAll("_", "").replaceAll("BLOCK", "").toLowerCase() + ":"
                                                                    + block.getDurability());
                                                } else {
                                                    MessageManager.sendFMessage(player, ConfigC.normal_ingameNowSolid, "block-"
                                                            + block.getType().name().replaceAll("_", "").replaceAll("BLOCK", "").toLowerCase());
                                                }
                                            }
                                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                                if (!pl.equals(player)) {
                                                    pl.hidePlayer(player);
                                                    pl.sendBlockChange(pBlock.getLocation(), block.getType(), (byte) block.getDurability());
                                                }
                                            }
                                        } else {
                                            MessageManager.sendFMessage(player, ConfigC.warning_ingameNoSolidPlace);
                                        }
                                    }
                                } else {
                                    block.setAmount(5);
                                    if (!DisguiseAPI.isDisguised(player)) {
                                        SolidBlockHandler.makePlayerUnsolid(player);
                                    }
                                }
                            }
                        }
                    }
                    ScoreboardHandler.updateScoreboard(arena); // TODO Only do this when needed (player added/removed)
                }

                for (Player pl : arena.playersInArena) {
                    pl.setLevel(arena.timer);
                    pl.setGameMode(GameMode.SURVIVAL);
                }
            }
            SignsHandler.updateSigns(); //TODO Only do this when needed (gamestate change or player count change)
        }, 0, 20);
	}

	public void onDisable() {
		for (Arena arena : MemoryStorage.arenaList) {
		    String cause = "[BlockHunt] Arena " + arena.arenaName + " has been stopped";
			ArenaHandler.stopArena(arena, cause, ConfigC.warning_arenaStopped);
		}

		MessageManager.sendFMessage(null, ConfigC.log_disabledPlugin, "name-" + BlockHunt.pdfFile.getName(), "version-" + BlockHunt.pdfFile.getVersion(), "autors-"
				+ BlockHunt.pdfFile.getAuthors().get(0));
	}

	/**
	 * Args to String. Makes 1 string.
	 * 
	 * @param input
	 *            String list which should be converted to a string.
	 * @param startArg
	 *            Start on this length.
	 * 
	 * @return The converted string.
	 */
	public static String stringBuilder(String[] input, int startArg) {
		if (input.length - startArg <= 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder(input[startArg]);
		for (int i = ++startArg; i < input.length; i++) {
			sb.append(' ').append(input[i]);
		}
		return sb.toString();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}

		for (CommandManager command : MemoryStorage.commands) {
			String[] argsSplit = null;
			String[] argsSplitAlias = null;

			if (command.args != null && command.argsalias != null) {
				argsSplit = command.args.split("/");
				argsSplitAlias = command.argsalias.split("/");
			}

			if (cmd.getName().equalsIgnoreCase(command.label)) {
				boolean equals = true;

				if (argsSplit == null) {
					equals = args.length == 0;
				} else {
					if (args.length >= argsSplit.length) {
						for (int i2 = argsSplit.length - 1; i2 >= 0; i2 = i2 - 1) {
							int loc = argsSplit.length - i2 - 1;
							if (!argsSplit[loc].equalsIgnoreCase(args[loc]) && !argsSplitAlias[loc].equalsIgnoreCase(args[loc])) {
								equals = false;
							}
						}
					} else {
						equals = false;
					}
				}

				if (equals) {
					if (PermissionsManager.hasPerm(player, command.permission, true)) {
						if (command.enabled) {
							command.CMD.execute(player, cmd, label, args);
						} else {
							MessageManager.sendFMessage(player, ConfigC.error_commandNotEnabled);
						}
					}

					return true;
				}
			}
		}
		new CMDnotfound().execute(player,cmd,label, args);;
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

		for (CommandManager command : MemoryStorage.commands) {
			if (cmd.getName().equalsIgnoreCase(command.label)) {
				if (args.length == 1) {
					return command.mainTABlist;
				}
			}
		}

		return null;
	}

	/**
	 * Short a String for like the Scoreboard title.
	 * 
	 * @param string
	 *            String to be shorten.
	 * @param maxLenght
	 *            Max lenght of the characters.
	 * @return Shorten string, else normal string.
	 */
	public static String cutString(String string, int maxLenght) {
		if (string.length() > maxLenght) {
			string = string.substring(0, maxLenght);
		}
		return string;
	}
}
