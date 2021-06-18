package github.thesivlerecho.zeropoint.mixin;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldRenderer.class)
public abstract class NameTagMixin
{
//	private static final Identifier TEXTURE = new Identifier("tweaks", "textures/ui/default_health_bar.png");
//	private final Tessellator instance = Tessellator.getInstance();
//	private final BufferBuilder bufferBuilder = instance.getBuffer();
//	HashMap<Byte, ItemStack> hashMap = new HashMap<>();
//
//	/*private static void renderHealthBar(ItemEntity passedEntity, MatrixStack matrices, float partialTicks, Camera camera, Entity viewPoint)
//	{
//		MinecraftClient mc = MinecraftClient.getInstance();
//		matrices.push();
//		{
//			double x = passedEntity.prevX + (passedEntity.getX() - passedEntity.prevX) * partialTicks;
//			double y = passedEntity.prevY + (passedEntity.getY() - passedEntity.prevY) * partialTicks;
//			double z = passedEntity.prevZ + (passedEntity.getZ() - passedEntity.prevZ) * partialTicks;
//			EntityRenderDispatcher renderManager = mc.getEntityRenderDispatcher();
//			matrices.push();
//			{
//				matrices.translate(x - renderManager.camera.getPos().x, y - renderManager.camera.getPos().y + passedEntity.getHeight() + 1,
//						z - renderManager.camera.getPos().z);
//				GL11.glNormal3f(0.0F, 1.0F, 0.0F);
//				RenderSystem.disableLighting();
//				VertexConsumerProvider.Immediate immediate = mc.getBufferBuilders().getEntityVertexConsumers();
//				final int light = 0xF000F0;
//				renderEntity(matrices, immediate, camera, passedEntity, light);
//			}
//			matrices.pop();
//			//			matrices.translate(0, 1, 0);
//		}
//		matrices.pop();
//
//	}*/
//
//	private static int getRed(int argb)
//	{
//		return (argb >> 16) & 0xFF;
//	}
//
//	private static int getGreen(int argb)
//	{
//		return (argb >> 8) & 0xFF;
//	}
//
//	private static int getBlue(int argb)
//	{
//		return argb & 0xFF;
//	}
//
//	private static void renderEntity(MatrixStack matrices, VertexConsumerProvider.Immediate immediate, Camera camera, ItemEntity passedEntity,
//	                                 int light)
//	{
//		String toDraw = /*passedEntity.getStack().getCount() + " " +*/ passedEntity.getStack().toString();//.getItem().getName().asString();
//		if (!toDraw.isEmpty())
//		{
//			MinecraftClient mc = MinecraftClient.getInstance();
//			Quaternion rotation = camera.getRotation().copy();
//			rotation.scale(-1.0F);
//			matrices.multiply(rotation);
//			float scale = 0.026666672F;
//			matrices.scale(-scale, -scale, -scale);
//			float size = 3;
//			float textScale = 2 * 0.5F;
//
//			//		matrices.scale(-scale, -scale, -scale);
//			float namel = mc.textRenderer.getWidth(toDraw) * textScale;
//			if (namel + 20 > size * 2)
//			{
//				size = namel / 2.0F + 10.0F;
//			}
//
//			MatrixStack.Entry entry = matrices.peek();
//			Matrix4f modelViewMatrix = entry.getModel();
//
//			Vector3f normal = new Vector3f(0.0F, 1.0F, 0.0F);
//			normal.transform(entry.getNormal());
//			VertexConsumer buffer = immediate.getBuffer(RenderLayer.getEntityTranslucent(TEXTURE, false));
//			int defaultUv = OverlayTexture.DEFAULT_UV;
//			int col = getColor(passedEntity);
//			int red = getRed(col);
//			int green = getGreen(col);
//			int blue = getBlue(col);
//
//			buffer.vertex(modelViewMatrix, -size, -5, 0.0F).color(red, green, blue, 127).texture(0.0F, 0.0F).overlay(defaultUv).light(light).normal(
//					normal.getX(), normal.getY(), normal.getZ()).next();
//			buffer.vertex(modelViewMatrix, -size, 5, 0.0F).color(red, green, blue, 127).texture(0.0F, 0.5F).overlay(defaultUv).light(light).normal(
//					normal.getX(), normal.getY(), normal.getZ()).next();
//			buffer.vertex(modelViewMatrix, size, 5, 0.0F).color(red, green, blue, 127).texture(1.0F, 0.5F).overlay(defaultUv).light(light).normal(
//					normal.getX(), normal.getY(), normal.getZ()).next();
//			buffer.vertex(modelViewMatrix, size, -5, 0.0F).color(red, green, blue, 127).texture(1.0F, 0.0F).overlay(defaultUv).light(light).normal(
//					normal.getX(), normal.getY(), normal.getZ()).next();
//			matrices.push();
//			matrices.translate(-size, -3.5F, 0.0F);
//			matrices.scale(textScale, textScale, textScale);
//			Matrix4f model = matrices.peek().getModel();
//			mc.textRenderer.draw(toDraw, /*-mc.textRenderer.getStringWidth(toDraw)*/5/*(size / (textScale * 1))*/, 0, 0xFFFFFF, false, model,
//					immediate, false, 0x000000, light);
//			matrices.pop();
//			RenderSystem.disableBlend();
//			RenderSystem.enableDepthTest();
//			RenderSystem.depthMask(true);
//			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//		}
//	}
//
//	private static int getColor(Entity entity)
//	{
//		int r = 0;
//		int g = 255;
//		int b = 0;
//		if (entity instanceof ItemEntity)
//		{
//			r = 128;
//			g = 0;
//			b = 128;
//		}
//		return 0xff000000 | r << 16 | g << 8 | b;
//	}
//
//	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;checkEmpty(Lnet/minecraft/client/util/math/MatrixStack;)V", ordinal = 0))
//	private void render(
//			MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer,
//			LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, CallbackInfo callbackInfo)
//	{
//		StreamSupport.stream(MinecraftClient.getInstance().world.getEntities().spliterator(), false).filter(
//				entity -> entity instanceof PlayerEntity && entity.isAlive()).map(PlayerEntity.class::cast).forEach(
//				playerEntity -> render(playerEntity, matrices, tickDelta, camera));
//
//		StreamSupport.stream(MinecraftClient.getInstance().world.getEntities().spliterator(), false).filter(
//				entity -> entity instanceof ItemEntity && entity.isAlive()).map(ItemEntity.class::cast).filter(
//				itemEntity -> itemEntity.getStack().hasTag() && itemEntity.getStack().getItem() instanceof BlockItem && ((BlockItem) itemEntity
//						.getStack().getItem()).getBlock() instanceof ShulkerBoxBlock).forEach(
//				entity -> renderInventory(entity, matrices, tickDelta, camera)/* renderHealthBar(entity, matrices, tickDelta, camera,
//				camera.getFocusedEntity() != null ? camera.getFocusedEntity() : MinecraftClient.getInstance().player)*/);
//
//		//				HealthBarRenderer.render(matrices, tickDelta, camera, gameRenderer, lightmapTextureManager, matrix4f, this.capturedFrustum);
//
//	}
//
//	private void render(PlayerEntity entity, MatrixStack matrices, float tickDelta, Camera camera)
//	{
//		matrices.push();
//		{
//			Vec3d position = new Vec3d(entity.prevX + (entity.getX() - entity.prevX) * tickDelta,
//					entity.prevY + (entity.getY() - entity.prevY) * tickDelta, entity.prevZ + (entity.getZ() - entity.prevZ) * tickDelta);
//			EntityRenderDispatcher renderManager = MinecraftClient.getInstance().getEntityRenderDispatcher();
//			matrices.push();
//			{
//				matrices.translate(position.x - renderManager.camera.getPos().x, position.y - renderManager.camera.getPos().y,
//						position.z - renderManager.camera.getPos().z);
//				renderSkeleton(matrices, camera, entity);
//			}
//			matrices.pop();
//		}
//		matrices.pop();
//
//	}
//
//	private void renderSkeleton(MatrixStack matrices, Camera camera, PlayerEntity entity)
//	{
//		matrices.push();
//		{
//
//			//			PlayerEntityModel<PlayerEntity> entityPlayerEntityModel = new PlayerEntityModel<>(1f, false);
//			//			entity.getCollisionBox().
//			//			entity.lef
//			//			matrices.entity.yaw
//			//			Quaternion rotation = camera.getRotation().copy();
//			//			rotation.scale(-1.0F);
//			//			matrices.multiply(rotation);
//			//						GuiHelper.drawBorderedRect(matrices, -20, 0, 20, entity.getHeight()*12, 2, -1);
//
//			//			matrices.multiply(new Quaternion(0,1,0,System.currentTimeMillis()%1000));
//			//RenderSystem.rotatef(System.currentTimeMillis()%1000*360,1,0,0);
//
//			//			RenderHelper.draw(matrices,209.5f, 600, 600);
//
//			//			bufferBuilder.vertex(matrices.peek().getModel(), 0, 0, 0).color(1, 1, 1, 1).next();
//
//			//			bufferBuilder.vertex(matrices.peek().getModel(), 0, 2/*entity.getHeight()*/, 0).color(1, 1, 1, 1).next();
//			//			bufferBuilder.begin(GL11.GL_LINE_LOOP, VertexFormats.POSITION_COLOR);
//			//			for (int w = 0; w <= 360; w ++)
//			//			{
//			//				bufferBuilder.vertex(/*Rotation3.identity().getMatrix(),*/ (float) ( 3 * Math.cos(Math.toRadians(w))),
//			//						(float) ( 3 * Math.sin(Math.toRadians(w))), 0.0F).next();
//			//			}
//
//			//			bufferBuilder.vertex(matrices.peek().getModel(), 0.5f, 0, 0).color(1, 1, 1, 1).next();
//			//			bufferBuilder.vertex(matrices.peek().getModel(), 0.5f, 2/*entity.getHeight()*/, 0).color(1, 1, 1, 1).next();
//			//
//			//			bufferBuilder.vertex(matrices.peek().getModel(), -0.5f, 0, 0).color(1, 1, 1, 1).next();
//			//			bufferBuilder.vertex(matrices.peek().getModel(), -0.5f, 2/*entity.getHeight()*/, 0).color(1, 1, 1, 1).next();
//
//			//			bufferBuilder.vertex(matrices.peek().getModel(), 1, 2/*entity.getHeight()*/, 1).color(1, 1, 1, 1).next();
//			//			bufferBuilder.vertex(matrices.peek().getModel(), 1, 0, 1).color(1, 1, 1, 1).next();
//
////			instance.draw();
//		}
//		matrices.pop();
//	}
//
//	private void renderInventory(ItemEntity entity, MatrixStack matrices, float tickDelta, Camera camera)
//	{
//		matrices.push();
//		{
//			Vec3d position = new Vec3d(entity.prevX + (entity.getX() - entity.prevX) * tickDelta,
//					entity.prevY + (entity.getY() - entity.prevY) * tickDelta, entity.prevZ + (entity.getZ() - entity.prevZ) * tickDelta);
//			EntityRenderDispatcher renderManager = MinecraftClient.getInstance().getEntityRenderDispatcher();
//			matrices.push();
//			{
//				matrices.translate(position.x - renderManager.camera.getPos().x,
//						position.y - renderManager.camera.getPos().y + entity.getHeight() + 1, position.z - renderManager.camera.getPos().z);
//				renderEntityInventory(matrices, camera, entity, 15728880);
//			}
//			matrices.pop();
//		}
//		matrices.pop();
//
//	}
//
//	protected void renderEntityInventory(MatrixStack matrices, Camera camera, ItemEntity entity, int light)
//	{
//
//		ListTag list = entity.getStack().getTag().getCompound("BlockEntityTag").getList("Items", 10);
//
//		Quaternion rotation = camera.getRotation().copy();
//		rotation.scale(-1.0F);
//		matrices.multiply(rotation);
//		//		matrices.scale(-0.5f,-0.5f,-0.5f);
//		float scale = 0.1f;//0.026666672F;
//		//		matrices.scale(0.5f,0.5f,0.5f);
//
//		matrices.push();
//
//		//		matrices.translate(5, 0, 3);
//		matrices.scale(-scale, scale, -scale);
//		ArrayList<ItemStack> a = new ArrayList<>();
//		for (int i = 0, len = list.size(); i < len; ++i)
//		{
//			CompoundTag itemTag = list.getCompound(i);
//			ItemStack s = ItemStack.fromTag(itemTag);
//			a.add(s);
//			hashMap.put(itemTag.getByte("slot"), s);
//			//			if (i % 9 == 0)
//			//			{
//			//				matrices.translate(-9, 1, 0);
//			//			}
//			//			matrices.translate(1, 0, 0);
//			//			MinecraftClient.getInstance().getItemRenderer().renderItem(s, ModelTransformation.Mode.GUI, false, matrices,
//			//					MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers(), 15728880, OverlayTexture.DEFAULT_UV,
//			//					MinecraftClient.getInstance().getItemRenderer().getHeldItemModel(s, null, null));
//
//			//			MinecraftClient.getInstance().getItemRenderer().renderGuiItemOverlay(MinecraftClient.getInstance().textRenderer, s, i, 12);
//		}
//		matrices.translate(0, 0, 0.1f);
//		for (int i = 0; i < a.size(); i++)
//		{
//			matrices.translate(1, 0, 0);
//			if (i % 9 == 0)
//			{
//				matrices.translate(-9, 0, 0);
//				matrices.translate(0, -1, 0);
//
//			}
//
//			MinecraftClient.getInstance().getItemRenderer().renderItem(a.get(i), ModelTransformation.Mode.GUI, false, matrices,
//					MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers(), 15728880, OverlayTexture.DEFAULT_UV,
//					MinecraftClient.getInstance().getItemRenderer().getHeldItemModel(a.get(i), null, null));
//		}
//		//		a.forEach(( itemStack) ->
//		//		{
//		//		});
//
//		//renderGuiItemIcon(new ItemStack(Items.SHULKER_BOX, 11), -5, 5);
//		matrices.pop();
//		matrices.push();
//		matrices.scale(-scale, -scale, -scale);
//		RenderSystem.enableBlend();
//		RenderSystem.enableDepthTest();
//		RenderSystem.defaultBlendFunc();
//		GuiHelper.drawOctagonRectFrame(matrices, -10, -5, 10, 5, 1, new Color(0, 0, 0, 140).getRGB());
//		RenderSystem.disableBlend();
//		matrices.pop();
//		RenderSystem.disableDepthTest();
//
//		//		GuiHelper.fill(matrices, 7, 1, 1, 5, 5, 1);
//		//		matrices.pop();
//
//		//		System.out.println(entity.getStack().getTag().getKeys());
//		//getCompound("BlockEntityTag").getCompound("Items").getKeys().forEach(System.out::println);
//	}

}

