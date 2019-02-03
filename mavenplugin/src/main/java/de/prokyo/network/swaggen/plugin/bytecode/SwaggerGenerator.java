package de.prokyo.network.swaggen.plugin.bytecode;

import com.google.common.base.Charsets;
import de.prokyo.network.swaggen.api.annotation.RestAPI;
import de.prokyo.network.swaggen.core.Node;
import de.prokyo.network.swaggen.core.StringValue;
import de.prokyo.network.swaggen.generate.JsonGenerator;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import java.io.File;
import java.io.IOException;

public class SwaggerGenerator {

	/**
	 * Generates a swagger documentation and writes it to the given <i>outputFile</i>.
	 *
	 * @param buildDir The build directory of your project. The build directory must contain *.class files.
	 * @param outputFile The output file in which the documentation will be written to
	 * @throws MojoExecutionException When an unexpected error occurs.
	 * @throws IOException When an IO error occurs.
	 * @see SwaggerGenerator#generate(File)
	 */
	public void generate(File buildDir, File outputFile) throws MojoExecutionException, IOException {
		FileUtils.write(outputFile, this.generate(buildDir), Charsets.UTF_8);
	}

	/**
	 * Generates a swagger documentation and returns the output.
	 *
	 * @param buildDir The build directory of your project; The build directory must contain *.class files.
	 * @return The swagger documentation as a String
	 * @throws MojoExecutionException When an unexpected error occurs.
	 */
	public String generate(File buildDir) throws MojoExecutionException {
		ClassLoader classLoader = new ClassLoader(buildDir);
		classLoader.loadClasses();

		// Sry for workaround... https://stackoverflow.com/a/5977866/7341726
		final Node[] root = new Node[1];
		Node info = new Node();

		classLoader.getClasses().forEach(clazz -> {
			try {
				Object restAPI = clazz.getAnnotation(RestAPI.class);
				if (restAPI != null) {
					root[0] = new Node();

					RestAPI api = (RestAPI) restAPI;
					info.add("title", new StringValue(api.title()));
					info.add("version", new StringValue(api.version()));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		if(root[0] == null) throw new MojoExecutionException("Cannot find usable classes for documentation generation.");

		// general information
		{
			root[0].add("swagger", new StringValue("2.0"));
			root[0].add("info", info);
		}

		return JsonGenerator.generateSwaggerJson(root[0]);
	}

}
