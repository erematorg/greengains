package com.fasterxml.jackson.core;

import A.a;
import androidx.browser.trusted.c;
import org.objectweb.asm.signature.SignatureVisitor;
import org.slf4j.Marker;

public final class Base64Variants {
    public static final Base64Variant MIME;
    public static final Base64Variant MIME_NO_LINEFEEDS;
    public static final Base64Variant MODIFIED_FOR_URL;
    public static final Base64Variant PEM;
    static final String STD_BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    static {
        Base64Variant base64Variant = new Base64Variant("MIME", STD_BASE64_ALPHABET, true, (char) SignatureVisitor.INSTANCEOF, 76);
        MIME = base64Variant;
        MIME_NO_LINEFEEDS = new Base64Variant(base64Variant, "MIME-NO-LINEFEEDS", Integer.MAX_VALUE);
        PEM = new Base64Variant(base64Variant, "PEM", true, (char) SignatureVisitor.INSTANCEOF, 64);
        StringBuilder sb = new StringBuilder(STD_BASE64_ALPHABET);
        sb.setCharAt(sb.indexOf(Marker.ANY_NON_NULL_MARKER), SignatureVisitor.SUPER);
        sb.setCharAt(sb.indexOf("/"), '_');
        MODIFIED_FOR_URL = new Base64Variant("MODIFIED-FOR-URL", sb.toString(), false, 0, Integer.MAX_VALUE);
    }

    public static Base64Variant getDefaultVariant() {
        return MIME_NO_LINEFEEDS;
    }

    public static Base64Variant valueOf(String str) throws IllegalArgumentException {
        Base64Variant base64Variant = MIME;
        if (base64Variant._name.equals(str)) {
            return base64Variant;
        }
        Base64Variant base64Variant2 = MIME_NO_LINEFEEDS;
        if (base64Variant2._name.equals(str)) {
            return base64Variant2;
        }
        Base64Variant base64Variant3 = PEM;
        if (base64Variant3._name.equals(str)) {
            return base64Variant3;
        }
        Base64Variant base64Variant4 = MODIFIED_FOR_URL;
        if (base64Variant4._name.equals(str)) {
            return base64Variant4;
        }
        throw new IllegalArgumentException(c.a("No Base64Variant with name ", str == null ? "<null>" : a.l("'", str, "'")));
    }
}
