package com.appsamurai.storyly.data.managers.storage;

import android.content.Context;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class d extends e {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(@NotNull Context context, @NotNull String str) {
        super(context, str, 0, 4);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "statusKey");
    }

    @NotNull
    public final List<v> a(@NotNull List<v> list) {
        boolean z2;
        Intrinsics.checkNotNullParameter(list, "storylyGroupItems");
        Map<String, ?> all = a().getAll();
        for (v vVar : list) {
            String str = (String) vVar.f4245y.getValue();
            String stringPlus = str == null ? null : Intrinsics.stringPlus("_", str);
            Iterator<T> it = vVar.f4226f.iterator();
            while (true) {
                z2 = true;
                if (!it.hasNext()) {
                    break;
                }
                z zVar = (z) it.next();
                String str2 = vVar.f4221a + '_' + zVar.f4302a;
                if (stringPlus != null) {
                    str2 = Intrinsics.stringPlus(str2, stringPlus);
                }
                Object obj = all.get("ttl_" + vVar.f4221a + '_' + zVar.f4302a);
                if ((obj instanceof Long ? (Long) obj : null) == null) {
                    Object obj2 = all.get(str2);
                    Boolean bool = obj2 instanceof Boolean ? (Boolean) obj2 : null;
                    if (!(bool == null ? false : bool.booleanValue())) {
                        z2 = false;
                    }
                }
                zVar.f4320s = z2;
            }
            Iterator<z> it2 = vVar.f4226f.iterator();
            int i3 = 0;
            while (true) {
                if (!it2.hasNext()) {
                    i3 = -1;
                    break;
                }
                z next = it2.next();
                if (!next.f4320s && next.f4318q) {
                    break;
                }
                i3++;
            }
            if (i3 != -1) {
                z2 = false;
            }
            vVar.f4237q = z2;
        }
        return list;
    }

    public final void b(@Nullable List<v> list) {
        if (list != null) {
            for (v vVar : list) {
                String str = (String) vVar.f4245y.getValue();
                String stringPlus = str == null ? null : Intrinsics.stringPlus("_", str);
                for (z zVar : vVar.f4226f) {
                    String str2 = vVar.f4221a + '_' + zVar.f4302a;
                    String str3 = "ttl_" + vVar.f4221a + '_' + zVar.f4302a;
                    if (zVar.f4320s) {
                        StoryGroupType storyGroupType = vVar.f4228h;
                        StoryGroupType storyGroupType2 = StoryGroupType.MomentsDefault;
                        if (storyGroupType == storyGroupType2 || storyGroupType == StoryGroupType.MomentsBlock) {
                            Intrinsics.checkNotNullParameter(str3, JwtUtilsKt.DID_METHOD_KEY);
                            if (!a().contains(str3)) {
                                a(str3, System.currentTimeMillis() + ((long) 90000000));
                            }
                        }
                        StoryGroupType storyGroupType3 = vVar.f4228h;
                        if (!(storyGroupType3 == storyGroupType2 || storyGroupType3 == StoryGroupType.MomentsBlock)) {
                            Intrinsics.checkNotNullParameter(str2, JwtUtilsKt.DID_METHOD_KEY);
                            if (!a().contains(str2)) {
                                if (stringPlus != null) {
                                    str2 = Intrinsics.stringPlus(str2, stringPlus);
                                }
                                a(str2, (Object) Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }
    }
}
