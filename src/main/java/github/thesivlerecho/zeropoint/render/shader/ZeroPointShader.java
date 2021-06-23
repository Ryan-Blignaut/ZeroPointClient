package github.thesivlerecho.zeropoint.render.shader;

import github.thesivlerecho.zeropoint.render.shader.programs.CircleLineShader;
import github.thesivlerecho.zeropoint.render.shader.programs.CircleShader;
import github.thesivlerecho.zeropoint.render.shader.programs.RoundedRectangleShader;
import github.thesivlerecho.zeropoint.render.shader.programs.RoundedRectangleTextureShader;

import java.util.function.Supplier;

public enum ZeroPointShader
{
	ROUND_RECT(RoundedRectangleShader::new), ROUND_RECT_TEXTURE(RoundedRectangleTextureShader::new), CIRCLE(CircleShader::new), CIRCLE_LINE(CircleLineShader::new);

	private final Supplier<? extends Shader> supplier;

	ZeroPointShader(Supplier<? extends Shader> supplier)
	{
		this.supplier = supplier;
	}

	public Supplier<? extends Shader> getSupplier()
	{
		return supplier;
	}

}
