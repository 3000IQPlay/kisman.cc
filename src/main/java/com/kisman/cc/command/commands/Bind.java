package com.kisman.cc.command.commands;

import com.kisman.cc.Kisman;
import com.kisman.cc.command.Command;
import com.kisman.cc.file.SaveConfig;
import com.kisman.cc.module.Module;

import org.lwjgl.input.Keyboard;

import i.gishreloaded.gishcode.utils.visual.ChatUtils;

public class Bind extends Command{
    public Bind() {
        super("Bind");
    }

    @Override
    public void runCommand(String s, String[] args) {
        try {
            String key = args[0];
            String isList = args[0];

            if(args.length == 1 && !isList.equalsIgnoreCase("list")) {
                ChatUtils.error("Usage: " + getSyntax());
                return;
            }

            if(args.length > 2 && isList.equalsIgnoreCase("list")) {
                ChatUtils.error("Usage: " + getSyntax());
                return;
            }

            if(args.length == 1 && isList.equalsIgnoreCase("list")) {
                for(Module mod : Kisman.instance.moduleManager.modules) {
                    if(Keyboard.KEY_NONE != mod.getKey()) {
                        ChatUtils.message(mod.getName() + " | " + Keyboard.getKeyName(mod.getKey()));
                    }
                }
                return;
            }

            for(Module mod : Kisman.instance.moduleManager.modules) {
                if(mod.getName().equalsIgnoreCase(args[1])) {
                    mod.setKey(Keyboard.getKeyIndex((key.toUpperCase())));
                    ChatUtils.message(mod.getName() + " binned to " + Keyboard.getKeyName(mod.getKey()));
                    SaveConfig.init();
                }
            }
        } catch(Exception e) {
            ChatUtils.error("Usage: " + getSyntax());
        }
    }

    @Override
	public String getDescription() {
		return "Change key for modules/commands.";
	}

	@Override
	public String getSyntax() {
		return "bind <key> <command/module> or bind list";
	}
}
