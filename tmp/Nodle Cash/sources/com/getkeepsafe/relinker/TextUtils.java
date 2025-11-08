package com.getkeepsafe.relinker;

final class TextUtils {
    private TextUtils() {
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
