package com.google.android.recaptcha.internal;

import android.content.Context;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzeo implements zzen {
    @NotNull
    private final Context zzb;
    @NotNull
    private final Map zzc = MapsKt.mapOf(TuplesKt.to(2, "activity"), TuplesKt.to(3, "phone"), TuplesKt.to(4, "input_method"), TuplesKt.to(5, MimeTypes.BASE_TYPE_AUDIO));

    public zzeo(@NotNull Context context) {
        this.zzb = context;
    }

    public final /* synthetic */ Object cs(Object[] objArr) {
        return zzel.zza(this, objArr);
    }

    @Nullable
    public final Object zza(@NotNull Object... objArr) {
        Integer num = objArr[0];
        if (true != (num instanceof Integer)) {
            num = null;
        }
        Integer num2 = num;
        if (num2 != null) {
            Object obj = this.zzc.get(Integer.valueOf(num2.intValue()));
            if (obj != null) {
                return this.zzb.getSystemService((String) obj);
            }
            throw new zzae(4, 4, (Throwable) null);
        }
        throw new zzae(4, 5, (Throwable) null);
    }
}
