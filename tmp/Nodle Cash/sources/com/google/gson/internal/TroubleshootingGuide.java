package com.google.gson.internal;

import androidx.browser.trusted.c;

public class TroubleshootingGuide {
    private TroubleshootingGuide() {
    }

    public static String createUrl(String str) {
        return c.a("https://github.com/google/gson/blob/main/Troubleshooting.md#", str);
    }
}
