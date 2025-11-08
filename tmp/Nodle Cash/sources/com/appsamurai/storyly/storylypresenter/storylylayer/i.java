package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.motion.widget.Key;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.b0;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class i extends o1 {
    @NotNull

    /* renamed from: A  reason: collision with root package name */
    public final Float[] f5766A;

    /* renamed from: B  reason: collision with root package name */
    public final float f5767B;

    /* renamed from: C  reason: collision with root package name */
    public final int f5768C;

    /* renamed from: D  reason: collision with root package name */
    public final float f5769D;

    /* renamed from: E  reason: collision with root package name */
    public final int f5770E;
    @Nullable
    public AnimatorSet F;
    @Nullable
    public AnimatorSet G;

    /* renamed from: H  reason: collision with root package name */
    public boolean f5771H;
    @NotNull

    /* renamed from: I  reason: collision with root package name */
    public final Lazy f5772I;
    @NotNull

    /* renamed from: J  reason: collision with root package name */
    public final Lazy f5773J;
    @NotNull
    public final Lazy K;
    @NotNull

    /* renamed from: L  reason: collision with root package name */
    public final Lazy f5774L;
    @NotNull
    public final Lazy M;
    @NotNull

    /* renamed from: N  reason: collision with root package name */
    public final Lazy f5775N;
    @NotNull
    public final Lazy O;
    @NotNull
    public final Lazy P;
    @NotNull

    /* renamed from: Q  reason: collision with root package name */
    public final Lazy f5776Q;
    @NotNull
    public final Lazy R;
    @NotNull

    /* renamed from: S  reason: collision with root package name */
    public final Lazy f5777S;
    @NotNull

    /* renamed from: T  reason: collision with root package name */
    public final Lazy f5778T;
    @NotNull

    /* renamed from: U  reason: collision with root package name */
    public final Lazy f5779U;
    @NotNull

    /* renamed from: V  reason: collision with root package name */
    public final Lazy f5780V;
    @NotNull

    /* renamed from: W  reason: collision with root package name */
    public final Lazy f5781W;
    @NotNull

    /* renamed from: a0  reason: collision with root package name */
    public final Lazy f5782a0;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f5783g;

    /* renamed from: h  reason: collision with root package name */
    public com.appsamurai.storyly.data.q f5784h;

    /* renamed from: i  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f5785i;

    /* renamed from: j  reason: collision with root package name */
    public Function0<Unit> f5786j;

    /* renamed from: k  reason: collision with root package name */
    public Function0<Unit> f5787k;

    /* renamed from: l  reason: collision with root package name */
    public Function0<Bitmap> f5788l;

    /* renamed from: m  reason: collision with root package name */
    public final float f5789m = 0.82f;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Float[] f5790n = {Float.valueOf(15.51f), Float.valueOf(19.09f), Float.valueOf(23.87f)};

    /* renamed from: o  reason: collision with root package name */
    public final float f5791o = 0.75f;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Float[] f5792p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Float[] f5793q;
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public final Float[] f5794r;
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    public final Float[] f5795s;
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public final Float[] f5796t;
    @NotNull

    /* renamed from: u  reason: collision with root package name */
    public final Float[] f5797u;
    @NotNull

    /* renamed from: v  reason: collision with root package name */
    public final Float[] f5798v;
    @NotNull

    /* renamed from: w  reason: collision with root package name */
    public final Float[] f5799w;
    @NotNull

    /* renamed from: x  reason: collision with root package name */
    public final Float[] f5800x;
    @NotNull

    /* renamed from: y  reason: collision with root package name */
    public final Float[] f5801y;
    @NotNull

    /* renamed from: z  reason: collision with root package name */
    public final Float[] f5802z;

    public static final class a extends Lambda implements Function0<Boolean> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i f5803a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(i iVar) {
            super(0);
            this.f5803a = iVar;
        }

        public Object invoke() {
            this.f5803a.a(false);
            return Boolean.TRUE;
        }
    }

    public static final class b implements TextWatcher {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ com.appsamurai.storyly.util.ui.g f5804a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ i f5805b;

        public b(com.appsamurai.storyly.util.ui.g gVar, i iVar) {
            this.f5804a = gVar;
            this.f5805b = iVar;
        }

        public void afterTextChanged(@Nullable Editable editable) {
            if (this.f5804a.getLineCount() > this.f5805b.f5768C && editable != null) {
                editable.delete(this.f5804a.getSelectionEnd() - 1, this.f5804a.getSelectionStart());
            }
            Editable text = this.f5804a.getText();
            if ((text == null ? 0 : text.length()) > 0) {
                i.k(this.f5805b);
            } else {
                i.h(this.f5805b);
            }
        }

        public void beforeTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
        }

        public void onTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
        }
    }

    public static final class c extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5806a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5806a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5806a);
            relativeLayout.setId(View.generateViewId());
            relativeLayout.setClipChildren(false);
            relativeLayout.setClipToPadding(false);
            return relativeLayout;
        }
    }

    public static final class d extends Lambda implements Function0<Handler> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5807a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5807a = context;
        }

        public Object invoke() {
            return new Handler(this.f5807a.getMainLooper());
        }
    }

    public static final class e extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5808a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f5808a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5808a);
            relativeLayout.setId(View.generateViewId());
            relativeLayout.setClipChildren(false);
            relativeLayout.setClipToPadding(false);
            return relativeLayout;
        }
    }

    public static final class f extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5809a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f5809a = context;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f5809a);
            frameLayout.setId(View.generateViewId());
            frameLayout.setClipChildren(false);
            frameLayout.setClipToPadding(false);
            return frameLayout;
        }
    }

    public static final class g extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5810a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f5810a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5810a);
            relativeLayout.setId(View.generateViewId());
            relativeLayout.setClipChildren(false);
            relativeLayout.setClipToPadding(false);
            return relativeLayout;
        }
    }

    public static final class h implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i f5811a;

        public h(i iVar) {
            this.f5811a = iVar;
        }

        public final void run() {
            this.f5811a.getPopupEditTextView().clearFocus();
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylylayer.i$i  reason: collision with other inner class name */
    public static final class C0053i implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i f5812a;

        public C0053i(i iVar) {
            this.f5812a = iVar;
        }

        public final void run() {
            this.f5812a.getOnUserInteractionEnded$storyly_release().invoke();
        }
    }

    public static final class j implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i f5813a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ boolean f5814b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ float f5815c;

        public j(i iVar, boolean z2, float f2) {
            this.f5813a = iVar;
            this.f5814b = z2;
            this.f5815c = f2;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
            i iVar = this.f5813a;
            iVar.f5771H = false;
            iVar.getPopupView().setVisibility(4);
            this.f5813a.getPopupBackgroundView().setVisibility(4);
            if (this.f5814b) {
                this.f5813a.getPopupEditTextView().setText("");
                RelativeLayout g2 = this.f5813a.getPopupView();
                g2.setTranslationY(g2.getTranslationY() - this.f5815c);
            }
            i.i(this.f5813a);
            this.f5813a.getCommentHandler().postDelayed(new C0053i(this.f5813a), 100);
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
        }
    }

    public static final class k implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i f5816a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ AnimatorSet f5817b;

        public k(i iVar, AnimatorSet animatorSet) {
            this.f5816a = iVar;
            this.f5817b = animatorSet;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
            this.f5816a.getPopupBackgroundView().setEnabled(false);
            this.f5816a.getCommentHandler().postDelayed(new h(this.f5816a), this.f5817b.getStartDelay());
        }
    }

    public static final class l extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5818a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public l(Context context) {
            super(0);
            this.f5818a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5818a);
            linearLayout.setId(View.generateViewId());
            linearLayout.setClipChildren(false);
            linearLayout.setClipToPadding(false);
            linearLayout.setGravity(17);
            return linearLayout;
        }
    }

    public static final class m extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5819a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public m(Context context) {
            super(0);
            this.f5819a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5819a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setMaxLines(1);
            appCompatTextView.setMinLines(1);
            appCompatTextView.setGravity(17);
            appCompatTextView.setTextAlignment(1);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setHorizontallyScrolling(false);
            return appCompatTextView;
        }
    }

    public static final class n extends Lambda implements Function0<com.appsamurai.storyly.util.ui.blur.c> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5820a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public n(Context context) {
            super(0);
            this.f5820a = context;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.util.ui.blur.c(this.f5820a, (AttributeSet) null, 0, 0, 14);
        }
    }

    public static final class o extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5821a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public o(Context context) {
            super(0);
            this.f5821a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5821a);
            relativeLayout.setId(View.generateViewId());
            relativeLayout.setVisibility(8);
            return relativeLayout;
        }
    }

    public static final class p extends Lambda implements Function0<com.appsamurai.storyly.util.ui.g> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5822a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ i f5823b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public p(Context context, i iVar) {
            super(0);
            this.f5822a = context;
            this.f5823b = iVar;
        }

        public Object invoke() {
            com.appsamurai.storyly.util.ui.g gVar = new com.appsamurai.storyly.util.ui.g(this.f5822a);
            i iVar = this.f5823b;
            gVar.setId(View.generateViewId());
            gVar.setMinLines(2);
            gVar.setMaxLines(iVar.f5768C);
            gVar.setGravity(8388659);
            gVar.setTextAlignment(1);
            gVar.setIncludeFontPadding(false);
            gVar.setHorizontallyScrolling(false);
            com.appsamurai.storyly.util.d.a(gVar);
            gVar.setCursorVisible(true);
            gVar.setFocusable(true);
            gVar.setFocusableInTouchMode(true);
            gVar.setImeOptions(1073741824);
            gVar.setInputType(131073);
            return gVar;
        }
    }

    public static final class q extends Lambda implements Function0<com.appsamurai.storyly.util.ui.h> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5824a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public q(Context context) {
            super(0);
            this.f5824a = context;
        }

        public Object invoke() {
            com.appsamurai.storyly.util.ui.h hVar = new com.appsamurai.storyly.util.ui.h(this.f5824a);
            hVar.setId(View.generateViewId());
            hVar.setScrollable(false);
            hVar.setFillViewport(true);
            hVar.setVerticalScrollBarEnabled(false);
            hVar.setHorizontalScrollBarEnabled(false);
            hVar.setOverScrollMode(2);
            com.appsamurai.storyly.util.ui.l.b(hVar);
            return hVar;
        }
    }

    public static final class r extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5825a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public r(Context context) {
            super(0);
            this.f5825a = context;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f5825a);
            frameLayout.setId(View.generateViewId());
            frameLayout.setVisibility(8);
            return frameLayout;
        }
    }

    public static final class s extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5826a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public s(Context context) {
            super(0);
            this.f5826a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5826a);
            imageView.setId(View.generateViewId());
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return imageView;
        }
    }

    public static final class t extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5827a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public t(Context context) {
            super(0);
            this.f5827a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5827a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setMinLines(1);
            appCompatTextView.setMaxLines(2);
            appCompatTextView.setGravity(17);
            appCompatTextView.setTextAlignment(1);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setHorizontallyScrolling(false);
            com.appsamurai.storyly.util.d.a(appCompatTextView);
            return appCompatTextView;
        }
    }

    public static final class u extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5828a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public u(Context context) {
            super(0);
            this.f5828a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5828a);
            relativeLayout.setId(View.generateViewId());
            relativeLayout.setClipChildren(false);
            relativeLayout.setClipToPadding(false);
            relativeLayout.setVisibility(8);
            return relativeLayout;
        }
    }

    public static final class v extends Lambda implements Function1<Boolean, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i f5829a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public v(i iVar) {
            super(1);
            this.f5829a = iVar;
        }

        public Object invoke(Object obj) {
            if (((Boolean) obj).booleanValue() && this.f5829a.getPopupView().getVisibility() == 0) {
                i.a(this.f5829a);
            }
            return Unit.INSTANCE;
        }
    }

    public static final class w implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i f5830a;

        public w(i iVar) {
            this.f5830a = iVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
            AnimatorSet animatorSet = this.f5830a.F;
            if (animatorSet != null) {
                animatorSet.removeAllListeners();
            }
            i iVar = this.f5830a;
            iVar.F = null;
            iVar.getPopupBackgroundView().setEnabled(true);
            i iVar2 = this.f5830a;
            iVar2.f5771H = false;
            String T2 = StringsKt__StringsJVMKt.replace$default(StringsKt__StringsKt.trim((CharSequence) String.valueOf(iVar2.getPopupEditTextView().getText())).toString(), "\n", StringUtils.SPACE, false, 4, (Object) null);
            Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> onUserReaction$storyly_release = this.f5830a.getOnUserReaction$storyly_release();
            com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.CommentSent;
            b0 storylyLayerItem$storyly_release = this.f5830a.getStorylyLayerItem$storyly_release();
            b0 storylyLayerItem$storyly_release2 = this.f5830a.getStorylyLayerItem$storyly_release();
            storylyLayerItem$storyly_release2.getClass();
            Intrinsics.checkNotNullParameter(T2, "userResponse");
            StoryComponent a2 = storylyLayerItem$storyly_release2.f3615j.a(storylyLayerItem$storyly_release2, T2);
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            JsonElementBuildersKt.put(jsonObjectBuilder, "activity", T2);
            Unit unit = Unit.INSTANCE;
            onUserReaction$storyly_release.invoke(aVar, storylyLayerItem$storyly_release, a2, jsonObjectBuilder.build(), new v(this.f5830a));
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
        }
    }

    public static final class x implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i f5831a;

        public x(i iVar) {
            this.f5831a = iVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
            this.f5831a.getPopupSendButton().setEnabled(false);
            this.f5831a.getPopupBackgroundView().setEnabled(false);
            this.f5831a.getPopupSendImage().setImageDrawable(AppCompatResources.getDrawable(this.f5831a.getContext(), R.drawable.st_comment_icon_load));
        }
    }

    public static final class y extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5832a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public y(Context context) {
            super(0);
            this.f5832a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f5832a);
            appCompatTextView.setId(View.generateViewId());
            appCompatTextView.setMinLines(2);
            appCompatTextView.setMaxLines(2);
            appCompatTextView.setGravity(17);
            appCompatTextView.setTextAlignment(1);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setHorizontallyScrolling(false);
            com.appsamurai.storyly.util.d.a(appCompatTextView);
            return appCompatTextView;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5783g = storylyConfig;
        Float valueOf = Float.valueOf(0.85f);
        this.f5792p = new Float[]{valueOf, Float.valueOf(0.88f), valueOf};
        this.f5793q = new Float[]{Float.valueOf(205.0f), Float.valueOf(256.0f), Float.valueOf(320.0f)};
        this.f5794r = new Float[]{Float.valueOf(122.0f), Float.valueOf(141.0f), Float.valueOf(178.75f)};
        this.f5795s = new Float[]{Float.valueOf(77.0f), Float.valueOf(93.4f), Float.valueOf(118.75f)};
        this.f5796t = new Float[]{Float.valueOf(17.92f), Float.valueOf(22.38f), Float.valueOf(27.97f)};
        Float valueOf2 = Float.valueOf(1.0f);
        this.f5797u = new Float[]{valueOf2, Float.valueOf(1.4f), Float.valueOf(1.75f)};
        Float valueOf3 = Float.valueOf(13.0f);
        this.f5798v = new Float[]{valueOf3, Float.valueOf(17.0f), Float.valueOf(21.5f)};
        Float valueOf4 = Float.valueOf(16.0f);
        Float valueOf5 = Float.valueOf(20.0f);
        this.f5799w = new Float[]{valueOf3, valueOf4, valueOf5};
        this.f5800x = new Float[]{valueOf3, valueOf4, valueOf5};
        this.f5801y = new Float[]{Float.valueOf(32.0f), Float.valueOf(38.0f), Float.valueOf(48.0f)};
        this.f5802z = new Float[]{Float.valueOf(35.0f), Float.valueOf(40.0f), Float.valueOf(52.0f)};
        this.f5766A = new Float[]{valueOf2, Float.valueOf(1.6f), Float.valueOf(2.0f)};
        this.f5767B = 296.0f;
        this.f5768C = 6;
        this.f5769D = 15.0f;
        this.f5770E = Color.parseColor("#1e1e1e66");
        this.f5772I = LazyKt.lazy(new d(context));
        this.f5773J = LazyKt.lazy(new e(context));
        this.K = LazyKt.lazy(new f(context));
        this.f5774L = LazyKt.lazy(new g(context));
        this.M = LazyKt.lazy(new c(context));
        this.f5775N = LazyKt.lazy(new l(context));
        this.O = LazyKt.lazy(new y(context));
        this.P = LazyKt.lazy(new m(context));
        this.f5776Q = LazyKt.lazy(new q(context));
        this.R = LazyKt.lazy(new o(context));
        this.f5777S = LazyKt.lazy(new n(context));
        this.f5778T = LazyKt.lazy(new u(context));
        this.f5779U = LazyKt.lazy(new t(context));
        this.f5780V = LazyKt.lazy(new p(context, this));
        this.f5781W = LazyKt.lazy(new r(context));
        this.f5782a0 = LazyKt.lazy(new s(context));
        com.appsamurai.storyly.util.ui.l.b(this);
    }

    private final RelativeLayout getBackgroundContainerView() {
        return (RelativeLayout) this.M.getValue();
    }

    /* access modifiers changed from: private */
    public final Handler getCommentHandler() {
        return (Handler) this.f5772I.getValue();
    }

    private final RelativeLayout getCommentView() {
        return (RelativeLayout) this.f5773J.getValue();
    }

    private final FrameLayout getContainerBorderView() {
        return (FrameLayout) this.K.getValue();
    }

    private final RelativeLayout getContainerView() {
        return (RelativeLayout) this.f5774L.getValue();
    }

    private final LinearLayout getInputContainerView() {
        return (LinearLayout) this.f5775N.getValue();
    }

    private final AppCompatTextView getInputTextView() {
        return (AppCompatTextView) this.P.getValue();
    }

    private final RelativeLayout getParentStorylyGroupView() {
        FrameLayout parentView = getParentView();
        ViewParent parent = parentView == null ? null : parentView.getParent();
        if (parent instanceof RelativeLayout) {
            return (RelativeLayout) parent;
        }
        return null;
    }

    private final FrameLayout getParentView() {
        ViewParent parent = getParent();
        if (parent instanceof FrameLayout) {
            return (FrameLayout) parent;
        }
        return null;
    }

    private final com.appsamurai.storyly.util.ui.blur.c getPopupBackgroundBlurView() {
        return (com.appsamurai.storyly.util.ui.blur.c) this.f5777S.getValue();
    }

    /* access modifiers changed from: private */
    public final RelativeLayout getPopupBackgroundView() {
        return (RelativeLayout) this.R.getValue();
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.util.ui.g getPopupEditTextView() {
        return (com.appsamurai.storyly.util.ui.g) this.f5780V.getValue();
    }

    private final com.appsamurai.storyly.util.ui.h getPopupHolderView() {
        return (com.appsamurai.storyly.util.ui.h) this.f5776Q.getValue();
    }

    /* access modifiers changed from: private */
    public final FrameLayout getPopupSendButton() {
        return (FrameLayout) this.f5781W.getValue();
    }

    /* access modifiers changed from: private */
    public final ImageView getPopupSendImage() {
        return (ImageView) this.f5782a0.getValue();
    }

    private final AppCompatTextView getPopupTextView() {
        return (AppCompatTextView) this.f5779U.getValue();
    }

    /* access modifiers changed from: private */
    public final RelativeLayout getPopupView() {
        return (RelativeLayout) this.f5778T.getValue();
    }

    private final AppCompatTextView getTitleTextView() {
        return (AppCompatTextView) this.O.getValue();
    }

    public static final void h(i iVar) {
        iVar.getPopupSendButton().setVisibility(8);
    }

    public static final void i(i iVar) {
        AnimatorSet animatorSet = iVar.F;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = iVar.G;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
        iVar.F = null;
        iVar.G = null;
        ImageView popupSendImage = iVar.getPopupSendImage();
        popupSendImage.setRotation(0.0f);
        popupSendImage.setImageDrawable(AppCompatResources.getDrawable(iVar.getContext(), R.drawable.st_comment_icon_send));
        FrameLayout popupSendButton = iVar.getPopupSendButton();
        popupSendButton.getLayoutParams().width = -1;
        popupSendButton.requestLayout();
    }

    public static final void j(i iVar) {
        Bitmap invoke = iVar.getOnExtractBackgroundBitmap$storyly_release().invoke();
        if (invoke != null) {
            iVar.getPopupBackgroundView().setBackground(new BitmapDrawable(iVar.getContext().getResources(), invoke));
        }
    }

    public static final void k(i iVar) {
        iVar.getPopupSendButton().setVisibility(0);
    }

    public void a(@NotNull d dVar) {
        float a2;
        float f2;
        com.appsamurai.storyly.data.c cVar;
        com.appsamurai.storyly.data.q qVar;
        com.appsamurai.storyly.data.q qVar2;
        int i3;
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        c();
        float b3 = dVar.b();
        float a3 = dVar.a();
        float f3 = (float) 100;
        float f4 = (getStorylyLayerItem$storyly_release().f3609d / f3) * b3;
        float f5 = (getStorylyLayerItem$storyly_release().f3610e / f3) * a3;
        com.appsamurai.storyly.data.q qVar3 = this.f5784h;
        if (qVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar3 = null;
        }
        float a4 = qVar3.a(this.f5793q);
        com.appsamurai.storyly.data.q qVar4 = this.f5784h;
        if (qVar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar4 = null;
        }
        if (qVar4.a()) {
            com.appsamurai.storyly.data.q qVar5 = this.f5784h;
            if (qVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar5 = null;
            }
            a2 = qVar5.a(this.f5794r);
        } else {
            com.appsamurai.storyly.data.q qVar6 = this.f5784h;
            if (qVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar6 = null;
            }
            a2 = qVar6.a(this.f5795s);
        }
        float f6 = a2;
        FrameLayout.LayoutParams a5 = a(new FrameLayout.LayoutParams(MathKt.roundToInt(f4), MathKt.roundToInt(f5)), getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, dVar.c(), dVar.d());
        setLayoutParams(a5);
        com.appsamurai.storyly.data.q qVar7 = this.f5784h;
        if (qVar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar7 = null;
        }
        if (!qVar7.b()) {
            setOnClickListener(new G0.h(this, 1));
        }
        addView(getCommentView(), new FrameLayout.LayoutParams(-1, -1));
        com.appsamurai.storyly.data.q qVar8 = this.f5784h;
        if (qVar8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar8 = null;
        }
        float a6 = qVar8.a(this.f5799w);
        Lazy lazy = com.appsamurai.storyly.util.m.f6342a;
        int i4 = (int) ((a6 * f4) / a4);
        com.appsamurai.storyly.data.q qVar9 = this.f5784h;
        if (qVar9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar9 = null;
        }
        int a7 = (int) ((qVar9.a(this.f5800x) * f5) / f6);
        com.appsamurai.storyly.data.q qVar10 = this.f5784h;
        if (qVar10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar10 = null;
        }
        int a8 = (int) ((qVar10.a(this.f5798v) * f5) / f6);
        int roundToInt = MathKt.roundToInt(f5);
        com.appsamurai.storyly.data.q qVar11 = this.f5784h;
        if (qVar11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar11 = null;
        }
        int roundToInt2 = MathKt.roundToInt((qVar11.a(this.f5797u) * f4) / a4);
        com.appsamurai.storyly.data.q qVar12 = this.f5784h;
        if (qVar12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar12 = null;
        }
        float a9 = (qVar12.a(this.f5796t) * f4) / a4;
        com.appsamurai.storyly.data.q qVar13 = this.f5784h;
        if (qVar13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar13 = null;
        }
        int roundToInt3 = MathKt.roundToInt((qVar13.a(this.f5802z) * f5) / f6);
        com.appsamurai.storyly.data.q qVar14 = this.f5784h;
        if (qVar14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar14 = null;
        }
        if (qVar14.b()) {
            com.appsamurai.storyly.data.q qVar15 = this.f5784h;
            if (qVar15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar15 = null;
            }
            Float f7 = qVar15.f4138s;
            if (f7 != null) {
                roundToInt3 = MathKt.roundToInt((f7.floatValue() / f3) * a3) - roundToInt2;
                Unit unit = Unit.INSTANCE;
            }
        }
        int i5 = roundToInt2 * 2;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a5.width - i5, roundToInt - i5);
        layoutParams.addRule(10);
        layoutParams.addRule(14);
        layoutParams.topMargin = roundToInt2;
        Unit unit2 = Unit.INSTANCE;
        RelativeLayout backgroundContainerView = getBackgroundContainerView();
        com.appsamurai.storyly.data.q qVar16 = this.f5784h;
        if (qVar16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar16 = null;
        }
        com.appsamurai.storyly.data.c cVar2 = qVar16.f4130k;
        if (cVar2 == null) {
            f2 = a4;
            cVar2 = new com.appsamurai.storyly.data.c(com.appsamurai.storyly.util.f.a(qVar16.f().f3624a, qVar16.f4141v));
        } else {
            f2 = a4;
        }
        int i6 = cVar2.f3624a;
        com.appsamurai.storyly.data.q qVar17 = this.f5784h;
        if (qVar17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar17 = null;
        }
        backgroundContainerView.setBackground(new a(a9, a8, roundToInt2, i6, qVar17.c().f3624a));
        RelativeLayout containerView = getContainerView();
        com.appsamurai.storyly.data.q qVar18 = this.f5784h;
        if (qVar18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar18 = null;
        }
        com.appsamurai.storyly.data.c cVar3 = qVar18.f4130k;
        if (cVar3 == null) {
            cVar3 = new com.appsamurai.storyly.data.c(com.appsamurai.storyly.util.f.a(qVar18.f().f3624a, qVar18.f4141v));
        }
        int i7 = cVar3.f3624a;
        com.appsamurai.storyly.data.q qVar19 = this.f5784h;
        if (qVar19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar19 = null;
        }
        if (qVar19.b()) {
            com.appsamurai.storyly.data.q qVar20 = this.f5784h;
            if (qVar20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar20 = null;
            }
            cVar = qVar20.d();
        } else {
            com.appsamurai.storyly.data.q qVar21 = this.f5784h;
            if (qVar21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar21 = null;
            }
            cVar = qVar21.c();
        }
        containerView.setBackground(new a(a9, a8, roundToInt2, i7, cVar.f3624a));
        com.appsamurai.storyly.data.q qVar22 = this.f5784h;
        if (qVar22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar22 = null;
        }
        if (qVar22.b()) {
            getCommentView().addView(getBackgroundContainerView(), layoutParams);
        }
        getCommentView().addView(getContainerView(), layoutParams);
        float a10 = a(true, f6, f5);
        com.appsamurai.storyly.data.q qVar23 = this.f5784h;
        if (qVar23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar23 = null;
        }
        if (qVar23.a()) {
            com.appsamurai.storyly.data.q qVar24 = this.f5784h;
            if (qVar24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar24 = null;
            }
            float a11 = (qVar24.a(this.f5801y) * f5) / f6;
            com.appsamurai.storyly.data.q qVar25 = this.f5784h;
            if (qVar25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar25 = null;
            }
            if (qVar25.b()) {
                i3 = ((int) f5) - roundToInt3;
            } else {
                i3 = MathKt.roundToInt(Math.max(2.0f * a10, a11));
            }
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, i3);
            layoutParams2.addRule(10);
            layoutParams2.addRule(14);
            com.appsamurai.storyly.data.q qVar26 = this.f5784h;
            if (qVar26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar26 = null;
            }
            if (!qVar26.b()) {
                int i8 = roundToInt2 + i4;
                layoutParams2.setMargins(i8, roundToInt2 + a7, i8, 0);
            } else {
                layoutParams2.setMargins(roundToInt2, roundToInt2, roundToInt2, 0);
            }
            AppCompatTextView titleTextView = getTitleTextView();
            com.appsamurai.storyly.data.q qVar27 = this.f5784h;
            if (qVar27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar27 = null;
            }
            titleTextView.setText(qVar27.f4122c);
            titleTextView.setLineHeight((int) a10);
            float lineHeight = (float) titleTextView.getLineHeight();
            com.appsamurai.storyly.data.q qVar28 = this.f5784h;
            if (qVar28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar28 = null;
            }
            titleTextView.setTextSize(0, lineHeight * (qVar28.b() ? this.f5791o : this.f5789m));
            com.appsamurai.storyly.data.q qVar29 = this.f5784h;
            if (qVar29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar29 = null;
            }
            titleTextView.setBackground(com.appsamurai.storyly.util.ui.b.a(titleTextView, qVar29.c().f3624a, a9, a9, 0.0f, 0.0f, (Integer) null, 0, 96));
            titleTextView.setTypeface(this.f5783g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
            com.appsamurai.storyly.data.q qVar30 = this.f5784h;
            if (qVar30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar30 = null;
            }
            boolean z2 = !qVar30.b();
            com.appsamurai.storyly.data.q qVar31 = this.f5784h;
            if (qVar31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar31 = null;
            }
            com.appsamurai.storyly.util.c.a(titleTextView, z2, qVar31.f4126g);
            com.appsamurai.storyly.data.q qVar32 = this.f5784h;
            if (qVar32 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar32 = null;
            }
            titleTextView.setTextColor(qVar32.f().f3624a);
            com.appsamurai.storyly.data.q qVar33 = this.f5784h;
            if (qVar33 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar33 = null;
            }
            if (qVar33.b()) {
                titleTextView.setPadding(i4, 0, i4, 0);
            }
            titleTextView.setLineSpacing(0.0f, 1.0f);
            getContainerView().addView(getTitleTextView(), layoutParams2);
        }
        float a12 = a(false, f6, f5);
        com.appsamurai.storyly.data.q qVar34 = this.f5784h;
        if (qVar34 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar34 = null;
        }
        float a13 = (f4 * qVar34.a(this.f5766A)) / f2;
        float f8 = ((float) roundToInt3) * 0.5f;
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, roundToInt3);
        layoutParams3.setMargins(i4, 0, i4, 0);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams4.addRule(3, getTitleTextView().getId());
        layoutParams4.addRule(12);
        com.appsamurai.storyly.data.q qVar35 = this.f5784h;
        if (qVar35 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar35 = null;
        }
        if (!qVar35.b()) {
            layoutParams4.setMargins(0, 0, 0, a8);
        }
        AppCompatTextView inputTextView = getInputTextView();
        inputTextView.setLineHeight((int) a12);
        inputTextView.setTypeface(this.f5783g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        com.appsamurai.storyly.data.q qVar36 = this.f5784h;
        if (qVar36 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar36 = null;
        }
        inputTextView.setTextColor(qVar36.e().f3624a);
        com.appsamurai.storyly.data.q qVar37 = this.f5784h;
        if (qVar37 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar37 = null;
        }
        if (qVar37.b()) {
            com.appsamurai.storyly.data.q qVar38 = this.f5784h;
            if (qVar38 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar38 = null;
            }
            boolean b4 = qVar38.b();
            com.appsamurai.storyly.data.q qVar39 = this.f5784h;
            if (qVar39 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar39 = null;
            }
            com.appsamurai.storyly.util.c.a(inputTextView, b4, qVar39.f4126g);
            com.appsamurai.storyly.data.q qVar40 = this.f5784h;
            if (qVar40 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar40 = null;
            }
            inputTextView.setText(qVar40.f4137r);
            inputTextView.setLineSpacing(0.0f, 1.2f);
            AppCompatTextView inputTextView2 = getInputTextView();
            com.appsamurai.storyly.data.q qVar41 = this.f5784h;
            if (qVar41 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar2 = null;
            } else {
                qVar2 = qVar41;
            }
            Integer num = qVar2.f4139t;
            inputTextView2.setMaxLines(num == null ? Math.max(((roundToInt3 - a8) - a7) / MathKt.roundToInt(a12), 1) : num.intValue());
            inputTextView.setPadding(0, 0, 0, a8);
        } else {
            com.appsamurai.storyly.data.q qVar42 = this.f5784h;
            if (qVar42 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar42 = null;
            }
            inputTextView.setText(qVar42.f4124e);
            com.appsamurai.storyly.data.q qVar43 = this.f5784h;
            if (qVar43 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar43 = null;
            }
            GradientDrawable a14 = com.appsamurai.storyly.util.ui.b.a(inputTextView, qVar43.d().f3624a, f8, (Integer) null, 0, 12);
            int roundToInt4 = MathKt.roundToInt(a13);
            com.appsamurai.storyly.data.q qVar44 = this.f5784h;
            if (qVar44 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar = null;
            } else {
                qVar = qVar44;
            }
            com.appsamurai.storyly.data.c cVar4 = qVar.f4133n;
            if (cVar4 == null) {
                cVar4 = new com.appsamurai.storyly.data.c(com.appsamurai.storyly.util.f.a(qVar.f().f3624a, qVar.f4143x));
            }
            a14.setStroke(roundToInt4, cVar4.f3624a);
            inputTextView.setBackground(a14);
            inputTextView.setLineSpacing(0.0f, 0.0f);
        }
        inputTextView.setTextSize(0, a12 * this.f5791o);
        getInputContainerView().addView(getInputTextView(), layoutParams3);
        getContainerView().addView(getInputContainerView(), layoutParams4);
        f();
    }

    @NotNull
    public final Function0<Bitmap> getOnExtractBackgroundBitmap$storyly_release() {
        Function0<Bitmap> function0 = this.f5788l;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onExtractBackgroundBitmap");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionEnded$storyly_release() {
        Function0<Unit> function0 = this.f5787k;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionEnded");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionStarted$storyly_release() {
        Function0<Unit> function0 = this.f5786j;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionStarted");
        return null;
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> getOnUserReaction$storyly_release() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f5785i;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        if (z2 && getPopupView().getVisibility() == 0 && !getPopupEditTextView().getHasFocused()) {
            getPopupEditTextView().requestFocus();
        }
    }

    public final void setOnExtractBackgroundBitmap$storyly_release(@NotNull Function0<Bitmap> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5788l = function0;
    }

    public final void setOnUserInteractionEnded$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5787k = function0;
    }

    public final void setOnUserInteractionStarted$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5786j = function0;
    }

    public final void setOnUserReaction$storyly_release(@NotNull Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5) {
        Intrinsics.checkNotNullParameter(function5, "<set-?>");
        this.f5785i = function5;
    }

    public static final void b(i iVar, View view) {
        Intrinsics.checkNotNullParameter(iVar, "this$0");
        iVar.getOnUserInteractionStarted$storyly_release().invoke();
        iVar.getOnUserReaction$storyly_release().invoke(com.appsamurai.storyly.analytics.a.CommentInputFocus, iVar.getStorylyLayerItem$storyly_release(), null, null, null);
        if (iVar.getPopupView().getVisibility() != 0 && !iVar.f5771H) {
            iVar.f5771H = true;
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(iVar.getCommentView(), "alpha", new float[]{0.0f, 0.0f}), ObjectAnimator.ofFloat(iVar.getPopupBackgroundView(), "alpha", new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(iVar.getPopupView(), "translationY", new float[]{(float) iVar.getPopupView().getMeasuredHeight(), 0.0f}), ObjectAnimator.ofFloat(iVar.getPopupView(), "alpha", new float[]{0.0f, 1.0f})});
            animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animatorSet.setDuration(300);
            animatorSet.addListener(new l(iVar));
            animatorSet.addListener(new k(iVar));
            animatorSet.start();
        }
    }

    public void c() {
        removeAllViews();
        getContainerBorderView().removeAllViews();
        getContainerView().removeAllViews();
        RelativeLayout parentStorylyGroupView = getParentStorylyGroupView();
        if (parentStorylyGroupView != null) {
            parentStorylyGroupView.removeView(getPopupHolderView());
        }
        getPopupHolderView().removeAllViews();
        getPopupBackgroundView().removeAllViews();
        getPopupBackgroundBlurView().removeAllViews();
        getPopupView().removeAllViews();
    }

    public void d() {
        a(false);
    }

    public final void f() {
        com.appsamurai.storyly.data.q qVar;
        RelativeLayout parentStorylyGroupView = getParentStorylyGroupView();
        if (parentStorylyGroupView != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            getPopupHolderView().setPadding(0, 0, 0, 0);
            Unit unit = Unit.INSTANCE;
            parentStorylyGroupView.addView(getPopupHolderView(), layoutParams);
            RelativeLayout popupBackgroundView = getPopupBackgroundView();
            popupBackgroundView.setOnClickListener(new G0.h(this, 0));
            popupBackgroundView.setPadding(0, 0, 0, 0);
            getPopupHolderView().addView(getPopupBackgroundView(), new RelativeLayout.LayoutParams(-1, -1));
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels);
            com.appsamurai.storyly.util.ui.blur.a aVar = (com.appsamurai.storyly.util.ui.blur.a) getPopupBackgroundBlurView().a(getPopupBackgroundView());
            aVar.f6362e = this.f5769D;
            aVar.a(this.f5770E);
            aVar.b(false);
            getPopupBackgroundView().addView(getPopupBackgroundBlurView(), layoutParams2);
            float f2 = this.f5767B;
            float b3 = getSafeFrame$storyly_release().b();
            Lazy lazy = com.appsamurai.storyly.util.m.f6342a;
            float f3 = (b3 * f2) / 360.0f;
            float f4 = this.f5767B;
            float f5 = (19.0f * f3) / f4;
            float f6 = (16.0f * f3) / f4;
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(MathKt.roundToInt(f3), -2);
            layoutParams3.gravity = 81;
            layoutParams3.bottomMargin = MathKt.roundToInt(((float) layoutParams2.height) * 0.5f);
            RelativeLayout popupView = getPopupView();
            com.appsamurai.storyly.data.q qVar2 = this.f5784h;
            if (qVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar2 = null;
            }
            popupView.setBackground(com.appsamurai.storyly.util.ui.b.a(popupView, qVar2.c().f3624a, f5, (Integer) null, 0, 12));
            int roundToInt = MathKt.roundToInt(f6);
            popupView.setPadding(roundToInt, roundToInt, roundToInt, roundToInt);
            getPopupBackgroundBlurView().addView(getPopupView(), layoutParams3);
            float f7 = this.f5767B;
            float f8 = 24.0f * f3;
            float f9 = f8 / f7;
            float f10 = (8.0f * f3) / f7;
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams4.addRule(10);
            layoutParams4.addRule(14);
            layoutParams4.bottomMargin = MathKt.roundToInt(f10);
            AppCompatTextView popupTextView = getPopupTextView();
            com.appsamurai.storyly.data.q qVar3 = this.f5784h;
            if (qVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar3 = null;
            }
            popupTextView.setText(qVar3.f4122c);
            popupTextView.setLineHeight(MathKt.roundToInt(f9));
            popupTextView.setTextSize(0, ((float) popupTextView.getLineHeight()) * this.f5789m);
            popupTextView.setTypeface(this.f5783g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
            com.appsamurai.storyly.data.q qVar4 = this.f5784h;
            if (qVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar4 = null;
            }
            boolean z2 = qVar4.f4125f;
            com.appsamurai.storyly.data.q qVar5 = this.f5784h;
            if (qVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar5 = null;
            }
            com.appsamurai.storyly.util.c.a(popupTextView, z2, qVar5.f4126g);
            com.appsamurai.storyly.data.q qVar6 = this.f5784h;
            if (qVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar6 = null;
            }
            popupTextView.setTextColor(qVar6.f().f3624a);
            popupTextView.setLineSpacing(0.0f, 1.0f);
            com.appsamurai.storyly.data.q qVar7 = this.f5784h;
            if (qVar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar7 = null;
            }
            popupTextView.setVisibility(qVar7.a() ? 0 : 8);
            popupTextView.setPadding(0, 0, 0, 0);
            getPopupView().addView(getPopupTextView(), layoutParams4);
            float f11 = this.f5767B;
            float f12 = (12.0f * f3) / f11;
            float f13 = (20.0f * f3) / f11;
            float f14 = (6.0f * f3) / f11;
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams5.addRule(14);
            layoutParams5.addRule(3, getPopupTextView().getId());
            com.appsamurai.storyly.util.ui.g popupEditTextView = getPopupEditTextView();
            popupEditTextView.setTextSize(0, f13 * this.f5789m);
            popupEditTextView.setTypeface(this.f5783g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
            com.appsamurai.storyly.data.q qVar8 = this.f5784h;
            if (qVar8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar8 = null;
            }
            popupEditTextView.setHintTextColor(qVar8.e().f3624a);
            com.appsamurai.storyly.data.q qVar9 = this.f5784h;
            if (qVar9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar9 = null;
            }
            popupEditTextView.setTextColor(qVar9.e().f3624a);
            popupEditTextView.setLineSpacing(0.0f, 1.0f);
            com.appsamurai.storyly.data.q qVar10 = this.f5784h;
            if (qVar10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar10 = null;
            }
            popupEditTextView.setBackground(com.appsamurai.storyly.util.ui.b.a(popupEditTextView, qVar10.d().f3624a, f14, (Integer) null, 0, 12));
            int roundToInt2 = MathKt.roundToInt(f12);
            popupEditTextView.setPadding(roundToInt2, roundToInt2, roundToInt2, roundToInt2);
            b bVar = new b(popupEditTextView, this);
            popupEditTextView.addTextChangedListener(bVar);
            popupEditTextView.addTextChangedListener(bVar);
            popupEditTextView.setOnBackPressed(new a(this));
            getPopupView().addView(getPopupEditTextView(), layoutParams5);
            float f15 = this.f5767B;
            float f16 = (10.0f * f3) / f15;
            float f17 = (300.0f * f3) / f15;
            float f18 = (9.0f * f3) / f15;
            float f19 = f8 / f15;
            float f20 = (f3 * 42.0f) / f15;
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams6.addRule(14);
            layoutParams6.addRule(3, getPopupEditTextView().getId());
            layoutParams6.topMargin = MathKt.roundToInt(f16);
            FrameLayout popupSendButton = getPopupSendButton();
            com.appsamurai.storyly.data.q qVar11 = this.f5784h;
            if (qVar11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar11 = null;
            }
            com.appsamurai.storyly.data.c cVar = qVar11.f4135p;
            if (cVar == null) {
                cVar = qVar11.f();
            }
            popupSendButton.setBackground(com.appsamurai.storyly.util.ui.b.a(popupSendButton, cVar.f3624a, f17, (Integer) null, 0, 12));
            int roundToInt3 = MathKt.roundToInt(f18);
            popupSendButton.setPadding(roundToInt3, roundToInt3, roundToInt3, roundToInt3);
            popupSendButton.setOnClickListener(new G0.i(this, f20));
            getPopupView().addView(getPopupSendButton(), layoutParams6);
            FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams(MathKt.roundToInt(f19), MathKt.roundToInt(f19));
            layoutParams7.gravity = 17;
            ImageView popupSendImage = getPopupSendImage();
            popupSendImage.setImageDrawable(AppCompatResources.getDrawable(popupSendImage.getContext(), R.drawable.st_comment_icon_send));
            com.appsamurai.storyly.data.q qVar12 = this.f5784h;
            if (qVar12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar = null;
            } else {
                qVar = qVar12;
            }
            com.appsamurai.storyly.data.c cVar2 = qVar.f4136q;
            if (cVar2 == null) {
                cVar2 = qVar.c();
            }
            popupSendImage.setColorFilter(new PorterDuffColorFilter(cVar2.f3624a, PorterDuff.Mode.SRC_ATOP));
            getPopupSendButton().addView(getPopupSendImage(), layoutParams7);
            Editable text = getPopupEditTextView().getText();
            if ((text == null ? 0 : text.length()) > 0) {
                getPopupSendButton().setVisibility(0);
            } else {
                getPopupSendButton().setVisibility(8);
            }
            getPopupView().measure(0, 0);
        }
    }

    public final float a(boolean z2, float f2, float f3) {
        float f4;
        com.appsamurai.storyly.data.q qVar = this.f5784h;
        com.appsamurai.storyly.data.q qVar2 = null;
        if (qVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            qVar = null;
        }
        if (qVar.b()) {
            com.appsamurai.storyly.data.q qVar3 = this.f5784h;
            if (qVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                qVar3 = null;
            }
            Float f5 = qVar3.f4140u;
            if (f5 != null) {
                float floatValue = f5.floatValue();
                if (z2) {
                    com.appsamurai.storyly.data.q qVar4 = this.f5784h;
                    if (qVar4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    } else {
                        qVar2 = qVar4;
                    }
                    f4 = qVar2.a(this.f5792p);
                } else {
                    f4 = 1.0f;
                }
                return (floatValue / ((float) 100)) * ((float) com.appsamurai.storyly.util.m.c().height()) * f4;
            }
        }
        com.appsamurai.storyly.data.q qVar5 = this.f5784h;
        if (qVar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
        } else {
            qVar2 = qVar5;
        }
        float a2 = qVar2.a(this.f5790n);
        Lazy lazy = com.appsamurai.storyly.util.m.f6342a;
        return (f3 * a2) / f2;
    }

    public static final void a(i iVar, View view) {
        Intrinsics.checkNotNullParameter(iVar, "this$0");
        iVar.a(false);
        iVar.getOnUserReaction$storyly_release().invoke(com.appsamurai.storyly.analytics.a.CommentInputFocusClear, iVar.getStorylyLayerItem$storyly_release(), null, null, null);
    }

    public static final void a(i iVar, float f2, View view) {
        Intrinsics.checkNotNullParameter(iVar, "this$0");
        iVar.a(f2);
    }

    public final void a(float f2) {
        int measuredWidth = getPopupSendButton().getMeasuredWidth();
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(getPopupSendImage(), Key.ROTATION, new float[]{360.0f});
        ofFloat.setRepeatCount(-1);
        ofFloat.setInterpolator(new LinearInterpolator());
        Unit unit = Unit.INSTANCE;
        animatorSet.play(ofFloat);
        animatorSet.setDuration(1000);
        animatorSet.start();
        this.G = animatorSet;
        AnimatorSet animatorSet2 = new AnimatorSet();
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{measuredWidth, (int) f2});
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.addUpdateListener(new G0.e(this, 1));
        animatorSet2.play(ofInt);
        animatorSet2.setDuration(200);
        animatorSet2.addListener(new x(this));
        animatorSet2.addListener(new w(this));
        animatorSet2.start();
        this.F = animatorSet2;
    }

    public static final void a(i iVar, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(iVar, "this$0");
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            iVar.getPopupSendButton().getLayoutParams().width = ((Integer) animatedValue).intValue();
            iVar.getPopupSendButton().requestLayout();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }

    public static final void a(i iVar) {
        AnimatorSet animatorSet = iVar.G;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        iVar.getPopupSendImage().setRotation(0.0f);
        iVar.getPopupSendImage().setImageDrawable(AppCompatResources.getDrawable(iVar.getContext(), R.drawable.st_comment_icon_tick));
        iVar.a(true);
    }

    public final void a(boolean z2) {
        if (getPopupView().getVisibility() == 0 && !this.f5771H) {
            this.f5771H = true;
            float measuredHeight = ((float) getPopupView().getMeasuredHeight()) * ((float) (z2 ? -1 : 1));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(getCommentView(), "alpha", new float[]{1.0f, 1.0f}), ObjectAnimator.ofFloat(getPopupBackgroundView(), "alpha", new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(getPopupView(), "translationY", new float[]{0.0f, measuredHeight}), ObjectAnimator.ofFloat(getPopupView(), "alpha", new float[]{1.0f, 0.0f})});
            animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animatorSet.setStartDelay(z2 ? 800 : 0);
            animatorSet.setDuration(300);
            animatorSet.addListener(new k(this, animatorSet));
            animatorSet.addListener(new j(this, z2, measuredHeight));
            animatorSet.start();
        }
    }
}
