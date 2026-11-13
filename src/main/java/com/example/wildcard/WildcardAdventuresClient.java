package com.example.wildcard;

import com.example.wildcard.client.AdventureScreen;
import com.example.wildcard.client.WildlingEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class WildcardAdventuresClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(WildcardAdventures.ADVENTURE_SCREEN, AdventureScreen::new);
        EntityRendererRegistry.register(WildcardAdventures.WILDLING, WildlingEntityRenderer::new);
    }
}
