package com.example.knockbackstick.command;

import com.example.knockbackstick.item.ModItems;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class KnockbackStickCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("givekstick")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(KnockbackStickCommand::giveKnockbackStick));
    }

    private static int giveKnockbackStick(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        if (source.getEntity() instanceof ServerPlayerEntity player) {
            ItemStack knockbackStick = new ItemStack(ModItems.KNOCKBACK_STICK);

            // Добавляем предмет в инвентарь
            if (player.getInventory().insertStack(knockbackStick)) {
                player.sendMessage(
                        Text.literal("Вы получили Палочку Откидывания! Ударьте кого-нибудь, чтобы откинуть его на 50 блоков!")
                                .formatted(Formatting.GREEN),
                        false
                );
                return 1;
            } else {
                player.sendMessage(
                        Text.literal("Ваш инвентарь полон!")
                                .formatted(Formatting.RED),
                        false
                );
                return 0;
            }
        } else {
            source.sendFeedback(
                    () -> Text.literal("Эту команду может использовать только игрок!")
                            .formatted(Formatting.RED),
                    false
            );
            return 0;
        }
    }
}
