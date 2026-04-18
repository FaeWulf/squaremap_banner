package faewulf.squaremap.banner.events;

import faewulf.squaremap.banner.utils.bannerManager;
import faewulf.squaremap.banner.utils.bannerRegister;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;

public class onLeftClick {
    public static void load() {
        AttackBlockCallback.EVENT.register(((player, world, hand, pos, direction) -> {

            if (!Permissions.check(player, faewulf.squaremap.banner.dataType.Permissions.USE, 1))
                return InteractionResult.PASS;

            if (!world.isClientSide()) {

                //if not holding filled map
                if (player.getItemInHand(hand).getItem() != Items.FILLED_MAP)
                    return InteractionResult.PASS;

                //if not sneaking
                if (!player.isShiftKeyDown())
                    return InteractionResult.PASS;

                bannerRegister.tryRemoveBanner(world, pos);

                bannerManager.save();
            }
            return InteractionResult.PASS;
        }));
    }
}
