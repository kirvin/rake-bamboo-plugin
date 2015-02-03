package au.id.wolfe.bamboo.ruby.tasks.bundler.pkg;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import au.id.wolfe.bamboo.ruby.common.RubyRuntime;
import au.id.wolfe.bamboo.ruby.locator.RubyLocator;
import au.id.wolfe.bamboo.ruby.tasks.AbstractCommandBuilder;

/**
 * Builder to assemble the bundle install command list.
 */
public class BundlerPackageCommandBuilder extends AbstractCommandBuilder<BundlerPackageCommandBuilder>{

    public static final String BUNDLE_COMMAND = "bundle";
    public static final String PATH_ARG = "--path";
    public static final String PACKAGE_ALL_ARG = "--all";
    public static final String PACKAGE_ARG = "package";

    public BundlerPackageCommandBuilder(RubyLocator rvmRubyLocator, RubyRuntime rubyRuntime, String rubyExecutablePath) {

        super( rvmRubyLocator, rubyRuntime, rubyExecutablePath );
    }

    /**
     * Append the bundle executable to the command list.
     *
     * @return BundlerPackageCommandBuilder command builder.
     */
    public BundlerPackageCommandBuilder addBundleExecutable() {
        getCommandList().add(getRvmRubyLocator().buildExecutablePath(getRubyRuntime().getRubyRuntimeName(), getRubyExecutablePath(), BUNDLE_COMMAND));
        return this;
    }

    /**
     * Will conditionally append bundle path parameter if path is not empty.
     *
     * @param path String which takes either null or a bundle path.
     * @return BundlerInstallCommandBuilder command builder.
     */
    public BundlerPackageCommandBuilder addPath(@Nullable String path) {
        if (StringUtils.isNotEmpty(path)) {
            getCommandList().add(PATH_ARG);
            getCommandList().add(path);
        }

        return this;
    }

    /**
     * Will conditionally append the all switch if the packageAll flag is "true"
     *
     * @param pacakgeAllFlag String which takes null or "true".
     * @return BundlerPackageCommandBuilder command builder.
     */
    public BundlerPackageCommandBuilder addIfPackageAll(@Nullable String packageAllFlag) {

        if (BooleanUtils.toBoolean(packageAllFlag)) {
            getCommandList().add(PACKAGE_ALL_ARG);
        }
        return this;

    }

    /**
     * Will append the package argument.
     *
     * @return BundlerPacakgeCommandBuilder command builder.
     */
    public BundlerPackageCommandBuilder addPackage() {
        getCommandList().add(PACKAGE_ARG);
        return this;
    }

}
