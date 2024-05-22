package faewulf.squaremap.banner.events;

import faewulf.squaremap.banner.utils.bannerManager;
import faewulf.squaremap.banner.utils.world;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import xyz.jpenilla.squaremap.api.SimpleLayerProvider;
import xyz.jpenilla.squaremap.api.Squaremap;
import xyz.jpenilla.squaremap.api.SquaremapProvider;
import xyz.jpenilla.squaremap.api.WorldIdentifier;

public class onWorldLoad {
    public static void load() {
        ServerWorldEvents.LOAD.register(
                (server, world_) -> {

                    //get world id: example minecraft:the_end
                    String worldKey = world_.getRegistryKey().getValue().toString();

                    //get sqrmap api
                    Squaremap api = SquaremapProvider.get();

                    //get mapworld based on id
                    api.getWorldIfEnabled(WorldIdentifier.parse(worldKey)).ifPresent(mapWorld -> {

                        //create new world obj
                        world worldObj = new world(mapWorld.identifier().asString());

                        SimpleLayerProvider provider = SimpleLayerProvider.builder("Banner")
                                .showControls(true)
                                .defaultHidden(false)
                                .layerPriority(5)
                                .zIndex(250)
                                .build();

                        //set provider
                        worldObj.setProvider(provider, "banner_" + mapWorld.identifier().namespace());

                        //then put into manager
                        bannerManager.bannerManager.put(worldKey, worldObj);
                    });
                }
        );
    }
}
