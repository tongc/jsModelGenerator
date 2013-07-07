package com.goda5.jsmodelgenerator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "generateJs")
public class jsModelGeneratorMojo extends AbstractMojo {
	public void execute() throws MojoExecutionException {
		getLog().info("Hello, world.");
	}
}