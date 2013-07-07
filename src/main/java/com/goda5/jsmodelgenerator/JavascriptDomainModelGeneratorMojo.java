package com.goda5.jsmodelgenerator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name="generateJs")
public class JavascriptDomainModelGeneratorMojo extends AbstractMojo {
	@Parameter(property = "generateJs.sourceDir", defaultValue = "**/*.java")
    private String sourceDir;

	public void execute() throws MojoExecutionException {
		getLog().info("Hello, world." + sourceDir);
	}
}