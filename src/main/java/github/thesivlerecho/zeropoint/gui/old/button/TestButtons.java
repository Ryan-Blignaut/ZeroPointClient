package github.thesivlerecho.zeropoint.gui.old.button;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;

import java.awt.*;

public class TestButtons
{
	public static void drawButton(MatrixStack matrix, float xCenter, float yCenter, int mouseX, int mouseY, int index, float pTicks)
	{
		float openTick = 1;
		float a = .5F * openTick;

		float hd = 40;
//		boolean hover = false   ;
		boolean hover = Math.abs(mouseX - xCenter) + Math.abs(mouseY - yCenter) < hd + 10;

		float r, g, b;
		float[] scales = new float[4];
		if (hover)
		{
			int color = Color.pink.getRGB();
			r = Math.max(.1F, (color >> 16 & 255) / 255.0F * .2f * scales[index]);
			g = Math.max(.1F, (color >> 8 & 255) / 255.0F * .2f * scales[index]);
			b = Math.max(.1F, (color & 255) / 255.0F * .2f * scales[index]);
		} else
		{
			r = .1F;
			g = .1F;
			b = .1F;
		}
		Matrix4f matrix4f = matrix.peek().getModel();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableTexture();
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, xCenter - hd, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter, yCenter + hd, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter + hd, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter, yCenter - hd, 0.0F).color(r, g, b, a).next();
		tessellator.draw();

		a = .75f;
		if (hover)
		{
			int color = Color.pink.getRGB();
			scales[index] = 0.92f;
			float scale = scales[index];
			r = Math.max(.1F, (color >> 16 & 255) / 255.0F * scale);
			g = Math.max(.1F, (color >> 8 & 255) / 255.0F * scale);
			b = Math.max(.1F, (color & 255) / 255.0F * scale);
		}

		float hdborder = hd + 3;

		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, xCenter, yCenter - hd, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter + hd, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter + hdborder, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter, yCenter - hdborder, 0.0F).color(r, g, b, a).next();
		tessellator.draw();
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, xCenter, yCenter + hdborder, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter + hdborder, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter + hd, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter, yCenter + hd, 0.0F).color(r, g, b, a).next();
		tessellator.draw();
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, xCenter, yCenter + hd, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter - hd, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter - hdborder, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter, yCenter + hdborder, 0.0F).color(r, g, b, a).next();
		tessellator.draw();
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, xCenter, yCenter - hdborder, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter - hdborder, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter - hd, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter, yCenter - hd, 0.0F).color(r, g, b, a).next();
		tessellator.draw();

		float hdshadow;
		if (hover)
		{
			a = .3f * openTick;
			hdshadow = hdborder + 6;
		} else
		{
			a = .2f * openTick;
			hdshadow = hdborder + 5;
		}
		r = .1F;
		g = .1F;
		b = .1F;

//		RenderSystem.disableAlphaTest();
//		RenderSystem.shadeModel(GL11.GL_SMOOTH);
/*		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, xCenter, yCenter - hdborder, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter + hdborder, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter + hdshadow, yCenter, 0.0F).color(r, g, b, 0).next();
		buffer.vertex(matrix4f, xCenter, yCenter - hdshadow, 0.0F).color(r, g, b, 0).next();
		tessellator.draw();
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, xCenter, yCenter + hdshadow, 0.0F).color(r, g, b, 0).next();
		buffer.vertex(matrix4f, xCenter + hdshadow, yCenter, 0.0F).color(r, g, b, 0).next();
		buffer.vertex(matrix4f, xCenter + hdborder, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter, yCenter + hdborder, 0.0F).color(r, g, b, a).next();
		tessellator.draw();
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, xCenter, yCenter + hdborder, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter - hdborder, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter - hdshadow, yCenter, 0.0F).color(r, g, b, 0).next();
		buffer.vertex(matrix4f, xCenter, yCenter + hdshadow, 0.0F).color(r, g, b, 0).next();
		tessellator.draw();
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, xCenter, yCenter - hdshadow, 0.0F).color(r, g, b, 0).next();
		buffer.vertex(matrix4f, xCenter - hdshadow, yCenter, 0.0F).color(r, g, b, 0).next();
		buffer.vertex(matrix4f, xCenter - hdborder, yCenter, 0.0F).color(r, g, b, a).next();
		buffer.vertex(matrix4f, xCenter, yCenter - hdborder, 0.0F).color(r, g, b, a).next();
		tessellator.draw();*/
//		RenderSystem.shadeModel(GL11.GL_FLAT);
//		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();

		matrix.push();
		RenderSystem.setShaderColor(1, 1, 1, openTick);
		int textAlpha = (int) (openTick * 255);
		int textColor = textAlpha << 24 | 0xffffff;

		String name;

		matrix.pop();
	}

}
