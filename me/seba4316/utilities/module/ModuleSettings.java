package me.seba4316.utilities.module;

import java.io.File;

import me.seba4316.utilities.config.Config; // Coming soon on GitHub.
import me.seba4316.utilities.config.Files; // Coming soon on GitHub.
import me.seba4316.utilities.generic.Lists; // Can be found on my GitHub: https://github.com/seba4316/Generic-Utils

public class ModuleSettings {

	private Module module;
	private Config config;

	public ModuleSettings(Module module) {
		this.module = module;

		/** This defines the path for the module.
		* If the path contains "jars" then it's a workspace for hacks, and 2 chars must be removed at the end of the path or it will give errors
		**/
		String path = (path.contains("jars") ? new File(".").getAbsolutePath().substring(0, path.length() - 2) : new File(".").getAbsolutePath());

		String clientName = "";

		if (!(Files.fileExists(path, clientName + "\\")))
			Files.createDirectory(path, clientName);
		if (!(Files.fileExists(path, clientName + "\\modules")))
			Files.createDirectory(path, clientName + "\\modules");
		if (!(Files.fileExists(path, clientName + "\\modules\\" + module.getCategory().toString().toLowerCase())))
			Files.createDirectory(path, clientName + "\\modules\\" + module.getCategory().toString().toLowerCase());

		this.config = new Config(path, clientName + "\\modules\\" + module.getCategory().toString().toLowerCase() + "\\" + module.getName() + ".cfg");
		config.addDefault("enabled", false);
		config.addDefault("key", module.getKey());
		config.addDefault("anticheat", "vanilla");
		config.generateConfigs();
	}

	public Module getModule() {
		return module;
	}

	public Config getConfig() {
		return config;
	}

	public void generateConfigs() {
		config.generateConfigs();
	}

	public void addDefault(String key, String value) {
		config.addDefault(key.toLowerCase(), value);
	}

	public void addDefault(String key, boolean value) {
		config.addDefault(key.toLowerCase(), value);
	}

	public void addDefault(String key, int value) {
		config.addDefault(key.toLowerCase(), value);
	}

	public void addDefault(String key, char value) {
		config.addDefault(key.toLowerCase(), value);
	}

	public void addDefault(String key, double value) {
		config.addDefault(key.toLowerCase(), value);
	}

	public void addDefault(String key, float value) {
		config.addDefault(key.toLowerCase(), value);
	}

	public void set(String key, Object value) {
		config.set(key.toLowerCase(), value);
	}

	public String getString(String key) {
		return config.getString(key.toLowerCase());
	}

	public boolean getBoolean(String key) {
		return config.getBoolean(key.toLowerCase());
	}

	public double getDouble(String key) {
		return config.getDouble(key.toLowerCase());
	}

	public float getFloat(String key) {
		return config.getFloat(key.toLowerCase());
	}

	public Object getObject(String key) {
		return config.getObject(key.toLowerCase());
	}

	public int getInt(String key) {
		return config.getInt(key.toLowerCase());
	}

}