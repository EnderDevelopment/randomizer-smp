package com.example.wildcard.client;

import com.example.wildcard.gui.AdventureScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class AdventureScreen extends HandledScreen<AdventureScreenHandler> {
    public AdventureScreen(AdventureScreenHandler handler, PlayerInventory inventory, Text title) { super(handler, inventory, title); }
    @Override protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int left = (width - backgroundWidth) / 2;
        int top = (height - backgroundHeight) / 2;
        context.fill(left, top, left + backgroundWidth, top + backgroundHeight, 0xFF241B2B);
        context.fill(left + 4, top + 4, left + backgroundWidth - 4, top + 42, 0xFF4C2A5E);
        context.drawText(textRenderer, title, left + 8, top + 8, 0xFFFFFF, false);
        context.drawText(textRenderer, Text.literal("Random rewards await"), left + 8, top + 25, 0xD8B4FE, false);
    }
    @Override public void render(DrawContext context, int mouseX, int mouseY, float delta) { renderBackground(context, mouseX, mouseY, delta); super.render(context, mouseX, mouseY, delta); drawMouseoverTooltip(context, mouseX, mouseY); }
}
