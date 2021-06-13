package github.thesivlerecho.zeropoint.module.render;

import github.thesivlerecho.zeropoint.gui.GuiHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;

import java.awt.*;

public class Radar
{
	private static final MinecraftClient mc = MinecraftClient.getInstance();

	public static void Render(MatrixStack matrixStack)
	{
		final Window window = MinecraftClient.getInstance().getWindow();
		final int scaledHeight = window.getScaledHeight();
		final int scaledWidth = window.getScaledWidth();

		int size = 200;
		float xOffset = 0;
		float yOffset = 0;
		float playerOffsetX = (float) mc.player.getX();
		float playerOffSetZ = (float) mc.player.getZ();

		float r = 0;
		float g = 85.0f;
		float b = 170.0f;

            /*this.hue =(float)((double)this.hue +0.1);
            RenderingUtil.rectangleBordered(xOffset,yOffset,xOffset +(float)size,yOffset +(float)size,0.5,Colors.getColor(90),Colors.getColor(0));
            RenderingUtil.rectangleBordered(xOffset +1.0f,yOffset +1.0f,xOffset +(float)size -1.0f,yOffset +(float)size -1.0f,1.0,Colors.getColor(90),Colors.getColor(61));
            RenderingUtil.rectangleBordered((double)xOffset +2.5,(double)yOffset +2.5,(double)(xOffset +(float)size)-2.5,(double)(yOffset +(float)size)-2.5,0.5,Colors.getColor(61),Colors.getColor(0));
            RenderingUtil.rectangleBordered(xOffset +3.0f,yOffset +3.0f,xOffset +(float)size -3.0f,yOffset +(float)size -3.0f,0.5,Colors.getColor(27),Colors.getColor(61));
            RenderingUtil.drawGradientSideways(xOffset +3.0f,yOffset +3.0f,xOffset +(float)(size /2),(double)yOffset +3.6,color1,color2);
            RenderingUtil.drawGradientSideways(xOffset +(float)(size /2),yOffset +3.0f,xOffset +(float)size -3.0f,(double)yOffset +3.6,color2,color3);
            RenderingUtil.rectangle((double)xOffset +((double)(size /2)-0.5),(double)yOffset +3.5,(double)xOffset +((double)(size /2)+0.5),(double)(yOffset +(float)size)-3.5,Colors.getColor(255,80));
            RenderingUtil.rectangle((double)xOffset +3.5,(double)yOffset +((double)(size /2)-0.5),(double)(xOffset +(float)size)-3.5,(double)yOffset +((double)(size /2)+0.5),Colors.getColor(255,80));*/

		for (PlayerEntity p : mc.world.getPlayers())
		{
			//			System.out.println(p.getDisplayName());
		/*	if (!(o instanceof EntityPlayer) || !(ent = (EntityPlayer) o).isEntityAlive() || ent == Radar.mc.thePlayer || ent.isInvisible() || ent
					.isInvisibleToPlayer(Radar.mc.thePlayer))
				continue;*/
			float pTicks = mc.getTickDelta()/*Radar.mc.timer.renderPartialTicks*/;
			float posX = (float) ((p.getX() + (p.getX() - p.prevX) * (double) pTicks - (double) playerOffsetX) * (2));
			float posZ = (float) ((p.getZ() + (p.getZ() - p.prevZ/*lastTickPosZ*/) * (double) pTicks - (double) playerOffSetZ) * 2);
			int color = Color.red.getRGB();/*FriendManager.isFriend(ent.getName()) ? (Radar.mc.thePlayer.canEntityBeSeen(ent) ?
					Colors.getColor(0, 195, 255) :
					Colors.getColor(0, 195, 255)) : (Radar.mc.thePlayer.canEntityBeSeen(ent) ? Colors.getColor(255, 0, 0) : Colors.getColor(255, 255,
					0));*/
			float cos = (float) Math.cos((double) Radar.mc.player.renderYaw/*rotationYaw*/ * 0.017453292519943295);
			float sin = (float) Math.sin((double) Radar.mc.player.renderYaw/*rotationYaw*/ * 0.017453292519943295);
			float rotY = -posZ * cos - posX * sin;
			float rotX = -posX * cos + posZ * sin;
			if (rotY > (float) (size / 2 - 5))
			{
				rotY = (float) (size / 2) - 5.0f;
			} else if (rotY < (float) (-size / 2 - 5))
			{
				rotY = -size / 2 - 5;
			}
			if (rotX > (float) (size / 2) - 5.0f)
			{
				rotX = size / 2 - 5;
			} else if (rotX < (float) (-size / 2 - 5))
			{
				rotX = -(float) (size / 2) - 5.0f;
			}
			GuiHelper.drawRect(matrixStack, (xOffset + (float) (size / 2) + rotX) - 1.5f, (yOffset + (float) (size / 2) + rotY) - 1.5f,
					xOffset + (float) (size / 2) + rotX + 1.5f, yOffset + (float) (size / 2) + rotY + 1.5f, color);
		/*	RenderingUtil.rectangleBordered((double) (xOffset + (float) (size / 2) + rotX) - 1.5,
					(double) (yOffset + (float) (size / 2) + rotY) - 1.5, (double) (xOffset + (float) (size / 2) + rotX) + 1.5,
					(double) (yOffset + (float) (size / 2) + rotY) + 1.5, 0.5, color, Colors.getColor(46));*/

		}

	}
}
