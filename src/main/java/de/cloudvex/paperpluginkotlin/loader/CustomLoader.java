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

        resolver.addRepository(new RemoteRepository.Builder("central", "default", "https://repo.maven.apache.org/maven2/").build());
        resolver.addRepository(new RemoteRepository.Builder("flyte", "default", "https://repo.flyte.gg/releases").build());
        resolver.addRepository(new RemoteRepository.Builder("laturia", "default", "https://repo.laturia.net/public/").build());

        resolver.addDependency(new Dependency(new DefaultArtifact("net.laturia:astral:" + BuildConfig.ASTRAL_VERSION), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("gg.flyte:twilight:" + BuildConfig.TWILIGHT_VERSION), null));

        classpathBuilder.addLibrary(resolver);
    }
}
