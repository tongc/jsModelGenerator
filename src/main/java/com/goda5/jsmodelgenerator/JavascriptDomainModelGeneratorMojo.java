package com.goda5.jsmodelgenerator;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Set;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

@Mojo(name = "generateJs")
public class JavascriptDomainModelGeneratorMojo extends AbstractMojo {
	@Parameter(property = "generateJs.packages")
	private Set<String> packages;

	@Parameter(defaultValue = "${project.compileSourceRoots}")
	private List<String> compileSourceRoots;

	@Parameter(defaultValue = "${basedir}")
	private String baseDir;

	@Parameter(defaultValue = "${project.build.outputDirectory}")
	private String outputDir;

	@Parameter(property = "generateJs.targetJsFolder")
	private String targetJsFolder;

	@Component
	private MavenProject project;

	private JavascriptDomainModelGenerator generator = new JavascriptDomainModelGenerator();

	public void execute() throws MojoExecutionException {
		List<String> runtimeClasspathElements;
		try {
			runtimeClasspathElements = project.getCompileClasspathElements();
			URL[] runtimeUrls = new URL[runtimeClasspathElements.size()];
			for (int i = 0; i < runtimeClasspathElements.size(); i++) {
				String element = runtimeClasspathElements.get(i);
				getLog().debug("Classpath:" + element);
				runtimeUrls[i] = new File(element).toURI().toURL();
			}

			ClassLoader cl = new URLClassLoader(runtimeUrls, Thread.currentThread()
					.getContextClassLoader());
			for(String p:packages) {
				Reflections reflections = new Reflections(new ConfigurationBuilder()
			    .setUrls(ClasspathHelper.forClassLoader(new ClassLoader[]{cl}))
			    .setScanners(new TypeAnnotationsScanner())
			    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(p))));
				for(String fullClassName:reflections.getStore().getStoreMap().get("TypeAnnotationsScanner").get(JavascriptDomainModel.class.getName())) {
					getLog().info("Processing class : " + fullClassName);
					generator.generate(cl.loadClass(fullClassName), new File(baseDir + targetJsFolder));
				}
			}
		} catch (Exception e) {
			throw new MojoExecutionException(e.getMessage(), e);
		}
	}
}