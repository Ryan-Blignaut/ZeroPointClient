package github.thesivlerecho.zeropoint.event.events;

import github.thesivlerecho.zeropoint.event.BaseEvent;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public record BlockOutlineEvent(MatrixStack matrixStack, VoxelShape voxelShape, double x, double y, double z,
                                CallbackInfo callbackInfo) implements BaseEvent
{
}
