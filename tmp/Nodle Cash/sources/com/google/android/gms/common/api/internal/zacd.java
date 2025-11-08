package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

final class zacd implements OnCompleteListener {
    private final GoogleApiManager zaa;
    private final int zab;
    private final ApiKey zac;
    private final long zad;
    private final long zae;

    @VisibleForTesting
    public zacd(GoogleApiManager googleApiManager, int i3, ApiKey apiKey, long j2, long j3, @Nullable String str, @Nullable String str2) {
        this.zaa = googleApiManager;
        this.zab = i3;
        this.zac = apiKey;
        this.zad = j2;
        this.zae = j3;
    }

    @Nullable
    public static zacd zaa(GoogleApiManager googleApiManager, int i3, ApiKey apiKey) {
        boolean z2;
        if (!googleApiManager.zaD()) {
            return null;
        }
        RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
        if (config == null) {
            z2 = true;
        } else if (!config.getMethodInvocationTelemetryEnabled()) {
            return null;
        } else {
            z2 = config.getMethodTimingTelemetryEnabled();
            zabq zai = googleApiManager.zai(apiKey);
            if (zai != null) {
                if (!(zai.zaf() instanceof BaseGmsClient)) {
                    return null;
                }
                BaseGmsClient baseGmsClient = (BaseGmsClient) zai.zaf();
                if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                    ConnectionTelemetryConfiguration zab2 = zab(zai, baseGmsClient, i3);
                    if (zab2 == null) {
                        return null;
                    }
                    zai.zaq();
                    z2 = zab2.getMethodTimingTelemetryEnabled();
                }
            }
        }
        return new zacd(googleApiManager, i3, apiKey, z2 ? System.currentTimeMillis() : 0, z2 ? SystemClock.elapsedRealtime() : 0, (String) null, (String) null);
    }

    @Nullable
    private static ConnectionTelemetryConfiguration zab(zabq zabq, BaseGmsClient baseGmsClient, int i3) {
        int[] methodInvocationMethodKeyAllowlist;
        int[] methodInvocationMethodKeyDisallowlist;
        ConnectionTelemetryConfiguration telemetryConfiguration = baseGmsClient.getTelemetryConfiguration();
        if (telemetryConfiguration == null || !telemetryConfiguration.getMethodInvocationTelemetryEnabled() || ((methodInvocationMethodKeyAllowlist = telemetryConfiguration.getMethodInvocationMethodKeyAllowlist()) != null ? !ArrayUtils.contains(methodInvocationMethodKeyAllowlist, i3) : !((methodInvocationMethodKeyDisallowlist = telemetryConfiguration.getMethodInvocationMethodKeyDisallowlist()) == null || !ArrayUtils.contains(methodInvocationMethodKeyDisallowlist, i3))) || zabq.zac() >= telemetryConfiguration.getMaxMethodInvocationsLogged()) {
            return null;
        }
        return telemetryConfiguration;
    }

    @WorkerThread
    public final void onComplete(@NonNull Task task) {
        zabq zai;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        long j2;
        long j3;
        if (this.zaa.zaD()) {
            RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
            if ((config == null || config.getMethodInvocationTelemetryEnabled()) && (zai = this.zaa.zai(this.zac)) != null && (zai.zaf() instanceof BaseGmsClient)) {
                BaseGmsClient baseGmsClient = (BaseGmsClient) zai.zaf();
                boolean z2 = true;
                int i8 = 0;
                boolean z3 = this.zad > 0;
                int gCoreServiceId = baseGmsClient.getGCoreServiceId();
                int i9 = 100;
                if (config != null) {
                    boolean methodTimingTelemetryEnabled = z3 & config.getMethodTimingTelemetryEnabled();
                    int batchPeriodMillis = config.getBatchPeriodMillis();
                    int maxMethodInvocationsInBatch = config.getMaxMethodInvocationsInBatch();
                    i5 = config.getVersion();
                    if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                        ConnectionTelemetryConfiguration zab2 = zab(zai, baseGmsClient, this.zab);
                        if (zab2 != null) {
                            if (!zab2.getMethodTimingTelemetryEnabled() || this.zad <= 0) {
                                z2 = false;
                            }
                            maxMethodInvocationsInBatch = zab2.getMaxMethodInvocationsLogged();
                            methodTimingTelemetryEnabled = z2;
                        } else {
                            return;
                        }
                    }
                    i4 = batchPeriodMillis;
                    i3 = maxMethodInvocationsInBatch;
                } else {
                    i5 = 0;
                    i3 = 100;
                    i4 = 5000;
                }
                GoogleApiManager googleApiManager = this.zaa;
                if (task.isSuccessful()) {
                    i6 = 0;
                } else {
                    if (!task.isCanceled()) {
                        Exception exception = task.getException();
                        if (exception instanceof ApiException) {
                            Status status = ((ApiException) exception).getStatus();
                            i9 = status.getStatusCode();
                            ConnectionResult connectionResult = status.getConnectionResult();
                            if (connectionResult != null) {
                                i6 = connectionResult.getErrorCode();
                                i8 = i9;
                            }
                        } else {
                            i8 = 101;
                            i6 = -1;
                        }
                    }
                    i8 = i9;
                    i6 = -1;
                }
                if (z3) {
                    long j4 = this.zad;
                    long j5 = this.zae;
                    long currentTimeMillis = System.currentTimeMillis();
                    i7 = (int) (SystemClock.elapsedRealtime() - j5);
                    j2 = currentTimeMillis;
                    j3 = j4;
                } else {
                    j3 = 0;
                    j2 = 0;
                    i7 = -1;
                }
                googleApiManager.zaw(new MethodInvocation(this.zab, i8, i6, j3, j2, (String) null, (String) null, gCoreServiceId, i7), i5, (long) i4, i3);
            }
        }
    }
}
