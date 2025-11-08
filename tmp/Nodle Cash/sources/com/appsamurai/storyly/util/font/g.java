package com.appsamurai.storyly.util.font;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@DebugMetadata(c = "com.appsamurai.storyly.util.font.FontManager", f = "FontManager.kt", i = {0, 0, 0}, l = {51}, m = "requestFont", n = {"this", "font", "fileName"}, s = {"L$0", "L$1", "L$2"})
public final class g extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    public Object f6325a;

    /* renamed from: b  reason: collision with root package name */
    public Object f6326b;

    /* renamed from: c  reason: collision with root package name */
    public Object f6327c;

    /* renamed from: d  reason: collision with root package name */
    public /* synthetic */ Object f6328d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ f f6329e;

    /* renamed from: f  reason: collision with root package name */
    public int f6330f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(f fVar, Continuation<? super g> continuation) {
        super(continuation);
        this.f6329e = fVar;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f6328d = obj;
        this.f6330f |= Integer.MIN_VALUE;
        return f.a(this.f6329e, (h) null, this);
    }
}
