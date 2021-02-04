package github.thesivlerecho.zeropoint.gui.widgets;

import net.minecraft.client.util.math.MatrixStack;

public class BasicElement extends AbstractElement
{

	public BasicElement(float x, float y, float w, float h)
	{
		super(x, y, w, h);
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta)
	{
		super.render(matrixStack, mouseX, mouseY, delta);
	}
}
