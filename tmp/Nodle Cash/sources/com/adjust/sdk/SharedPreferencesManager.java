package com.adjust.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import org.json.JSONArray;
import org.json.JSONException;

public class SharedPreferencesManager {
    private static final int INDEX_CLICK_TIME = 1;
    private static final int INDEX_IS_SENDING = 2;
    private static final int INDEX_RAW_REFERRER = 0;
    private static final String PREFS_KEY_DEEPLINK_CLICK_TIME = "deeplink_click_time";
    private static final String PREFS_KEY_DEEPLINK_URL = "deeplink_url";
    private static final String PREFS_KEY_DISABLE_THIRD_PARTY_SHARING = "disable_third_party_sharing";
    private static final String PREFS_KEY_GDPR_FORGET_ME = "gdpr_forget_me";
    private static final String PREFS_KEY_INSTALL_TRACKED = "install_tracked";
    private static final String PREFS_KEY_PREINSTALL_PAYLOAD_READ_STATUS = "preinstall_payload_read_status";
    private static final String PREFS_KEY_PREINSTALL_SYSTEM_INSTALLER_REFERRER = "preinstall_system_installer_referrer";
    private static final String PREFS_KEY_PUSH_TOKEN = "push_token";
    private static final String PREFS_KEY_RAW_REFERRERS = "raw_referrers";
    private static final String PREFS_NAME = "adjust_preferences";
    private static final int REFERRERS_COUNT = 10;
    private static SharedPreferencesManager defaultInstance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;

    private SharedPreferencesManager(Context context) {
        try {
            SharedPreferences sharedPreferences2 = context.getSharedPreferences(PREFS_NAME, 0);
            sharedPreferences = sharedPreferences2;
            sharedPreferencesEditor = sharedPreferences2.edit();
        } catch (Exception e3) {
            AdjustFactory.getLogger().error("Cannot access to SharedPreferences", e3.getMessage());
            sharedPreferences = null;
            sharedPreferencesEditor = null;
        }
    }

    private synchronized boolean getBoolean(String str, boolean z2) {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            return z2;
        }
        try {
            return sharedPreferences2.getBoolean(str, z2);
        } catch (ClassCastException unused) {
            return z2;
        }
    }

    public static synchronized SharedPreferencesManager getDefaultInstance(Context context) {
        SharedPreferencesManager sharedPreferencesManager;
        synchronized (SharedPreferencesManager.class) {
            try {
                if (defaultInstance == null) {
                    defaultInstance = new SharedPreferencesManager(context);
                }
                sharedPreferencesManager = defaultInstance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return sharedPreferencesManager;
    }

    private synchronized long getLong(String str, long j2) {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            return j2;
        }
        try {
            return sharedPreferences2.getLong(str, j2);
        } catch (ClassCastException unused) {
            return j2;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockFinish
        jadx.core.utils.exceptions.JadxRuntimeException: Dominance frontier not set for block: B:15:0x002d
        	at jadx.core.dex.nodes.BlockNode.lock(BlockNode.java:75)
        	at jadx.core.utils.ImmutableList.forEach(ImmutableList.java:108)
        	at jadx.core.dex.nodes.MethodNode.finishBasicBlocks(MethodNode.java:472)
        	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:27)
        */
    private synchronized int getRawReferrerIndex(java.lang.String r8, long r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            org.json.JSONArray r0 = r7.getRawReferrerArray()     // Catch:{ JSONException -> 0x0034 }
            r1 = 0
            r2 = r1
        L_0x0007:
            int r3 = r0.length()     // Catch:{ JSONException -> 0x0034 }
            if (r2 >= r3) goto L_0x0034
            org.json.JSONArray r3 = r0.getJSONArray(r2)     // Catch:{ JSONException -> 0x0034 }
            r4 = 0
            java.lang.String r4 = r3.optString(r1, r4)     // Catch:{ JSONException -> 0x0034 }
            if (r4 == 0) goto L_0x002f
            boolean r4 = r4.equals(r8)     // Catch:{ JSONException -> 0x0034 }
            if (r4 != 0) goto L_0x001f
            goto L_0x002f
        L_0x001f:
            r4 = -1
            r6 = 1
            long r3 = r3.optLong(r6, r4)     // Catch:{ JSONException -> 0x0034 }
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 == 0) goto L_0x002b
            goto L_0x002f
        L_0x002b:
            monitor-exit(r7)
            return r2
        L_0x002d:
            r8 = move-exception
            goto L_0x0032
        L_0x002f:
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0032:
            monitor-exit(r7)     // Catch:{ all -> 0x002d }
            throw r8
        L_0x0034:
            monitor-exit(r7)
            r7 = -1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.SharedPreferencesManager.getRawReferrerIndex(java.lang.String, long):int");
    }

    private synchronized String getString(String str) {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            return null;
        }
        try {
            return sharedPreferences2.getString(str, (String) null);
        } catch (ClassCastException unused) {
            return null;
        } catch (Throwable unused2) {
            if (str.equals(PREFS_KEY_RAW_REFERRERS)) {
                remove(PREFS_KEY_RAW_REFERRERS);
            }
            return null;
        }
    }

    private synchronized void remove(String str) {
        SharedPreferences.Editor editor = sharedPreferencesEditor;
        if (editor != null) {
            editor.remove(str).apply();
        }
    }

    private synchronized void saveBoolean(String str, boolean z2) {
        SharedPreferences.Editor editor = sharedPreferencesEditor;
        if (editor != null) {
            editor.putBoolean(str, z2).apply();
        }
    }

    private synchronized void saveInteger(String str, int i3) {
        SharedPreferences.Editor editor = sharedPreferencesEditor;
        if (editor != null) {
            editor.putInt(str, i3).apply();
        }
    }

    private synchronized void saveLong(String str, long j2) {
        SharedPreferences.Editor editor = sharedPreferencesEditor;
        if (editor != null) {
            editor.putLong(str, j2).apply();
        }
    }

    private synchronized void saveString(String str, String str2) {
        SharedPreferences.Editor editor = sharedPreferencesEditor;
        if (editor != null) {
            editor.putString(str, str2).apply();
        }
    }

    public synchronized void clear() {
        SharedPreferences.Editor editor = sharedPreferencesEditor;
        if (editor != null) {
            editor.clear().apply();
        }
    }

    public synchronized long getDeeplinkClickTime() {
        return getLong(PREFS_KEY_DEEPLINK_CLICK_TIME, -1);
    }

    public synchronized String getDeeplinkUrl() {
        return getString(PREFS_KEY_DEEPLINK_URL);
    }

    public synchronized boolean getDisableThirdPartySharing() {
        return getBoolean(PREFS_KEY_DISABLE_THIRD_PARTY_SHARING, false);
    }

    public synchronized boolean getGdprForgetMe() {
        return getBoolean(PREFS_KEY_GDPR_FORGET_ME, false);
    }

    public synchronized boolean getInstallTracked() {
        return getBoolean(PREFS_KEY_INSTALL_TRACKED, false);
    }

    public synchronized long getPreinstallPayloadReadStatus() {
        return getLong(PREFS_KEY_PREINSTALL_PAYLOAD_READ_STATUS, 0);
    }

    public synchronized String getPreinstallReferrer() {
        return getString(PREFS_KEY_PREINSTALL_SYSTEM_INSTALLER_REFERRER);
    }

    public synchronized String getPushToken() {
        return getString(PREFS_KEY_PUSH_TOKEN);
    }

    public synchronized JSONArray getRawReferrer(String str, long j2) {
        int rawReferrerIndex = getRawReferrerIndex(str, j2);
        if (rawReferrerIndex >= 0) {
            try {
                return getRawReferrerArray().getJSONArray(rawReferrerIndex);
            } catch (JSONException unused) {
                return null;
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:1|2|3|(3:5|6|(6:8|(1:10)|26|11|12|13)(4:14|15|16|17))|18|19|20|21) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0034 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized org.json.JSONArray getRawReferrerArray() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = "raw_referrers"
            java.lang.String r0 = r5.getString(r0)     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0034
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0034 }
            r1.<init>(r0)     // Catch:{ JSONException -> 0x0034 }
            int r2 = r1.length()     // Catch:{ JSONException -> 0x0034 }
            r3 = 10
            if (r2 <= r3) goto L_0x002d
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0034 }
            r0.<init>()     // Catch:{ JSONException -> 0x0034 }
            r2 = 0
        L_0x001c:
            if (r2 >= r3) goto L_0x0028
            java.lang.Object r4 = r1.get(r2)     // Catch:{ JSONException -> 0x0034 }
            r0.put(r4)     // Catch:{ JSONException -> 0x0034 }
            int r2 = r2 + 1
            goto L_0x001c
        L_0x0028:
            r5.saveRawReferrerArray(r0)     // Catch:{ JSONException -> 0x0034 }
            monitor-exit(r5)
            return r0
        L_0x002d:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0034 }
            r1.<init>(r0)     // Catch:{ JSONException -> 0x0034 }
            monitor-exit(r5)
            return r1
        L_0x0034:
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ all -> 0x003b }
            r0.<init>()     // Catch:{ all -> 0x003b }
            monitor-exit(r5)
            return r0
        L_0x003b:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x003b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.SharedPreferencesManager.getRawReferrerArray():org.json.JSONArray");
    }

    public synchronized void removeDeeplink() {
        remove(PREFS_KEY_DEEPLINK_URL);
        remove(PREFS_KEY_DEEPLINK_CLICK_TIME);
    }

    public synchronized void removeDisableThirdPartySharing() {
        remove(PREFS_KEY_DISABLE_THIRD_PARTY_SHARING);
    }

    public synchronized void removeGdprForgetMe() {
        remove(PREFS_KEY_GDPR_FORGET_ME);
    }

    public synchronized void removePreinstallReferrer() {
        remove(PREFS_KEY_PREINSTALL_SYSTEM_INSTALLER_REFERRER);
    }

    public synchronized void removePushToken() {
        remove(PREFS_KEY_PUSH_TOKEN);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0040, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeRawReferrer(java.lang.String r3, long r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 == 0) goto L_0x003f
            int r0 = r3.length()     // Catch:{ all -> 0x002d }
            if (r0 != 0) goto L_0x000a
            goto L_0x003f
        L_0x000a:
            int r3 = r2.getRawReferrerIndex(r3, r4)     // Catch:{ all -> 0x002d }
            if (r3 >= 0) goto L_0x0012
            monitor-exit(r2)
            return
        L_0x0012:
            org.json.JSONArray r4 = r2.getRawReferrerArray()     // Catch:{ all -> 0x002d }
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ all -> 0x002d }
            r5.<init>()     // Catch:{ all -> 0x002d }
            r0 = 0
        L_0x001c:
            int r1 = r4.length()     // Catch:{ all -> 0x002d }
            if (r0 >= r1) goto L_0x0032
            if (r0 != r3) goto L_0x0025
            goto L_0x002f
        L_0x0025:
            org.json.JSONArray r1 = r4.getJSONArray(r0)     // Catch:{ JSONException -> 0x002f }
            r5.put(r1)     // Catch:{ JSONException -> 0x002f }
            goto L_0x002f
        L_0x002d:
            r3 = move-exception
            goto L_0x003d
        L_0x002f:
            int r0 = r0 + 1
            goto L_0x001c
        L_0x0032:
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x002d }
            java.lang.String r4 = "raw_referrers"
            r2.saveString(r4, r3)     // Catch:{ all -> 0x002d }
            monitor-exit(r2)
            return
        L_0x003d:
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            throw r3
        L_0x003f:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.SharedPreferencesManager.removeRawReferrer(java.lang.String, long):void");
    }

    public synchronized void saveDeeplink(Uri uri, long j2) {
        if (uri != null) {
            saveString(PREFS_KEY_DEEPLINK_URL, uri.toString());
            saveLong(PREFS_KEY_DEEPLINK_CLICK_TIME, j2);
        }
    }

    public synchronized void savePreinstallReferrer(String str) {
        saveString(PREFS_KEY_PREINSTALL_SYSTEM_INSTALLER_REFERRER, str);
    }

    public synchronized void savePushToken(String str) {
        saveString(PREFS_KEY_PUSH_TOKEN, str);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockFinish
        jadx.core.utils.exceptions.JadxRuntimeException: Dominance frontier not set for block: B:14:0x002f
        	at jadx.core.dex.nodes.BlockNode.lock(BlockNode.java:75)
        	at jadx.core.utils.ImmutableList.forEach(ImmutableList.java:108)
        	at jadx.core.dex.nodes.MethodNode.finishBasicBlocks(MethodNode.java:472)
        	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:27)
        */
    public synchronized void saveRawReferrer(java.lang.String r4, long r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            org.json.JSONArray r0 = r3.getRawReferrer(r4, r5)     // Catch:{ JSONException -> 0x0032 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r3)
            return
        L_0x0009:
            org.json.JSONArray r0 = r3.getRawReferrerArray()     // Catch:{ JSONException -> 0x0032 }
            int r1 = r0.length()     // Catch:{ JSONException -> 0x0032 }
            r2 = 10
            if (r1 != r2) goto L_0x0017
            monitor-exit(r3)
            return
        L_0x0017:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0032 }
            r1.<init>()     // Catch:{ JSONException -> 0x0032 }
            r2 = 0
            r1.put(r2, r4)     // Catch:{ JSONException -> 0x0032 }
            r4 = 1
            r1.put(r4, r5)     // Catch:{ JSONException -> 0x0032 }
            r4 = 2
            r1.put(r4, r2)     // Catch:{ JSONException -> 0x0032 }
            r0.put(r1)     // Catch:{ JSONException -> 0x0032 }
            r3.saveRawReferrerArray(r0)     // Catch:{ JSONException -> 0x0032 }
            goto L_0x0032
        L_0x002f:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002f }
            throw r4
        L_0x0032:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.SharedPreferencesManager.saveRawReferrer(java.lang.String, long):void");
    }

    public synchronized void saveRawReferrerArray(JSONArray jSONArray) {
        try {
            saveString(PREFS_KEY_RAW_REFERRERS, jSONArray.toString());
        } catch (Throwable unused) {
            remove(PREFS_KEY_RAW_REFERRERS);
        }
    }

    public synchronized void setDisableThirdPartySharing() {
        saveBoolean(PREFS_KEY_DISABLE_THIRD_PARTY_SHARING, true);
    }

    public synchronized void setGdprForgetMe() {
        saveBoolean(PREFS_KEY_GDPR_FORGET_ME, true);
    }

    public synchronized void setInstallTracked() {
        saveBoolean(PREFS_KEY_INSTALL_TRACKED, true);
    }

    public synchronized void setPreinstallPayloadReadStatus(long j2) {
        saveLong(PREFS_KEY_PREINSTALL_PAYLOAD_READ_STATUS, j2);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockFinish
        jadx.core.utils.exceptions.JadxRuntimeException: Dominance frontier not set for block: B:8:0x0020
        	at jadx.core.dex.nodes.BlockNode.lock(BlockNode.java:75)
        	at jadx.core.utils.ImmutableList.forEach(ImmutableList.java:108)
        	at jadx.core.dex.nodes.MethodNode.finishBasicBlocks(MethodNode.java:472)
        	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:27)
        */
    public synchronized void setSendingReferrersAsNotSent() {
        /*
            r8 = this;
            monitor-enter(r8)
            org.json.JSONArray r0 = r8.getRawReferrerArray()     // Catch:{ JSONException -> 0x002d }
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x0008:
            int r4 = r0.length()     // Catch:{ JSONException -> 0x002d }
            if (r2 >= r4) goto L_0x0025
            org.json.JSONArray r4 = r0.getJSONArray(r2)     // Catch:{ JSONException -> 0x002d }
            r5 = -1
            r6 = 2
            int r5 = r4.optInt(r6, r5)     // Catch:{ JSONException -> 0x002d }
            r7 = 1
            if (r5 != r7) goto L_0x0022
            r4.put(r6, r1)     // Catch:{ JSONException -> 0x002d }
            r3 = r7
            goto L_0x0022
        L_0x0020:
            r0 = move-exception
            goto L_0x002b
        L_0x0022:
            int r2 = r2 + 1
            goto L_0x0008
        L_0x0025:
            if (r3 == 0) goto L_0x002d
            r8.saveRawReferrerArray(r0)     // Catch:{ JSONException -> 0x002d }
            goto L_0x002d
        L_0x002b:
            monitor-exit(r8)     // Catch:{ all -> 0x0020 }
            throw r0
        L_0x002d:
            monitor-exit(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.SharedPreferencesManager.setSendingReferrersAsNotSent():void");
    }
}
