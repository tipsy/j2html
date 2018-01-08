package j2html.attributes;

import j2html.reflection.MethodFinder;
import java.util.function.Function;

public interface LambdaAttribute extends MethodFinder, Function<String, Object> {

    default String name() {
        checkParametersEnabled();
        return parameter(0).getName();
    }

    default String value() {
        checkParametersEnabled();
        return String.valueOf(this.apply(name()));
    }

    default void checkParametersEnabled() {
        if ("arg0".equals(parameter(0).getName())) {
            throw new IllegalStateException("You need java 8u60 or newer for parameter reflection to work");
        }
    }

}
