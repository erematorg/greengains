package com.appsamurai.storyly.data.managers.storage;

import android.content.Context;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class c extends e {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(@NotNull Context context, @NotNull String str) {
        super(context, str, 0, 4);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "statusKey");
    }

    @NotNull
    public final List<v> a(@NotNull List<v> list) {
        Intrinsics.checkNotNullParameter(list, "storylyGroupItems");
        for (v vVar : list) {
            for (z zVar : vVar.f4226f) {
                Object a2 = a("ttl_" + vVar.f4221a + '_' + zVar.f4302a);
                if (a2 instanceof Boolean) {
                    zVar.f4316o = ((Boolean) a2).booleanValue();
                } else if (a2 instanceof Long) {
                    zVar.f4316o = true;
                }
            }
        }
        return list;
    }
}
