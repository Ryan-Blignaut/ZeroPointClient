package github.thesivlerecho.zeropoint.event.events;

import github.thesivlerecho.zeropoint.event.BaseEvent;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public record RenderTileEntityEvent(BlockEntityRenderer<BlockEntity> a, BlockEntity blockEntity, float tickDelta,
                                    MatrixStack matrices,
                                    VertexConsumerProvider vertexConsumers, CallbackInfo ci) implements BaseEvent
{
}
