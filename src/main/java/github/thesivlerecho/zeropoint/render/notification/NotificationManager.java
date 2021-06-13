package github.thesivlerecho.zeropoint.render.notification;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;

import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationManager
{

	public static final NotificationManager INSTANCE = new NotificationManager();

	private final CopyOnWriteArrayList<Notification> notifications = new CopyOnWriteArrayList<>();
	private final CopyOnWriteArrayList<Notification> notificationQueue = new CopyOnWriteArrayList<>();


	public void addNotification(Notification notification)
	{
		notificationQueue.add(notification);
	}

	public void onRender(MatrixStack matrixStack)
	{
		final Window window = MinecraftClient.getInstance().getWindow();
		for (Notification n : notificationQueue)
		{
			n.setPosY(window.getScaledHeight() - 54 - (54 * notifications.size()));
			notifications.add(n);
			notificationQueue.remove(n);
		}
		for (Notification not : notifications)
		{
			not.onRender(matrixStack);
		}
	}

}
