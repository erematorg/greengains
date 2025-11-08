package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.jetbrains.annotations.NotNull;

public final class zzt {
    @NotNull
    public static final zzr zza = new zzr((DefaultConstructorMarker) null);
    @NotNull
    private final CoroutineScope zzb = CoroutineScopeKt.MainScope();
    @NotNull
    private final CoroutineScope zzc;
    @NotNull
    private final CoroutineScope zzd;

    public zzt() {
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(ThreadPoolDispatcherKt.newSingleThreadContext("reCaptcha"));
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new zzs((Continuation) null), 3, (Object) null);
        this.zzc = CoroutineScope;
        this.zzd = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    }

    @NotNull
    public final CoroutineScope zza() {
        return this.zzd;
    }

    @NotNull
    public final CoroutineScope zzb() {
        return this.zzb;
    }

    @NotNull
    public final CoroutineScope zzc() {
        return this.zzc;
    }
}
