package faewulf.squaremap.banner.events;

import faewulf.squaremap.banner.utils.bannerManager;
import faewulf.squaremap.banner.utils.bannerRegister;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

public class onLeftClick {
    public static void load() {
        AttackBlockCallback.EVENT.register(((player, world, hand, pos, direction) -> {

            if (!Permissions.check(player, faewulf.squaremap.banner.dataType.Permissions.USE, 1))
                return ActionResult.PASS;

            if (!world.isClient()) {

                //if not holding filled map
                if (player.getStackInHand(hand).getItem() != Items.FILLED_MAP)
                    return ActionResult.PASS;

                //if not sneaking
                if (!player.isSneaking())
                    return ActionResult.PASS;

                bannerRegister.tryRemoveBanner(world, pos);

                bannerManager.save();
            }
            return ActionResult.PASS;
        }));
    }
}
