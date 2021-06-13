package github.thesivlerecho.zeropoint.render.shader;

import github.thesivlerecho.zeropoint.render.shader.programs.CircleShader;
import github.thesivlerecho.zeropoint.render.shader.programs.RoundedRectangleShader;
import github.thesivlerecho.zeropoint.render.shader.programs.TestShader;

import java.util.function.Supplier;

public enum ZeroPointShader
{
	ROUND_RECT(RoundedRectangleShader::new), CIRCLE(CircleShader::new), TEST(TestShader::new);

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
