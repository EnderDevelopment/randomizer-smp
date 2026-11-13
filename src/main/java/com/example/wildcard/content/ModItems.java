package com.example.wildcard.content;

import com.example.wildcard.WildcardAdventures;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class ModItems {
    public static final Item WILDCARD_SHARD = register("wildcard_shard", new Item(new Item.Settings()));
    public static final Item WILDCARD_COMPASS = register("wildcard_compass", new Item(new Item.Settings().maxCount(1)));

    private ModItems() { }

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, WildcardAdventures.id(name), item);
    }

    public static void register() { }
}
