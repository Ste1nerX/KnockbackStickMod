package com.example.knockbackstick;

import com.example.knockbackstick.command.KnockbackStickCommand;
import com.example.knockbackstick.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnockbackStickMod implements ModInitializer {
    public static final String MOD_ID = "knockbackstick";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Инициализация Knockback Stick Mod!");

        // Регистрируем предметы
        ModItems.registerItems();

        // Регистрируем команду
        CommandRegistrationCallback.EVENT.register(KnockbackStickCommand::register);

        LOGGER.info("Knockback Stick Mod успешно загружен!");
    }
}
