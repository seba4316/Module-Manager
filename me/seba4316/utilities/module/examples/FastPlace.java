package me.sappy.client.module.modules.world;

import me.seba4316.utilities.module.Category;
import me.seba4316.utilities.module.Module;

public class FastPlace extends Module {

	public FastPlace() {
		super("fastplace", "FastPlace", Category.WORLD);
	}
	
	@Override
	public void onUpdate() {
		player.setRightDelayTimer(0);
	}
	
	@Override
	public void onDisable() {
		player.setRightDelayTimer(4);
	}
	
}