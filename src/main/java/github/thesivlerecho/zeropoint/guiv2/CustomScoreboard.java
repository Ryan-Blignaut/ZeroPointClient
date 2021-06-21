package github.thesivlerecho.zeropoint.guiv2;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class CustomScoreboard
{
	private static int height, width, posX, posY;
	private static boolean numbers;

	public static void render(MatrixStack matrices, ScoreboardObjective objective)
	{

		final TextRenderer fr = MinecraftClient.getInstance().textRenderer;
		final Scoreboard scoreboard = objective.getScoreboard();
		final List<ScoreboardPlayerScore> scores = new ArrayList<>();

		for (ScoreboardPlayerScore score : scoreboard.getAllPlayerScores(objective))
		{
			String name = score.getPlayerName();
			if (scores.size() < 15 && name != null && !name.startsWith("#"))
			{
				Team team = scoreboard.getTeam(name);
				String s2 = CustomScoreboard.numbers ? ": " + Formatting.RED + score.getScore() : "";
				String str = Team.decorateName(team, new LiteralText(name)) + s2;
				CustomScoreboard.width = Math.max(CustomScoreboard.width, fr.getWidth(str) / 10);
				scores.add(score);
			}
		}


	}

}
