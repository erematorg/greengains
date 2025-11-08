package com.kenai.jffi;

public final class NativeMethod {
    final long function;
    final String name;
    final String signature;

    public NativeMethod(long j2, String str, String str2) {
        this.function = j2;
        this.name = str;
        this.signature = str2;
    }
}
