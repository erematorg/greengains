package com.appsamurai.storyly.util;

import androidx.compose.animation.core.a;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class k extends Lambda implements Function1<Byte, CharSequence> {

    /* renamed from: a  reason: collision with root package name */
    public static final k f6341a = new k();

    public k() {
        super(1);
    }

    public Object invoke(Object obj) {
        return a.t(new Object[]{Byte.valueOf(((Number) obj).byteValue())}, 1, "%02x", "java.lang.String.format(this, *args)");
    }
}
