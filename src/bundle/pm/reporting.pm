<plugins>
	<plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-javadoc-plugin</artifactId>
		<version>${pm.version.maven-javadoc-plugin}</version>
		<reportSets>
			<reportSet>
				<id>default</id>
				<reports>
					<report>javadoc</report>
				</reports>
			</reportSet>
		</reportSets>
		<configuration>
			<source>${maven.compiler.target}</source>
			<doclet>org.asciidoctor.Asciidoclet</doclet>
			<docletArtifact>
				<groupId>org.asciidoctor</groupId>
				<artifactId>asciidoclet</artifactId>
				<version>${pm.version.asciidoctor.asciidoclet}</version>
			</docletArtifact>
			<overview>src/main/asciidoc/overview.adoc</overview>
			<additionalparam>
				--base-dir ${project.basedir}/src
				--attribute "name=${project.name}"
				--attribute "release-version=${project.version}"
				--attribute "title-link=${mp.project.url}/[${project.name} ${project.version}]"
			</additionalparam>
			<encoding>${project.resources.sourceEncoding}</encoding>
			<docencoding>${project.resources.sourceEncoding}</docencoding>
			<charset>${project.resources.sourceEncoding}</charset>
			<aggregate>true</aggregate>
			<nohelp>true</nohelp>
			<header>${project.name}: ${project.version}</header>
			<footer>${project.name}: ${project.version}</footer>
			<doctitle>${project.name}: ${project.version}</doctitle>
			<noqualifier>all</noqualifier>
			<detectLinks>true</detectLinks>
			<detectJavaApiLink>true</detectJavaApiLink>
			<javadocDirectory>${basedir}/src/main/javadoc</javadocDirectory>
			<docfilessubdirs>true</docfilessubdirs>
		</configuration>
	</plugin>
</plugins>
