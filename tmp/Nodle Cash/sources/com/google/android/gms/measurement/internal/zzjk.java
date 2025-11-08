package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.measurement.zzdl;
import com.google.android.gms.internal.measurement.zzox;
import com.google.android.gms.internal.measurement.zzpd;
import com.google.android.gms.internal.measurement.zzrl;
import com.google.android.gms.internal.measurement.zzrw;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzjc;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import org.checkerframework.dataflow.qual.Pure;

public final class zzjk extends zzf {
    final zzs zza;
    @VisibleForTesting
    private zzkw zzb;
    private zzjg zzc;
    private final Set<zzjj> zzd = new CopyOnWriteArraySet();
    private boolean zze;
    private final AtomicReference<String> zzf = new AtomicReference<>();
    private final Object zzg = new Object();
    /* access modifiers changed from: private */
    public boolean zzh = false;
    /* access modifiers changed from: private */
    public int zzi = 1;
    private zzax zzj;
    private PriorityQueue<zznk> zzk;
    private boolean zzl;
    @GuardedBy("consentLock")
    private zzjc zzm = zzjc.zza;
    private final AtomicLong zzn = new AtomicLong(0);
    private long zzo = -1;
    @VisibleForTesting
    private boolean zzp = true;
    /* access modifiers changed from: private */
    public zzax zzq;
    private SharedPreferences.OnSharedPreferenceChangeListener zzr;
    private zzax zzs;
    private final zzoo zzt = new zzko(this);

    public zzjk(zzhw zzhw) {
        super(zzhw);
        this.zza = new zzs(zzhw);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzav() {
        zzt();
        String zza2 = zzk().zzh.zza();
        if (zza2 != null) {
            if ("unset".equals(zza2)) {
                zza("app", "_npa", (Object) null, zzb().currentTimeMillis());
            } else {
                zza("app", "_npa", (Object) Long.valueOf("true".equals(zza2) ? 1 : 0), zzb().currentTimeMillis());
            }
        }
        if (!this.zzu.zzac() || !this.zzp) {
            zzj().zzc().zza("Updating Scion state (FE)");
            zzo().zzak();
            return;
        }
        zzj().zzc().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzan();
        zzp().zza.zza();
        zzl().zzb((Runnable) new zzka(this));
    }

    private final Bundle zzf(Bundle bundle) {
        Bundle zza2 = zzk().zzt.zza();
        for (String next : bundle.keySet()) {
            Object obj = bundle.get(next);
            if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                zzq();
                if (zzop.zza(obj)) {
                    zzq();
                    zzop.zza(this.zzt, 27, (String) null, (String) null, 0);
                }
                zzj().zzv().zza("Invalid default event parameter type. Name, value", next, obj);
            } else if (zzop.zzg(next)) {
                zzj().zzv().zza("Invalid default event parameter name. Name", next);
            } else if (obj == null) {
                zza2.remove(next);
            } else if (zzq().zza("param", next, zze().zza((String) null, false), obj)) {
                zzq().zza(zza2, next, obj);
            }
        }
        zzq();
        if (zzop.zza(zza2, zze().zzg())) {
            zzq();
            zzop.zza(this.zzt, 26, (String) null, (String) null, 0);
            zzj().zzv().zza("Too many default event parameters set. Discarding beyond event parameter limit");
        }
        return zza2;
    }

    public final Application.ActivityLifecycleCallbacks zzaa() {
        return this.zzb;
    }

    @WorkerThread
    public final zzak zzab() {
        zzt();
        return zzo().zzaa();
    }

    public final Boolean zzac() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzl().zza(atomicReference, 15000, "boolean test flag value", new zzjx(this, atomicReference));
    }

    public final Double zzad() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzl().zza(atomicReference, 15000, "double test flag value", new zzkt(this, atomicReference));
    }

    public final Integer zzae() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzl().zza(atomicReference, 15000, "int test flag value", new zzkq(this, atomicReference));
    }

    public final Long zzaf() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzl().zza(atomicReference, 15000, "long test flag value", new zzkr(this, atomicReference));
    }

    public final String zzag() {
        return this.zzf.get();
    }

    public final String zzah() {
        zzlh zzaa = this.zzu.zzq().zzaa();
        if (zzaa != null) {
            return zzaa.zzb;
        }
        return null;
    }

    public final String zzai() {
        zzlh zzaa = this.zzu.zzq().zzaa();
        if (zzaa != null) {
            return zzaa.zza;
        }
        return null;
    }

    public final String zzaj() {
        if (this.zzu.zzu() != null) {
            return this.zzu.zzu();
        }
        try {
            return new zzhq(zza(), this.zzu.zzx()).zza("google_app_id");
        } catch (IllegalStateException e3) {
            this.zzu.zzj().zzg().zza("getGoogleAppId failed with exception", e3);
            return null;
        }
    }

    public final String zzak() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzl().zza(atomicReference, 15000, "String test flag value", new zzkg(this, atomicReference));
    }

    @TargetApi(30)
    public final PriorityQueue<zznk> zzal() {
        if (this.zzk == null) {
            this.zzk = new PriorityQueue<>(Comparator.comparing(new zzjn(), new zzjm()));
        }
        return this.zzk;
    }

    @WorkerThread
    public final void zzam() {
        zzt();
        zzu();
        if (zze().zza(zzbj.zzdh)) {
            zzlp zzo2 = zzo();
            zzo2.zzt();
            zzo2.zzu();
            if (!zzo2.zzap() || zzo2.zzq().zzg() >= 242600) {
                zzo().zzac();
            }
        }
    }

    @WorkerThread
    public final void zzan() {
        zzt();
        zzu();
        if (this.zzu.zzaf()) {
            Boolean zze2 = zze().zze("google_analytics_deferred_deep_link_enabled");
            if (zze2 != null && zze2.booleanValue()) {
                zzj().zzc().zza("Deferred Deep Link feature enabled.");
                zzl().zzb((Runnable) new zzjt(this));
            }
            zzo().zzad();
            this.zzp = false;
            String zzw = zzk().zzw();
            if (!TextUtils.isEmpty(zzw)) {
                zzf().zzac();
                if (!zzw.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzw);
                    zzc("auto", "_ou", bundle);
                }
            }
        }
    }

    public final void zzao() {
        if ((zza().getApplicationContext() instanceof Application) && this.zzb != null) {
            ((Application) zza().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zzb);
        }
    }

    public final void zzap() {
        if (zzrl.zza() && zze().zza(zzbj.zzch)) {
            if (zzl().zzg()) {
                zzj().zzg().zza("Cannot get trigger URIs from analytics worker thread");
            } else if (zzac.zza()) {
                zzj().zzg().zza("Cannot get trigger URIs from main thread");
            } else {
                zzu();
                zzj().zzp().zza("Getting trigger URIs (FE)");
                AtomicReference atomicReference = new AtomicReference();
                zzl().zza(atomicReference, 5000, "get trigger URIs", new zzjp(this, atomicReference));
                List list = (List) atomicReference.get();
                if (list == null) {
                    zzj().zzg().zza("Timed out waiting for get trigger URIs");
                } else {
                    zzl().zzb((Runnable) new zzjo(this, list));
                }
            }
        }
    }

    @WorkerThread
    public final void zzaq() {
        zzt();
        if (zzk().zzo.zza()) {
            zzj().zzc().zza("Deferred Deep Link already retrieved. Not fetching again.");
            return;
        }
        long zza2 = zzk().zzp.zza();
        zzk().zzp.zza(1 + zza2);
        if (zza2 >= 5) {
            zzj().zzu().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
            zzk().zzo.zza(true);
            return;
        }
        if (this.zzq == null) {
            this.zzq = new zzkj(this, this.zzu);
        }
        this.zzq.zza(0);
    }

    @WorkerThread
    public final void zzar() {
        zzt();
        zzj().zzc().zza("Handle tcf update.");
        zzni zza2 = zzni.zza(zzk().zzc());
        zzj().zzp().zza("Tcf preferences read", zza2);
        if (zzk().zza(zza2)) {
            Bundle zza3 = zza2.zza();
            zzj().zzp().zza("Consent generated from Tcf", zza3);
            if (zza3 != Bundle.EMPTY) {
                zza(zza3, -30, zzb().currentTimeMillis());
            }
            Bundle bundle = new Bundle();
            bundle.putString("_tcfd", zza2.zzb());
            zzc("auto", "_tcf", bundle);
        }
    }

    @WorkerThread
    @TargetApi(30)
    public final void zzas() {
        zznk poll;
        MeasurementManagerFutures zzn2;
        zzt();
        this.zzl = false;
        if (!zzal().isEmpty() && !this.zzh && (poll = zzal().poll()) != null && (zzn2 = zzq().zzn()) != null) {
            this.zzh = true;
            zzj().zzp().zza("Registering trigger URI", poll.zza);
            ListenableFuture<Unit> registerTriggerAsync = zzn2.registerTriggerAsync(Uri.parse(poll.zza));
            if (registerTriggerAsync == null) {
                this.zzh = false;
                zzal().add(poll);
                return;
            }
            if (!zze().zza(zzbj.zzcm)) {
                SparseArray<Long> zzh2 = zzk().zzh();
                zzh2.put(poll.zzc, Long.valueOf(poll.zzb));
                zzk().zza(zzh2);
            }
            Futures.addCallback(registerTriggerAsync, new zzjz(this, poll), new zzjw(this));
        }
    }

    @WorkerThread
    public final void zzat() {
        zzt();
        zzj().zzc().zza("Register tcfPrefChangeListener.");
        if (this.zzr == null) {
            this.zzs = new zzkc(this, this.zzu);
            this.zzr = new zzjv(this);
        }
        zzk().zzc().registerOnSharedPreferenceChangeListener(this.zzr);
    }

    public final boolean zzau() {
        return this.zzl;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzgc zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgi zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgu zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhp zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzjk zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzlg zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzlp zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzop zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final boolean zzz() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zza zzc() {
        return super.zzc();
    }

    public final void zzd(Bundle bundle, long j2) {
        zza(bundle, -20, j2);
    }

    public final void zze(Bundle bundle) {
        if (zze().zza(zzbj.zzdl)) {
            zzl().zzb((Runnable) new zzjs(this, bundle == null ? new Bundle() : new Bundle(bundle)));
        }
    }

    public static /* synthetic */ void zzb(zzjk zzjk, Bundle bundle) {
        Bundle bundle2 = bundle;
        zzjk.zzt();
        zzjk.zzu();
        Preconditions.checkNotNull(bundle);
        String string = bundle2.getString("name");
        String string2 = bundle2.getString("origin");
        Preconditions.checkNotEmpty(string);
        Preconditions.checkNotEmpty(string2);
        Preconditions.checkNotNull(bundle2.get("value"));
        if (!zzjk.zzu.zzac()) {
            zzjk.zzj().zzp().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        zzok zzok = new zzok(string, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle2.get("value"), string2);
        try {
            zzbh zza2 = zzjk.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), string2, 0, true, true);
            zzbh zza3 = zzjk.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), string2, 0, true, true);
            zzbh zza4 = zzjk.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), string2, 0, true, true);
            zzjk.zzo().zza(new zzaf(bundle2.getString("app_id"), string2, zzok, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zza3, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), zza2, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zza4));
        } catch (IllegalArgumentException unused) {
        }
    }

    @WorkerThread
    public final void zzc(String str, String str2, Bundle bundle) {
        zzt();
        zza(str, str2, zzb().currentTimeMillis(), bundle);
    }

    public final void zzd(Bundle bundle) {
        zzl().zzb((Runnable) new zzjr(this, bundle == null ? new Bundle() : new Bundle(bundle)));
    }

    public static int zza(String str) {
        Preconditions.checkNotEmpty(str);
        return 25;
    }

    public final void zzc(String str) {
        this.zzf.set(str);
    }

    public final void zzc(Bundle bundle) {
        zzb(bundle, zzb().currentTimeMillis());
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    public final void zzc(Bundle bundle, long j2) {
        zzl().zzc((Runnable) new zzjq(this, bundle, j2));
    }

    public static /* synthetic */ int zza(zzjk zzjk, Throwable th) {
        String message = th.getMessage();
        zzjk.zzl = false;
        if (message == null) {
            return 2;
        }
        if ((th instanceof IllegalStateException) || message.contains("garbage collected") || th.getClass().getSimpleName().equals("ServiceUnavailableException")) {
            if (message.contains("Background")) {
                zzjk.zzl = true;
            }
            return 1;
        } else if (!(th instanceof SecurityException) || message.endsWith("READ_DEVICE_CONFIG")) {
            return 2;
        } else {
            return 3;
        }
    }

    public final void zzc(boolean z2) {
        zzu();
        zzl().zzb((Runnable) new zzkb(this, z2));
    }

    public final void zzc(long j2) {
        zzl().zzb((Runnable) new zzkd(this, j2));
    }

    public final ArrayList<Bundle> zza(String str, String str2) {
        if (zzl().zzg()) {
            zzj().zzg().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        } else if (zzac.zza()) {
            zzj().zzg().zza("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzu.zzl().zza(atomicReference, 5000, "get conditional user properties", new zzkn(this, atomicReference, (String) null, str, str2));
            List list = (List) atomicReference.get();
            if (list != null) {
                return zzop.zzb((List<zzaf>) list);
            }
            zzj().zzg().zza("Timed out waiting for get conditional user properties", (Object) null);
            return new ArrayList<>();
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzbb zzf() {
        return super.zzf();
    }

    public final List<zzok> zza(boolean z2) {
        zzu();
        zzj().zzp().zza("Getting user properties (FE)");
        if (zzl().zzg()) {
            zzj().zzg().zza("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzac.zza()) {
            zzj().zzg().zza("Cannot get all user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzu.zzl().zza(atomicReference, 5000, "get user properties", new zzkh(this, atomicReference, z2));
            List<zzok> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zzj().zzg().zza("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z2));
            return Collections.emptyList();
        }
    }

    public static /* synthetic */ void zzb(zzjk zzjk, int i3) {
        if (zzjk.zzj == null) {
            zzjk.zzj = new zzjy(zzjk, zzjk.zzu);
        }
        zzjk.zzj.zza((long) (i3 * 1000));
    }

    public final /* synthetic */ void zzb(Bundle bundle) {
        if (!bundle.isEmpty()) {
            bundle = zzf(bundle);
        }
        zzk().zzt.zza(bundle);
        zzlp zzo2 = zzo();
        zzo2.zzt();
        zzo2.zzu();
        if (!zzo2.zzap() || zzo2.zzq().zzg() >= 243100) {
            zzo().zzb(bundle);
        }
    }

    public final /* synthetic */ void zzb(String str) {
        if (zzg().zzb(str)) {
            zzg().zzag();
        }
    }

    public final Map<String, Object> zza(String str, String str2, boolean z2) {
        if (zzl().zzg()) {
            zzj().zzg().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        } else if (zzac.zza()) {
            zzj().zzg().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzu.zzl().zza(atomicReference, 5000, "get user properties", new zzkm(this, atomicReference, (String) null, str, str2, z2));
            List<zzok> list = (List) atomicReference.get();
            if (list == null) {
                zzj().zzg().zza("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z2));
                return Collections.emptyMap();
            }
            ArrayMap arrayMap = new ArrayMap(list.size());
            for (zzok zzok : list) {
                Object zza2 = zzok.zza();
                if (zza2 != null) {
                    arrayMap.put(zzok.zza, zza2);
                }
            }
            return arrayMap;
        }
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, zzb().currentTimeMillis());
    }

    @WorkerThread
    public final void zzb(long j2) {
        zza(j2, true);
    }

    private final void zzb(String str, String str2, long j2, Bundle bundle, boolean z2, boolean z3, boolean z4, String str3) {
        zzl().zzb((Runnable) new zzkf(this, str, str2, j2, zzop.zza(bundle), z2, z3, z4, str3));
    }

    public final void zzb(boolean z2) {
        if (zza().getApplicationContext() instanceof Application) {
            Application application = (Application) zza().getApplicationContext();
            if (this.zzb == null) {
                this.zzb = new zzkw(this);
            }
            if (z2) {
                application.unregisterActivityLifecycleCallbacks(this.zzb);
                application.registerActivityLifecycleCallbacks(this.zzb);
                zzj().zzp().zza("Registered activity lifecycle callback");
            }
        }
    }

    public final void zzb(Bundle bundle, long j2) {
        Preconditions.checkNotNull(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            zzj().zzu().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        Preconditions.checkNotNull(bundle2);
        Class<String> cls = String.class;
        zziz.zza(bundle2, "app_id", cls, null);
        zziz.zza(bundle2, "origin", cls, null);
        zziz.zza(bundle2, "name", cls, null);
        zziz.zza(bundle2, "value", Object.class, null);
        zziz.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, cls, null);
        Class<Long> cls2 = Long.class;
        zziz.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, cls2, 0L);
        zziz.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, cls, null);
        Class<Bundle> cls3 = Bundle.class;
        zziz.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, cls3, null);
        zziz.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, cls, null);
        zziz.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, cls3, null);
        zziz.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, cls2, 0L);
        zziz.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, cls, null);
        zziz.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, cls3, null);
        Preconditions.checkNotEmpty(bundle2.getString("name"));
        Preconditions.checkNotEmpty(bundle2.getString("origin"));
        Preconditions.checkNotNull(bundle2.get("value"));
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j2);
        String string = bundle2.getString("name");
        Object obj = bundle2.get("value");
        if (zzq().zzb(string) != 0) {
            zzj().zzg().zza("Invalid conditional user property name", zzi().zzc(string));
        } else if (zzq().zza(string, obj) != 0) {
            zzj().zzg().zza("Invalid conditional user property value", zzi().zzc(string), obj);
        } else {
            Object zzc2 = zzq().zzc(string, obj);
            if (zzc2 == null) {
                zzj().zzg().zza("Unable to normalize conditional user property value", zzi().zzc(string), obj);
                return;
            }
            zziz.zza(bundle2, zzc2);
            long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            if (TextUtils.isEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME)) || (j3 <= 15552000000L && j3 >= 1)) {
                long j4 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
                if (j4 > 15552000000L || j4 < 1) {
                    zzj().zzg().zza("Invalid conditional user property time to live", zzi().zzc(string), Long.valueOf(j4));
                } else {
                    zzl().zzb((Runnable) new zzkl(this, bundle2));
                }
            } else {
                zzj().zzg().zza("Invalid conditional user property timeout", zzi().zzc(string), Long.valueOf(j3));
            }
        }
    }

    public static /* synthetic */ void zza(zzjk zzjk, Bundle bundle) {
        Bundle bundle2 = bundle;
        zzjk.zzt();
        zzjk.zzu();
        Preconditions.checkNotNull(bundle);
        String checkNotEmpty = Preconditions.checkNotEmpty(bundle2.getString("name"));
        if (!zzjk.zzu.zzac()) {
            zzjk.zzj().zzp().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        zzok zzok = new zzok(checkNotEmpty, 0, (Object) null, "");
        try {
            zzbh zza2 = zzjk.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), "", bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, true);
            String string = bundle2.getString("app_id");
            long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP);
            boolean z2 = bundle2.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE);
            zzaf zzaf = r4;
            zzaf zzaf2 = new zzaf(string, "", zzok, j2, z2, bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), (zzbh) null, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), (zzbh) null, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zza2);
            zzjk.zzo().zza(zzaf);
        } catch (IllegalArgumentException unused) {
        }
    }

    public static /* synthetic */ void zza(zzjk zzjk, zzjc zzjc, zzjc zzjc2) {
        if (!zzpd.zza() || !zzjk.zze().zza(zzbj.zzdb)) {
            zzjc.zza zza2 = zzjc.zza.ANALYTICS_STORAGE;
            zzjc.zza zza3 = zzjc.zza.AD_STORAGE;
            boolean zza4 = zzjc.zza(zzjc2, zza2, zza3);
            boolean zzb2 = zzjc.zzb(zzjc2, zza2, zza3);
            if (zza4 || zzb2) {
                zzjk.zzg().zzag();
            }
        }
    }

    public static /* synthetic */ void zza(zzjk zzjk, zzjc zzjc, long j2, boolean z2, boolean z3) {
        zzjk.zzt();
        zzjk.zzu();
        zzjc zzn2 = zzjk.zzk().zzn();
        if (j2 <= zzjk.zzo && zzjc.zza(zzn2.zza(), zzjc.zza())) {
            zzjk.zzj().zzn().zza("Dropped out-of-date consent setting, proposed settings", zzjc);
        } else if (zzjk.zzk().zza(zzjc)) {
            zzjk.zzj().zzp().zza("Setting storage consent(FE)", zzjc);
            zzjk.zzo = j2;
            if (zzjk.zzo().zzao()) {
                zzjk.zzo().zzb(z2);
            } else {
                zzjk.zzo().zza(z2);
            }
            if (z3) {
                zzjk.zzo().zza((AtomicReference<String>) new AtomicReference());
            }
        } else {
            zzjk.zzj().zzn().zza("Lower precedence consent source ignored, proposed source", Integer.valueOf(zzjc.zza()));
        }
    }

    public final void zza(String str, String str2, Bundle bundle) {
        long currentTimeMillis = zzb().currentTimeMillis();
        Preconditions.checkNotEmpty(str);
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", str);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str2 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str2);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzl().zzb((Runnable) new zzkk(this, bundle2));
    }

    public final void zzb(zzjj zzjj) {
        zzu();
        Preconditions.checkNotNull(zzjj);
        if (!this.zzd.remove(zzjj)) {
            zzj().zzu().zza("OnEventListener had not been registered");
        }
    }

    public final void zza(zzdl zzdl) throws RemoteException {
        zzl().zzb((Runnable) new zzkp(this, zzdl));
    }

    public final /* synthetic */ void zza(AtomicReference atomicReference) {
        Bundle zza2 = zzk().zzi.zza();
        zzlp zzo2 = zzo();
        if (zza2 == null) {
            zza2 = new Bundle();
        }
        zzo2.zza((AtomicReference<List<zznk>>) atomicReference, zza2);
    }

    public final /* synthetic */ void zza(List list) {
        zzt();
        if (Build.VERSION.SDK_INT >= 30) {
            SparseArray<Long> zzh2 = zzk().zzh();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                zznk zznk = (zznk) it.next();
                if (!zzh2.contains(zznk.zzc) || zzh2.get(zznk.zzc).longValue() < zznk.zzb) {
                    zzal().add(zznk);
                }
            }
            zzas();
        }
    }

    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        if ("IABTCF_TCString".equals(str)) {
            zzj().zzp().zza("IABTCF_TCString change picked up in listener.");
            ((zzax) Preconditions.checkNotNull(this.zzs)).zza(500);
        }
    }

    public final /* synthetic */ void zza(Bundle bundle, long j2) {
        if (TextUtils.isEmpty(zzg().zzae())) {
            zza(bundle, 0, j2);
        } else {
            zzj().zzv().zza("Using developer consent only; google app id found");
        }
    }

    public final /* synthetic */ void zza(Bundle bundle) {
        Bundle bundle2;
        if (bundle.isEmpty()) {
            bundle2 = bundle;
        } else {
            bundle2 = zzf(bundle);
        }
        zzk().zzt.zza(bundle2);
        if (!bundle.isEmpty() || zze().zza(zzbj.zzdj)) {
            zzo().zza(bundle2);
        }
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z2, boolean z3, long j2) {
        String str3 = str == null ? "app" : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        String str4 = str2;
        if (Objects.equals(str2, FirebaseAnalytics.Event.SCREEN_VIEW)) {
            zzn().zza(bundle2, j2);
            return;
        }
        long j3 = j2;
        zzb(str3, str2, j2, bundle2, z3, !z3 || this.zzc == null || zzop.zzg(str2), z2, (String) null);
    }

    public final void zza(String str, String str2, Bundle bundle, String str3) {
        zzs();
        zzb(str, str2, zzb().currentTimeMillis(), bundle, false, true, true, str3);
    }

    public final void zza(String str, String str2, Bundle bundle, long j2) {
        zza(str, str2, bundle, true, false, j2);
    }

    @WorkerThread
    public final void zza(String str, String str2, long j2, Bundle bundle) {
        zzt();
        zza(str, str2, j2, bundle, true, this.zzc == null || zzop.zzg(str2), true, (String) null);
    }

    @WorkerThread
    public final void zza(String str, String str2, long j2, Bundle bundle, boolean z2, boolean z3, boolean z4, String str3) {
        boolean z5;
        ArrayList arrayList;
        String str4;
        long j3;
        int i3;
        Object obj;
        Class<?> cls;
        String str5 = str;
        String str6 = str2;
        long j4 = j2;
        Bundle bundle2 = bundle;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzt();
        zzu();
        if (!this.zzu.zzac()) {
            zzj().zzc().zza("Event not sent since app measurement is disabled");
            return;
        }
        List<String> zzaf = zzg().zzaf();
        if (zzaf == null || zzaf.contains(str6)) {
            if (!this.zze) {
                this.zze = true;
                try {
                    if (!this.zzu.zzag()) {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, zza().getClassLoader());
                    } else {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
                    }
                    try {
                        cls.getDeclaredMethod("initialize", new Class[]{Context.class}).invoke((Object) null, new Object[]{zza()});
                    } catch (Exception e3) {
                        zzj().zzu().zza("Failed to invoke Tag Manager's initialize() method", e3);
                    }
                } catch (ClassNotFoundException unused) {
                    zzj().zzn().zza("Tag Manager is not found and thus will not be used");
                }
            }
            if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(str6) && bundle2.containsKey("gclid")) {
                zza("auto", "_lgclid", (Object) bundle2.getString("gclid"), zzb().currentTimeMillis());
            }
            if (z2 && zzop.zzj(str2)) {
                zzq().zza(bundle2, zzk().zzt.zza());
            }
            int i4 = 0;
            if (!z4 && !"_iap".equals(str6)) {
                zzop zzt2 = this.zzu.zzt();
                int i5 = 2;
                if (zzt2.zzc(NotificationCompat.CATEGORY_EVENT, str6)) {
                    if (!zzt2.zza(NotificationCompat.CATEGORY_EVENT, zzjf.zza, zzjf.zzb, str6)) {
                        i5 = 13;
                    } else if (zzt2.zza(NotificationCompat.CATEGORY_EVENT, 40, str6)) {
                        i5 = 0;
                    }
                }
                if (i5 != 0) {
                    zzj().zzh().zza("Invalid public event name. Event will not be logged (FE)", zzi().zza(str6));
                    this.zzu.zzt();
                    String zza2 = zzop.zza(str6, 40, true);
                    if (str6 != null) {
                        i4 = str2.length();
                    }
                    this.zzu.zzt();
                    zzop.zza(this.zzt, i5, "_ev", zza2, i4);
                    return;
                }
            }
            zzlh zza3 = zzn().zza(false);
            if (zza3 != null && !bundle2.containsKey("_sc")) {
                zza3.zzd = true;
            }
            zzop.zza(zza3, bundle2, z2 && !z4);
            boolean equals = "am".equals(str5);
            boolean zzg2 = zzop.zzg(str2);
            if (z2 && this.zzc != null && !zzg2 && !equals) {
                zzj().zzc().zza("Passing event to registered event handler (FE)", zzi().zza(str6), zzi().zza(bundle2));
                Preconditions.checkNotNull(this.zzc);
                this.zzc.interceptEvent(str, str2, bundle, j2);
            } else if (this.zzu.zzaf()) {
                int zza4 = zzq().zza(str6);
                if (zza4 != 0) {
                    zzj().zzh().zza("Invalid event name. Event will not be logged (FE)", zzi().zza(str6));
                    zzq();
                    String zza5 = zzop.zza(str6, 40, true);
                    if (str6 != null) {
                        i4 = str2.length();
                    }
                    this.zzu.zzt();
                    zzop.zza(this.zzt, str3, zza4, "_ev", zza5, i4);
                    return;
                }
                String str7 = "_o";
                Bundle zza6 = zzq().zza(str3, str2, bundle, (List<String>) CollectionUtils.listOf((T[]) new String[]{"_o", "_sn", "_sc", "_si"}), z4);
                Preconditions.checkNotNull(zza6);
                if (zzn().zza(false) != null && "_ae".equals(str6)) {
                    zznh zznh = zzp().zzb;
                    long elapsedRealtime = zznh.zzb.zzb().elapsedRealtime();
                    long j5 = elapsedRealtime - zznh.zza;
                    zznh.zza = elapsedRealtime;
                    if (j5 > 0) {
                        zzq().zza(zza6, j5);
                    }
                }
                if (!"auto".equals(str5) && "_ssr".equals(str6)) {
                    zzop zzq2 = zzq();
                    String string = zza6.getString("_ffr");
                    if (Strings.isEmptyOrWhitespace(string)) {
                        string = null;
                    } else if (string != null) {
                        string = string.trim();
                    }
                    if (Objects.equals(string, zzq2.zzk().zzq.zza())) {
                        zzq2.zzj().zzc().zza("Not logging duplicate session_start_with_rollout event");
                        return;
                    }
                    zzq2.zzk().zzq.zza(string);
                } else if ("_ae".equals(str6)) {
                    String zza7 = zzq().zzk().zzq.zza();
                    if (!TextUtils.isEmpty(zza7)) {
                        zza6.putString("_ffr", zza7);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(zza6);
                if (zze().zza(zzbj.zzcn)) {
                    z5 = zzp().zzaa();
                } else {
                    z5 = zzk().zzn.zza();
                }
                if (zzk().zzk.zza() <= 0 || !zzk().zza(j4) || !z5) {
                    str4 = "_ae";
                    arrayList = arrayList2;
                    j3 = 0;
                } else {
                    zzj().zzp().zza("Current session is expired, remove the session number, ID, and engagement time");
                    arrayList = arrayList2;
                    j3 = 0;
                    str4 = "_ae";
                    zza("auto", "_sid", (Object) null, zzb().currentTimeMillis());
                    zza("auto", "_sno", (Object) null, zzb().currentTimeMillis());
                    zza("auto", "_se", (Object) null, zzb().currentTimeMillis());
                    zzk().zzl.zza(0);
                }
                if (zza6.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, j3) == 1) {
                    zzj().zzp().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                    i3 = 1;
                    this.zzu.zzs().zza.zza(j4, true);
                } else {
                    i3 = 1;
                }
                ArrayList arrayList3 = new ArrayList(zza6.keySet());
                Collections.sort(arrayList3);
                int size = arrayList3.size();
                int i6 = 0;
                while (i6 < size) {
                    Object obj2 = arrayList3.get(i6);
                    i6 += i3;
                    String str8 = (String) obj2;
                    if (str8 != null) {
                        zzq();
                        Bundle[] zzb2 = zzop.zzb(zza6.get(str8));
                        if (zzb2 != null) {
                            zza6.putParcelableArray(str8, zzb2);
                        }
                    }
                    i3 = 1;
                }
                int i7 = 0;
                while (i7 < arrayList.size()) {
                    ArrayList arrayList4 = arrayList;
                    Bundle bundle3 = (Bundle) arrayList4.get(i7);
                    String str9 = i7 != 0 ? "_ep" : str6;
                    bundle3.putString(str7, str5);
                    if (z3) {
                        obj = null;
                        bundle3 = zzq().zza(bundle3, (String) null);
                    } else {
                        obj = null;
                    }
                    Bundle bundle4 = bundle3;
                    zzbh zzbh = r1;
                    String str10 = str7;
                    Object obj3 = obj;
                    Bundle bundle5 = bundle4;
                    zzbh zzbh2 = new zzbh(str9, new zzbc(bundle4), str, j2);
                    zzo().zza(zzbh, str3);
                    if (!equals) {
                        for (zzjj onEvent : this.zzd) {
                            onEvent.onEvent(str, str2, new Bundle(bundle5), j2);
                            String str11 = str3;
                        }
                    }
                    i7++;
                    arrayList = arrayList4;
                    str7 = str10;
                }
                if (zzn().zza(false) != null && str4.equals(str6)) {
                    zzp().zza(true, true, zzb().elapsedRealtime());
                }
            }
        } else {
            zzj().zzc().zza("Dropping non-safelisted event. event name, origin", str6, str5);
        }
    }

    public final void zza(zzjj zzjj) {
        zzu();
        Preconditions.checkNotNull(zzjj);
        if (!this.zzd.add(zzjj)) {
            zzj().zzu().zza("OnEventListener already registered");
        }
    }

    public final void zza(long j2) {
        zzc((String) null);
        zzl().zzb((Runnable) new zzki(this, j2));
    }

    public final void zza(long j2, boolean z2) {
        zzt();
        zzu();
        zzj().zzc().zza("Resetting analytics data (FE)");
        zznb zzp2 = zzp();
        zzp2.zzt();
        zzp2.zzb.zza();
        zzg().zzag();
        boolean zzac = this.zzu.zzac();
        zzgu zzk2 = zzk();
        zzk2.zzc.zza(j2);
        if (!TextUtils.isEmpty(zzk2.zzk().zzq.zza())) {
            zzk2.zzq.zza((String) null);
        }
        zzk2.zzk.zza(0);
        zzk2.zzl.zza(0);
        if (!zzk2.zze().zzz()) {
            zzk2.zzb(!zzac);
        }
        zzk2.zzr.zza((String) null);
        zzk2.zzs.zza(0);
        zzk2.zzt.zza((Bundle) null);
        if (z2) {
            zzo().zzai();
        }
        zzp().zza.zza();
        this.zzp = !zzac;
    }

    private final void zza(String str, String str2, long j2, Object obj) {
        zzl().zzb((Runnable) new zzke(this, str, str2, obj, j2));
    }

    @VisibleForTesting
    private final void zza(Bundle bundle, int i3, long j2) {
        String str;
        zzu();
        String zza2 = zzjc.zza(bundle);
        if (zza2 != null) {
            zzj().zzv().zza("Ignoring invalid consent setting", zza2);
            zzj().zzv().zza("Valid consent values are 'granted', 'denied'");
        }
        boolean zzg2 = zzl().zzg();
        zzjc zza3 = zzjc.zza(bundle, i3);
        if (zza3.zzk()) {
            zza(zza3, j2, zzg2);
        }
        zzaz zza4 = zzaz.zza(bundle, i3);
        if (zza4.zzg()) {
            zza(zza4, zzg2);
        }
        Boolean zza5 = zzaz.zza(bundle);
        if (zza5 != null) {
            if (i3 == -30) {
                str = "tcf";
            } else {
                str = "app";
            }
            zza(str, FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS, (Object) zza5.toString(), false);
        }
    }

    public final void zza(zzaz zzaz, boolean z2) {
        zzkv zzkv = new zzkv(this, zzaz);
        if (z2) {
            zzt();
            zzkv.run();
            return;
        }
        zzl().zzb((Runnable) zzkv);
    }

    @WorkerThread
    public final void zza(zzjg zzjg) {
        zzjg zzjg2;
        zzt();
        zzu();
        if (!(zzjg == null || zzjg == (zzjg2 = this.zzc))) {
            Preconditions.checkState(zzjg2 == null, "EventInterceptor already set.");
        }
        this.zzc = zzjg;
    }

    public final void zza(Boolean bool) {
        zzu();
        zzl().zzb((Runnable) new zzks(this, bool));
    }

    @WorkerThread
    public final void zza(zzjc zzjc) {
        zzt();
        boolean z2 = (zzjc.zzj() && zzjc.zzi()) || zzo().zzan();
        if (z2 != this.zzu.zzad()) {
            this.zzu.zzb(z2);
            Boolean zzu = zzk().zzu();
            if (!z2 || zzu == null || zzu.booleanValue()) {
                zza(Boolean.valueOf(z2), false);
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(Boolean bool, boolean z2) {
        zzt();
        zzu();
        zzj().zzc().zza("Setting app measurement enabled (FE)", bool);
        zzk().zza(bool);
        if (z2) {
            zzk().zzb(bool);
        }
        if (this.zzu.zzad() || (bool != null && !bool.booleanValue())) {
            zzav();
        }
    }

    @WorkerThread
    public final void zza(Intent intent) {
        if (zzrw.zza() && zze().zza(zzbj.zzbx)) {
            Uri data = intent.getData();
            if (data == null) {
                zzj().zzn().zza("Activity intent has no data. Preview Mode was not enabled.");
                return;
            }
            String queryParameter = data.getQueryParameter("sgtm_debug_enable");
            if (queryParameter == null || !queryParameter.equals("1")) {
                zzj().zzn().zza("Preview Mode was not enabled.");
                zze().zzh((String) null);
                return;
            }
            String queryParameter2 = data.getQueryParameter("sgtm_preview_key");
            if (!TextUtils.isEmpty(queryParameter2)) {
                zzj().zzn().zza("Preview Mode was enabled. Using the sgtmPreviewKey: ", queryParameter2);
                zze().zzh(queryParameter2);
            }
        }
    }

    public final void zza(zzjc zzjc, long j2, boolean z2) {
        zzjc zzjc2;
        boolean z3;
        boolean z4;
        zzjc zzjc3;
        boolean z5;
        zzjb zzjb;
        zzjc zzjc4 = zzjc;
        zzu();
        int zza2 = zzjc.zza();
        if (!zzox.zza() || !zze().zza(zzbj.zzcx)) {
            if (zza2 != -10 && zzjc.zze() == null && zzjc.zzf() == null) {
                zzj().zzv().zza("Discarding empty consent settings");
                return;
            }
        } else if (zza2 != -10 && zzjc.zzc() == (zzjb = zzjb.UNINITIALIZED) && zzjc.zzd() == zzjb) {
            zzj().zzv().zza("Ignoring empty consent settings");
            return;
        }
        synchronized (this.zzg) {
            try {
                zzjc2 = this.zzm;
                z3 = false;
                if (zzjc.zza(zza2, zzjc2.zza())) {
                    boolean zzc2 = zzjc.zzc(this.zzm);
                    if (zzjc.zzj() && !this.zzm.zzj()) {
                        z3 = true;
                    }
                    zzjc zzb2 = zzjc.zzb(this.zzm);
                    this.zzm = zzb2;
                    z4 = z3;
                    z3 = true;
                    boolean z6 = zzc2;
                    zzjc3 = zzb2;
                    z5 = z6;
                } else {
                    zzjc3 = zzjc4;
                    z5 = false;
                    z4 = false;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (!z3) {
            zzj().zzn().zza("Ignoring lower-priority consent settings, proposed settings", zzjc3);
            return;
        }
        long andIncrement = this.zzn.getAndIncrement();
        if (z5) {
            zzc((String) null);
            zzku zzku = new zzku(this, zzjc3, j2, andIncrement, z4, zzjc2);
            if (z2) {
                zzt();
                zzku.run();
                return;
            }
            zzl().zzc((Runnable) zzku);
            return;
        }
        zzkx zzkx = new zzkx(this, zzjc3, andIncrement, z4, zzjc2);
        if (z2) {
            zzt();
            zzkx.run();
        } else if (zza2 == 30 || zza2 == -10) {
            zzl().zzc((Runnable) zzkx);
        } else {
            zzl().zzb((Runnable) zzkx);
        }
    }

    public final void zza(String str, long j2) {
        if (str == null || !TextUtils.isEmpty(str)) {
            zzl().zzb((Runnable) new zzju(this, str));
            zza((String) null, "_id", (Object) str, true, j2);
            return;
        }
        this.zzu.zzj().zzu().zza("User ID must be non-empty or null");
    }

    public final void zza(String str, String str2, Object obj, boolean z2) {
        zza(str, str2, obj, z2, zzb().currentTimeMillis());
    }

    public final void zza(String str, String str2, Object obj, boolean z2, long j2) {
        int i3;
        if (str == null) {
            str = "app";
        }
        String str3 = str;
        int i4 = 0;
        if (z2) {
            i3 = zzq().zzb(str2);
        } else {
            zzop zzq2 = zzq();
            if (zzq2.zzc("user property", str2)) {
                if (!zzq2.zza("user property", zzjh.zza, str2)) {
                    i3 = 15;
                } else if (zzq2.zza("user property", 24, str2)) {
                    i3 = 0;
                }
            }
            i3 = 6;
        }
        if (i3 != 0) {
            zzq();
            String zza2 = zzop.zza(str2, 24, true);
            if (str2 != null) {
                i4 = str2.length();
            }
            this.zzu.zzt();
            zzop.zza(this.zzt, i3, "_ev", zza2, i4);
        } else if (obj != null) {
            int zza3 = zzq().zza(str2, obj);
            if (zza3 != 0) {
                zzq();
                String zza4 = zzop.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i4 = String.valueOf(obj).length();
                }
                this.zzu.zzt();
                zzop.zza(this.zzt, zza3, "_ev", zza4, i4);
                return;
            }
            Object zzc2 = zzq().zzc(str2, obj);
            if (zzc2 != null) {
                zza(str3, str2, j2, zzc2);
            }
        } else {
            zza(str3, str2, j2, (Object) null);
        }
    }

    @WorkerThread
    public final void zza(String str, String str2, Object obj, long j2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzu();
        if (FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS.equals(str2)) {
            if (obj instanceof String) {
                String str3 = (String) obj;
                if (!TextUtils.isEmpty(str3)) {
                    String str4 = "false";
                    Long valueOf = Long.valueOf(str4.equals(str3.toLowerCase(Locale.ENGLISH)) ? 1 : 0);
                    zzha zzha = zzk().zzh;
                    if (valueOf.longValue() == 1) {
                        str4 = "true";
                    }
                    zzha.zza(str4);
                    obj = valueOf;
                    str2 = "_npa";
                    zzj().zzp().zza("Setting user property(FE)", "non_personalized_ads(_npa)", obj);
                }
            }
            if (obj == null) {
                zzk().zzh.zza("unset");
                str2 = "_npa";
            }
            zzj().zzp().zza("Setting user property(FE)", "non_personalized_ads(_npa)", obj);
        }
        String str5 = str2;
        Object obj2 = obj;
        if (!this.zzu.zzac()) {
            zzj().zzp().zza("User property not set since app measurement is disabled");
        } else if (this.zzu.zzaf()) {
            zzo().zza(new zzok(str5, j2, obj2, str));
        }
    }
}
