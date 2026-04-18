package faewulf.squaremap.banner.utils;

import com.google.common.collect.Maps;
import faewulf.squaremap.banner.Squaremapbanner;
import faewulf.squaremap.banner.dataType.icon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import java.util.Map;


public class bannerManager {
    public static Map<String, world> bannerManager = Maps.newHashMap();

    public static void save() {
        saveDataHandler.saveConfig();
    }

    public static void load(MinecraftServer server) {
        Map<String, world> bannerManagerDataLoad = saveDataHandler.loadData();

        if (bannerManagerDataLoad == null)
            return;

        bannerManagerDataLoad.forEach((s, world) -> {
            //get world based on data file's identifier
            world worldInManager = faewulf.squaremap.banner.utils.bannerManager.bannerManager.get(world.identifier);

            if (worldInManager != null) {
                //then forEach every banner in the data
                world.getData().forEach((position, data) -> {

                    //if banner is not exist in actual world, then avoid add it into worldObj

                    try {
                        //obtain server world
                        ResourceKey<Level> worldKey = ResourceKey.create(Registries.DIMENSION, Identifier.tryParse(world.identifier));
                        ServerLevel serverWorld = server.getLevel(worldKey);

                        if (serverWorld != null && !position.isBanner(serverWorld))
                            return;

                    } catch (Exception e) {
                        Squaremapbanner.LOGGER.error(e.toString());
                    }

                    worldInManager.addBanner(position, icon.getIcon(DyeColor.valueOf(data.keyIconType().getKey())), data.name(), data.key());
                });
            }
        });
    }

}

