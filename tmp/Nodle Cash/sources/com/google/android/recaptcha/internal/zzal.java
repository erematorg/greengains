package com.google.android.recaptcha.internal;

import android.app.Application;
import android.os.Build;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class zzal extends SuspendLambda implements Function2 {
    final /* synthetic */ Application zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzbd zzc;
    final /* synthetic */ zzbq zzd;
    final /* synthetic */ zzab zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzal(Application application, String str, zzbd zzbd, zzbq zzbq, zzab zzab, Continuation continuation) {
        super(2, continuation);
        this.zza = application;
        this.zzb = str;
        this.zzc = zzbd;
        this.zzd = zzbq;
        this.zze = zzab;
    }

    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zzal(this.zza, this.zzb, this.zzc, this.zzd, this.zze, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzal) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzaf zzaf = zzaf.zza;
        zzbd zzbd = this.zzc;
        Application application = this.zza;
        String zza2 = zzaf.zza(application);
        String packageName = application.getPackageName();
        String zzd2 = zzbd.zzd();
        zzq zzq = new zzq(application);
        int i3 = Build.VERSION.SDK_INT;
        String zza3 = zzq.zza("_GRECAPTCHA_KC");
        if (zza3 == null) {
            zza3 = "";
        }
        String encode = URLEncoder.encode(this.zzb, "UTF-8");
        String encode2 = URLEncoder.encode(packageName, "UTF-8");
        String encode3 = URLEncoder.encode(zza2, "UTF-8");
        String encode4 = URLEncoder.encode("18.4.0", "UTF-8");
        String encode5 = URLEncoder.encode(zzd2, "UTF-8");
        StringBuilder l2 = C0118y.l("k=", encode, "&pk=", encode2, "&mst=");
        b.w(l2, encode3, "&msv=", encode4, "&msi=");
        b.v(l2, encode5, "&mov=", i3, "&mkc=");
        l2.append(zza3);
        byte[] bytes = l2.toString().getBytes(Charset.forName("UTF-8"));
        zzbq zzbq = this.zzd;
        zzab zzab = this.zze;
        return zzbq.zza(zzab.zzb(), bytes, this.zzc);
    }
}
