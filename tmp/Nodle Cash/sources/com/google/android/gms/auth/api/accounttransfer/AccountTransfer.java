package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.auth.zzao;

public final class AccountTransfer {
    @NonNull
    public static final String ACTION_ACCOUNT_EXPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_EXPORT_DATA_AVAILABLE";
    @NonNull
    public static final String ACTION_ACCOUNT_IMPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_IMPORT_DATA_AVAILABLE";
    @NonNull
    public static final String ACTION_START_ACCOUNT_EXPORT = "com.google.android.gms.auth.START_ACCOUNT_EXPORT";
    @NonNull
    public static final String KEY_EXTRA_ACCOUNT_TYPE = "key_extra_account_type";
    @NonNull
    public static final Api zza;
    @Deprecated
    public static final zzao zzb = new zzao();
    @Deprecated
    public static final zzao zzc = new zzao();
    private static final Api.ClientKey zzd;
    private static final Api.AbstractClientBuilder zze;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zzd = clientKey;
        zza zza2 = new zza();
        zze = zza2;
        zza = new Api("AccountTransfer.ACCOUNT_TRANSFER_API", zza2, clientKey);
    }

    private AccountTransfer() {
    }

    @NonNull
    public static AccountTransferClient getAccountTransferClient(@NonNull Activity activity) {
        return new AccountTransferClient(activity, (zzr) null);
    }

    @NonNull
    public static AccountTransferClient getAccountTransferClient(@NonNull Context context) {
        return new AccountTransferClient(context, (zzr) null);
    }
}
