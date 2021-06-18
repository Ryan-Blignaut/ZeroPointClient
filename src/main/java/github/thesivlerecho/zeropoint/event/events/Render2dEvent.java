package github.thesivlerecho.zeropoint.event.events;

import github.thesivlerecho.zeropoint.event.BaseEvent;
import net.minecraft.client.util.math.MatrixStack;

public record Render2dEvent(MatrixStack matrixStack, float tickDelta) implements BaseEvent
{
	public float tickDelta() { return tickDelta;}

	public MatrixStack getMatrixStack()
	{
		return matrixStack;
	}
}
