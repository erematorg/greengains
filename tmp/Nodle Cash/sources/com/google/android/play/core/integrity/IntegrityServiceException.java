package com.google.android.play.core.integrity;

import com.google.android.gms.common.api.ApiException;

public class IntegrityServiceException extends ApiException {

    /* renamed from: a  reason: collision with root package name */
    private final Throwable f6715a;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IntegrityServiceException(int r5, java.lang.Throwable r6) {
        /*
            r4 = this;
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r1 = com.google.android.play.core.integrity.model.a.a(r5)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Integrity API error ("
            r2.<init>(r3)
            r2.append(r5)
            java.lang.String r3 = "): "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = "."
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>((int) r5, (java.lang.String) r1)
            r4.<init>(r0)
            if (r5 == 0) goto L_0x002e
            r4.f6715a = r6
            return
        L_0x002e:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "ErrorCode should not be 0."
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.integrity.IntegrityServiceException.<init>(int, java.lang.Throwable):void");
    }

    public final synchronized Throwable getCause() {
        return this.f6715a;
    }

    public int getErrorCode() {
        return super.getStatusCode();
    }
}
