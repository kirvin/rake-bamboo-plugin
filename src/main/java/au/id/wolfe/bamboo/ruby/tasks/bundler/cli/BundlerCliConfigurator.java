package au.id.wolfe.bamboo.ruby.tasks.bundler.cli;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import au.id.wolfe.bamboo.ruby.common.AbstractRubyTaskConfigurator;
import au.id.wolfe.bamboo.ruby.tasks.AbstractRubyTask;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.TaskConfigConstants;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.google.common.collect.Sets;

/**
 * Bundler CLI configurator which acts as a binding to the task UI in bamboo.
 */
public class BundlerCliConfigurator extends AbstractRubyTaskConfigurator {

    private static final Set<String> FIELDS_TO_COPY = Sets.newHashSet(
            RUBY_KEY,
            TaskConfigConstants.CFG_WORKING_SUB_DIRECTORY,
            BundlerCliTask.ARGUMENTS,
            BundlerCliTask.BUNDLE_EXEC,
            AbstractRubyTask.ENVIRONMENT,
            BundlerCliTask.VERBOSE,
            BundlerCliTask.TRACE);

    protected Set<String> getFieldsToCopy(){
        return FIELDS_TO_COPY;
    }

    @Override
    public void validate(@NotNull ActionParametersMap params, @NotNull ErrorCollection errorCollection) {

        super.validate( params, errorCollection );

        final String arguments = params.getString(BundlerCliTask.ARGUMENTS);

        if (StringUtils.isEmpty(arguments)) {
            errorCollection.addError(BundlerCliTask.ARGUMENTS, "You must specify at least one argument.");
        }
    }
}