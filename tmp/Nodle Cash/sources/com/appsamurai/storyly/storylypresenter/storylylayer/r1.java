package com.appsamurai.storyly.storylypresenter.storylylayer;

import G0.n;
import G0.o;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.e0;
import com.appsamurai.storyly.util.m;
import com.appsamurai.storyly.util.ui.l;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class r1 extends o1 {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f5985g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f5986h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public FrameLayout f5987i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public View f5988j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public Button f5989k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public Button f5990l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public TextView f5991m;

    /* renamed from: n  reason: collision with root package name */
    public int f5992n;

    /* renamed from: o  reason: collision with root package name */
    public int f5993o;

    /* renamed from: p  reason: collision with root package name */
    public int f5994p = 8;

    /* renamed from: q  reason: collision with root package name */
    public final float f5995q = 1.5f;

    /* renamed from: r  reason: collision with root package name */
    public final float f5996r = 1.2f;
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    public final List<Float> f5997s = CollectionsKt.listOf(Float.valueOf(14.0f), Float.valueOf(18.0f), Float.valueOf(22.0f));
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public final List<Integer> f5998t = CollectionsKt.listOf(4, 4, 5);

    /* renamed from: u  reason: collision with root package name */
    public final int f5999u = 40;

    /* renamed from: v  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f6000v;

    /* renamed from: w  reason: collision with root package name */
    public e0 f6001w;
    @NotNull

    /* renamed from: x  reason: collision with root package name */
    public final Lazy f6002x;

    public enum a {
        ALL,
        ONLY_LEFT,
        ONLY_RIGHT
    }

    public enum b {
        ALL_LEFT,
        ALL_RIGHT,
        BOTH
    }

    public /* synthetic */ class c {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[b.values().length];
            iArr[0] = 1;
            iArr[2] = 2;
            iArr[1] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[a.values().length];
            iArr2[0] = 1;
            iArr2[1] = 2;
            iArr2[2] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final class d implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ r1 f6011a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ PropertyValuesHolder f6012b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ Ref.ObjectRef f6013c;

        /* renamed from: d  reason: collision with root package name */
        public final /* synthetic */ Pair f6014d;

        public d(r1 r1Var, PropertyValuesHolder propertyValuesHolder, long j2, Ref.ObjectRef objectRef, Pair pair) {
            this.f6011a = r1Var;
            this.f6012b = propertyValuesHolder;
            this.f6013c = objectRef;
            this.f6014d = pair;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            animatorSet.addListener(new h(this.f6013c, this.f6011a, this.f6014d));
            ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(this.f6011a.f5989k, new PropertyValuesHolder[]{this.f6012b}).setDuration(100);
            Intrinsics.checkNotNullExpressionValue(duration, "ofPropertyValuesHolder(l…on(postAnimationDuration)");
            ObjectAnimator duration2 = ObjectAnimator.ofPropertyValuesHolder(this.f6011a.f5990l, new PropertyValuesHolder[]{this.f6012b}).setDuration(100);
            Intrinsics.checkNotNullExpressionValue(duration2, "ofPropertyValuesHolder(r…on(postAnimationDuration)");
            arrayList.addAll(CollectionsKt.listOf(duration, duration2));
            animatorSet.playTogether(arrayList);
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.start();
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
        }
    }

    public static final class e implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ r1 f6015a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ Ref.ObjectRef f6016b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ int f6017c;

        public e(r1 r1Var, Ref.ObjectRef objectRef, int i3) {
            this.f6015a = r1Var;
            this.f6016b = objectRef;
            this.f6017c = i3;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
            this.f6015a.f5989k.setOnClickListener(f.f6018a);
            this.f6015a.f5990l.setOnClickListener(g.f6019a);
            int ordinal = ((b) this.f6016b.element).ordinal();
            e0 e0Var = null;
            if (ordinal == 0 || ordinal == 1) {
                r1 r1Var = this.f6015a;
                View view = r1Var.f5988j;
                a aVar = a.ALL;
                float f2 = ((float) this.f6017c) / 10.0f;
                e0 e0Var2 = r1Var.f6001w;
                if (e0Var2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                } else {
                    e0Var = e0Var2;
                }
                view.setBackground(r1Var.a(aVar, f2, e0Var.a().f3624a));
            } else if (ordinal == 2) {
                r1 r1Var2 = this.f6015a;
                int i3 = r1Var2.f5999u;
                r1Var2.f5989k.setPadding(i3, 0, 0, 0);
                this.f6015a.f5990l.setPadding(0, 0, i3, 0);
                r1 r1Var3 = this.f6015a;
                View view2 = r1Var3.f5988j;
                a aVar2 = a.ONLY_LEFT;
                float f3 = ((float) this.f6017c) / 10.0f;
                e0 e0Var3 = r1Var3.f6001w;
                if (e0Var3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                } else {
                    e0Var = e0Var3;
                }
                view2.setBackground(r1Var3.a(aVar2, f3, e0Var.a().f3624a));
            }
        }
    }

    public static final class f implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        public static final f f6018a = new f();

        public final void onClick(View view) {
        }
    }

    public static final class g implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        public static final g f6019a = new g();

        public final void onClick(View view) {
        }
    }

    public static final class h implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Ref.ObjectRef f6020a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ r1 f6021b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ Pair f6022c;

        public h(Ref.ObjectRef objectRef, r1 r1Var, Pair pair) {
            this.f6020a = objectRef;
            this.f6021b = r1Var;
            this.f6022c = pair;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
            int ordinal = ((b) this.f6020a.element).ordinal();
            if (ordinal == 0) {
                this.f6021b.f5988j.setVisibility(4);
                this.f6021b.f5990l.setVisibility(4);
                this.f6021b.f5989k.setGravity(3);
                this.f6021b.f5989k.setGravity(17);
                Button button = this.f6021b.f5989k;
                int right = button.getRight();
                int i3 = this.f6021b.f5992n;
                button.setRight((i3 - (i3 / 2)) + right);
                this.f6021b.f5989k.setText((CharSequence) this.f6022c.getFirst());
            } else if (ordinal == 1) {
                this.f6021b.f5988j.setVisibility(4);
                this.f6021b.f5989k.setVisibility(4);
                Button button2 = this.f6021b.f5990l;
                button2.setLeft(button2.getLeft() - (this.f6021b.f5992n / 2));
                this.f6021b.f5990l.setGravity(3);
                this.f6021b.f5990l.setGravity(17);
                this.f6021b.f5990l.setText((CharSequence) this.f6022c.getSecond());
            } else if (ordinal == 2) {
                this.f6021b.f5989k.setGravity(19);
                this.f6021b.f5989k.setText((CharSequence) this.f6022c.getFirst());
                this.f6021b.f5990l.setGravity(21);
                this.f6021b.f5990l.setText((CharSequence) this.f6022c.getSecond());
            }
        }
    }

    public static final class i extends Lambda implements Function0<SharedPreferences> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6023a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(Context context) {
            super(0);
            this.f6023a = context;
        }

        public Object invoke() {
            return this.f6023a.getSharedPreferences("stryly-poll-results", 0);
        }
    }

    public static final class j extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6024a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public j(Context context) {
            super(0);
            this.f6024a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f6024a);
            linearLayout.setOrientation(1);
            return linearLayout;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public r1(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5985g = storylyConfig;
        this.f5986h = LazyKt.lazy(new i(context));
        this.f5988j = new View(context);
        this.f5989k = new Button(context);
        this.f5990l = new Button(context);
        this.f5991m = new TextView(context);
        this.f6002x = LazyKt.lazy(new j(context));
        setImportantForAccessibility(2);
        setLayoutDirection(0);
        Button button = this.f5989k;
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        button.setEllipsize(truncateAt);
        this.f5989k.setMaxLines(2);
        this.f5989k.setPadding(20, 0, 20, 0);
        this.f5989k.setAllCaps(false);
        this.f5990l.setEllipsize(truncateAt);
        this.f5990l.setMaxLines(2);
        this.f5990l.setPadding(20, 0, 20, 0);
        this.f5990l.setAllCaps(false);
        this.f5991m.setEllipsize(truncateAt);
        com.appsamurai.storyly.util.d.a(this.f5991m);
        this.f5991m.setMaxLines(2);
        this.f5991m.setHorizontallyScrolling(false);
    }

    public static final void a(View view) {
    }

    public static final void b(View view) {
    }

    private final SharedPreferences getPollSharedPreferences() {
        return (SharedPreferences) this.f5986h.getValue();
    }

    private final LinearLayout getPollView() {
        return (LinearLayout) this.f6002x.getValue();
    }

    public void c() {
        super.c();
        FrameLayout frameLayout = this.f5987i;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        getPollView().removeAllViews();
        removeAllViews();
        this.f5988j.setVisibility(0);
        this.f5989k.setVisibility(0);
        this.f5990l.setVisibility(0);
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> getOnUserReaction$storyly_release() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f6000v;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    public final void setOnUserReaction$storyly_release(@NotNull Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5) {
        Intrinsics.checkNotNullParameter(function5, "<set-?>");
        this.f6000v = function5;
    }

    public static final void b(r1 r1Var, View view) {
        Intrinsics.checkNotNullParameter(r1Var, "this$0");
        String str = r1Var.getStorylyLayerItem$storyly_release().f3614i;
        SharedPreferences pollSharedPreferences = r1Var.getPollSharedPreferences();
        Intrinsics.checkNotNullExpressionValue(pollSharedPreferences, "pollSharedPreferences");
        SharedPreferences.Editor edit = pollSharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        edit.putBoolean(str, false);
        edit.apply();
        r1Var.a(false, r1Var.f5993o, view);
    }

    public void a(@NotNull d dVar) {
        String str;
        e0 e0Var;
        e0 e0Var2;
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        c();
        float b3 = dVar.b();
        float a2 = dVar.a();
        e0 e0Var3 = this.f6001w;
        if (e0Var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var3 = null;
        }
        if (e0Var3.f3654i) {
            Resources resources = getResources();
            int i3 = R.string.st_desc_poll_with_title;
            e0 e0Var4 = this.f6001w;
            if (e0Var4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var4 = null;
            }
            str = resources.getString(i3, new Object[]{e0Var4.f3650e});
        } else {
            str = getResources().getString(R.string.st_desc_poll_without_title);
        }
        Intrinsics.checkNotNullExpressionValue(str, "if (storylyLayer.hasTitl…_desc_poll_without_title)");
        a(str);
        addView(getPollView(), new FrameLayout.LayoutParams(-1, -1));
        float f2 = (float) 100;
        int a3 = A.a.a(getStorylyLayerItem$storyly_release().f3610e, f2, a2);
        this.f5992n = A.a.a(getStorylyLayerItem$storyly_release().f3609d, f2, b3);
        e0 e0Var5 = this.f6001w;
        if (e0Var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var5 = null;
        }
        int a4 = A.a.a(e0Var5.f3651f, f2, a2);
        this.f5993o = a4;
        setLayoutParams(a(new FrameLayout.LayoutParams(this.f5992n, a3), getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, dVar.c(), dVar.d()));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.f5992n, a3 - a4);
        this.f5991m.setImportantForAccessibility(2);
        this.f5991m.setLayoutDirection(0);
        this.f5991m.setGravity(81);
        this.f5991m.setTextAlignment(1);
        this.f5991m.setPadding(5, 0, 5, 15);
        TextView textView = this.f5991m;
        e0 e0Var6 = this.f6001w;
        if (e0Var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var6 = null;
        }
        com.appsamurai.storyly.data.c cVar = e0Var6.f3660o;
        if (cVar == null) {
            cVar = new com.appsamurai.storyly.data.c(com.appsamurai.storyly.util.f.a((int) ViewCompat.MEASURED_STATE_MASK, 0.32f));
        }
        textView.setShadowLayer(2.0f, 0.0f, 1.0f, cVar.f3624a);
        e0 e0Var7 = this.f6001w;
        if (e0Var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var7 = null;
        }
        if (e0Var7.f3654i) {
            getPollView().addView(this.f5991m, layoutParams);
        }
        this.f5987i = new FrameLayout(getContext());
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(this.f5992n, this.f5993o);
        layoutParams2.gravity = 0;
        FrameLayout frameLayout = this.f5987i;
        if (frameLayout != null) {
            a aVar = a.ALL;
            float f3 = ((float) this.f5993o) / 10.0f;
            e0 e0Var8 = this.f6001w;
            if (e0Var8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var8 = null;
            }
            com.appsamurai.storyly.data.c cVar2 = e0Var8.f3664s;
            if (cVar2 == null && (cVar2 = e0Var8.f3655j) == null) {
                cVar2 = e0Var8.f3671z;
            }
            GradientDrawable gradientDrawable = (GradientDrawable) a(aVar, f3, cVar2.f3624a);
            List<Integer> list = this.f5998t;
            e0 e0Var9 = this.f6001w;
            if (e0Var9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var9 = null;
            }
            int intValue = list.get(e0Var9.f3653h).intValue();
            e0 e0Var10 = this.f6001w;
            if (e0Var10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var10 = null;
            }
            com.appsamurai.storyly.data.c cVar3 = e0Var10.f3658m;
            if (cVar3 == null) {
                cVar3 = new com.appsamurai.storyly.data.c(com.appsamurai.storyly.util.f.a(e0Var10.b().f3624a, 0.166f));
            }
            gradientDrawable.setStroke(intValue, cVar3.f3624a);
            Unit unit = Unit.INSTANCE;
            frameLayout.setBackground(gradientDrawable);
        }
        getPollView().addView(this.f5987i, layoutParams2);
        String str2 = getStorylyLayerItem$storyly_release().f3614i;
        Boolean valueOf = getPollSharedPreferences().contains(str2) ? Boolean.valueOf(getPollSharedPreferences().getBoolean(str2, false)) : null;
        e0 e0Var11 = this.f6001w;
        if (e0Var11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var11 = null;
        }
        if (e0Var11.f3669x || valueOf != null) {
            b bVar = b.BOTH;
            Pair<Integer, Integer> a5 = a(valueOf);
            int intValue2 = a5.component1().intValue();
            int intValue3 = a5.component2().intValue();
            if (intValue3 == 100) {
                bVar = b.ALL_RIGHT;
            }
            if (intValue2 == 100) {
                bVar = b.ALL_LEFT;
            }
            Pair<Spannable, Spannable> a6 = a(intValue2, intValue3);
            this.f5989k.setText(a6.component1());
            this.f5990l.setText(a6.component2());
            this.f5989k.setOnClickListener(new o(0));
            this.f5990l.setOnClickListener(new o(1));
            FrameLayout accessibilityLayerView$storyly_release = getAccessibilityLayerView$storyly_release();
            if (accessibilityLayerView$storyly_release != null) {
                accessibilityLayerView$storyly_release.addChildrenForAccessibility(CollectionsKt.arrayListOf(this.f5989k, this.f5990l));
                Unit unit2 = Unit.INSTANCE;
            }
            this.f5989k.setImportantForAccessibility(1);
            Button button = this.f5989k;
            Resources resources2 = getResources();
            int i4 = R.string.st_desc_poll_after_selection;
            e0 e0Var12 = this.f6001w;
            if (e0Var12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var12 = null;
            }
            button.setContentDescription(resources2.getString(i4, new Object[]{e0Var12.f3648c, Integer.valueOf(intValue2)}));
            this.f5990l.setImportantForAccessibility(1);
            Button button2 = this.f5990l;
            Resources resources3 = getResources();
            e0 e0Var13 = this.f6001w;
            if (e0Var13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var13 = null;
            }
            button2.setContentDescription(resources3.getString(i4, new Object[]{e0Var13.f3649d, Integer.valueOf(intValue3)}));
            int ordinal = bVar.ordinal();
            if (ordinal == 0) {
                this.f5988j.setVisibility(4);
                this.f5990l.setVisibility(4);
                this.f5989k.setGravity(17);
                this.f5989k.setTextAlignment(1);
                FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(this.f5992n, this.f5993o);
                FrameLayout frameLayout2 = this.f5987i;
                if (frameLayout2 != null) {
                    frameLayout2.addView(this.f5989k, layoutParams3);
                    Unit unit3 = Unit.INSTANCE;
                }
            } else if (ordinal == 1) {
                this.f5988j.setVisibility(4);
                this.f5989k.setVisibility(4);
                this.f5990l.setGravity(17);
                this.f5990l.setTextAlignment(1);
                FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(this.f5992n, this.f5993o);
                FrameLayout frameLayout3 = this.f5987i;
                if (frameLayout3 != null) {
                    frameLayout3.addView(this.f5990l, layoutParams4);
                    Unit unit4 = Unit.INSTANCE;
                }
            } else if (ordinal == 2) {
                int i5 = this.f5993o / 4;
                int i6 = this.f5992n;
                int max = Math.max(i5, Math.min(i6 - i5, (int) ((((float) intValue2) / f2) * ((float) i6))));
                int max2 = (int) ((((float) this.f5992n) * ((float) Math.max(25, Math.min(75, intValue2)))) / f2);
                int i7 = this.f5992n - max2;
                FrameLayout.LayoutParams layoutParams5 = new FrameLayout.LayoutParams(max, this.f5993o);
                View view = this.f5988j;
                a aVar2 = a.ONLY_LEFT;
                float f4 = ((float) this.f5993o) / 10.0f;
                e0 e0Var14 = this.f6001w;
                if (e0Var14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    e0Var = null;
                } else {
                    e0Var = e0Var14;
                }
                view.setBackground(a(aVar2, f4, e0Var.a().f3624a));
                FrameLayout frameLayout4 = this.f5987i;
                if (frameLayout4 != null) {
                    frameLayout4.addView(this.f5988j, layoutParams5);
                    Unit unit5 = Unit.INSTANCE;
                }
                FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(max2, this.f5993o);
                FrameLayout frameLayout5 = this.f5987i;
                if (frameLayout5 != null) {
                    frameLayout5.addView(this.f5989k, layoutParams6);
                    Unit unit6 = Unit.INSTANCE;
                }
                this.f5989k.setGravity(17);
                this.f5989k.setTextAlignment(1);
                FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams(i7, this.f5993o);
                layoutParams7.leftMargin = max2;
                FrameLayout frameLayout6 = this.f5987i;
                if (frameLayout6 != null) {
                    frameLayout6.addView(this.f5990l, layoutParams7);
                    Unit unit7 = Unit.INSTANCE;
                }
                this.f5990l.setGravity(17);
                this.f5990l.setTextAlignment(1);
            }
        } else {
            int i8 = this.f5992n;
            int i9 = i8 / 2;
            int i10 = i8 - i9;
            FrameLayout.LayoutParams layoutParams8 = new FrameLayout.LayoutParams(this.f5994p, this.f5993o - 4);
            layoutParams8.gravity = 16;
            layoutParams8.leftMargin = i9 - (this.f5994p / 2);
            Unit unit8 = Unit.INSTANCE;
            FrameLayout frameLayout7 = this.f5987i;
            if (frameLayout7 != null) {
                frameLayout7.addView(this.f5988j, layoutParams8);
            }
            FrameLayout.LayoutParams layoutParams9 = new FrameLayout.LayoutParams(i9, this.f5993o);
            this.f5989k.setBackgroundColor(0);
            FrameLayout frameLayout8 = this.f5987i;
            if (frameLayout8 != null) {
                frameLayout8.addView(this.f5989k, layoutParams9);
            }
            this.f5989k.setOnClickListener(new n(this, 0));
            this.f5989k.setGravity(17);
            this.f5989k.setTextAlignment(1);
            Button button3 = this.f5989k;
            e0 e0Var15 = this.f6001w;
            if (e0Var15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var15 = null;
            }
            button3.setText(e0Var15.f3648c);
            Button button4 = this.f5989k;
            e0 e0Var16 = this.f6001w;
            if (e0Var16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var16 = null;
            }
            com.appsamurai.storyly.data.c cVar4 = e0Var16.f3661p;
            if (cVar4 == null) {
                cVar4 = e0Var16.b();
            }
            button4.setTextColor(cVar4.f3624a);
            FrameLayout.LayoutParams layoutParams10 = new FrameLayout.LayoutParams(i10, this.f5993o);
            layoutParams10.leftMargin = i9;
            this.f5990l.setBackgroundColor(0);
            FrameLayout frameLayout9 = this.f5987i;
            if (frameLayout9 != null) {
                frameLayout9.addView(this.f5990l, layoutParams10);
            }
            this.f5990l.setOnClickListener(new n(this, 1));
            this.f5990l.setGravity(17);
            this.f5990l.setTextAlignment(1);
            Button button5 = this.f5990l;
            e0 e0Var17 = this.f6001w;
            if (e0Var17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var17 = null;
            }
            button5.setText(e0Var17.f3649d);
            Button button6 = this.f5990l;
            e0 e0Var18 = this.f6001w;
            if (e0Var18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var2 = null;
            } else {
                e0Var2 = e0Var18;
            }
            com.appsamurai.storyly.data.c cVar5 = e0Var2.f3662q;
            if (cVar5 == null) {
                cVar5 = e0Var2.b();
            }
            button6.setTextColor(cVar5.f3624a);
        }
    }

    public static final void a(r1 r1Var, View view) {
        Intrinsics.checkNotNullParameter(r1Var, "this$0");
        String str = r1Var.getStorylyLayerItem$storyly_release().f3614i;
        SharedPreferences pollSharedPreferences = r1Var.getPollSharedPreferences();
        Intrinsics.checkNotNullExpressionValue(pollSharedPreferences, "pollSharedPreferences");
        SharedPreferences.Editor edit = pollSharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        edit.putBoolean(str, true);
        edit.apply();
        r1Var.a(true, r1Var.f5993o, view);
    }

    public final Drawable a(a aVar, float f2, int i3) {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.st_rectangle_shape_drawable);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) ((GradientDrawable) drawable).mutate();
            gradientDrawable.setColor(i3);
            float applyDimension = TypedValue.applyDimension(1, f2, getContext().getResources().getDisplayMetrics());
            int i4 = c.$EnumSwitchMapping$1[aVar.ordinal()];
            if (i4 == 1) {
                gradientDrawable.setCornerRadii(new float[]{applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension});
            } else if (i4 == 2) {
                gradientDrawable.setCornerRadii(new float[]{applyDimension, applyDimension, 0.0f, 0.0f, 0.0f, 0.0f, applyDimension, applyDimension});
            } else if (i4 == 3) {
                gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, applyDimension, applyDimension, applyDimension, applyDimension, 0.0f, 0.0f});
            }
            return gradientDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }

    public final void a(boolean z2, int i3, View view) {
        r1 r1Var = this;
        Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> onUserReaction$storyly_release = getOnUserReaction$storyly_release();
        com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.PollSelected;
        b0 storylyLayerItem$storyly_release = getStorylyLayerItem$storyly_release();
        b0 storylyLayerItem$storyly_release2 = getStorylyLayerItem$storyly_release();
        StoryComponent a2 = storylyLayerItem$storyly_release2.f3615j.a(storylyLayerItem$storyly_release2, z2 ^ true ? 1 : 0);
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "activity", z2 ? "L" : "R");
        Unit unit = Unit.INSTANCE;
        onUserReaction$storyly_release.invoke(aVar, storylyLayerItem$storyly_release, a2, jsonObjectBuilder.build(), null);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = b.BOTH;
        Pair<Integer, Integer> a3 = r1Var.a(Boolean.valueOf(z2));
        int intValue = a3.component1().intValue();
        int intValue2 = a3.component2().intValue();
        if (intValue2 == 100) {
            objectRef.element = b.ALL_RIGHT;
        }
        if (intValue == 100) {
            objectRef.element = b.ALL_LEFT;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        Pair<Spannable, Spannable> a4 = r1Var.a(intValue, intValue2);
        r1Var.f5989k.setImportantForAccessibility(1);
        Button button = r1Var.f5989k;
        Resources resources = getResources();
        int i4 = R.string.st_desc_poll_after_selection;
        e0 e0Var = r1Var.f6001w;
        e0 e0Var2 = null;
        if (e0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var = null;
        }
        button.setContentDescription(resources.getString(i4, new Object[]{e0Var.f3648c, Integer.valueOf(intValue)}));
        r1Var.f5990l.setImportantForAccessibility(1);
        Button button2 = r1Var.f5990l;
        Resources resources2 = getResources();
        e0 e0Var3 = r1Var.f6001w;
        if (e0Var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
        } else {
            e0Var2 = e0Var3;
        }
        button2.setContentDescription(resources2.getString(i4, new Object[]{e0Var2.f3649d, Integer.valueOf(intValue2)}));
        if (view != null) {
            l.a(view);
        }
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f});
        ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(r1Var.f5989k, new PropertyValuesHolder[]{ofFloat}).setDuration(400);
        Intrinsics.checkNotNullExpressionValue(duration, "ofPropertyValuesHolder(l…ion(preAnimationDuration)");
        ObjectAnimator duration2 = ObjectAnimator.ofPropertyValuesHolder(r1Var.f5990l, new PropertyValuesHolder[]{ofFloat}).setDuration(400);
        Intrinsics.checkNotNullExpressionValue(duration2, "ofPropertyValuesHolder(r…ion(preAnimationDuration)");
        int ordinal = ((b) objectRef.element).ordinal();
        if (ordinal == 0 || ordinal == 1) {
            ObjectAnimator duration3 = ObjectAnimator.ofPropertyValuesHolder(r1Var.f5988j, new PropertyValuesHolder[]{PropertyValuesHolder.ofInt(TtmlNode.LEFT, new int[]{r1Var.f5988j.getLeft() - ((r1Var.f5992n / 2) - (r1Var.f5994p / 2))}), PropertyValuesHolder.ofInt(TtmlNode.RIGHT, new int[]{((r1Var.f5992n / 2) + r1Var.f5988j.getRight()) - (r1Var.f5994p / 2)})}).setDuration(400);
            Intrinsics.checkNotNullExpressionValue(duration3, "ofPropertyValuesHolder(m…ion(preAnimationDuration)");
            arrayList.add(duration3);
        } else if (ordinal == 2) {
            r1Var = this;
            PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt(TtmlNode.LEFT, new int[]{r1Var.f5988j.getLeft() - ((r1Var.f5992n / 2) - (r1Var.f5994p / 2))});
            int i5 = r1Var.f5993o / 4;
            ObjectAnimator duration4 = ObjectAnimator.ofPropertyValuesHolder(r1Var.f5988j, new PropertyValuesHolder[]{ofInt, PropertyValuesHolder.ofInt(TtmlNode.RIGHT, new int[]{Math.max(i5, Math.min(r1Var.f5992n - i5, (((r1Var.f5992n / 2) + r1Var.f5988j.getRight()) - (r1Var.f5994p / 2)) + (-((int) ((float) Math.ceil((double) ((((float) r1Var.f5992n) * ((float) intValue2)) / ((float) 100))))))))})}).setDuration(400);
            Intrinsics.checkNotNullExpressionValue(duration4, "ofPropertyValuesHolder(m…ion(preAnimationDuration)");
            arrayList.add(duration4);
        }
        arrayList.addAll(CollectionsKt.listOf(duration, duration2));
        animatorSet.addListener(new e(r1Var, objectRef, i3));
        animatorSet.playTogether(arrayList);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
        animatorSet.addListener(new d(this, ofFloat2, 100, objectRef, a4));
    }

    public final Pair<Spannable, Spannable> a(int i3, int i4) {
        Lazy lazy = m.f6342a;
        float f2 = ((float) this.f5993o) / (((float) Resources.getSystem().getDisplayMetrics().densityDpi) / ((float) 160));
        float f3 = (f2 - (f2 / ((float) 10))) / 4.0f;
        this.f5990l.setTextSize(1, f3);
        this.f5989k.setTextSize(1, f3);
        StringBuilder sb = new StringBuilder();
        e0 e0Var = this.f6001w;
        e0 e0Var2 = null;
        if (e0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var = null;
        }
        sb.append(e0Var.f3648c);
        sb.append(10);
        sb.append(i3);
        sb.append('%');
        SpannableString spannableString = new SpannableString(sb.toString());
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(this.f5995q);
        e0 e0Var3 = this.f6001w;
        if (e0Var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var3 = null;
        }
        spannableString.setSpan(relativeSizeSpan, e0Var3.f3648c.length() + 1, spannableString.length() - 1, 0);
        spannableString.setSpan(new RelativeSizeSpan(this.f5996r), spannableString.length() - 1, spannableString.length(), 0);
        e0 e0Var4 = this.f6001w;
        if (e0Var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var4 = null;
        }
        com.appsamurai.storyly.data.c cVar = e0Var4.f3661p;
        if (cVar == null) {
            cVar = e0Var4.b();
        }
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(cVar.f3624a);
        e0 e0Var5 = this.f6001w;
        if (e0Var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var5 = null;
        }
        spannableString.setSpan(foregroundColorSpan, 0, e0Var5.f3648c.length(), 0);
        e0 e0Var6 = this.f6001w;
        if (e0Var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var6 = null;
        }
        com.appsamurai.storyly.data.c cVar2 = e0Var6.f3663r;
        if (cVar2 == null) {
            cVar2 = e0Var6.b();
        }
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(cVar2.f3624a);
        e0 e0Var7 = this.f6001w;
        if (e0Var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var7 = null;
        }
        spannableString.setSpan(foregroundColorSpan2, e0Var7.f3648c.length() + 1, spannableString.length(), 0);
        StringBuilder sb2 = new StringBuilder();
        e0 e0Var8 = this.f6001w;
        if (e0Var8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var8 = null;
        }
        sb2.append(e0Var8.f3649d);
        sb2.append(10);
        sb2.append(i4);
        sb2.append('%');
        SpannableString spannableString2 = new SpannableString(sb2.toString());
        RelativeSizeSpan relativeSizeSpan2 = new RelativeSizeSpan(this.f5995q);
        e0 e0Var9 = this.f6001w;
        if (e0Var9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var9 = null;
        }
        spannableString2.setSpan(relativeSizeSpan2, e0Var9.f3649d.length() + 1, spannableString2.length() - 1, 0);
        spannableString2.setSpan(new RelativeSizeSpan(this.f5996r), spannableString2.length() - 1, spannableString2.length(), 0);
        e0 e0Var10 = this.f6001w;
        if (e0Var10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var10 = null;
        }
        com.appsamurai.storyly.data.c cVar3 = e0Var10.f3662q;
        if (cVar3 == null) {
            cVar3 = e0Var10.b();
        }
        ForegroundColorSpan foregroundColorSpan3 = new ForegroundColorSpan(cVar3.f3624a);
        e0 e0Var11 = this.f6001w;
        if (e0Var11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var11 = null;
        }
        spannableString2.setSpan(foregroundColorSpan3, 0, e0Var11.f3649d.length(), 0);
        e0 e0Var12 = this.f6001w;
        if (e0Var12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            e0Var12 = null;
        }
        com.appsamurai.storyly.data.c cVar4 = e0Var12.f3663r;
        if (cVar4 == null) {
            cVar4 = e0Var12.b();
        }
        ForegroundColorSpan foregroundColorSpan4 = new ForegroundColorSpan(cVar4.f3624a);
        e0 e0Var13 = this.f6001w;
        if (e0Var13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
        } else {
            e0Var2 = e0Var13;
        }
        spannableString2.setSpan(foregroundColorSpan4, e0Var2.f3649d.length() + 1, spannableString2.length(), 0);
        return new Pair<>(spannableString, spannableString2);
    }

    public final Pair<Integer, Integer> a(Boolean bool) {
        int i3;
        int i4;
        e0 e0Var = null;
        if (Intrinsics.areEqual((Object) bool, (Object) Boolean.TRUE)) {
            e0 e0Var2 = this.f6001w;
            if (e0Var2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var2 = null;
            }
            i3 = e0Var2.f3646a + 1;
        } else {
            e0 e0Var3 = this.f6001w;
            if (e0Var3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                e0Var3 = null;
            }
            i3 = e0Var3.f3646a;
        }
        if (Intrinsics.areEqual((Object) bool, (Object) Boolean.FALSE)) {
            e0 e0Var4 = this.f6001w;
            if (e0Var4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            } else {
                e0Var = e0Var4;
            }
            i4 = e0Var.f3647b + 1;
        } else {
            e0 e0Var5 = this.f6001w;
            if (e0Var5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            } else {
                e0Var = e0Var5;
            }
            i4 = e0Var.f3647b;
        }
        int i5 = i3 + i4;
        if (i5 == 0) {
            return new Pair<>(50, 50);
        }
        float f2 = (float) i5;
        float f3 = (float) 100;
        int ceil = (int) ((float) Math.ceil((double) ((((float) i3) / f2) * f3)));
        int ceil2 = (int) ((float) Math.ceil((double) ((((float) i4) / f2) * f3)));
        if (ceil < ceil2) {
            ceil2 = 100 - ceil;
        } else {
            ceil = 100 - ceil2;
        }
        return new Pair<>(Integer.valueOf(ceil), Integer.valueOf(ceil2));
    }
}
