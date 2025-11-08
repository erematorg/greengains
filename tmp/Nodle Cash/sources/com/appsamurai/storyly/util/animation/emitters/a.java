package com.appsamurai.storyly.util.animation.emitters;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public final class a extends b {

    /* renamed from: b  reason: collision with root package name */
    public int f6278b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f6279c;

    public void a(float f2) {
        if (!this.f6279c) {
            this.f6279c = true;
            int i3 = this.f6278b;
            for (int i4 = 0; i4 < i3; i4++) {
                Function0<Unit> function0 = this.f6280a;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        }
    }

    public boolean a() {
        return this.f6279c;
    }
}
