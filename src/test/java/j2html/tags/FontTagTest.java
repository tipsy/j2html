package j2html.tags;

import org.junit.Test;

import j2html.TagCreator;
import org.junit.Assert;

public class FontTagTest {

	@Test
	public void testFontTag() {
		String render = TagCreator.font("Mein Text with font property").withFontSize("30pt").withFontColor("#ff0000")
				.withFontFace("verdana").render();
		String expected = "<font size=\"30pt\" color=\"#ff0000\" face=\"verdana\">Mein Text with font property</font>";
		Assert.assertEquals(expected, render);
	}

}
