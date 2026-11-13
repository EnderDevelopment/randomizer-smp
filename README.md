# Minecraft Fabric Mod: Wildcard Adventures

Wildcard Adventures is a Minecraft Fabric mod for Minecraft 1.21.11 and Java 21 that adds a custom hostile mob, randomized hostile-mob rewards, craftable items, shrines, server commands, and multiplayer-friendly server-side gameplay.

## Install

1. Install Java 21.
2. Install a Fabric Loader profile for Minecraft 1.21.11.
3. Install Fabric API for Minecraft 1.21.11.
4. Build the mod from the project root:

```bash
./gradlew build
```

On Windows, run:

```bat
gradlew.bat build
```

5. Copy `build/libs/wildcard-adventures-1.0.0.jar` into the server or client `mods` directory.
6. Ensure Fabric API is also present in the same `mods` directory.
7. Start Minecraft with the Fabric profile.

The Minecraft, Fabric Loader, Fabric API, mappings, Java, and mod versions are configured in `gradle.properties`.

## Use

1. Join a Fabric Minecraft 1.21.11 world or server.
2. Use `/wildcard menu` to open the Wildcard Rewards screen.
3. Use `/wildcard shrine` to place a shrine at the player’s current position when the required ground is solid.
4. Explore the Overworld to encounter Wildlings, a custom hostile mob with melee AI and player targeting.
5. Defeat hostile mobs to receive occasional Wildcard Shards or Amethyst Shards.
6. Use the gold, echo shard, and compass recipe to craft a Wildcard Compass.
7. Shrines may also appear automatically when chunks load.
8. Players receive a starter Wildcard Compass when they join if they do not already have one.

The implemented project does not currently include the Randomizer SMP systems described as random block drops, per-player random crafting, random structures and loot, timed random events, or the `/randomizer` command family.

---

More Minecraft mod projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/e399fd29-071b-4dc7-a585-a353fc0b87a4?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=e399fd29-071b-4dc7-a585-a353fc0b87a4
