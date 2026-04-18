package faewulf.squaremap.banner.events;

import faewulf.squaremap.banner.modules.bannerTitle;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.level.ServerPlayer;

import static faewulf.squaremap.banner.Squaremapbanner.modConfigs;

public class onPlayerLeave {
    public static void load() {
        if (modConfigs.bannerEnterAnnouncer)
            ServerPlayConnectionEvents.DISCONNECT.register(((handler, server) -> {
                ServerPlayer player = handler.getPlayer();

                if (bannerTitle.playerList.containsKey(player)) {
                    bannerTitle.playerList.put(player, null);
                }
            }));
    }
}
