package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;

public interface zzg extends HasApiKey {
    Task zza(zzbw zzbw);

    Task zzb(@NonNull AccountChangeEventsRequest accountChangeEventsRequest);

    Task zzc(@NonNull Account account, @NonNull String str, Bundle bundle);

    Task zzd(@NonNull Account account);

    Task zze(@NonNull String str);
}
