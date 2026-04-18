package faewulf.squaremap.banner.mixin;

import faewulf.squaremap.banner.modules.bannerTitle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static faewulf.squaremap.banner.Squaremapbanner.modConfigs;

@Mixin(Player.class)
public class PlayerEntityMixin {
    @Unique
    private Vec3 lastPos;

    @Unique
    private int delayTick = 0;

    @Inject(at = @At("TAIL"), method = "aiStep")
    private void tickMovementMixin(CallbackInfo cif) {

        if (!modConfigs.bannerEnterAnnouncer)
            return;

        delayTick++;
        if (delayTick < 20)
            return;
        delayTick = 0;

        Player player = (Player) (Object) this;
        Vec3 currentPos = player.position();

        if (lastPos != null && !currentPos.equals(lastPos)) {
            // Player has moved
            //System.out.println("Player moved: " + player.getName().getString() + " to position " + currentPos);
            bannerTitle.check(player.level(), player);
        }

        // Update last position
        lastPos = currentPos;
    }
}

