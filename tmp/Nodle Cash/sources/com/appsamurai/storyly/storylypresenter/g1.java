package com.appsamurai.storyly.storylypresenter;

import android.animation.ObjectAnimator;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.storylypresenter.storylyfooter.d;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class g1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f4997a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ d f4998b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g1(o oVar, d dVar) {
        super(0);
        this.f4997a = oVar;
        this.f4998b = dVar;
    }

    public Object invoke() {
        StringBuilder sb = new StringBuilder("ttl_");
        v storylyGroupItem$storyly_release = this.f4997a.getStorylyGroupItem$storyly_release();
        String str = null;
        sb.append(storylyGroupItem$storyly_release == null ? null : storylyGroupItem$storyly_release.f4221a);
        sb.append('_');
        z h3 = this.f4997a.getStorylyItem();
        if (h3 != null) {
            str = h3.f4302a;
        }
        sb.append(str);
        this.f4997a.getReportSharedPreferencesManager().a(sb.toString(), System.currentTimeMillis() + ((long) 90000000));
        z h4 = this.f4997a.getStorylyItem();
        if (h4 != null) {
            h4.f4316o = true;
        }
        d dVar = this.f4998b;
        dVar.f5430a.setBackgroundColor(0);
        ((BottomSheetDialog) dVar.f5444o.getValue()).dismiss();
        dVar.a().setVisibility(0);
        dVar.b().setVisibility(0);
        dVar.b().setY((float) dVar.f5430a.getHeight());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(dVar.b(), "y", new float[]{((float) dVar.f5430a.getHeight()) - ((float) dVar.b().getHeight())});
        ofFloat.setDuration(300);
        ofFloat.start();
        dVar.i();
        return Unit.INSTANCE;
    }
}
