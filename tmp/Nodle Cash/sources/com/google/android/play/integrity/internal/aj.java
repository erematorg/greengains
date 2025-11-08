package com.google.android.play.integrity.internal;

public final class aj implements ai {

    /* renamed from: a  reason: collision with root package name */
    private static final aj f6832a = new aj((Object) null);

    /* renamed from: b  reason: collision with root package name */
    private final Object f6833b;

    private aj(Object obj) {
        this.f6833b = obj;
    }

    public static ai b(Object obj) {
        if (obj != null) {
            return new aj(obj);
        }
        throw new NullPointerException("instance cannot be null");
    }

    public final Object a() {
        return this.f6833b;
    }
}
