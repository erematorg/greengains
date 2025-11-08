package com.caverock.androidsvg;

import org.xml.sax.SAXException;

public class SVGParseException extends SAXException {
    public SVGParseException(String str) {
        super(str);
    }

    public SVGParseException(String str, Exception exc) {
        super(str, exc);
    }
}
