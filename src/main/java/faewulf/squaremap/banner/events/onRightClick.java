package faewulf.squaremap.banner.events;

import faewulf.squaremap.banner.utils.bannerManager;
import faewulf.squaremap.banner.utils.bannerRegister;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;

public class onRightClick {
    public static void load() {
        UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {

            if (!Permissions.check(player, faewulf.squaremap.banner.dataType.Permissions.USE, 1))
                return InteractionResult.PASS;

            if (!world.isClientSide()) {

                //if not holding filled map
                if (player.getItemInHand(hand).getItem() != Items.FILLED_MAP)
                    return InteractionResult.PASS;

                //if not sneaking
                if (!player.isShiftKeyDown())
                    return InteractionResult.PASS;

                bannerRegister.tryAddBanner(player, world, hitResult.getBlockPos());

                //save data
                bannerManager.save();
            }
            return InteractionResult.PASS;
        }));
    }
}
