/**
 * * CSSMin Copyright License Agreement (BSD License)
 *
 * Copyright (c) 2011-2014, Barry van Oudtshoorn
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the
 * following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other
 * materials provided with the distribution.
 *
 * - Neither the name of Barryvan nor the names of its
 * contributors may be used to endorse or promote products
 * derived from this software without specific prior
 * written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * CSSMin takes in well-formed, human-readable CSS and reduces its size substantially.
 * It removes unnecessary whitespace and comments, and orders the contents of CSS
 * selectors alphabetically to enhance GZIP compression.
 *
 * Originally by Barry van Oudtshoorn, with bug reports, fixes, and contributions by
 * <ul>
 *   <li>Kevin de Groote</li>
 *   <li>Pedro Pinheiro</li>
 *   <li>Asier Lostalé</li>
 *   <li>David Åse</li>
 * </ul>
 * Some code is based on the YUI CssCompressor code, by Julien Lecomte.
 *
 * @author Barry van Oudtshoorn
 * Repo: https://github.com/barryvan/CSSMin
 */

package j2html.utils;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.lang.*;

public class CSSMin {

    //TODO: Stop using sout, srsly...

    protected static boolean bDebug = false;

    /**
     * Minify CSS from a reader to a printstream.
     *
     * @param input Where to read the CSS from
     */
    public static String compress(String input) {
        try {
            int k,
                j, // Number of open braces
                n; // Current position in stream
            char curr;

            BufferedReader br = new BufferedReader(new StringReader(input));
            StringBuilder sb = new StringBuilder();

            if (bDebug) {
                System.err.println("Reading file into StringBuffer...");
            }
            String s;
            while ((s = br.readLine()) != null) {
                if (s.trim().equals("")) continue;
                sb.append(s);
            }

            if (bDebug) {
                System.err.println("Removing comments...");
            }
            // Find the start of the comment
            n = 0;
            while ((n = sb.indexOf("/*", n)) != -1) {
                if (sb.charAt(n + 2) == '*') { // Retain special comments
                    n += 2;
                    continue;
                }
                k = sb.indexOf("*/", n + 2);
                if (k == -1) {
                    throw new UnterminatedCommentException();
                }
                sb.delete(n, k + 2);
            }
            if (bDebug) {
                System.err.println(sb.toString());
                System.err.println("\n\n");
            }

            if (bDebug) {
                System.err.println("Parsing and processing selectors...");
            }
            Vector<Selector> selectors = new Vector<>();
            n = 0;
            j = 0;
            for (int i = 0; i < sb.length(); i++) {
                curr = sb.charAt(i);
                if (j < 0) {
                    throw new UnbalancedBracesException();
                }
                if (curr == '{') {
                    j++;
                } else if (curr == '}') {
                    j--;
                    if (j == 0) {
                        try {
                            selectors.addElement(new Selector(sb.substring(n, i + 1)));
                        } catch (UnterminatedSelectorException usex) {
                            System.out.println("Unterminated selector: " + usex.getMessage());
                        } catch (EmptySelectorBodyException ebex) {
                            if (bDebug) System.out.println("Empty selector body: " + ebex.getMessage());
                        }
                        n = i + 1;
                    }
                }
            }

            StringBuilder result = new StringBuilder();

            for (Selector selector : selectors) {
                result.append(selector);
            }

            if (bDebug) {
                System.err.println("Process completed successfully.");
            }

            return result.toString();

        } catch (UnterminatedCommentException ucex) {
            System.out.println("Unterminated comment.");
        } catch (UnbalancedBracesException ubex) {
            System.out.println("Unbalanced braces.");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            System.out.println(ex.getMessage());
        }

        return null;

    }
}

class Selector {
    private Property[] properties = null;
    private Vector<Selector> subSelectors = null;
    private String selector;

    /**
     * Creates a new Selector using the supplied strings.
     *
     * @param selector The selector; for example, "div { border: solid 1px red; color: blue; }"
     * @throws IncompleteSelectorException, UnterminatedSelectorException, EmptySelectorBodyException If the selector is incomplete and cannot be parsed.
     */
    public Selector(String selector) throws IncompleteSelectorException, UnterminatedSelectorException, EmptySelectorBodyException {
        String[] parts = selector.split("\\{"); // We have to escape the { with a \ for the regex, which itself requires escaping for the string. Sigh.
        if (parts.length < 2) {
            throw new IncompleteSelectorException(selector);
        }

        this.selector = parts[0].trim();

        // Simplify combinators
        this.selector = this.selector.replaceAll("\\s?(\\+|~|,|=|~=|\\^=|\\$=|\\*=|\\|=|>)\\s?", "$1");


        // We're dealing with a nested property, eg @-webkit-keyframes
        if (parts.length > 2) {
            this.subSelectors = new Vector<>();
            parts = selector.split("(\\s*\\{\\s*)|(\\s*\\}\\s*)");
            for (int i = 1; i < parts.length; i += 2) {
                parts[i] = parts[i].trim();
                parts[i + 1] = parts[i + 1].trim();
                if (!(parts[i].equals("") || (parts[i + 1].equals("")))) {
                    this.subSelectors.addElement(new Selector(parts[i] + "{" + parts[i + 1] + "}"));
                }
            }
        } else {
            String contents = parts[parts.length - 1].trim();
            if (CSSMin.bDebug) {
                System.err.println("Parsing selector: " + this.selector);
                System.err.println("\t" + contents);
            }
            if (contents.charAt(contents.length() - 1) != '}') { // Ensure that we have a leading and trailing brace.
                throw new UnterminatedSelectorException(selector);
            }
            if (contents.length() == 1) {
                throw new EmptySelectorBodyException(selector);
            }
            contents = contents.substring(0, contents.length() - 2);

            this.properties = new Property[0];
            this.properties = parseProperties(contents).toArray(this.properties);
            sortProperties(this.properties);
        }
    }

    /**
     * Prints out this selector and its contents nicely, with the contents sorted alphabetically.
     *
     * @return A string representing this selector, minified.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.selector).append("{");
        if (this.subSelectors != null) {
            for (Selector s : this.subSelectors) {
                sb.append(s.toString());
            }
        }
        if (this.properties != null) {
            for (Property p : this.properties) {
                sb.append(p.toString());
            }
        }
        if (sb.charAt(sb.length() - 1) == ';') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Parses out the properties of a selector's body.
     *
     * @param contents The body; for example, "border: solid 1px red; color: blue;"
     * @return An array of properties parsed from this selector.
     */
    private ArrayList<Property> parseProperties(String contents) {
        ArrayList<String> parts = new ArrayList<>();
        boolean bInsideString = false,
                bInsideURL = false;
        int j = 0;
        String substr;
        for (int i = 0; i < contents.length(); i++) {
            if (bInsideString) { // If we're inside a string
                bInsideString = !(contents.charAt(i) == '"');
            } else if (bInsideURL) { // If we're inside a URL
                bInsideURL = !(contents.charAt(i) == ')');
            } else if (contents.charAt(i) == '"') {
                bInsideString = true;
            } else if (contents.charAt(i) == '(') {
                if ((i - 3) > 0 && "url".equals(contents.substring(i - 3, i))) bInsideURL = true;
            } else if (contents.charAt(i) == ';') {
                substr = contents.substring(j, i);
                if (!(substr.trim().equals(""))) parts.add(substr);
                j = i + 1;
            }
        }
        substr = contents.substring(j, contents.length());
        if (!(substr.trim().equals(""))) parts.add(substr);

        ArrayList<Property> results = new ArrayList<>();
        for (String part : parts) {
            try {
                results.add(new Property(part));
            } catch (IncompletePropertyException ipex) {
                System.out.println("Incomplete property in selector \"" + this.selector + "\": \"" + ipex.getMessage() + "\"");
            }
        }

        return results;
    }

    /**
     * Sorts the properties array to enhance gzipping.
     *
     * @param properties The array to be sorted.
     */
    private void sortProperties(Property[] properties) {
        Arrays.sort(properties);
    }
}

class Property implements Comparable<Property> {
    protected String property;
    protected Part[] parts;

    /**
     * Creates a new Property using the supplied strings. Parses out the values of the property selector.
     *
     * @param property The property; for example, "border: solid 1px red;" or "-moz-box-shadow: 3px 3px 3px rgba(255, 255, 0, 0.5);".
     * @throws IncompletePropertyException If the property is incomplete and cannot be parsed.
     */
    public Property(String property) throws IncompletePropertyException {
        try {
            // Parse the property.
            ArrayList<String> parts = new ArrayList<>();
            boolean bCanSplit = true;
            int j = 0;
            String substr;
            if (CSSMin.bDebug) {
                System.err.println("\t\tExamining property: " + property);
            }
            for (int i = 0; i < property.length(); i++) {
                if (!bCanSplit) { // If we're inside a string
                    bCanSplit = (property.charAt(i) == '"');
                } else if (property.charAt(i) == '"') {
                    bCanSplit = false;
                } else if (property.charAt(i) == ':' && parts.size() < 1) {
                    substr = property.substring(j, i);
                    if (!(substr.trim().equals(""))) parts.add(substr);
                    j = i + 1;
                }
            }
            substr = property.substring(j, property.length());
            if (!(substr.trim().equals(""))) parts.add(substr);
            if (parts.size() < 2) {
                throw new IncompletePropertyException(property);
            }

            String prop = parts.get(0).trim();
            if (!(prop.length() > 2 && prop.substring(0, 2).equals("--"))) {
                prop = prop.toLowerCase();
            }
            this.property = prop;

            this.parts = parseValues(simplifyColours(parts.get(1).trim().replaceAll(", ", ",")));

        } catch (PatternSyntaxException e) {
            // Invalid regular expression used.
        }
    }

    /**
     * Prints out this property nicely.
     *
     * @return A string representing this property, minified.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.property).append(":");
        for (Part p : this.parts) {
            sb.append(p.toString()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1); // Delete the trailing comma.
        sb.append(";");
        if (CSSMin.bDebug) {
            System.err.println(sb.toString());
        }
        return sb.toString();
    }

    /**
     * Compare this property with another.
     */
    public int compareTo(Property other) {
        // We can't just use String.compareTo(), because we need to sort properties that have hack prefixes last -- eg, *display should come after display.
        String thisProp = this.property;
        String thatProp = other.property;

        if (thisProp.charAt(0) == '-') {
            thisProp = thisProp.substring(1);
            thisProp = thisProp.substring(thisProp.indexOf('-') + 1);
        } else if (thisProp.charAt(0) < 65) {
            thisProp = thisProp.substring(1);
        }

        if (thatProp.charAt(0) == '-') {
            thatProp = thatProp.substring(1);
            thatProp = thatProp.substring(thatProp.indexOf('-') + 1);
        } else if (thatProp.charAt(0) < 65) {
            thatProp = thatProp.substring(1);
        }

        return thisProp.compareTo(thatProp);
    }

    /**
     * Parse the values out of a property.
     *
     * @param contents The property to parse
     * @return An array of Parts
     */
    private Part[] parseValues(String contents) {
        String[] parts = contents.split(",");
        Part[] results = new Part[parts.length];

        for (int i = 0; i < parts.length; i++) {
            try {
                results[i] = new Part(parts[i], property);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                results[i] = null;
            }
        }

        return results;
    }

    private String simplifyColours(String contents) {
        // This replacement, although it results in a smaller uncompressed file,
        // actually makes the gzipped file bigger -- people tend to use rgba(0,0,0,0.x)
        // quite a lot, which means that rgba(0,0,0,0) has its first eight or so characters
        // compressed really efficiently; much more so than "transparent".
        //contents = contents.replaceAll("rgba\\(0,0,0,0\\)", "transparent");

        return simplifyRGBColours(contents);
    }

    // Convert rgb(51,102,153) to #336699 (this code largely based on YUI code)
    private String simplifyRGBColours(String contents) {
        StringBuffer newContents = new StringBuffer();
        StringBuffer hexColour;
        String[] rgbColours;
        int colourValue;

        Pattern pattern = Pattern.compile("rgb\\s*\\(\\s*([0-9,\\s]+)\\s*\\)");
        Matcher matcher = pattern.matcher(contents);

        while (matcher.find()) {
            hexColour = new StringBuffer("#");
            rgbColours = matcher.group(1).split(",");
            for (String rgbColour : rgbColours) {
                colourValue = Integer.parseInt(rgbColour);
                if (colourValue < 16) {
                    hexColour.append("0");
                }
                hexColour.append(Integer.toHexString(colourValue));
            }
            matcher.appendReplacement(newContents, hexColour.toString());
        }
        matcher.appendTail(newContents);

        return newContents.toString();
    }
}

class Part {
    String contents;
    String property;

    /**
     * Create a new property by parsing the given string.
     *
     * @param contents The string to parse.
     * @throws Exception If the part cannot be parsed.
     */
    public Part(String contents, String property) throws Exception {
        // Many of these regular expressions are adapted from those used in the YUI CSS Compressor.

        // For simpler regexes.
        this.contents = " " + contents;
        this.property = property;

        simplify();
    }

    private void simplify() {

        // !important doesn't need to be spaced
        this.contents = this.contents.replaceAll(" !important", "!important");

        // Replace 0in, 0cm, etc. with just 0
        this.contents = this.contents.replaceAll("(\\s)(0)(px|em|%|in|cm|mm|pc|pt|ex)", "$1$2");

        this.contents = this.contents.trim();

        // Simplify multiple zeroes
        if (this.contents.equals("0 0 0 0")) this.contents = "0";
        if (this.contents.equals("0 0 0")) this.contents = "0";
        if (this.contents.equals("0 0")) this.contents = "0";

        // Simplify multiple-parameter properties
        simplifyParameters();

        // Simplify font weights
        simplifyFontWeights();

        // Strip unnecessary quotes from url() and single-word parts, and make as much lowercase as possible.
        simplifyQuotesAndCaps();

        // Simplify colours
        simplifyColourNames();
        simplifyHexColours();
    }

    private void simplifyParameters() {
        if (this.property.equals("background-size")) return;

        StringBuilder newContents = new StringBuilder();

        String[] params = this.contents.split(" ");
        if (params.length == 4) {
            // We can drop off the fourth item if the second and fourth items match
            // ie turn 3px 0 3px 0 into 3px 0 3px
            if (params[1].equalsIgnoreCase(params[3])) {
                params = Arrays.copyOf(params, 3);
            }
        }
        if (params.length == 3) {
            // We can drop off the third item if the first and third items match
            // ie turn 3px 0 3px into 3px 0
            if (params[0].equalsIgnoreCase(params[2])) {
                params = Arrays.copyOf(params, 2);
            }
        }
        if (params.length == 2) {
            // We can drop off the second item if the first and second items match
            // ie turn 3px 3px into 3px
            if (params[0].equalsIgnoreCase(params[1])) {
                params = Arrays.copyOf(params, 1);
            }
        }

        for (String param : params) {
            newContents.append(param).append(" ");
        }
        newContents.deleteCharAt(newContents.length() - 1); // Delete the trailing space

        this.contents = newContents.toString();
    }

    private void simplifyFontWeights() {
        if (!this.property.equals("font-weight")) return;

        String lcContents = this.contents.toLowerCase();

        for (int i = 0; i < Constants.fontWeightNames.length; i++) {
            if (lcContents.equals(Constants.fontWeightNames[i])) {
                this.contents = Constants.fontWeightValues[i];
                break;
            }
        }
    }

    private void simplifyQuotesAndCaps() {
        // Strip quotes from URLs
        if ((this.contents.length() > 4) && (this.contents.substring(0, 4).equalsIgnoreCase("url("))) {
            this.contents = this.contents.replaceAll("(?i)url\\(('|\")?(.*?)\\1\\)", "url($2)");
        } else if ((this.contents.length() > 4) && (this.contents.substring(0, 4).equalsIgnoreCase("var("))) {
            this.contents = this.contents.replaceAll("\\s", "");
        } else {
            String[] words = this.contents.split("\\s");
            if (words.length == 1) {
                if (!this.property.equalsIgnoreCase("animation-name")) {
                    this.contents = this.contents.toLowerCase();
                }
                this.contents = this.contents.replaceAll("('|\")?(.*?)\1", "$2");
            }
        }
    }

    private void simplifyColourNames() {
        String lcContents = this.contents.toLowerCase();

        for (int i = 0; i < Constants.htmlColourNames.length; i++) {
            if (lcContents.equals(Constants.htmlColourNames[i])) {
                if (Constants.htmlColourValues[i].length() < Constants.htmlColourNames[i].length()) {
                    this.contents = Constants.htmlColourValues[i];
                }
                break;
            } else if (lcContents.equals(Constants.htmlColourValues[i])) {
                if (Constants.htmlColourNames[i].length() < Constants.htmlColourValues[i].length()) {
                    this.contents = Constants.htmlColourNames[i];
                }
            }
        }
    }

    private void simplifyHexColours() {
        StringBuffer newContents = new StringBuffer();

        Pattern pattern = Pattern.compile("#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])");
        Matcher matcher = pattern.matcher(this.contents);

        while (matcher.find()) {
            if (matcher.group(1).equalsIgnoreCase(matcher.group(2)) && matcher.group(3).equalsIgnoreCase(matcher.group(4)) && matcher.group(5).equalsIgnoreCase(matcher.group(6))) {
                matcher.appendReplacement(newContents, "#" + matcher.group(1).toLowerCase() + matcher.group(3).toLowerCase() + matcher.group(5).toLowerCase());
            } else {
                matcher.appendReplacement(newContents, matcher.group().toLowerCase());
            }
        }
        matcher.appendTail(newContents);

        this.contents = newContents.toString();
    }

    /**
     * Returns itself.
     *
     * @return this part's string representation.
     */
    public String toString() {
        return this.contents;
    }
}

class UnterminatedCommentException extends Exception {}

class UnbalancedBracesException extends Exception {}

class IncompletePropertyException extends Exception {
    String message = null;

    public IncompletePropertyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

class EmptySelectorBodyException extends Exception {
    String message = null;

    public EmptySelectorBodyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

class UnterminatedSelectorException extends Exception {
    String message = null;

    public UnterminatedSelectorException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

class IncompleteSelectorException extends Exception {
    String message = null;

    public IncompleteSelectorException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

class Constants {
    static final String[] htmlColourNames  = { "aliceblue", "antiquewhite", "aqua", "aquamarine", "azure", "beige", "bisque", "black", "blanchedalmond", "blue", "blueviolet", "brown", "burlywood", "cadetblue", "chartreuse", "chocolate", "coral", "cornflowerblue", "cornsilk", "crimson", "cyan", "darkblue", "darkcyan", "darkgoldenrod", "darkgray", "darkgreen", "darkkhaki", "darkmagenta", "darkolivegreen", "darkorange", "darkorchid", "darkred", "darksalmon", "darkseagreen", "darkslateblue", "darkslategray", "darkturquoise", "darkviolet", "deeppink", "deepskyblue", "dimgray", "dodgerblue", "firebrick", "floralwhite", "forestgreen", "fuchsia", "gainsboro", "ghostwhite", "gold", "goldenrod", "gray", "green", "greenyellow", "honeydew", "hotpink", "indianred ", "indigo ", "ivory", "khaki", "lavender", "lavenderblush", "lawngreen", "lemonchiffon", "lightblue", "lightcoral", "lightcyan", "lightgoldenrodyellow", "lightgrey", "lightgreen", "lightpink", "lightsalmon", "lightseagreen", "lightskyblue", "lightslategray", "lightsteelblue", "lightyellow", "lime", "limegreen", "linen", "magenta", "maroon", "mediumaquamarine", "mediumblue", "mediumorchid", "mediumpurple", "mediumseagreen", "mediumslateblue", "mediumspringgreen", "mediumturquoise", "mediumvioletred", "midnightblue", "mintcream", "mistyrose", "moccasin", "navajowhite", "navy", "oldlace", "olive", "olivedrab", "orange", "orangered", "orchid", "palegoldenrod", "palegreen", "paleturquoise", "palevioletred", "papayawhip", "peachpuff", "peru", "pink", "plum", "powderblue", "purple", "red", "rosybrown", "royalblue", "saddlebrown", "salmon", "sandybrown", "seagreen", "seashell", "sienna", "silver", "skyblue", "slateblue", "slategray", "snow", "springgreen", "steelblue", "tan", "teal", "thistle", "tomato", "turquoise", "violet", "wheat", "white", "whitesmoke", "yellow", "yellowgreen" };
    static final String[] htmlColourValues = { "#f0f8ff", "#faebd7", "#00ffff", "#7fffd4", "#f0ffff", "#f5f5dc", "#ffe4c4", "#000", "#ffebcd", "#00f", "#8a2be2", "#a52a2a", "#deb887", "#5f9ea0", "#7fff00", "#d2691e", "#ff7f50", "#6495ed", "#fff8dc", "#dc143c", "#0ff", "#00008b", "#008b8b", "#b8860b", "#a9a9a9", "#006400", "#bdb76b", "#8b008b", "#556b2f", "#ff8c00", "#9932cc", "#8b0000", "#e9967a", "#8fbc8f", "#483d8b", "#2f4f4f", "#00ced1", "#9400d3", "#ff1493", "#00bfff", "#696969", "#1e90ff", "#b22222", "#fffaf0", "#228b22", "#f0f", "#dcdcdc", "#f8f8ff", "#ffd700", "#daa520", "#808080", "#008000", "#adff2f", "#f0fff0", "#ff69b4", "#cd5c5c", "#4b0082", "#fffff0", "#f0e68c", "#e6e6fa", "#fff0f5", "#7cfc00", "#fffacd", "#add8e6", "#f08080", "#e0ffff", "#fafad2", "#d3d3d3", "#90ee90", "#ffb6c1", "#ffa07a", "#20b2aa", "#87cefa", "#789", "#b0c4de", "#ffffe0", "#0f0", "#32cd32", "#faf0e6", "#f0f", "#800000", "#66cdaa", "#0000cd", "#ba55d3", "#9370d8", "#3cb371", "#7b68ee", "#00fa9a", "#48d1cc", "#c71585", "#191970", "#f5fffa", "#ffe4e1", "#ffe4b5", "#ffdead", "#000080", "#fdf5e6", "#808000", "#6b8e23", "#ffa500", "#ff4500", "#da70d6", "#eee8aa", "#98fb98", "#afeeee", "#d87093", "#ffefd5", "#ffdab9", "#cd853f", "#ffc0cb", "#dda0dd", "#b0e0e6", "#800080", "#f00", "#bc8f8f", "#4169e1", "#8b4513", "#fa8072", "#f4a460", "#2e8b57", "#fff5ee", "#a0522d", "#c0c0c0", "#87ceeb", "#6a5acd", "#708090", "#fffafa", "#00ff7f", "#4682b4", "#d2b48c", "#008080", "#d8bfd8", "#ff6347", "#40e0d0", "#ee82ee", "#f5deb3", "#fff", "#f5f5f5", "#ff0", "#9acd32" };
    static final String[] fontWeightNames  = { "normal", "bold", "bolder", "lighter" };
    static final String[] fontWeightValues = { "400", "700", "900", "100" };
}
