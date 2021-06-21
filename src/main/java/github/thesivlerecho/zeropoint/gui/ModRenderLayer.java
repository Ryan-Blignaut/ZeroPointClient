package github.thesivlerecho.zeropoint.gui;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;

public class ModRenderLayer extends RenderLayer
{

	public ModRenderLayer(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction)
	{
		super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
	}




}
