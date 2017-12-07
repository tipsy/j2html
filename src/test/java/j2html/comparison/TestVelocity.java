package j2html.comparison;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class TestVelocity {

    private static VelocityEngine velocityEngine;

    static {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    }

    private static String render(String templatePath, Map<String, Object> model) {
        StringWriter stringWriter = new StringWriter();
        velocityEngine.getTemplate(templatePath, StandardCharsets.UTF_8.name()).merge(
            new VelocityContext(model), stringWriter
        );
        return stringWriter.toString();
    }

    public static String helloWorld() {
        return render("/comparison/velocity/helloWorld.vm", null);
    }

    public static String fiveHundredEmployees() {
        Map<String, Object> model = new HashMap<>();
        model.put("employees", ComparisonData.fiveHundredEmployees());
        return render("/comparison/velocity/fiveHundredEmployees.vm", model);
    }

    public static String macros() {
        return render("/comparison/velocity/macros.vm", null);
    }

}
