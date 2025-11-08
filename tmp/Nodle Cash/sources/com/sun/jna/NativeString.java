package com.sun.jna;

class NativeString implements CharSequence, Comparable {
    static final String WIDE_STRING = "--WIDE-STRING--";
    private String encoding;
    private Pointer pointer;

    public class StringMemory extends Memory {
        public StringMemory(long j2) {
            super(j2);
        }

        public String toString() {
            return NativeString.this.toString();
        }
    }

    public NativeString(String str) {
        this(str, Native.getDefaultStringEncoding());
    }

    public char charAt(int i3) {
        return toString().charAt(i3);
    }

    public int compareTo(Object obj) {
        if (obj == null) {
            return 1;
        }
        return toString().compareTo(obj.toString());
    }

    public boolean equals(Object obj) {
        return (obj instanceof CharSequence) && compareTo(obj) == 0;
    }

    public Pointer getPointer() {
        return this.pointer;
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
        return WIDE_STRING.equals(this.encoding) ? this.pointer.getWideString(0) : this.pointer.getString(0, this.encoding);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NativeString(String str, boolean z2) {
        this(str, z2 ? WIDE_STRING : Native.getDefaultStringEncoding());
    }

    public NativeString(WString wString) {
        this(wString.toString(), WIDE_STRING);
    }

    public NativeString(String str, String str2) {
        if (str != null) {
            this.encoding = str2;
            if (WIDE_STRING.equals(str2)) {
                StringMemory stringMemory = new StringMemory((long) ((str.length() + 1) * Native.WCHAR_SIZE));
                this.pointer = stringMemory;
                stringMemory.setWideString(0, str);
                return;
            }
            byte[] bytes = Native.getBytes(str, str2);
            StringMemory stringMemory2 = new StringMemory((long) (bytes.length + 1));
            this.pointer = stringMemory2;
            stringMemory2.write(0, bytes, 0, bytes.length);
            this.pointer.setByte((long) bytes.length, (byte) 0);
            return;
        }
        throw new NullPointerException("String must not be null");
    }
}
