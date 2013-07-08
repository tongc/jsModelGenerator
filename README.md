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