package github.thesivlerecho.zeropoint.render.shader;

import github.thesivlerecho.zeropoint.render.shader.programs.*;

import java.util.function.Supplier;

public enum ZeroPointShader
{
	ROUND_RECT(RoundedRectangleShader::new), CIRCLE(CircleShader::new), CONTRAST_POST_P(ContrastPostprocessShader::new), BLUR(BlurPostprocessShader::new), BLUR2(BlurPostprocessShaderSize::new);

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
