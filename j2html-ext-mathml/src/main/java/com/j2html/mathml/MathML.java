package com.j2html.mathml;

import com.j2html.mathml.tags.*;
import j2html.tags.DomContent;

public class MathML {

    public static AnnotationTag annotation(String text) {
        return new AnnotationTag().withText(text);
    }

    public static AnnotationTag annotation(DomContent... dc) {
        return new AnnotationTag().with(dc);
    }

    public static MactionTag maction(String text) {
        return new MactionTag().withText(text);
    }

    public static MactionTag maction(DomContent... dc) {
        return new MactionTag().with(dc);
    }

    public static MathTag math(String text) {
        return new MathTag().withText(text);
    }

    public static MathTag math(DomContent... dc) {
        return new MathTag().with(dc);
    }

    public static MerrorTag merror(String text) {
        return new MerrorTag().withText(text);
    }

    public static MerrorTag merror(DomContent... dc) {
        return new MerrorTag().with(dc);
    }

    public static MfracTag mfrac(String text) {
        return new MfracTag().withText(text);
    }

    public static MfracTag mfrac(DomContent... dc) {
        return new MfracTag().with(dc);
    }

    public static MiTag mi(String text) {
        return new MiTag().withText(text);
    }

    public static MiTag mi(DomContent... dc) {
        return new MiTag().with(dc);
    }

    public static MmultiscriptsTag mmultiscripts(String text) {
        return new MmultiscriptsTag().withText(text);
    }

    public static MmultiscriptsTag mmultiscripts(DomContent... dc) {
        return new MmultiscriptsTag().with(dc);
    }

    public static MnTag mn(String text) {
        return new MnTag().withText(text);
    }

    public static MnTag mn(DomContent... dc) {
        return new MnTag().with(dc);
    }

    public static MoTag mo(String text) {
        return new MoTag().withText(text);
    }

    public static MoTag mo(DomContent... dc) {
        return new MoTag().with(dc);
    }

    public static MoverTag mover(String text) {
        return new MoverTag().withText(text);
    }

    public static MoverTag mover(DomContent... dc) {
        return new MoverTag().with(dc);
    }

    public static MpaddedTag mpadded(String text) {
        return new MpaddedTag().withText(text);
    }

    public static MpaddedTag mpadded(DomContent... dc) {
        return new MpaddedTag().with(dc);
    }

    public static MphantomTag mphantom(String text) {
        return new MphantomTag().withText(text);
    }

    public static MphantomTag mphantom(DomContent... dc) {
        return new MphantomTag().with(dc);
    }

    public static MprescriptsTag mprescripts(String text) {
        return new MprescriptsTag().withText(text);
    }

    public static MprescriptsTag mprescripts(DomContent... dc) {
        return new MprescriptsTag().with(dc);
    }

    public static MrootTag mroot(String text) {
        return new MrootTag().withText(text);
    }

    public static MrootTag mroot(DomContent... dc) {
        return new MrootTag().with(dc);
    }

    public static MrowTag mrow(String text) {
        return new MrowTag().withText(text);
    }

    public static MrowTag mrow(DomContent... dc) {
        return new MrowTag().with(dc);
    }

    public static MsTag ms(String text) {
        return new MsTag().withText(text);
    }

    public static MsTag ms(DomContent... dc) {
        return new MsTag().with(dc);
    }

    public static MspaceTag mspace(String text) {
        return new MspaceTag().withText(text);
    }

    public static MspaceTag mspace(DomContent... dc) {
        return new MspaceTag().with(dc);
    }

    public static MsqrtTag msqrt(String text) {
        return new MsqrtTag().withText(text);
    }

    public static MsqrtTag msqrt(DomContent... dc) {
        return new MsqrtTag().with(dc);
    }

    public static MstyleTag mstyle(String text) {
        return new MstyleTag().withText(text);
    }

    public static MstyleTag mstyle(DomContent... dc) {
        return new MstyleTag().with(dc);
    }

    public static MsubTag msub(String text) {
        return new MsubTag().withText(text);
    }

    public static MsubTag msub(DomContent... dc) {
        return new MsubTag().with(dc);
    }

    public static MsubsupTag msubsup(String text) {
        return new MsubsupTag().withText(text);
    }

    public static MsubsupTag msubsup(DomContent... dc) {
        return new MsubsupTag().with(dc);
    }

    public static MsupTag msup(String text) {
        return new MsupTag().withText(text);
    }

    public static MsupTag msup(DomContent... dc) {
        return new MsupTag().with(dc);
    }

    public static MtableTag mtable(String text) {
        return new MtableTag().withText(text);
    }

    public static MtableTag mtable(DomContent... dc) {
        return new MtableTag().with(dc);
    }

    public static MtdTag mtd(String text) {
        return new MtdTag().withText(text);
    }

    public static MtdTag mtd(DomContent... dc) {
        return new MtdTag().with(dc);
    }

    public static MtextTag mtext(String text) {
        return new MtextTag().withText(text);
    }

    public static MtextTag mtext(DomContent... dc) {
        return new MtextTag().with(dc);
    }

    public static MtrTag mtr(String text) {
        return new MtrTag().withText(text);
    }

    public static MtrTag mtr(DomContent... dc) {
        return new MtrTag().with(dc);
    }

    public static MunderTag munder(String text) {
        return new MunderTag().withText(text);
    }

    public static MunderTag munder(DomContent... dc) {
        return new MunderTag().with(dc);
    }

    public static MunderoverTag munderover(String text) {
        return new MunderoverTag().withText(text);
    }

    public static MunderoverTag munderover(DomContent... dc) {
        return new MunderoverTag().with(dc);
    }

    public static NoneTag none(String text) {
        return new NoneTag().withText(text);
    }

    public static NoneTag none(DomContent... dc) {
        return new NoneTag().with(dc);
    }

    public static SemanticsTag semantics(String text) {
        return new SemanticsTag().withText(text);
    }

    public static SemanticsTag semantics(DomContent... dc) {
        return new SemanticsTag().with(dc);
    }
}
