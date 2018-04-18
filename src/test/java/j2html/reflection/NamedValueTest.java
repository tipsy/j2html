package j2html.reflection;

import j2html.attributes.LambdaAttribute;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NamedValueTest {

    @Test
    public void testNamedValueWorks() {
        LambdaAttribute pair = five -> 5;
        assertThat("five", is(pair.name()));
        assertThat("5", is(pair.value()));
    }

}
