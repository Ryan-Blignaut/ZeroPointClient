/*
package github.thesivlerecho.zeropoint.particle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

public class FancyParticle extends Particle
{
	public BlockPos pos;
	Block block;
	BlockState blockState;
	BlockModelRenderer mr;
	BakedModel modelPrefab;
	MinecraftClient mc;
	Direction facing;
//	FBPVector3d prevRot;
//	FBPVector3d rot;
	long textureSeed;
	float startingHeight;
	float startingAngle;
	float step = 0.00275F;
	float field_187135_o;
	float prevHeight;
	float smoothHeight;
	boolean lookingUp;
	boolean spawned = false;
	long tick = -1L;
	boolean blockSet = false;
//	TileEntity tileEntity;

	protected FancyParticle(ClientWorld world, double x, double y, double z)
	{
		super(world, x, y, z);

		this.pos = new BlockPos(x, y, z);
		this.mc = MinecraftClient.getInstance();
		this.facing = this.mc.field_71439_g.func_174811_aO();
		this.lookingUp = Float.valueOf(MathHelper.func_76142_g(this.mc.field_71439_g.field_70125_A)) <= 0.0F;
		this.prevHeight = this.field_187135_o = this.startingHeight = (float)FBP.random.nextDouble(0.065D, 0.115D);
		this.startingAngle = (float)FBP.random.nextDouble(0.03125D, 0.0635D);
		this.prevRot = new FBPVector3d();
		this.rot = new FBPVector3d();


	}

	public FancyParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ)
	{
		super(world, x, y, z, velocityX, velocityY, velocityZ);
	}

	@Override
	public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta)
	{

	}

	@Override
	public ParticleTextureSheet getType()
	{
		return null;
	}
}
*/
