package com.sun.jna;

import A.a;

public class LastErrorException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private int errorCode;

    public LastErrorException(String str) {
        super(parseMessage(str.trim()));
        try {
            this.errorCode = Integer.parseInt(str.startsWith("[") ? str.substring(1, str.indexOf("]")) : str);
        } catch (NumberFormatException unused) {
            this.errorCode = -1;
        }
    }

    private static String formatMessage(int i3) {
        return Platform.isWindows() ? a.k("GetLastError() returned ", i3) : a.k("errno was ", i3);
    }

    private static String parseMessage(String str) {
        try {
            return formatMessage(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public LastErrorException(int i3) {
        this(i3, formatMessage(i3));
    }

    public LastErrorException(int i3, String str) {
        super(str);
        this.errorCode = i3;
    }
}
