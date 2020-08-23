package j2html.tags.specialized;

import j2html.tags.ContainerTag;
import j2html.tags.attributes.*;

public final class MapTag extends ContainerTag<MapTag>
    implements IName<MapTag> {
    public MapTag() {
        super("map");
    }
}
