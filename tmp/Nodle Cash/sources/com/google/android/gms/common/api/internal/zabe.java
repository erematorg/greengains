package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.internal.zaj;
import com.google.android.gms.common.internal.zak;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zabe extends GoogleApiClient implements zabz {
    @VisibleForTesting
    final Queue zaa = new LinkedList();
    @VisibleForTesting
    @Nullable
    zabx zab;
    final Map zac;
    Set zad;
    final ClientSettings zae;
    final Map zaf;
    final Api.AbstractClientBuilder zag;
    @Nullable
    Set zah;
    final zadc zai;
    private final Lock zaj;
    private final zak zak;
    @Nullable
    private zaca zal = null;
    private final int zam;
    /* access modifiers changed from: private */
    public final Context zan;
    private final Looper zao;
    private volatile boolean zap;
    private long zaq;
    private long zar;
    private final zabc zas;
    private final GoogleApiAvailability zat;
    private final ListenerHolders zau;
    private final ArrayList zav;
    private Integer zaw;
    private final zaj zax;

    public zabe(Context context, Lock lock, Looper looper, ClientSettings clientSettings, GoogleApiAvailability googleApiAvailability, Api.AbstractClientBuilder abstractClientBuilder, Map map, List list, List list2, Map map2, int i3, int i4, ArrayList arrayList) {
        Looper looper2 = looper;
        int i5 = i3;
        this.zaq = true != ClientLibraryUtils.isPackageSide() ? 120000 : 10000;
        this.zar = 5000;
        this.zad = new HashSet();
        this.zau = new ListenerHolders();
        this.zaw = null;
        this.zah = null;
        zaay zaay = new zaay(this);
        this.zax = zaay;
        this.zan = context;
        this.zaj = lock;
        this.zak = new zak(looper, zaay);
        this.zao = looper2;
        this.zas = new zabc(this, looper);
        this.zat = googleApiAvailability;
        this.zam = i5;
        if (i5 >= 0) {
            this.zaw = Integer.valueOf(i4);
        }
        this.zaf = map;
        this.zac = map2;
        this.zav = arrayList;
        this.zai = new zadc();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.zak.zaf((GoogleApiClient.ConnectionCallbacks) it.next());
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            this.zak.zag((GoogleApiClient.OnConnectionFailedListener) it2.next());
        }
        this.zae = clientSettings;
        this.zag = abstractClientBuilder;
    }

    public static int zad(Iterable iterable, boolean z2) {
        Iterator it = iterable.iterator();
        boolean z3 = false;
        boolean z4 = false;
        while (it.hasNext()) {
            Api.Client client = (Api.Client) it.next();
            z3 |= client.requiresSignIn();
            z4 |= client.providesSignIn();
        }
        if (z3) {
            return (!z4 || !z2) ? 1 : 2;
        }
        return 3;
    }

    public static String zag(int i3) {
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? "UNKNOWN" : "SIGN_IN_MODE_NONE" : "SIGN_IN_MODE_OPTIONAL" : "SIGN_IN_MODE_REQUIRED";
    }

    public static /* bridge */ /* synthetic */ void zai(zabe zabe) {
        zabe.zaj.lock();
        try {
            if (zabe.zap) {
                zabe.zan();
            }
        } finally {
            zabe.zaj.unlock();
        }
    }

    public static /* bridge */ /* synthetic */ void zaj(zabe zabe) {
        zabe.zaj.lock();
        try {
            if (zabe.zak()) {
                zabe.zan();
            }
        } finally {
            zabe.zaj.unlock();
        }
    }

    private final void zal(int i3) {
        Integer num = this.zaw;
        if (num == null) {
            this.zaw = Integer.valueOf(i3);
        } else if (num.intValue() != i3) {
            throw new IllegalStateException("Cannot use sign-in mode: " + zag(i3) + ". Mode was already set to " + zag(this.zaw.intValue()));
        }
        if (this.zal == null) {
            boolean z2 = false;
            boolean z3 = false;
            for (Api.Client client : this.zac.values()) {
                z2 |= client.requiresSignIn();
                z3 |= client.providesSignIn();
            }
            int intValue = this.zaw.intValue();
            if (intValue != 1) {
                if (intValue == 2 && z2) {
                    this.zal = zaaa.zag(this.zan, this, this.zaj, this.zao, this.zat, this.zac, this.zae, this.zaf, this.zag, this.zav);
                    return;
                }
            } else if (!z2) {
                throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
            } else if (z3) {
                throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            this.zal = new zabi(this.zan, this, this.zaj, this.zao, this.zat, this.zac, this.zae, this.zaf, this.zag, this.zav, this);
        }
    }

    /* access modifiers changed from: private */
    public final void zam(GoogleApiClient googleApiClient, StatusPendingResult statusPendingResult, boolean z2) {
        Common.zaa.zaa(googleApiClient).setResultCallback(new zabb(this, statusPendingResult, z2, googleApiClient));
    }

    @GuardedBy("lock")
    private final void zan() {
        this.zak.zab();
        ((zaca) Preconditions.checkNotNull(this.zal)).zaq();
    }

    @ResultIgnorabilityUnspecified
    public final ConnectionResult blockingConnect() {
        boolean z2 = true;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zaj.lock();
        try {
            if (this.zam >= 0) {
                if (this.zaw == null) {
                    z2 = false;
                }
                Preconditions.checkState(z2, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.zaw;
                if (num == null) {
                    this.zaw = Integer.valueOf(zad(this.zac.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            zal(((Integer) Preconditions.checkNotNull(this.zaw)).intValue());
            this.zak.zab();
            ConnectionResult zab2 = ((zaca) Preconditions.checkNotNull(this.zal)).zab();
            this.zaj.unlock();
            return zab2;
        } catch (Throwable th) {
            this.zaj.unlock();
            throw th;
        }
    }

    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        Preconditions.checkState(isConnected(), "GoogleApiClient is not connected yet.");
        Integer num = this.zaw;
        boolean z2 = true;
        if (num != null && num.intValue() == 2) {
            z2 = false;
        }
        Preconditions.checkState(z2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        StatusPendingResult statusPendingResult = new StatusPendingResult((GoogleApiClient) this);
        if (this.zac.containsKey(Common.CLIENT_KEY)) {
            zam(this, statusPendingResult, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            zaaz zaaz = new zaaz(this, atomicReference, statusPendingResult);
            zaba zaba = new zaba(this, statusPendingResult);
            GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this.zan);
            builder.addApi(Common.API);
            builder.addConnectionCallbacks(zaaz);
            builder.addOnConnectionFailedListener(zaba);
            builder.setHandler(this.zas);
            GoogleApiClient build = builder.build();
            atomicReference.set(build);
            build.connect();
        }
        return statusPendingResult;
    }

    public final void connect() {
        this.zaj.lock();
        try {
            int i3 = 2;
            boolean z2 = false;
            if (this.zam >= 0) {
                Preconditions.checkState(this.zaw != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.zaw;
                if (num == null) {
                    this.zaw = Integer.valueOf(zad(this.zac.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            int intValue = ((Integer) Preconditions.checkNotNull(this.zaw)).intValue();
            this.zaj.lock();
            if (intValue == 3 || intValue == 1) {
                i3 = intValue;
            } else if (intValue != 2) {
                i3 = intValue;
                Preconditions.checkArgument(z2, "Illegal sign-in mode: " + i3);
                zal(i3);
                zan();
                this.zaj.unlock();
                this.zaj.unlock();
            }
            z2 = true;
            Preconditions.checkArgument(z2, "Illegal sign-in mode: " + i3);
            zal(i3);
            zan();
            this.zaj.unlock();
            this.zaj.unlock();
        } catch (Throwable th) {
            this.zaj.unlock();
            throw th;
        }
    }

    public final void disconnect() {
        this.zaj.lock();
        try {
            this.zai.zab();
            zaca zaca = this.zal;
            if (zaca != null) {
                zaca.zar();
            }
            this.zau.zab();
            for (BaseImplementation.ApiMethodImpl apiMethodImpl : this.zaa) {
                apiMethodImpl.zan((zadb) null);
                apiMethodImpl.cancel();
            }
            this.zaa.clear();
            if (this.zal != null) {
                zak();
                this.zak.zaa();
            }
            this.zaj.unlock();
        } catch (Throwable th) {
            this.zaj.unlock();
            throw th;
        }
    }

    public final void dump(String str, @Nullable FileDescriptor fileDescriptor, PrintWriter printWriter, @Nullable String[] strArr) {
        printWriter.append(str).append("mContext=").println(this.zan);
        printWriter.append(str).append("mResuming=").print(this.zap);
        printWriter.append(" mWorkQueue.size()=").print(this.zaa.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zai.zab.size());
        zaca zaca = this.zal;
        if (zaca != null) {
            zaca.zas(str, fileDescriptor, printWriter, strArr);
        }
    }

    @ResultIgnorabilityUnspecified
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t2) {
        Api<?> api = t2.getApi();
        boolean containsKey = this.zac.containsKey(t2.getClientKey());
        String zad2 = api != null ? api.zad() : "the API";
        Preconditions.checkArgument(containsKey, "GoogleApiClient is not configured to use " + zad2 + " required for this call.");
        this.zaj.lock();
        try {
            zaca zaca = this.zal;
            if (zaca == null) {
                this.zaa.add(t2);
            } else {
                t2 = zaca.zae(t2);
            }
            return t2;
        } finally {
            this.zaj.unlock();
        }
    }

    @ResultIgnorabilityUnspecified
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t2) {
        Map map = this.zac;
        Api<?> api = t2.getApi();
        boolean containsKey = map.containsKey(t2.getClientKey());
        String zad2 = api != null ? api.zad() : "the API";
        Preconditions.checkArgument(containsKey, "GoogleApiClient is not configured to use " + zad2 + " required for this call.");
        this.zaj.lock();
        try {
            zaca zaca = this.zal;
            if (zaca != null) {
                if (this.zap) {
                    this.zaa.add(t2);
                    while (!this.zaa.isEmpty()) {
                        BaseImplementation.ApiMethodImpl apiMethodImpl = (BaseImplementation.ApiMethodImpl) this.zaa.remove();
                        this.zai.zaa(apiMethodImpl);
                        apiMethodImpl.setFailedResult(Status.RESULT_INTERNAL_ERROR);
                    }
                } else {
                    t2 = zaca.zaf(t2);
                }
                this.zaj.unlock();
                return t2;
            }
            throw new IllegalStateException("GoogleApiClient is not connected yet.");
        } catch (Throwable th) {
            this.zaj.unlock();
            throw th;
        }
    }

    @NonNull
    public final <C extends Api.Client> C getClient(@NonNull Api.AnyClientKey<C> anyClientKey) {
        C c3 = (Api.Client) this.zac.get(anyClientKey);
        Preconditions.checkNotNull(c3, "Appropriate Api was not requested.");
        return c3;
    }

    @NonNull
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        ConnectionResult connectionResult;
        this.zaj.lock();
        try {
            if (!isConnected()) {
                if (!this.zap) {
                    throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
                }
            }
            if (this.zac.containsKey(api.zab())) {
                ConnectionResult zad2 = ((zaca) Preconditions.checkNotNull(this.zal)).zad(api);
                if (zad2 == null) {
                    if (this.zap) {
                        connectionResult = ConnectionResult.RESULT_SUCCESS;
                    } else {
                        Log.w("GoogleApiClientImpl", zaf());
                        String zad3 = api.zad();
                        Log.wtf("GoogleApiClientImpl", zad3 + " requested in getConnectionResult is not connected but is not present in the failed  connections map", new Exception());
                        connectionResult = new ConnectionResult(8, (PendingIntent) null);
                    }
                    return connectionResult;
                }
                this.zaj.unlock();
                return zad2;
            }
            String zad4 = api.zad();
            throw new IllegalArgumentException(zad4 + " was never registered with GoogleApiClient");
        } finally {
            this.zaj.unlock();
        }
    }

    public final Context getContext() {
        return this.zan;
    }

    public final Looper getLooper() {
        return this.zao;
    }

    public final boolean hasApi(@NonNull Api<?> api) {
        return this.zac.containsKey(api.zab());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r2 = (com.google.android.gms.common.api.Api.Client) r2.zac.get(r3.zab());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasConnectedApi(@androidx.annotation.NonNull com.google.android.gms.common.api.Api<?> r3) {
        /*
            r2 = this;
            boolean r0 = r2.isConnected()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.util.Map r2 = r2.zac
            com.google.android.gms.common.api.Api$AnyClientKey r3 = r3.zab()
            java.lang.Object r2 = r2.get(r3)
            com.google.android.gms.common.api.Api$Client r2 = (com.google.android.gms.common.api.Api.Client) r2
            if (r2 == 0) goto L_0x001e
            boolean r2 = r2.isConnected()
            if (r2 == 0) goto L_0x001e
            r2 = 1
            return r2
        L_0x001e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zabe.hasConnectedApi(com.google.android.gms.common.api.Api):boolean");
    }

    public final boolean isConnected() {
        zaca zaca = this.zal;
        return zaca != null && zaca.zaw();
    }

    public final boolean isConnecting() {
        zaca zaca = this.zal;
        return zaca != null && zaca.zax();
    }

    public final boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zak.zaj(connectionCallbacks);
    }

    public final boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zak.zak(onConnectionFailedListener);
    }

    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        zaca zaca = this.zal;
        return zaca != null && zaca.zay(signInConnectionListener);
    }

    public final void maybeSignOut() {
        zaca zaca = this.zal;
        if (zaca != null) {
            zaca.zau();
        }
    }

    public final void reconnect() {
        disconnect();
        connect();
    }

    public final void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zak.zaf(connectionCallbacks);
    }

    public final void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zak.zag(onConnectionFailedListener);
    }

    public final <L> ListenerHolder<L> registerListener(@NonNull L l2) {
        this.zaj.lock();
        try {
            return this.zau.zaa(l2, this.zao, "NO_TYPE");
        } finally {
            this.zaj.unlock();
        }
    }

    public final void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) fragmentActivity);
        if (this.zam >= 0) {
            zak.zaa(lifecycleActivity).zae(this.zam);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    public final void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zak.zah(connectionCallbacks);
    }

    public final void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zak.zai(onConnectionFailedListener);
    }

    @GuardedBy("lock")
    public final void zaa(ConnectionResult connectionResult) {
        if (!this.zat.isPlayServicesPossiblyUpdating(this.zan, connectionResult.getErrorCode())) {
            zak();
        }
        if (!this.zap) {
            this.zak.zac(connectionResult);
            this.zak.zaa();
        }
    }

    @GuardedBy("lock")
    public final void zab(@Nullable Bundle bundle) {
        while (!this.zaa.isEmpty()) {
            execute((BaseImplementation.ApiMethodImpl) this.zaa.remove());
        }
        this.zak.zad(bundle);
    }

    @GuardedBy("lock")
    public final void zac(int i3, boolean z2) {
        if (i3 == 1) {
            if (!z2 && !this.zap) {
                this.zap = true;
                if (this.zab == null && !ClientLibraryUtils.isPackageSide()) {
                    try {
                        this.zab = this.zat.zac(this.zan.getApplicationContext(), new zabd(this));
                    } catch (SecurityException unused) {
                    }
                }
                zabc zabc = this.zas;
                zabc.sendMessageDelayed(zabc.obtainMessage(1), this.zaq);
                zabc zabc2 = this.zas;
                zabc2.sendMessageDelayed(zabc2.obtainMessage(2), this.zar);
            }
            i3 = 1;
        }
        for (BasePendingResult forceFailureUnlessReady : (BasePendingResult[]) this.zai.zab.toArray(new BasePendingResult[0])) {
            forceFailureUnlessReady.forceFailureUnlessReady(zadc.zaa);
        }
        this.zak.zae(i3);
        this.zak.zaa();
        if (i3 == 2) {
            zan();
        }
    }

    public final String zaf() {
        StringWriter stringWriter = new StringWriter();
        dump("", (FileDescriptor) null, new PrintWriter(stringWriter), (String[]) null);
        return stringWriter.toString();
    }

    @ResultIgnorabilityUnspecified
    @GuardedBy("lock")
    public final boolean zak() {
        if (!this.zap) {
            return false;
        }
        this.zap = false;
        this.zas.removeMessages(2);
        this.zas.removeMessages(1);
        zabx zabx = this.zab;
        if (zabx != null) {
            zabx.zab();
            this.zab = null;
        }
        return true;
    }

    public final void zao(zada zada) {
        this.zaj.lock();
        try {
            if (this.zah == null) {
                this.zah = new HashSet();
            }
            this.zah.add(zada);
            this.zaj.unlock();
        } catch (Throwable th) {
            this.zaj.unlock();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0041, code lost:
        if (r3 != false) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zap(com.google.android.gms.common.api.internal.zada r3) {
        /*
            r2 = this;
            java.util.concurrent.locks.Lock r0 = r2.zaj
            r0.lock()
            java.util.Set r0 = r2.zah     // Catch:{ all -> 0x0016 }
            java.lang.String r1 = "GoogleApiClientImpl"
            if (r0 != 0) goto L_0x0018
            java.lang.String r3 = "Attempted to remove pending transform when no transforms are registered."
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ all -> 0x0016 }
            r0.<init>()     // Catch:{ all -> 0x0016 }
            android.util.Log.wtf(r1, r3, r0)     // Catch:{ all -> 0x0016 }
            goto L_0x004a
        L_0x0016:
            r3 = move-exception
            goto L_0x0057
        L_0x0018:
            boolean r3 = r0.remove(r3)     // Catch:{ all -> 0x0016 }
            if (r3 != 0) goto L_0x0029
            java.lang.String r3 = "Failed to remove pending transform - this may lead to memory leaks!"
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ all -> 0x0016 }
            r0.<init>()     // Catch:{ all -> 0x0016 }
            android.util.Log.wtf(r1, r3, r0)     // Catch:{ all -> 0x0016 }
            goto L_0x004a
        L_0x0029:
            java.util.concurrent.locks.Lock r3 = r2.zaj     // Catch:{ all -> 0x0016 }
            r3.lock()     // Catch:{ all -> 0x0016 }
            java.util.Set r3 = r2.zah     // Catch:{ all -> 0x0050 }
            if (r3 != 0) goto L_0x0038
            java.util.concurrent.locks.Lock r3 = r2.zaj     // Catch:{ all -> 0x0016 }
            r3.unlock()     // Catch:{ all -> 0x0016 }
            goto L_0x0043
        L_0x0038:
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0050 }
            java.util.concurrent.locks.Lock r0 = r2.zaj     // Catch:{ all -> 0x0016 }
            r0.unlock()     // Catch:{ all -> 0x0016 }
            if (r3 == 0) goto L_0x004a
        L_0x0043:
            com.google.android.gms.common.api.internal.zaca r3 = r2.zal     // Catch:{ all -> 0x0016 }
            if (r3 == 0) goto L_0x004a
            r3.zat()     // Catch:{ all -> 0x0016 }
        L_0x004a:
            java.util.concurrent.locks.Lock r2 = r2.zaj
            r2.unlock()
            return
        L_0x0050:
            r3 = move-exception
            java.util.concurrent.locks.Lock r0 = r2.zaj     // Catch:{ all -> 0x0016 }
            r0.unlock()     // Catch:{ all -> 0x0016 }
            throw r3     // Catch:{ all -> 0x0016 }
        L_0x0057:
            java.util.concurrent.locks.Lock r2 = r2.zaj
            r2.unlock()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zabe.zap(com.google.android.gms.common.api.internal.zada):void");
    }

    public final ConnectionResult blockingConnect(long j2, @NonNull TimeUnit timeUnit) {
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        this.zaj.lock();
        try {
            Integer num = this.zaw;
            if (num == null) {
                this.zaw = Integer.valueOf(zad(this.zac.values(), false));
            } else if (num.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zal(((Integer) Preconditions.checkNotNull(this.zaw)).intValue());
            this.zak.zab();
            ConnectionResult zac2 = ((zaca) Preconditions.checkNotNull(this.zal)).zac(j2, timeUnit);
            this.zaj.unlock();
            return zac2;
        } catch (Throwable th) {
            this.zaj.unlock();
            throw th;
        }
    }

    public final void connect(int i3) {
        this.zaj.lock();
        boolean z2 = true;
        if (!(i3 == 3 || i3 == 1)) {
            if (i3 == 2) {
                i3 = 2;
            } else {
                z2 = false;
            }
        }
        try {
            Preconditions.checkArgument(z2, "Illegal sign-in mode: " + i3);
            zal(i3);
            zan();
        } finally {
            this.zaj.unlock();
        }
    }
}
