package com.google.android.recaptcha.internal;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzeh implements zzen {
    @NotNull
    private final Context zzb;

    public zzeh(@NotNull Context context) {
        this.zzb = context;
    }

    public final /* synthetic */ Object cs(Object[] objArr) {
        return zzel.zza(this, objArr);
    }

    @Nullable
    public final Object zza(@NotNull Object... objArr) {
        Context context = this.zzb;
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.google.android.gsf.gservices"), (String[]) null, (String) null, new String[]{"android_id"}, (String) null);
        if (query == null || !query.moveToFirst() || query.getColumnCount() < 2) {
            return "";
        }
        String valueOf = String.valueOf(Long.parseLong(query.getString(1)));
        query.close();
        return valueOf;
    }
}
