package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class zzf extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzg zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ zzoe zzd;
    private /* synthetic */ Object zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzf(zzg zzg, long j2, zzoe zzoe, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzg;
        this.zzc = j2;
        this.zzd = zzoe;
    }

    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        zzf zzf = new zzf(this.zzb, this.zzc, this.zzd, continuation);
        zzf.zze = obj;
        return zzf;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzf) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Ref.ObjectRef objectRef;
        Object obj2;
        Object obj3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza != 0) {
            ResultKt.throwOnFailure(obj);
            objectRef = (Ref.ObjectRef) this.zze;
            obj2 = obj;
        } else {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.zze;
            ArrayList arrayList = new ArrayList();
            for (zza zze2 : this.zzb.zzc()) {
                arrayList.add(BuildersKt__Builders_commonKt.async$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new zze(zze2, this.zzc, this.zzd, (Continuation) null), 3, (Object) null));
            }
            objectRef = new Ref.ObjectRef();
            Deferred[] deferredArr = (Deferred[]) arrayList.toArray(new Deferred[0]);
            this.zze = objectRef;
            this.zza = 1;
            obj2 = AwaitKt.awaitAll((Deferred<? extends T>[]) (Deferred[]) Arrays.copyOf(deferredArr, deferredArr.length), this);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        for (Result r12 : (List) obj2) {
            T r13 = Result.m8982exceptionOrNullimpl(r12.m8988unboximpl());
            if (r13 != null) {
                T t2 = null;
                if (objectRef.element != null) {
                    t2 = new zzp(zzn.zzc, zzl.zzal, (String) null);
                } else if (r13 instanceof zzp) {
                    t2 = (zzp) r13;
                }
                objectRef.element = t2;
            }
        }
        zzp zzp = (zzp) objectRef.element;
        if (zzp != null) {
            Result.Companion companion = Result.Companion;
            obj3 = ResultKt.createFailure(zzp);
        } else {
            Result.Companion companion2 = Result.Companion;
            obj3 = Unit.INSTANCE;
        }
        return Result.m8978boximpl(Result.m8979constructorimpl(obj3));
    }
}
