BlockHunt
====
# ![Thanks to ColeRule for making this awesome banner!](http://g2f.nl/0qheg35 "Thanks to ColeRule for making this awesome banner!") 

## Introduction
Hide and seek is a popular game mode on Minecraft that allows you to hide as blocks and disguise as normal props from seekers. This plugin allows you to do that but with a lot more features added on such as: shops, stats etc...

This plugin has been heavily inspired from the gamemode "PropHunt", from the game, "Garry's Mod".

**Download and try this plugin out for yourself!**

### Downloads

| Branch               | Plugin version          | Tested Minecraft version | Download |
| ---                  | ---                     | ---                      | ---      |
| master (rt50631 fork)| **v0.3.0-Snapshot**     | **v1.12**                | [DOWNLOAD](http://s000.tinyupload.com/index.php?file_id=00810016419868876516) |

#### This plugin requires you to download and install two addinonal plugins! ([Lib's Disguises](https://www.spigotmc.org/resources/libs-disguises.81/) and [ProtocolLib](http://dev.bukkit.org/bukkit-plugins/protocollib/))

### Support

 |Method|Link|
 | --- | --- |
 | **Issues/Tickets on GitHub** | [Click Here](https://github.com/AddstarMC/BlockHunt/issues/) |

### Features
* Custom wand for selection arena.
* Multiple arenas.
* **Join/Leave signs!**
* Arena full bypass.
* Configurable blocks.
* **Solid blocks like the Hives!**
* Enable commands per arena.
* Executing commands on win.
* **Shop with tokens!**
* Instant respawn.
* **_And more!_**

##Commands & Permissions
_**Note:** Instead of using /BlockHunt you could use:_
* /bh
* /hideandseek
* /seekandfind (from my old plugin)

<> = Required [] = Optional

|Command|Description|Permission||
|:--|:--|:--|:--|
|`/BlockHunt [info/i]`|Displays the plugin's info.|blockhunt.info|_All players have this permission from default._|
|`/BlockHunt <help/h>`|Shows a list of commands.|blockhunt.help|_All players have this permission from default._|
|`/BlockHunt <reload/r>`|Reloads all configs.|blockhunt.reload|blockhunt.admin|
|`/BlockHunt <join/j> <arenaname>`|Joins a BlockHunt game.|blockhunt.join|blockhunt.player|
|`/BlockHunt <leave/l>`|Leave a BlockHunt game.|blockhunt.leave|blockhunt.player|
|`/BlockHunt <list/li>`|Shows a list of available arenas.|blockhunt.list|blockhunt.player|
|`/BlockHunt <shop/sh>`|Opens the BlockHunt shop.|blockhunt.shop|blockhunt.player|
|`/BlockHunt <start/go> <arenaname>`|Forces an arena to start.|blockhunt.start|blockhunt.moderator|
|`/BlockHunt <wand/w>`|Gives you the wand selection tool.|blockhunt.create|blockhunt.admin|
|`/BlockHunt <create/c> <arenaname>`|Creates an arena from your selection.|blockhunt.create|blockhunt.admin|
|`/BlockHunt <set/s> <arenaname>`|Opens a panel to set settings.|blockhunt.set|blockhunt.moderator|
|`/BlockHunt <setwarp/sw> <lobby/hiders/seekers/spawn> <arenaname>`|Sets warps for your arena.|blockhunt.setwarp|blockhunt.moderator|
|`/BlockHunt <remove/delete> <arenaname>`|Deletes an Arena.|blockhunt.remove|blockhunt.admin|
|`/BlockHunt <tokens/t> <set/add/take> <playername> <amount>`|Change someones tokens.|blockhunt.tokens|blockhunt.admin|
||Able to join full games.|blockhunt.joinfull|blockhunt.moderator|
||Able to join/leave using join/leave signs.|blockhunt.joinsign|blockhunt.player|
||Able to create a join/leave sign.|blockhunt.signcreate|blockhunt.moderator|
||Gives you the BlockChooser.|blockhunt.shop.blockchooser|blockhunt.admin|
||Gives you the ability to do all commands in-game.|blockhunt.allcommands|Operators|

###Other special permissions
|Permission|Description|
|:--|:--|
|`blockhunt.*`|All BlockHunt permissions.|
|`blockhunt.player`|All player related permissions.|
|`blockhunt.moderator`|All moderator related permissions.|
|`blockhunt.admin`|All admin related permissions.|
|`*`|All permissions on your server, including BlockHunt's permissions.|

##How to set-up?
Use this video to manage your way through the set-up phase!

<a href="http://www.youtube.com/watch?feature=player_embedded&v=msPQ1UMiHWg
" target="_blank"><img src="http://img.youtube.com/vi/msPQ1UMiHWg/0.jpg" 
alt="" width="240" height="180" border="10" /></a>

**Thanks to [Koz4Christ](https://www.youtube.com/user/koz4christ/) for this video!**
