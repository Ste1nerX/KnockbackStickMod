package com.example.knockbackstick.item;

import com.example.knockbackstick.KnockbackStickMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    // Правильная регистрация для Fabric 1.21.8+
    public static final Item KNOCKBACK_STICK = register("knockback_stick",
            settings -> new KnockbackStickItem(settings), new Item.Settings().maxCount(1));

    public static Item register(String name, java.util.function.Function<Item.Settings, Item> factory, Item.Settings settings) {
        // Создаём ключ реестра
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM,
                Identifier.of(KnockbackStickMod.MOD_ID, name));

        // Устанавливаем ключ в настройки (КРИТИЧЕСКИ ВАЖНО!)
        Item item = factory.apply(settings.registryKey(itemKey));

        // Регистрируем предмет
        return Registry.register(Registries.ITEM, itemKey, item);
    }

    public static void registerItems() {
        KnockbackStickMod.LOGGER.info("Регистрация предметов для " + KnockbackStickMod.MOD_ID);

        // Добавляем в группу инструментов
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(KNOCKBACK_STICK);
        });
    }
}
