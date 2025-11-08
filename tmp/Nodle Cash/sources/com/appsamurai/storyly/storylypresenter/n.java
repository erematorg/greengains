package com.appsamurai.storyly.storylypresenter;

import androidx.recyclerview.widget.DiffUtil;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.storylypresenter.d;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class n extends DiffUtil.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List<v> f5032a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ List<v> f5033b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ d.c f5034c;

    public n(List<v> list, List<v> list2, d.c cVar) {
        this.f5032a = list;
        this.f5033b = list2;
        this.f5034c = cVar;
    }

    public boolean areContentsTheSame(int i3, int i4) {
        v vVar = this.f5032a.get(i3);
        v vVar2 = this.f5033b.get(i4);
        d.b bVar = (d.b) this.f5034c;
        bVar.getClass();
        Intrinsics.checkNotNullParameter(bVar, "this");
        String str = null;
        String str2 = vVar == null ? null : vVar.f4221a;
        if (vVar2 != null) {
            str = vVar2.f4221a;
        }
        return Intrinsics.areEqual((Object) str2, (Object) str);
    }

    public boolean areItemsTheSame(int i3, int i4) {
        v vVar = this.f5032a.get(i3);
        String str = null;
        String str2 = vVar == null ? null : vVar.f4221a;
        v vVar2 = this.f5033b.get(i4);
        if (vVar2 != null) {
            str = vVar2.f4221a;
        }
        return Intrinsics.areEqual((Object) str2, (Object) str);
    }

    public int getNewListSize() {
        return this.f5033b.size();
    }

    public int getOldListSize() {
        return this.f5032a.size();
    }
}
