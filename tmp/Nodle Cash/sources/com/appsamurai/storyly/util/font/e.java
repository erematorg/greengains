package com.appsamurai.storyly.util.font;

import android.graphics.Typeface;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@DebugMetadata(c = "com.appsamurai.storyly.util.font.FontManager$fetchFont$1", f = "FontManager.kt", i = {}, l = {37, 37, 38}, m = "invokeSuspend", n = {}, s = {})
public final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public int f6315a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ h f6316b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ f f6317c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Function1<Typeface, Unit> f6318d;

    @DebugMetadata(c = "com.appsamurai.storyly.util.font.FontManager$fetchFont$1$1", f = "FontManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function1<Typeface, Unit> f6319a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ Typeface f6320b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Function1<? super Typeface, Unit> function1, Typeface typeface, Continuation<? super a> continuation) {
            super(2, continuation);
            this.f6319a = function1;
            this.f6320b = typeface;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.f6319a, this.f6320b, continuation);
        }

        public Object invoke(Object obj, Object obj2) {
            CoroutineScope coroutineScope = (CoroutineScope) obj;
            return new a(this.f6319a, this.f6320b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            Function1<Typeface, Unit> function1 = this.f6319a;
            Typeface typeface = this.f6320b;
            Intrinsics.checkNotNullExpressionValue(typeface, "typeface");
            function1.invoke(typeface);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(h hVar, f fVar, Function1<? super Typeface, Unit> function1, Continuation<? super e> continuation) {
        super(2, continuation);
        this.f6316b = hVar;
        this.f6317c = fVar;
        this.f6318d = function1;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new e(this.f6316b, this.f6317c, this.f6318d, continuation);
    }

    public Object invoke(Object obj, Object obj2) {
        CoroutineScope coroutineScope = (CoroutineScope) obj;
        return new e(this.f6316b, this.f6317c, this.f6318d, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0069 A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.f6315a
            r2 = 2
            r3 = 1
            r4 = 0
            r5 = 3
            if (r1 == 0) goto L_0x0026
            if (r1 == r3) goto L_0x0022
            if (r1 == r2) goto L_0x001e
            if (r1 != r5) goto L_0x0016
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x006a
        L_0x0016:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x001e:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x004f
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x003a
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r7)
            com.appsamurai.storyly.util.font.h r7 = r6.f6316b
            android.graphics.Typeface r1 = r7.f6332b
            if (r1 != 0) goto L_0x0056
            com.appsamurai.storyly.util.font.f r1 = r6.f6317c
            r6.f6315a = r3
            java.lang.Object r7 = com.appsamurai.storyly.util.font.f.a(r1, r7, r6)
            if (r7 != r0) goto L_0x003a
            return r0
        L_0x003a:
            r1 = r7
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 != 0) goto L_0x0056
            com.appsamurai.storyly.util.font.f r7 = r6.f6317c
            com.appsamurai.storyly.util.font.h r1 = new com.appsamurai.storyly.util.font.h
            r1.<init>(r4, r4, r5)
            r6.f6315a = r2
            java.lang.Object r7 = com.appsamurai.storyly.util.font.f.a(r7, r1, r6)
            if (r7 != r0) goto L_0x004f
            return r0
        L_0x004f:
            r1 = r7
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 != 0) goto L_0x0056
            android.graphics.Typeface r1 = android.graphics.Typeface.DEFAULT
        L_0x0056:
            kotlinx.coroutines.MainCoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getMain()
            com.appsamurai.storyly.util.font.e$a r2 = new com.appsamurai.storyly.util.font.e$a
            kotlin.jvm.functions.Function1<android.graphics.Typeface, kotlin.Unit> r3 = r6.f6318d
            r2.<init>(r3, r1, r4)
            r6.f6315a = r5
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r7, r2, r6)
            if (r6 != r0) goto L_0x006a
            return r0
        L_0x006a:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.util.font.e.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
