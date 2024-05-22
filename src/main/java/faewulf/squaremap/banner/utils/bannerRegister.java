package faewulf.squaremap.banner.utils;

import faewulf.squaremap.banner.dataType.Position;
import faewulf.squaremap.banner.dataType.icon;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.jpenilla.squaremap.api.Key;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static faewulf.squaremap.banner.Squaremapbanner.modConfigs;

public class bannerRegister {

    public static void tryAddBanner(PlayerEntity player, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof BannerBlockEntity bannerEntity) {
            // Get the base color of the banner
            DyeColor baseColor = bannerEntity.getColorForState();


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
                player.sendMessage(Text.of("This banner's name contains a blacklist word: \"" + badWord.get() + "\", please rename it and try again"));
                return;
            }

            //get world id
            String worldKey = world.getRegistryKey().getValue().toString();

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

    public static void tryRemoveBanner(World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        //if a banner
        if (blockEntity instanceof BannerBlockEntity bannerEntity) {
            if (bannerEntity.getCustomName() == null)
                return;

            //get world id
            String worldKey = world.getRegistryKey().getValue().toString();

            //get provider based on the world id
            if (bannerManager.bannerManager.containsKey(worldKey)) {
                world w = bannerManager.bannerManager.get(worldKey);

                //then just add banner to the world
                w.removeBanner(Position.of(pos));
            }
        }
    }

}
