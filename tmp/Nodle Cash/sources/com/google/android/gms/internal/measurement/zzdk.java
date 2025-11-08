package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

public interface zzdk extends IInterface {
    void beginAdUnitExposure(String str, long j2) throws RemoteException;

    void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException;

    void clearMeasurementEnabled(long j2) throws RemoteException;

    void endAdUnitExposure(String str, long j2) throws RemoteException;

    void generateEventId(zzdl zzdl) throws RemoteException;

    void getAppInstanceId(zzdl zzdl) throws RemoteException;

    void getCachedAppInstanceId(zzdl zzdl) throws RemoteException;

    void getConditionalUserProperties(String str, String str2, zzdl zzdl) throws RemoteException;

    void getCurrentScreenClass(zzdl zzdl) throws RemoteException;

    void getCurrentScreenName(zzdl zzdl) throws RemoteException;

    void getGmpAppId(zzdl zzdl) throws RemoteException;

    void getMaxUserProperties(String str, zzdl zzdl) throws RemoteException;

    void getSessionId(zzdl zzdl) throws RemoteException;

    void getTestFlag(zzdl zzdl, int i3) throws RemoteException;

    void getUserProperties(String str, String str2, boolean z2, zzdl zzdl) throws RemoteException;

    void initForTests(Map map) throws RemoteException;

    void initialize(IObjectWrapper iObjectWrapper, zzdt zzdt, long j2) throws RemoteException;

    void isDataCollectionEnabled(zzdl zzdl) throws RemoteException;

    void logEvent(String str, String str2, Bundle bundle, boolean z2, boolean z3, long j2) throws RemoteException;

    void logEventAndBundle(String str, String str2, Bundle bundle, zzdl zzdl, long j2) throws RemoteException;

    void logHealthData(int i3, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException;

    void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j2) throws RemoteException;

    void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j2) throws RemoteException;

    void onActivityPaused(IObjectWrapper iObjectWrapper, long j2) throws RemoteException;

    void onActivityResumed(IObjectWrapper iObjectWrapper, long j2) throws RemoteException;

    void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzdl zzdl, long j2) throws RemoteException;

    void onActivityStarted(IObjectWrapper iObjectWrapper, long j2) throws RemoteException;

    void onActivityStopped(IObjectWrapper iObjectWrapper, long j2) throws RemoteException;

    void performAction(Bundle bundle, zzdl zzdl, long j2) throws RemoteException;

    void registerOnMeasurementEventListener(zzdq zzdq) throws RemoteException;

    void resetAnalyticsData(long j2) throws RemoteException;

    void setConditionalUserProperty(Bundle bundle, long j2) throws RemoteException;

    void setConsent(Bundle bundle, long j2) throws RemoteException;

    void setConsentThirdParty(Bundle bundle, long j2) throws RemoteException;

    void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j2) throws RemoteException;

    void setDataCollectionEnabled(boolean z2) throws RemoteException;

    void setDefaultEventParameters(Bundle bundle) throws RemoteException;

    void setDefaultEventParametersWithBackfill(Bundle bundle) throws RemoteException;

    void setEventInterceptor(zzdq zzdq) throws RemoteException;

    void setInstanceIdProvider(zzdr zzdr) throws RemoteException;

    void setMeasurementEnabled(boolean z2, long j2) throws RemoteException;

    void setMinimumSessionDuration(long j2) throws RemoteException;

    void setSessionTimeoutDuration(long j2) throws RemoteException;

    void setSgtmDebugInfo(Intent intent) throws RemoteException;

    void setUserId(String str, long j2) throws RemoteException;

    void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z2, long j2) throws RemoteException;

    void unregisterOnMeasurementEventListener(zzdq zzdq) throws RemoteException;
}
