package com.kisman.cc.module.movement;

import com.kisman.cc.Kisman;
import com.kisman.cc.module.Category;
import com.kisman.cc.module.Module;
import com.kisman.cc.settings.Setting;

public class ReverseStep extends Module {
    public ReverseStep() {
        super("ReverseStep", "", Category.MOVEMENT);
        Kisman.instance.settingsManager.rSetting(new Setting("Height", this, 1.0, 0.5, 2.5, false));
    }

    public void update() {
        double height = Kisman.instance.settingsManager.getSettingByName(this, "Height").getValDouble();

        if (mc.world == null || mc.player == null || mc.player.isInWater() || mc.player.isInLava() || mc.player.isOnLadder()
                || mc.gameSettings.keyBindJump.isKeyDown()) {
            return;
        }

        //if (ModuleManager.isModuleEnabled(Speed.class)) return;

        if (mc.player != null && mc.player.onGround && !mc.player.isInWater() && !mc.player.isOnLadder()) {
            for (double y = 0.0; y < height + 0.5; y += 0.01) {
                if (!mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
                    mc.player.motionY = -10.0;
                    break;
                }
            }
        }
    }
}
