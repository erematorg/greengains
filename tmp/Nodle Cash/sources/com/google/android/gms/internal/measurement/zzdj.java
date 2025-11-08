package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzdj extends zzbx implements zzdk {
    public zzdj() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public static zzdk asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        return queryLocalInterface instanceof zzdk ? (zzdk) queryLocalInterface : new zzdm(iBinder);
    }

    /* JADX WARNING: type inference failed for: r4v0 */
    /* JADX WARNING: type inference failed for: r4v6, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v8 */
    /* JADX WARNING: type inference failed for: r4v12, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v17, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v25, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v32, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v37, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v42, types: [com.google.android.gms.internal.measurement.zzdr] */
    /* JADX WARNING: type inference failed for: r4v47, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v52, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v57, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v62, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v68, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v73, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v81, types: [com.google.android.gms.internal.measurement.zzdq] */
    /* JADX WARNING: type inference failed for: r4v86, types: [com.google.android.gms.internal.measurement.zzdq] */
    /* JADX WARNING: type inference failed for: r4v91, types: [com.google.android.gms.internal.measurement.zzdq] */
    /* JADX WARNING: type inference failed for: r4v96, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v101, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v106, types: [com.google.android.gms.internal.measurement.zzdl] */
    /* JADX WARNING: type inference failed for: r4v111 */
    /* JADX WARNING: type inference failed for: r4v112 */
    /* JADX WARNING: type inference failed for: r4v113 */
    /* JADX WARNING: type inference failed for: r4v114 */
    /* JADX WARNING: type inference failed for: r4v115 */
    /* JADX WARNING: type inference failed for: r4v116 */
    /* JADX WARNING: type inference failed for: r4v117 */
    /* JADX WARNING: type inference failed for: r4v118 */
    /* JADX WARNING: type inference failed for: r4v119 */
    /* JADX WARNING: type inference failed for: r4v120 */
    /* JADX WARNING: type inference failed for: r4v121 */
    /* JADX WARNING: type inference failed for: r4v122 */
    /* JADX WARNING: type inference failed for: r4v123 */
    /* JADX WARNING: type inference failed for: r4v124 */
    /* JADX WARNING: type inference failed for: r4v125 */
    /* JADX WARNING: type inference failed for: r4v126 */
    /* JADX WARNING: type inference failed for: r4v127 */
    /* JADX WARNING: type inference failed for: r4v128 */
    /* JADX WARNING: type inference failed for: r4v129 */
    /* JADX WARNING: type inference failed for: r4v130 */
    /* JADX WARNING: type inference failed for: r4v131 */
    /* JADX WARNING: type inference failed for: r4v132 */
    /* JADX WARNING: type inference failed for: r4v133 */
    /* JADX WARNING: type inference failed for: r4v134 */
    /* JADX WARNING: type inference failed for: r4v135 */
    /* JADX WARNING: type inference failed for: r4v136 */
    /* JADX WARNING: type inference failed for: r4v137 */
    /* JADX WARNING: type inference failed for: r4v138 */
    /* JADX WARNING: type inference failed for: r4v139 */
    /* JADX WARNING: type inference failed for: r4v140 */
    /* JADX WARNING: type inference failed for: r4v141 */
    /* JADX WARNING: type inference failed for: r4v142 */
    /* JADX WARNING: type inference failed for: r4v143 */
    /* JADX WARNING: type inference failed for: r4v144 */
    /* JADX WARNING: type inference failed for: r4v145 */
    /* JADX WARNING: type inference failed for: r4v146 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r10, android.os.Parcel r11, android.os.Parcel r12, int r13) throws android.os.RemoteException {
        /*
            r9 = this;
            java.lang.String r2 = "com.google.android.gms.measurement.api.internal.IEventHandlerProxy"
            java.lang.String r3 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            r4 = 0
            switch(r10) {
                case 1: goto L_0x04e4;
                case 2: goto L_0x04ba;
                case 3: goto L_0x0481;
                case 4: goto L_0x045b;
                case 5: goto L_0x042f;
                case 6: goto L_0x040b;
                case 7: goto L_0x03fb;
                case 8: goto L_0x03e7;
                case 9: goto L_0x03cf;
                case 10: goto L_0x03a7;
                case 11: goto L_0x0397;
                case 12: goto L_0x038b;
                case 13: goto L_0x037f;
                case 14: goto L_0x0373;
                case 15: goto L_0x0352;
                case 16: goto L_0x0332;
                case 17: goto L_0x0312;
                case 18: goto L_0x02f0;
                case 19: goto L_0x02d0;
                case 20: goto L_0x02b0;
                case 21: goto L_0x0290;
                case 22: goto L_0x0270;
                case 23: goto L_0x0260;
                case 24: goto L_0x0250;
                case 25: goto L_0x023c;
                case 26: goto L_0x0228;
                case 27: goto L_0x020c;
                case 28: goto L_0x01f8;
                case 29: goto L_0x01e4;
                case 30: goto L_0x01d0;
                case 31: goto L_0x01a4;
                case 32: goto L_0x0178;
                case 33: goto L_0x014a;
                case 34: goto L_0x012a;
                case 35: goto L_0x010a;
                case 36: goto L_0x00ea;
                case 37: goto L_0x00de;
                case 38: goto L_0x00ba;
                case 39: goto L_0x00ae;
                case 40: goto L_0x008e;
                case 41: goto L_0x0008;
                case 42: goto L_0x007e;
                case 43: goto L_0x0072;
                case 44: goto L_0x005e;
                case 45: goto L_0x004a;
                case 46: goto L_0x002a;
                case 47: goto L_0x0008;
                case 48: goto L_0x001a;
                case 49: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            r0 = 0
            return r0
        L_0x000a:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setDefaultEventParametersWithBackfill(r2)
            goto L_0x04fe
        L_0x001a:
            android.os.Parcelable$Creator r2 = android.content.Intent.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r2)
            android.content.Intent r2 = (android.content.Intent) r2
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setSgtmDebugInfo(r2)
            goto L_0x04fe
        L_0x002a:
            android.os.IBinder r2 = r11.readStrongBinder()
            if (r2 != 0) goto L_0x0031
            goto L_0x0042
        L_0x0031:
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x003d
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x0042
        L_0x003d:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r2)
        L_0x0042:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.getSessionId(r4)
            goto L_0x04fe
        L_0x004a:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setConsentThirdParty(r2, r3)
            goto L_0x04fe
        L_0x005e:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setConsent(r2, r3)
            goto L_0x04fe
        L_0x0072:
            long r2 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.clearMeasurementEnabled(r2)
            goto L_0x04fe
        L_0x007e:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setDefaultEventParameters(r2)
            goto L_0x04fe
        L_0x008e:
            android.os.IBinder r2 = r11.readStrongBinder()
            if (r2 != 0) goto L_0x0095
            goto L_0x00a6
        L_0x0095:
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x00a1
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x00a6
        L_0x00a1:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r2)
        L_0x00a6:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.isDataCollectionEnabled(r4)
            goto L_0x04fe
        L_0x00ae:
            boolean r2 = com.google.android.gms.internal.measurement.zzbw.zzc(r11)
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setDataCollectionEnabled(r2)
            goto L_0x04fe
        L_0x00ba:
            android.os.IBinder r2 = r11.readStrongBinder()
            if (r2 != 0) goto L_0x00c1
            goto L_0x00d2
        L_0x00c1:
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x00cd
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x00d2
        L_0x00cd:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r2)
        L_0x00d2:
            int r2 = r11.readInt()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.getTestFlag(r4, r2)
            goto L_0x04fe
        L_0x00de:
            java.util.HashMap r2 = com.google.android.gms.internal.measurement.zzbw.zza(r11)
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.initForTests(r2)
            goto L_0x04fe
        L_0x00ea:
            android.os.IBinder r3 = r11.readStrongBinder()
            if (r3 != 0) goto L_0x00f1
            goto L_0x0102
        L_0x00f1:
            android.os.IInterface r2 = r3.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.measurement.zzdq
            if (r4 == 0) goto L_0x00fd
            r4 = r2
            com.google.android.gms.internal.measurement.zzdq r4 = (com.google.android.gms.internal.measurement.zzdq) r4
            goto L_0x0102
        L_0x00fd:
            com.google.android.gms.internal.measurement.zzds r4 = new com.google.android.gms.internal.measurement.zzds
            r4.<init>(r3)
        L_0x0102:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.unregisterOnMeasurementEventListener(r4)
            goto L_0x04fe
        L_0x010a:
            android.os.IBinder r3 = r11.readStrongBinder()
            if (r3 != 0) goto L_0x0111
            goto L_0x0122
        L_0x0111:
            android.os.IInterface r2 = r3.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.measurement.zzdq
            if (r4 == 0) goto L_0x011d
            r4 = r2
            com.google.android.gms.internal.measurement.zzdq r4 = (com.google.android.gms.internal.measurement.zzdq) r4
            goto L_0x0122
        L_0x011d:
            com.google.android.gms.internal.measurement.zzds r4 = new com.google.android.gms.internal.measurement.zzds
            r4.<init>(r3)
        L_0x0122:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.registerOnMeasurementEventListener(r4)
            goto L_0x04fe
        L_0x012a:
            android.os.IBinder r3 = r11.readStrongBinder()
            if (r3 != 0) goto L_0x0131
            goto L_0x0142
        L_0x0131:
            android.os.IInterface r2 = r3.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.measurement.zzdq
            if (r4 == 0) goto L_0x013d
            r4 = r2
            com.google.android.gms.internal.measurement.zzdq r4 = (com.google.android.gms.internal.measurement.zzdq) r4
            goto L_0x0142
        L_0x013d:
            com.google.android.gms.internal.measurement.zzds r4 = new com.google.android.gms.internal.measurement.zzds
            r4.<init>(r3)
        L_0x0142:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setEventInterceptor(r4)
            goto L_0x04fe
        L_0x014a:
            int r2 = r11.readInt()
            java.lang.String r3 = r11.readString()
            android.os.IBinder r4 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r4)
            android.os.IBinder r5 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r5)
            android.os.IBinder r6 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r6)
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r0 = r9
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r0.logHealthData(r1, r2, r3, r4, r5)
            goto L_0x04fe
        L_0x0178:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            android.os.IBinder r5 = r11.readStrongBinder()
            if (r5 != 0) goto L_0x0187
            goto L_0x0198
        L_0x0187:
            android.os.IInterface r3 = r5.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x0193
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x0198
        L_0x0193:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r5)
        L_0x0198:
            long r5 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.performAction(r2, r4, r5)
            goto L_0x04fe
        L_0x01a4:
            android.os.IBinder r2 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            android.os.IBinder r5 = r11.readStrongBinder()
            if (r5 != 0) goto L_0x01b3
            goto L_0x01c4
        L_0x01b3:
            android.os.IInterface r3 = r5.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x01bf
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x01c4
        L_0x01bf:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r5)
        L_0x01c4:
            long r5 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.onActivitySaveInstanceState(r2, r4, r5)
            goto L_0x04fe
        L_0x01d0:
            android.os.IBinder r2 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.onActivityResumed(r2, r3)
            goto L_0x04fe
        L_0x01e4:
            android.os.IBinder r2 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.onActivityPaused(r2, r3)
            goto L_0x04fe
        L_0x01f8:
            android.os.IBinder r2 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.onActivityDestroyed(r2, r3)
            goto L_0x04fe
        L_0x020c:
            android.os.IBinder r2 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            long r4 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.onActivityCreated(r2, r3, r4)
            goto L_0x04fe
        L_0x0228:
            android.os.IBinder r2 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.onActivityStopped(r2, r3)
            goto L_0x04fe
        L_0x023c:
            android.os.IBinder r2 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.onActivityStarted(r2, r3)
            goto L_0x04fe
        L_0x0250:
            java.lang.String r2 = r11.readString()
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.endAdUnitExposure(r2, r3)
            goto L_0x04fe
        L_0x0260:
            java.lang.String r2 = r11.readString()
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.beginAdUnitExposure(r2, r3)
            goto L_0x04fe
        L_0x0270:
            android.os.IBinder r2 = r11.readStrongBinder()
            if (r2 != 0) goto L_0x0277
            goto L_0x0288
        L_0x0277:
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x0283
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x0288
        L_0x0283:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r2)
        L_0x0288:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.generateEventId(r4)
            goto L_0x04fe
        L_0x0290:
            android.os.IBinder r2 = r11.readStrongBinder()
            if (r2 != 0) goto L_0x0297
            goto L_0x02a8
        L_0x0297:
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x02a3
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x02a8
        L_0x02a3:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r2)
        L_0x02a8:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.getGmpAppId(r4)
            goto L_0x04fe
        L_0x02b0:
            android.os.IBinder r2 = r11.readStrongBinder()
            if (r2 != 0) goto L_0x02b7
            goto L_0x02c8
        L_0x02b7:
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x02c3
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x02c8
        L_0x02c3:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r2)
        L_0x02c8:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.getAppInstanceId(r4)
            goto L_0x04fe
        L_0x02d0:
            android.os.IBinder r2 = r11.readStrongBinder()
            if (r2 != 0) goto L_0x02d7
            goto L_0x02e8
        L_0x02d7:
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x02e3
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x02e8
        L_0x02e3:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r2)
        L_0x02e8:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.getCachedAppInstanceId(r4)
            goto L_0x04fe
        L_0x02f0:
            android.os.IBinder r2 = r11.readStrongBinder()
            if (r2 != 0) goto L_0x02f7
            goto L_0x030a
        L_0x02f7:
            java.lang.String r3 = "com.google.android.gms.measurement.api.internal.IStringProvider"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdr
            if (r4 == 0) goto L_0x0305
            r4 = r3
            com.google.android.gms.internal.measurement.zzdr r4 = (com.google.android.gms.internal.measurement.zzdr) r4
            goto L_0x030a
        L_0x0305:
            com.google.android.gms.internal.measurement.zzdu r4 = new com.google.android.gms.internal.measurement.zzdu
            r4.<init>(r2)
        L_0x030a:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setInstanceIdProvider(r4)
            goto L_0x04fe
        L_0x0312:
            android.os.IBinder r2 = r11.readStrongBinder()
            if (r2 != 0) goto L_0x0319
            goto L_0x032a
        L_0x0319:
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x0325
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x032a
        L_0x0325:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r2)
        L_0x032a:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.getCurrentScreenClass(r4)
            goto L_0x04fe
        L_0x0332:
            android.os.IBinder r2 = r11.readStrongBinder()
            if (r2 != 0) goto L_0x0339
            goto L_0x034a
        L_0x0339:
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x0345
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x034a
        L_0x0345:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r2)
        L_0x034a:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.getCurrentScreenName(r4)
            goto L_0x04fe
        L_0x0352:
            android.os.IBinder r2 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            java.lang.String r3 = r11.readString()
            java.lang.String r4 = r11.readString()
            long r5 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r0 = r9
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r0.setCurrentScreen(r1, r2, r3, r4)
            goto L_0x04fe
        L_0x0373:
            long r2 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setSessionTimeoutDuration(r2)
            goto L_0x04fe
        L_0x037f:
            long r2 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setMinimumSessionDuration(r2)
            goto L_0x04fe
        L_0x038b:
            long r2 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.resetAnalyticsData(r2)
            goto L_0x04fe
        L_0x0397:
            boolean r2 = com.google.android.gms.internal.measurement.zzbw.zzc(r11)
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setMeasurementEnabled(r2, r3)
            goto L_0x04fe
        L_0x03a7:
            java.lang.String r2 = r11.readString()
            java.lang.String r5 = r11.readString()
            android.os.IBinder r6 = r11.readStrongBinder()
            if (r6 != 0) goto L_0x03b6
            goto L_0x03c7
        L_0x03b6:
            android.os.IInterface r3 = r6.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x03c2
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x03c7
        L_0x03c2:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r6)
        L_0x03c7:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.getConditionalUserProperties(r2, r5, r4)
            goto L_0x04fe
        L_0x03cf:
            java.lang.String r2 = r11.readString()
            java.lang.String r3 = r11.readString()
            android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r4)
            android.os.Bundle r4 = (android.os.Bundle) r4
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.clearConditionalUserProperty(r2, r3, r4)
            goto L_0x04fe
        L_0x03e7:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setConditionalUserProperty(r2, r3)
            goto L_0x04fe
        L_0x03fb:
            java.lang.String r2 = r11.readString()
            long r3 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.setUserId(r2, r3)
            goto L_0x04fe
        L_0x040b:
            java.lang.String r2 = r11.readString()
            android.os.IBinder r5 = r11.readStrongBinder()
            if (r5 != 0) goto L_0x0416
            goto L_0x0427
        L_0x0416:
            android.os.IInterface r3 = r5.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x0422
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x0427
        L_0x0422:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r5)
        L_0x0427:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.getMaxUserProperties(r2, r4)
            goto L_0x04fe
        L_0x042f:
            java.lang.String r2 = r11.readString()
            java.lang.String r5 = r11.readString()
            boolean r6 = com.google.android.gms.internal.measurement.zzbw.zzc(r11)
            android.os.IBinder r7 = r11.readStrongBinder()
            if (r7 != 0) goto L_0x0442
            goto L_0x0453
        L_0x0442:
            android.os.IInterface r3 = r7.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x044e
            r4 = r3
            com.google.android.gms.internal.measurement.zzdl r4 = (com.google.android.gms.internal.measurement.zzdl) r4
            goto L_0x0453
        L_0x044e:
            com.google.android.gms.internal.measurement.zzdn r4 = new com.google.android.gms.internal.measurement.zzdn
            r4.<init>(r7)
        L_0x0453:
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.getUserProperties(r2, r5, r6, r4)
            goto L_0x04fe
        L_0x045b:
            java.lang.String r2 = r11.readString()
            java.lang.String r3 = r11.readString()
            android.os.IBinder r4 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r4)
            boolean r5 = com.google.android.gms.internal.measurement.zzbw.zzc(r11)
            long r6 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r0 = r9
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r0.setUserProperty(r1, r2, r3, r4, r5)
            goto L_0x04fe
        L_0x0481:
            java.lang.String r2 = r11.readString()
            java.lang.String r5 = r11.readString()
            android.os.Parcelable$Creator r6 = android.os.Bundle.CREATOR
            android.os.Parcelable r6 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r6)
            android.os.Bundle r6 = (android.os.Bundle) r6
            android.os.IBinder r7 = r11.readStrongBinder()
            if (r7 != 0) goto L_0x0498
            goto L_0x04aa
        L_0x0498:
            android.os.IInterface r3 = r7.queryLocalInterface(r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzdl
            if (r4 == 0) goto L_0x04a4
            com.google.android.gms.internal.measurement.zzdl r3 = (com.google.android.gms.internal.measurement.zzdl) r3
        L_0x04a2:
            r4 = r3
            goto L_0x04aa
        L_0x04a4:
            com.google.android.gms.internal.measurement.zzdn r3 = new com.google.android.gms.internal.measurement.zzdn
            r3.<init>(r7)
            goto L_0x04a2
        L_0x04aa:
            long r7 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r0 = r9
            r1 = r2
            r2 = r5
            r3 = r6
            r5 = r7
            r0.logEventAndBundle(r1, r2, r3, r4, r5)
            goto L_0x04fe
        L_0x04ba:
            java.lang.String r2 = r11.readString()
            java.lang.String r3 = r11.readString()
            android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r4)
            android.os.Bundle r4 = (android.os.Bundle) r4
            boolean r5 = com.google.android.gms.internal.measurement.zzbw.zzc(r11)
            boolean r6 = com.google.android.gms.internal.measurement.zzbw.zzc(r11)
            long r7 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r0 = r9
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r0.logEvent(r1, r2, r3, r4, r5, r6)
            goto L_0x04fe
        L_0x04e4:
            android.os.IBinder r2 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdt> r3 = com.google.android.gms.internal.measurement.zzdt.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.measurement.zzbw.zza((android.os.Parcel) r11, r3)
            com.google.android.gms.internal.measurement.zzdt r3 = (com.google.android.gms.internal.measurement.zzdt) r3
            long r4 = r11.readLong()
            com.google.android.gms.internal.measurement.zzbw.zzb(r11)
            r9.initialize(r2, r3, r4)
        L_0x04fe:
            r12.writeNoException()
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzdj.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
