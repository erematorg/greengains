package com.appsamurai.storyly.storylypresenter.storylylayer;

import G0.j;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.r;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.util.animation.models.b;
import com.appsamurai.storyly.util.ui.l;
import io.nodle.cash.data.model.transaction.BridgePendingTransaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import kotlinx.serialization.json.JsonObject;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class m extends o1 {
    @NotNull

    /* renamed from: A  reason: collision with root package name */
    public final List<String> f5847A;

    /* renamed from: B  reason: collision with root package name */
    public final float f5848B;

    /* renamed from: C  reason: collision with root package name */
    public float f5849C;

    /* renamed from: D  reason: collision with root package name */
    public float f5850D;

    /* renamed from: E  reason: collision with root package name */
    public int f5851E;
    public int F;
    public int G;

    /* renamed from: H  reason: collision with root package name */
    public int f5852H;

    /* renamed from: I  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f5853I;

    /* renamed from: J  reason: collision with root package name */
    public r f5854J;
    @NotNull
    public com.appsamurai.storyly.util.notification.a K;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final z f5855g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public final String f5856h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final StorylyConfig f5857i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final RelativeLayout f5858j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final RelativeLayout f5859k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final TextView f5860l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Button f5861m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final RelativeLayout f5862n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final RelativeLayout f5863o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public List<RelativeLayout> f5864p = new ArrayList();
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final com.appsamurai.storyly.util.animation.b f5865q;
    @Nullable

    /* renamed from: r  reason: collision with root package name */
    public com.appsamurai.storyly.util.animation.c f5866r;
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    public final Lazy f5867s;

    /* renamed from: t  reason: collision with root package name */
    public final long f5868t;

    /* renamed from: u  reason: collision with root package name */
    public final long f5869u;

    /* renamed from: v  reason: collision with root package name */
    public final long f5870v;
    @NotNull

    /* renamed from: w  reason: collision with root package name */
    public final RelativeLayout f5871w;
    @NotNull

    /* renamed from: x  reason: collision with root package name */
    public final ImageView f5872x;
    @NotNull

    /* renamed from: y  reason: collision with root package name */
    public final Lazy f5873y;
    @NotNull

    /* renamed from: z  reason: collision with root package name */
    public final Lazy f5874z;

    public enum a {
        LEFT,
        RIGHT,
        ALL,
        NONE
    }

    public /* synthetic */ class b {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[a.values().length];
            iArr[0] = 1;
            iArr[1] = 2;
            iArr[2] = 3;
            iArr[3] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final class c extends Lambda implements Function0<Handler> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5880a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5880a = context;
        }

        public Object invoke() {
            return new Handler(this.f5880a.getMainLooper());
        }
    }

    public static final class d extends Lambda implements Function0<Handler> {

        /* renamed from: a  reason: collision with root package name */
        public static final d f5881a = new d();

        public d() {
            super(0);
        }

        public Object invoke() {
            return new Handler(Looper.getMainLooper());
        }
    }

    public static final class e extends Lambda implements Function0<TextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5882a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f5882a = context;
        }

        public Object invoke() {
            TextView textView = new TextView(this.f5882a);
            textView.setMaxLines(1);
            textView.setMinLines(1);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            return textView;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m(@NotNull Context context, @NotNull z zVar, @Nullable String str, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(zVar, "storylyItem");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5855g = zVar;
        this.f5856h = str;
        this.f5857i = storylyConfig;
        this.f5858j = new RelativeLayout(context);
        this.f5859k = new RelativeLayout(context);
        TextView textView = new TextView(context);
        this.f5860l = textView;
        this.f5861m = new Button(context);
        this.f5862n = new RelativeLayout(context);
        this.f5863o = new RelativeLayout(context);
        this.f5865q = new com.appsamurai.storyly.util.animation.b(context);
        this.f5867s = LazyKt.lazy(new c(context));
        this.f5868t = 600;
        this.f5869u = ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS;
        this.f5870v = 300;
        this.f5871w = new RelativeLayout(context);
        this.f5872x = new ImageView(context);
        this.f5873y = LazyKt.lazy(new e(context));
        this.f5874z = LazyKt.lazy(d.f5881a);
        String string = context.getString(R.string.days_text);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.days_text)");
        String string2 = context.getString(R.string.hours_text);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.hours_text)");
        String string3 = context.getString(R.string.minutes_text);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.minutes_text)");
        this.f5847A = CollectionsKt.listOf(string, string2, string3);
        this.f5848B = 15.0f;
        this.K = new com.appsamurai.storyly.util.notification.a(context);
        textView.setId(View.generateViewId());
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setMaxLines(2);
        textView.setMinLines(2);
        com.appsamurai.storyly.util.d.a(textView);
        textView.setHorizontallyScrolling(false);
        setClipChildren(false);
        setClipToPadding(false);
        l.b(this);
    }

    private final int getAlarmImage() {
        if (f()) {
            return R.drawable.st_alarm_on;
        }
        r rVar = this.f5854J;
        if (rVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar = null;
        }
        return Intrinsics.areEqual((Object) rVar.f4170b, (Object) "Dark") ? R.drawable.st_alarm_dark_off : R.drawable.st_alarm_light_off;
    }

    private final RelativeLayout.LayoutParams getCountDownItemParams() {
        Pair<Float, Float> countDownItemSizes = getCountDownItemSizes();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.width = (int) countDownItemSizes.getFirst().floatValue();
        layoutParams.height = (int) countDownItemSizes.getSecond().floatValue();
        layoutParams.addRule(10);
        layoutParams.addRule(12);
        return layoutParams;
    }

    private final Pair<Float, Float> getCountDownItemSizes() {
        float f2 = (float) 3;
        float seperatorSpaceSize = ((float) (this.f5851E - (this.F * 2))) - ((getSeperatorSpaceSize() * ((float) 2)) + (getItemSpaceSize() * f2));
        if (h()) {
            seperatorSpaceSize -= (float) ((this.F / 2) + this.f5852H);
        }
        float f3 = seperatorSpaceSize / ((float) 6);
        return new Pair<>(Float.valueOf(f3), Float.valueOf((f3 / f2) * ((float) 4)));
    }

    private final RelativeLayout.LayoutParams getCountDownUnitParams() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (getCountDownUnitSize() - getSeperatorSpaceSize()), -2);
        layoutParams.addRule(9);
        return layoutParams;
    }

    private final float getCountDownUnitSize() {
        return (getCountDownItemSizes().getFirst().floatValue() * ((float) 2)) + getItemSpaceSize() + getSeperatorSpaceSize();
    }

    private final float getItemSpaceSize() {
        r rVar = this.f5854J;
        if (rVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar = null;
        }
        return (rVar.f4175g * 3.0f) + 8.0f;
    }

    private final Handler getKonfettiHandler() {
        return (Handler) this.f5867s.getValue();
    }

    private final float getNumberFontSize() {
        r rVar = this.f5854J;
        if (rVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar = null;
        }
        return (rVar.f4175g * 1.75f) + 24.0f;
    }

    private final TextView getSeparatorView() {
        TextView textView = new TextView(getContext());
        textView.setText(":");
        textView.setTypeface(this.f5857i.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        textView.setGravity(17);
        textView.setTextAlignment(1);
        textView.setTextSize(getNumberFontSize());
        r rVar = this.f5854J;
        if (rVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar = null;
        }
        textView.setTextColor(rVar.b().f3624a);
        return textView;
    }

    private final float getSeperatorSpaceSize() {
        r rVar = this.f5854J;
        if (rVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar = null;
        }
        return (rVar.f4175g * 3.0f) + 24.0f;
    }

    private final long getTimestamp() {
        return new Date().getTime() / ((long) 1000);
    }

    private final float getTitleFontSize() {
        float f2 = h() ? 14.0f : 16.0f;
        r rVar = this.f5854J;
        if (rVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar = null;
        }
        return (rVar.f4175g * 1.75f) + f2;
    }

    private final Handler getToastHandler() {
        return (Handler) this.f5874z.getValue();
    }

    private final TextView getToastMessage() {
        return (TextView) this.f5873y.getValue();
    }

    private final float getUnitFontSize() {
        r rVar = this.f5854J;
        if (rVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar = null;
        }
        return (rVar.f4175g * 1.5f) + 12.0f;
    }

    public void a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        a0 a0Var = b0Var.f3615j;
        r rVar = null;
        r rVar2 = a0Var instanceof r ? (r) a0Var : null;
        if (rVar2 != null) {
            this.f5854J = rVar2;
            setStorylyLayerItem$storyly_release(b0Var);
            RelativeLayout relativeLayout = this.f5858j;
            r rVar3 = this.f5854J;
            if (rVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                rVar3 = null;
            }
            relativeLayout.setBackgroundColor(rVar3.a().f3624a);
            this.f5859k.setId(View.generateViewId());
            TextView textView = this.f5860l;
            r rVar4 = this.f5854J;
            if (rVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                rVar4 = null;
            }
            textView.setTextColor(rVar4.b().f3624a);
            TextView textView2 = this.f5860l;
            r rVar5 = this.f5854J;
            if (rVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                rVar5 = null;
            }
            textView2.setText(rVar5.f4169a);
            this.f5860l.setEllipsize(TextUtils.TruncateAt.END);
            this.f5860l.setTextSize(2, getTitleFontSize());
            this.f5860l.setTypeface(this.f5857i.getStory$storyly_release().getInteractiveTypeface$storyly_release());
            TextView textView3 = this.f5860l;
            r rVar6 = this.f5854J;
            if (rVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                rVar6 = null;
            }
            boolean z2 = rVar6.f4182n;
            r rVar7 = this.f5854J;
            if (rVar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                rVar7 = null;
            }
            com.appsamurai.storyly.util.c.a(textView3, z2, rVar7.f4183o);
            this.f5861m.setId(View.generateViewId());
            this.f5861m.setBackgroundResource(getAlarmImage());
            this.f5861m.setVisibility(g() ? 0 : 4);
            this.f5862n.setId(View.generateViewId());
            this.f5862n.setBackgroundColor(0);
            this.f5871w.setId(View.generateViewId());
            this.f5871w.setAlpha(0.0f);
            RelativeLayout relativeLayout2 = this.f5871w;
            a aVar = a.ALL;
            r rVar8 = this.f5854J;
            if (rVar8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                rVar8 = null;
            }
            relativeLayout2.setBackground(a(aVar, rVar8.a().f3624a, 15.0f));
            this.f5872x.setId(View.generateViewId());
            this.f5872x.setBackgroundResource(getAlarmImage());
            getToastMessage().setId(View.generateViewId());
            getToastMessage().setTextSize(2, this.f5848B);
            TextView toastMessage = getToastMessage();
            r rVar9 = this.f5854J;
            if (rVar9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            } else {
                rVar = rVar9;
            }
            toastMessage.setTextColor(rVar.b().f3624a);
            this.f5858j.setRotation(b0Var.f3613h);
            getOnLayerLoad$storyly_release().invoke();
        }
    }

    public final RelativeLayout b(String str) {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setId(View.generateViewId());
        a aVar = a.ALL;
        r rVar = this.f5854J;
        r rVar2 = null;
        if (rVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar = null;
        }
        relativeLayout.setBackground(a(aVar, (Intrinsics.areEqual((Object) rVar.f4170b, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_434343 : com.appsamurai.storyly.config.styling.a.COLOR_EFEFEF).b().f3624a, 7.0f));
        TextView textView = new TextView(getContext());
        textView.setId(View.generateViewId());
        textView.setText(str);
        textView.setTypeface(this.f5857i.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        textView.setGravity(1);
        textView.setTextAlignment(1);
        textView.setTextSize(getNumberFontSize());
        r rVar3 = this.f5854J;
        if (rVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
        } else {
            rVar2 = rVar3;
        }
        textView.setTextColor(rVar2.b().f3624a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        textView.setLayoutParams(layoutParams);
        relativeLayout.addView(textView);
        return relativeLayout;
    }

    public void c() {
        com.appsamurai.storyly.util.animation.c cVar = this.f5866r;
        if (cVar != null) {
            com.appsamurai.storyly.util.animation.b bVar = cVar.f6270a;
            bVar.getClass();
            Intrinsics.checkNotNullParameter(cVar, "particleSystem");
            bVar.f6266a.remove(cVar);
            com.appsamurai.storyly.util.animation.listeners.a aVar = bVar.f6268c;
            if (aVar != null) {
                aVar.b(bVar, cVar, bVar.f6266a.size());
            }
        }
        this.f5866r = null;
        this.f5858j.removeAllViews();
        this.f5859k.removeAllViews();
        this.f5862n.removeAllViews();
        this.f5863o.removeAllViews();
        getKonfettiHandler().removeCallbacksAndMessages((Object) null);
        removeView(this.f5871w);
        this.f5871w.removeAllViews();
        removeAllViews();
    }

    public final boolean f() {
        com.appsamurai.storyly.util.notification.a aVar = this.K;
        String str = this.f5855g.f4302a;
        aVar.getClass();
        Intrinsics.checkNotNullParameter(str, "storylyId");
        return aVar.a(str, 536870912) != null;
    }

    public final boolean g() {
        int timestamp = (int) getTimestamp();
        r rVar = this.f5854J;
        if (rVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar = null;
        }
        Long l2 = rVar.f4172d;
        if (l2 == null) {
            return false;
        }
        return ((long) timestamp) <= l2.longValue();
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> getOnUserReaction$storyly_release() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f5853I;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    public final boolean h() {
        if (g()) {
            r rVar = this.f5854J;
            if (rVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                rVar = null;
            }
            if (!rVar.f4176h) {
                return true;
            }
        }
        return false;
    }

    public final void i() {
        getKonfettiHandler().removeCallbacksAndMessages((Object) null);
        removeView(this.f5865q);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(10);
        layoutParams.addRule(12);
        layoutParams.addRule(9);
        layoutParams.addRule(11);
        addView(this.f5865q, layoutParams);
        getKonfettiHandler().postDelayed(new j(this, 0), this.f5868t);
    }

    public final void setOnUserReaction$storyly_release(@NotNull Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5) {
        Intrinsics.checkNotNullParameter(function5, "<set-?>");
        this.f5853I = function5;
    }

    public static final void b(m mVar) {
        Intrinsics.checkNotNullParameter(mVar, "this$0");
        ViewPropertyAnimator animate = mVar.f5871w.animate();
        animate.setInterpolator(new DecelerateInterpolator());
        animate.setDuration(mVar.f5870v / ((long) 2));
        animate.alpha(0.0f);
        animate.start();
    }

    public final void a(boolean z2) {
        Context context;
        int i3;
        removeView(this.f5871w);
        this.f5871w.removeAllViews();
        getToastHandler().removeCallbacksAndMessages((Object) null);
        int dimension = (int) getContext().getResources().getDimension(R.dimen.st_story_toast_width);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dimension, (int) getContext().getResources().getDimension(R.dimen.st_story_toast_height));
        layoutParams.gravity = 0;
        layoutParams.topMargin = MathKt.roundToInt(this.f5850D - ((float) ((int) getContext().getResources().getDimension(R.dimen.st_story_toast_margin))));
        layoutParams.leftMargin = MathKt.roundToInt((this.f5849C - ((float) dimension)) / ((float) 2));
        addView(this.f5871w, layoutParams);
        this.f5871w.bringToFront();
        int dimension2 = (int) getContext().getResources().getDimension(R.dimen.st_story_toast_button_size);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dimension2, dimension2);
        layoutParams2.addRule(20);
        layoutParams2.addRule(15);
        layoutParams2.setMarginStart((int) getContext().getResources().getDimension(R.dimen.st_story_toast_inline_margin));
        this.f5871w.addView(this.f5872x, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams3.addRule(17, this.f5872x.getId());
        layoutParams3.addRule(21);
        layoutParams3.addRule(10);
        layoutParams3.addRule(12);
        TextView toastMessage = getToastMessage();
        if (z2) {
            context = getContext();
            i3 = R.string.reminder_on_text;
        } else {
            context = getContext();
            i3 = R.string.reminder_off_text;
        }
        toastMessage.setText(context.getString(i3));
        getToastMessage().setGravity(16);
        getToastMessage().setTextAlignment(4);
        getToastMessage().setPadding((int) getContext().getResources().getDimension(R.dimen.st_cd_toast_message_padding_start), 0, (int) getContext().getResources().getDimension(R.dimen.st_cd_toast_message_padding_end), 0);
        this.f5871w.addView(getToastMessage(), layoutParams3);
        this.f5872x.setBackgroundResource(getAlarmImage());
        ViewPropertyAnimator animate = this.f5871w.animate();
        animate.setInterpolator(new LinearInterpolator());
        animate.setDuration(this.f5870v);
        animate.alpha(1.0f);
        animate.start();
        getToastHandler().postDelayed(new j(this, 1), this.f5869u);
    }

    public void a(@NotNull d dVar) {
        char[] cArr;
        Unit unit;
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        c();
        this.f5849C = dVar.b();
        this.f5850D = dVar.a();
        float f2 = this.f5849C;
        r rVar = this.f5854J;
        if (rVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar = null;
        }
        this.f5851E = A.a.a((rVar.f4175g * 4.0f) + 55.0f, (float) 100, f2);
        this.F = (int) getContext().getResources().getDimension(R.dimen.st_story_cd_horizontal_margin);
        this.G = (int) getContext().getResources().getDimension(R.dimen.st_story_cd_vertical_margin);
        this.f5852H = (int) getContext().getResources().getDimension(R.dimen.st_story_cd_alarm_size);
        if (h()) {
            this.f5851E = this.f5852H + this.F + this.f5851E;
        }
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        setBackgroundColor(0);
        FrameLayout.LayoutParams a2 = a(new FrameLayout.LayoutParams(this.f5851E, -2), getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, dVar.c(), dVar.d());
        RelativeLayout relativeLayout = this.f5858j;
        a aVar = a.ALL;
        r rVar2 = this.f5854J;
        if (rVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar2 = null;
        }
        GradientDrawable gradientDrawable = (GradientDrawable) a(aVar, rVar2.a().f3624a, 15.0f);
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.st_cd_background_border_initial_thickness);
        r rVar3 = this.f5854J;
        if (rVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar3 = null;
        }
        com.appsamurai.storyly.data.c cVar = rVar3.f4181m;
        if (cVar == null) {
            cVar = (Intrinsics.areEqual((Object) rVar3.f4170b, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_3D3D3D : com.appsamurai.storyly.config.styling.a.COLOR_E0E0E0).b();
        }
        gradientDrawable.setStroke(dimensionPixelSize, cVar.f3624a);
        Unit unit2 = Unit.INSTANCE;
        relativeLayout.setBackground(gradientDrawable);
        addView(this.f5858j, a2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = this.G;
        int i3 = this.F;
        layoutParams.leftMargin = i3;
        layoutParams.rightMargin = i3;
        this.f5859k.setBackgroundColor(0);
        r rVar4 = this.f5854J;
        if (rVar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar4 = null;
        }
        if (rVar4.f4176h) {
            this.f5858j.addView(this.f5859k, layoutParams);
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.setMarginEnd(g() ? this.F + this.f5852H : 0);
        r rVar5 = this.f5854J;
        if (rVar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar5 = null;
        }
        if (rVar5.f4176h) {
            this.f5859k.addView(this.f5860l, layoutParams2);
        }
        this.f5860l.setGravity((a() ? 3 : 5) | 16);
        this.f5860l.setTextAlignment(1);
        int i4 = this.f5852H;
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i4, i4);
        layoutParams3.addRule(21);
        float abs = Math.abs((((float) this.f5852H) - getCountDownItemSizes().getSecond().floatValue()) / ((float) 2));
        if (h()) {
            layoutParams3.addRule(10);
            layoutParams3.topMargin = MathKt.roundToInt(((float) this.F) + abs);
            layoutParams3.setMarginEnd(this.F);
            this.f5858j.addView(this.f5861m, layoutParams3);
        } else {
            layoutParams3.addRule(15);
            this.f5859k.addView(this.f5861m, layoutParams3);
        }
        this.f5861m.setOnClickListener(new E0.c(this, 3));
        float floatValue = getCountDownItemSizes().getSecond().floatValue();
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams4.setMarginStart(this.F);
        layoutParams4.setMarginEnd(h() ? this.F + this.f5852H : this.F);
        layoutParams4.topMargin = this.F;
        layoutParams4.height = (int) floatValue;
        if (!h()) {
            r rVar6 = this.f5854J;
            if (rVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                rVar6 = null;
            }
            if (rVar6.f4176h) {
                layoutParams4.addRule(3, this.f5859k.getId());
            }
        }
        if (h() && !a()) {
            this.f5862n.setPadding(this.F, 0, 0, 0);
        }
        this.f5858j.addView(this.f5862n, layoutParams4);
        this.f5864p = new ArrayList();
        r rVar7 = this.f5854J;
        if (rVar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar7 = null;
        }
        int i5 = (int) rVar7.f4171c;
        int timestamp = (int) getTimestamp();
        int i6 = i5 - timestamp;
        if (i5 < timestamp) {
            cArr = "000000".toCharArray();
            Intrinsics.checkNotNullExpressionValue(cArr, "(this as java.lang.String).toCharArray()");
        } else {
            String a3 = a(i6 / BridgePendingTransaction.BRIDGE_TRANSACTION_ETA);
            int i7 = i6 % BridgePendingTransaction.BRIDGE_TRANSACTION_ETA;
            String a4 = a(i7 / 3600);
            String a5 = a((i7 % 3600) / 60);
            if (a3 != null) {
                char[] charArray = a3.toCharArray();
                Intrinsics.checkNotNullExpressionValue(charArray, "(this as java.lang.String).toCharArray()");
                if (a4 != null) {
                    char[] charArray2 = a4.toCharArray();
                    Intrinsics.checkNotNullExpressionValue(charArray2, "(this as java.lang.String).toCharArray()");
                    if (a5 != null) {
                        char[] charArray3 = a5.toCharArray();
                        Intrinsics.checkNotNullExpressionValue(charArray3, "(this as java.lang.String).toCharArray()");
                        cArr = ArraysKt.plus(ArraysKt.plus(charArray, charArray2), charArray3);
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
        }
        int length = cArr.length;
        int i8 = 0;
        int i9 = 0;
        while (i8 < length) {
            int i10 = i9 + 1;
            RelativeLayout b3 = b(String.valueOf(cArr[i8]));
            RelativeLayout.LayoutParams countDownItemParams = getCountDownItemParams();
            if (((RelativeLayout) CollectionsKt.lastOrNull(this.f5864p)) == null) {
                unit = null;
            } else {
                float seperatorSpaceSize = i9 % 2 == 0 ? getSeperatorSpaceSize() : getItemSpaceSize();
                countDownItemParams.addRule(1, this.f5864p.get(i9 - 1).getId());
                countDownItemParams.leftMargin = (int) seperatorSpaceSize;
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                countDownItemParams.addRule(9);
                Unit unit3 = Unit.INSTANCE;
            }
            this.f5862n.addView(b3, countDownItemParams);
            this.f5864p.add(b3);
            i8++;
            i9 = i10;
        }
        if (ArraysKt.toHashSet(cArr).size() == 1) {
            RelativeLayout b4 = b("1");
            RelativeLayout.LayoutParams countDownItemParams2 = getCountDownItemParams();
            float itemSpaceSize = getItemSpaceSize();
            countDownItemParams2.addRule(1, this.f5864p.get(4).getId());
            countDownItemParams2.leftMargin = (int) itemSpaceSize;
            this.f5862n.addView(b4, countDownItemParams2);
            this.f5864p.add(b4);
            this.f5864p.get(6).animate().withLayer().rotationX(180.0f).alpha(0.0f).setDuration(300).setStartDelay(300).start();
            this.f5864p.get(5).setAlpha(0.0f);
            this.f5864p.get(5).setRotationX(-180.0f);
            this.f5864p.get(5).animate().withLayer().rotationX(0.0f).alpha(1.0f).setDuration(300).setStartDelay(300).start();
            i();
        }
        TextView separatorView = getSeparatorView();
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams((int) getSeperatorSpaceSize(), -2);
        layoutParams5.addRule(1, this.f5864p.get(1).getId());
        layoutParams5.addRule(10);
        layoutParams5.addRule(12);
        this.f5862n.addView(separatorView, layoutParams5);
        TextView separatorView2 = getSeparatorView();
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams((int) getSeperatorSpaceSize(), -2);
        layoutParams6.addRule(1, this.f5864p.get(3).getId());
        layoutParams6.addRule(10);
        layoutParams6.addRule(12);
        this.f5862n.addView(separatorView2, layoutParams6);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams7.addRule(5, this.f5862n.getId());
        layoutParams7.addRule(7, this.f5862n.getId());
        layoutParams7.addRule(3, this.f5862n.getId());
        r rVar8 = this.f5854J;
        if (rVar8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            rVar8 = null;
        }
        layoutParams7.bottomMargin = rVar8.f4176h ? this.G : this.F;
        this.f5858j.addView(this.f5863o, layoutParams7);
        int i11 = 0;
        for (T next : this.f5847A) {
            int i12 = i11 + 1;
            if (i11 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            RelativeLayout.LayoutParams countDownUnitParams = getCountDownUnitParams();
            TextView textView = new TextView(getContext());
            textView.setTextAlignment(4);
            textView.setText((String) next);
            textView.setTypeface(this.f5857i.getStory$storyly_release().getInteractiveTypeface$storyly_release());
            textView.setMaxLines(1);
            textView.setTextSize(getUnitFontSize());
            r rVar9 = this.f5854J;
            if (rVar9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                rVar9 = null;
            }
            textView.setTextColor((Intrinsics.areEqual((Object) rVar9.f4170b, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_ADADAD : com.appsamurai.storyly.config.styling.a.COLOR_262626).b().f3624a);
            if (i11 == 0) {
                this.f5863o.addView(textView, countDownUnitParams);
            } else {
                countDownUnitParams.leftMargin = (int) (getCountDownUnitSize() * ((float) i11));
                this.f5863o.addView(textView, countDownUnitParams);
            }
            i11 = i12;
        }
    }

    public final Drawable a(a aVar, int i3, float f2) {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.st_rectangle_shape_drawable);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) ((GradientDrawable) drawable).mutate();
            gradientDrawable.setColor(i3);
            float applyDimension = TypedValue.applyDimension(1, f2, getContext().getResources().getDisplayMetrics());
            int i4 = b.$EnumSwitchMapping$0[aVar.ordinal()];
            if (i4 == 1) {
                gradientDrawable.setCornerRadii(new float[]{applyDimension, applyDimension, 0.0f, 0.0f, 0.0f, 0.0f, applyDimension, applyDimension});
            } else if (i4 == 2) {
                gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, applyDimension, applyDimension, applyDimension, applyDimension, 0.0f, 0.0f});
            } else if (i4 == 3) {
                gradientDrawable.setCornerRadii(new float[]{applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension});
            }
            return gradientDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }

    public static final String a(int i3) {
        return i3 < 10 ? Intrinsics.stringPlus(SchemaSymbols.ATTVAL_FALSE_0, Integer.valueOf(i3)) : String.valueOf(i3);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: android.app.AlarmManager} */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v1, types: [android.content.Intent] */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: type inference failed for: r5v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void a(com.appsamurai.storyly.storylypresenter.storylylayer.m r20, android.view.View r21) {
        /*
            r0 = r20
            java.lang.String r1 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            boolean r1 = r20.f()
            r2 = 0
            java.lang.String r3 = "alarm"
            java.lang.String r4 = "storylyId"
            r5 = 0
            if (r1 == 0) goto L_0x0055
            com.appsamurai.storyly.util.notification.a r1 = r0.K
            com.appsamurai.storyly.data.z r6 = r0.f5855g
            java.lang.String r6 = r6.f4302a
            r1.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r4)
            r4 = 536870912(0x20000000, float:1.0842022E-19)
            android.app.PendingIntent r4 = r1.a(r6, r4)
            if (r4 != 0) goto L_0x002a
            goto L_0x0040
        L_0x002a:
            android.content.Context r1 = r1.f6345a
            java.lang.Object r1 = r1.getSystemService(r3)
            boolean r3 = r1 instanceof android.app.AlarmManager
            if (r3 == 0) goto L_0x0037
            r5 = r1
            android.app.AlarmManager r5 = (android.app.AlarmManager) r5
        L_0x0037:
            if (r5 != 0) goto L_0x003a
            goto L_0x0040
        L_0x003a:
            r5.cancel(r4)
            r4.cancel()
        L_0x0040:
            kotlin.jvm.functions.Function5 r6 = r20.getOnUserReaction$storyly_release()
            com.appsamurai.storyly.analytics.a r7 = com.appsamurai.storyly.analytics.a.CountdownReminderRemoved
            com.appsamurai.storyly.data.b0 r8 = r20.getStorylyLayerItem$storyly_release()
            r10 = 0
            r11 = 0
            r9 = 0
            r6.invoke(r7, r8, r9, r10, r11)
            r0.a((boolean) r2)
            goto L_0x01c8
        L_0x0055:
            com.appsamurai.storyly.data.r r1 = r0.f5854J
            java.lang.String r6 = "storylyLayer"
            if (r1 != 0) goto L_0x0060
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r1 = r5
        L_0x0060:
            java.lang.String r1 = r1.f4174f
            if (r1 == 0) goto L_0x0076
            int r1 = r1.length()
            if (r1 != 0) goto L_0x006b
            goto L_0x0076
        L_0x006b:
            com.appsamurai.storyly.data.r r1 = r0.f5854J
            if (r1 != 0) goto L_0x0073
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r1 = r5
        L_0x0073:
            java.lang.String r1 = r1.f4174f
            goto L_0x00a4
        L_0x0076:
            com.appsamurai.storyly.data.z r1 = r0.f5855g
            java.lang.String r1 = r1.f4302a
            java.lang.String r7 = r0.f5856h
            android.net.Uri$Builder r8 = new android.net.Uri$Builder
            r8.<init>()
            java.lang.String r9 = "storyly"
            android.net.Uri$Builder r10 = r8.scheme(r9)
            android.net.Uri$Builder r9 = r10.authority(r9)
            java.lang.String r10 = "g"
            android.net.Uri$Builder r7 = r9.appendQueryParameter(r10, r7)
            java.lang.String r9 = "s"
            r7.appendQueryParameter(r9, r1)
            android.net.Uri r1 = r8.build()
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = "builder.build().toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r7)
        L_0x00a4:
            com.appsamurai.storyly.util.notification.a r7 = r0.K
            com.appsamurai.storyly.data.z r8 = r0.f5855g
            java.lang.String r8 = r8.f4302a
            com.appsamurai.storyly.data.r r9 = r0.f5854J
            if (r9 != 0) goto L_0x00b2
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r9 = r5
        L_0x00b2:
            java.lang.String r9 = r9.f4173e
            if (r9 != 0) goto L_0x00c0
            com.appsamurai.storyly.data.r r9 = r0.f5854J
            if (r9 != 0) goto L_0x00be
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r9 = r5
        L_0x00be:
            java.lang.String r9 = r9.f4169a
        L_0x00c0:
            com.appsamurai.storyly.data.r r10 = r0.f5854J
            if (r10 != 0) goto L_0x00c8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r10 = r5
        L_0x00c8:
            java.lang.Long r10 = r10.f4172d
            if (r10 != 0) goto L_0x00d7
            com.appsamurai.storyly.data.r r10 = r0.f5854J
            if (r10 != 0) goto L_0x00d4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r10 = r5
        L_0x00d4:
            long r10 = r10.f4171c
            goto L_0x00db
        L_0x00d7:
            long r10 = r10.longValue()
        L_0x00db:
            r7.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r4)
            java.lang.String r4 = "message"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r4)
            android.content.Context r4 = r7.f6345a
            java.lang.String r6 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r6)
            android.content.pm.ApplicationInfo r6 = r4.getApplicationInfo()
            java.lang.String r12 = "context.applicationInfo"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r12)
            int r12 = r6.labelRes
            if (r12 != 0) goto L_0x0101
            java.lang.CharSequence r4 = r6.nonLocalizedLabel
            java.lang.String r4 = r4.toString()
            goto L_0x010a
        L_0x0101:
            java.lang.String r4 = r4.getString(r12)
            java.lang.String r6 = "context.getString(stringId)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)
        L_0x010a:
            if (r4 != 0) goto L_0x010e
            java.lang.String r4 = ""
        L_0x010e:
            androidx.core.app.NotificationCompat$Builder r6 = new androidx.core.app.NotificationCompat$Builder
            android.content.Context r12 = r7.f6345a
            java.lang.String r13 = "storyly-notification-channel-id"
            r6.<init>((android.content.Context) r12, (java.lang.String) r13)
            r6.setContentTitle(r4)
            r6.setContentText(r9)
            int r4 = com.appsamurai.storyly.R.drawable.st_ic_countdown_small_notification_icon
            r6.setSmallIcon((int) r4)
            android.content.Context r4 = r7.f6345a
            android.content.pm.PackageManager r9 = r4.getPackageManager()
            java.lang.String r4 = r4.getPackageName()
            android.graphics.drawable.Drawable r14 = r9.getApplicationIcon(r4)
            java.lang.String r4 = "context.packageManager.g…Icon(context.packageName)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r4)
            r18 = 7
            r19 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            android.graphics.Bitmap r4 = androidx.core.graphics.drawable.DrawableKt.toBitmap$default(r14, r15, r16, r17, r18, r19)
            r6.setLargeIcon((android.graphics.Bitmap) r4)
            r4 = 1
            r6.setAutoCancel(r4)
            r6.setChannelId(r13)
            r6.setOngoing(r4)
            r6.setPriority(r4)
            java.lang.String r9 = "event"
            r6.setCategory(r9)
            android.app.Notification r6 = r6.build()
            java.lang.String r9 = "builder.build()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r9)
            if (r1 != 0) goto L_0x0164
            goto L_0x018f
        L_0x0164:
            android.content.Intent r5 = new android.content.Intent
            android.content.Context r9 = r7.f6345a
            java.lang.Class<com.appsamurai.storyly.util.notification.StorylyNotificationReceiver> r12 = com.appsamurai.storyly.util.notification.StorylyNotificationReceiver.class
            r5.<init>(r9, r12)
            android.content.Context r9 = r7.f6345a
            java.lang.String r9 = r9.getPackageName()
            r5.setPackage(r9)
            java.lang.String r9 = "com.appsamurai.storyly.ACTION_COUNTDOWN_NOTIFICATION"
            r5.setAction(r9)
            java.lang.String r9 = "notification"
            r5.putExtra(r9, r6)
            java.lang.String r6 = "storyly-notification-outlink"
            r5.putExtra(r6, r1)
            int r1 = r5.getFlags()
            r1 = r1 | 32
            r5.setFlags(r1)
        L_0x018f:
            if (r5 != 0) goto L_0x0192
            goto L_0x01b3
        L_0x0192:
            r1 = 1000(0x3e8, float:1.401E-42)
            long r12 = (long) r1
            long r10 = r10 * r12
            android.content.Context r1 = r7.f6345a
            int r6 = java.lang.Integer.parseInt(r8)
            r8 = 134217728(0x8000000, float:3.85186E-34)
            int r8 = com.appsamurai.storyly.util.notification.b.a(r8)
            android.app.PendingIntent r1 = android.app.PendingIntent.getBroadcast(r1, r6, r5, r8)
            android.content.Context r5 = r7.f6345a
            java.lang.Object r3 = r5.getSystemService(r3)
            if (r3 == 0) goto L_0x01d2
            android.app.AlarmManager r3 = (android.app.AlarmManager) r3
            r3.setAndAllowWhileIdle(r2, r10, r1)
        L_0x01b3:
            kotlin.jvm.functions.Function5 r12 = r20.getOnUserReaction$storyly_release()
            com.appsamurai.storyly.analytics.a r13 = com.appsamurai.storyly.analytics.a.CountdownReminderAdded
            com.appsamurai.storyly.data.b0 r14 = r20.getStorylyLayerItem$storyly_release()
            r16 = 0
            r17 = 0
            r15 = 0
            r12.invoke(r13, r14, r15, r16, r17)
            r0.a((boolean) r4)
        L_0x01c8:
            android.widget.Button r1 = r0.f5861m
            int r0 = r20.getAlarmImage()
            r1.setBackgroundResource(r0)
            return
        L_0x01d2:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "null cannot be cast to non-null type android.app.AlarmManager"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.m.a(com.appsamurai.storyly.storylypresenter.storylylayer.m, android.view.View):void");
    }

    public static final void a(m mVar) {
        m mVar2 = mVar;
        Intrinsics.checkNotNullParameter(mVar2, "this$0");
        Rect rect = new Rect();
        mVar2.f5858j.getGlobalVisibleRect(rect);
        int width = (rect.width() / 2) + rect.left;
        int height = (rect.height() / 2) + rect.top;
        com.appsamurai.storyly.util.animation.b bVar = mVar2.f5865q;
        bVar.getClass();
        com.appsamurai.storyly.util.animation.c cVar = new com.appsamurai.storyly.util.animation.c(bVar);
        List<com.appsamurai.storyly.config.styling.a> listOf = CollectionsKt.listOf(com.appsamurai.storyly.config.styling.a.COLOR_F26645, com.appsamurai.storyly.config.styling.a.COLOR_FFC75C, com.appsamurai.storyly.config.styling.a.COLOR_7AC7A3, com.appsamurai.storyly.config.styling.a.COLOR_4DC2D9, com.appsamurai.storyly.config.styling.a.COLOR_94638C);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10));
        for (com.appsamurai.storyly.config.styling.a aVar : listOf) {
            arrayList.add(Integer.valueOf(Color.parseColor(aVar.f3589a)));
        }
        Intrinsics.checkNotNullParameter(arrayList, "colors");
        cVar.f6273d = CollectionsKt.toIntArray(arrayList);
        cVar.f6272c.f6306b = Math.toRadians(0.0d);
        cVar.f6272c.f6307c = Double.valueOf(Math.toRadians(359.0d));
        com.appsamurai.storyly.util.animation.modules.b bVar2 = cVar.f6272c;
        bVar2.f6308d = 4.0f;
        Float valueOf = Float.valueOf(7.0f);
        Intrinsics.checkNotNull(valueOf);
        bVar2.f6309e = valueOf;
        com.appsamurai.storyly.util.animation.models.a aVar2 = cVar.f6276g;
        aVar2.f6292a = true;
        aVar2.f6293b = ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS;
        com.appsamurai.storyly.util.animation.models.b[] bVarArr = {b.C0056b.f6298a, b.a.f6296a};
        Intrinsics.checkNotNullParameter(bVarArr, "shapes");
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < 2; i3++) {
            com.appsamurai.storyly.util.animation.models.b bVar3 = bVarArr[i3];
            if (bVar3 != null) {
                arrayList2.add(bVar3);
            }
        }
        Object[] array = arrayList2.toArray(new com.appsamurai.storyly.util.animation.models.b[0]);
        if (array != null) {
            cVar.f6275f = (com.appsamurai.storyly.util.animation.models.b[]) array;
            com.appsamurai.storyly.util.animation.models.c[] cVarArr = {new com.appsamurai.storyly.util.animation.models.c(10, 5.0f), new com.appsamurai.storyly.util.animation.models.c(12, 6.0f)};
            Intrinsics.checkNotNullParameter(cVarArr, "possibleSizes");
            ArrayList arrayList3 = new ArrayList();
            for (int i4 = 0; i4 < 2; i4++) {
                com.appsamurai.storyly.util.animation.models.c cVar2 = cVarArr[i4];
                if (cVar2 != null) {
                    arrayList3.add(cVar2);
                }
            }
            Object[] array2 = arrayList3.toArray(new com.appsamurai.storyly.util.animation.models.c[0]);
            if (array2 != null) {
                com.appsamurai.storyly.util.animation.models.c[] cVarArr2 = (com.appsamurai.storyly.util.animation.models.c[]) array2;
                cVar.f6274e = cVarArr2;
                com.appsamurai.storyly.util.animation.modules.a aVar3 = cVar.f6271b;
                aVar3.f6303a = (float) width;
                aVar3.f6304b = (float) height;
                com.appsamurai.storyly.util.animation.emitters.a aVar4 = new com.appsamurai.storyly.util.animation.emitters.a();
                aVar4.f6278b = 120;
                aVar4.f6279c = false;
                com.appsamurai.storyly.util.animation.emitters.c cVar3 = new com.appsamurai.storyly.util.animation.emitters.c(aVar3, cVar.f6272c, cVarArr2, cVar.f6275f, cVar.f6273d, cVar.f6276g, aVar4);
                Intrinsics.checkNotNullParameter(cVar3, "<set-?>");
                cVar.f6277h = cVar3;
                com.appsamurai.storyly.util.animation.b bVar4 = cVar.f6270a;
                bVar4.getClass();
                Intrinsics.checkNotNullParameter(cVar, "particleSystem");
                bVar4.f6266a.add(cVar);
                com.appsamurai.storyly.util.animation.listeners.a aVar5 = bVar4.f6268c;
                if (aVar5 != null) {
                    aVar5.a(bVar4, cVar, bVar4.f6266a.size());
                }
                bVar4.invalidate();
                Unit unit = Unit.INSTANCE;
                mVar2.f5866r = cVar;
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
