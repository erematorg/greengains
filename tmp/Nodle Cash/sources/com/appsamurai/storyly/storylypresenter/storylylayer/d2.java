package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.ColorUtils;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.n0;
import com.appsamurai.storyly.util.f;
import com.appsamurai.storyly.util.ui.l;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class d2 extends o1 {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f5699g;

    /* renamed from: h  reason: collision with root package name */
    public Function1<? super b0, Unit> f5700h;

    /* renamed from: i  reason: collision with root package name */
    public n0 f5701i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public AnimatorSet f5702j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f5703k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f5704l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f5705m;

    public final class a implements View.OnTouchListener {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final GestureDetector f5706a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d2 f5707b;

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylylayer.d2$a$a  reason: collision with other inner class name */
        public final class C0052a extends GestureDetector.SimpleOnGestureListener {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ a f5708a;

            public C0052a(a aVar) {
                Intrinsics.checkNotNullParameter(aVar, "this$0");
                this.f5708a = aVar;
            }

            public boolean onFling(@NotNull MotionEvent motionEvent, @NotNull MotionEvent motionEvent2, float f2, float f3) {
                Intrinsics.checkNotNullParameter(motionEvent, "e1");
                Intrinsics.checkNotNullParameter(motionEvent2, "e2");
                float y2 = motionEvent2.getY() - motionEvent.getY();
                if (Math.abs(motionEvent2.getX() - motionEvent.getX()) >= Math.abs(y2) || Math.abs(y2) <= 30.0f || y2 >= 0.0f) {
                    return true;
                }
                a aVar = this.f5708a;
                aVar.f5707b.getOnUserActionClick$storyly_release().invoke(aVar.f5707b.getStorylyLayerItem$storyly_release());
                return true;
            }

            public boolean onSingleTapConfirmed(@Nullable MotionEvent motionEvent) {
                a aVar = this.f5708a;
                aVar.f5707b.getOnUserActionClick$storyly_release().invoke(aVar.f5707b.getStorylyLayerItem$storyly_release());
                return super.onSingleTapConfirmed(motionEvent);
            }
        }

        public a(d2 d2Var) {
            Intrinsics.checkNotNullParameter(d2Var, "this$0");
            this.f5707b = d2Var;
            this.f5706a = new GestureDetector(d2Var.getContext(), new C0052a(this));
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(@Nullable View view, @Nullable MotionEvent motionEvent) {
            this.f5706a.onTouchEvent(motionEvent);
            return true;
        }
    }

    public static final class b extends Lambda implements Function0<AppCompatButton> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5709a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f5709a = context;
        }

        public Object invoke() {
            AppCompatButton appCompatButton = new AppCompatButton(this.f5709a);
            appCompatButton.setAllCaps(false);
            appCompatButton.setSingleLine(true);
            appCompatButton.setGravity(17);
            appCompatButton.setTextAlignment(4);
            appCompatButton.setEllipsize(TextUtils.TruncateAt.END);
            appCompatButton.setImportantForAccessibility(2);
            appCompatButton.setContentDescription((CharSequence) null);
            appCompatButton.setElevation(0.0f);
            return appCompatButton;
        }
    }

    public static final class c extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5710a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5710a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5710a);
            imageView.setAdjustViewBounds(true);
            imageView.setImageResource(R.drawable.st_swipe_up_single_arrow_up);
            return imageView;
        }
    }

    public static final class d extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5711a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5711a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5711a);
            linearLayout.setClickable(false);
            linearLayout.setOrientation(1);
            return linearLayout;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d2(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5699g = storylyConfig;
        this.f5703k = LazyKt.lazy(new d(context));
        this.f5704l = LazyKt.lazy(new c(context));
        this.f5705m = LazyKt.lazy(new b(context));
    }

    private final AppCompatButton getActionButton() {
        return (AppCompatButton) this.f5705m.getValue();
    }

    private final Drawable getEndDrawable() {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.st_swipe_button_action_bg);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            n0 n0Var = this.f5701i;
            n0 n0Var2 = null;
            if (n0Var == null) {
                Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
                n0Var = null;
            }
            gradientDrawable.setColor(n0Var.f4061c.f3624a);
            n0 n0Var3 = this.f5701i;
            if (n0Var3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
            } else {
                n0Var2 = n0Var3;
            }
            com.appsamurai.storyly.data.c cVar = n0Var2.f4062d;
            if (cVar == null) {
                cVar = new com.appsamurai.storyly.data.c(ColorUtils.blendARGB(f.a(n0Var2.k(), 0.25f), n0Var2.f4061c.f3624a, 0.5f));
            }
            gradientDrawable.setStroke(4, cVar.f3624a);
            return gradientDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }

    /* access modifiers changed from: private */
    public final ImageView getImageView() {
        return (ImageView) this.f5704l.getValue();
    }

    private final Drawable getStartDrawable() {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.st_swipe_button_action_bg);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            n0 n0Var = this.f5701i;
            n0 n0Var2 = null;
            if (n0Var == null) {
                Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
                n0Var = null;
            }
            gradientDrawable.setColor(f.a(n0Var.k(), 0.15f));
            n0 n0Var3 = this.f5701i;
            if (n0Var3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
            } else {
                n0Var2 = n0Var3;
            }
            n0Var2.getClass();
            gradientDrawable.setStroke(4, f.a(n0Var2.f4061c.f3624a, 0.5f));
            return gradientDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }

    private final LinearLayout getSwipeActionView() {
        return (LinearLayout) this.f5703k.getValue();
    }

    public void c() {
        ArrayList<Animator> childAnimations;
        super.c();
        AnimatorSet animatorSet = this.f5702j;
        if (!(animatorSet == null || (childAnimations = animatorSet.getChildAnimations()) == null)) {
            for (Animator removeAllListeners : childAnimations) {
                removeAllListeners.removeAllListeners();
            }
        }
        AnimatorSet animatorSet2 = this.f5702j;
        if (animatorSet2 != null) {
            animatorSet2.removeAllListeners();
        }
        AnimatorSet animatorSet3 = this.f5702j;
        if (animatorSet3 != null) {
            animatorSet3.cancel();
        }
        removeAllViews();
    }

    @NotNull
    public final Function1<b0, Unit> getOnUserActionClick$storyly_release() {
        Function1<? super b0, Unit> function1 = this.f5700h;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserActionClick");
        return null;
    }

    public final void setOnUserActionClick$storyly_release(@NotNull Function1<? super b0, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5700h = function1;
    }

    public void a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        a0 a0Var = b0Var.f3615j;
        n0 n0Var = null;
        n0 n0Var2 = a0Var instanceof n0 ? (n0) a0Var : null;
        if (n0Var2 != null) {
            this.f5701i = n0Var2;
            setStorylyLayerItem$storyly_release(b0Var);
            getOnLayerLoad$storyly_release().invoke();
            setImportantForAccessibility(1);
            n0 n0Var3 = this.f5701i;
            if (n0Var3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
            } else {
                n0Var = n0Var3;
            }
            String str = n0Var.f4059a;
            if (str.length() == 0) {
                str = getResources().getString(R.string.st_desc_swipeup);
                Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.string.st_desc_swipeup)");
            }
            setContentDescription(str);
            l.a(this, new com.appsamurai.storyly.util.ui.c(getActionButton()));
        }
    }

    public void a(@NotNull d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        a aVar = new a(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 80;
        setLayoutParams(layoutParams);
        addView(getSwipeActionView(), new LinearLayout.LayoutParams(-1, -2));
        int a2 = (int) (dVar.a() * 0.065f);
        int i3 = a2 / 2;
        float f2 = ((float) i3) * 0.75f;
        ImageView imageView = getImageView();
        imageView.clearColorFilter();
        n0 n0Var = this.f5701i;
        n0 n0Var2 = null;
        if (n0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
            n0Var = null;
        }
        imageView.setColorFilter(n0Var.f4061c.f3624a, PorterDuff.Mode.MULTIPLY);
        imageView.setOnTouchListener(aVar);
        AppCompatButton actionButton = getActionButton();
        actionButton.setTextSize(0, f2);
        n0 n0Var3 = this.f5701i;
        if (n0Var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
            n0Var3 = null;
        }
        actionButton.setText(n0Var3.f4059a);
        actionButton.setTypeface(this.f5699g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        n0 n0Var4 = this.f5701i;
        if (n0Var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
            n0Var4 = null;
        }
        boolean z2 = n0Var4.f4064f;
        n0 n0Var5 = this.f5701i;
        if (n0Var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
            n0Var5 = null;
        }
        com.appsamurai.storyly.util.c.a(actionButton, z2, n0Var5.f4065g);
        actionButton.setOnTouchListener(aVar);
        n0 n0Var6 = this.f5701i;
        if (n0Var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
            n0Var6 = null;
        }
        actionButton.setTextColor(n0Var6.f4061c.f3624a);
        actionButton.setBackground(getStartDrawable());
        actionButton.setPadding(i3, 0, i3, 0);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, (int) getContext().getResources().getDimension(R.dimen.st_swipe_action_button_image_height));
        layoutParams2.topMargin = (int) (dVar.a() * 0.01f);
        layoutParams2.gravity = 1;
        getSwipeActionView().addView(getImageView(), layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, a2);
        layoutParams3.gravity = 1;
        layoutParams3.leftMargin = (int) (dVar.b() * 0.05f);
        layoutParams3.rightMargin = (int) (dVar.b() * 0.05f);
        layoutParams3.topMargin = (int) (dVar.a() * 0.01f);
        layoutParams3.bottomMargin = (int) (dVar.a() * 0.02f);
        getSwipeActionView().addView(getActionButton(), layoutParams3);
        getImageView().setVisibility(8);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(getActionButton(), "translationY", new float[]{0.0f, -12.5f});
        ofFloat.setStartDelay(600);
        ofFloat.setDuration(600);
        ofFloat.setRepeatMode(2);
        ofFloat.setRepeatCount(-1);
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{getStartDrawable(), getEndDrawable()});
        getActionButton().setBackground(transitionDrawable);
        n0 n0Var7 = this.f5701i;
        if (n0Var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
            n0Var7 = null;
        }
        int k2 = n0Var7.k();
        n0 n0Var8 = this.f5701i;
        if (n0Var8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeActionLayer");
        } else {
            n0Var2 = n0Var8;
        }
        ObjectAnimator ofInt = ObjectAnimator.ofInt(getActionButton(), "textColor", new int[]{n0Var2.f4061c.f3624a, k2});
        ofInt.setEvaluator(new ArgbEvaluator());
        ofInt.setStartDelay(600);
        ofInt.setDuration(600);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(getImageView(), "translationY", new float[]{getSafeFrame$storyly_release().a() * 0.01f, -5.0f});
        ofFloat2.setDuration(600);
        ofFloat2.setStartDelay(600);
        ofFloat2.addListener(new f2(transitionDrawable, this));
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(getImageView(), "alpha", new float[]{0.0f, 1.0f});
        ofFloat3.setStartDelay(600);
        ofFloat3.setDuration(600);
        ofFloat3.addListener(new e2(this));
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(getImageView(), "translationY", new float[]{-5.0f, (float) (((double) (getSafeFrame$storyly_release().a() * 0.01f)) * 0.9d)});
        ofFloat4.setDuration(600);
        ofFloat4.setRepeatMode(2);
        ofFloat4.setRepeatCount(-1);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(getImageView(), "scaleY", new float[]{1.0f, 0.7f});
        ofFloat5.setDuration(600);
        ofFloat5.setRepeatMode(2);
        ofFloat5.setRepeatCount(-1);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(getImageView(), "scaleX", new float[]{1.0f, 1.15f});
        ofFloat6.setDuration(600);
        ofFloat6.setRepeatMode(2);
        ofFloat6.setRepeatCount(-1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat2);
        animatorSet.play(ofFloat2).with(ofFloat3).with(ofInt);
        animatorSet.play(ofFloat4).after(ofFloat2).with(ofFloat5).with(ofFloat6).with(ofFloat);
        animatorSet.start();
        Unit unit = Unit.INSTANCE;
        this.f5702j = animatorSet;
    }
}
