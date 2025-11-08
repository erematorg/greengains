package com.sun.jna;

public final class WString implements CharSequence, Comparable {
    private String string;

    public WString(String str) {
        if (str != null) {
            this.string = str;
            return;
        }
        throw new NullPointerException("String initializer must be non-null");
    }

    public char charAt(int i3) {
        return toString().charAt(i3);
    }

    public int compareTo(Object obj) {
        return toString().compareTo(obj.toString());
    }

    public boolean equals(Object obj) {
        return (obj instanceof WString) && toString().equals(obj.toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public int length() {
        return toString().length();
    }

    public CharSequence subSequence(int i3, int i4) {
        return toString().subSequence(i3, i4);
    }

    public String toString() {
        return this.string;
    }
}
