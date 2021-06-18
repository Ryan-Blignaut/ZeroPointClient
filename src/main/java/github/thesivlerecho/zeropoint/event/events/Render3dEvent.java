package github.thesivlerecho.zeropoint.event.events;

import github.thesivlerecho.zeropoint.event.BaseEvent;
import net.minecraft.client.util.math.MatrixStack;

public record Render3dEvent(MatrixStack matrixStack) implements BaseEvent
{
	public MatrixStack getMatrixStack()
	{
		return matrixStack;
	}
}
