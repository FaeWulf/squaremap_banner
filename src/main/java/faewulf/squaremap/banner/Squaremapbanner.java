package faewulf.squaremap.banner;

import faewulf.squaremap.banner.dataType.ModConfigs;
import faewulf.squaremap.banner.events.*;
import faewulf.squaremap.banner.utils.ConfigHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Squaremapbanner implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("squaremap-banner");

    public static ModConfigs modConfigs;

    public static String MODID = "squaremap-banner";

    @Override
    public void onInitialize() {
        LOGGER.info("Cool! Now loading modules and events...");

        //load config file
        modConfigs = ConfigHandler.loadConfig();

        ConfigHandler.saveConfig(modConfigs);

        this.loadEvents();
    }

    private void loadEvents() {
        onWorldLoad.load();
        onLeftClick.load();
        onRightClick.load();
        onPlayerBreakBlock.load();
        onServerStarted.load();
        onPlayerLeave.load();
    }
}