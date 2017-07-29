package j2html;

import java.io.IOException;

/**
 * @author chmuchme
 * @since 0.1
 * on 01/05/17.
 */
public class RenderException extends RuntimeException {
    public RenderException(IOException e) {
        super(e);
    }
}
