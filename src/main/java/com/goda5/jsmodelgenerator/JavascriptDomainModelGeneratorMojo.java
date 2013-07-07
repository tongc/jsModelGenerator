package com.goda5.jsmodelgenerator;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.compiler.util.scan.InclusionScanException;
import org.codehaus.plexus.compiler.util.scan.SimpleSourceInclusionScanner;
import org.codehaus.plexus.compiler.util.scan.SourceInclusionScanner;
import org.codehaus.plexus.compiler.util.scan.mapping.SourceMapping;
import org.codehaus.plexus.compiler.util.scan.mapping.SuffixMapping;

@Mojo(name = "generateJs")
public class JavascriptDomainModelGeneratorMojo extends AbstractMojo {
	@Parameter(property = "generateJs.sourceDir", defaultValue = "**/*.java")
	private Set<String> includes;

	@Parameter(defaultValue = "${project.compileSourceRoots}")
	private List<String> compileSourceRoots;

	@Parameter(defaultValue = "${project.build.outputDirectory}")
	private String outputDir;

	public void execute() throws MojoExecutionException {
		Set<File> files = new HashSet<File>();
		SourceInclusionScanner scanner = new SimpleSourceInclusionScanner(
				includes, Collections.<String> emptySet());
		SourceMapping mapping = new SuffixMapping(".java", ".class");
		scanner.addSourceMapping(mapping);
		getLog().info("Checking source folder(s): " + compileSourceRoots);
		for (String sourceRoot : compileSourceRoots) {
			try {
				files.addAll(scanner.getIncludedSources(new File(sourceRoot),
						new File(outputDir)));
			} catch (InclusionScanException e) {
				throw new MojoExecutionException(
						"Error scanning source root: \'"
								+ new File(sourceRoot).getPath() + "\' "
								+ "for stale files to recompile.", e);
			}
		}
		getLog().info("Hello, world." + files);
	}
}