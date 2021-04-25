package j2html.tags;

import java.io.IOException;

import j2html.Config;

public abstract class DomContent implements Renderable {
    @Override
    public String toString() {
        return render();
    }

    protected void renderFormatted(Appendable writer, int level) throws IOException {
        indent(writer, level);
        render(writer);
        writer.append("\n");
    }

    protected static void indent(Appendable appendable, int level) throws IOException {
        appendable.append(Config.indenter.indent(level, ""));
    }
}
