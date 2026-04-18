package faewulf.squaremap.banner.utils;

import faewulf.squaremap.banner.dataType.Position;
import faewulf.squaremap.banner.dataType.icon;
import xyz.jpenilla.squaremap.api.Key;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

import static faewulf.squaremap.banner.Squaremapbanner.modConfigs;

public class bannerRegister {

    public static void tryAddBanner(Player player, Level world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof BannerBlockEntity bannerEntity) {
            // Get the base color of the banner
            DyeColor baseColor = bannerEntity.getBaseColor();


            if (bannerEntity.getCustomName() == null)
                return;

            //get name
            String name = bannerEntity.getCustomName().getString();
            //bad word check
            AtomicReference<String> badWord = new AtomicReference<>();
            modConfigs.blacklist.forEach(blacklistString -> {
                if (wordCheck.containsWholeWord(name, blacklistString)) {
                    badWord.set(blacklistString);
                }
            });

            if (badWord.get() != null) {
                player.sendOverlayMessage(Component.nullToEmpty("This banner's name contains a blacklist word: \"" + badWord.get() + "\", please rename it and try again"));
                return;
            }

            //get world id
            String worldKey = world.dimension().identifier().toString();

            //get provider based on the world id
            if (bannerManager.bannerManager.containsKey(worldKey)) {
                world w = bannerManager.bannerManager.get(worldKey);

                //generate new random unique key for banner
                UUID uuid = UUID.randomUUID();

                //then just add banner to the world
                w.addBanner(Position.of(pos), icon.getIcon(baseColor), name, Key.of(uuid.toString()));
            }
        }
    }

    public static void tryRemoveBanner(Level world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        //if a banner
        if (blockEntity instanceof BannerBlockEntity bannerEntity) {
            if (bannerEntity.getCustomName() == null)
                return;

            //get world id
            String worldKey = world.dimension().identifier().toString();

            //get provider based on the world id
            if (bannerManager.bannerManager.containsKey(worldKey)) {
                world w = bannerManager.bannerManager.get(worldKey);

                //then just add banner to the world
                w.removeBanner(Position.of(pos));
            }
        }
    }

}
