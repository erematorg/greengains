package com.appsamurai.storyly.storylypresenter;

import android.content.Context;
import com.appsamurai.storyly.ShareType;
import com.appsamurai.storyly.StoryType;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.storylypresenter.share.d;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class p0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f5105a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Context f5106b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public p0(o oVar, Context context) {
        super(0);
        this.f5105a = oVar;
        this.f5106b = context;
    }

    public Object invoke() {
        z h3 = this.f5105a.getStorylyItem();
        StoryType storyType = null;
        ShareType shareType = h3 == null ? null : h3.f4312k;
        z h4 = this.f5105a.getStorylyItem();
        if (h4 != null) {
            storyType = h4.f4307f;
        }
        d dVar = new d(shareType, storyType, this.f5106b, this.f5105a.f5047b.getShare$storyly_release());
        dVar.f5321f = new o0(this.f5105a);
        dVar.show();
        return Unit.INSTANCE;
    }
}
