package faewulf.squaremap.banner.modules;

import faewulf.squaremap.banner.dataType.Data;
import faewulf.squaremap.banner.dataType.icon;
import faewulf.squaremap.banner.utils.bannerManager;
import faewulf.squaremap.banner.utils.world;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;

import static faewulf.squaremap.banner.Squaremapbanner.modConfigs;

public class bannerTitle {

    public static Map<Player, String> playerList = new HashMap<>();


    public static void check(Level playerWorld, Player player) {

        if (playerWorld.isClientSide())
            return;

        //get world id
        String worldId = playerWorld.dimension().identifier().toString();

        //get worldObj from bannerManager
        world worldObj = bannerManager.bannerManager.get(worldId);

        //safe check
        if (worldObj == null)
            return;

        long playerX = (long) player.position().x();
        long playerZ = (long) player.position().z();

        //banner title radius
        int radius = modConfigs.radius;

        AtomicReference<Double> minDistance = new AtomicReference<>((double) radius + 1);
        AtomicReference<Data> selectedBannerData = new AtomicReference<>();

        //checking if player near any registered banner
        worldObj.getData().forEach(((position, data) -> {
            int bannerX = position.loc().getX();
            int bannerZ = position.loc().getZ();

            double distance = Math.sqrt(Math.pow(bannerX - playerX, 2) + Math.pow(bannerZ - playerZ, 2));

            //if found near banner
            //check if it is nearest banner to the player then select it
            if (distance <= radius) {
                if (distance < minDistance.get()) {
                    minDistance.set(distance);
                    selectedBannerData.set(data);
                }
            } else {
                //checking if player has left the entered banner area
                String bannerKey = playerList.get(player);
                String forEachKey = data.key().getKey();
                if (bannerKey != null && bannerKey.equals(forEachKey)) {
                    playerList.put(player, null);
                }
            }
        }));

        //if found a valid banner
        if (selectedBannerData.get() != null) {

            //check if player in playerList that has entered a banner area recently
            String bannerKey = playerList.get(player);

            //selected Key
            String selectedKey = selectedBannerData.get().key().getKey();

            //if null or != selected key then insert player into the list
            if (bannerKey == null || !bannerKey.equals(selectedKey)) {
                playerList.put(player, selectedKey);
                String bannerName = selectedBannerData.get().name();
                TextColor color = icon.getTextColor(DyeColor.valueOf(selectedBannerData.get().keyIconType().getKey()));
                //then sendMessage
                Component sendString = Component.literal("Entering ")
                        .append(
                                Component.literal(bannerName).withStyle(style -> style.withColor(color))
                        );

                player.sendOverlayMessage(sendString);
            }
        }
    }
}
