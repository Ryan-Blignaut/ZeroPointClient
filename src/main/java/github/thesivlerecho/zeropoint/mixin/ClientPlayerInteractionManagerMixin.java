package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.event.EventManager;
import github.thesivlerecho.zeropoint.event.events.BlockBreakEvent;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin
{
	@Shadow protected abstract void syncSelectedSlot();

	@Inject(method = "updateBlockBreakingProgress", at = @At("HEAD"), cancellable = true)
	private void onPlayerDamageBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir)
	{
		this.syncSelectedSlot();
		EventManager.call(new BlockBreakEvent(pos, direction, cir));
	}

}
