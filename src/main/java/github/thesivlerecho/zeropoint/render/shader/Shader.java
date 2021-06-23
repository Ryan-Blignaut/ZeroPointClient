package github.thesivlerecho.zeropoint.render.shader;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.ZeroPointClient;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vector4f;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Consumer;

import static org.lwjgl.opengl.GL20.*;

public class Shader
{
	protected int programId;
	private final String fragLocation;
	private final String vertLocation;

	private static final String COLOUR_VERT_LOCATION = "new/colour";


	private static final Identifier SHADER = new Identifier(ZeroPointClient.MOD_ID, "shaders");
	private static final FloatBuffer FLOAT_BUFFER = MemoryUtil.memAllocFloat(16);

	public Shader(String fragLocation)
	{
		this(fragLocation, COLOUR_VERT_LOCATION);
	}

	public Shader(String fragLocation, String vertLocation)
	{
		this.fragLocation = fragLocation + ".frag";
		this.vertLocation = vertLocation + ".vert";
	}


	public Optional<String> getShaderString(String name, ResourceManager manager)
	{
		Identifier location = new Identifier(SHADER.getNamespace(), SHADER.getPath() + "/" + name);
		try (InputStream is = manager.getResource(location).getInputStream())
		{
			return Optional.of(IOUtils.toString(is, StandardCharsets.UTF_8));
		} catch (IOException ignored)
		{
			System.err.println("is not found");
			return Optional.empty();
		}
	}

	private int genShader(int glFragmentShader, String loc, ResourceManager manager)
	{
		final int[] programId = {-1};
		getShaderString(loc, manager).ifPresent(shaderSource ->
		{
			programId[0] = glCreateShader(glFragmentShader);
			glShaderSource(programId[0], shaderSource);
			glCompileShader(programId[0]);

			if (glGetShaderi(programId[0], GL_COMPILE_STATUS) == GL_FALSE)
				ZeroPointClient.LOGGER.error(glGetShaderInfoLog(programId[0], 100));
		});
		return programId[0];
	}


	public Shader create(ResourceManager manager)
	{

		int vertId = genShader(GL_VERTEX_SHADER, vertLocation, manager);
		int fragId = genShader(GL_FRAGMENT_SHADER, fragLocation, manager);

		programId = glCreateProgram();
//		glBindFragDataLocation(programId, 0, "fragColor");
		apply(vertId, id -> glAttachShader(programId, id));
		apply(fragId, id -> glAttachShader(programId, id));
		glLinkProgram(programId);

		apply(vertId, GL20::glDeleteShader);
		apply(fragId, GL20::glDeleteShader);

		if (glGetProgrami(programId, GL_LINK_STATUS) == GL_FALSE)
			ZeroPointClient.LOGGER.error(GL20.glGetProgramInfoLog(programId));

		glValidateProgram(programId);

		if (glGetProgrami(programId, GL_VALIDATE_STATUS) == GL_FALSE)
			ZeroPointClient.LOGGER.error(GL20.glGetProgramInfoLog(programId));


		return this;
	}

	private void apply(int id, Consumer<Integer> consumer)
	{
		if (id != -1) consumer.accept(id);
	}

	public Shader bind()
	{
		glUseProgram(programId);
		setArgument("ModelViewMat", RenderSystem.getModelViewMatrix());
		setArgument("ProjMat", RenderSystem.getProjectionMatrix());
		return this;
	}

	public void unBind()
	{
		glUseProgram(0);
	}

	public void destroy()
	{
		glDeleteProgram(programId);
	}

	public void setArgument(String var, Object value)
	{
		int location = GL20.glGetUniformLocation(this.programId, var);
		if (value instanceof Float)
			glUniform1f(location, (Float) value);
		else if (value instanceof Integer)
			glUniform1i(location, (Integer) value);
		else if (value instanceof final Vec2f vec2f)
		{
			glUniform2f(location, vec2f.x, vec2f.y);
		} else if (value instanceof final Matrix4f matrix4f)
		{
			FLOAT_BUFFER.position(0);
			matrix4f.writeColumnMajor(FLOAT_BUFFER);
			GL20.glUniformMatrix4fv(location, false, FLOAT_BUFFER);
		} else if (value instanceof final Vector4f vec)
		{
			GL20.glUniform4f(location, vec.getX(), vec.getY(), vec.getZ(), vec.getW());
		} else
			throw new UnsupportedOperationException("Failed to load data into shader: Unsupported data type.");

	}

}
