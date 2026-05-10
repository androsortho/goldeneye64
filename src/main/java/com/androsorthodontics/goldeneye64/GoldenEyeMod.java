package com.androsorthodontics.goldeneye64;

import com.androsorthodontics.goldeneye64.command.BattleRoyaleCommand;
import com.androsorthodontics.goldeneye64.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GoldenEye 64 Battle Royale - Main mod entry point.
 *
 * Inspired by GoldenEye 007 (N64, 1997). Adds period-correct weapons,
 * proximity mines, and a /br command that runs a shrinking-border
 * battle royale match.
 */
public class GoldenEyeMod implements ModInitializer {
    public static final String MOD_ID = "goldeneye64";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[GoldenEye64] Initializing — license to kill granted.");

        ModItems.register();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            BattleRoyaleCommand.register(dispatcher)
        );

        LOGGER.info("[GoldenEye64] Ready. Type /br help in-game to start a match.");
    }
}
