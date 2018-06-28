package nl.Steffion.BlockHunt.Managers;
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
import java.io.File;

import nl.Steffion.BlockHunt.BlockHunt;
import nl.Steffion.BlockHunt.ConfigC;
import nl.Steffion.BlockHunt.MemoryStorage;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {


	private String fileName;
	private File file;
	private FileConfiguration fileC;
	@SuppressWarnings("unused")
	private ConfigurationSection fileCS;
	@SuppressWarnings("unused")
	private File fileLocation;

	/**
	 * Use this class to create an automated config file.
	 * 
	 * @param fileName
	 *            Name of the file.
	 */
	public ConfigManager(String fileName) {
		this.fileName = fileName;
		this.file = new File(BlockHunt.plugin.getDataFolder(), fileName + ".yml");
		this.fileLocation = BlockHunt.plugin.getDataFolder();
		this.fileC = new YamlConfiguration();
		this.checkFile();
		this.fileCS = fileC.getConfigurationSection("");
		this.load();
	}

	/**
	 * Use this class to create an automated config file.
	 * 
	 * @param fileName
	 *            Name of the file.
	 * @param subdirectory
	 *            Sub-Location of the file.
	 */
	public ConfigManager(String fileName, String subdirectory) {
		this.fileName = fileName;
		File directory = new File( BlockHunt.plugin.getDataFolder() , subdirectory );
		this.file = new File( directory,fileName + ".yml");
		this.fileLocation = directory;
		this.fileC = new YamlConfiguration();
		this.checkFile();
		this.fileCS = fileC.getConfigurationSection("");
		this.load();
	}

	/**
	 * Check if there are new files created if so, display a message to the
	 * console.
	 */
	public static void newFiles() {
		ConfigManager.setDefaults();
		for (String fileName : MemoryStorage.newFiles) {
			MessageManager.sendMessage(null, "%TAG%WCouldn't find '%A%fileName%.yml%MemoryStorage' creating new one.", "fileName-" + fileName);
		}

		MemoryStorage.newFiles.clear();
	}

	/**
	 * Add config settings to the files if they don't exist.
	 */
	public static void setDefaults() {
		for (ConfigC value : ConfigC.values()) {
			value.config.load();
			if (value.config.getFile().get(value.location) == null) {
				value.config.getFile().set(value.location, value.value);
				value.config.save();
			}
		}
	}

	/**
	 * Check if file exists, if not create one.
	 */
	public void checkFile() {
		if (!this.file.exists()) {
			try {
				this.file.getParentFile().mkdirs();
				this.file.createNewFile();
				if(file.exists()) {
					MemoryStorage.newFiles.add(this.fileName);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Save the file.
	 */
	public void save() {
		try {
			this.fileC.save(this.file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the file.
	 */
	public void load() {
		this.checkFile();
		if (this.file.exists()) {
			try {
				this.fileC.load(this.file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get the File. Very useful for just loading/saving.
	 */
	public FileConfiguration getFile() {
		return this.fileC;
	}

	/**
	 * Get object from a Config.
	 * 
	 * @param location
	 *            Config location.
	 * @return Object
	 */
	public Object get(ConfigC location) {
		return this.getFile().get(location.location);
	}
}