package github.thesivlerecho.zeropoint.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.ZeroPointClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.UUID;

@Mixin(BossBarHud.class)
public abstract class BossBarHudMixin extends DrawableHelper
{
	private final Identifier BACK = new Identifier(ZeroPointClient.MOD_ID, "textures/ui/bar/back.png");
	private final Identifier HEALTH = new Identifier(ZeroPointClient.MOD_ID, "textures/ui/bar/health.png");
	private final Identifier TOP = new Identifier(ZeroPointClient.MOD_ID, "textures/ui/bar/top.png");

	@Shadow
	@Final
	private Map<UUID, ClientBossBar> bossBars;
	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
	public void renderNewHud(MatrixStack matrices, CallbackInfo ci)
	{
//		MathHelper.
		ci.cancel();
		if (!this.bossBars.isEmpty())
		{
			int i = this.client.getWindow().getScaledWidth();
			int j = 0;

			for (ClientBossBar clientBossBar : this.bossBars.values())
			{
				int k = i / 2 - 160;
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				this.renderBossBarNew(matrices, k, j, clientBossBar);
				Text text = clientBossBar.getName();
				int m = this.client.textRenderer.getWidth(text);
				int n = i / 2 - m / 2;
				int o = 3;
				this.client.textRenderer.drawWithShadow(matrices, text, (float) n, (float) o, 16777215);
				j += 10 + 9;
				if (j >= this.client.getWindow().getScaledHeight() / 3)
				{
					break;
				}
			}

		}

	}

	private void renderBossBarNew(MatrixStack matrices, int x, int y, ClientBossBar bossBar)
	{
		float percent = bossBar.getPercent();
		RenderSystem.setShaderTexture(0, BACK);
		drawTexture(matrices, x, (int) ((float) y * 1.25F), 0, 0.0F, 0.0F, 320, 40, 40, 320);
		RenderSystem.setShaderTexture(0, HEALTH);
		drawTexture(matrices, x + 35, (int) ((float) y * 1.25F), 0, 0.0F, 0.0F, (int) (percent * 250.0F), 40, 40, 250);
		RenderSystem.setShaderTexture(0, TOP);
		drawTexture(matrices, x, (int) ((float) y * 1.25F), 0, 0.0F, 0.0F, 320, 40, 40, 320);


	}
}
