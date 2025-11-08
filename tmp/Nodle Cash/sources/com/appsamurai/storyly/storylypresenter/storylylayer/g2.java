package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.o0;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.util.d;
import com.appsamurai.storyly.util.font.h;
import com.appsamurai.storyly.util.font.i;
import com.appsamurai.storyly.util.ui.e;
import com.appsamurai.storyly.util.ui.l;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class g2 extends o1 {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f5741g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public final v f5742h;
    @NotNull
    @SuppressLint({"RtlHardcoded"})

    /* renamed from: i  reason: collision with root package name */
    public final List<Integer> f5743i = CollectionsKt.listOf(3, 1, 5);
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public List<Integer> f5744j = CollectionsKt.listOf(48, 16, 80);

    /* renamed from: k  reason: collision with root package name */
    public o0 f5745k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f5746l;

    public static final class a extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ g2 f5747a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(g2 g2Var) {
            super(0);
            this.f5747a = g2Var;
        }

        public Object invoke() {
            this.f5747a.getOnLayerLoad$storyly_release().invoke();
            return Unit.INSTANCE;
        }
    }

    public static final class b extends Lambda implements Function0<i> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5748a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f5748a = context;
        }

        public Object invoke() {
            i iVar = new i(this.f5748a, (AttributeSet) null);
            iVar.setTextIsSelectable(false);
            d.a(iVar);
            Intrinsics.checkNotNullParameter(iVar, "<this>");
            iVar.setHyphenationFrequency(0);
            iVar.setClickable(false);
            return iVar;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g2(@NotNull Context context, @NotNull StorylyConfig storylyConfig, @Nullable v vVar) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5741g = storylyConfig;
        this.f5742h = vVar;
        this.f5746l = LazyKt.lazy(new b(context));
        setImportantForAccessibility(1);
        l.a(this, new e());
    }

    private final i getTextView() {
        return (i) this.f5746l.getValue();
    }

    public void a(@NotNull b0 b0Var) {
        StoryGroupType storyGroupType;
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        a0 a0Var = b0Var.f3615j;
        Typeface typeface = null;
        o0 o0Var = a0Var instanceof o0 ? (o0) a0Var : null;
        if (o0Var != null) {
            this.f5745k = o0Var;
            setStorylyLayerItem$storyly_release(b0Var);
            i textView = getTextView();
            o0 o0Var2 = this.f5745k;
            if (o0Var2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                o0Var2 = null;
            }
            textView.setText(o0Var2.f4081a);
            setRotation(b0Var.f3613h);
            v vVar = this.f5742h;
            if (vVar == null) {
                storyGroupType = null;
            } else {
                storyGroupType = vVar.f4228h;
            }
            if (storyGroupType == StoryGroupType.MomentsDefault) {
                o0 o0Var3 = this.f5745k;
                if (o0Var3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    o0Var3 = null;
                }
                String str = o0Var3.f4090j;
                if (str != null) {
                    typeface = this.f5741g.getMoments$storyly_release().getTextStyling$storyly_release().getMomentsCustomFont$storyly_release(str);
                }
                if (typeface != null) {
                    getTextView().setTypeface(typeface);
                } else {
                    getTextView().setTypeface(Typeface.DEFAULT);
                }
                getOnLayerLoad$storyly_release().invoke();
                return;
            }
            i textView2 = getTextView();
            o0 o0Var4 = this.f5745k;
            if (o0Var4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                o0Var4 = null;
            }
            h hVar = o0Var4.f4091k;
            if (hVar == null) {
                hVar = new h((String) null, this.f5741g.getStory$storyly_release().get_interactiveTypeface$storyly_release(), 1);
            }
            textView2.a(hVar, new a(this));
        }
    }

    public void c() {
        removeAllViews();
    }

    public void a(@NotNull d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        float b3 = dVar.b();
        float a2 = dVar.a();
        addView(getTextView(), new FrameLayout.LayoutParams(-1, -1));
        float f2 = (float) 100;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(A.a.a(getStorylyLayerItem$storyly_release().f3609d, f2, b3), A.a.a(getStorylyLayerItem$storyly_release().f3610e, f2, a2));
        getTextView().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        a(layoutParams, getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, dVar.c(), dVar.d()).rightMargin = MathKt.roundToInt(b3 - (((getStorylyLayerItem$storyly_release().f3609d / f2) * b3) + ((getStorylyLayerItem$storyly_release().f3607b / f2) * b3)));
        setLayoutParams(layoutParams);
        i textView = getTextView();
        o0 o0Var = this.f5745k;
        o0 o0Var2 = null;
        if (o0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            o0Var = null;
        }
        textView.setTextColor(o0Var.f4085e.f3624a);
        i textView2 = getTextView();
        o0 o0Var3 = this.f5745k;
        if (o0Var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            o0Var3 = null;
        }
        Float f3 = o0Var3.f4083c;
        if (f3 == null) {
            f3 = null;
        } else {
            f3.floatValue();
        }
        textView2.setTextSize(0, ((f3 == null ? o0Var3.a() : f3.floatValue()) / f2) * a2);
        i textView3 = getTextView();
        o0 o0Var4 = this.f5745k;
        if (o0Var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            o0Var4 = null;
        }
        textView3.setLineHeight((int) ((o0Var4.a() / f2) * a2));
        i textView4 = getTextView();
        List<Integer> list = this.f5744j;
        o0 o0Var5 = this.f5745k;
        if (o0Var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            o0Var5 = null;
        }
        int intValue = list.get(o0Var5.f4088h).intValue();
        List<Integer> list2 = this.f5743i;
        o0 o0Var6 = this.f5745k;
        if (o0Var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            o0Var6 = null;
        }
        textView4.setGravity(intValue | list2.get(o0Var6.f4087g).intValue());
        getTextView().setTextAlignment(1);
        getTextView().setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        getTextView().setPadding(0, 0, 0, 0);
        o0 o0Var7 = this.f5745k;
        if (o0Var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            o0Var7 = null;
        }
        SpannableString spannableString = new SpannableString(o0Var7.f4081a);
        o0 o0Var8 = this.f5745k;
        if (o0Var8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            o0Var8 = null;
        }
        int i3 = o0Var8.f4089i.f3624a;
        List<Integer> list3 = this.f5743i;
        o0 o0Var9 = this.f5745k;
        if (o0Var9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            o0Var9 = null;
        }
        com.appsamurai.storyly.util.ui.i iVar = new com.appsamurai.storyly.util.ui.i(i3, list3.get(o0Var9.f4087g).intValue(), getResources().getDimensionPixelSize(R.dimen.st_text_color_span_padding), 0.0f);
        o0 o0Var10 = this.f5745k;
        if (o0Var10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            o0Var10 = null;
        }
        spannableString.setSpan(iVar, 0, o0Var10.f4081a.length(), 33);
        getTextView().setText(spannableString);
        o0 o0Var11 = this.f5745k;
        if (o0Var11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
        } else {
            o0Var2 = o0Var11;
        }
        Integer num = o0Var2.f4084d;
        if (num != null) {
            int intValue2 = num.intValue();
            getTextView().setMinLines(intValue2);
            getTextView().setMaxLines(intValue2);
            getTextView().setEllipsize(TextUtils.TruncateAt.END);
        }
    }
}
