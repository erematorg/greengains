package com.appsamurai.storyly.storylypresenter.storylylayer;

import G0.e;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.k0;
import com.appsamurai.storyly.util.ui.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;

public final class b2 extends o1 {

    /* renamed from: A  reason: collision with root package name */
    public int f5626A;

    /* renamed from: B  reason: collision with root package name */
    public int f5627B;

    /* renamed from: C  reason: collision with root package name */
    public int f5628C;
    @NotNull

    /* renamed from: D  reason: collision with root package name */
    public final Lazy f5629D;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f5630g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f5631h;

    /* renamed from: i  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f5632i;

    /* renamed from: j  reason: collision with root package name */
    public k0 f5633j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final List<Float> f5634k = CollectionsKt.listOf(Float.valueOf(11.0f), Float.valueOf(15.0f), Float.valueOf(19.0f));
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final List<Float> f5635l = CollectionsKt.listOf(Float.valueOf(9.0f), Float.valueOf(12.0f), Float.valueOf(16.0f));
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final List<Float> f5636m = CollectionsKt.listOf(Float.valueOf(1.2f), Float.valueOf(1.8f), Float.valueOf(2.4f));
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final List<Float> f5637n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final List<Float> f5638o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public final List<Float> f5639p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public final List<Integer> f5640q;
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public List<RelativeLayout> f5641r;
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    public List<View> f5642s;
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public List<ImageView> f5643t;
    @NotNull

    /* renamed from: u  reason: collision with root package name */
    public List<TextView> f5644u;
    @NotNull

    /* renamed from: v  reason: collision with root package name */
    public TextView f5645v;
    @NotNull

    /* renamed from: w  reason: collision with root package name */
    public final List<Integer> f5646w;
    @NotNull

    /* renamed from: x  reason: collision with root package name */
    public final List<Integer> f5647x;
    @NotNull

    /* renamed from: y  reason: collision with root package name */
    public final List<Integer> f5648y;

    /* renamed from: z  reason: collision with root package name */
    public int f5649z;

    public enum a {
        ALL,
        NONE,
        TOP,
        BOTTOM
    }

    public /* synthetic */ class b {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[a.values().length];
            iArr[2] = 1;
            iArr[3] = 2;
            iArr[0] = 3;
            iArr[1] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final class c extends Lambda implements Function0<SharedPreferences> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5655a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5655a = context;
        }

        public Object invoke() {
            return this.f5655a.getSharedPreferences("stryly-quiz-results", 0);
        }
    }

    public static final class d extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5656a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5656a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5656a);
            linearLayout.setOrientation(1);
            return linearLayout;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b2(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5630g = storylyConfig;
        this.f5631h = LazyKt.lazy(new c(context));
        Float valueOf = Float.valueOf(2.5f);
        this.f5637n = CollectionsKt.listOf(valueOf, valueOf, valueOf);
        this.f5638o = CollectionsKt.listOf(valueOf, Float.valueOf(3.5f), Float.valueOf(4.5f));
        this.f5639p = CollectionsKt.listOf(Float.valueOf(3.3f), Float.valueOf(4.4f), Float.valueOf(5.5f));
        this.f5640q = CollectionsKt.listOf(2, 2, 3);
        this.f5641r = new ArrayList();
        this.f5642s = new ArrayList();
        this.f5643t = new ArrayList();
        this.f5644u = new ArrayList();
        this.f5645v = new TextView(context);
        this.f5646w = CollectionsKt.listOf(Integer.valueOf(R.drawable.st_quiz_light_a), Integer.valueOf(R.drawable.st_quiz_light_b), Integer.valueOf(R.drawable.st_quiz_light_c), Integer.valueOf(R.drawable.st_quiz_light_d));
        this.f5647x = CollectionsKt.listOf(Integer.valueOf(R.drawable.st_quiz_dark_a), Integer.valueOf(R.drawable.st_quiz_dark_b), Integer.valueOf(R.drawable.st_quiz_dark_c), Integer.valueOf(R.drawable.st_quiz_dark_d));
        this.f5648y = CollectionsKt.listOf(Integer.valueOf(R.string.st_desc_option_a), Integer.valueOf(R.string.st_desc_option_b), Integer.valueOf(R.string.st_desc_option_c), Integer.valueOf(R.string.st_desc_option_d));
        this.f5629D = LazyKt.lazy(new d(context));
        setImportantForAccessibility(2);
    }

    private final SharedPreferences getQuizSharedPreferences() {
        return (SharedPreferences) this.f5631h.getValue();
    }

    private final LinearLayout getQuizView() {
        return (LinearLayout) this.f5629D.getValue();
    }

    public void a(@NotNull d dVar) {
        String str;
        Unit unit;
        Integer num;
        boolean z2;
        Integer valueOf;
        int i3 = 0;
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        c();
        float b3 = dVar.b();
        float a2 = dVar.a();
        k0 k0Var = this.f5633j;
        if (k0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var = null;
        }
        if (k0Var.f3814h) {
            Resources resources = getResources();
            int i4 = R.string.st_desc_quiz_with_title;
            k0 k0Var2 = this.f5633j;
            if (k0Var2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var2 = null;
            }
            str = resources.getString(i4, new Object[]{k0Var2.f3809c});
        } else {
            str = getResources().getString(R.string.st_desc_quiz_without_title);
        }
        Intrinsics.checkNotNullExpressionValue(str, "if (storylyLayer.hasTitl…_desc_quiz_without_title)");
        a(str);
        int i5 = -1;
        addView(getQuizView(), new FrameLayout.LayoutParams(-1, -1));
        k0 k0Var3 = this.f5633j;
        if (k0Var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var3 = null;
        }
        List<Integer> list = Intrinsics.areEqual((Object) k0Var3.f3807a, (Object) "Dark") ? this.f5647x : this.f5646w;
        float f2 = (float) 100;
        int a3 = A.a.a(getStorylyLayerItem$storyly_release().f3610e, f2, a2);
        this.f5627B = A.a.a(getStorylyLayerItem$storyly_release().f3609d, f2, b3);
        k0 k0Var4 = this.f5633j;
        if (k0Var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var4 = null;
        }
        this.f5628C = A.a.a(k0Var4.f3808b, f2, a2);
        List<Float> list2 = this.f5636m;
        k0 k0Var5 = this.f5633j;
        if (k0Var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var5 = null;
        }
        int floatValue = (int) ((list2.get(k0Var5.f3813g).floatValue() * a2) / f2);
        List<Float> list3 = this.f5638o;
        k0 k0Var6 = this.f5633j;
        if (k0Var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var6 = null;
        }
        this.f5626A = (int) ((list3.get(k0Var6.f3813g).floatValue() * b3) / f2);
        int i6 = this.f5628C + floatValue;
        k0 k0Var7 = this.f5633j;
        if (k0Var7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var7 = null;
        }
        int size = (a3 - (k0Var7.f3810d.size() * i6)) - floatValue;
        List<Float> list4 = this.f5637n;
        k0 k0Var8 = this.f5633j;
        if (k0Var8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var8 = null;
        }
        this.f5649z = (int) ((list4.get(k0Var8.f3813g).floatValue() * b3) / f2);
        List<Float> list5 = this.f5639p;
        k0 k0Var9 = this.f5633j;
        if (k0Var9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var9 = null;
        }
        int floatValue2 = (int) ((list5.get(k0Var9.f3813g).floatValue() * b3) / f2);
        k0 k0Var10 = this.f5633j;
        if (k0Var10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var10 = null;
        }
        if (!k0Var10.f3814h) {
            a3 -= size;
        }
        int i7 = floatValue2;
        setLayoutParams(a(new FrameLayout.LayoutParams(this.f5627B, a3), getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, dVar.c(), dVar.d()));
        a aVar = a.ALL;
        k0 k0Var11 = this.f5633j;
        if (k0Var11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var11 = null;
        }
        com.appsamurai.storyly.data.c cVar = k0Var11.f3815i;
        if (cVar == null) {
            cVar = Intrinsics.areEqual((Object) k0Var11.f3807a, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_141414.b() : new com.appsamurai.storyly.data.c(-1);
        }
        GradientDrawable gradientDrawable = (GradientDrawable) a(aVar, 15.0f, cVar.f3624a);
        k0 k0Var12 = this.f5633j;
        if (k0Var12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var12 = null;
        }
        com.appsamurai.storyly.data.c cVar2 = k0Var12.f3825s;
        if (cVar2 == null) {
            cVar2 = (Intrinsics.areEqual((Object) k0Var12.f3807a, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_3D3D3D : com.appsamurai.storyly.config.styling.a.COLOR_E0E0E0).b();
        }
        gradientDrawable.setStroke(1, cVar2.f3624a);
        Unit unit2 = Unit.INSTANCE;
        setBackground(gradientDrawable);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f5627B, size);
        k0 k0Var13 = this.f5633j;
        if (k0Var13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var13 = null;
        }
        if (k0Var13.f3814h) {
            getQuizView().addView(this.f5645v, layoutParams);
        }
        this.f5645v.setPadding(i7, 0, i7, 0);
        TextView textView = this.f5645v;
        a aVar2 = a.TOP;
        k0 k0Var14 = this.f5633j;
        if (k0Var14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var14 = null;
        }
        com.appsamurai.storyly.data.c cVar3 = k0Var14.f3817k;
        if (cVar3 == null) {
            cVar3 = Intrinsics.areEqual((Object) k0Var14.f3807a, (Object) "Dark") ? new com.appsamurai.storyly.data.c(-1) : com.appsamurai.storyly.config.styling.a.COLOR_141414.b();
        }
        textView.setBackground(a(aVar2, 15.0f, cVar3.f3624a));
        this.f5645v.setMaxLines(2);
        this.f5645v.setEllipsize(TextUtils.TruncateAt.END);
        this.f5645v.setGravity(17);
        this.f5645v.setTextAlignment(1);
        TextView textView2 = this.f5645v;
        k0 k0Var15 = this.f5633j;
        if (k0Var15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var15 = null;
        }
        com.appsamurai.storyly.data.c cVar4 = k0Var15.f3816j;
        if (cVar4 == null) {
            cVar4 = Intrinsics.areEqual((Object) k0Var15.f3807a, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_141414.b() : new com.appsamurai.storyly.data.c(-1);
        }
        textView2.setTextColor(cVar4.f3624a);
        TextView textView3 = this.f5645v;
        k0 k0Var16 = this.f5633j;
        if (k0Var16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var16 = null;
        }
        textView3.setText(k0Var16.f3809c);
        this.f5645v.setTypeface(this.f5630g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        TextView textView4 = this.f5645v;
        k0 k0Var17 = this.f5633j;
        if (k0Var17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var17 = null;
        }
        boolean z3 = k0Var17.f3826t;
        k0 k0Var18 = this.f5633j;
        if (k0Var18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var18 = null;
        }
        com.appsamurai.storyly.util.c.a(textView4, z3, k0Var18.f3827u);
        this.f5645v.setImportantForAccessibility(2);
        TextView textView5 = this.f5645v;
        List<Float> list6 = this.f5634k;
        k0 k0Var19 = this.f5633j;
        if (k0Var19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var19 = null;
        }
        textView5.setTextSize(1, list6.get(k0Var19.f3813g).floatValue());
        k0 k0Var20 = this.f5633j;
        if (k0Var20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var20 = null;
        }
        int i8 = 0;
        for (T next : k0Var20.f3810d) {
            int i9 = i8 + 1;
            if (i8 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str2 = (String) next;
            String string = getResources().getString(this.f5648y.get(i8).intValue());
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(optionNames[index])");
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            relativeLayout.setImportantForAccessibility(1);
            relativeLayout.setContentDescription(getResources().getString(R.string.st_desc_quiz_before, new Object[]{string, str2}));
            Unit unit3 = Unit.INSTANCE;
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(this.f5627B - (this.f5626A * 2), this.f5628C);
            layoutParams2.topMargin = floatValue;
            layoutParams2.setMarginStart(this.f5626A);
            getQuizView().addView(relativeLayout, layoutParams2);
            relativeLayout.setOnClickListener(new G0.c(this, i8, i3));
            l.a(relativeLayout, new com.appsamurai.storyly.util.ui.c((View) null));
            a aVar3 = a.ALL;
            float f3 = ((float) this.f5628C) / 2.0f;
            k0 k0Var21 = this.f5633j;
            if (k0Var21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var21 = null;
            }
            GradientDrawable gradientDrawable2 = (GradientDrawable) a(aVar3, f3, k0Var21.a().f3624a);
            List<Integer> list7 = this.f5640q;
            k0 k0Var22 = this.f5633j;
            if (k0Var22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var22 = null;
            }
            int intValue = list7.get(k0Var22.f3813g).intValue();
            k0 k0Var23 = this.f5633j;
            if (k0Var23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var23 = null;
            }
            gradientDrawable2.setStroke(intValue, k0Var23.b().f3624a);
            relativeLayout.setBackground(gradientDrawable2);
            View view = new View(getContext());
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(20);
            view.setVisibility(8);
            relativeLayout.addView(view, layoutParams3);
            ImageView imageView = new ImageView(getContext());
            imageView.setId(i9);
            imageView.setAdjustViewBounds(true);
            imageView.setImageResource(list.get(i8).intValue());
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            int i10 = this.f5628C / 5;
            layoutParams4.topMargin = i10;
            layoutParams4.bottomMargin = i10;
            layoutParams4.setMarginStart(this.f5649z);
            relativeLayout.addView(imageView, layoutParams4);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            TextView textView6 = new TextView(getContext());
            textView6.setId(i9 * 4);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, i5);
            layoutParams5.addRule(17, imageView.getId());
            layoutParams5.setMarginStart(this.f5649z);
            layoutParams5.setMarginEnd(this.f5649z * 2);
            textView6.setEllipsize(TextUtils.TruncateAt.END);
            com.appsamurai.storyly.util.d.a(textView6);
            textView6.setMaxLines(2);
            textView6.setGravity(8388627);
            textView6.setTextAlignment(1);
            textView6.setText(str2);
            relativeLayout.addView(textView6, layoutParams5);
            textView6.setTypeface(this.f5630g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
            k0 k0Var24 = this.f5633j;
            if (k0Var24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var24 = null;
            }
            boolean z4 = k0Var24.f3828v;
            k0 k0Var25 = this.f5633j;
            if (k0Var25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var25 = null;
            }
            com.appsamurai.storyly.util.c.a(textView6, z4, k0Var25.f3829w);
            k0 k0Var26 = this.f5633j;
            if (k0Var26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var26 = null;
            }
            textView6.setTextColor(k0Var26.c().f3624a);
            List<Float> list8 = this.f5635l;
            k0 k0Var27 = this.f5633j;
            if (k0Var27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var27 = null;
            }
            textView6.setTextSize(1, list8.get(k0Var27.f3813g).floatValue());
            this.f5641r.add(relativeLayout);
            this.f5643t.add(imageView);
            this.f5644u.add(textView6);
            this.f5642s.add(view);
            i8 = i9;
            i3 = 0;
            i5 = -1;
        }
        FrameLayout accessibilityLayerView$storyly_release = getAccessibilityLayerView$storyly_release();
        if (accessibilityLayerView$storyly_release != null) {
            accessibilityLayerView$storyly_release.addChildrenForAccessibility(new ArrayList(this.f5641r));
            Unit unit4 = Unit.INSTANCE;
        }
        String str3 = getStorylyLayerItem$storyly_release().f3614i;
        Integer valueOf2 = getQuizSharedPreferences().contains(str3) ? Integer.valueOf(getQuizSharedPreferences().getInt(str3, -1)) : null;
        k0 k0Var28 = this.f5633j;
        if (k0Var28 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var28 = null;
        }
        Integer num2 = k0Var28.f3812f;
        if (num2 == null) {
            unit = null;
        } else {
            int intValue2 = num2.intValue();
            k0 k0Var29 = this.f5633j;
            if (k0Var29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var29 = null;
            }
            if (!k0Var29.f3830x) {
                valueOf = valueOf2;
            } else {
                valueOf = Integer.valueOf(intValue2);
            }
            a(valueOf, intValue2, false);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            k0 k0Var30 = this.f5633j;
            if (k0Var30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var30 = null;
            }
            if (!k0Var30.f3830x) {
                num = valueOf2;
                z2 = false;
            } else {
                z2 = false;
                num = null;
            }
            a(num, z2);
            Unit unit5 = Unit.INSTANCE;
        }
    }

    public void c() {
        super.c();
        for (RelativeLayout removeAllViews : this.f5641r) {
            removeAllViews.removeAllViews();
        }
        this.f5641r.clear();
        this.f5642s.clear();
        this.f5643t.clear();
        this.f5644u.clear();
        getQuizView().removeAllViews();
        removeAllViews();
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> getOnUserReaction$storyly_release() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f5632i;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    public final void setOnUserReaction$storyly_release(@NotNull Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5) {
        Intrinsics.checkNotNullParameter(function5, "<set-?>");
        this.f5632i = function5;
    }

    public static final void a(b2 b2Var, int i3, View view) {
        Unit unit;
        Intrinsics.checkNotNullParameter(b2Var, "this$0");
        Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> onUserReaction$storyly_release = b2Var.getOnUserReaction$storyly_release();
        com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.QuizAnswered;
        b0 storylyLayerItem$storyly_release = b2Var.getStorylyLayerItem$storyly_release();
        b0 storylyLayerItem$storyly_release2 = b2Var.getStorylyLayerItem$storyly_release();
        StoryComponent a2 = storylyLayerItem$storyly_release2.f3615j.a(storylyLayerItem$storyly_release2, i3);
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "activity", String.valueOf(i3));
        Unit unit2 = Unit.INSTANCE;
        onUserReaction$storyly_release.invoke(aVar, storylyLayerItem$storyly_release, a2, jsonObjectBuilder.build(), null);
        String str = b2Var.getStorylyLayerItem$storyly_release().f3614i;
        SharedPreferences quizSharedPreferences = b2Var.getQuizSharedPreferences();
        Intrinsics.checkNotNullExpressionValue(quizSharedPreferences, "quizSharedPreferences");
        SharedPreferences.Editor edit = quizSharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        edit.putInt(str, i3);
        edit.apply();
        Iterator<T> it = b2Var.f5641r.iterator();
        while (true) {
            unit = null;
            if (!it.hasNext()) {
                break;
            }
            ((RelativeLayout) it.next()).setOnClickListener((View.OnClickListener) null);
        }
        k0 k0Var = b2Var.f5633j;
        if (k0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            k0Var = null;
        }
        Integer num = k0Var.f3812f;
        if (num != null) {
            b2Var.a(Integer.valueOf(i3), num.intValue(), true);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            b2Var.a(Integer.valueOf(i3), true);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: android.view.View$OnClickListener} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: android.view.View$OnClickListener} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: com.appsamurai.storyly.data.k0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: com.appsamurai.storyly.data.k0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: android.view.View$OnClickListener} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v17, resolved type: com.appsamurai.storyly.data.k0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v18, resolved type: com.appsamurai.storyly.data.k0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v19, resolved type: com.appsamurai.storyly.data.k0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v20, resolved type: com.appsamurai.storyly.data.k0} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e5 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.Integer r17, boolean r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = 1
            java.lang.String r2 = "storylyLayer"
            r3 = 0
            if (r17 != 0) goto L_0x0016
            com.appsamurai.storyly.data.k0 r4 = r0.f5633j
            if (r4 != 0) goto L_0x0011
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r4 = r3
        L_0x0011:
            boolean r4 = r4.f3830x
            if (r4 != 0) goto L_0x0016
            return
        L_0x0016:
            com.appsamurai.storyly.data.k0 r4 = r0.f5633j
            if (r4 != 0) goto L_0x001e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r4 = r3
        L_0x001e:
            java.util.List<java.lang.Integer> r4 = r4.f3811e
            r5 = 100
            r6 = 0
            if (r4 != 0) goto L_0x0027
            goto L_0x00e2
        L_0x0027:
            int r7 = r4.size()
            com.appsamurai.storyly.data.k0 r8 = r0.f5633j
            if (r8 != 0) goto L_0x0033
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r8 = r3
        L_0x0033:
            java.util.List<java.lang.String> r8 = r8.f3810d
            int r8 = r8.size()
            if (r7 == r8) goto L_0x003d
            goto L_0x00e2
        L_0x003d:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            com.appsamurai.storyly.data.k0 r8 = r0.f5633j
            if (r8 != 0) goto L_0x004a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r8 = r3
        L_0x004a:
            boolean r8 = r8.f3830x
            if (r8 != 0) goto L_0x0057
            r8 = r4
            java.util.Collection r8 = (java.util.Collection) r8
            int r8 = kotlin.collections.CollectionsKt___CollectionsKt.sumOfInt(r8)
            int r8 = r8 + r1
            goto L_0x005e
        L_0x0057:
            r8 = r4
            java.util.Collection r8 = (java.util.Collection) r8
            int r8 = kotlin.collections.CollectionsKt___CollectionsKt.sumOfInt(r8)
        L_0x005e:
            if (r8 == 0) goto L_0x009b
            java.util.Iterator r4 = r4.iterator()
            r9 = r6
        L_0x0065:
            boolean r10 = r4.hasNext()
            if (r10 == 0) goto L_0x00b4
            java.lang.Object r10 = r4.next()
            int r11 = r9 + 1
            if (r9 >= 0) goto L_0x0076
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x0076:
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            if (r17 != 0) goto L_0x007f
            goto L_0x0086
        L_0x007f:
            int r12 = r17.intValue()
            if (r12 != r9) goto L_0x0086
            int r10 = r10 + r1
        L_0x0086:
            float r9 = (float) r10
            float r10 = (float) r8
            float r9 = r9 / r10
            float r10 = (float) r5
            float r9 = r9 * r10
            double r9 = (double) r9
            double r9 = java.lang.Math.ceil(r9)
            float r9 = (float) r9
            int r9 = (int) r9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r7.add(r9)
            r9 = r11
            goto L_0x0065
        L_0x009b:
            int r8 = r4.size()
            r9 = r6
        L_0x00a0:
            if (r9 >= r8) goto L_0x00b4
            int r10 = r4.size()
            double r10 = (double) r10
            r12 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r12 = r12 / r10
            double r10 = java.lang.Math.ceil(r12)
            int r10 = (int) r10
            int r9 = android.support.v4.media.session.a.e(r10, r7, r9, r1)
            goto L_0x00a0
        L_0x00b4:
            int r4 = kotlin.collections.CollectionsKt___CollectionsKt.sumOfInt(r7)
            if (r4 == r5) goto L_0x00e3
            java.lang.Comparable r4 = kotlin.collections.CollectionsKt.maxOrNull(r7)
            java.lang.Integer r4 = (java.lang.Integer) r4
            if (r4 != 0) goto L_0x00c4
            r4 = r3
            goto L_0x00e0
        L_0x00c4:
            int r4 = r4.intValue()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            int r8 = r7.indexOf(r8)
            int r9 = kotlin.collections.CollectionsKt___CollectionsKt.sumOfInt(r7)
            int r9 = r9 - r4
            int r4 = 100 - r9
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r7.set(r8, r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
        L_0x00e0:
            if (r4 != 0) goto L_0x00b4
        L_0x00e2:
            r7 = r3
        L_0x00e3:
            if (r7 != 0) goto L_0x00e6
            return
        L_0x00e6:
            java.util.List<android.widget.RelativeLayout> r4 = r0.f5641r
            java.util.Iterator r4 = r4.iterator()
            r8 = r6
        L_0x00ed:
            boolean r9 = r4.hasNext()
            if (r9 == 0) goto L_0x0340
            java.lang.Object r9 = r4.next()
            int r10 = r8 + 1
            if (r8 >= 0) goto L_0x00fe
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x00fe:
            android.widget.RelativeLayout r9 = (android.widget.RelativeLayout) r9
            r9.setOnClickListener(r3)
            android.content.res.Resources r11 = r16.getResources()
            java.util.List<java.lang.Integer> r12 = r0.f5648y
            java.lang.Object r12 = r12.get(r8)
            java.lang.Number r12 = (java.lang.Number) r12
            int r12 = r12.intValue()
            java.lang.String r11 = r11.getString(r12)
            java.lang.String r12 = "resources.getString(optionNames[pointer])"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r12)
            java.util.List<android.widget.ImageView> r12 = r0.f5643t
            java.lang.Object r12 = r12.get(r8)
            android.view.View r12 = (android.view.View) r12
            r9.removeView(r12)
            r9.setImportantForAccessibility(r1)
            android.content.res.Resources r12 = r9.getResources()
            int r13 = com.appsamurai.storyly.R.string.st_desc_quiz_after_poll
            com.appsamurai.storyly.data.k0 r14 = r0.f5633j
            if (r14 != 0) goto L_0x0138
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r14 = r3
        L_0x0138:
            java.util.List<java.lang.String> r14 = r14.f3810d
            java.lang.Object r14 = r14.get(r8)
            java.lang.Object r15 = r7.get(r8)
            java.lang.Object[] r11 = new java.lang.Object[]{r11, r14, r15}
            java.lang.String r11 = r12.getString(r13, r11)
            r9.setContentDescription(r11)
            java.lang.Object r11 = r7.get(r8)
            java.lang.Number r11 = (java.lang.Number) r11
            int r11 = r11.intValue()
            android.widget.TextView r12 = new android.widget.TextView
            android.content.Context r13 = r16.getContext()
            r12.<init>(r13)
            r12.setId(r10)
            java.lang.String r13 = "100%"
            r12.setText(r13)
            com.appsamurai.storyly.config.StorylyConfig r13 = r0.f5630g
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r13 = r13.getStory$storyly_release()
            android.graphics.Typeface r13 = r13.getInteractiveTypeface$storyly_release()
            r12.setTypeface(r13)
            java.util.List<java.lang.Float> r13 = r0.f5635l
            com.appsamurai.storyly.data.k0 r14 = r0.f5633j
            if (r14 != 0) goto L_0x017f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r14 = r3
        L_0x017f:
            int r14 = r14.f3813g
            java.lang.Object r13 = r13.get(r14)
            java.lang.Number r13 = (java.lang.Number) r13
            float r13 = r13.floatValue()
            r12.setTextSize(r1, r13)
            r12.setTextColor(r6)
            r13 = 17
            r12.setGravity(r13)
            r12.setTextAlignment(r1)
            android.widget.RelativeLayout$LayoutParams r13 = new android.widget.RelativeLayout$LayoutParams
            r14 = -2
            r15 = -1
            r13.<init>(r14, r15)
            int r3 = r0.f5649z
            r13.setMarginStart(r3)
            r9.addView(r12, r13)
            android.widget.TextView r3 = new android.widget.TextView
            android.content.Context r13 = r16.getContext()
            r3.<init>(r13)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r11)
            r6 = 37
            r13.append(r6)
            java.lang.String r6 = r13.toString()
            r3.setText(r6)
            com.appsamurai.storyly.config.StorylyConfig r6 = r0.f5630g
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r6 = r6.getStory$storyly_release()
            android.graphics.Typeface r6 = r6.getInteractiveTypeface$storyly_release()
            r3.setTypeface(r6)
            java.util.List<java.lang.Float> r6 = r0.f5635l
            com.appsamurai.storyly.data.k0 r13 = r0.f5633j
            if (r13 != 0) goto L_0x01dc
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r13 = 0
        L_0x01dc:
            int r13 = r13.f3813g
            java.lang.Object r6 = r6.get(r13)
            java.lang.Number r6 = (java.lang.Number) r6
            float r6 = r6.floatValue()
            r3.setTextSize(r1, r6)
            com.appsamurai.storyly.data.k0 r6 = r0.f5633j
            if (r6 != 0) goto L_0x01f3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r6 = 0
        L_0x01f3:
            com.appsamurai.storyly.data.c r6 = r6.c()
            int r6 = r6.f3624a
            r3.setTextColor(r6)
            r6 = 8388627(0x800013, float:1.175497E-38)
            r3.setGravity(r6)
            r3.setTextAlignment(r1)
            android.widget.RelativeLayout$LayoutParams r6 = new android.widget.RelativeLayout$LayoutParams
            r6.<init>(r14, r15)
            int r13 = r12.getId()
            r14 = 18
            r6.addRule(r14, r13)
            int r12 = r12.getId()
            r13 = 19
            r6.addRule(r13, r12)
            r9.addView(r3, r6)
            java.util.List<android.view.View> r3 = r0.f5642s
            java.lang.Object r3 = r3.get(r8)
            android.view.View r3 = (android.view.View) r3
            java.lang.String r6 = "Dark"
            r12 = 1073741824(0x40000000, float:2.0)
            if (r11 <= 0) goto L_0x02b1
            int r13 = r0.f5627B
            int r14 = r0.f5626A
            int r14 = r14 * 2
            int r13 = r13 - r14
            float r14 = (float) r13
            float r11 = (float) r11
            float r14 = r14 * r11
            float r11 = (float) r5
            float r14 = r14 / r11
            double r14 = (double) r14
            double r14 = java.lang.Math.ceil(r14)
            float r11 = (float) r14
            int r11 = (int) r11
            com.appsamurai.storyly.storylypresenter.storylylayer.b2$a r14 = com.appsamurai.storyly.storylypresenter.storylylayer.b2.a.ALL
            int r15 = r0.f5628C
            float r15 = (float) r15
            float r15 = r15 / r12
            com.appsamurai.storyly.data.k0 r1 = r0.f5633j
            if (r1 != 0) goto L_0x024e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = 0
        L_0x024e:
            com.appsamurai.storyly.data.c r5 = r1.f3823q
            if (r5 != 0) goto L_0x0264
            java.lang.String r1 = r1.f3807a
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r6)
            if (r1 == 0) goto L_0x0261
            com.appsamurai.storyly.config.styling.a r1 = com.appsamurai.storyly.config.styling.a.COLOR_7A7A7A
        L_0x025c:
            com.appsamurai.storyly.data.c r5 = r1.b()
            goto L_0x0264
        L_0x0261:
            com.appsamurai.storyly.config.styling.a r1 = com.appsamurai.storyly.config.styling.a.COLOR_F1F1F1
            goto L_0x025c
        L_0x0264:
            int r1 = r5.f3624a
            android.graphics.drawable.Drawable r1 = r0.a((com.appsamurai.storyly.storylypresenter.storylylayer.b2.a) r14, (float) r15, (int) r1)
            r3.setBackground(r1)
            r1 = 0
            r3.setVisibility(r1)
            android.view.ViewGroup$LayoutParams r5 = r3.getLayoutParams()
            if (r5 == 0) goto L_0x02a9
            android.widget.RelativeLayout$LayoutParams r5 = (android.widget.RelativeLayout.LayoutParams) r5
            if (r18 == 0) goto L_0x02a3
            r3.setLayoutParams(r5)
            android.animation.ValueAnimator r5 = new android.animation.ValueAnimator
            r5.<init>()
            int[] r11 = new int[]{r1, r11}
            r5.setIntValues(r11)
            G0.d r11 = new G0.d
            r11.<init>(r0, r13, r3)
            r5.addUpdateListener(r11)
            android.view.animation.DecelerateInterpolator r3 = new android.view.animation.DecelerateInterpolator
            r3.<init>()
            r5.setInterpolator(r3)
            r13 = 500(0x1f4, double:2.47E-321)
            r5.setDuration(r13)
            r5.start()
            goto L_0x02b2
        L_0x02a3:
            r5.width = r11
            r3.setLayoutParams(r5)
            goto L_0x02b2
        L_0x02a9:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams"
            r0.<init>(r1)
            throw r0
        L_0x02b1:
            r1 = 0
        L_0x02b2:
            java.util.List<android.widget.RelativeLayout> r3 = r0.f5641r
            java.lang.Object r3 = r3.get(r8)
            android.widget.RelativeLayout r3 = (android.widget.RelativeLayout) r3
            com.appsamurai.storyly.storylypresenter.storylylayer.b2$a r5 = com.appsamurai.storyly.storylypresenter.storylylayer.b2.a.ALL
            int r11 = r0.f5628C
            float r11 = (float) r11
            float r11 = r11 / r12
            com.appsamurai.storyly.data.k0 r12 = r0.f5633j
            if (r12 != 0) goto L_0x02c8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r12 = 0
        L_0x02c8:
            com.appsamurai.storyly.data.c r12 = r12.a()
            int r12 = r12.f3624a
            android.graphics.drawable.Drawable r5 = r0.a((com.appsamurai.storyly.storylypresenter.storylylayer.b2.a) r5, (float) r11, (int) r12)
            android.graphics.drawable.GradientDrawable r5 = (android.graphics.drawable.GradientDrawable) r5
            java.util.List<java.lang.Integer> r11 = r0.f5640q
            com.appsamurai.storyly.data.k0 r12 = r0.f5633j
            if (r12 != 0) goto L_0x02de
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r12 = 0
        L_0x02de:
            int r12 = r12.f3813g
            java.lang.Object r11 = r11.get(r12)
            java.lang.Number r11 = (java.lang.Number) r11
            int r11 = r11.intValue()
            if (r17 != 0) goto L_0x02ed
            goto L_0x0314
        L_0x02ed:
            int r12 = r17.intValue()
            if (r8 != r12) goto L_0x0314
            com.appsamurai.storyly.data.k0 r12 = r0.f5633j
            if (r12 != 0) goto L_0x02fb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r12 = 0
        L_0x02fb:
            com.appsamurai.storyly.data.c r13 = r12.f3824r
            if (r13 != 0) goto L_0x0311
            java.lang.String r12 = r12.f3807a
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x030e
            com.appsamurai.storyly.config.styling.a r6 = com.appsamurai.storyly.config.styling.a.COLOR_BFBFBF
        L_0x0309:
            com.appsamurai.storyly.data.c r13 = r6.b()
            goto L_0x0311
        L_0x030e:
            com.appsamurai.storyly.config.styling.a r6 = com.appsamurai.storyly.config.styling.a.COLOR_C4C4C4
            goto L_0x0309
        L_0x0311:
            int r6 = r13.f3624a
            goto L_0x0322
        L_0x0314:
            com.appsamurai.storyly.data.k0 r6 = r0.f5633j
            if (r6 != 0) goto L_0x031c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r6 = 0
        L_0x031c:
            com.appsamurai.storyly.data.c r6 = r6.b()
            int r6 = r6.f3624a
        L_0x0322:
            r5.setStroke(r11, r6)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            r3.setBackground(r5)
            if (r18 == 0) goto L_0x0338
            if (r17 != 0) goto L_0x032f
            goto L_0x0338
        L_0x032f:
            int r3 = r17.intValue()
            if (r8 != r3) goto L_0x0338
            com.appsamurai.storyly.util.ui.l.a(r9)
        L_0x0338:
            r6 = r1
            r8 = r10
            r1 = 1
            r3 = 0
            r5 = 100
            goto L_0x00ed
        L_0x0340:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.b2.a(java.lang.Integer, boolean):void");
    }

    public static final void a(b2 b2Var, View view, int i3, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(b2Var, "this$0");
        Intrinsics.checkNotNullParameter(view, "$animatedBar");
        if (b2Var.a()) {
            Object animatedValue = valueAnimator.getAnimatedValue();
            if (animatedValue != null) {
                view.setRight(((Integer) animatedValue).intValue());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
        }
        Object animatedValue2 = valueAnimator.getAnimatedValue();
        if (animatedValue2 != null) {
            view.setLeft(i3 - ((Integer) animatedValue2).intValue());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }

    public final void a(Integer num, int i3, boolean z2) {
        if (num != null) {
            int i4 = 0;
            for (T next : this.f5641r) {
                int i5 = i4 + 1;
                if (i4 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                RelativeLayout relativeLayout = (RelativeLayout) next;
                k0 k0Var = null;
                relativeLayout.setOnClickListener((View.OnClickListener) null);
                String string = getResources().getString(this.f5648y.get(i4).intValue());
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(optionNames[index])");
                relativeLayout.setImportantForAccessibility(1);
                Resources resources = relativeLayout.getResources();
                int i6 = i4 == i3 ? R.string.st_desc_quiz_after_sngl_true : R.string.st_desc_quiz_after_sngl_false;
                k0 k0Var2 = this.f5633j;
                if (k0Var2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    k0Var2 = null;
                }
                relativeLayout.setContentDescription(resources.getString(i6, new Object[]{string, k0Var2.f3810d.get(i4)}));
                long j2 = 0;
                if (i4 != num.intValue()) {
                    relativeLayout.setAlpha(0.5f);
                    if (i4 == i3) {
                        this.f5644u.get(i4).setTextColor(-1);
                        if (z2) {
                            j2 = 1000;
                        }
                        k0 k0Var3 = this.f5633j;
                        if (k0Var3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                        } else {
                            k0Var = k0Var3;
                        }
                        com.appsamurai.storyly.data.c cVar = k0Var.f3822p;
                        if (cVar == null) {
                            cVar = com.appsamurai.storyly.config.styling.a.COLOR_51C41A.b();
                        }
                        a(relativeLayout, j2, cVar.f3624a);
                        this.f5643t.get(i4).setImageResource(R.drawable.st_quiz_right_answer);
                    } else {
                        this.f5643t.get(i4).setImageResource(R.drawable.st_quiz_wrong_answer_ns);
                    }
                } else if (i4 == i3) {
                    this.f5644u.get(i4).setTextColor(-1);
                    if (z2) {
                        j2 = 1000;
                    }
                    k0 k0Var4 = this.f5633j;
                    if (k0Var4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    } else {
                        k0Var = k0Var4;
                    }
                    com.appsamurai.storyly.data.c cVar2 = k0Var.f3822p;
                    if (cVar2 == null) {
                        cVar2 = com.appsamurai.storyly.config.styling.a.COLOR_51C41A.b();
                    }
                    a(relativeLayout, j2, cVar2.f3624a);
                    this.f5643t.get(i4).setImageResource(R.drawable.st_quiz_right_answer);
                } else {
                    this.f5644u.get(i4).setTextColor(-1);
                    if (z2) {
                        j2 = 1000;
                    }
                    k0 k0Var5 = this.f5633j;
                    if (k0Var5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    } else {
                        k0Var = k0Var5;
                    }
                    com.appsamurai.storyly.data.c cVar3 = k0Var.f3821o;
                    if (cVar3 == null) {
                        cVar3 = com.appsamurai.storyly.config.styling.a.COLOR_FF4D50.b();
                    }
                    a(relativeLayout, j2, cVar3.f3624a);
                    this.f5643t.get(i4).setImageResource(R.drawable.st_quiz_wrong_answer);
                }
                if (z2 && i4 == num.intValue()) {
                    l.a(relativeLayout);
                }
                i4 = i5;
            }
        }
    }

    public final Drawable a(a aVar, float f2, int i3) {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.st_rectangle_shape_drawable);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) ((GradientDrawable) drawable).mutate();
            gradientDrawable.setColor(i3);
            float applyDimension = TypedValue.applyDimension(1, f2, getContext().getResources().getDisplayMetrics());
            int i4 = b.$EnumSwitchMapping$0[aVar.ordinal()];
            if (i4 == 1) {
                gradientDrawable.setCornerRadii(new float[]{applyDimension, applyDimension, applyDimension, applyDimension, 0.0f, 0.0f, 0.0f, 0.0f});
            } else if (i4 == 2) {
                gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, applyDimension, applyDimension, applyDimension, applyDimension});
            } else if (i4 == 3) {
                gradientDrawable.setCornerRadii(new float[]{applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension});
            }
            return gradientDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }

    public final void a(RelativeLayout relativeLayout, long j2, int i3) {
        Drawable background = relativeLayout.getBackground();
        if (background != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            ValueAnimator valueAnimator = new ValueAnimator();
            k0 k0Var = this.f5633j;
            if (k0Var == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                k0Var = null;
            }
            valueAnimator.setIntValues(new int[]{k0Var.a().f3624a, i3});
            valueAnimator.setEvaluator(new ArgbEvaluator());
            valueAnimator.addUpdateListener(new e(gradientDrawable, 0));
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.setDuration(j2);
            valueAnimator.start();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }

    public static final void a(GradientDrawable gradientDrawable, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(gradientDrawable, "$background");
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            gradientDrawable.setColor(((Integer) animatedValue).intValue());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }
}
