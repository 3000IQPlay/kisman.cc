package com.kisman.cc.module.combat;

import com.kisman.cc.Kisman;
import com.kisman.cc.module.Category;
import com.kisman.cc.module.Module;
import com.kisman.cc.settings.Setting;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

import java.util.ArrayList;
import java.util.Arrays;

public class Rubberband extends Module {
    private String mode;

    public Rubberband() {
        super("Rubberband", "Rubberband", Category.EXPLOIT);

        Kisman.instance.settingsManager.rSetting(new Setting("Mode", this, "Packet", new ArrayList<>(Arrays.asList("Motion", "Teleport", "Packet"))));
        Kisman.instance.settingsManager.rSetting(new Setting("Motion", this, 5, 1, 15, false));
    }

    public void onEnable() {
        if(mc.player == null && mc.world == null) return;
    }

    public void update() {
        this.mode = Kisman.instance.settingsManager.getSettingByName(this, "Mode").getValString();

        float ym = (float) Kisman.instance.settingsManager.getSettingByName(this, "Motion").getValDouble();

        switch (mode) {
            case "Motion": {
                mc.player.motionY = ym;
                break;
            }
            case "Packet": {
                mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(mc.player.posX, mc.player.posY + ym, mc.player.posZ, true));
            }
            case "Teleport": {
                mc.player.setPositionAndUpdate(mc.player.posX, mc.player.posY + ym, mc.player.posZ);
            }
        }
    }
}
