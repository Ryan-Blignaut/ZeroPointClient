package github.thesivlerecho.zeropoint.event.events;

import github.thesivlerecho.zeropoint.event.BaseEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public record BlockBreakEvent(BlockPos pos, Direction direction,
                              CallbackInfoReturnable<Boolean> callbackInfoReturnable) implements BaseEvent
{
}
