package de.cloudvex.paperpluginkotlin.loader;

import de.cloudvex.paperpluginkotlin.BuildConfig;
import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

public class CustomLoader implements PluginLoader {

    @Override
    public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();

        resolver.addRepository(new RemoteRepository.Builder("central", "default", "https://repo.central.maven.org/maven2/").build());
        resolver.addDependency(new Dependency(new DefaultArtifact("net.axay:kspigot:" + BuildConfig.KSPIGOT_VERSION), null));

        //resolver.addRepository(new RemoteRepository.Builder("laturia", "default", "https://repo.laturia.net/releases").build());
        //resolver.addDependency(new Dependency(new DefaultArtifact("net.laturia:api:" + BuildConfig.LATURIA_API_VERSION), null));

        classpathBuilder.addLibrary(resolver);
    }
}
