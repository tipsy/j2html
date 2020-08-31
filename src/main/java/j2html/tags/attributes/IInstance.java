package j2html.tags.attributes;

public interface IInstance<T> {

    //to get the actual instance
    // (every implementing class would have to implement: { return this; }
    // public T get();

    // this method shows up in autocomplete.
    // this is really undesireable as it does not do anything.
    default T get() {
        //we know that the implementing class will supply
        //its own type as the type argument.
        //therefore every instance of IInstance can assume it
        //is also of type T

        return (T) this;
    }
}
