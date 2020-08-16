package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.IDisabled;
import j2html.tags.attributes.IDownload;
import j2html.tags.attributes.IForm;
import j2html.tags.attributes.IType;

public final class TextAreaTag extends ContainerTag
    implements
    IDisabled<TextAreaTag>,
    IForm<TextAreaTag>
{

    public TextAreaTag() {
        super("textarea");
    }
}
