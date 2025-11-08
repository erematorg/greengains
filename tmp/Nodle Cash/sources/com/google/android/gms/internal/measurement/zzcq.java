package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzcq implements SharedPreferences {
    /* access modifiers changed from: private */
    public final Map<String, Object> zza = new HashMap();
    /* access modifiers changed from: private */
    public final Set<SharedPreferences.OnSharedPreferenceChangeListener> zzb = new HashSet();

    public final boolean contains(String str) {
        return this.zza.containsKey(str);
    }

    public final SharedPreferences.Editor edit() {
        return new zzcp(this);
    }

    public final Map<String, ?> getAll() {
        return this.zza;
    }

    public final boolean getBoolean(String str, boolean z2) {
        return ((Boolean) zza(str, Boolean.valueOf(z2))).booleanValue();
    }

    public final float getFloat(String str, float f2) {
        return ((Float) zza(str, Float.valueOf(f2))).floatValue();
    }

    public final int getInt(String str, int i3) {
        return ((Integer) zza(str, Integer.valueOf(i3))).intValue();
    }

    public final long getLong(String str, long j2) {
        return ((Long) zza(str, Long.valueOf(j2))).longValue();
    }

    public final String getString(String str, String str2) {
        return (String) zza(str, str2);
    }

    public final Set<String> getStringSet(String str, Set<String> set) {
        return (Set) zza(str, set);
    }

    public final void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.zzb.add(onSharedPreferenceChangeListener);
    }

    public final void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.zzb.remove(onSharedPreferenceChangeListener);
    }

    private final <T> T zza(String str, T t2) {
        T t3 = this.zza.get(str);
        return t3 != null ? t3 : t2;
    }
}
