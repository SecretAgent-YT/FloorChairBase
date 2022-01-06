package me.secretagent.floorchairbase.api.user.rank;

import java.util.Arrays;

public enum FloorChairRank {

    DEFAULT(0),
    FLOOR(1),
    CHAIR(2),
    BUILDER(3),
    HELPER(4),
    ADMIN(5),
    OWNER(6);

    private final int level;

    FloorChairRank(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static FloorChairRank getAtLevel(int level) {
        return Arrays.stream(values()).filter(floorChairRank -> floorChairRank.getLevel() == level).findFirst().orElse(null);
    }

}
