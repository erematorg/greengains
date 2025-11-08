package com.google.android.play.integrity.internal;

import org.apache.commons.text.StringSubstitutor;

final class e extends f {

    /* renamed from: a  reason: collision with root package name */
    private final int f6835a;

    /* renamed from: b  reason: collision with root package name */
    private final long f6836b;

    public e(int i3, long j2) {
        this.f6835a = i3;
        this.f6836b = j2;
    }

    public final int a() {
        return this.f6835a;
    }

    public final long b() {
        return this.f6836b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            return this.f6835a == fVar.a() && this.f6836b == fVar.b();
        }
    }

    public final int hashCode() {
        long j2 = this.f6836b;
        return ((int) (j2 ^ (j2 >>> 32))) ^ ((this.f6835a ^ 1000003) * 1000003);
    }

    public final String toString() {
        int i3 = this.f6835a;
        long j2 = this.f6836b;
        return "EventRecord{eventType=" + i3 + ", eventTimestamp=" + j2 + StringSubstitutor.DEFAULT_VAR_END;
    }
}
