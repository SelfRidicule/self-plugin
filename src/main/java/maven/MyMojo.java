package maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

@Mojo(name="countLines", defaultPhase = LifecyclePhase.COMPILE)
public class MyMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.sourceDirectory}" , property = "sourceDir" , required = true)
    private File sourceFile;

    public void execute() throws MojoExecutionException, MojoFailureException {
        String prefix = System.getProperty("prefix");
        try {
            Files.walkFileTree(sourceFile.toPath() , new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(prefix != null){
                        if(file.getFileName().toString().startsWith(prefix)){
                            int lineCount = Files.readAllLines(file).size();
                            getLog().info("line count of file " + file + " is " + lineCount);
                        }
                    }
                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
