package j2html.tags;

import org.junit.Assert;
import org.junit.Test;

import j2html.TagCreator;

public class TextContainerTest {

	@Test
	public void test() {
		String render = TagCreator.textcontainer()
				.with(TagCreator.text("This is "), TagCreator.b(" a simple "), TagCreator.text("test")).render();
		Assert.assertEquals("This is <b> a simple </b>test", render);
	}

}
