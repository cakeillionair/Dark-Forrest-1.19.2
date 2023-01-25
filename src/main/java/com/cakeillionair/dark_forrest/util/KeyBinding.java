package com.cakeillionair.dark_forrest.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_DF = "key.category.dark_forrest.bindings";
    public static final String KEY_WARP = "key.dark_forrest.warp";

    public static final KeyMapping WARP_KEY = new KeyMapping(KEY_WARP, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Y, KEY_CATEGORY_DF);
}
