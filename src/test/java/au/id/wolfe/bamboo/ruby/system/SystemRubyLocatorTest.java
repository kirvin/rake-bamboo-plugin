package au.id.wolfe.bamboo.ruby.system;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import au.id.wolfe.bamboo.ruby.common.RubyRuntime;
import au.id.wolfe.bamboo.ruby.util.FileSystemHelper;

/**
 * Test the system ruby locator
 */
@RunWith(MockitoJUnitRunner.class)
public class SystemRubyLocatorTest {

    @Mock
    FileSystemHelper fileSystemHelper;

    @Mock
    SystemRubyLocator systemRubyLocator;

    @Before
    public void setup() {
        systemRubyLocator = new SystemRubyLocator(fileSystemHelper);
    }

    @Test
    @Ignore("Test is dependent on external configuration and will fail depending on runtime environment.")
    public void testListRubyRuntimes() throws Exception {

        when(fileSystemHelper.pathExists("/usr/bin", "ruby")).thenReturn(true);
        when(fileSystemHelper.pathExists("/usr/bin", "gem")).thenReturn(true);

        List<RubyRuntime> rubyRuntimeList = systemRubyLocator.listRubyRuntimes();

        assertThat(rubyRuntimeList.size(), equalTo(1));

        assertThat(rubyRuntimeList.get(0).getRubyName(), equalTo("2.0.0-p451"));


    }
}
