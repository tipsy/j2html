package j2html.tags;

import org.apache.commons.lang3.StringUtils;

public class TextContainer extends ContainerTag {

	public TextContainer() {
		super(StringUtils.EMPTY);
	}

	/**
	 * Render the tag and its children
	 */
	@Override
	public String render() {
		StringBuilder rendered = new StringBuilder(StringUtils.EMPTY);
		if (children != null && children.size() > 0) {
			for (Tag child : children) {
				rendered.append(child.render());
			}
		}
		return rendered.toString();
	}

}
