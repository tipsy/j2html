package j2html.tags;

public abstract class DomContent {

    public abstract String render();

    @Override
    public String toString() {
        return render();
    }

}
