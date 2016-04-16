package j2html.tags;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DisabledTagTest {
    @Test
    public void testDisabledTag() throws Exception {
        DisabledTag tag = new DisabledTag();
        assertEquals(tag.render(), "");
    }
}
