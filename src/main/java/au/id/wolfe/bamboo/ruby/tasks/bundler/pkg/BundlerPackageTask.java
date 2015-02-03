package au.id.wolfe.bamboo.ruby.tasks.bundler.pkg;

import java.util.List;

import au.id.wolfe.bamboo.ruby.common.RubyLabel;
import au.id.wolfe.bamboo.ruby.common.RubyRuntime;
import au.id.wolfe.bamboo.ruby.locator.RubyLocator;
import au.id.wolfe.bamboo.ruby.locator.RuntimeLocatorException;
import au.id.wolfe.bamboo.ruby.tasks.AbstractRubyTask;

import com.atlassian.bamboo.configuration.ConfigurationMap;

/**
 * Bamboo task which runs bundler to install the gems required by the project.
 */
public class BundlerPackageTask extends AbstractRubyTask {

    public static final String PATH = "path";
    public static final String PACKAGE_ALL = "packageAll";

    @Override
    protected List<String> buildCommandList(RubyLabel rubyRuntimeLabel, ConfigurationMap config) throws RuntimeLocatorException {

        final RubyLocator rubyLocator = getRubyLocator(rubyRuntimeLabel.getRubyRuntimeManager());

        final String path = config.get(PATH);
        final String packageAllFlag = config.get(PACKAGE_ALL);

        final RubyRuntime rubyRuntime = rubyLocator.getRubyRuntime(rubyRuntimeLabel.getRubyRuntime());

        final String rubyExecutablePath = getRubyExecutablePath(rubyRuntimeLabel);

        return new BundlerPackageCommandBuilder(rubyLocator, rubyRuntime, rubyExecutablePath)
                .addRubyExecutable()
                .addBundleExecutable()
                .addPackage()
                .addPath(path)
                .addIfPackageAll(packageAllFlag)
                .build();
    }

}
