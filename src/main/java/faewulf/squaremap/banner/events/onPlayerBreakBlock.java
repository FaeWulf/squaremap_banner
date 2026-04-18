package faewulf.squaremap.banner.events;

import faewulf.squaremap.banner.dataType.Position;
import faewulf.squaremap.banner.utils.bannerManager;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.world.level.block.entity.BannerBlockEntity;


public class onPlayerBreakBlock {

    public static void load() {
        PlayerBlockBreakEvents.AFTER.register(((world, player, pos, state, blockEntity) -> {
            if (!world.isClientSide()) {
                //if a banner
                if (blockEntity instanceof BannerBlockEntity bannerEntity) {
                    if (bannerEntity.getCustomName() == null)
                        return;

                    //get world id
                    String worldKey = world.dimension().identifier().toString();

                    //get provider based on the world id
                    if (bannerManager.bannerManager.containsKey(worldKey)) {
                        faewulf.squaremap.banner.utils.world w = bannerManager.bannerManager.get(worldKey);
                        w.removeBanner(Position.of(pos));

                        bannerManager.save();
                    }
                }

            }
        }));
    }
}
