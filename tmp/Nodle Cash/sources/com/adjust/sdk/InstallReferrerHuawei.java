package com.adjust.sdk;

import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

public class InstallReferrerHuawei {
    private static final int COLUMN_INDEX_CLICK_TIME = 1;
    private static final int COLUMN_INDEX_INSTALL_TIME = 2;
    private static final int COLUMN_INDEX_REFERRER = 0;
    private static final int COLUMN_INDEX_TRACK_ID = 4;
    private static final String REFERRER_PROVIDER_AUTHORITY = "com.huawei.appmarket.commondata";
    private static final String REFERRER_PROVIDER_URI = "content://com.huawei.appmarket.commondata/item/5";
    private Context context;
    private ILogger logger = AdjustFactory.getLogger();
    private final InstallReferrerReadListener referrerCallback;
    private final AtomicBoolean shouldTryToRead;

    public InstallReferrerHuawei(Context context2, InstallReferrerReadListener installReferrerReadListener) {
        this.context = context2;
        this.referrerCallback = installReferrerReadListener;
        this.shouldTryToRead = new AtomicBoolean(true);
    }

    private boolean isValidReferrerHuaweiAds(String str) {
        return str != null && !str.isEmpty();
    }

    private boolean isValidReferrerHuaweiAppGallery(String str) {
        return str != null && !str.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b9, code lost:
        if (r8 != null) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00cb, code lost:
        if (r8 == null) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00cd, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d0, code lost:
        r15.shouldTryToRead.set(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d5, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readReferrer() {
        /*
            r15 = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = r15.shouldTryToRead
            boolean r0 = r0.get()
            r1 = 0
            if (r0 != 0) goto L_0x0013
            com.adjust.sdk.ILogger r15 = r15.logger
            java.lang.Object[] r0 = new java.lang.Object[r1]
            java.lang.String r1 = "Should not try to read Install referrer Huawei"
            r15.debug(r1, r0)
            return
        L_0x0013:
            android.content.Context r0 = r15.context
            java.lang.String r2 = "com.huawei.appmarket.commondata"
            boolean r0 = com.adjust.sdk.Util.resolveContentProvider(r0, r2)
            if (r0 != 0) goto L_0x001e
            return
        L_0x001e:
            java.lang.String r0 = "content://com.huawei.appmarket.commondata/item/5"
            android.net.Uri r0 = android.net.Uri.parse(r0)
            android.content.Context r2 = r15.context
            android.content.ContentResolver r2 = r2.getContentResolver()
            android.content.Context r3 = r15.context
            java.lang.String r3 = r3.getPackageName()
            java.lang.String[] r6 = new java.lang.String[]{r3}
            r5 = 0
            r7 = 0
            r8 = 0
            r4 = 0
            r3 = r0
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x008e }
            if (r8 == 0) goto L_0x00a4
            boolean r2 = r8.moveToFirst()     // Catch:{ Exception -> 0x008e }
            if (r2 == 0) goto L_0x00a4
            java.lang.String r10 = r8.getString(r1)     // Catch:{ Exception -> 0x008e }
            r0 = 4
            java.lang.String r3 = r8.getString(r0)     // Catch:{ Exception -> 0x008e }
            com.adjust.sdk.ILogger r0 = r15.logger     // Catch:{ Exception -> 0x008e }
            java.lang.String r2 = "InstallReferrerHuawei reads index_referrer[%s] index_track_id[%s]"
            java.lang.Object[] r4 = new java.lang.Object[]{r10, r3}     // Catch:{ Exception -> 0x008e }
            r0.debug(r2, r4)     // Catch:{ Exception -> 0x008e }
            r0 = 1
            java.lang.String r0 = r8.getString(r0)     // Catch:{ Exception -> 0x008e }
            r2 = 2
            java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x008e }
            com.adjust.sdk.ILogger r4 = r15.logger     // Catch:{ Exception -> 0x008e }
            java.lang.String r5 = "InstallReferrerHuawei reads clickTime[%s] installTime[%s]"
            java.lang.Object[] r6 = new java.lang.Object[]{r0, r2}     // Catch:{ Exception -> 0x008e }
            r4.debug(r5, r6)     // Catch:{ Exception -> 0x008e }
            long r4 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x008e }
            long r6 = java.lang.Long.parseLong(r2)     // Catch:{ Exception -> 0x008e }
            boolean r0 = r15.isValidReferrerHuaweiAds(r10)     // Catch:{ Exception -> 0x008e }
            if (r0 == 0) goto L_0x0090
            com.adjust.sdk.ReferrerDetails r0 = new com.adjust.sdk.ReferrerDetails     // Catch:{ Exception -> 0x008e }
            r9 = r0
            r11 = r4
            r13 = r6
            r9.<init>((java.lang.String) r10, (long) r11, (long) r13)     // Catch:{ Exception -> 0x008e }
            com.adjust.sdk.InstallReferrerReadListener r2 = r15.referrerCallback     // Catch:{ Exception -> 0x008e }
            java.lang.String r9 = "huawei_ads"
            r2.onInstallReferrerRead(r0, r9)     // Catch:{ Exception -> 0x008e }
            goto L_0x0090
        L_0x008c:
            r15 = move-exception
            goto L_0x00d6
        L_0x008e:
            r0 = move-exception
            goto L_0x00bc
        L_0x0090:
            boolean r0 = r15.isValidReferrerHuaweiAppGallery(r3)     // Catch:{ Exception -> 0x008e }
            if (r0 == 0) goto L_0x00b9
            com.adjust.sdk.ReferrerDetails r0 = new com.adjust.sdk.ReferrerDetails     // Catch:{ Exception -> 0x008e }
            r2 = r0
            r2.<init>((java.lang.String) r3, (long) r4, (long) r6)     // Catch:{ Exception -> 0x008e }
            com.adjust.sdk.InstallReferrerReadListener r2 = r15.referrerCallback     // Catch:{ Exception -> 0x008e }
            java.lang.String r3 = "huawei_app_gallery"
            r2.onInstallReferrerRead(r0, r3)     // Catch:{ Exception -> 0x008e }
            goto L_0x00b9
        L_0x00a4:
            com.adjust.sdk.ILogger r2 = r15.logger     // Catch:{ Exception -> 0x008e }
            java.lang.String r3 = "InstallReferrerHuawei fail to read referrer for package [%s] and content uri [%s]"
            android.content.Context r4 = r15.context     // Catch:{ Exception -> 0x008e }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ Exception -> 0x008e }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x008e }
            java.lang.Object[] r0 = new java.lang.Object[]{r4, r0}     // Catch:{ Exception -> 0x008e }
            r2.debug(r3, r0)     // Catch:{ Exception -> 0x008e }
        L_0x00b9:
            if (r8 == 0) goto L_0x00d0
            goto L_0x00cd
        L_0x00bc:
            com.adjust.sdk.ILogger r2 = r15.logger     // Catch:{ all -> 0x008c }
            java.lang.String r3 = "InstallReferrerHuawei error [%s]"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x008c }
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ all -> 0x008c }
            r2.debug(r3, r0)     // Catch:{ all -> 0x008c }
            if (r8 == 0) goto L_0x00d0
        L_0x00cd:
            r8.close()
        L_0x00d0:
            java.util.concurrent.atomic.AtomicBoolean r15 = r15.shouldTryToRead
            r15.set(r1)
            return
        L_0x00d6:
            if (r8 == 0) goto L_0x00db
            r8.close()
        L_0x00db:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.InstallReferrerHuawei.readReferrer():void");
    }
}
