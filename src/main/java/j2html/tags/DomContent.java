package j2html.tags;

import java.io.IOException;

public abstract class DomContent {

    public abstract String render();
    
    public void render(Appendable writer) throws IOException {
        writer.append(render());
    }

    @Override
    public String toString() {
        return render();
    }

}
