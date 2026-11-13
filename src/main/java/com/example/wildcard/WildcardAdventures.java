package com.example.wildcard;

import com.example.wildcard.content.ModItems;
import com.example.wildcard.gui.AdventureScreenHandler;
import com.example.wildcard.world.ShrineFeature;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;

public class WildcardAdventures implements ModInitializer {
    public static final String MOD_ID = "wildcard_adventures";
    public static Identifier id(String path) { return Identifier.of(MOD_ID, path); }
    public static final EntityType<WildlingEntity> WILDLING = Registry.register(Registries.ENTITY_TYPE, id("wildling"), EntityType.Builder.create(WildlingEntity::new, SpawnGroup.MONSTER).dimensions(0.65f, 1.9f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, id("wildling"))));
    public static final ScreenHandlerType<AdventureScreenHandler> ADVENTURE_SCREEN = Registry.register(Registries.SCREEN_HANDLER, id("adventure"), new ExtendedScreenHandlerType<>(AdventureScreenHandler::new, net.minecraft.network.codec.PacketCodecs.VAR_INT));

    @Override public void onInitialize() {
        ModItems.register();
        FabricDefaultAttributeRegistry.register(WILDLING, WildlingEntity.createAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, WILDLING, 8, 1, 2);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("wildcard")
            .then(CommandManager.literal("menu").executes(context -> {
                ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                player.openHandledScreen(new NamedScreenHandlerFactory() {
                    @Override public Text getDisplayName() { return Text.literal("Wildcard Rewards"); }
                    @Override public AdventureScreenHandler createMenu(int syncId, PlayerInventory inventory, net.minecraft.entity.player.PlayerEntity user) { return new AdventureScreenHandler(syncId, inventory, 0); }
                });
                return 1;
            }))
            .then(CommandManager.literal("shrine").executes(context -> {
                ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                ShrineFeature.place(player.getServerWorld(), player.getBlockPos().up());
                context.getSource().sendFeedback(() -> Text.literal("A Wildcard Shrine was placed."), false);
                return 1;
            }))));
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, attacker, victim) -> {
            if (victim instanceof HostileEntity && world.random.nextInt(victim instanceof WildlingEntity ? 1 : 6) == 0) victim.dropStack(world, new ItemStack(world.random.nextBoolean() ? ModItems.WILDCARD_SHARD : Items.AMETHYST_SHARD, 1 + world.random.nextInt(2)));
        });
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            handler.player.sendMessage(Text.literal("The wilds are unpredictable. Use /wildcard for adventures."), false);
            if (!handler.player.getInventory().contains(new ItemStack(ModItems.WILDCARD_COMPASS))) handler.player.giveItemStack(new ItemStack(ModItems.WILDCARD_COMPASS));
        });
        ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
            if (world.getRandom().nextInt(160) == 0) {
                ChunkPos position = chunk.getPos();
                BlockPos surface = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(position.getStartX() + 8, 0, position.getStartZ() + 8));
                ShrineFeature.place(world, surface);
            }
        });
    }
}
