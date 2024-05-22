package faewulf.squaremap.banner.events;

import faewulf.squaremap.banner.Squaremapbanner;
import faewulf.squaremap.banner.utils.bannerManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class onServerStarted {
    public static void load() {
        ServerLifecycleEvents.SERVER_STARTED.register((server -> {
            Squaremapbanner.LOGGER.info("Restoring banner data if exist...");
            bannerManager.load(server);

            //reload save file
            bannerManager.save();
        }));
    }
}
