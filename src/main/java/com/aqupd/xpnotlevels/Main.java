package com.aqupd.xpnotlevels;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

import static com.aqupd.xpnotlevels.utils.AqLogger.*;

public class Main implements ModInitializer {
    @Override
    public void onInitialize() {
        logInfo("Mod initialized");
    }

    public static int getExperienceCostAtLevel(int startLevel, int levelCost) {
        if (levelCost < 0 || startLevel < 0)
            throw new CrashException(CrashReport.create(
                    new RuntimeException("Value cannot be negative"),
                    "Calculating experience using negative value"));
        int cost = 0;
        for (int i = startLevel; i > startLevel - levelCost; --i) {
            cost += getSingleLevelCost(i);
        }
        return cost;
    }
    private static int getSingleLevelCost(int level) {
        level -= 1;
        if (level >= 0 && level <= 15) {
            return 2 * level + 7;
        } else if (level >= 16 && level <= 30) {
            return 5 * level - 38;
        } else if (level >= 31) {
            return 9 * level - 158;
        } else {
            throw new RuntimeException("Undefined level! [" + level + "]");
        }
    }
}
