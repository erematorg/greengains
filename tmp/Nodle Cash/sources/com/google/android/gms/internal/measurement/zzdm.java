package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

public final class zzdm extends zzbu implements zzdk {
    public zzdm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public final void beginAdUnitExposure(String str, long j2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeLong(j2);
        zzb(23, a_);
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (Parcelable) bundle);
        zzb(9, a_);
    }

    public final void clearMeasurementEnabled(long j2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j2);
        zzb(43, a_);
    }

    public final void endAdUnitExposure(String str, long j2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeLong(j2);
        zzb(24, a_);
    }

    public final void generateEventId(zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(22, a_);
    }

    public final void getAppInstanceId(zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(20, a_);
    }

    public final void getCachedAppInstanceId(zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(19, a_);
    }

    public final void getConditionalUserProperties(String str, String str2, zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(10, a_);
    }

    public final void getCurrentScreenClass(zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(17, a_);
    }

    public final void getCurrentScreenName(zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(16, a_);
    }

    public final void getGmpAppId(zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(21, a_);
    }

    public final void getMaxUserProperties(String str, zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(6, a_);
    }

    public final void getSessionId(zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(46, a_);
    }

    public final void getTestFlag(zzdl zzdl, int i3) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdl);
        a_.writeInt(i3);
        zzb(38, a_);
    }

    public final void getUserProperties(String str, String str2, boolean z2, zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, z2);
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(5, a_);
    }

    public final void initForTests(Map map) throws RemoteException {
        Parcel a_ = a_();
        a_.writeMap(map);
        zzb(37, a_);
    }

    public final void initialize(IObjectWrapper iObjectWrapper, zzdt zzdt, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        zzbw.zza(a_, (Parcelable) zzdt);
        a_.writeLong(j2);
        zzb(1, a_);
    }

    public final void isDataCollectionEnabled(zzdl zzdl) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdl);
        zzb(40, a_);
    }

    public final void logEvent(String str, String str2, Bundle bundle, boolean z2, boolean z3, long j2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (Parcelable) bundle);
        zzbw.zza(a_, z2);
        zzbw.zza(a_, z3);
        a_.writeLong(j2);
        zzb(2, a_);
    }

    public final void logEventAndBundle(String str, String str2, Bundle bundle, zzdl zzdl, long j2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (Parcelable) bundle);
        zzbw.zza(a_, (IInterface) zzdl);
        a_.writeLong(j2);
        zzb(3, a_);
    }

    public final void logHealthData(int i3, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel a_ = a_();
        a_.writeInt(i3);
        a_.writeString(str);
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        zzbw.zza(a_, (IInterface) iObjectWrapper2);
        zzbw.zza(a_, (IInterface) iObjectWrapper3);
        zzb(33, a_);
    }

    public final void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        zzbw.zza(a_, (Parcelable) bundle);
        a_.writeLong(j2);
        zzb(27, a_);
    }

    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeLong(j2);
        zzb(28, a_);
    }

    public final void onActivityPaused(IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeLong(j2);
        zzb(29, a_);
    }

    public final void onActivityResumed(IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeLong(j2);
        zzb(30, a_);
    }

    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzdl zzdl, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        zzbw.zza(a_, (IInterface) zzdl);
        a_.writeLong(j2);
        zzb(31, a_);
    }

    public final void onActivityStarted(IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeLong(j2);
        zzb(25, a_);
    }

    public final void onActivityStopped(IObjectWrapper iObjectWrapper, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeLong(j2);
        zzb(26, a_);
    }

    public final void performAction(Bundle bundle, zzdl zzdl, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        zzbw.zza(a_, (IInterface) zzdl);
        a_.writeLong(j2);
        zzb(32, a_);
    }

    public final void registerOnMeasurementEventListener(zzdq zzdq) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdq);
        zzb(35, a_);
    }

    public final void resetAnalyticsData(long j2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j2);
        zzb(12, a_);
    }

    public final void setConditionalUserProperty(Bundle bundle, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        a_.writeLong(j2);
        zzb(8, a_);
    }

    public final void setConsent(Bundle bundle, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        a_.writeLong(j2);
        zzb(44, a_);
    }

    public final void setConsentThirdParty(Bundle bundle, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        a_.writeLong(j2);
        zzb(45, a_);
    }

    public final void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeLong(j2);
        zzb(15, a_);
    }

    public final void setDataCollectionEnabled(boolean z2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, z2);
        zzb(39, a_);
    }

    public final void setDefaultEventParameters(Bundle bundle) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        zzb(42, a_);
    }

    public final void setDefaultEventParametersWithBackfill(Bundle bundle) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        zzb(49, a_);
    }

    public final void setEventInterceptor(zzdq zzdq) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdq);
        zzb(34, a_);
    }

    public final void setInstanceIdProvider(zzdr zzdr) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdr);
        zzb(18, a_);
    }

    public final void setMeasurementEnabled(boolean z2, long j2) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, z2);
        a_.writeLong(j2);
        zzb(11, a_);
    }

    public final void setMinimumSessionDuration(long j2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j2);
        zzb(13, a_);
    }

    public final void setSessionTimeoutDuration(long j2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j2);
        zzb(14, a_);
    }

    public final void setSgtmDebugInfo(Intent intent) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) intent);
        zzb(48, a_);
    }

    public final void setUserId(String str, long j2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeLong(j2);
        zzb(7, a_);
    }

    public final void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z2, long j2) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        zzbw.zza(a_, z2);
        a_.writeLong(j2);
        zzb(4, a_);
    }

    public final void unregisterOnMeasurementEventListener(zzdq zzdq) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdq);
        zzb(36, a_);
    }
}
