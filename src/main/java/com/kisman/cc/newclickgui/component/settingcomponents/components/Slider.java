package com.kisman.cc.newclickgui.component.settingcomponents.components;

import com.kisman.cc.module.Module;
import com.kisman.cc.newclickgui.component.settingcomponents.Component;
import com.kisman.cc.newclickgui.component.settingcomponents.SettingButton;
import com.kisman.cc.settings.Setting;
import com.kisman.cc.util.customfont.CustomFontUtil;
import net.minecraft.client.gui.GuiScreen;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends Component {
    private int x;
    private int y;
    private int width;
    private int heigth;
    private int offset;
    private int sX;
    private int sY;

    private SettingButton button;
    private Setting set;
    private Module mod;

    private boolean dragging;

    private double renderWidth;

    public Slider(int x, int y, int offset, int width, int heigth, SettingButton button, Setting set, Module mod) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.offset = offset;

        this.sX = this.x;
        this.sY = this.y + this.offset + 2 + CustomFontUtil.getFontHeight();
//        this.sY = (this.y + this.offset + 1) + (CustomFontUtil.getFontHeight() + ((this.heigth - CustomFontUtil.getFontHeight() - 6) / 2 + 2));

        this.button = button;
        this.set = set;
        this.mod = mod;
    }

    public void renderComponent() {
//        GuiScreen.drawRect(this.x, this.y + this.offset, this.x + this.width, this.y + this.offset + this.heigth, -1);

        CustomFontUtil.drawStringWithShadow(this.set.getName(), this.x + 1, this.y + this.offset + 1, 0x303030);

        GuiScreen.drawRect(this.sX - 1, this.sY, this.sX + (int) this.renderWidth - 1, this.sY + 1, -1);
    }

    public void updateComponent(int mouseX, int mouseY) {
        double diff = Math.min(200, Math.max(0, mouseX - this.x));

        double min = set.getMin();
        double max = set.getMax();

        renderWidth = (200) * (set.getValDouble() - min) / (max - min);

        if (dragging) {
            if (diff == 0) {
                set.setValDouble(set.getMin());
            }
            else {
                double newValue = roundToPlace(((diff / 200) * (max - min) + min), 2);
                set.setValDouble(newValue);
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if(isMouseOnButtonD(mouseX, mouseY) && button == 0) {
            this.dragging = true;
        }
        if(isMouseOnButtonI(mouseX, mouseY) && button == 0) {
            this.dragging = true;
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int button) {
        this.dragging = false;
    }

    private static double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public boolean isMouseOnButtonD(int x, int y) {
        if(x > this.x && x < this.x + (200 / 2 + 1) && y > this.y + this.offset && y < this.y + this.offset + 12) {
            return true;
        }
        return false;
    }

    public boolean isMouseOnButtonI(int x, int y) {
        if(x > this.x + 200 / 2 && x < this.x + 200 && y > this.y && y < this.y + this.offset + this.offset + 12) {
            return true;
        }
        return false;
    }
}
