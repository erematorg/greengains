package com.neovisionaries.ws.client;

class Token {
    public static boolean isSeparator(char c3) {
        if (c3 == 9 || c3 == ' ' || c3 == '\"' || c3 == ',' || c3 == '/' || c3 == '{' || c3 == '}' || c3 == '(' || c3 == ')') {
            return true;
        }
        switch (c3) {
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
                return true;
            default:
                switch (c3) {
                    case '[':
                    case '\\':
                    case ']':
                        return true;
                    default:
                        return false;
                }
        }
    }

    public static boolean isValid(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            if (isSeparator(str.charAt(i3))) {
                return false;
            }
        }
        return true;
    }

    public static String unescape(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(92) < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        boolean z2 = false;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (charAt != '\\' || z2) {
                sb.append(charAt);
                z2 = false;
            } else {
                z2 = true;
            }
        }
        return sb.toString();
    }

    public static String unquote(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length < 2 || str.charAt(0) != '\"') {
            return str;
        }
        int i3 = length - 1;
        return str.charAt(i3) != '\"' ? str : unescape(str.substring(1, i3));
    }
}
