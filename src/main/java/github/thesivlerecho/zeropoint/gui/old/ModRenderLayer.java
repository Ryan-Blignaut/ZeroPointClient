/*
package github.thesivlerecho.zeropoint.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.ZeroPointClient;
import github.thesivlerecho.zeropoint.shader.CosmicShader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModRenderLayer extends RenderLayer
{
	public static CosmicShader shader = new CosmicShader();
	public static RenderLayer POT_OVERLAY;
	public static RenderLayer EXTREME_OVER;
	private static boolean active = false;

	static
	{
		MultiPhaseParameters transparency = MultiPhaseParameters.builder().texture(new Texture(ItemRenderer.ENCHANTED_ITEM_GLINT, true, false)).writeMaskState(COLOR_MASK).cull(DISABLE_CULLING).depthTest(LEQUAL_DEPTH_TEST).transparency(GLINT_TRANSPARENCY).texturing(new Texturing("glint_transparency", () ->
				setupGlintTexturingCustom(8.0F), () ->
		{
			RenderSystem.matrixMode(5890);
			RenderSystem.popMatrix();
			RenderSystem.matrixMode(5888);
		})).build(true);

		MultiPhaseParameters extremeOver = MultiPhaseParameters.builder().writeMaskState(COLOR_MASK).cull(DISABLE_CULLING).depthTest(EQUAL_DEPTH_TEST).transparency(GLINT_TRANSPARENCY).texturing(new Texturing("glint_transparency", () ->
		{
			if (!active)
			{
				for (int i = 0; i < ZeroPointClient.sprites.length; i++)
				{
					ZeroPointClient.sprites[i] = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).apply(new Identifier(ZeroPointClient.MOD_ID, "cosmic/cosmic_" + i))*/
/*.apply(new Identifier(ZeroPointClient.MOD_ID, "cosmic/cosmic_" + i))*//*
;
					active = true;
					Sprite sprite = ZeroPointClient.sprites[i];
//				Sprite sprite = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).apply(new Identifier(ZeroPointClient.MOD_ID, "cosmic/cosmic_" + i));

					ZeroPointClient.cosmicUVs.put(sprite.getMinU());
					ZeroPointClient.cosmicUVs.put(sprite.getMinV());
					ZeroPointClient.cosmicUVs.put(sprite.getMaxU());
					ZeroPointClient.cosmicUVs.put(sprite.getMaxV());
				}
				ZeroPointClient.cosmicUVs.flip();
			}
			shader.bind();
		}, shader::unBind)).build(false);

		POT_OVERLAY = RenderLayer.of("extreme", VertexFormats.POSITION_TEXTURE, 7, 256, transparency);
		EXTREME_OVER = RenderLayer.of("extreme_over", VertexFormats.POSITION_TEXTURE, 7, 256, extremeOver);

	}

	public ModRenderLayer(String name, VertexFormat vertexFormat, int drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction)
	{
		super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
	}

	private static void setupGlintTexturingCustom(float scale)
	{
		RenderSystem.matrixMode(5890);
		RenderSystem.pushMatrix();
		RenderSystem.loadIdentity();
		long l = Util.getMeasuringTimeMs() * 8L;
		float f = (float) (l % 110000L) / 110000.0F;
		float g = (float) (l % 30000L) / 30000.0F;
		RenderSystem.translatef(-f, g, 0.0F);
		RenderSystem.rotatef(10.0F, 0.0F, 0.0F, 1.0F);
		RenderSystem.scalef(scale, scale, scale);
		RenderSystem.matrixMode(5888);
	}
}
*/
