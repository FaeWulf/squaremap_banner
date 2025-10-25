package faewulf.squaremap.banner.events;

import faewulf.squaremap.banner.dataType.Position;
import faewulf.squaremap.banner.utils.bannerManager;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.entity.BannerBlockEntity;


public class onPlayerBreakBlock {

    public static void load() {
        PlayerBlockBreakEvents.AFTER.register(((world, player, pos, state, blockEntity) -> {
            if (!world.isClient()) {
                //if a banner
                if (blockEntity instanceof BannerBlockEntity bannerEntity) {
                    if (bannerEntity.getCustomName() == null)
                        return;

                    //get world id
                    String worldKey = world.getRegistryKey().getValue().toString();

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
