Please see below example for sample configuration.

	<plugin>
		<groupId>com.goda5</groupId>
		<artifactId>jsModelGenerator</artifactId>
		<version>1.0-SNAPSHOT</version>
		<configuration>
			<packages>
				<package>com.expedia.copilot.domain</package>
			</packages>
			<targetJsFolder>/src/main/webapp/js/generatedModel/</targetJsFolder>
		</configuration>
		<executions>
			<execution>
				<phase>generate-sources</phase>
				<goals>
					<goal>generateJs</goal>
				</goals>
			</execution>
		</executions>
	</plugin>

Which will generate Editing.js

	function Editing(source, posStart, posEnd, value) {
    	this.source = source;
    	this.posStart = posStart;
    	this.posEnd = posEnd;
    	this.value = value;
	}
From given input Editing.java

	@JavascriptDomainModel
	public class Editing {
		private String source;
		private int posStart;
		private int posEnd;
		private String value;
	}