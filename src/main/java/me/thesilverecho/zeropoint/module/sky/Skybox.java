package me.thesilverecho.zeropoint.module.sky;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import me.thesilverecho.zeropoint.ZeroPointClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class Skybox
{


	//	private static final Identifier textureSkybox = new Identifier(LibResources.MISC_SKYBOX);
	private static final Identifier textureRainbow = new Identifier(ZeroPointClient.MOD_ID, "textures/r.png");
//	private static final Identifier[] planetTextures = new Identifier[]{
//			new Identifier(LibResources.MISC_PLANET + "0.png"),
//			new Identifier(LibResources.MISC_PLANET + "1.png"),
//			new Identifier(LibResources.MISC_PLANET + "2.png"),
//			new Identifier(LibResources.MISC_PLANET + "3.png"),
//			new Identifier(LibResources.MISC_PLANET + "4.png"),
//			new Identifier(LibResources.MISC_PLANET + "5.png")
//};

	public static void renderExtra(MatrixStack ms, ClientWorld world, float partialTicks, float insideVoid)
	{
		Tessellator tessellator = Tessellator.getInstance();
		float rain = 1.0F - world.getRainGradient(partialTicks);
		float celAng = world.getSkyAngle(partialTicks);
		float effCelAng = celAng;
		if (celAng > 0.5)
		{
			effCelAng = 0.5F - (celAng - 0.5F);
		}

		// === Planets
		float scale = 20F;
		float lowA = Math.max(0F, effCelAng - 0.3F) * rain;
		float a = Math.max(0.1F, lowA);

		RenderSystem.blendFuncSeparate(770, 771, 1, 0);
		/*ms.push();
		RenderSystem.color4f(1F, 1F, 1F, a * 4 * (1F - insideVoid));
		ms.multiply(new Vector3f(0.5F, 0.5F, 0F).getDegreesQuaternion(90));
		for (int p = 0; p < planetTextures.length; p++)
		{
			Minecraft.getInstance().textureManager.bindTexture(planetTextures[p]);
			Matrix4f mat = ms.getLast().getMatrix();
			tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			tessellator.getBuffer().vertex(mat, -scale, 100, -scale).texture(0.0F, 0.0F).next();
			tessellator.getBuffer().vertex(mat, scale, 100, -scale).texture(1.0F, 0.0F).next();
			tessellator.getBuffer().vertex(mat, scale, 100, scale).texture(1.0F, 1.0F).next();
			tessellator.getBuffer().vertex(mat, -scale, 100, scale).texture(0.0F, 1.0F).next();
			tessellator.draw();

			switch (p)
			{
				case 0:
					ms.rotate(Vector3f.XP.rotationDegrees(70));
					scale = 12F;
					break;
				case 1:
					ms.rotate(Vector3f.ZP.rotationDegrees(120));
					scale = 15F;
					break;
				case 2:
					ms.rotate(new Vector3f(1, 0, 1).rotationDegrees(80));
					scale = 25F;
					break;
				case 3:
					ms.rotate(Vector3f.ZP.rotationDegrees(100));
					scale = 10F;
					break;
				case 4:
					ms.rotate(new Vector3f(1, 0, 0.5F).rotationDegrees(-60));
					scale = 40F;
			}
		}
		RenderSystem.color4f(1F, 1F, 1F, 1F);
		ms.pop();*/

		/*// === Rays
		MinecraftClient.getInstance().getTextureManager().bindTexture(textureSkybox);

		scale = 20F;
		a = lowA;
		ms.push();
		RenderSystem.blendFuncSeparate(770, 1, 1, 0);
		ms.translate(0, -1, 0);
		ms.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(220));
		RenderSystem.color4f(1F, 1F, 1F, a);
		int angles = 90;
		float y = 2F;
		float y0 = 0F;
		float uPer = 1F / 360F;
		float anglePer = 360F / angles;
		double fuzzPer = Math.PI * 10 / angles;
		float rotSpeed = 1F;
		float rotSpeedMod = 0.4F;

		for (int p = 0; p < 3; p++)
		{
			float baseAngle = rotSpeed * rotSpeedMod * (ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks);
			ms.rotate(Vector3f.YP.rotationDegrees((ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks) * 0.25F * rotSpeed * rotSpeedMod));

			Matrix4f mat = ms.peek().getModel();
			tessellator.getBuffer().begin(GL11.GL_QUADS, VertexFormats.POSITION_TEXTURE);
			for (int i = 0; i < angles; i++)
			{
				int j = i;
				if (i % 2 == 0)
				{
					j--;
				}

				float ang = j * anglePer + baseAngle;
				float xp = (float) Math.cos(ang * Math.PI / 180F) * scale;
				float zp = (float) Math.sin(ang * Math.PI / 180F) * scale;
				float yo = (float) Math.sin(fuzzPer * j) * 1;

				float ut = ang * uPer;
				if (i % 2 == 0)
				{
					tessellator.getBuffer().vertex(mat, xp, yo + y0 + y, zp).texture(ut, 1F).next();
					tessellator.getBuffer().vertex(mat, xp, yo + y0, zp).texture(ut, 0).next();
				} else
				{
					tessellator.getBuffer().vertex(mat, xp, yo + y0, zp).texture(ut, 0).next();
					tessellator.getBuffer().vertex(mat, xp, yo + y0 + y, zp).texture(ut, 1F).next();
				}

			}
			tessellator.draw();

			switch (p)
			{
				case 0:
					ms.rotate(Vector3f.XP.rotationDegrees(20));
					RenderSystem.color4f(1F, 0.4F, 0.4F, a);
					fuzzPer = Math.PI * 14 / angles;
					rotSpeed = 0.2F;
					break;
				case 1:
					ms.rotate(Vector3f.XP.rotationDegrees(50));
					RenderSystem.color4f(0.4F, 1F, 0.7F, a);
					fuzzPer = Math.PI * 6 / angles;
					rotSpeed = 2F;
					break;
			}
		}
		ms.pop();*/
		int angles = 90;
		float y = 2F;
		float y0 = 0F;
		float uPer = 1F / 360F;
		float anglePer = 360F / angles;
		// === Rainbow
		ms.push();
		GlStateManager.blendFuncSeparate(770, 771, 1, 0);
		MinecraftClient.getInstance().getTextureManager().bindTexture(textureRainbow);
		scale = 10F;
		float effCelAng1 = celAng;
		if (effCelAng1 > 0.25F)
		{
			effCelAng1 = 1F - effCelAng1;
		}
		effCelAng1 = 0.25F - Math.min(0.25F, effCelAng1);

		long time = world.getTimeOfDay() + 1000;
		int day = (int) (time / 24000L);
		Random rand = new Random(day * 0xFF);
		float angle1 = rand.nextFloat() * 360F;
		float angle2 = rand.nextFloat() * 360F;
		RenderSystem.color4f(1F, 1F, 1F, effCelAng1 * (1F - insideVoid));
		ms.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(angle1));
		ms.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(angle2));

		Matrix4f mat = ms.peek().getModel();
		tessellator.getBuffer().begin(GL11.GL_QUADS, VertexFormats.POSITION_TEXTURE);
		for (int i = 0; i < angles; i++)
		{
			int j = i;
			if (i % 2 == 0)
			{
				j--;
			}

			float ang = j * anglePer;
			float xp = (float) Math.cos(ang * Math.PI / 180F) * scale;
			float zp = (float) Math.sin(ang * Math.PI / 180F) * scale;
			float yo = 0;

			float ut = ang * uPer;
			if (i % 2 == 0)
			{
				tessellator.getBuffer().vertex(mat, xp, yo + y0 + y, zp).texture(ut, 1F).next();
				tessellator.getBuffer().vertex(mat, xp, yo + y0, zp).texture(ut, 0).next();
			} else
			{
				tessellator.getBuffer().vertex(mat, xp, yo + y0, zp).texture(ut, 0).next();
				tessellator.getBuffer().vertex(mat, xp, yo + y0 + y, zp).texture(ut, 1F).next();
			}

		}
		tessellator.draw();
		ms.pop();
		RenderSystem.color4f(1F, 1F, 1F, 1F - insideVoid);
		GlStateManager.blendFuncSeparate(770, 1, 1, 0);
	}

//	public static void renderStars(VertexFormat format, VertexBuffer starVBO, MatrixStack ms, float partialTicks)
//	{
//		Minecraft mc = Minecraft.getInstance();
//		float rain = 1.0F - mc.world.getRainStrength(partialTicks);
//		float celAng = mc.world.func_242415_f(partialTicks);
//		float effCelAng = celAng;
//		if (celAng > 0.5)
//		{
//			effCelAng = 0.5F - (celAng - 0.5F);
//		}
//		float alpha = rain * Math.max(0.1F, effCelAng * 2);
//
//		float t = (ClientTickHandler.ticksInGame + partialTicks + 2000) * 0.005F;
//		ms.push();
//
//		starVBO.bindBuffer();
//		format.setupBufferState(0);
//
//		ms.push();
//		ms.rotate(Vector3f.YP.rotationDegrees(t * 3));
//		RenderSystem.color4f(1F, 1F, 1F, alpha);
//		starVBO.draw(ms.getLast().getMatrix(), GL11.GL_QUADS);
//		ms.pop();
//
//		ms.push();
//		ms.rotate(Vector3f.YP.rotationDegrees(t));
//		RenderSystem.color4f(0.5F, 1F, 1F, alpha);
//		starVBO.draw(ms.getLast().getMatrix(), GL11.GL_QUADS);
//		ms.pop();
//
//		ms.push();
//		ms.rotate(Vector3f.YP.rotationDegrees(t * 2));
//		RenderSystem.color4f(1F, 0.75F, 0.75F, alpha);
//		starVBO.draw(ms.getLast().getMatrix(), GL11.GL_QUADS);
//		ms.pop();
//
//		ms.push();
//		ms.rotate(Vector3f.ZP.rotationDegrees(t * 3));
//		RenderSystem.color4f(1F, 1F, 1F, 0.25F * alpha);
//		starVBO.draw(ms.getLast().getMatrix(), GL11.GL_QUADS);
//		ms.pop();
//
//		ms.push();
//		ms.rotate(Vector3f.ZP.rotationDegrees(t));
//		RenderSystem.color4f(0.5F, 1F, 1F, 0.25F * alpha);
//		starVBO.draw(ms.getLast().getMatrix(), GL11.GL_QUADS);
//		ms.pop();
//
//		ms.push();
//		ms.rotate(Vector3f.ZP.rotationDegrees(t * 2));
//		RenderSystem.color4f(1F, 0.75F, 0.75F, 0.25F * alpha);
//		starVBO.draw(ms.getLast().getMatrix(), GL11.GL_QUADS);
//		ms.pop();
//
//		ms.pop();
//
//		VertexBuffer.unbindBuffer();
//		format.clearBufferState();
//	}

}
