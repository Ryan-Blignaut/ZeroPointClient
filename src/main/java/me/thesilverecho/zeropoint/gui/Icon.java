package me.thesilverecho.zeropoint.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

public class Icon
{
	private final Identifier resource;

	private AbstractTexture texture;

	private final float u1;
	private final float v1;
	private final float u2;
	private final float v2;

	private final boolean mm;
	private final boolean bl;

	public Icon(Identifier resource, float u1, float v1, float u2, float v2, boolean mm, boolean bl)
	{
		this.resource = resource;
		this.u1 = u1;
		this.v1 = v1;
		this.u2 = u2;
		this.v2 = v2;
		this.mm = mm;
		this.bl = bl;
	}

	private void loadTexture()
	{
		TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();
		AbstractTexture texture = textureManager.getTexture(resource);

		if (texture == null)
		{
			texture = new ResourceTexture(resource);
			textureManager.registerTexture(resource, texture);
		}
		this.texture = texture;
	}

	public void bindTexture()
	{
		if (texture == null)
		{
			loadTexture();
			texture.bindTexture();
			texture.setFilter(bl, mm);
		} else
		{
			texture.bindTexture();
		}
	}

	public float getLeft()
	{
		return u1;
	}

	public float getTop()
	{
		return v1;
	}

	public float getRight()
	{
		return u2;
	}

	public float getBottom()
	{
		return v2;
	}

}
