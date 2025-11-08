package com.google.crypto.tink;

import androidx.browser.trusted.c;
import java.security.GeneralSecurityException;

public final class KeyTemplates {
    private KeyTemplates() {
    }

    public static KeyTemplate get(String str) throws GeneralSecurityException {
        KeyTemplate keyTemplate = Registry.keyTemplateMap().get(str);
        if (keyTemplate != null) {
            return keyTemplate;
        }
        throw new GeneralSecurityException(c.a("cannot find key template: ", str));
    }
}
