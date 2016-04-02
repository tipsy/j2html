package j2html.tags;

abstract class DomContent {

    public abstract String render();

    @Override
    public String toString() {
        return this.render();
    }

}
