package com.appsamurai.storyly.storylypresenter.storylylayer;

import G0.k;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupKt;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.widget.EmojiTextView;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.t;
import com.appsamurai.storyly.util.ui.l;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import kotlin.random.Random;
import kotlin.sequences.SequencesKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class p extends o1 {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f5901g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final List<EmojiTextView> f5902h = new ArrayList();

    /* renamed from: i  reason: collision with root package name */
    public float f5903i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public String f5904j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public List<TextView> f5905k = new ArrayList();
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public List<AppCompatTextView> f5906l = new ArrayList();
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public AnimatorSet f5907m;

    /* renamed from: n  reason: collision with root package name */
    public t f5908n;

    /* renamed from: o  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f5909o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final Lazy f5910p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final Lazy f5911q;

    public static final class a extends Lambda implements Function0<SharedPreferences> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5912a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f5912a = context;
        }

        public Object invoke() {
            return this.f5912a.getSharedPreferences("stryly-emoji-selections", 0);
        }
    }

    public static final class b extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5913a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f5913a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5913a);
            linearLayout.setOrientation(0);
            linearLayout.setClickable(true);
            linearLayout.setClipChildren(false);
            linearLayout.setClipToPadding(false);
            return linearLayout;
        }
    }

    public static final class c implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ p f5914a;

        public c(p pVar) {
            this.f5914a = pVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
            this.f5914a.setEmojisClickable(true);
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
        }
    }

    public static final class d implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ p f5915a;

        public d(p pVar) {
            this.f5915a = pVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
            this.f5915a.setEmojisClickable(false);
        }
    }

    public static final class e implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ EmojiTextView f5916a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ p f5917b;

        public e(EmojiTextView emojiTextView, p pVar) {
            this.f5916a = emojiTextView;
            this.f5917b = pVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
            this.f5916a.setScaleX(1.0f);
            this.f5916a.setScaleY(1.0f);
            this.f5916a.setY(this.f5917b.getSafeFrame$storyly_release().a());
            this.f5916a.setAlpha(1.0f);
            this.f5916a.setVisibility(4);
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
        }
    }

    public static final class f implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ EmojiTextView f5918a;

        public f(EmojiTextView emojiTextView) {
            this.f5918a = emojiTextView;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
            float nextDouble = (float) Random.Default.nextDouble(1.0d, 3.0d);
            this.f5918a.setScaleX(nextDouble);
            this.f5918a.setScaleY(nextDouble);
            this.f5918a.setVisibility(0);
        }
    }

    public static final class g implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ GradientDrawable f5919a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ int f5920b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ p f5921c;

        public g(GradientDrawable gradientDrawable, int i3, p pVar) {
            this.f5919a = gradientDrawable;
            this.f5920b = i3;
            this.f5921c = pVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
            GradientDrawable gradientDrawable = this.f5919a;
            if (gradientDrawable != null) {
                gradientDrawable.setColor(this.f5920b);
            }
            GradientDrawable gradientDrawable2 = this.f5919a;
            if (gradientDrawable2 != null) {
                int ceil = (int) ((float) Math.ceil((double) this.f5921c.getButtonBorderSize()));
                t tVar = this.f5921c.f5908n;
                if (tVar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    tVar = null;
                }
                gradientDrawable2.setStroke(ceil, tVar.b().f3624a);
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public p(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5901g = storylyConfig;
        this.f5910p = LazyKt.lazy(new a(context));
        this.f5911q = LazyKt.lazy(new b(context));
        l.b(this);
        setClipChildren(false);
        setClipToPadding(false);
    }

    public static final void b(ArgbEvaluator argbEvaluator, int i3, int i4, GradientDrawable gradientDrawable, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(argbEvaluator, "$evaluator");
        Object evaluate = argbEvaluator.evaluate(valueAnimator.getAnimatedFraction(), Integer.valueOf(i3), Integer.valueOf(i4));
        if (evaluate != null) {
            int intValue = ((Integer) evaluate).intValue();
            if (gradientDrawable != null) {
                gradientDrawable.setColor(intValue);
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }

    private final float getButtonAnimatedHeight() {
        return this.f5903i * 1.25f;
    }

    /* access modifiers changed from: private */
    public final float getButtonBorderSize() {
        return this.f5903i * 0.017857144f;
    }

    private final float getClickedNumberFontSize() {
        return this.f5903i * 0.25f;
    }

    private final SharedPreferences getEmojiSelectionSharedPreferences() {
        return (SharedPreferences) this.f5910p.getValue();
    }

    private final LinearLayout getEmojiView() {
        return (LinearLayout) this.f5911q.getValue();
    }

    private final float getIconFontSize() {
        return this.f5903i * 0.5f;
    }

    private final float getSpacing() {
        return this.f5903i * -0.1782f;
    }

    private final float getSpacingForButtons() {
        return this.f5903i * 0.2142f;
    }

    /* access modifiers changed from: private */
    public final void setEmojisClickable(boolean z2) {
        for (View clickable : ViewGroupKt.getChildren(getEmojiView())) {
            clickable.setClickable(z2);
        }
    }

    public void c() {
        getEmojiView().removeAllViews();
        removeAllViews();
        for (EmojiTextView removeView : this.f5902h) {
            removeView(removeView);
        }
        this.f5902h.clear();
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> getOnUserReaction$storyly_release() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f5909o;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    public final void setOnUserReaction$storyly_release(@NotNull Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5) {
        Intrinsics.checkNotNullParameter(function5, "<set-?>");
        this.f5909o = function5;
    }

    public void a(@NotNull d dVar) {
        String str;
        int i3;
        int i4;
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        c();
        float b3 = dVar.b();
        float a2 = dVar.a();
        this.f5903i = (getStorylyLayerItem$storyly_release().f3610e * a2) / 100.0f;
        t tVar = this.f5908n;
        if (tVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            tVar = null;
        }
        float buttonAnimatedHeight = (getButtonAnimatedHeight() + getSpacingForButtons()) * ((float) tVar.f4202a.size());
        float f2 = this.f5903i * 1.1428f;
        float buttonAnimatedHeight2 = getButtonAnimatedHeight() * 1.1428f;
        t tVar2 = this.f5908n;
        if (tVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            tVar2 = null;
        }
        float spacingForButtons = (this.f5903i + getSpacingForButtons()) * ((float) tVar2.f4202a.size());
        boolean z2 = getStorylyLayerItem$storyly_release().f3613h == 0.0f;
        if (z2) {
            buttonAnimatedHeight = spacingForButtons;
        }
        if (z2) {
            f2 = buttonAnimatedHeight2;
        }
        float buttonBorderSize = (this.f5903i * 0.07139999f) + getButtonBorderSize();
        float buttonAnimatedHeight3 = getButtonAnimatedHeight() * 0.07139999f;
        if (!z2) {
            buttonBorderSize = buttonAnimatedHeight3;
        }
        float buttonAnimatedHeight4 = getButtonAnimatedHeight() * 0.07139999f;
        float f3 = -buttonBorderSize;
        if (!z2) {
            f3 = ((((float) 2) * buttonAnimatedHeight4) + f3) - getButtonBorderSize();
        }
        float f4 = z2 ? -buttonAnimatedHeight4 : buttonAnimatedHeight4 - (((float) 2) * buttonBorderSize);
        getEmojiView().setGravity(z2 ? 48 : 16);
        String str2 = getStorylyLayerItem$storyly_release().f3614i;
        this.f5904j = getEmojiSelectionSharedPreferences().contains(str2) ? getEmojiSelectionSharedPreferences().getString(str2, "") : null;
        List<Integer> b4 = b((String) null);
        if (b4 != null) {
            int i5 = 0;
            for (T next : b4) {
                int i6 = i5 + 1;
                if (i5 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ((Number) next).intValue();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) this.f5903i, (int) getClickedNumberFontSize());
                layoutParams.topMargin = (int) getSpacing();
                TextView textView = new TextView(getContext());
                textView.setTypeface(this.f5901g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
                textView.setTextAlignment(4);
                textView.setGravity(17);
                textView.setIncludeFontPadding(false);
                t tVar3 = this.f5908n;
                if (tVar3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    tVar3 = null;
                }
                textView.setTextColor(tVar3.e().f3624a);
                textView.setTextSize(0, getClickedNumberFontSize());
                textView.setLayoutParams(layoutParams);
                StringBuilder sb = new StringBuilder();
                Integer num = (Integer) com.appsamurai.storyly.util.e.a(b4, Integer.valueOf(i5));
                sb.append(num == null ? 0 : num.intValue());
                sb.append('%');
                textView.setText(sb.toString());
                this.f5905k.add(textView);
                d dVar2 = dVar;
                i5 = i6;
            }
        }
        int roundToInt = MathKt.roundToInt(b3);
        int roundToInt2 = MathKt.roundToInt(a2);
        float f5 = ((float) roundToInt) / ((float) 20);
        int i7 = 0;
        while (true) {
            int i8 = i7 + 1;
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 0;
            EmojiTextView emojiTextView = new EmojiTextView(getContext());
            emojiTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            emojiTextView.setLayoutParams(layoutParams2);
            emojiTextView.setBackgroundColor(0);
            emojiTextView.setY((float) roundToInt2);
            emojiTextView.setX(((float) i7) * f5);
            emojiTextView.setVisibility(4);
            this.f5902h.add(emojiTextView);
            emojiTextView.setElevation(1.0f);
            ViewParent parent = getParent();
            FrameLayout frameLayout = parent instanceof FrameLayout ? (FrameLayout) parent : null;
            if (frameLayout != null) {
                frameLayout.addView(emojiTextView);
            }
            if (i8 >= 20) {
                break;
            }
            i7 = i8;
        }
        a(getStorylyLayerItem$storyly_release().f3613h);
        LinearLayout emojiView = getEmojiView();
        float f6 = (float) 2;
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams((int) (buttonAnimatedHeight - (f6 * buttonBorderSize)), (int) (f2 - (f6 * buttonAnimatedHeight4)));
        layoutParams3.topMargin = (int) buttonAnimatedHeight4;
        layoutParams3.setMarginStart((int) buttonBorderSize);
        layoutParams3.gravity = 8388659;
        Unit unit = Unit.INSTANCE;
        addView(emojiView, layoutParams3);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams((int) buttonAnimatedHeight, (int) f2);
        a(layoutParams4, getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, dVar.c() + f3, dVar.d() + f4);
        setLayoutParams(layoutParams4);
        setClickable(true);
        t tVar4 = this.f5908n;
        if (tVar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            tVar4 = null;
        }
        if (tVar4.f4210i || this.f5904j != null) {
            t tVar5 = this.f5908n;
            if (tVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                tVar5 = null;
            }
            if (tVar5.f4210i) {
                str = null;
            } else {
                str = this.f5904j;
            }
            a(str, true);
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i9 = 0;
        for (View next2 : ViewGroupKt.getChildren(getEmojiView())) {
            int i10 = i9 + 1;
            if (i9 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            View view = next2;
            AnimatorSet a3 = a(view, i9);
            a3.addListener(new q(this, view));
            arrayList.add(a3);
            i9 = i10;
        }
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        int i11 = 0;
        while (i11 < size) {
            if (i11 % 2 == 0) {
                i4 = i11 / 2;
                i3 = 1;
            } else {
                i3 = 1;
                i4 = (arrayList.size() - (i11 / 2)) - 1;
            }
            arrayList2.add((Animator) arrayList.get(i4));
            i11 += i3;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(200);
        Unit unit2 = Unit.INSTANCE;
        this.f5907m = animatorSet;
        animatorSet.playSequentially(arrayList2);
        AnimatorSet animatorSet2 = this.f5907m;
        if (animatorSet2 != null) {
            animatorSet2.start();
        }
    }

    public final List<Integer> b(String str) {
        Unit unit;
        Integer num;
        t tVar = this.f5908n;
        if (tVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            tVar = null;
        }
        Map<String, Integer> map = tVar.f4209h;
        if (map == null) {
            return null;
        }
        t tVar2 = this.f5908n;
        if (tVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            tVar2 = null;
        }
        Map<String, Integer> map2 = tVar2.f4209h;
        if (map2 != null) {
            int size = map2.size();
            t tVar3 = this.f5908n;
            if (tVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                tVar3 = null;
            }
            if (size == tVar3.f4202a.size()) {
                ArrayList arrayList = new ArrayList();
                t tVar4 = this.f5908n;
                if (tVar4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    tVar4 = null;
                }
                int z2 = !tVar4.f4210i ? CollectionsKt___CollectionsKt.sumOfInt(map.values()) + 1 : CollectionsKt___CollectionsKt.sumOfInt(map.values());
                t tVar5 = this.f5908n;
                if (tVar5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    tVar5 = null;
                }
                for (String str2 : tVar5.f4202a) {
                    t tVar6 = this.f5908n;
                    if (tVar6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                        tVar6 = null;
                    }
                    Map<String, Integer> map3 = tVar6.f4209h;
                    if (map3 == null) {
                        num = null;
                    } else {
                        Integer num2 = map3.get(str2);
                        if (num2 == null) {
                            return null;
                        }
                        num = num2;
                    }
                    if (num != null) {
                        int intValue = num.intValue();
                        if (z2 == 0) {
                            arrayList.add(Integer.valueOf((int) Math.ceil(100.0d / ((double) map.size()))));
                        } else {
                            if (Intrinsics.areEqual((Object) str, (Object) str2)) {
                                intValue++;
                            }
                            arrayList.add(Integer.valueOf((int) ((float) Math.ceil((double) ((((float) intValue) / ((float) z2)) * ((float) 100))))));
                        }
                    }
                }
                while (CollectionsKt___CollectionsKt.sumOfInt(arrayList) != 100) {
                    Integer num3 = (Integer) CollectionsKt.maxOrNull(arrayList);
                    if (num3 == null) {
                        unit = null;
                        continue;
                    } else {
                        int intValue2 = num3.intValue();
                        arrayList.set(arrayList.indexOf(Integer.valueOf(intValue2)), Integer.valueOf(100 - (CollectionsKt___CollectionsKt.sumOfInt(arrayList) - intValue2)));
                        unit = Unit.INSTANCE;
                        continue;
                    }
                    if (unit == null) {
                        return null;
                    }
                }
                return arrayList;
            }
        }
        return null;
    }

    public final void c(String str) {
        for (EmojiTextView text : this.f5902h) {
            text.setText(EmojiCompat.get().process(str));
        }
        ArrayList arrayList = new ArrayList(20);
        for (int i3 = 0; i3 < 20; i3 = android.support.v4.media.session.a.e(i3, arrayList, i3, 1)) {
        }
        Collections.shuffle(arrayList);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        int i4 = 0;
        while (it.hasNext()) {
            EmojiTextView emojiTextView = this.f5902h.get(((Number) it.next()).intValue());
            ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(emojiTextView, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(FrameLayout.TRANSLATION_Y, new float[]{getSafeFrame$storyly_release().a() / ((float) 2)}), PropertyValuesHolder.ofFloat(FrameLayout.ALPHA, new float[]{0.0f})}).setDuration(1500);
            Intrinsics.checkNotNullExpressionValue(duration, "ofPropertyValuesHolder(e…OATING_DURATION.toLong())");
            duration.addListener(new e(emojiTextView, this));
            duration.addListener(new f(emojiTextView));
            duration.setStartDelay((long) (i4 * 75));
            arrayList2.add(duration);
            i4++;
        }
        animatorSet.addListener(new d(this));
        animatorSet.addListener(new c(this));
        animatorSet.playTogether(arrayList2);
        animatorSet.start();
    }

    public final ValueAnimator b(View view, float f2, long j2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams = null;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{marginLayoutParams != null ? marginLayoutParams.rightMargin : 0, (int) f2});
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(j2);
        ofInt.addUpdateListener(new k(view, 1));
        return ofInt;
    }

    public static final void c(ArgbEvaluator argbEvaluator, int i3, int i4, GradientDrawable gradientDrawable, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(argbEvaluator, "$evaluator");
        Object evaluate = argbEvaluator.evaluate(valueAnimator.getAnimatedFraction(), Integer.valueOf(i3), Integer.valueOf(i4));
        if (evaluate != null) {
            int intValue = ((Integer) evaluate).intValue();
            if (gradientDrawable != null) {
                gradientDrawable.setColor(intValue);
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }

    public static final void b(View view, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(view, "$emojiButton");
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            int intValue = ((Integer) animatedValue).intValue();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
            if (layoutParams2 != null) {
                layoutParams2.rightMargin = intValue;
            }
            view.requestLayout();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }

    public final ValueAnimator c(View view, float f2, long j2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            layoutParams = null;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{marginLayoutParams != null ? marginLayoutParams.leftMargin : 0, (int) ((f2 - this.f5903i) / ((float) 2))});
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(j2);
        ofInt.addUpdateListener(new k(view, 0));
        return ofInt;
    }

    public static final void c(View view, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(view, "$emojiButton");
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            int intValue = ((Integer) animatedValue).intValue();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
            if (layoutParams2 != null) {
                layoutParams2.leftMargin = intValue;
            }
            view.requestLayout();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0070, code lost:
        if (r7.f4210i != false) goto L_0x0072;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0109  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(float r12) {
        /*
            r11 = this;
            com.appsamurai.storyly.data.t r0 = r11.f5908n
            r1 = 0
            java.lang.String r2 = "storylyLayer"
            if (r0 != 0) goto L_0x000c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L_0x000c:
            java.util.List<java.lang.String> r0 = r0.f4202a
            java.util.Iterator r0 = r0.iterator()
        L_0x0012:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x012e
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            android.widget.LinearLayout$LayoutParams r4 = new android.widget.LinearLayout$LayoutParams
            float r5 = r11.f5903i
            int r5 = (int) r5
            r4.<init>(r5, r5)
            float r5 = r11.getSpacingForButtons()
            int r5 = (int) r5
            r4.setMarginEnd(r5)
            android.widget.LinearLayout r5 = new android.widget.LinearLayout
            android.content.Context r6 = r11.getContext()
            r5.<init>(r6)
            android.graphics.drawable.GradientDrawable r6 = new android.graphics.drawable.GradientDrawable
            r6.<init>()
            java.lang.String r7 = r11.f5904j
            r8 = 0
            if (r7 != 0) goto L_0x0051
            com.appsamurai.storyly.data.t r7 = r11.f5908n
            if (r7 != 0) goto L_0x0049
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r7 = r1
        L_0x0049:
            boolean r7 = r7.f4210i
            if (r7 != 0) goto L_0x0051
            r6.setColor(r8)
            goto L_0x0062
        L_0x0051:
            com.appsamurai.storyly.data.t r7 = r11.f5908n
            if (r7 != 0) goto L_0x0059
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r7 = r1
        L_0x0059:
            com.appsamurai.storyly.data.c r7 = r7.a()
            int r7 = r7.f3624a
            r6.setColor(r7)
        L_0x0062:
            java.lang.String r7 = r11.f5904j
            if (r7 != 0) goto L_0x0072
            com.appsamurai.storyly.data.t r7 = r11.f5908n
            if (r7 != 0) goto L_0x006e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r7 = r1
        L_0x006e:
            boolean r7 = r7.f4210i
            if (r7 == 0) goto L_0x008e
        L_0x0072:
            float r7 = r11.getButtonBorderSize()
            double r9 = (double) r7
            double r9 = java.lang.Math.ceil(r9)
            float r7 = (float) r9
            int r7 = (int) r7
            com.appsamurai.storyly.data.t r9 = r11.f5908n
            if (r9 != 0) goto L_0x0085
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r9 = r1
        L_0x0085:
            com.appsamurai.storyly.data.c r9 = r9.b()
            int r9 = r9.f3624a
            r6.setStroke(r7, r9)
        L_0x008e:
            float r7 = r11.f5903i
            r9 = 2
            float r9 = (float) r9
            float r7 = r7 / r9
            r6.setCornerRadius(r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            r5.setBackground(r6)
            r6 = 1
            r5.setOrientation(r6)
            float r7 = -r12
            r5.setRotation(r7)
            r5.setLayoutParams(r4)
            r5.setClickable(r6)
            com.appsamurai.storyly.data.t r4 = r11.f5908n
            if (r4 != 0) goto L_0x00b1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r4 = r1
        L_0x00b1:
            boolean r4 = r4.f4210i
            if (r4 != 0) goto L_0x00be
            B0.a r4 = new B0.a
            r6 = 3
            r4.<init>(r11, r3, r6)
            r5.setOnClickListener(r4)
        L_0x00be:
            android.widget.LinearLayout$LayoutParams r4 = new android.widget.LinearLayout$LayoutParams
            float r6 = r11.f5903i
            int r6 = (int) r6
            r4.<init>(r6, r6)
            r6 = 48
            r4.gravity = r6
            androidx.appcompat.widget.AppCompatTextView r6 = new androidx.appcompat.widget.AppCompatTextView
            android.content.Context r7 = r11.getContext()
            r6.<init>(r7)
            com.appsamurai.storyly.data.t r7 = r11.f5908n
            if (r7 != 0) goto L_0x00db
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r7 = r1
        L_0x00db:
            com.appsamurai.storyly.data.c r7 = r7.e()
            int r7 = r7.f3624a
            r6.setTextColor(r7)
            androidx.emoji.text.EmojiCompat r7 = androidx.emoji.text.EmojiCompat.get()
            java.lang.CharSequence r3 = r7.process(r3)
            r6.setText(r3)
            r3 = 17
            r6.setGravity(r3)
            float r3 = r11.getIconFontSize()
            r6.setTextSize(r8, r3)
            r3 = 4
            r6.setTextAlignment(r3)
            r6.setIncludeFontPadding(r8)
            r6.setLayoutParams(r4)
            java.lang.String r3 = r11.f5904j
            if (r3 != 0) goto L_0x0118
            com.appsamurai.storyly.data.t r3 = r11.f5908n
            if (r3 != 0) goto L_0x0111
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r3 = r1
        L_0x0111:
            boolean r3 = r3.f4210i
            if (r3 == 0) goto L_0x0116
            goto L_0x0118
        L_0x0116:
            r3 = 0
            goto L_0x011a
        L_0x0118:
            r3 = 1065353216(0x3f800000, float:1.0)
        L_0x011a:
            r6.setAlpha(r3)
            java.util.List<androidx.appcompat.widget.AppCompatTextView> r3 = r11.f5906l
            r3.add(r6)
            r5.addView(r6)
            android.widget.LinearLayout r3 = r11.getEmojiView()
            r3.addView(r5)
            goto L_0x0012
        L_0x012e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.p.a(float):void");
    }

    public final void a(String str, String str2) {
        SharedPreferences emojiSelectionSharedPreferences = getEmojiSelectionSharedPreferences();
        Intrinsics.checkNotNullExpressionValue(emojiSelectionSharedPreferences, "emojiSelectionSharedPreferences");
        SharedPreferences.Editor edit = emojiSelectionSharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        if (str2 != null) {
            edit.putString(str, str2);
        } else {
            edit.remove(str);
        }
        edit.apply();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: com.appsamurai.storyly.data.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: com.appsamurai.storyly.data.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: com.appsamurai.storyly.data.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v16, resolved type: com.appsamurai.storyly.data.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v17, resolved type: com.appsamurai.storyly.data.t} */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x026a, code lost:
        r2 = (java.lang.Integer) com.appsamurai.storyly.util.e.a(r0, java.lang.Integer.valueOf(r2));
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void a(com.appsamurai.storyly.storylypresenter.storylylayer.p r29, java.lang.String r30, android.view.View r31) {
        /*
            r6 = r29
            r7 = r30
            r8 = 2
            r9 = 1
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "$emojiCode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = r6.f5904j
            r10 = 0
            java.lang.String r1 = "activity"
            java.lang.String r11 = "storylyLayer"
            r12 = 0
            if (r0 != 0) goto L_0x0059
            kotlin.jvm.functions.Function5 r13 = r29.getOnUserReaction$storyly_release()
            com.appsamurai.storyly.analytics.a r14 = com.appsamurai.storyly.analytics.a.ReactionClicked
            com.appsamurai.storyly.data.b0 r15 = r29.getStorylyLayerItem$storyly_release()
            com.appsamurai.storyly.data.b0 r0 = r29.getStorylyLayerItem$storyly_release()
            com.appsamurai.storyly.data.t r2 = r6.f5908n
            if (r2 != 0) goto L_0x0032
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            goto L_0x0033
        L_0x0032:
            r12 = r2
        L_0x0033:
            java.util.List<java.lang.String> r2 = r12.f4202a
            int r2 = r2.indexOf(r7)
            com.appsamurai.storyly.data.a0 r3 = r0.f3615j
            com.appsamurai.storyly.StoryComponent r16 = r3.a((com.appsamurai.storyly.data.b0) r0, (int) r2)
            kotlinx.serialization.json.JsonObjectBuilder r0 = new kotlinx.serialization.json.JsonObjectBuilder
            r0.<init>()
            kotlinx.serialization.json.JsonElementBuildersKt.put((kotlinx.serialization.json.JsonObjectBuilder) r0, (java.lang.String) r1, (java.lang.String) r7)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            kotlinx.serialization.json.JsonObject r17 = r0.build()
            r18 = 0
            r13.invoke(r14, r15, r16, r17, r18)
            r6.a((java.lang.String) r7, (boolean) r10)
            r6.f5904j = r7
            goto L_0x03c6
        L_0x0059:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r7)
            r13 = 300(0x12c, double:1.48E-321)
            java.lang.String r15 = "scaleX"
            java.lang.String r4 = "scaleY"
            r16 = 1065353216(0x3f800000, float:1.0)
            if (r0 == 0) goto L_0x01dc
            kotlin.jvm.functions.Function5 r17 = r29.getOnUserReaction$storyly_release()
            com.appsamurai.storyly.analytics.a r18 = com.appsamurai.storyly.analytics.a.ReactionClicked
            com.appsamurai.storyly.data.b0 r19 = r29.getStorylyLayerItem$storyly_release()
            com.appsamurai.storyly.data.b0 r0 = r29.getStorylyLayerItem$storyly_release()
            com.appsamurai.storyly.data.t r2 = r6.f5908n
            if (r2 != 0) goto L_0x007d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r2 = r12
        L_0x007d:
            java.util.List<java.lang.String> r2 = r2.f4202a
            int r2 = r2.indexOf(r7)
            com.appsamurai.storyly.data.a0 r3 = r0.f3615j
            com.appsamurai.storyly.StoryComponent r20 = r3.a((com.appsamurai.storyly.data.b0) r0, (int) r2)
            kotlinx.serialization.json.JsonObjectBuilder r0 = new kotlinx.serialization.json.JsonObjectBuilder
            r0.<init>()
            kotlinx.serialization.json.JsonElementBuildersKt.put((kotlinx.serialization.json.JsonObjectBuilder) r0, (java.lang.String) r1, (java.lang.String) r12)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            kotlinx.serialization.json.JsonObject r21 = r0.build()
            r22 = 0
            r17.invoke(r18, r19, r20, r21, r22)
            com.appsamurai.storyly.data.t r0 = r6.f5908n
            if (r0 != 0) goto L_0x00a4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = r12
        L_0x00a4:
            com.appsamurai.storyly.data.c r0 = r0.a()
            int r7 = r0.f3624a
            com.appsamurai.storyly.data.t r0 = r6.f5908n
            if (r0 != 0) goto L_0x00b2
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = r12
        L_0x00b2:
            com.appsamurai.storyly.data.c r0 = r0.c()
            int r5 = r0.f3624a
            com.appsamurai.storyly.data.t r0 = r6.f5908n
            if (r0 != 0) goto L_0x00c0
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = r12
        L_0x00c0:
            java.util.List<java.lang.String> r0 = r0.f4202a
            java.lang.String r1 = r6.f5904j
            int r11 = kotlin.collections.CollectionsKt.indexOf(r0, r1)
            com.appsamurai.storyly.data.b0 r0 = r29.getStorylyLayerItem$storyly_release()
            java.lang.String r0 = r0.f3614i
            r6.a((java.lang.String) r0, (java.lang.String) r12)
            android.widget.LinearLayout r0 = r29.getEmojiView()
            kotlin.sequences.Sequence r0 = androidx.core.view.ViewGroupKt.getChildren(r0)
            java.util.Iterator r17 = r0.iterator()
        L_0x00dd:
            boolean r0 = r17.hasNext()
            if (r0 == 0) goto L_0x01d7
            java.lang.Object r0 = r17.next()
            r1 = r0
            android.view.View r1 = (android.view.View) r1
            android.widget.LinearLayout r0 = r29.getEmojiView()
            kotlin.sequences.Sequence r0 = androidx.core.view.ViewGroupKt.getChildren(r0)
            int r0 = kotlin.sequences.SequencesKt___SequencesKt.indexOf(r0, r1)
            java.util.List<android.widget.TextView> r2 = r6.f5905k
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            java.lang.Object r2 = com.appsamurai.storyly.util.e.a(r2, r3)
            android.widget.TextView r2 = (android.widget.TextView) r2
            if (r2 != 0) goto L_0x0105
            goto L_0x010b
        L_0x0105:
            r3 = r1
            android.widget.LinearLayout r3 = (android.widget.LinearLayout) r3
            r3.removeView(r2)
        L_0x010b:
            android.animation.AnimatorSet r3 = new android.animation.AnimatorSet
            r3.<init>()
            android.view.animation.DecelerateInterpolator r2 = new android.view.animation.DecelerateInterpolator
            r2.<init>()
            r3.setInterpolator(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            float r18 = r1.getScaleY()
            float[] r12 = new float[r8]
            r12[r10] = r18
            r12[r9] = r16
            android.animation.ObjectAnimator r12 = android.animation.ObjectAnimator.ofFloat(r1, r4, r12)
            if (r12 != 0) goto L_0x012f
            r12 = 0
            goto L_0x013a
        L_0x012f:
            android.view.animation.AccelerateDecelerateInterpolator r9 = new android.view.animation.AccelerateDecelerateInterpolator
            r9.<init>()
            r12.setInterpolator(r9)
            r12.setDuration(r13)
        L_0x013a:
            if (r12 != 0) goto L_0x013d
            goto L_0x0140
        L_0x013d:
            r2.add(r12)
        L_0x0140:
            float r9 = r1.getScaleX()
            float[] r12 = new float[r8]
            r12[r10] = r9
            r9 = 1
            r12[r9] = r16
            android.animation.ObjectAnimator r9 = android.animation.ObjectAnimator.ofFloat(r1, r15, r12)
            if (r9 != 0) goto L_0x0153
            r9 = 0
            goto L_0x015e
        L_0x0153:
            android.view.animation.AccelerateDecelerateInterpolator r12 = new android.view.animation.AccelerateDecelerateInterpolator
            r12.<init>()
            r9.setInterpolator(r12)
            r9.setDuration(r13)
        L_0x015e:
            if (r9 != 0) goto L_0x0161
            goto L_0x0164
        L_0x0161:
            r2.add(r9)
        L_0x0164:
            float r9 = r6.f5903i
            android.animation.ValueAnimator r9 = r6.a((android.view.View) r1, (float) r9, (long) r13)
            r2.add(r9)
            com.appsamurai.storyly.data.b0 r9 = r29.getStorylyLayerItem$storyly_release()
            float r9 = r9.f3613h
            r12 = 0
            int r9 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r9 != 0) goto L_0x0179
            goto L_0x019f
        L_0x0179:
            float r9 = r29.getSpacingForButtons()
            android.animation.ValueAnimator r9 = r6.b(r1, r9, r13)
            r2.add(r9)
            android.widget.LinearLayout r9 = r29.getEmojiView()
            kotlin.sequences.Sequence r9 = androidx.core.view.ViewGroupKt.getChildren(r9)
            java.lang.Object r9 = kotlin.sequences.SequencesKt.first(r9)
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r9)
            if (r9 == 0) goto L_0x019f
            float r9 = r6.f5903i
            android.animation.ValueAnimator r9 = r6.c(r1, r9, r13)
            r2.add(r9)
        L_0x019f:
            if (r0 != r11) goto L_0x01b5
            r19 = 300(0x12c, double:1.48E-321)
            r0 = r29
            r9 = r2
            r2 = r5
            r12 = r3
            r3 = r7
            r13 = r4
            r14 = r5
            r4 = r19
            android.animation.ValueAnimator r0 = r0.a(r1, r2, r3, r4)
            r9.add(r0)
            goto L_0x01b9
        L_0x01b5:
            r9 = r2
            r12 = r3
            r13 = r4
            r14 = r5
        L_0x01b9:
            com.appsamurai.storyly.storylypresenter.storylylayer.o r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.o
            r0.<init>(r6)
            r12.addListener(r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.n r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.n
            r0.<init>(r6)
            r12.addListener(r0)
            r12.playTogether(r9)
            r12.start()
            r4 = r13
            r5 = r14
            r9 = 1
            r12 = 0
            r13 = 300(0x12c, double:1.48E-321)
            goto L_0x00dd
        L_0x01d7:
            r9 = r12
            r6.f5904j = r9
            goto L_0x03c6
        L_0x01dc:
            r13 = r4
            r9 = r12
            kotlin.jvm.functions.Function5 r0 = r29.getOnUserReaction$storyly_release()
            com.appsamurai.storyly.analytics.a r2 = com.appsamurai.storyly.analytics.a.ReactionClicked
            com.appsamurai.storyly.data.b0 r3 = r29.getStorylyLayerItem$storyly_release()
            com.appsamurai.storyly.data.b0 r4 = r29.getStorylyLayerItem$storyly_release()
            com.appsamurai.storyly.data.t r5 = r6.f5908n
            if (r5 != 0) goto L_0x01f4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r5 = r9
        L_0x01f4:
            java.util.List<java.lang.String> r5 = r5.f4202a
            int r5 = r5.indexOf(r7)
            com.appsamurai.storyly.data.a0 r12 = r4.f3615j
            com.appsamurai.storyly.StoryComponent r4 = r12.a((com.appsamurai.storyly.data.b0) r4, (int) r5)
            kotlinx.serialization.json.JsonObjectBuilder r5 = new kotlinx.serialization.json.JsonObjectBuilder
            r5.<init>()
            kotlinx.serialization.json.JsonElementBuildersKt.put((kotlinx.serialization.json.JsonObjectBuilder) r5, (java.lang.String) r1, (java.lang.String) r7)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            kotlinx.serialization.json.JsonObject r5 = r5.build()
            r12 = 0
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r12
            r0.invoke(r1, r2, r3, r4, r5)
            com.appsamurai.storyly.data.t r0 = r6.f5908n
            if (r0 != 0) goto L_0x021f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = r9
        L_0x021f:
            com.appsamurai.storyly.data.c r0 = r0.a()
            int r12 = r0.f3624a
            com.appsamurai.storyly.data.t r0 = r6.f5908n
            if (r0 != 0) goto L_0x022d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = r9
        L_0x022d:
            com.appsamurai.storyly.data.c r0 = r0.c()
            int r14 = r0.f3624a
            com.appsamurai.storyly.data.t r0 = r6.f5908n
            if (r0 != 0) goto L_0x023b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = r9
        L_0x023b:
            java.util.List<java.lang.String> r0 = r0.f4202a
            java.lang.String r1 = r6.f5904j
            int r4 = kotlin.collections.CollectionsKt.indexOf(r0, r1)
            java.util.List r0 = r29.b(r30)
            java.util.List<android.widget.TextView> r1 = r6.f5905k
            java.util.Iterator r1 = r1.iterator()
            r2 = r10
        L_0x024e:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x028e
            java.lang.Object r3 = r1.next()
            r5 = 1
            int r17 = r2 + 1
            if (r2 >= 0) goto L_0x0260
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x0260:
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            if (r0 != 0) goto L_0x026a
            goto L_0x0276
        L_0x026a:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r2 = com.appsamurai.storyly.util.e.a(r0, r2)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 != 0) goto L_0x0278
        L_0x0276:
            r2 = r10
            goto L_0x027c
        L_0x0278:
            int r2 = r2.intValue()
        L_0x027c:
            r5.append(r2)
            r2 = 37
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r3.setText(r2)
            r2 = r17
            goto L_0x024e
        L_0x028e:
            com.appsamurai.storyly.data.b0 r0 = r29.getStorylyLayerItem$storyly_release()
            java.lang.String r0 = r0.f3614i
            r6.a((java.lang.String) r0, (java.lang.String) r7)
            r29.c(r30)
            android.widget.LinearLayout r0 = r29.getEmojiView()
            kotlin.sequences.Sequence r0 = androidx.core.view.ViewGroupKt.getChildren(r0)
            java.util.Iterator r17 = r0.iterator()
        L_0x02a6:
            boolean r0 = r17.hasNext()
            if (r0 == 0) goto L_0x03c4
            java.lang.Object r0 = r17.next()
            r5 = r0
            android.view.View r5 = (android.view.View) r5
            android.widget.LinearLayout r0 = r29.getEmojiView()
            kotlin.sequences.Sequence r0 = androidx.core.view.ViewGroupKt.getChildren(r0)
            int r0 = kotlin.sequences.SequencesKt___SequencesKt.indexOf(r0, r5)
            android.animation.AnimatorSet r3 = new android.animation.AnimatorSet
            r3.<init>()
            android.view.animation.DecelerateInterpolator r1 = new android.view.animation.DecelerateInterpolator
            r1.<init>()
            r3.setInterpolator(r1)
            com.appsamurai.storyly.data.t r1 = r6.f5908n
            if (r1 != 0) goto L_0x02d4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r1 = r9
        L_0x02d4:
            java.util.List<java.lang.String> r1 = r1.f4202a
            int r1 = r1.indexOf(r7)
            if (r0 != r1) goto L_0x03c0
            r19 = 300(0x12c, double:1.48E-321)
            r0 = r29
            r1 = r5
            r2 = r12
            r9 = r3
            r3 = r14
            r23 = r4
            r24 = r5
            r4 = r19
            android.animation.ValueAnimator r19 = r0.a(r1, r2, r3, r4)
            float r0 = r24.getScaleY()
            r1 = 1066551109(0x3f924745, float:1.1428)
            float[] r2 = new float[r8]
            r2[r10] = r0
            r0 = 1
            r2[r0] = r1
            r0 = r24
            android.animation.ObjectAnimator r2 = android.animation.ObjectAnimator.ofFloat(r0, r13, r2)
            if (r2 != 0) goto L_0x0307
            r20 = 0
            goto L_0x0316
        L_0x0307:
            android.view.animation.AccelerateDecelerateInterpolator r3 = new android.view.animation.AccelerateDecelerateInterpolator
            r3.<init>()
            r2.setInterpolator(r3)
            r3 = 300(0x12c, double:1.48E-321)
            r2.setDuration(r3)
            r20 = r2
        L_0x0316:
            float r2 = r0.getScaleX()
            float[] r3 = new float[r8]
            r3[r10] = r2
            r2 = 1
            r3[r2] = r1
            android.animation.ObjectAnimator r0 = android.animation.ObjectAnimator.ofFloat(r0, r15, r3)
            if (r0 != 0) goto L_0x032a
            r24 = 0
            goto L_0x0339
        L_0x032a:
            android.view.animation.AccelerateDecelerateInterpolator r1 = new android.view.animation.AccelerateDecelerateInterpolator
            r1.<init>()
            r0.setInterpolator(r1)
            r1 = 300(0x12c, double:1.48E-321)
            r0.setDuration(r1)
            r24 = r0
        L_0x0339:
            android.widget.LinearLayout r0 = r29.getEmojiView()
            kotlin.sequences.Sequence r0 = androidx.core.view.ViewGroupKt.getChildren(r0)
            r4 = r23
            java.lang.Object r0 = kotlin.sequences.SequencesKt.elementAt(r0, r4)
            r1 = r0
            android.view.View r1 = (android.view.View) r1
            float r0 = r1.getScaleY()
            float[] r2 = new float[r8]
            r2[r10] = r0
            r0 = 1
            r2[r0] = r16
            android.animation.ObjectAnimator r0 = android.animation.ObjectAnimator.ofFloat(r1, r13, r2)
            if (r0 != 0) goto L_0x035e
            r23 = 0
            goto L_0x036d
        L_0x035e:
            android.view.animation.AccelerateDecelerateInterpolator r2 = new android.view.animation.AccelerateDecelerateInterpolator
            r2.<init>()
            r0.setInterpolator(r2)
            r2 = 300(0x12c, double:1.48E-321)
            r0.setDuration(r2)
            r23 = r0
        L_0x036d:
            float r0 = r1.getScaleX()
            float[] r2 = new float[r8]
            r2[r10] = r0
            r0 = 1
            r2[r0] = r16
            android.animation.ObjectAnimator r0 = android.animation.ObjectAnimator.ofFloat(r1, r15, r2)
            if (r0 != 0) goto L_0x0383
            r2 = 300(0x12c, double:1.48E-321)
            r21 = 0
            goto L_0x0392
        L_0x0383:
            android.view.animation.AccelerateDecelerateInterpolator r2 = new android.view.animation.AccelerateDecelerateInterpolator
            r2.<init>()
            r0.setInterpolator(r2)
            r2 = 300(0x12c, double:1.48E-321)
            r0.setDuration(r2)
            r21 = r0
        L_0x0392:
            r25 = 300(0x12c, double:1.48E-321)
            r0 = r29
            r27 = r2
            r2 = r14
            r3 = r12
            r22 = r4
            r4 = r25
            android.animation.ValueAnimator r0 = r0.a(r1, r2, r3, r4)
            r1 = 6
            android.animation.Animator[] r1 = new android.animation.Animator[r1]
            r1[r10] = r20
            r2 = 1
            r1[r2] = r24
            r1[r8] = r23
            r3 = 3
            r1[r3] = r21
            r3 = 4
            r1[r3] = r0
            r0 = 5
            r1[r0] = r19
            r9.playTogether(r1)
            r9.start()
            r4 = r22
            r9 = 0
            goto L_0x02a6
        L_0x03c0:
            r27 = 300(0x12c, double:1.48E-321)
            goto L_0x02a6
        L_0x03c4:
            r6.f5904j = r7
        L_0x03c6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.p.a(com.appsamurai.storyly.storylypresenter.storylylayer.p, java.lang.String, android.view.View):void");
    }

    @SuppressLint({"Recycle"})
    public final void a(String str, boolean z2) {
        AnimatorSet animatorSet;
        ArrayList arrayList;
        View view;
        Integer num;
        String str2 = str;
        float buttonAnimatedHeight = getButtonAnimatedHeight();
        float spacingForButtons = (buttonAnimatedHeight - this.f5903i) + getSpacingForButtons();
        long j2 = z2 ? 0 : 300;
        if (!z2 && str2 != null) {
            c(str);
        }
        a(getStorylyLayerItem$storyly_release().f3614i, str2);
        for (View next : ViewGroupKt.getChildren(getEmojiView())) {
            int r2 = SequencesKt___SequencesKt.indexOf(ViewGroupKt.getChildren(getEmojiView()), next);
            TextView textView = (TextView) com.appsamurai.storyly.util.e.a(this.f5905k, Integer.valueOf(r2));
            if (textView != null) {
                LinearLayout linearLayout = next instanceof LinearLayout ? (LinearLayout) next : null;
                if (linearLayout != null) {
                    linearLayout.addView(textView);
                }
            }
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setInterpolator(new DecelerateInterpolator());
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(a(next, buttonAnimatedHeight, j2));
            t tVar = this.f5908n;
            if (tVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                tVar = null;
            }
            if (r2 == CollectionsKt.indexOf(tVar.f4202a, str2)) {
                t tVar2 = this.f5908n;
                if (tVar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    tVar2 = null;
                }
                int i3 = tVar2.a().f3624a;
                t tVar3 = this.f5908n;
                if (tVar3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    tVar3 = null;
                }
                arrayList = arrayList2;
                View view2 = next;
                animatorSet = animatorSet2;
                arrayList.add(a(next, i3, tVar3.c().f3624a, j2));
                List<Integer> b3 = b(str);
                int i4 = 0;
                for (T next2 : this.f5905k) {
                    int i5 = i4 + 1;
                    if (i4 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    TextView textView2 = (TextView) next2;
                    StringBuilder sb = new StringBuilder();
                    sb.append((b3 == null || (num = (Integer) com.appsamurai.storyly.util.e.a(b3, Integer.valueOf(i4))) == null) ? 0 : num.intValue());
                    sb.append('%');
                    textView2.setText(sb.toString());
                    i4 = i5;
                }
                view = view2;
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleY", new float[]{view2.getScaleY(), 1.1428f});
                if (ofFloat == null) {
                    ofFloat = null;
                } else {
                    ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
                    ofFloat.setDuration(j2);
                }
                if (ofFloat != null) {
                    arrayList.add(ofFloat);
                }
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleX", new float[]{view.getScaleX(), 1.1428f});
                if (ofFloat2 == null) {
                    ofFloat2 = null;
                } else {
                    ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
                    ofFloat2.setDuration(j2);
                }
                if (ofFloat2 != null) {
                    arrayList.add(ofFloat2);
                }
            } else {
                arrayList = arrayList2;
                view = next;
                animatorSet = animatorSet2;
            }
            if (getStorylyLayerItem$storyly_release().f3613h != 0.0f) {
                arrayList.add(b(view, spacingForButtons, j2));
                if (Intrinsics.areEqual((Object) view, SequencesKt.first(ViewGroupKt.getChildren(getEmojiView())))) {
                    arrayList.add(c(view, buttonAnimatedHeight, j2));
                }
            }
            AnimatorSet animatorSet3 = animatorSet;
            animatorSet3.playTogether(arrayList);
            animatorSet3.start();
        }
    }

    public final AnimatorSet a(View view, int i3) {
        View view2 = view;
        t tVar = this.f5908n;
        ValueAnimator valueAnimator = null;
        if (tVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            tVar = null;
        }
        int i4 = tVar.a().f3624a;
        t tVar2 = this.f5908n;
        if (tVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            tVar2 = null;
        }
        int i5 = tVar2.c().f3624a;
        Drawable background = view.getBackground();
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.setDuration(200);
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        if (ofFloat != null) {
            ofFloat.addUpdateListener(new G0.l(argbEvaluator, i4, i5, gradientDrawable, 2));
            ofFloat.addListener(new g(gradientDrawable, i4, this));
            valueAnimator = ofFloat;
        }
        animatorSet.playTogether(new Animator[]{valueAnimator, ObjectAnimator.ofFloat(this.f5906l.get(i3), "alpha", new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(view2, "scaleY", new float[]{view.getScaleY(), 1.1428f}), ObjectAnimator.ofFloat(view2, "scaleX", new float[]{view.getScaleX(), 1.1428f})});
        return animatorSet;
    }

    public final AnimatorSet a(View view) {
        t tVar = this.f5908n;
        ValueAnimator valueAnimator = null;
        if (tVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            tVar = null;
        }
        int i3 = tVar.a().f3624a;
        t tVar2 = this.f5908n;
        if (tVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            tVar2 = null;
        }
        int i4 = tVar2.c().f3624a;
        Drawable background = view.getBackground();
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.setDuration(200);
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        if (ofFloat != null) {
            ofFloat.addUpdateListener(new G0.l(argbEvaluator, i4, i3, gradientDrawable, 0));
            valueAnimator = ofFloat;
        }
        animatorSet.playTogether(new Animator[]{valueAnimator, ObjectAnimator.ofFloat(view, "scaleY", new float[]{view.getScaleY(), 1.0f}), ObjectAnimator.ofFloat(view, "scaleX", new float[]{view.getScaleX(), 1.0f})});
        return animatorSet;
    }

    public final ValueAnimator a(View view, int i3, int i4, long j2) {
        Drawable background = view.getBackground();
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.setDuration(j2);
        ofFloat.addUpdateListener(new G0.l(argbEvaluator, i3, i4, gradientDrawable, 1));
        return ofFloat;
    }

    public static final void a(ArgbEvaluator argbEvaluator, int i3, int i4, GradientDrawable gradientDrawable, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(argbEvaluator, "$evaluator");
        Object evaluate = argbEvaluator.evaluate(valueAnimator.getAnimatedFraction(), Integer.valueOf(i3), Integer.valueOf(i4));
        if (evaluate != null) {
            int intValue = ((Integer) evaluate).intValue();
            if (gradientDrawable != null) {
                gradientDrawable.setColor(intValue);
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }

    public final ValueAnimator a(View view, float f2, long j2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{view.getMeasuredHeight(), (int) f2});
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(j2);
        ofInt.addUpdateListener(new k(view, 2));
        return ofInt;
    }

    public static final void a(View view, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(view, "$emojiButton");
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            view.getLayoutParams().height = ((Integer) animatedValue).intValue();
            view.requestLayout();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }
}
