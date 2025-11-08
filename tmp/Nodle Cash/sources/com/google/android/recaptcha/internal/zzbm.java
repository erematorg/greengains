package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzbm implements zzbh {
    @NotNull
    public static final zzbi zza = new zzbi((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @Nullable
    public static Timer zzb;
    @NotNull
    private final zzbn zzc;
    /* access modifiers changed from: private */
    @NotNull
    public final CoroutineScope zzd;
    /* access modifiers changed from: private */
    @Nullable
    public final zzaz zze;

    public zzbm(@NotNull Context context, @NotNull zzbn zzbn, @NotNull CoroutineScope coroutineScope) {
        this.zzc = zzbn;
        this.zzd = coroutineScope;
        zzaz zzaz = null;
        try {
            zzaz zzc2 = zzaz.zzc;
            zzc2 = zzc2 == null ? new zzaz(context, (DefaultConstructorMarker) null) : zzc2;
            zzaz.zzc = zzc2;
            zzaz = zzc2;
        } catch (Exception unused) {
        }
        this.zze = zzaz;
        zzh();
    }

    /* access modifiers changed from: private */
    public final void zzg() {
        zzaz zzaz;
        zzaz zzaz2 = this.zze;
        if (zzaz2 != null) {
            for (List<zzba> it : CollectionsKt___CollectionsKt.windowed(zzaz2.zzd(), 20, 20, true)) {
                zznh zzi = zzni.zzi();
                ArrayList arrayList = new ArrayList();
                for (zzba zzba : it) {
                    try {
                        zzpd zzk = zzpd.zzk(zzfy.zzg().zzj(zzba.zzc()));
                        int zzJ = zzk.zzJ();
                        int i3 = zzJ - 1;
                        if (zzJ != 0) {
                            if (i3 == 0) {
                                zzi.zzp(zzk.zzf());
                            } else if (i3 == 1) {
                                zzi.zzq(zzk.zzg());
                            }
                            arrayList.add(zzba);
                        } else {
                            throw null;
                        }
                    } catch (Exception unused) {
                        zzaz zzaz3 = this.zze;
                        if (zzaz3 != null) {
                            zzaz3.zzf(zzba);
                        }
                    }
                }
                if (zzi.zze() + zzi.zzd() != 0) {
                    if (this.zzc.zza(((zzni) zzi.zzj()).zzd()) && (zzaz = this.zze) != null) {
                        zzaz.zza(arrayList);
                    }
                }
            }
        }
    }

    private final void zzh() {
        if (zzb == null) {
            Timer timer = new Timer();
            zzb = timer;
            timer.schedule(new zzbj(this), 120000, 120000);
        }
    }

    public final void zza(@NotNull zzpd zzpd) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.zzd, (CoroutineContext) null, (CoroutineStart) null, new zzbl(this, zzpd, (Continuation) null), 3, (Object) null);
        zzh();
    }
}
