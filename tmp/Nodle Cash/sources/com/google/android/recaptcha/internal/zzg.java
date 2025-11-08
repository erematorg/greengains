package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzg {
    @NotNull
    private final List zza;

    public zzg() {
        this((List) null, 1, (DefaultConstructorMarker) null);
    }

    @Nullable
    public final Object zza(@NotNull String str, long j2, @NotNull Continuation continuation) {
        return CoroutineScopeKt.coroutineScope(new zzc(this, str, j2, (Continuation) null), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzb(long r11, @org.jetbrains.annotations.NotNull com.google.android.recaptcha.internal.zzoe r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.google.android.recaptcha.internal.zzd
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.google.android.recaptcha.internal.zzd r0 = (com.google.android.recaptcha.internal.zzd) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzd r0 = new com.google.android.recaptcha.internal.zzd
            r0.<init>(r10, r14)
        L_0x0018:
            java.lang.Object r14 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0047
        L_0x0029:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r14)
            com.google.android.recaptcha.internal.zzf r14 = new com.google.android.recaptcha.internal.zzf
            r9 = 0
            r4 = r14
            r5 = r10
            r6 = r11
            r8 = r13
            r4.<init>(r5, r6, r8, r9)
            r0.zzc = r3
            java.lang.Object r14 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r14, r0)
            if (r14 != r1) goto L_0x0047
            return r1
        L_0x0047:
            kotlin.Result r14 = (kotlin.Result) r14
            java.lang.Object r10 = r14.m8988unboximpl()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzg.zzb(long, com.google.android.recaptcha.internal.zzoe, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final List zzc() {
        return this.zza;
    }

    public final void zzd(@NotNull zza zza2) {
        this.zza.add(zza2);
    }

    public /* synthetic */ zzg(List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        List emptyList = CollectionsKt.emptyList();
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        arrayList.addAll(emptyList);
    }
}
