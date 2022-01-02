package com.kisman.cc.module.movement;

import com.kisman.cc.Kisman;
import com.kisman.cc.module.Category;
import com.kisman.cc.module.Module;

import com.kisman.cc.settings.Setting;
import i.gishreloaded.gishcode.wrappers.Wrapper;

public class Spider extends Module{
	
	public Spider() {
		super("Spider", "HackCategory.PLAYER", Category.MOVEMENT);

		Kisman.instance.settingsManager.rSetting(new Setting("voidsetting", this, "void", "setting"));
	}
	
	public void update() {
		if(mc.world != null && mc.player != null) {
			if(!Wrapper.INSTANCE.player().isOnLadder() 
        		&& Wrapper.INSTANCE.player().collidedHorizontally 
        		&& Wrapper.INSTANCE.player().motionY < 0.2) {
        		Wrapper.INSTANCE.player().motionY = 0.2;
        	}
		}
	}
}
