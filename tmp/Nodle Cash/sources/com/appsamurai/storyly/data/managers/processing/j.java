package com.appsamurai.storyly.data.managers.processing;

import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.data.managers.pagination.a;
import com.appsamurai.storyly.data.managers.pagination.c;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class j extends Lambda implements Function1<String, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ f f4013a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j(f fVar) {
        super(1);
        this.f4013a = fVar;
    }

    public Object invoke(Object obj) {
        T t2;
        String str = (String) obj;
        f fVar = this.f4013a;
        b bVar = fVar.f3975e;
        if ((bVar == null ? null : bVar.f3950b) == e.PageData) {
            c d2 = fVar.d();
            if (!d2.f3946e.isEmpty()) {
                Iterator<T> it = d2.f3946e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        t2 = null;
                        break;
                    }
                    t2 = it.next();
                    if (((a) t2).f3933c != StoryGroupType.MomentsDefault) {
                        break;
                    }
                }
                if (t2 != null) {
                    f.a(this.f4013a, str, true, false, 4);
                    return Unit.INSTANCE;
                }
            }
            f.a(this.f4013a, e.PageData, (Function0) null, (Function0) null, 6);
        }
        f.a(this.f4013a, str, false, false, 6);
        return Unit.INSTANCE;
    }
}
