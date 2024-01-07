package com.movtery.difficultyd.Client;

import com.movtery.difficultyd.Config.ConfigScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class DifficultydClient implements ClientModInitializer {
    private static KeyBinding OPEN_CONFIG_SCREEN;

    @Override
    public void onInitializeClient() {
        // 注册一个新的按键设置
        OPEN_CONFIG_SCREEN = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.difficultyd.key.openConfigScreen",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                "key.difficultyd.category"
        ));
        // 打开配置屏幕
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (OPEN_CONFIG_SCREEN.wasPressed()) {
                Screen screen = ConfigScreen.getConfigScreen().build();
                MinecraftClient.getInstance().setScreen(screen);
            }
        });
    }
}
