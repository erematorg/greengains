package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.p;
import com.appsamurai.storyly.util.c;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@SuppressLint({"ViewConstructor"})
public final class h extends o1 {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f5749g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final List<Integer> f5750h = CollectionsKt.listOf(Integer.valueOf(GravityCompat.START), 17, Integer.valueOf(GravityCompat.END));

    /* renamed from: i  reason: collision with root package name */
    public Function1<? super b0, Unit> f5751i;

    /* renamed from: j  reason: collision with root package name */
    public p f5752j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f5753k;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5749g = storylyConfig;
        this.f5753k = LazyKt.lazy(new a(context, this));
    }

    private final AppCompatButton getActionButton() {
        return (AppCompatButton) this.f5753k.getValue();
    }

    public void a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        a0 a0Var = b0Var.f3615j;
        p pVar = null;
        p pVar2 = a0Var instanceof p ? (p) a0Var : null;
        if (pVar2 != null) {
            this.f5752j = pVar2;
            setStorylyLayerItem$storyly_release(b0Var);
            getActionButton().setTypeface(this.f5749g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
            AppCompatButton actionButton = getActionButton();
            p pVar3 = this.f5752j;
            if (pVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                pVar3 = null;
            }
            boolean z2 = pVar3.f4103j;
            p pVar4 = this.f5752j;
            if (pVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                pVar4 = null;
            }
            c.a(actionButton, z2, pVar4.f4104k);
            AppCompatButton actionButton2 = getActionButton();
            p pVar5 = this.f5752j;
            if (pVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                pVar5 = null;
            }
            actionButton2.setTextColor(pVar5.f4096c.f3624a);
            AppCompatButton actionButton3 = getActionButton();
            float dimension = getContext().getResources().getDimension(R.dimen.st_button_action_initial_text_size);
            p pVar6 = this.f5752j;
            if (pVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                pVar6 = null;
            }
            actionButton3.setTextSize(0, (getContext().getResources().getDimension(R.dimen.st_button_action_text_size_step) * ((float) pVar6.f4097d)) + dimension);
            AppCompatButton actionButton4 = getActionButton();
            p pVar7 = this.f5752j;
            if (pVar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                pVar7 = null;
            }
            actionButton4.setText(pVar7.f4094a);
            setRotation(b0Var.f3613h);
            AppCompatButton actionButton5 = getActionButton();
            List<Integer> list = this.f5750h;
            p pVar8 = this.f5752j;
            if (pVar8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            } else {
                pVar = pVar8;
            }
            actionButton5.setGravity(list.get(pVar.f4095b).intValue() | 16);
            getActionButton().setEllipsize(TextUtils.TruncateAt.END);
            getActionButton().setElevation(0.0f);
            getOnLayerLoad$storyly_release().invoke();
        }
    }

    public void c() {
        removeAllViews();
    }

    @NotNull
    public final Function1<b0, Unit> getOnUserActionClick$storyly_release() {
        Function1<? super b0, Unit> function1 = this.f5751i;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserActionClick");
        return null;
    }

    public final void setOnUserActionClick$storyly_release(@NotNull Function1<? super b0, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5751i = function1;
    }

    public static final class a extends Lambda implements Function0<AppCompatButton> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5754a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ h f5755b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, h hVar) {
            super(0);
            this.f5754a = context;
            this.f5755b = hVar;
        }

        @NotNull
        /* renamed from: a */
        public final AppCompatButton invoke() {
            AppCompatButton appCompatButton = new AppCompatButton(this.f5754a);
            h hVar = this.f5755b;
            appCompatButton.setAllCaps(false);
            appCompatButton.setSingleLine(true);
            appCompatButton.setTextAlignment(1);
            appCompatButton.setOnClickListener(new E0.c(hVar, 2));
            return appCompatButton;
        }

        public static final void a(h hVar, View view) {
            Intrinsics.checkNotNullParameter(hVar, "this$0");
            hVar.getOnUserActionClick$storyly_release().invoke(hVar.getStorylyLayerItem$storyly_release());
        }
    }

    public void a(@NotNull d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        float b3 = dVar.b();
        float a2 = dVar.a();
        addView(getActionButton(), new FrameLayout.LayoutParams(-2, -2));
        measure(0, 0);
        float f2 = (float) 100;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(A.a.a(getStorylyLayerItem$storyly_release().f3609d, f2, b3), A.a.a(getStorylyLayerItem$storyly_release().f3610e, f2, a2));
        getActionButton().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        setLayoutParams(a(layoutParams, getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, dVar.c(), dVar.d()));
        float measuredHeight = (float) getMeasuredHeight();
        p pVar = this.f5752j;
        p pVar2 = null;
        if (pVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            pVar = null;
        }
        float f3 = (((float) pVar.f4101h) / 100.0f) * measuredHeight;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.st_button_action_bg);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            gradientDrawable.mutate();
            p pVar3 = this.f5752j;
            if (pVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                pVar3 = null;
            }
            gradientDrawable.setColor(pVar3.f4098e.f3624a);
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.st_button_action_border_initial_thickness);
            p pVar4 = this.f5752j;
            if (pVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                pVar4 = null;
            }
            int dimensionPixelSize2 = (getContext().getResources().getDimensionPixelSize(R.dimen.st_button_action_border_thickness_step) * pVar4.f4100g) + dimensionPixelSize;
            p pVar5 = this.f5752j;
            if (pVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            } else {
                pVar2 = pVar5;
            }
            gradientDrawable.setStroke(dimensionPixelSize2, pVar2.f4099f.f3624a);
            gradientDrawable.setCornerRadius(f3);
            getActionButton().setBackground(gradientDrawable);
            getActionButton().setPadding(0, 0, 0, 0);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }
}
