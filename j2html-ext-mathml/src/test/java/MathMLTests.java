import org.junit.Test;

import static com.j2html.mathml.MathML.*;
import static org.junit.Assert.assertEquals;

public class MathMLTests {
    
    @Test
    public void all_tags_render_containers(){
        assertEquals("<annotation></annotation>", annotation().render());
        assertEquals("<maction></maction>", maction().render());
        assertEquals("<math></math>", math().render());
        assertEquals("<merror></merror>", merror().render());
        assertEquals("<mfrac></mfrac>", mfrac().render());
        assertEquals("<mi></mi>", mi().render());
        assertEquals("<mmultiscripts></mmultiscripts>", mmultiscripts().render());
        assertEquals("<mn></mn>", mn().render());
        assertEquals("<mo></mo>", mo().render());
        assertEquals("<mover></mover>", mover().render());
        assertEquals("<mpadded></mpadded>", mpadded().render());
        assertEquals("<mphantom></mphantom>", mphantom().render());
        assertEquals("<mprescripts></mprescripts>", mprescripts().render());
        assertEquals("<mroot></mroot>", mroot().render());
        assertEquals("<mrow></mrow>", mrow().render());
        assertEquals("<ms></ms>", ms().render());
        assertEquals("<mspace></mspace>", mspace().render());
        assertEquals("<msqrt></msqrt>", msqrt().render());
        assertEquals("<mstyle></mstyle>", mstyle().render());
        assertEquals("<msub></msub>", msub().render());
        assertEquals("<msubsup></msubsup>", msubsup().render());
        assertEquals("<msup></msup>", msup().render());
        assertEquals("<mtable></mtable>", mtable().render());
        assertEquals("<mtd></mtd>", mtd().render());
        assertEquals("<mtext></mtext>", mtext().render());
        assertEquals("<mtr></mtr>", mtr().render());
        assertEquals("<munder></munder>", munder().render());
        assertEquals("<munderover></munderover>", munderover().render());
        assertEquals("<none></none>", none().render());
        assertEquals("<semantics></semantics>", semantics().render());
    }
    
}
