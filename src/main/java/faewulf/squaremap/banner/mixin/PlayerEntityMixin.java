package faewulf.squaremap.banner.mixin;

import faewulf.squaremap.banner.modules.bannerTitle;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static faewulf.squaremap.banner.Squaremapbanner.modConfigs;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Unique
    private Vec3d lastPos;

    @Unique
    private int delayTick = 0;

    @Inject(at = @At("TAIL"), method = "tickMovement")
    private void tickMovementMixin(CallbackInfo cif) {

        if (!modConfigs.bannerEnterAnnouncer)
            return;

        delayTick++;
        if (delayTick < 20)
            return;
        delayTick = 0;

        PlayerEntity player = (PlayerEntity) (Object) this;
        Vec3d currentPos = player.getEntityPos();

        if (lastPos != null && !currentPos.equals(lastPos)) {
            // Player has moved
            //System.out.println("Player moved: " + player.getName().getString() + " to position " + currentPos);
            bannerTitle.check(player.getEntityWorld(), player);
        }

        // Update last position
        lastPos = currentPos;
    }
}

