package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Preconditions;

@KeepName
public class UserRecoverableAuthException extends GoogleAuthException {
    @Nullable
    private final Intent zza;
    @Nullable
    private final PendingIntent zzb;
    private final zzn zzc;

    public UserRecoverableAuthException(@Nullable String str, @Nullable Intent intent) {
        this(str, intent, (PendingIntent) null, zzn.LEGACY);
    }

    @NonNull
    public static UserRecoverableAuthException zza(@Nullable String str, @NonNull Intent intent, @NonNull PendingIntent pendingIntent) {
        Preconditions.checkNotNull(intent);
        Preconditions.checkNotNull(pendingIntent);
        return new UserRecoverableAuthException(str, intent, pendingIntent, zzn.AUTH_INSTANTIATION);
    }

    @Nullable
    public Intent getIntent() {
        Intent intent = this.zza;
        if (intent != null) {
            return new Intent(intent);
        }
        int ordinal = this.zzc.ordinal();
        if (ordinal == 0) {
            Log.w("Auth", "Make sure that an intent was provided to class instantiation.");
            return null;
        } else if (ordinal == 1) {
            Log.e("Auth", "This shouldn't happen. Gms API throwing this exception should support the recovery Intent.");
            return null;
        } else if (ordinal != 2) {
            return null;
        } else {
            Log.e("Auth", "this instantiation of UserRecoverableAuthException doesn't support an Intent.");
            return null;
        }
    }

    private UserRecoverableAuthException(@Nullable String str, @Nullable Intent intent, @Nullable PendingIntent pendingIntent, zzn zzn) {
        super(str);
        this.zzb = pendingIntent;
        this.zza = intent;
        this.zzc = (zzn) Preconditions.checkNotNull(zzn);
    }
}
