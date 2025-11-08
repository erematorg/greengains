package com.appsamurai.storyly.storylypresenter.storylylayer;

import G0.q;
import G0.s;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.widget.EmojiTextView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.y;
import com.appsamurai.storyly.util.ui.l;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class u extends o1 {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final StorylyConfig f6108g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f6109h;

    /* renamed from: i  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f6110i;

    /* renamed from: j  reason: collision with root package name */
    public y f6111j;

    /* renamed from: k  reason: collision with root package name */
    public Function0<Unit> f6112k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public AtomicInteger f6113l = new AtomicInteger(0);
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public AtomicInteger f6114m = new AtomicInteger(0);

    /* renamed from: n  reason: collision with root package name */
    public int f6115n;

    /* renamed from: o  reason: collision with root package name */
    public int f6116o;

    /* renamed from: p  reason: collision with root package name */
    public float f6117p;

    /* renamed from: q  reason: collision with root package name */
    public float f6118q;

    /* renamed from: r  reason: collision with root package name */
    public boolean f6119r;
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    public final List<Integer> f6120s = CollectionsKt.listOf(Integer.valueOf(R.drawable.st_image_quiz_a), Integer.valueOf(R.drawable.st_image_quiz_b), Integer.valueOf(R.drawable.st_image_quiz_c), Integer.valueOf(R.drawable.st_image_quiz_d));
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public final List<Integer> f6121t = CollectionsKt.listOf(Integer.valueOf(R.string.st_desc_option_a), Integer.valueOf(R.string.st_desc_option_b), Integer.valueOf(R.string.st_desc_option_c), Integer.valueOf(R.string.st_desc_option_d));
    @NotNull

    /* renamed from: u  reason: collision with root package name */
    public final List<b> f6122u = new ArrayList();
    @NotNull

    /* renamed from: v  reason: collision with root package name */
    public final List<Target<?>> f6123v = new ArrayList();
    @NotNull

    /* renamed from: w  reason: collision with root package name */
    public final Lazy f6124w;
    @NotNull

    /* renamed from: x  reason: collision with root package name */
    public final Lazy f6125x;
    @NotNull

    /* renamed from: y  reason: collision with root package name */
    public final Lazy f6126y;
    @NotNull

    /* renamed from: z  reason: collision with root package name */
    public final Lazy f6127z;

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

    public static final class c implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f6133a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ u f6134b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ long f6135c;

        /* renamed from: d  reason: collision with root package name */
        public final /* synthetic */ float f6136d;

        /* renamed from: e  reason: collision with root package name */
        public final /* synthetic */ Integer f6137e;

        /* renamed from: f  reason: collision with root package name */
        public final /* synthetic */ float f6138f;

        public c(b bVar, u uVar, long j2, float f2, Integer num, float f3) {
            this.f6133a = bVar;
            this.f6134b = uVar;
            this.f6135c = j2;
            this.f6136d = f2;
            this.f6137e = num;
            this.f6138f = f3;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
            String str;
            Integer num;
            Integer num2;
            String str2;
            this.f6133a.getEmojiView().animate().setDuration(this.f6135c).alpha(0.0f).scaleY(1.0f).scaleX(1.0f);
            int i3 = 0;
            for (T next : this.f6134b.f6122u) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                b bVar = (b) next;
                String string = this.f6134b.getResources().getString(this.f6134b.f6121t.get(i3).intValue());
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(optionNames[index])");
                Resources resources = this.f6134b.getResources();
                int i5 = R.string.st_desc_image_quiz_alt_text;
                y yVar = this.f6134b.f6111j;
                y yVar2 = null;
                if (yVar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    yVar = null;
                }
                List<String> list = yVar.f4281c;
                String str3 = "";
                if (list == null || (str = list.get(i3)) == null) {
                    str = str3;
                }
                y yVar3 = this.f6134b.f6111j;
                if (yVar3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    yVar3 = null;
                }
                List<String> list2 = yVar3.f4282d;
                if (!(list2 == null || (str2 = list2.get(i3)) == null)) {
                    str3 = str2;
                }
                String string2 = resources.getString(i5, new Object[]{str, str3});
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …) ?: \"\"\n                )");
                AppCompatImageView optionImage = bVar.getOptionImage();
                optionImage.setImportantForAccessibility(1);
                optionImage.setContentDescription(optionImage.getResources().getString(bVar.f5598a ? R.string.st_desc_quiz_after_sngl_true : R.string.st_desc_quiz_after_sngl_false, new Object[]{string, string2}));
                bVar.getOptionImageWrongBgDrawable().setImageDrawable(this.f6134b.a(this.f6136d, new int[]{com.appsamurai.storyly.util.f.a(-1, 0.2f)}, 1.0f));
                Integer num3 = this.f6137e;
                if (num3 == null || i3 != num3.intValue()) {
                    y yVar4 = this.f6134b.f6111j;
                    if (yVar4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                        yVar4 = null;
                    }
                    Integer num4 = yVar4.f4283e;
                    if (num4 != null && i3 == num4.intValue()) {
                        ImageView optionImageBorderDrawable = bVar.getOptionImageBorderDrawable();
                        u uVar = this.f6134b;
                        a aVar = a.ALL;
                        float f2 = this.f6136d;
                        float f3 = this.f6138f;
                        y yVar5 = uVar.f6111j;
                        if (yVar5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                            yVar5 = null;
                        }
                        com.appsamurai.storyly.data.c cVar = yVar5.f4289k;
                        if (cVar == null) {
                            cVar = com.appsamurai.storyly.config.styling.a.COLOR_51C41A.b();
                        }
                        int i6 = cVar.f3624a;
                        Context context = this.f6134b.getContext();
                        Intrinsics.checkNotNullExpressionValue(context, "context");
                        optionImageBorderDrawable.setImageDrawable(uVar.a(aVar, f2, f3, 0, i6, context));
                    } else {
                        ImageView optionImageBorderDrawable2 = bVar.getOptionImageBorderDrawable();
                        u uVar2 = this.f6134b;
                        a aVar2 = a.ALL;
                        float f4 = this.f6136d;
                        float f5 = this.f6138f;
                        y yVar6 = uVar2.f6111j;
                        if (yVar6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                            yVar6 = null;
                        }
                        com.appsamurai.storyly.data.c cVar2 = yVar6.f4288j;
                        if (cVar2 == null) {
                            cVar2 = com.appsamurai.storyly.config.styling.a.COLOR_FFB8B9.b();
                        }
                        int i7 = cVar2.f3624a;
                        Context context2 = this.f6134b.getContext();
                        Intrinsics.checkNotNullExpressionValue(context2, "context");
                        optionImageBorderDrawable2.setImageDrawable(uVar2.a(aVar2, f4, f5, 0, i7, context2));
                    }
                }
                Integer num5 = this.f6137e;
                y yVar7 = this.f6134b.f6111j;
                if (yVar7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                } else {
                    yVar2 = yVar7;
                }
                if (!Intrinsics.areEqual((Object) num5, (Object) yVar2.f4283e) && ((num2 = this.f6137e) == null || i3 != num2.intValue())) {
                    bVar.getOptionImageWrongBgDrawable().animate().setDuration(this.f6135c).alpha(1.0f);
                }
                bVar.getOptionImageBorderDrawable().animate().setDuration(this.f6135c).alpha(1.0f);
                bVar.getOptionChoiceResultImage().animate().setDuration(this.f6135c).alpha(1.0f);
                if (this.f6134b.f6119r && (num = this.f6137e) != null && i3 == num.intValue()) {
                    l.a(bVar.getOptionImage());
                }
                i3 = i4;
            }
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
        }
    }

    public static final class d extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6139a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f6139a = context;
        }

        public Object invoke() {
            return new RelativeLayout(this.f6139a);
        }
    }

    public static final class e extends Lambda implements Function0<SharedPreferences> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6140a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f6140a = context;
        }

        public Object invoke() {
            return this.f6140a.getSharedPreferences("stryly-image-quiz-results", 0);
        }
    }

    public static final class f extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6141a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f6141a = context;
        }

        public Object invoke() {
            return new LinearLayout(this.f6141a);
        }
    }

    public static final class g extends Lambda implements Function0<AppCompatTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6142a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f6142a = context;
        }

        public Object invoke() {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f6142a);
            appCompatTextView.setMaxLines(2);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            appCompatTextView.setGravity(17);
            appCompatTextView.setTextAlignment(4);
            appCompatTextView.setIncludeFontPadding(false);
            appCompatTextView.setLineSpacing(0.0f, 1.0f);
            appCompatTextView.setImportantForAccessibility(2);
            return appCompatTextView;
        }
    }

    public static final class h extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6143a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f6143a = context;
        }

        public Object invoke() {
            return new RelativeLayout(this.f6143a);
        }
    }

    public static final class i implements RequestListener<Drawable> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ u f6144a;

        public i(u uVar) {
            this.f6144a = uVar;
        }

        public static final void a(u uVar) {
            Intrinsics.checkNotNullParameter(uVar, "this$0");
            uVar.getOnLayerLoadFail$storyly_release().invoke();
        }

        public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z2) {
            if (this.f6144a.f6114m.incrementAndGet() != 1) {
                return false;
            }
            new Handler(Looper.getMainLooper()).post(new s(this.f6144a, 0));
            return false;
        }

        public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z2) {
            Drawable drawable = (Drawable) obj;
            int incrementAndGet = this.f6144a.f6113l.incrementAndGet();
            y yVar = this.f6144a.f6111j;
            if (yVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                yVar = null;
            }
            if (incrementAndGet == yVar.f4279a.size()) {
                this.f6144a.f6113l.set(0);
                this.f6144a.getOnLayerLoad$storyly_release().invoke();
            }
            return false;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public u(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f6108g = storylyConfig;
        this.f6109h = LazyKt.lazy(new e(context));
        this.f6124w = LazyKt.lazy(new f(context));
        this.f6125x = LazyKt.lazy(new d(context));
        this.f6126y = LazyKt.lazy(new h(context));
        this.f6127z = LazyKt.lazy(new g(context));
        setImportantForAccessibility(2);
    }

    private final RelativeLayout getImageQuizOptionViewContainer() {
        return (RelativeLayout) this.f6125x.getValue();
    }

    private final SharedPreferences getImageQuizSharedPreferences() {
        return (SharedPreferences) this.f6109h.getValue();
    }

    private final LinearLayout getImageQuizView() {
        return (LinearLayout) this.f6124w.getValue();
    }

    private final AppCompatTextView getQuizTitle() {
        return (AppCompatTextView) this.f6127z.getValue();
    }

    private final RelativeLayout getQuizTitleContainer() {
        return (RelativeLayout) this.f6126y.getValue();
    }

    private final void setImageFromSource(List<String> list) {
        int i3 = 0;
        for (T next : list) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            this.f6123v.set(i3, Glide.with(getContext().getApplicationContext()).load((String) next).listener(new i(this)).preload());
            i3 = i4;
        }
    }

    public void a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        a0 a0Var = b0Var.f3615j;
        y yVar = null;
        y yVar2 = a0Var instanceof y ? (y) a0Var : null;
        if (yVar2 != null) {
            this.f6111j = yVar2;
            setStorylyLayerItem$storyly_release(b0Var);
            y yVar3 = this.f6111j;
            if (yVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                yVar3 = null;
            }
            if (yVar3.f4279a.isEmpty()) {
                getOnLayerLoadFail$storyly_release().invoke();
                return;
            }
            y yVar4 = this.f6111j;
            if (yVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                yVar4 = null;
            }
            int i3 = 0;
            for (T next : yVar4.f4279a) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str = (String) next;
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                this.f6122u.add(new b(context));
                this.f6123v.add((Object) null);
                i3 = i4;
            }
            y yVar5 = this.f6111j;
            if (yVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                yVar5 = null;
            }
            Integer num = yVar5.f4283e;
            if (num != null) {
                this.f6122u.get(num.intValue()).setRightAnswer(true);
            }
            y yVar6 = this.f6111j;
            if (yVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            } else {
                yVar = yVar6;
            }
            setImageFromSource(yVar.f4279a);
            setRotation(b0Var.f3613h);
        }
    }

    public void c() {
        y yVar = this.f6111j;
        if (yVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            yVar = null;
        }
        int size = yVar.f4279a.size();
        if (size > 0) {
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                b bVar = this.f6122u.get(i3);
                Glide.with(bVar.getContext().getApplicationContext()).clear((View) bVar);
                bVar.getOptionImageContainer().removeAllViews();
                bVar.removeAllViews();
                Glide.with(getContext().getApplicationContext()).clear(this.f6123v.get(i3));
                this.f6123v.set(i3, (Object) null);
                if (i4 >= size) {
                    break;
                }
                i3 = i4;
            }
        }
        this.f6113l.set(0);
        this.f6114m.set(0);
        getImageQuizOptionViewContainer().removeAllViews();
        getImageQuizView().removeAllViews();
        removeAllViews();
    }

    @NotNull
    public final Function0<Unit> getOnImageReady$storyly_release() {
        Function0<Unit> function0 = this.f6112k;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onImageReady");
        return null;
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> getOnUserReaction$storyly_release() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f6110i;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    public final void setOnImageReady$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f6112k = function0;
    }

    public final void setOnUserReaction$storyly_release(@NotNull Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5) {
        Intrinsics.checkNotNullParameter(function5, "<set-?>");
        this.f6110i = function5;
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e7 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.Integer r19, long r20, float r22) {
        /*
            r18 = this;
            r0 = r18
            r1 = r20
            java.lang.String r3 = "storylyLayer"
            if (r19 != 0) goto L_0x0016
            com.appsamurai.storyly.data.y r5 = r0.f6111j
            if (r5 != 0) goto L_0x0011
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r5 = 0
        L_0x0011:
            boolean r5 = r5.f4295q
            if (r5 != 0) goto L_0x0016
            return
        L_0x0016:
            com.appsamurai.storyly.data.y r5 = r0.f6111j
            if (r5 != 0) goto L_0x001e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r5 = 0
        L_0x001e:
            java.util.List<java.lang.Integer> r5 = r5.f4284f
            r6 = 1
            if (r5 != 0) goto L_0x0025
            goto L_0x00e4
        L_0x0025:
            int r8 = r5.size()
            com.appsamurai.storyly.data.y r9 = r0.f6111j
            if (r9 != 0) goto L_0x0031
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r9 = 0
        L_0x0031:
            java.util.List<java.lang.String> r9 = r9.f4279a
            int r9 = r9.size()
            if (r8 == r9) goto L_0x003b
            goto L_0x00e4
        L_0x003b:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            com.appsamurai.storyly.data.y r9 = r0.f6111j
            if (r9 != 0) goto L_0x0048
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r9 = 0
        L_0x0048:
            boolean r9 = r9.f4295q
            if (r9 != 0) goto L_0x0055
            r9 = r5
            java.util.Collection r9 = (java.util.Collection) r9
            int r9 = kotlin.collections.CollectionsKt___CollectionsKt.sumOfInt(r9)
            int r9 = r9 + r6
            goto L_0x005c
        L_0x0055:
            r9 = r5
            java.util.Collection r9 = (java.util.Collection) r9
            int r9 = kotlin.collections.CollectionsKt___CollectionsKt.sumOfInt(r9)
        L_0x005c:
            r10 = 100
            if (r9 == 0) goto L_0x009c
            java.util.Iterator r5 = r5.iterator()
            r11 = 0
        L_0x0065:
            boolean r12 = r5.hasNext()
            if (r12 == 0) goto L_0x00b6
            java.lang.Object r12 = r5.next()
            int r13 = r11 + 1
            if (r11 >= 0) goto L_0x0076
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x0076:
            java.lang.Number r12 = (java.lang.Number) r12
            int r12 = r12.intValue()
            if (r19 != 0) goto L_0x007f
            goto L_0x0087
        L_0x007f:
            int r14 = r19.intValue()
            if (r14 != r11) goto L_0x0087
            int r12 = r12 + 1
        L_0x0087:
            float r11 = (float) r12
            float r12 = (float) r9
            float r11 = r11 / r12
            float r12 = (float) r10
            float r11 = r11 * r12
            double r11 = (double) r11
            double r11 = java.lang.Math.ceil(r11)
            float r11 = (float) r11
            int r11 = (int) r11
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r8.add(r11)
            r11 = r13
            goto L_0x0065
        L_0x009c:
            int r9 = r5.size()
            r11 = 0
        L_0x00a1:
            if (r11 >= r9) goto L_0x00b6
            int r12 = r5.size()
            double r12 = (double) r12
            r14 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r14 = r14 / r12
            double r12 = java.lang.Math.ceil(r14)
            int r12 = (int) r12
            r13 = 1
            int r11 = android.support.v4.media.session.a.e(r12, r8, r11, r13)
            goto L_0x00a1
        L_0x00b6:
            int r5 = kotlin.collections.CollectionsKt___CollectionsKt.sumOfInt(r8)
            if (r5 == r10) goto L_0x00e5
            java.lang.Comparable r5 = kotlin.collections.CollectionsKt.maxOrNull(r8)
            java.lang.Integer r5 = (java.lang.Integer) r5
            if (r5 != 0) goto L_0x00c6
            r5 = 0
            goto L_0x00e2
        L_0x00c6:
            int r5 = r5.intValue()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r5)
            int r9 = r8.indexOf(r9)
            int r11 = kotlin.collections.CollectionsKt___CollectionsKt.sumOfInt(r8)
            int r11 = r11 - r5
            int r5 = 100 - r11
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r8.set(r9, r5)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
        L_0x00e2:
            if (r5 != 0) goto L_0x00b6
        L_0x00e4:
            r8 = 0
        L_0x00e5:
            if (r8 != 0) goto L_0x00e8
            return
        L_0x00e8:
            int r5 = r0.f6116o
            float r5 = (float) r5
            r9 = 1056440320(0x3ef80000, float:0.484375)
            float r5 = r5 * r9
            int r5 = (int) r5
            float r10 = (float) r5
            r11 = 1027144113(0x3d38f9b1, float:0.04516)
            float r10 = r10 * r11
            int r10 = (int) r10
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r11 = r0.f6122u
            java.util.Iterator r11 = r11.iterator()
            r12 = 0
        L_0x00fc:
            boolean r13 = r11.hasNext()
            if (r13 == 0) goto L_0x022f
            java.lang.Object r13 = r11.next()
            int r14 = r12 + 1
            if (r12 >= 0) goto L_0x010d
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x010d:
            com.appsamurai.storyly.storylypresenter.storylylayer.b r13 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r13
            android.content.res.Resources r15 = r18.getResources()
            java.util.List<java.lang.Integer> r4 = r0.f6121t
            java.lang.Object r4 = r4.get(r12)
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            java.lang.String r4 = r15.getString(r4)
            java.lang.String r15 = "resources.getString(optionNames[index])"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r15)
            android.content.res.Resources r15 = r18.getResources()
            int r9 = com.appsamurai.storyly.R.string.st_desc_image_quiz_alt_text
            com.appsamurai.storyly.data.y r7 = r0.f6111j
            if (r7 != 0) goto L_0x0136
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r7 = 0
        L_0x0136:
            java.util.List<java.lang.String> r7 = r7.f4281c
            java.lang.String r17 = ""
            if (r7 != 0) goto L_0x013d
            goto L_0x0145
        L_0x013d:
            java.lang.Object r7 = r7.get(r12)
            java.lang.String r7 = (java.lang.String) r7
            if (r7 != 0) goto L_0x0147
        L_0x0145:
            r7 = r17
        L_0x0147:
            com.appsamurai.storyly.data.y r6 = r0.f6111j
            if (r6 != 0) goto L_0x014f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r6 = 0
        L_0x014f:
            java.util.List<java.lang.String> r6 = r6.f4282d
            if (r6 != 0) goto L_0x0154
            goto L_0x015c
        L_0x0154:
            java.lang.Object r6 = r6.get(r12)
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L_0x015e
        L_0x015c:
            r6 = r17
        L_0x015e:
            java.lang.Object[] r6 = new java.lang.Object[]{r7, r6}
            java.lang.String r6 = r15.getString(r9, r6)
            java.lang.String r7 = "resources.getString(\n   …ndex) ?: \"\"\n            )"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            androidx.appcompat.widget.AppCompatImageView r7 = r13.getOptionImage()
            r9 = 1
            r7.setImportantForAccessibility(r9)
            android.content.res.Resources r15 = r7.getResources()
            int r9 = com.appsamurai.storyly.R.string.st_desc_quiz_after_poll
            r17 = r3
            java.lang.Object r3 = r8.get(r12)
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r6, r3}
            java.lang.String r3 = r15.getString(r9, r3)
            r7.setContentDescription(r3)
            r3 = 0
            r13.setClickable(r3)
            android.widget.ImageView r3 = r13.getOptionImageWrongBgDrawable()
            r4 = -1
            r6 = 1045220557(0x3e4ccccd, float:0.2)
            int r4 = com.appsamurai.storyly.util.f.a((int) r4, (float) r6)
            int[] r4 = new int[]{r4}
            r6 = 1065353216(0x3f800000, float:1.0)
            r7 = r22
            android.graphics.drawable.Drawable r4 = r0.a((float) r7, (int[]) r4, (float) r6)
            r3.setImageDrawable(r4)
            java.lang.Object r3 = r8.get(r12)
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            android.animation.ValueAnimator r4 = new android.animation.ValueAnimator
            r4.<init>()
            int r9 = r0.f6116o
            float r9 = (float) r9
            r15 = 1056440320(0x3ef80000, float:0.484375)
            float r9 = r9 * r15
            int r9 = (int) r9
            int r16 = r3 + 40
            int r9 = r9 * r16
            int r9 = r9 / 155
            r15 = 0
            int[] r9 = new int[]{r15, r9}
            r4.setIntValues(r9)
            android.widget.TextView r9 = r13.getPercentageText()
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r3)
            r3 = 37
            r15.append(r3)
            java.lang.String r3 = r15.toString()
            r9.setText(r3)
            G0.r r3 = new G0.r
            r3.<init>(r13, r5, r10)
            r4.addUpdateListener(r3)
            if (r19 != 0) goto L_0x01f0
            goto L_0x01f6
        L_0x01f0:
            int r3 = r19.intValue()
            if (r12 == r3) goto L_0x0205
        L_0x01f6:
            android.widget.ImageView r3 = r13.getOptionImageWrongBgDrawable()
            android.view.ViewPropertyAnimator r3 = r3.animate()
            android.view.ViewPropertyAnimator r3 = r3.setDuration(r1)
            r3.alpha(r6)
        L_0x0205:
            r4.setDuration(r1)
            android.view.animation.DecelerateInterpolator r3 = new android.view.animation.DecelerateInterpolator
            r3.<init>()
            r4.setInterpolator(r3)
            r4.start()
            boolean r3 = r0.f6119r
            if (r3 == 0) goto L_0x0227
            if (r19 != 0) goto L_0x021a
            goto L_0x0227
        L_0x021a:
            int r3 = r19.intValue()
            if (r12 != r3) goto L_0x0227
            androidx.appcompat.widget.AppCompatImageView r3 = r13.getOptionImage()
            com.appsamurai.storyly.util.ui.l.a(r3)
        L_0x0227:
            r12 = r14
            r3 = r17
            r6 = 1
            r9 = 1056440320(0x3ef80000, float:0.484375)
            goto L_0x00fc
        L_0x022f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.u.a(java.lang.Integer, long, float):void");
    }

    public static final void a(b bVar, int i3, int i4, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(bVar, "$imageQuizOptionView");
        View pollResultAnimatedBar = bVar.getPollResultAnimatedBar();
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((Integer) animatedValue).intValue(), (int) (((float) i3) * 0.206f));
            layoutParams.addRule(6, bVar.getOptionImageContainer().getId());
            layoutParams.addRule(18, bVar.getOptionImageContainer().getId());
            layoutParams.topMargin = i4;
            layoutParams.setMarginStart(i4);
            Unit unit = Unit.INSTANCE;
            pollResultAnimatedBar.setLayoutParams(layoutParams);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
    }

    public final void a(Integer num, float f2, float f3, long j2, long j3) {
        com.appsamurai.storyly.data.c cVar;
        Integer num2 = num;
        if (num2 != null) {
            b bVar = this.f6122u.get(num.intValue());
            y yVar = this.f6111j;
            y yVar2 = null;
            if (yVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                yVar = null;
            }
            if (Intrinsics.areEqual((Object) yVar.f4283e, (Object) num2)) {
                y yVar3 = this.f6111j;
                if (yVar3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                } else {
                    yVar2 = yVar3;
                }
                cVar = yVar2.f4289k;
                if (cVar == null) {
                    cVar = com.appsamurai.storyly.config.styling.a.COLOR_51C41A.b();
                }
            } else {
                y yVar4 = this.f6111j;
                if (yVar4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                } else {
                    yVar2 = yVar4;
                }
                cVar = yVar2.f4290l;
                if (cVar == null) {
                    cVar = com.appsamurai.storyly.config.styling.a.COLOR_FF4D50.b();
                }
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(bVar.getEmojiView(), "alpha", new float[]{0.0f, 1.0f});
            ofFloat.setDuration(j2);
            bVar.getEmojiView().setScaleX(1.3125f);
            bVar.getEmojiView().setScaleY(1.3125f);
            ImageView optionImageBorderDrawable = bVar.getOptionImageBorderDrawable();
            a aVar = a.ALL;
            int i3 = cVar.f3624a;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            optionImageBorderDrawable.setImageDrawable(a(aVar, f2, f3, 0, i3, context));
            bVar.getOptionChoiceImage().setAlpha(0.0f);
            Intrinsics.checkNotNullExpressionValue(ofFloat, "emojiShowUp");
            ofFloat.addListener(new c(bVar, this, j3, f2, num, f3));
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(bVar.getOptionImageBorderDrawable(), "alpha", new float[]{0.0f, 1.0f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(ofFloat);
            animatorSet.play(ofFloat2);
            animatorSet.start();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x0444  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0453  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0456  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x046d  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0475  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0479  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x04a2  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x04c5  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x04e6  */
    /* JADX WARNING: Removed duplicated region for block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0293  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x02e9  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x035a  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x03aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.storylypresenter.storylylayer.d r22) {
        /*
            r21 = this;
            r8 = r21
            r7 = 2
            r10 = 1
            java.lang.String r0 = "safeFrame"
            r1 = r22
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            com.appsamurai.storyly.data.y r0 = r8.f6111j
            java.lang.String r11 = "storylyLayer"
            if (r0 != 0) goto L_0x0016
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = 0
        L_0x0016:
            java.util.List<java.lang.String> r0 = r0.f4279a
            int r13 = r0.size()
            com.appsamurai.storyly.data.y r0 = r8.f6111j
            if (r0 != 0) goto L_0x0024
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = 0
        L_0x0024:
            java.lang.String r0 = r0.f4280b
            if (r0 == 0) goto L_0x0031
            boolean r0 = kotlin.text.StringsKt.isBlank(r0)
            if (r0 == 0) goto L_0x002f
            goto L_0x0031
        L_0x002f:
            r14 = 0
            goto L_0x0032
        L_0x0031:
            r14 = r10
        L_0x0032:
            float r0 = r22.b()
            r8.f6117p = r0
            float r0 = r22.a()
            r8.f6118q = r0
            if (r14 != 0) goto L_0x0059
            android.content.res.Resources r0 = r21.getResources()
            int r2 = com.appsamurai.storyly.R.string.st_desc_quiz_with_title
            com.appsamurai.storyly.data.y r3 = r8.f6111j
            if (r3 != 0) goto L_0x004e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r3 = 0
        L_0x004e:
            java.lang.String r3 = r3.f4280b
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r0 = r0.getString(r2, r3)
            goto L_0x0063
        L_0x0059:
            android.content.res.Resources r0 = r21.getResources()
            int r2 = com.appsamurai.storyly.R.string.st_desc_quiz_without_title
            java.lang.String r0 = r0.getString(r2)
        L_0x0063:
            java.lang.String r2 = "if (hasTitle) resources.…_desc_quiz_without_title)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            r8.a((java.lang.String) r0)
            float r0 = r8.f6117p
            com.appsamurai.storyly.data.b0 r2 = r21.getStorylyLayerItem$storyly_release()
            float r2 = r2.f3609d
            r3 = 100
            float r15 = (float) r3
            int r0 = A.a.a(r2, r15, r0)
            r8.f6116o = r0
            float r0 = r8.f6118q
            com.appsamurai.storyly.data.b0 r2 = r21.getStorylyLayerItem$storyly_release()
            float r2 = r2.f3610e
            int r0 = A.a.a(r2, r15, r0)
            r8.f6115n = r0
            int r0 = r8.f6116o
            float r0 = (float) r0
            r2 = 1033342288(0x3d978d50, float:0.074)
            float r16 = r0 * r2
            r2 = 1028443341(0x3d4ccccd, float:0.05)
            float r17 = r0 * r2
            r0 = 1033677832(0x3d9cac08, float:0.0765)
            float r18 = r17 * r0
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            int r0 = r8.f6116o
            int r3 = r8.f6115n
            r2.<init>(r0, r3)
            com.appsamurai.storyly.data.b0 r0 = r21.getStorylyLayerItem$storyly_release()
            android.graphics.Point r0 = r0.b()
            int r3 = r0.x
            com.appsamurai.storyly.data.b0 r0 = r21.getStorylyLayerItem$storyly_release()
            android.graphics.Point r0 = r0.b()
            int r4 = r0.y
            float r5 = r22.c()
            float r6 = r22.d()
            r0 = r21
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            android.widget.FrameLayout$LayoutParams r0 = r0.a(r1, r2, r3, r4, r5)
            r8.setLayoutParams(r0)
            android.widget.LinearLayout r0 = r21.getImageQuizView()
            android.widget.FrameLayout$LayoutParams r1 = new android.widget.FrameLayout$LayoutParams
            r6 = -1
            r1.<init>(r6, r6)
            r8.addView(r0, r1)
            android.widget.LinearLayout r0 = r21.getImageQuizView()
            r0.setOrientation(r10)
            android.widget.LinearLayout r5 = r21.getImageQuizView()
            com.appsamurai.storyly.storylypresenter.storylylayer.u$a r1 = com.appsamurai.storyly.storylypresenter.storylylayer.u.a.ALL
            com.appsamurai.storyly.data.y r0 = r8.f6111j
            if (r0 != 0) goto L_0x00f1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = 0
        L_0x00f1:
            com.appsamurai.storyly.data.c r0 = r0.f4285g
            if (r0 != 0) goto L_0x00fa
            com.appsamurai.storyly.data.c r0 = new com.appsamurai.storyly.data.c
            r0.<init>(r6)
        L_0x00fa:
            int r4 = r0.f3624a
            com.appsamurai.storyly.data.y r0 = r8.f6111j
            if (r0 != 0) goto L_0x0104
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = 0
        L_0x0104:
            com.appsamurai.storyly.data.c r0 = r0.f4292n
            if (r0 != 0) goto L_0x010e
            com.appsamurai.storyly.config.styling.a r0 = com.appsamurai.storyly.config.styling.a.COLOR_E0E0E0
            com.appsamurai.storyly.data.c r0 = r0.b()
        L_0x010e:
            int r3 = r0.f3624a
            android.content.Context r2 = r21.getContext()
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            r12 = r0
            r0 = r21
            r19 = r2
            r2 = r17
            r20 = r3
            r3 = r18
            r10 = r5
            r5 = r20
            r9 = r6
            r6 = r19
            android.graphics.drawable.Drawable r0 = r0.a((com.appsamurai.storyly.storylypresenter.storylylayer.u.a) r1, (float) r2, (float) r3, (int) r4, (int) r5, (android.content.Context) r6)
            r10.setBackground(r0)
            if (r13 != r7) goto L_0x013b
            int r0 = r8.f6115n
            float r0 = (float) r0
            r1 = 1051931443(0x3eb33333, float:0.35)
        L_0x0139:
            float r0 = r0 * r1
            goto L_0x0142
        L_0x013b:
            int r0 = r8.f6115n
            float r0 = (float) r0
            r1 = 1046274166(0x3e5ce076, float:0.2157)
            goto L_0x0139
        L_0x0142:
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            int r2 = r8.f6116o
            r3 = -2
            r1.<init>(r2, r3)
            r10 = 20
            r6 = 10
            if (r14 != 0) goto L_0x018e
            android.widget.LinearLayout r2 = r21.getImageQuizView()
            android.widget.RelativeLayout r3 = r21.getQuizTitleContainer()
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            int r5 = r8.f6116o
            int r7 = (int) r0
            r4.<init>(r5, r7)
            r2.addView(r3, r4)
            android.widget.RelativeLayout r2 = r21.getQuizTitleContainer()
            androidx.appcompat.widget.AppCompatTextView r3 = r21.getQuizTitle()
            r4 = 15
            r1.addRule(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            r2.addView(r3, r1)
            android.widget.LinearLayout r1 = r21.getImageQuizView()
            android.widget.RelativeLayout r2 = r21.getImageQuizOptionViewContainer()
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            int r4 = r8.f6116o
            r3.<init>(r4, r4)
            r3.addRule(r6, r9)
            r3.addRule(r10, r9)
            r1.addView(r2, r3)
            goto L_0x01a5
        L_0x018e:
            android.widget.LinearLayout r1 = r21.getImageQuizView()
            android.widget.RelativeLayout r2 = r21.getImageQuizOptionViewContainer()
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            int r4 = r8.f6116o
            r3.<init>(r4, r4)
            r3.addRule(r6, r9)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            r1.addView(r2, r3)
        L_0x01a5:
            android.widget.RelativeLayout r7 = r21.getQuizTitleContainer()
            r1 = 1047233823(0x3e6b851f, float:0.23)
            float r0 = r0 * r1
            int r0 = (int) r0
            r1 = 0
            r7.setPadding(r0, r0, r0, r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.u$a r1 = com.appsamurai.storyly.storylypresenter.storylylayer.u.a.TOP
            com.appsamurai.storyly.data.y r0 = r8.f6111j
            if (r0 != 0) goto L_0x01bc
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = 0
        L_0x01bc:
            com.appsamurai.storyly.data.c r0 = r0.f4287i
            if (r0 != 0) goto L_0x01c6
            com.appsamurai.storyly.config.styling.a r0 = com.appsamurai.storyly.config.styling.a.COLOR_212121
            com.appsamurai.storyly.data.c r0 = r0.b()
        L_0x01c6:
            int r4 = r0.f3624a
            com.appsamurai.storyly.data.y r0 = r8.f6111j
            if (r0 != 0) goto L_0x01d0
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = 0
        L_0x01d0:
            com.appsamurai.storyly.data.c r0 = r0.f4292n
            if (r0 != 0) goto L_0x01da
            com.appsamurai.storyly.config.styling.a r0 = com.appsamurai.storyly.config.styling.a.COLOR_E0E0E0
            com.appsamurai.storyly.data.c r0 = r0.b()
        L_0x01da:
            int r5 = r0.f3624a
            android.content.Context r3 = r7.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r12)
            r0 = r21
            r2 = r17
            r12 = r3
            r3 = r18
            r10 = r6
            r6 = r12
            android.graphics.drawable.Drawable r0 = r0.a((com.appsamurai.storyly.storylypresenter.storylylayer.u.a) r1, (float) r2, (float) r3, (int) r4, (int) r5, (android.content.Context) r6)
            r7.setBackground(r0)
            androidx.appcompat.widget.AppCompatTextView r0 = r21.getQuizTitle()
            com.appsamurai.storyly.data.y r1 = r8.f6111j
            if (r1 != 0) goto L_0x01ff
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r1 = 0
        L_0x01ff:
            com.appsamurai.storyly.data.c r1 = r1.f4286h
            if (r1 != 0) goto L_0x0208
            com.appsamurai.storyly.data.c r1 = new com.appsamurai.storyly.data.c
            r1.<init>(r9)
        L_0x0208:
            int r1 = r1.f3624a
            r0.setTextColor(r1)
            com.appsamurai.storyly.data.y r1 = r8.f6111j
            if (r1 != 0) goto L_0x0215
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r1 = 0
        L_0x0215:
            java.lang.String r1 = r1.f4280b
            r0.setText(r1)
            com.appsamurai.storyly.config.StorylyConfig r1 = r8.f6108g
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r1 = r1.getStory$storyly_release()
            android.graphics.Typeface r1 = r1.getInteractiveTypeface$storyly_release()
            r0.setTypeface(r1)
            int r1 = kotlin.math.MathKt.roundToInt((float) r16)
            r0.setLineHeight(r1)
            com.appsamurai.storyly.data.y r1 = r8.f6111j
            if (r1 != 0) goto L_0x0236
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r1 = 0
        L_0x0236:
            boolean r1 = r1.f4293o
            com.appsamurai.storyly.data.y r2 = r8.f6111j
            if (r2 != 0) goto L_0x0240
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r2 = 0
        L_0x0240:
            boolean r2 = r2.f4294p
            com.appsamurai.storyly.util.c.a(r0, r1, r2)
            r1 = 1061158912(0x3f400000, float:0.75)
            float r1 = r1 * r16
            r2 = 0
            r0.setTextSize(r2, r1)
            int r0 = r8.f6116o
            float r0 = (float) r0
            r1 = 1056440320(0x3ef80000, float:0.484375)
            float r2 = r0 * r1
            int r12 = (int) r2
            r2 = 2
            if (r13 != r2) goto L_0x026b
            if (r14 != 0) goto L_0x0264
            int r1 = r8.f6115n
            float r1 = (float) r1
            r2 = 1058935931(0x3f1e147b, float:0.6175)
        L_0x0260:
            float r1 = r1 * r2
            int r1 = (int) r1
        L_0x0262:
            r6 = r1
            goto L_0x027a
        L_0x0264:
            int r1 = r8.f6115n
            float r1 = (float) r1
            r2 = 1064514355(0x3f733333, float:0.95)
            goto L_0x0260
        L_0x026b:
            if (r14 != 0) goto L_0x0274
            int r1 = r8.f6115n
            float r1 = (float) r1
            r2 = 1052938076(0x3ec28f5c, float:0.38)
            goto L_0x0260
        L_0x0274:
            int r2 = r8.f6115n
            float r2 = (float) r2
            float r2 = r2 * r1
            int r1 = (int) r2
            goto L_0x0262
        L_0x027a:
            r1 = 1067450368(0x3fa00000, float:1.25)
            float r0 = r0 * r1
            float r0 = r0 / r15
            int r0 = (int) r0
            r1 = 2
            if (r13 != r1) goto L_0x0293
            if (r14 != 0) goto L_0x028d
            int r1 = r8.f6115n
            float r1 = (float) r1
            r2 = 1069547520(0x3fc00000, float:1.5)
        L_0x0289:
            float r1 = r1 * r2
            float r1 = r1 / r15
            int r1 = (int) r1
            goto L_0x029a
        L_0x028d:
            int r1 = r8.f6115n
            float r1 = (float) r1
            r2 = 1077936128(0x40400000, float:3.0)
            goto L_0x0289
        L_0x0293:
            int r1 = r8.f6115n
            float r1 = (float) r1
            r2 = 1065017672(0x3f7ae148, float:0.98)
            goto L_0x0289
        L_0x029a:
            android.widget.RelativeLayout r2 = r21.getImageQuizOptionViewContainer()
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r3 = r8.f6122u
            r4 = 0
            java.lang.Object r3 = r3.get(r4)
            com.appsamurai.storyly.storylypresenter.storylylayer.b r3 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r3
            android.widget.RelativeLayout r3 = r3.getOptionImageContainer()
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r4.<init>(r12, r6)
            r4.addRule(r10, r9)
            r5 = 20
            r4.addRule(r5, r9)
            r4.setMarginStart(r0)
            r4.topMargin = r1
            r2.addView(r3, r4)
            android.widget.RelativeLayout r2 = r21.getImageQuizOptionViewContainer()
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r3 = r8.f6122u
            r4 = 1
            java.lang.Object r3 = r3.get(r4)
            com.appsamurai.storyly.storylypresenter.storylylayer.b r3 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r3
            android.widget.RelativeLayout r3 = r3.getOptionImageContainer()
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r4.<init>(r12, r6)
            r4.addRule(r10, r9)
            r5 = 21
            r4.addRule(r5, r9)
            r4.setMarginEnd(r0)
            r4.topMargin = r1
            r2.addView(r3, r4)
            r2 = 3
            if (r13 == r2) goto L_0x035a
            r3 = 4
            if (r13 == r3) goto L_0x02ee
            goto L_0x0391
        L_0x02ee:
            android.widget.RelativeLayout r3 = r21.getImageQuizOptionViewContainer()
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r4 = r8.f6122u
            r7 = 2
            java.lang.Object r4 = r4.get(r7)
            com.appsamurai.storyly.storylypresenter.storylylayer.b r4 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r4
            android.widget.RelativeLayout r4 = r4.getOptionImageContainer()
            android.widget.RelativeLayout$LayoutParams r7 = new android.widget.RelativeLayout$LayoutParams
            r7.<init>(r12, r6)
            r10 = 20
            r7.addRule(r10, r9)
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r10 = r8.f6122u
            r13 = 0
            java.lang.Object r10 = r10.get(r13)
            com.appsamurai.storyly.storylypresenter.storylylayer.b r10 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r10
            android.widget.RelativeLayout r10 = r10.getOptionImageContainer()
            int r10 = r10.getId()
            r7.addRule(r2, r10)
            r7.setMarginStart(r0)
            r7.topMargin = r1
            r3.addView(r4, r7)
            android.widget.RelativeLayout r3 = r21.getImageQuizOptionViewContainer()
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r4 = r8.f6122u
            java.lang.Object r4 = r4.get(r2)
            com.appsamurai.storyly.storylypresenter.storylylayer.b r4 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r4
            android.widget.RelativeLayout r4 = r4.getOptionImageContainer()
            android.widget.RelativeLayout$LayoutParams r7 = new android.widget.RelativeLayout$LayoutParams
            r7.<init>(r12, r6)
            r7.addRule(r5, r9)
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r5 = r8.f6122u
            r10 = 1
            java.lang.Object r5 = r5.get(r10)
            com.appsamurai.storyly.storylypresenter.storylylayer.b r5 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r5
            android.widget.RelativeLayout r5 = r5.getOptionImageContainer()
            int r5 = r5.getId()
            r7.addRule(r2, r5)
            r7.setMarginEnd(r0)
            r7.topMargin = r1
            r3.addView(r4, r7)
            goto L_0x0391
        L_0x035a:
            android.widget.RelativeLayout r3 = r21.getImageQuizOptionViewContainer()
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r4 = r8.f6122u
            r5 = 2
            java.lang.Object r4 = r4.get(r5)
            com.appsamurai.storyly.storylypresenter.storylylayer.b r4 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r4
            android.widget.RelativeLayout r4 = r4.getOptionImageContainer()
            android.widget.RelativeLayout$LayoutParams r5 = new android.widget.RelativeLayout$LayoutParams
            r5.<init>(r12, r6)
            r7 = 14
            r5.addRule(r7, r9)
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r7 = r8.f6122u
            r10 = 1
            java.lang.Object r7 = r7.get(r10)
            com.appsamurai.storyly.storylypresenter.storylylayer.b r7 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r7
            android.widget.RelativeLayout r7 = r7.getOptionImageContainer()
            int r7 = r7.getId()
            r5.addRule(r2, r7)
            r5.setMarginStart(r0)
            r5.topMargin = r1
            r3.addView(r4, r5)
        L_0x0391:
            float r0 = (float) r6
            r1 = 1034657622(0x3dab9f56, float:0.0838)
            float r10 = r0 * r1
            r1 = 1017026781(0x3c9e98dd, float:0.01936)
            float r13 = r0 * r1
            int r0 = (int) r10
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r1 = r8.f6122u
            java.util.Iterator r1 = r1.iterator()
            r2 = 0
        L_0x03a4:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0432
            java.lang.Object r3 = r1.next()
            r4 = 1
            int r5 = r2 + 1
            if (r2 >= 0) goto L_0x03b6
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x03b6:
            com.appsamurai.storyly.storylypresenter.storylylayer.b r3 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r3
            com.appsamurai.storyly.data.y r4 = r8.f6111j
            if (r4 != 0) goto L_0x03c0
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r4 = 0
        L_0x03c0:
            java.util.List<java.lang.String> r4 = r4.f4279a
            java.lang.Object r2 = r4.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            androidx.appcompat.widget.AppCompatImageView r3 = r3.getOptionImage()
            if (r0 <= 0) goto L_0x03ee
            com.bumptech.glide.request.RequestOptions r4 = new com.bumptech.glide.request.RequestOptions
            r4.<init>()
            com.bumptech.glide.load.resource.bitmap.CenterCrop r7 = new com.bumptech.glide.load.resource.bitmap.CenterCrop
            r7.<init>()
            com.bumptech.glide.load.resource.bitmap.RoundedCorners r14 = new com.bumptech.glide.load.resource.bitmap.RoundedCorners
            r14.<init>(r0)
            r15 = 2
            com.bumptech.glide.load.Transformation[] r9 = new com.bumptech.glide.load.Transformation[r15]
            r17 = 0
            r9[r17] = r7
            r7 = 1
            r9[r7] = r14
            com.bumptech.glide.request.BaseRequestOptions r4 = r4.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>[]) r9)
            com.bumptech.glide.request.RequestOptions r4 = (com.bumptech.glide.request.RequestOptions) r4
            goto L_0x0401
        L_0x03ee:
            r15 = 2
            r17 = 0
            com.bumptech.glide.request.RequestOptions r4 = new com.bumptech.glide.request.RequestOptions
            r4.<init>()
            com.bumptech.glide.load.resource.bitmap.CenterCrop r7 = new com.bumptech.glide.load.resource.bitmap.CenterCrop
            r7.<init>()
            com.bumptech.glide.request.BaseRequestOptions r4 = r4.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r7)
            com.bumptech.glide.request.RequestOptions r4 = (com.bumptech.glide.request.RequestOptions) r4
        L_0x0401:
            java.lang.String r7 = "if (cornerRadius > 0) {\n…m(CenterCrop())\n        }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r7)
            android.content.Context r7 = r21.getContext()
            android.content.Context r7 = r7.getApplicationContext()
            com.bumptech.glide.RequestManager r7 = com.bumptech.glide.Glide.with((android.content.Context) r7)
            com.bumptech.glide.RequestBuilder r2 = r7.load((java.lang.String) r2)
            com.bumptech.glide.load.engine.DiskCacheStrategy r7 = com.bumptech.glide.load.engine.DiskCacheStrategy.ALL
            com.bumptech.glide.request.BaseRequestOptions r2 = r2.diskCacheStrategy(r7)
            com.bumptech.glide.RequestBuilder r2 = (com.bumptech.glide.RequestBuilder) r2
            com.appsamurai.storyly.storylypresenter.storylylayer.v r7 = new com.appsamurai.storyly.storylypresenter.storylylayer.v
            r7.<init>(r8)
            com.bumptech.glide.RequestBuilder r2 = r2.listener(r7)
            com.bumptech.glide.RequestBuilder r2 = r2.apply((com.bumptech.glide.request.BaseRequestOptions<?>) r4)
            r2.into((android.widget.ImageView) r3)
            r2 = r5
            r9 = -1
            goto L_0x03a4
        L_0x0432:
            r17 = 0
            com.appsamurai.storyly.data.b0 r0 = r21.getStorylyLayerItem$storyly_release()
            java.lang.String r0 = r0.f3614i
            android.content.SharedPreferences r1 = r21.getImageQuizSharedPreferences()
            boolean r1 = r1.contains(r0)
            if (r1 == 0) goto L_0x0453
            android.content.SharedPreferences r1 = r21.getImageQuizSharedPreferences()
            r2 = -1
            int r0 = r1.getInt(r0, r2)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r9 = r0
            goto L_0x0454
        L_0x0453:
            r9 = 0
        L_0x0454:
            if (r9 != 0) goto L_0x0466
            com.appsamurai.storyly.data.y r0 = r8.f6111j
            if (r0 != 0) goto L_0x045e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = 0
        L_0x045e:
            boolean r0 = r0.f4295q
            if (r0 == 0) goto L_0x0463
            goto L_0x0466
        L_0x0463:
            r1 = r17
            goto L_0x0467
        L_0x0466:
            r1 = 1
        L_0x0467:
            r8.f6119r = r1
            com.appsamurai.storyly.data.y r0 = r8.f6111j
            if (r0 != 0) goto L_0x0471
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = 0
        L_0x0471:
            java.lang.Integer r0 = r0.f4283e
            if (r0 != 0) goto L_0x0479
            r16 = r6
            r0 = 0
            goto L_0x04a0
        L_0x0479:
            int r0 = r0.intValue()
            com.appsamurai.storyly.data.y r1 = r8.f6111j
            if (r1 != 0) goto L_0x0485
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r1 = 0
        L_0x0485:
            boolean r1 = r1.f4295q
            if (r1 != 0) goto L_0x048b
            r1 = r9
            goto L_0x0490
        L_0x048b:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1 = r0
        L_0x0490:
            r4 = 0
            r14 = 0
            r0 = r21
            r2 = r10
            r3 = r13
            r16 = r6
            r6 = r14
            r0.a((java.lang.Integer) r1, (float) r2, (float) r3, (long) r4, (long) r6)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x04a0:
            if (r0 != 0) goto L_0x04b7
            com.appsamurai.storyly.data.y r0 = r8.f6111j
            if (r0 != 0) goto L_0x04aa
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r0 = 0
        L_0x04aa:
            boolean r0 = r0.f4295q
            if (r0 != 0) goto L_0x04af
            goto L_0x04b0
        L_0x04af:
            r9 = 0
        L_0x04b0:
            r0 = 0
            r8.a((java.lang.Integer) r9, (long) r0, (float) r10)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x04b7:
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r0 = r8.f6122u
            java.util.Iterator r6 = r0.iterator()
            r2 = r17
        L_0x04bf:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x04df
            java.lang.Object r0 = r6.next()
            r7 = 1
            int r10 = r2 + 1
            if (r2 >= 0) goto L_0x04d1
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x04d1:
            r1 = r0
            com.appsamurai.storyly.storylypresenter.storylylayer.b r1 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r1
            r0 = r21
            r3 = r12
            r4 = r16
            r5 = r13
            r0.a((com.appsamurai.storyly.storylypresenter.storylylayer.b) r1, (int) r2, (int) r3, (int) r4, (float) r5)
            r2 = r10
            goto L_0x04bf
        L_0x04df:
            android.widget.FrameLayout r0 = r21.getAccessibilityLayerView$storyly_release()
            if (r0 != 0) goto L_0x04e6
            goto L_0x0513
        L_0x04e6:
            java.util.List<com.appsamurai.storyly.storylypresenter.storylylayer.b> r1 = r8.f6122u
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, 10)
            r2.<init>(r3)
            java.util.Iterator r1 = r1.iterator()
        L_0x04f5:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0509
            java.lang.Object r3 = r1.next()
            com.appsamurai.storyly.storylypresenter.storylylayer.b r3 = (com.appsamurai.storyly.storylypresenter.storylylayer.b) r3
            androidx.appcompat.widget.AppCompatImageView r3 = r3.getOptionImage()
            r2.add(r3)
            goto L_0x04f5
        L_0x0509:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r2)
            r0.addChildrenForAccessibility(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x0513:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.u.a(com.appsamurai.storyly.storylypresenter.storylylayer.d):void");
    }

    public final void a(b bVar, int i3, int i4, int i5, float f2) {
        String str;
        String str2;
        float f3;
        b bVar2;
        float f4;
        int i6;
        int i7;
        int i8;
        String str3;
        int i9 = i3;
        int i10 = i4;
        int i11 = i5;
        float f5 = (float) i10;
        int i12 = (int) (0.04516f * f5);
        int i13 = (int) (0.26f * f5);
        int i14 = (int) (0.897f * f5);
        float f6 = (float) i11;
        float f7 = f6 * 0.0838f;
        float f8 = ((float) this.f6116o) * 0.074f;
        String string = getResources().getString(this.f6121t.get(i9).intValue());
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(optionNames[index])");
        Resources resources = getResources();
        int i15 = R.string.st_desc_image_quiz_alt_text;
        y yVar = this.f6111j;
        if (yVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            yVar = null;
        }
        List<String> list = yVar.f4281c;
        if (list == null || (str = list.get(i9)) == null) {
            str = "";
        }
        y yVar2 = this.f6111j;
        if (yVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            yVar2 = null;
        }
        List<String> list2 = yVar2.f4282d;
        if (list2 == null || (str2 = list2.get(i9)) == null) {
            str2 = "";
        }
        String string2 = resources.getString(i15, new Object[]{str, str2});
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …et(index) ?: \"\"\n        )");
        if (!this.f6119r) {
            AppCompatImageView optionImage = bVar.getOptionImage();
            optionImage.setImportantForAccessibility(1);
            optionImage.setContentDescription(getResources().getString(R.string.st_desc_quiz_before, new Object[]{string, string2}));
            Unit unit = Unit.INSTANCE;
        }
        int i16 = (int) (f5 - f2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i16, i16);
        layoutParams.addRule(15, bVar.getOptionImageContainer().getId());
        layoutParams.addRule(14, bVar.getOptionImageContainer().getId());
        Unit unit2 = Unit.INSTANCE;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i10, i11);
        layoutParams2.addRule(15, bVar.getOptionImageContainer().getId());
        layoutParams2.addRule(14, bVar.getOptionImageContainer().getId());
        bVar.getOptionImageContainer().addView(bVar.getOptionImage(), layoutParams);
        bVar.getOptionImageContainer().addView(bVar.getOptionImageBGDrawable(), layoutParams);
        bVar.getOptionImageContainer().addView(bVar.getOptionImageWrongBgDrawable(), layoutParams);
        bVar.getOptionImageContainer().addView(bVar.getOptionImageBorderDrawable(), layoutParams2);
        bVar.getOptionImageBGDrawable().setImageDrawable(a(f7, new int[]{com.appsamurai.storyly.util.f.a((int) ViewCompat.MEASURED_STATE_MASK, 0.72f), com.appsamurai.storyly.util.f.a((int) ViewCompat.MEASURED_STATE_MASK, 0.2f), com.appsamurai.storyly.util.f.a((int) ViewCompat.MEASURED_STATE_MASK, 0.1f), 0}, 0.8f));
        RelativeLayout imageQuizOptionViewContainer = getImageQuizOptionViewContainer();
        AppCompatImageView optionChoiceImage = bVar.getOptionChoiceImage();
        float f9 = f5 * 0.206f;
        int i17 = (int) f9;
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i17, i17);
        layoutParams3.addRule(6, bVar.getOptionImageContainer().getId());
        layoutParams3.addRule(18, bVar.getOptionImageContainer().getId());
        layoutParams3.topMargin = i12;
        layoutParams3.setMarginStart(i12);
        imageQuizOptionViewContainer.addView(optionChoiceImage, layoutParams3);
        bVar.getOptionChoiceImage().setImageResource(this.f6120s.get(i9).intValue());
        y yVar3 = this.f6111j;
        if (yVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            yVar3 = null;
        }
        if (yVar3.f4283e == null) {
            f3 = f9;
            bVar2 = null;
        } else {
            RelativeLayout imageQuizOptionViewContainer2 = getImageQuizOptionViewContainer();
            AppCompatImageView optionChoiceResultImage = bVar.getOptionChoiceResultImage();
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(i17, i17);
            layoutParams4.addRule(6, bVar.getOptionImageContainer().getId());
            layoutParams4.addRule(18, bVar.getOptionImageContainer().getId());
            layoutParams4.topMargin = i12;
            layoutParams4.setMarginStart(i12);
            imageQuizOptionViewContainer2.addView(optionChoiceResultImage, layoutParams4);
            EmojiTextView emojiView = bVar.getEmojiView();
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(i17, -2);
            layoutParams5.addRule(6, bVar.getOptionImageContainer().getId());
            layoutParams5.addRule(18, bVar.getOptionImageContainer().getId());
            f3 = f9;
            layoutParams5.topMargin = (int) (((double) i12) * 0.75d);
            layoutParams5.setMarginStart(i12);
            imageQuizOptionViewContainer2.addView(emojiView, layoutParams5);
            bVar.getEmojiView().setTextSize(0, ((float) i14) * 0.17f);
            y yVar4 = this.f6111j;
            if (yVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                yVar4 = null;
            }
            Integer num = yVar4.f4283e;
            bVar.getOptionChoiceResultImage().setImageResource((num != null && i9 == num.intValue()) ? R.drawable.st_image_quiz_correct_answer : R.drawable.st_image_quiz_wrong_answer);
            bVar2 = bVar;
        }
        if (bVar2 == null) {
            float f10 = f7 * 1.23f;
            float f11 = f10 * 0.0625f;
            RelativeLayout imageQuizOptionViewContainer3 = getImageQuizOptionViewContainer();
            View pollResultAnimatedBar = bVar.getPollResultAnimatedBar();
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(0, 0);
            float f12 = f3;
            layoutParams6.addRule(6, bVar.getOptionImageContainer().getId());
            layoutParams6.addRule(18, bVar.getOptionImageContainer().getId());
            int i18 = i12 * 2;
            layoutParams6.topMargin = i18;
            layoutParams6.setMarginStart(i18);
            imageQuizOptionViewContainer3.addView(pollResultAnimatedBar, layoutParams6);
            View pollResultAnimatedBar2 = bVar.getPollResultAnimatedBar();
            a aVar = a.ALL;
            y yVar5 = this.f6111j;
            if (yVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                yVar5 = null;
            }
            com.appsamurai.storyly.data.c cVar = yVar5.f4291m;
            if (cVar == null) {
                cVar = com.appsamurai.storyly.config.styling.a.COLOR_E0E0E0.b();
            }
            int i19 = cVar.f3624a;
            Context context = bVar.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            f4 = f7;
            pollResultAnimatedBar2.setBackground(a(aVar, f10, f11, -1, i19, context));
            RelativeLayout imageQuizOptionViewContainer4 = getImageQuizOptionViewContainer();
            TextView percentageText = bVar.getPercentageText();
            RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams((int) (0.387f * f5), -2);
            layoutParams7.addRule(6, bVar.getOptionImageContainer().getId());
            i6 = 18;
            layoutParams7.addRule(18, bVar.getOptionImageContainer().getId());
            layoutParams7.setMarginStart((int) (f6 * 0.103f));
            layoutParams7.topMargin = i12 + ((int) (f12 * 0.1875f));
            imageQuizOptionViewContainer4.addView(percentageText, layoutParams7);
            i7 = 0;
            bVar.getPercentageText().setTextSize(0, f8 * 0.75f * 0.85f);
            TextView percentageText2 = bVar.getPercentageText();
            y yVar6 = this.f6111j;
            if (yVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                yVar6 = null;
            }
            boolean z2 = yVar6.f4293o;
            y yVar7 = this.f6111j;
            if (yVar7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                yVar7 = null;
            }
            com.appsamurai.storyly.util.c.a(percentageText2, z2, yVar7.f4294p);
        } else {
            f4 = f7;
            i7 = 0;
            i6 = 18;
        }
        RelativeLayout imageQuizOptionViewContainer5 = getImageQuizOptionViewContainer();
        TextView optionText = bVar.getOptionText();
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(i14, i13);
        layoutParams8.addRule(8, bVar.getOptionImageContainer().getId());
        layoutParams8.addRule(i6, bVar.getOptionImageContainer().getId());
        layoutParams8.setMarginStart((int) (0.05f * f5));
        layoutParams8.bottomMargin = (int) (f5 * 0.0774f);
        imageQuizOptionViewContainer5.addView(optionText, layoutParams8);
        y yVar8 = this.f6111j;
        if (yVar8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            yVar8 = null;
        }
        List<String> list3 = yVar8.f4281c;
        if (list3 != null && !list3.isEmpty()) {
            i8 = i7;
            int i20 = i3;
            if (list3.size() > i20) {
                TextView optionText2 = bVar.getOptionText();
                y yVar9 = this.f6111j;
                if (yVar9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                    yVar9 = null;
                }
                List<String> list4 = yVar9.f4281c;
                if (list4 == null || (str3 = list4.get(i20)) == null) {
                    str3 = "";
                }
                optionText2.setText(str3);
            }
        } else {
            i8 = i7;
            int i21 = i3;
        }
        bVar.getOptionText().setTypeface(this.f6108g.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        bVar.getOptionText().setTextSize(i8, f8 * 0.75f * 0.7f);
        TextView optionText3 = bVar.getOptionText();
        y yVar10 = this.f6111j;
        if (yVar10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            yVar10 = null;
        }
        boolean z3 = yVar10.f4293o;
        y yVar11 = this.f6111j;
        if (yVar11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            yVar11 = null;
        }
        com.appsamurai.storyly.util.c.a(optionText3, z3, yVar11.f4294p);
        bVar.getOptionImage().setOnClickListener(new q(this, i3, bVar, f4, f2));
        l.a(bVar.getOptionImage(), new com.appsamurai.storyly.util.ui.c((View) null));
    }

    public static final void a(u uVar, int i3, b bVar, float f2, float f3, View view) {
        Intrinsics.checkNotNullParameter(uVar, "this$0");
        Intrinsics.checkNotNullParameter(bVar, "$this_apply");
        if (!uVar.f6119r) {
            Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> onUserReaction$storyly_release = uVar.getOnUserReaction$storyly_release();
            com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.ImageQuizAnswered;
            b0 storylyLayerItem$storyly_release = uVar.getStorylyLayerItem$storyly_release();
            b0 storylyLayerItem$storyly_release2 = uVar.getStorylyLayerItem$storyly_release();
            StoryComponent a2 = storylyLayerItem$storyly_release2.f3615j.a(storylyLayerItem$storyly_release2, i3);
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            JsonElementBuildersKt.put(jsonObjectBuilder, "activity", String.valueOf(i3));
            Unit unit = Unit.INSTANCE;
            onUserReaction$storyly_release.invoke(aVar, storylyLayerItem$storyly_release, a2, jsonObjectBuilder.build(), null);
            String str = uVar.getStorylyLayerItem$storyly_release().f3614i;
            SharedPreferences imageQuizSharedPreferences = uVar.getImageQuizSharedPreferences();
            Intrinsics.checkNotNullExpressionValue(imageQuizSharedPreferences, "imageQuizSharedPreferences");
            SharedPreferences.Editor edit = imageQuizSharedPreferences.edit();
            Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
            edit.putInt(str, i3);
            edit.apply();
            y yVar = uVar.f6111j;
            Unit unit2 = null;
            if (yVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                yVar = null;
            }
            if (yVar.f4283e != null) {
                if (bVar.f5598a) {
                    bVar.getEmojiView().setText(EmojiCompat.get().process("🥳"));
                } else {
                    bVar.getEmojiView().setText(EmojiCompat.get().process("😕"));
                }
                uVar.a(Integer.valueOf(i3), f2, f3, 800, 600);
                unit2 = Unit.INSTANCE;
            }
            if (unit2 == null) {
                uVar.a(Integer.valueOf(i3), 600, f2);
            }
        }
        uVar.f6119r = true;
    }

    public final Drawable a(float f2, int[] iArr, float f3) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        if (iArr.length == 1) {
            gradientDrawable.setColor(iArr[0]);
        } else {
            gradientDrawable.setColors(iArr);
        }
        gradientDrawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        gradientDrawable.setGradientType(0);
        gradientDrawable.setCornerRadii(new float[]{f2, f2, f2, f2, f2, f2, f2, f2});
        setAlpha(f3);
        return gradientDrawable;
    }

    public final Drawable a(a aVar, float f2, float f3, int i3, int i4, Context context) {
        float f4 = f3;
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.st_rectangle_drawable_shape);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) ((GradientDrawable) drawable).mutate();
            if (f4 != 0.0f) {
                gradientDrawable.setStroke((int) ((float) Math.ceil((double) f4)), i4);
            }
            gradientDrawable.setColor(i3);
            int i5 = b.$EnumSwitchMapping$0[aVar.ordinal()];
            if (i5 == 1) {
                gradientDrawable.setCornerRadii(new float[]{f2, f2, f2, f2, 0.0f, 0.0f, 0.0f, 0.0f});
            } else if (i5 == 2) {
                gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, f2, f2, f2, f2});
            } else if (i5 == 3) {
                gradientDrawable.setCornerRadii(new float[]{f2, f2, f2, f2, f2, f2, f2, f2});
            }
            return gradientDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }
}
