package com.example.wildcard.gui;

import com.example.wildcard.WildcardAdventures;
import com.example.wildcard.content.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AdventureScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public AdventureScreenHandler(int syncId, PlayerInventory playerInventory, int data) {
        super(WildcardAdventures.ADVENTURE_SCREEN, syncId);
        inventory = new SimpleInventory(9);
        inventory.setStack(4, new ItemStack(ModItems.WILDCARD_SHARD, 3));
        for (int i = 0; i < 9; i++) {
            final int slotIndex = i;
            addSlot(new Slot(inventory, i, 8 + i * 18, 20) {
                @Override public boolean canInsert(ItemStack stack) { return false; }
                @Override public boolean canTakeItems(PlayerEntity player) { return slotIndex == 4 && player.getInventory().contains(new ItemStack(ModItems.WILDCARD_COMPASS)); }
                @Override public ItemStack takeStack(int amount) {
                    if (slotIndex != 4 || amount < 1) return ItemStack.EMPTY;
                    ItemStack stack = inventory.getStack(slotIndex).copy();
                    inventory.setStack(slotIndex, ItemStack.EMPTY);
                    onSlotChanged();
                    return stack;
                }
            });
        }
        for (int row = 0; row < 3; row++) for (int column = 0; column < 9; column++) addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 51 + row * 18));
        for (int column = 0; column < 9; column++) addSlot(new Slot(playerInventory, column, 8 + column * 18, 109));
    }

    @Override public ItemStack quickMove(PlayerEntity player, int index) {
        if (index < 0 || index >= slots.size()) return ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (!slot.hasStack()) return ItemStack.EMPTY;
        ItemStack stack = slot.getStack();
        ItemStack original = stack.copy();
        if (index < 9) {
            if (!insertItem(stack, 9, 45, true)) return ItemStack.EMPTY;
        } else if (!insertItem(stack, 0, 9, false)) return ItemStack.EMPTY;
        if (stack.isEmpty()) slot.setStack(ItemStack.EMPTY); else slot.markDirty();
        return original;
    }

    @Override public boolean canUse(PlayerEntity player) { return inventory.canPlayerUse(player); }
}
