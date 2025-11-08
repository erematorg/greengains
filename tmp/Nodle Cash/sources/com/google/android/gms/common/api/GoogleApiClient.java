package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zada;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zab;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

@Deprecated
public abstract class GoogleApiClient {
    @NonNull
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    /* access modifiers changed from: private */
    @GuardedBy("allClients")
    public static final Set zaa = Collections.newSetFromMap(new WeakHashMap());

    @Deprecated
    public interface ConnectionCallbacks extends com.google.android.gms.common.api.internal.ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    }

    @Deprecated
    public interface OnConnectionFailedListener extends com.google.android.gms.common.api.internal.OnConnectionFailedListener {
    }

    public static void dumpAll(@NonNull String str, @NonNull FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @NonNull String[] strArr) {
        Set<GoogleApiClient> set = zaa;
        synchronized (set) {
            try {
                String str2 = str + "  ";
                int i3 = 0;
                for (GoogleApiClient dump : set) {
                    printWriter.append(str).append("GoogleApiClient#").println(i3);
                    dump.dump(str2, fileDescriptor, printWriter, strArr);
                    i3++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @NonNull
    @KeepForSdk
    public static Set<GoogleApiClient> getAllClients() {
        Set<GoogleApiClient> set = zaa;
        synchronized (set) {
        }
        return set;
    }

    @ResultIgnorabilityUnspecified
    @NonNull
    public abstract ConnectionResult blockingConnect();

    @ResultIgnorabilityUnspecified
    @NonNull
    public abstract ConnectionResult blockingConnect(long j2, @NonNull TimeUnit timeUnit);

    @NonNull
    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i3) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(@NonNull String str, @NonNull FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @NonNull String[] strArr);

    @ResultIgnorabilityUnspecified
    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t2) {
        throw new UnsupportedOperationException();
    }

    @ResultIgnorabilityUnspecified
    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t2) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @KeepForSdk
    public <C extends Api.Client> C getClient(@NonNull Api.AnyClientKey<C> anyClientKey) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull Api<?> api);

    @NonNull
    @KeepForSdk
    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @KeepForSdk
    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public boolean hasApi(@NonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @KeepForSdk
    public boolean maybeSignIn(@NonNull SignInConnectionListener signInConnectionListener) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @NonNull
    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@NonNull L l2) {
        throw new UnsupportedOperationException();
    }

    public abstract void stopAutoManage(@NonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public void zao(zada zada) {
        throw new UnsupportedOperationException();
    }

    public void zap(zada zada) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static final class Builder {
        @Nullable
        private Account zaa;
        private final Set zab;
        private final Set zac;
        private int zad;
        private View zae;
        private String zaf;
        private String zag;
        private final Map zah;
        private final Context zai;
        private final Map zaj;
        private LifecycleActivity zak;
        private int zal;
        @Nullable
        private OnConnectionFailedListener zam;
        private Looper zan;
        private GoogleApiAvailability zao;
        private Api.AbstractClientBuilder zap;
        private final ArrayList zaq;
        private final ArrayList zar;

        public Builder(@NonNull Context context) {
            this.zab = new HashSet();
            this.zac = new HashSet();
            this.zah = new ArrayMap();
            this.zaj = new ArrayMap();
            this.zal = -1;
            this.zao = GoogleApiAvailability.getInstance();
            this.zap = zad.zac;
            this.zaq = new ArrayList();
            this.zar = new ArrayList();
            this.zai = context;
            this.zan = context.getMainLooper();
            this.zaf = context.getPackageName();
            this.zag = context.getClass().getName();
        }

        private final void zab(Api api, @Nullable Api.ApiOptions apiOptions, Scope... scopeArr) {
            HashSet hashSet = new HashSet(((Api.BaseClientBuilder) Preconditions.checkNotNull(api.zac(), "Base client builder must not be null")).getImpliedScopes(apiOptions));
            for (Scope add : scopeArr) {
                hashSet.add(add);
            }
            this.zah.put(api, new zab(hashSet));
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zaj.put(api, (Object) null);
            List<Scope> impliedScopes = ((Api.BaseClientBuilder) Preconditions.checkNotNull(api.zac(), "Base client builder must not be null")).getImpliedScopes(null);
            this.zac.addAll(impliedScopes);
            this.zab.addAll(impliedScopes);
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O o3, @NonNull Scope... scopeArr) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(o3, "Null options are not permitted for this Api");
            this.zaj.put(api, o3);
            zab(api, o3, scopeArr);
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
            Preconditions.checkNotNull(connectionCallbacks, "Listener must not be null");
            this.zaq.add(connectionCallbacks);
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
            Preconditions.checkNotNull(onConnectionFailedListener, "Listener must not be null");
            this.zar.add(onConnectionFailedListener);
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addScope(@NonNull Scope scope) {
            Preconditions.checkNotNull(scope, "Scope must not be null");
            this.zab.add(scope);
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: com.google.android.gms.common.api.Api} */
        /* JADX WARNING: Multi-variable type inference failed */
        @com.google.errorprone.annotations.ResultIgnorabilityUnspecified
        @androidx.annotation.NonNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.android.gms.common.api.GoogleApiClient build() {
            /*
                r22 = this;
                r0 = r22
                java.util.Map r1 = r0.zaj
                boolean r1 = r1.isEmpty()
                r2 = 1
                r1 = r1 ^ r2
                java.lang.String r3 = "must call addApi() to add at least one API"
                com.google.android.gms.common.internal.Preconditions.checkArgument(r1, r3)
                com.google.android.gms.common.internal.ClientSettings r1 = r22.zaa()
                java.util.Map r3 = r1.zad()
                androidx.collection.ArrayMap r11 = new androidx.collection.ArrayMap
                r11.<init>()
                androidx.collection.ArrayMap r14 = new androidx.collection.ArrayMap
                r14.<init>()
                java.util.ArrayList r15 = new java.util.ArrayList
                r15.<init>()
                java.util.Map r4 = r0.zaj
                java.util.Set r4 = r4.keySet()
                java.util.Iterator r12 = r4.iterator()
                r13 = 0
                r4 = 0
                r16 = r4
                r17 = r13
            L_0x0036:
                boolean r4 = r12.hasNext()
                if (r4 == 0) goto L_0x00b3
                java.lang.Object r4 = r12.next()
                r10 = r4
                com.google.android.gms.common.api.Api r10 = (com.google.android.gms.common.api.Api) r10
                java.util.Map r4 = r0.zaj
                java.lang.Object r18 = r4.get(r10)
                java.lang.Object r4 = r3.get(r10)
                if (r4 == 0) goto L_0x0051
                r4 = r2
                goto L_0x0052
            L_0x0051:
                r4 = r13
            L_0x0052:
                java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
                r11.put(r10, r5)
                com.google.android.gms.common.api.internal.zat r9 = new com.google.android.gms.common.api.internal.zat
                r9.<init>(r10, r4)
                r15.add(r9)
                com.google.android.gms.common.api.Api$AbstractClientBuilder r4 = r10.zaa()
                java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
                r19 = r4
                com.google.android.gms.common.api.Api$AbstractClientBuilder r19 = (com.google.android.gms.common.api.Api.AbstractClientBuilder) r19
                android.content.Context r5 = r0.zai
                android.os.Looper r6 = r0.zan
                r4 = r19
                r7 = r1
                r8 = r18
                r20 = r9
                r21 = r10
                r10 = r20
                com.google.android.gms.common.api.Api$Client r4 = r4.buildClient((android.content.Context) r5, (android.os.Looper) r6, (com.google.android.gms.common.internal.ClientSettings) r7, r8, (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r9, (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r10)
                com.google.android.gms.common.api.Api$AnyClientKey r5 = r21.zab()
                r14.put(r5, r4)
                int r5 = r19.getPriority()
                if (r5 != r2) goto L_0x0094
                if (r18 == 0) goto L_0x0092
                r17 = r2
                goto L_0x0094
            L_0x0092:
                r17 = r13
            L_0x0094:
                boolean r4 = r4.providesSignIn()
                if (r4 == 0) goto L_0x0036
                if (r16 != 0) goto L_0x009f
                r16 = r21
                goto L_0x0036
            L_0x009f:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = r21.zad()
                java.lang.String r2 = r16.zad()
                java.lang.String r3 = " cannot be used with "
                java.lang.String r1 = android.support.v4.media.session.a.n(r1, r3, r2)
                r0.<init>(r1)
                throw r0
            L_0x00b3:
                if (r16 == 0) goto L_0x00f1
                if (r17 != 0) goto L_0x00df
                android.accounts.Account r3 = r0.zaa
                if (r3 != 0) goto L_0x00bc
                r13 = r2
            L_0x00bc:
                java.lang.String r3 = r16.zad()
                java.lang.Object[] r3 = new java.lang.Object[]{r3}
                java.lang.String r4 = "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead"
                com.google.android.gms.common.internal.Preconditions.checkState(r13, r4, r3)
                java.util.Set r3 = r0.zab
                java.util.Set r4 = r0.zac
                boolean r3 = r3.equals(r4)
                java.lang.String r4 = r16.zad()
                java.lang.Object[] r4 = new java.lang.Object[]{r4}
                java.lang.String r5 = "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead."
                com.google.android.gms.common.internal.Preconditions.checkState(r3, r5, r4)
                goto L_0x00f1
            L_0x00df:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = r16.zad()
                java.lang.String r2 = "With using "
                java.lang.String r3 = ", GamesOptions can only be specified within GoogleSignInOptions.Builder"
                java.lang.String r1 = A.a.l(r2, r1, r3)
                r0.<init>(r1)
                throw r0
            L_0x00f1:
                java.util.Collection r3 = r14.values()
                int r16 = com.google.android.gms.common.api.internal.zabe.zad(r3, r2)
                android.content.Context r5 = r0.zai
                com.google.android.gms.common.api.internal.zabe r2 = new com.google.android.gms.common.api.internal.zabe
                java.util.concurrent.locks.ReentrantLock r6 = new java.util.concurrent.locks.ReentrantLock
                r6.<init>()
                android.os.Looper r7 = r0.zan
                com.google.android.gms.common.GoogleApiAvailability r9 = r0.zao
                com.google.android.gms.common.api.Api$AbstractClientBuilder r10 = r0.zap
                java.util.ArrayList r12 = r0.zaq
                java.util.ArrayList r13 = r0.zar
                int r3 = r0.zal
                r4 = r2
                r8 = r1
                r1 = r15
                r15 = r3
                r17 = r1
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
                java.util.Set r1 = com.google.android.gms.common.api.GoogleApiClient.zaa
                monitor-enter(r1)
                java.util.Set r3 = com.google.android.gms.common.api.GoogleApiClient.zaa     // Catch:{ all -> 0x0136 }
                r3.add(r2)     // Catch:{ all -> 0x0136 }
                monitor-exit(r1)     // Catch:{ all -> 0x0136 }
                int r1 = r0.zal
                if (r1 < 0) goto L_0x0135
                com.google.android.gms.common.api.internal.LifecycleActivity r1 = r0.zak
                com.google.android.gms.common.api.internal.zak r1 = com.google.android.gms.common.api.internal.zak.zaa(r1)
                int r3 = r0.zal
                com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener r0 = r0.zam
                r1.zad(r3, r2, r0)
            L_0x0135:
                return r2
            L_0x0136:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0136 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApiClient.Builder.build():com.google.android.gms.common.api.GoogleApiClient");
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, int i3, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) fragmentActivity);
            Preconditions.checkArgument(i3 >= 0, "clientId must be non-negative");
            this.zal = i3;
            this.zam = onConnectionFailedListener;
            this.zak = lifecycleActivity;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder setAccountName(@NonNull String str) {
            this.zaa = str == null ? null : new Account(str, "com.google");
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder setGravityForPopups(int i3) {
            this.zad = i3;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder setHandler(@NonNull Handler handler) {
            Preconditions.checkNotNull(handler, "Handler must not be null");
            this.zan = handler.getLooper();
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder setViewForPopups(@NonNull View view) {
            Preconditions.checkNotNull(view, "View must not be null");
            this.zae = view;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder useDefaultAccount() {
            setAccountName("<<default account>>");
            return this;
        }

        @NonNull
        public final ClientSettings zaa() {
            SignInOptions signInOptions = SignInOptions.zaa;
            Map map = this.zaj;
            Api api = zad.zag;
            if (map.containsKey(api)) {
                signInOptions = (SignInOptions) this.zaj.get(api);
            }
            return new ClientSettings(this.zaa, this.zab, this.zah, this.zad, this.zae, this.zaf, this.zag, signInOptions, false);
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public <T extends Api.ApiOptions.NotRequiredOptions> Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api, @NonNull Scope... scopeArr) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zaj.put(api, (Object) null);
            zab(api, (Api.ApiOptions) null, scopeArr);
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> api, @NonNull O o3) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(o3, "Null options are not permitted for this Api");
            this.zaj.put(api, o3);
            List<Scope> impliedScopes = ((Api.BaseClientBuilder) Preconditions.checkNotNull(api.zac(), "Base client builder must not be null")).getImpliedScopes(o3);
            this.zac.addAll(impliedScopes);
            this.zab.addAll(impliedScopes);
            return this;
        }

        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectionCallbacks, @NonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            Preconditions.checkNotNull(connectionCallbacks, "Must provide a connected listener");
            this.zaq.add(connectionCallbacks);
            Preconditions.checkNotNull(onConnectionFailedListener, "Must provide a connection failed listener");
            this.zar.add(onConnectionFailedListener);
        }
    }
}
