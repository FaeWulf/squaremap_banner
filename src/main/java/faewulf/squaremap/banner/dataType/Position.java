package faewulf.squaremap.banner.dataType;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import xyz.jpenilla.squaremap.api.Point;

public record Position(BlockPos loc) {
    public static Position of(BlockPos loc) {
        return new Position(loc);
    }

    public Point point() {
        return Point.of(this.loc.getX(), this.loc.getZ());
    }

    public boolean isBanner(Level world) {
        return world.getBlockEntity(loc) instanceof BannerBlockEntity;
    }
}

