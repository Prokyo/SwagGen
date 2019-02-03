package de.prokyo.network.swaggen.plugin;

import de.prokyo.network.swaggen.plugin.bytecode.SwaggerGenerator;
import lombok.SneakyThrows;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import java.io.File;

/**
 * The SwagGen (Swagger Generator) plugin generates a full swagger documentation based on Java annotations.
 */
@Mojo(name = "swaggen", defaultPhase = LifecyclePhase.VERIFY, threadSafe = true, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class SwagGenMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project}")
	private MavenProject project;

	@Parameter(defaultValue = "target/classes")
	private String buildDir;

	/**
	 * Validates the input/settings of the plugin and executes the goal.
	 *
	 * @throws MojoExecutionException When an unexpected error occurs. This exception will cause a "BUILD ERROR".
	 * @throws MojoFailureException When an unexpected error occurs. This exception will cause a "BUILD FAILURE".
	 */
	@SneakyThrows
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("*************************************************");
		getLog().info("*************** SwagGen by Prokyo ***************");
		getLog().info("*************************************************");

		String output = new SwaggerGenerator().generate(new File(this.buildDir));
		System.out.println(output);
	}

}
