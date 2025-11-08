package com.google.android.gms.internal.measurement;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import java.util.Map;

public final class zzhw implements zzhy {
    @Nullable
    public final String zza(ContentResolver contentResolver, String str) throws zzhx {
        Cursor query;
        Uri uri = zzhq.zza;
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
        if (acquireUnstableContentProviderClient != null) {
            try {
                query = acquireUnstableContentProviderClient.query(uri, (String[]) null, (String) null, new String[]{str}, (String) null);
                if (query == null) {
                    throw new zzhx("ContentProvider query returned null cursor");
                } else if (query.moveToFirst()) {
                    String string = query.getString(1);
                    query.close();
                    acquireUnstableContentProviderClient.release();
                    return string;
                } else {
                    query.close();
                    acquireUnstableContentProviderClient.release();
                    return null;
                }
            } catch (RemoteException e3) {
                throw new zzhx("ContentProvider query failed", e3);
            } catch (Throwable th) {
                acquireUnstableContentProviderClient.release();
                throw th;
            }
        } else {
            throw new zzhx("Unable to acquire ContentProviderClient");
        }
        throw th;
    }

    public final <T extends Map<String, String>> T zza(ContentResolver contentResolver, String[] strArr, zzhv<T> zzhv) throws zzhx {
        Cursor query;
        Uri uri = zzhq.zzb;
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
        if (acquireUnstableContentProviderClient != null) {
            try {
                query = acquireUnstableContentProviderClient.query(uri, (String[]) null, (String) null, strArr, (String) null);
                if (query != null) {
                    T zza = zzhv.zza(query.getCount());
                    while (query.moveToNext()) {
                        zza.put(query.getString(0), query.getString(1));
                    }
                    if (query.isAfterLast()) {
                        query.close();
                        acquireUnstableContentProviderClient.release();
                        return zza;
                    }
                    throw new zzhx("Cursor read incomplete (ContentProvider dead?)");
                }
                throw new zzhx("ContentProvider query returned null cursor");
            } catch (RemoteException e3) {
                try {
                    throw new zzhx("ContentProvider query failed", e3);
                } catch (Throwable th) {
                    acquireUnstableContentProviderClient.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            throw new zzhx("Unable to acquire ContentProviderClient");
        }
        throw th;
    }
}
