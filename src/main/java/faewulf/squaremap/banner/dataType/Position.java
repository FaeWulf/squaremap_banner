package faewulf.squaremap.banner.dataType;

import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.jpenilla.squaremap.api.Point;

public record Position(BlockPos loc) {
    public static Position of(BlockPos loc) {
        return new Position(loc);
    }

    public Point point() {
        return Point.of(this.loc.getX(), this.loc.getZ());
    }

    public boolean isBanner(World world) {
        return world.getBlockEntity(loc) instanceof BannerBlockEntity;
    }
}

