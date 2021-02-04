package github.thesivlerecho.zeropoint.shader;

import github.thesivlerecho.zeropoint.ZeroPointClient;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.opengl.ARBShaderObjects;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class CosmicShader extends Shader
{
	public static boolean inventoryRender;

	public CosmicShader()
	{
		super("cosmic.vert", "cosmic.frag");
		create();
	}

	public void bind()
	{
		glUseProgram(proId);
		float yaw = 0;
		float pitch = 0;
		float scale = 1.0f;

		MinecraftClient mc = MinecraftClient.getInstance();
		if (mc.player != null && mc.player.world != null)
		{
			int time = ARBShaderObjects.glGetUniformLocationARB(proId, "time");
			ARBShaderObjects.glUniform1iARB(time, (int) (mc.player.world.getTime() % Integer.MAX_VALUE));
		}

		if (!inventoryRender)
		{
			yaw = (float) ((mc.player.renderYaw * 2 * Math.PI) / 360.0);
			pitch = -(float) ((mc.player.renderPitch * 2 * Math.PI) / 360.0);
		} else
		{
			scale = 25.0f;
		}

		int x = ARBShaderObjects.glGetUniformLocationARB(proId, "yaw");
		ARBShaderObjects.glUniform1fARB(x, yaw);

		int z = ARBShaderObjects.glGetUniformLocationARB(proId, "pitch");
		ARBShaderObjects.glUniform1fARB(z, pitch);

		int l = ARBShaderObjects.glGetUniformLocationARB(proId, "lightlevel");
		ARBShaderObjects.glUniform3fARB(l, 1, 1, 1);

		int lightmix = ARBShaderObjects.glGetUniformLocationARB(proId, "lightmix");
		ARBShaderObjects.glUniform1fARB(lightmix, 0.2f);

		int uvs = ARBShaderObjects.glGetUniformLocationARB(proId, "cosmicuvs");
		ARBShaderObjects.glUniformMatrix2fvARB(uvs, false, ZeroPointClient.cosmicUVs);

		int s = ARBShaderObjects.glGetUniformLocationARB(proId, "externalScale");
		ARBShaderObjects.glUniform1fARB(s, scale);

		int o = ARBShaderObjects.glGetUniformLocationARB(proId, "opacity");
		ARBShaderObjects.glUniform1fARB(o, 1);
	}
}
