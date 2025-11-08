package com.adjust.sdk;

import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

public class InstallReferrerMeta {
    private static final String COLUMN_ACTUAL_TIMESTAMP = "actual_timestamp";
    private static final String COLUMN_INSTALL_REFERRER = "install_referrer";
    private static final String COLUMN_IS_CT = "is_ct";
    private static final String FACEBOOK_REFERRER_PROVIDER_AUTHORITY = "com.facebook.katana.provider.InstallReferrerProvider";
    private static final String INSTAGRAM_REFERRER_PROVIDER_AUTHORITY = "com.instagram.contentprovider.InstallReferrerProvider";
    private Context context;
    private String fbAppId;
    private ILogger logger = AdjustFactory.getLogger();
    private final InstallReferrerReadListener referrerCallback;
    private final AtomicBoolean shouldTryToRead;

    public InstallReferrerMeta(Context context2, String str, InstallReferrerReadListener installReferrerReadListener) {
        this.context = context2;
        this.fbAppId = str;
        this.referrerCallback = installReferrerReadListener;
        this.shouldTryToRead = new AtomicBoolean(true);
    }

    private boolean isValidReferrer(String str) {
        return str != null && !str.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00eb, code lost:
        if (r5 == null) goto L_0x00f0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readReferrer() {
        /*
            r13 = this;
            java.lang.String r0 = "actual_timestamp"
            java.lang.String r1 = "is_ct"
            java.lang.String r2 = "install_referrer"
            java.lang.String r3 = "content://com.instagram.contentprovider.InstallReferrerProvider/"
            java.lang.String r4 = "content://com.facebook.katana.provider.InstallReferrerProvider/"
            java.util.concurrent.atomic.AtomicBoolean r5 = r13.shouldTryToRead
            boolean r5 = r5.get()
            r6 = 0
            if (r5 != 0) goto L_0x001d
            com.adjust.sdk.ILogger r13 = r13.logger
            java.lang.Object[] r0 = new java.lang.Object[r6]
            java.lang.String r1 = "Should not retry to read Install referrer Meta"
            r13.debug(r1, r0)
            return
        L_0x001d:
            java.lang.String r5 = r13.fbAppId
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 == 0) goto L_0x002f
            com.adjust.sdk.ILogger r13 = r13.logger
            java.lang.Object[] r0 = new java.lang.Object[r6]
            java.lang.String r1 = "Can't read Install referrer Meta with null or empty FB app ID"
            r13.debug(r1, r0)
            return
        L_0x002f:
            r5 = 0
            android.content.Context r7 = r13.context     // Catch:{ Exception -> 0x004c }
            java.lang.String r8 = "com.facebook.katana.provider.InstallReferrerProvider"
            boolean r7 = com.adjust.sdk.Util.resolveContentProvider(r7, r8)     // Catch:{ Exception -> 0x004c }
            if (r7 == 0) goto L_0x004f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004c }
            r3.<init>(r4)     // Catch:{ Exception -> 0x004c }
            java.lang.String r4 = r13.fbAppId     // Catch:{ Exception -> 0x004c }
            r3.append(r4)     // Catch:{ Exception -> 0x004c }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x004c }
            goto L_0x0067
        L_0x0049:
            r13 = move-exception
            goto L_0x00f6
        L_0x004c:
            r0 = move-exception
            goto L_0x00dc
        L_0x004f:
            android.content.Context r4 = r13.context     // Catch:{ Exception -> 0x004c }
            java.lang.String r7 = "com.instagram.contentprovider.InstallReferrerProvider"
            boolean r4 = com.adjust.sdk.Util.resolveContentProvider(r4, r7)     // Catch:{ Exception -> 0x004c }
            if (r4 == 0) goto L_0x00db
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004c }
            r4.<init>(r3)     // Catch:{ Exception -> 0x004c }
            java.lang.String r3 = r13.fbAppId     // Catch:{ Exception -> 0x004c }
            r4.append(r3)     // Catch:{ Exception -> 0x004c }
            java.lang.String r3 = r4.toString()     // Catch:{ Exception -> 0x004c }
        L_0x0067:
            android.net.Uri r8 = android.net.Uri.parse(r3)     // Catch:{ Exception -> 0x004c }
            android.content.Context r3 = r13.context     // Catch:{ Exception -> 0x004c }
            android.content.ContentResolver r7 = r3.getContentResolver()     // Catch:{ Exception -> 0x004c }
            java.lang.String[] r9 = new java.lang.String[]{r2, r1, r0}     // Catch:{ Exception -> 0x004c }
            r11 = 0
            r12 = 0
            r10 = 0
            android.database.Cursor r5 = r7.query(r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x004c }
            if (r5 == 0) goto L_0x00d6
            boolean r3 = r5.moveToFirst()     // Catch:{ Exception -> 0x004c }
            if (r3 != 0) goto L_0x0085
            goto L_0x00d6
        L_0x0085:
            int r2 = r5.getColumnIndex(r2)     // Catch:{ Exception -> 0x004c }
            int r0 = r5.getColumnIndex(r0)     // Catch:{ Exception -> 0x004c }
            int r1 = r5.getColumnIndex(r1)     // Catch:{ Exception -> 0x004c }
            java.lang.String r2 = r5.getString(r2)     // Catch:{ Exception -> 0x004c }
            long r3 = r5.getLong(r0)     // Catch:{ Exception -> 0x004c }
            int r0 = r5.getInt(r1)     // Catch:{ Exception -> 0x004c }
            r1 = 1
            if (r0 != r1) goto L_0x00a1
            goto L_0x00a2
        L_0x00a1:
            r1 = r6
        L_0x00a2:
            com.adjust.sdk.ILogger r7 = r13.logger     // Catch:{ Exception -> 0x004c }
            java.lang.String r8 = "InstallReferrerMeta reads installReferrer[%s] actualTimestampInSec[%d] isClick[%b]"
            java.lang.Long r9 = java.lang.Long.valueOf(r3)     // Catch:{ Exception -> 0x004c }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x004c }
            java.lang.Object[] r0 = new java.lang.Object[]{r2, r9, r0}     // Catch:{ Exception -> 0x004c }
            r7.debug(r8, r0)     // Catch:{ Exception -> 0x004c }
            boolean r0 = r13.isValidReferrer(r2)     // Catch:{ Exception -> 0x004c }
            if (r0 == 0) goto L_0x00cc
            com.adjust.sdk.ReferrerDetails r0 = new com.adjust.sdk.ReferrerDetails     // Catch:{ Exception -> 0x004c }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x004c }
            r0.<init>((java.lang.String) r2, (long) r3, (java.lang.Boolean) r1)     // Catch:{ Exception -> 0x004c }
            com.adjust.sdk.InstallReferrerReadListener r1 = r13.referrerCallback     // Catch:{ Exception -> 0x004c }
            java.lang.String r2 = "meta"
            r1.onInstallReferrerRead(r0, r2)     // Catch:{ Exception -> 0x004c }
            goto L_0x00ed
        L_0x00cc:
            com.adjust.sdk.ILogger r0 = r13.logger     // Catch:{ Exception -> 0x004c }
            java.lang.String r1 = "InstallReferrerMeta invalid installReferrer"
            java.lang.Object[] r2 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x004c }
            r0.debug(r1, r2)     // Catch:{ Exception -> 0x004c }
            goto L_0x00ed
        L_0x00d6:
            if (r5 == 0) goto L_0x00db
            r5.close()
        L_0x00db:
            return
        L_0x00dc:
            com.adjust.sdk.ILogger r1 = r13.logger     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "InstallReferrerMeta error [%s]"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0049 }
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ all -> 0x0049 }
            r1.debug(r2, r0)     // Catch:{ all -> 0x0049 }
            if (r5 == 0) goto L_0x00f0
        L_0x00ed:
            r5.close()
        L_0x00f0:
            java.util.concurrent.atomic.AtomicBoolean r13 = r13.shouldTryToRead
            r13.set(r6)
            return
        L_0x00f6:
            if (r5 == 0) goto L_0x00fb
            r5.close()
        L_0x00fb:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.InstallReferrerMeta.readReferrer():void");
    }
}
