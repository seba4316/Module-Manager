package me.seba4316.utilities.module;

import java.util.ArrayList;
import java.util.List;

import me.seba4316.utilities.event.events.EventPostMotionUpdate; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.EventPreMotionUpdate; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.PacketSentEvent; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.PacketReceivedEvent; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.PacketSentEvent; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.Render2d; // Can be found on my GitHub: https://github.com/seba4316/Events
import me.seba4316.utilities.event.events.Render3d; // Can be found on my GitHub: https://github.com/seba4316/Events

import net.minecraft.network.Packet;

public class ModuleManager {

	private List<Module> modules;

	public ModuleManager() {
		this.modules = new ArrayList<Module>();
	}

	public Module registerModule(Module module) {
		modules.add(module);
		module.setup();
		return getModule(module.getName());
	}

	public Module getModule(Class<? extends Module> clasz) {
		for (int i = 0; i < modules.size(); i++) {
			Module module = modules.get(i);
			if (module.getClass() == clasz)
				return module;
		}
		return null;
	}

	public Module getModule(String name) {
		for (int i = 0; i < modules.size(); i++) {
			if (modules.get(i).getName().equalsIgnoreCase(name))
				return modules.get(i);
		}
		return null;
	}

	public Module getModule(int key) {
		for (int i = 0; i < modules.size(); i++) {
			if (modules.get(i).getKey() != key)
				continue;
			return modules.get(i);
		}
		return null;
	}

	public void toggle(int key) {
		Module module = getModule(key);
		if (module == null)
			return;
		module.toggle();
	}

	public int setKey(String moduleName, int key) {
		Module module = getModule(moduleName);
		if (module == null)
			return 0;
		module.setKey(key);
		return key;
	}

	public List<Module> getEnabledModules() {
		List<Module> modules = new ArrayList<Module>(this.modules);
		List<Module> enabledModules = new ArrayList<Module>();
		for (int i = 0; i < modules.size(); i++) {
			Module module = modules.get(i);
			if (!(module.isToggled()))
				continue;
			enabledModules.add(module);
		}
		return enabledModules;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void onUpdate() {
		List<Module> enabledModules = new ArrayList<Module>(getEnabledModules());
		for (int i = 0; i < enabledModules.size(); i++) {
			Module module = enabledModules.get(i);
			module.onUpdate();
		}
	}

	public void onRender2d(Render2d Render2d) {
		List<Module> enabledModules = new ArrayList<Module>(getEnabledModules());
		for (int i = 0; i < enabledModules.size(); i++) {
			Module module = enabledModules.get(i);
			module.onRender2d(Render2d);
		}
	}

	public void onPreMotionUpdate(EventPreMotionUpdate eventPreMotionUpdate) {
		List<Module> enabledModules = new ArrayList<Module>(getEnabledModules());
		for (int i = 0; i < enabledModules.size(); i++) {
			Module module = enabledModules.get(i);
			module.onPreMotion(eventPreMotionUpdate);
		}
	}

	public void onPostMotion(EventPostMotionUpdate eventPostMotionUpdate) {
		List<Module> enabledModules = new ArrayList<Module>(getEnabledModules());
		for (int i = 0; i < enabledModules.size(); i++) {
			Module module = enabledModules.get(i);
			module.onPostMotion(eventPostMotionUpdate);
		}
	}

	public void onRender3d(Render3d render3d) {
		List<Module> enabledModules = new ArrayList<Module>(getEnabledModules());
		for (int i = 0; i < enabledModules.size(); i++) {
			Module module = enabledModules.get(i);
			module.onRender3d(render3d);
		}
	}

	public void onPacketSent(PacketSentEvent event, Packet packet) {
		List<Module> enabledModules = new ArrayList<Module>(getEnabledModules());
		for (int i = 0; i < enabledModules.size(); i++) {
			Module module = enabledModules.get(i);
			module.onPacketSent(event, packet);
		}
	}

	public void onPacketReceived(PacketReceivedEvent event, Packet packet) {
		List<Module> enabledModules = new ArrayList<Module>(getEnabledModules());
		for (int i = 0; i < enabledModules.size(); i++) {
			Module module = enabledModules.get(i);
			module.onPacketReceived(event, packet);
		}
	}

}