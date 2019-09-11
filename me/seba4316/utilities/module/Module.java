package me.seba4316.utilities.module;

import java.awt.Color;
import java.util.HashMap;

import EventManager; // The event manager has been created by Hexeption: https://github.com/Hexeption/Hex-s-Event-System (Maybe I will create one later on)
import me.seba4316.utilities.event.events.EventPostMotionUpdate; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.EventPreMotionUpdate; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.PacketReceivedEvent; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.PacketSentEvent; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.Render2d; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.Render3d; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.Lists; // Can be found on my GitHub: https://github.com/seba4316/Generic-Utils
import me.seba4316.utilities.generic.Player; // Can be found on my GitHub: https://github.com/seba4316/Generic-Utils
import me.seba4316.utilities.generic.Timer; // Can be found on my GitHub: https://github.com/seba4316/Generic-Utils

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;

public abstract class Module {

	private final String name;
	private final String displayName;
	private final Category category;
	private int key;
	private Module[] dependencies;
	private boolean enabled;
	protected Minecraft mc; // This is private instead of protected since I don't think you will create the commands under the me.seba4316.module package. Read the TOS in the GitHub Readme for more informations.
	protected EventManager eventManager; // This is private instead of protected since I don't think you will create the commands under the me.seba4316.module package. Read the TOS in the GitHub Readme for more informations.
	protected ModuleManager moduleManager; // This is private instead of protected since I don't think you will create the commands under the me.seba4316.module package. Read the TOS in the GitHub Readme for more informations.
	protected ModuleSettings settings; // This is private instead of protected since I don't think you will create the commands under the me.seba4316.module package. Read the TOS in the GitHub Readme for more informations.
	protected Timer timer; // This is private instead of protected since I don't think you will create the commands under the me.seba4316.module package. Read the TOS in the GitHub Readme for more informations.
	protected Player player; // This is private instead of protected since I don't think you will create the commands under the me.seba4316.module package. Read the TOS in the GitHub Readme for more informations.
	protected HashMap<String, Object> customInfo; /** Instead of using the settings for infos about the module that are temporary, change a lot, or don't need to be saved, use this (#addCustomInfo() and #getCustomInfo() */ // This is private instead of protected since I don't think you will create the commands under the me.seba4316.module package. Read the TOS in the GitHub Readme for more informations.

	public Module(String name, String displayName, Category category, int key, Module... dependencies) {
		this.name = name;
		this.displayName = displayName;
		this.category = category;
		this.settings = new ModuleSettings(this);
		this.settings.addDefault("key", key);
		this.dependencies = dependencies;
		this.enabled = settings.getBoolean("enabled");
		this.key = settings.getInt("key");
		this.mc = Minecraft.getMinecraft();
		this.player = new Player();
		this.eventManager = sappy.getEventManager();
		this.moduleManager = sappy.getModuleManager();
		this.timer = new Timer();
		this.customInfo = new HashMap<String, Object>();
		if (settings != null)
			settings.addDefault("anticheat", "vanilla");
	}

	public Module(String name, String displayName, Category category, Module... dependencies) {
		this.name = name;
		this.displayName = displayName;
		this.category = category;
		this.settings = new ModuleSettings(this);
		this.settings.addDefault("key", -123456);
		this.dependencies = dependencies;
		this.enabled = settings.getBoolean("enabled");
		this.key = settings.getInt("key");
		this.mc = Minecraft.getMinecraft();
		this.player = new Player();
		this.eventManager = sappy.getEventManager();
		this.moduleManager = sappy.getModuleManager();
		this.timer = new Timer();
		this.customInfo = new HashMap<String, Object>();
		if (settings != null)
			settings.addDefault("anticheat", "vanilla");
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Category getCategory() {
		return category;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
		settings.set("key", key);
	}

	public Module[] getDependencies() {
		return dependencies;
	}

	public boolean isToggled() {
		return enabled;
	}

	public boolean setEnabled(boolean enabled) {
		this.enabled = enabled;
		settings.set("enabled", enabled);
		if (enabled) {
			onEnable();
			/** Enable any dependency that the module is using */
			// TODO: Make it so the dependency can not be enabled manually if set so
			for (int i = 0; i < dependencies.length; i++) {
				Module dependency = dependencies[i];
				dependency.enabled = true;
			}
		} else {
			onDisable();
			/** Disable any dependency that the module is using */
			// TODO: Make it so the module can not be disabled if it has been enabled as a dependency
			for (int i = 0; i < dependencies.length; i++) {
				Module dependency = dependencies[i];
				dependency.enabled = false;
			}
		}
		return enabled;
	}

	public boolean toggle() {
		setEnabled(!(enabled));
		return enabled;
	}

	public ModuleSettings getSettings() {
		return settings;
	}

	public void setAntiCheat(AntiCheat antiCheat) {
		settings.set("anticheat", antiCheat);
	}

	public AntiCheat getAntiCheat() {
		return (settings.getString("anticheat") == null ? AntiCheat.VANILLA : AntiCheat.valueOf(settings.getString("anticheat").toUpperCase()));
	}

	public EntityPlayerSP getPlayer() {
		return mc.thePlayer;
	}

	public void addCustomInfo(String key, Object value) {
		customInfo.put(key, value);
	}

	public HashMap<String, Object> getCustomInfo() {
		return customInfo;
	}
	
	public Minecraft mc() {
		return mc;
	}
	
	public EventManager getEventManager() {
		return eventManager;
	}
	
	public ModuleManager getModuleManager() {
		return moduleManager;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Timer getTimer() {
		return timer;
	}

	public void setup() {
	}

	public void onEnable() {
	}

	public void onDisable() {
	}

	public void onEvent(Object... args) {
	}

	public void onUpdate() {
	}

	public void onPreMotion(EventPreMotionUpdate event) {
	}

	public void onPostMotion(EventPostMotionUpdate event) {
	}

	public void onRender2d(Render2d event) {
	}

	public void onRender3d(Render3d event) {
	}

	public void onPacketSent(PacketSentEvent event, Packet packet) {
	}

	public void onPacketReceived(PacketReceivedEvent event, Packet packet) {
	}

}