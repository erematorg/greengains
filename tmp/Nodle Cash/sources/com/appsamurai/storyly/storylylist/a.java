package com.appsamurai.storyly.storylylist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryGroup;
import com.appsamurai.storyly.StoryGroupAnimation;
import com.appsamurai.storyly.StoryGroupSize;
import com.appsamurai.storyly.StoryGroupStyle;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.config.styling.group.StoryGroupView;
import com.appsamurai.storyly.data.m0;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.util.ui.k;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class a extends StoryGroupView {

    /* renamed from: o  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f4672o = {androidx.compose.ui.autofill.a.m(a.class, "thematicIconLabel", "getThematicIconLabel()Ljava/lang/String;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f4673a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public v f4674b;

    /* renamed from: c  reason: collision with root package name */
    public final int f4675c;

    /* renamed from: d  reason: collision with root package name */
    public final int f4676d;

    /* renamed from: e  reason: collision with root package name */
    public final float f4677e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public Triple<? extends StoryGroupSize, Integer, Integer> f4678f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final com.appsamurai.storyly.databinding.c f4679g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f4680h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f4681i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f4682j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f4683k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f4684l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public CountDownTimer f4685m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final ReadWriteProperty f4686n;

    /* renamed from: com.appsamurai.storyly.storylylist.a$a  reason: collision with other inner class name */
    public /* synthetic */ class C0014a {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StoryGroupSize.values().length];
            iArr[StoryGroupSize.Small.ordinal()] = 1;
            iArr[StoryGroupSize.Custom.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final class b extends Lambda implements Function0<CardView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4687a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ a f4688b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context, a aVar) {
            super(0);
            this.f4687a = context;
            this.f4688b = aVar;
        }

        public Object invoke() {
            CardView cardView = new CardView(this.f4687a);
            a aVar = this.f4688b;
            cardView.setCardElevation(0.0f);
            cardView.setRadius(aVar.f4677e);
            cardView.setCardBackgroundColor(aVar.getConfig().getGroup$storyly_release().getIconBackgroundColor$storyly_release());
            return cardView;
        }
    }

    public static final class c extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4689a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f4689a = context;
        }

        public Object invoke() {
            return new FrameLayout(this.f4689a);
        }
    }

    public static final class d extends Lambda implements Function0<k> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4690a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ a f4691b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context, a aVar) {
            super(0);
            this.f4690a = context;
            this.f4691b = aVar;
        }

        public Object invoke() {
            return new k(this.f4690a, this.f4691b.getConfig(), false, 4);
        }
    }

    public static final class e implements RequestListener<Drawable> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f4692a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StoryGroup f4693b;

        public e(a aVar, StoryGroup storyGroup) {
            this.f4692a = aVar;
            this.f4693b = storyGroup;
        }

        public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z2) {
            return false;
        }

        /* JADX WARNING: Removed duplicated region for block: B:42:0x00c4  */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x00c6  */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x00e3  */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x00e5  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x00f4  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onResourceReady(java.lang.Object r17, java.lang.Object r18, com.bumptech.glide.request.target.Target r19, com.bumptech.glide.load.DataSource r20, boolean r21) {
            /*
                r16 = this;
                r0 = r16
                r1 = r17
                android.graphics.drawable.Drawable r1 = (android.graphics.drawable.Drawable) r1
                com.appsamurai.storyly.storylylist.a r1 = r0.f4692a
                com.appsamurai.storyly.util.ui.k r1 = r1.getStorylyIconBorder()
                com.appsamurai.storyly.storylylist.a r2 = r0.f4692a
                com.appsamurai.storyly.StoryGroup r3 = r0.f4693b
                r2.getClass()
                boolean r4 = r3.getSeen()
                r6 = 0
                r7 = 1
                if (r4 != r7) goto L_0x003c
                com.appsamurai.storyly.config.StorylyConfig r3 = r2.f4673a
                com.appsamurai.storyly.data.m0 r3 = r3.getStorylyStyle$storyly_release()
                if (r3 != 0) goto L_0x0025
                r3 = 0
                goto L_0x0027
            L_0x0025:
                java.util.List<java.lang.Integer> r3 = r3.f3863b
            L_0x0027:
                if (r3 != 0) goto L_0x0036
                com.appsamurai.storyly.config.StorylyConfig r2 = r2.getConfig()
                com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling r2 = r2.getGroup$storyly_release()
                java.util.List r3 = r2.getIconBorderColorSeen$storyly_release()
                goto L_0x0037
            L_0x0036:
                r7 = r6
            L_0x0037:
                java.util.List r2 = kotlin.collections.CollectionsKt.toMutableList(r3)
                goto L_0x007e
            L_0x003c:
                com.appsamurai.storyly.StoryGroupStyle r3 = r3.getStyle()
                if (r3 != 0) goto L_0x0043
                goto L_0x0058
            L_0x0043:
                java.util.List r3 = r3.getBorderUnseenColors()
                if (r3 != 0) goto L_0x004a
                goto L_0x0058
            L_0x004a:
                java.util.List r3 = kotlin.collections.CollectionsKt.toMutableList(r3)
                if (r3 != 0) goto L_0x0051
                goto L_0x0058
            L_0x0051:
                boolean r4 = r3.isEmpty()
                if (r4 != 0) goto L_0x0058
                goto L_0x0059
            L_0x0058:
                r3 = 0
            L_0x0059:
                if (r3 != 0) goto L_0x007c
                com.appsamurai.storyly.config.StorylyConfig r3 = r2.f4673a
                com.appsamurai.storyly.data.m0 r3 = r3.getStorylyStyle$storyly_release()
                if (r3 != 0) goto L_0x0065
                r3 = 0
                goto L_0x0067
            L_0x0065:
                java.util.List<java.lang.Integer> r3 = r3.f3862a
            L_0x0067:
                if (r3 != 0) goto L_0x0076
                com.appsamurai.storyly.config.StorylyConfig r2 = r2.getConfig()
                com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling r2 = r2.getGroup$storyly_release()
                java.util.List r3 = r2.getIconBorderColorNotSeen$storyly_release()
                goto L_0x0077
            L_0x0076:
                r7 = r6
            L_0x0077:
                java.util.List r2 = kotlin.collections.CollectionsKt.toMutableList(r3)
                goto L_0x007e
            L_0x007c:
                r2 = r3
                r7 = r6
            L_0x007e:
                if (r7 != 0) goto L_0x0087
                java.lang.Object r3 = kotlin.collections.CollectionsKt.first(r2)
                r2.add(r3)
            L_0x0087:
                r1.setBorderColor$storyly_release(r2)
                com.appsamurai.storyly.storylylist.a r1 = r0.f4692a
                com.appsamurai.storyly.databinding.c r1 = r1.f4679g
                android.widget.FrameLayout r1 = r1.f4337d
                com.appsamurai.storyly.StoryGroup r2 = r0.f4693b
                boolean r2 = r2.getPinned()
                r3 = 8
                if (r2 == 0) goto L_0x00ac
                com.appsamurai.storyly.StoryGroup r2 = r0.f4693b
                com.appsamurai.storyly.StoryGroupStyle r2 = r2.getStyle()
                if (r2 != 0) goto L_0x00a4
                r2 = 0
                goto L_0x00a8
            L_0x00a4:
                com.appsamurai.storyly.StoryGroupBadgeStyle r2 = r2.getBadge()
            L_0x00a8:
                if (r2 != 0) goto L_0x00ac
                r2 = r6
                goto L_0x00ad
            L_0x00ac:
                r2 = r3
            L_0x00ad:
                r1.setVisibility(r2)
                com.appsamurai.storyly.storylylist.a r1 = r0.f4692a
                com.appsamurai.storyly.databinding.c r2 = r1.f4679g
                android.widget.TextView r2 = r2.f4339f
                com.appsamurai.storyly.config.StorylyConfig r1 = r1.getConfig()
                com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling r1 = r1.getGroup$storyly_release()
                boolean r1 = r1.isTitleVisible$storyly_release()
                if (r1 == 0) goto L_0x00c6
                r1 = r6
                goto L_0x00c7
            L_0x00c6:
                r1 = r3
            L_0x00c7:
                r2.setVisibility(r1)
                com.appsamurai.storyly.storylylist.a r1 = r0.f4692a
                com.appsamurai.storyly.databinding.c r2 = r1.f4679g
                android.widget.TextView r2 = r2.f4339f
                com.appsamurai.storyly.StoryGroup r4 = r0.f4693b
                int r1 = r1.a((com.appsamurai.storyly.StoryGroup) r4)
                r2.setTextColor(r1)
                com.appsamurai.storyly.storylylist.a r1 = r0.f4692a
                com.appsamurai.storyly.StoryGroup r0 = r0.f4693b
                com.appsamurai.storyly.StoryGroupStyle r0 = r0.getStyle()
                if (r0 != 0) goto L_0x00e5
                r0 = 0
                goto L_0x00e9
            L_0x00e5:
                com.appsamurai.storyly.StoryGroupBadgeStyle r0 = r0.getBadge()
            L_0x00e9:
                com.appsamurai.storyly.databinding.c r2 = r1.f4679g
                android.widget.TextView r2 = r2.f4338e
                r2.setVisibility(r3)
                if (r0 != 0) goto L_0x00f4
                goto L_0x0240
            L_0x00f4:
                com.appsamurai.storyly.databinding.c r2 = r1.f4679g
                android.widget.TextView r2 = r2.f4338e
                r2.setVisibility(r6)
                java.lang.String r2 = r0.getText()
                if (r2 != 0) goto L_0x0103
                java.lang.String r2 = ""
            L_0x0103:
                java.lang.Integer r3 = r0.getTextColor()
                if (r3 != 0) goto L_0x010b
                r3 = -1
                goto L_0x010f
            L_0x010b:
                int r3 = r3.intValue()
            L_0x010f:
                java.lang.Integer r7 = r0.getBackgroundColor()
                if (r7 != 0) goto L_0x0118
                r7 = -16777216(0xffffffffff000000, float:-1.7014118E38)
                goto L_0x011c
            L_0x0118:
                int r7 = r7.intValue()
            L_0x011c:
                android.content.res.Resources r8 = r1.getResources()
                int r9 = com.appsamurai.storyly.R.dimen.st_story_group_badge_text_border_round
                int r8 = r8.getDimensionPixelSize(r9)
                float r8 = (float) r8
                java.lang.Integer r10 = java.lang.Integer.valueOf(r6)
                r11 = 0
                r12 = 8
                r16 = r1
                r17 = r7
                r18 = r8
                r19 = r10
                r20 = r11
                r21 = r12
                android.graphics.drawable.GradientDrawable r7 = com.appsamurai.storyly.util.ui.b.a(r16, r17, r18, r19, r20, r21)
                com.appsamurai.storyly.databinding.c r8 = r1.f4679g
                android.widget.FrameLayout r8 = r8.f4335b
                r8.setBackground(r7)
                java.lang.Long r0 = r0.getEndTime()
                if (r0 != 0) goto L_0x014e
                r0 = 0
                goto L_0x01ef
            L_0x014e:
                long r7 = r0.longValue()
                com.appsamurai.storyly.databinding.c r0 = r1.f4679g
                android.widget.TextView r0 = r0.f4338e
                java.lang.String r10 = "sans-serif-medium"
                android.graphics.Typeface r10 = android.graphics.Typeface.create(r10, r6)
                r0.setTypeface(r10)
                com.appsamurai.storyly.databinding.c r0 = r1.f4679g
                android.widget.TextView r0 = r0.f4338e
                float r0 = r0.getTextSize()
                java.util.Date r10 = new java.util.Date
                r10.<init>()
                long r10 = r10.getTime()
                r12 = 1000(0x3e8, float:1.401E-42)
                long r12 = (long) r12
                long r10 = r10 / r12
                long r10 = r7 - r10
                int r10 = (int) r10
                java.lang.String r10 = com.appsamurai.storyly.util.formatter.c.a(r10)
                android.content.Context r11 = r1.getContext()
                java.lang.String r14 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r14)
                com.appsamurai.storyly.databinding.c r15 = r1.f4679g
                android.widget.TextView r15 = r15.f4338e
                android.graphics.Typeface r15 = r15.getTypeface()
                java.lang.String r5 = "storyGroupViewBinding.stStoryGroupBadge.typeface"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r5)
                android.graphics.Rect r11 = com.appsamurai.storyly.util.p.a(r11, r10, r0, r15)
                com.appsamurai.storyly.config.StorylyConfig r15 = r1.getConfig()
                com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling r15 = r15.getGroup$storyly_release()
                int r15 = r15.getIconWidth$storyly_release()
                android.content.res.Resources r4 = r1.getResources()
                int r4 = r4.getDimensionPixelSize(r9)
                int r4 = r4 * 2
                int r15 = r15 - r4
            L_0x01ac:
                int r4 = r11.width()
                if (r4 < r15) goto L_0x01d2
                r4 = 1065353216(0x3f800000, float:1.0)
                int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r4 <= 0) goto L_0x01d2
                r4 = -1082130432(0xffffffffbf800000, float:-1.0)
                float r0 = r0 + r4
                android.content.Context r4 = r1.getContext()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r14)
                com.appsamurai.storyly.databinding.c r9 = r1.f4679g
                android.widget.TextView r9 = r9.f4338e
                android.graphics.Typeface r9 = r9.getTypeface()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r5)
                android.graphics.Rect r11 = com.appsamurai.storyly.util.p.a(r4, r10, r0, r9)
                goto L_0x01ac
            L_0x01d2:
                com.appsamurai.storyly.databinding.c r4 = r1.f4679g
                android.widget.TextView r4 = r4.f4338e
                r4.setTextSize(r6, r0)
                long r7 = r7 * r12
                java.util.Date r0 = new java.util.Date
                r0.<init>()
                long r4 = r0.getTime()
                long r7 = r7 - r4
                com.appsamurai.storyly.storylylist.b r0 = new com.appsamurai.storyly.storylylist.b
                r0.<init>(r1, r7)
                r1.f4685m = r0
                android.os.CountDownTimer r0 = r0.start()
            L_0x01ef:
                if (r0 != 0) goto L_0x01f8
                com.appsamurai.storyly.databinding.c r0 = r1.f4679g
                android.widget.TextView r0 = r0.f4338e
                r0.setText(r2)
            L_0x01f8:
                com.appsamurai.storyly.databinding.c r0 = r1.f4679g
                android.widget.TextView r0 = r0.f4338e
                r0.setTextColor(r3)
                com.appsamurai.storyly.databinding.c r0 = r1.f4679g
                android.widget.FrameLayout r0 = r0.f4335b
                android.view.ViewGroup$LayoutParams r2 = r0.getLayoutParams()
                boolean r3 = r2 instanceof android.widget.RelativeLayout.LayoutParams
                if (r3 == 0) goto L_0x020e
                android.widget.RelativeLayout$LayoutParams r2 = (android.widget.RelativeLayout.LayoutParams) r2
                goto L_0x020f
            L_0x020e:
                r2 = 0
            L_0x020f:
                if (r2 != 0) goto L_0x0213
                r5 = 0
                goto L_0x023d
            L_0x0213:
                r3 = -1
                float r3 = (float) r3
                com.appsamurai.storyly.databinding.c r4 = r1.f4679g
                android.widget.TextView r4 = r4.f4338e
                android.text.TextPaint r4 = r4.getPaint()
                android.graphics.Paint$FontMetrics r4 = r4.getFontMetrics()
                float r4 = r4.descent
                com.appsamurai.storyly.databinding.c r1 = r1.f4679g
                android.widget.TextView r1 = r1.f4338e
                android.text.TextPaint r1 = r1.getPaint()
                android.graphics.Paint$FontMetrics r1 = r1.getFontMetrics()
                float r1 = r1.ascent
                float r4 = r4 - r1
                float r4 = r4 * r3
                double r3 = (double) r4
                r7 = 4620693217682128896(0x4020000000000000, double:8.0)
                double r3 = r3 / r7
                int r1 = (int) r3
                r2.bottomMargin = r1
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                r5 = r2
            L_0x023d:
                r0.setLayoutParams(r5)
            L_0x0240:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylylist.a.e.onResourceReady(java.lang.Object, java.lang.Object, com.bumptech.glide.request.target.Target, com.bumptech.glide.load.DataSource, boolean):boolean");
        }
    }

    public static final class f extends ObservableProperty<String> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f4694a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ Context f4695b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ a f4696c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Object obj, Object obj2, Context context, a aVar) {
            super(obj2);
            this.f4694a = obj;
            this.f4695b = context;
            this.f4696c = aVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, String str, String str2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            String str3 = str2;
            String str4 = str;
            Glide.with(this.f4695b.getApplicationContext()).load(this.f4696c.getIconPath()).into((ImageView) this.f4696c.getStorylyIcon());
        }
    }

    public static final class g extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4697a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f4697a = context;
        }

        public Object invoke() {
            return new AppCompatImageView(this.f4697a);
        }
    }

    public static final class h extends Lambda implements Function0<k> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4698a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ a f4699b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context, a aVar) {
            super(0);
            this.f4698a = context;
            this.f4699b = aVar;
        }

        public Object invoke() {
            return new k(this.f4698a, this.f4699b.getConfig(), this.f4699b.getConfig().getGroup$storyly_release().getSize$storyly_release() == StoryGroupSize.Custom);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f4673a = storylyConfig;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.st_story_group_icon_distance_to_border);
        this.f4675c = dimensionPixelSize;
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.st_story_group_icon_border_thickness);
        this.f4676d = dimensionPixelSize2;
        this.f4677e = Math.max(((float) storylyConfig.getGroup$storyly_release().getIconCornerRadius$storyly_release()) - ((float) (dimensionPixelSize + dimensionPixelSize2)), 0.0f);
        com.appsamurai.storyly.databinding.c a2 = com.appsamurai.storyly.databinding.c.a(LayoutInflater.from(context));
        Intrinsics.checkNotNullExpressionValue(a2, "inflate(LayoutInflater.from(context))");
        this.f4679g = a2;
        this.f4680h = LazyKt.lazy(new b(context, this));
        this.f4681i = LazyKt.lazy(new g(context));
        this.f4682j = LazyKt.lazy(new c(context));
        this.f4683k = LazyKt.lazy(new h(context, this));
        this.f4684l = LazyKt.lazy(new d(context, this));
        Delegates delegates = Delegates.INSTANCE;
        String iconThematicImageLabel$storyly_release = storylyConfig.getGroup$storyly_release().getIconThematicImageLabel$storyly_release();
        this.f4686n = new f(iconThematicImageLabel$storyly_release, iconThematicImageLabel$storyly_release, context, this);
        e();
        d();
        int a3 = a();
        b();
        addView(a2.a(), new FrameLayout.LayoutParams(a3, -1));
    }

    private final CardView getAvatarCardView() {
        return (CardView) this.f4680h.getValue();
    }

    private final FrameLayout getGroupIconWrapper() {
        return (FrameLayout) this.f4682j.getValue();
    }

    /* access modifiers changed from: private */
    public final String getIconPath() {
        v vVar = this.f4674b;
        if (vVar == null) {
            return null;
        }
        String stringPlus = Intrinsics.stringPlus(vVar.f4223c, vVar.f4224d);
        if (StringsKt__StringsJVMKt.startsWith$default(vVar.f4224d, "http", false, 2, (Object) null)) {
            stringPlus = vVar.f4224d;
        }
        return (vVar.f4232l == null || getThematicIconLabel() == null || vVar.f4232l.get(getThematicIconLabel()) == null) ? stringPlus : Intrinsics.stringPlus(vVar.f4223c, vVar.f4232l.get(getThematicIconLabel()));
    }

    private final k getPinIcon() {
        return (k) this.f4684l.getValue();
    }

    /* access modifiers changed from: private */
    public final AppCompatImageView getStorylyIcon() {
        return (AppCompatImageView) this.f4681i.getValue();
    }

    /* access modifiers changed from: private */
    public final k getStorylyIconBorder() {
        return (k) this.f4683k.getValue();
    }

    private final String getThematicIconLabel() {
        return (String) this.f4686n.getValue(this, f4672o[0]);
    }

    private final void setThematicIconLabel(String str) {
        this.f4686n.setValue(this, f4672o[0], str);
    }

    public final void d() {
        this.f4679g.f4338e.setTextSize(this.f4673a.getGroup$storyly_release().getTitleTextSize$storyly_release().getFirst().intValue(), (float) this.f4673a.getGroup$storyly_release().getTitleTextSize$storyly_release().getSecond().intValue());
    }

    public final void e() {
        this.f4679g.f4339f.setVisibility(this.f4673a.getGroup$storyly_release().isTitleVisible$storyly_release() ? 0 : 8);
        this.f4679g.f4339f.setTypeface(this.f4673a.getGroup$storyly_release().getTitleTypeface$storyly_release());
        TextView textView = this.f4679g.f4339f;
        v vVar = this.f4674b;
        textView.setTextColor(a(vVar == null ? null : vVar.d()));
        Integer titleLineCount$storyly_release = this.f4673a.getGroup$storyly_release().getTitleLineCount$storyly_release();
        if (titleLineCount$storyly_release != null) {
            this.f4679g.f4339f.setLines(titleLineCount$storyly_release.intValue());
        }
        Integer titleMinLineCount$storyly_release = this.f4673a.getGroup$storyly_release().getTitleMinLineCount$storyly_release();
        if (titleMinLineCount$storyly_release != null) {
            this.f4679g.f4339f.setMinLines(titleMinLineCount$storyly_release.intValue());
        }
        Integer titleMaxLineCount$storyly_release = this.f4673a.getGroup$storyly_release().getTitleMaxLineCount$storyly_release();
        if (titleMaxLineCount$storyly_release != null) {
            this.f4679g.f4339f.setMaxLines(titleMaxLineCount$storyly_release.intValue());
        }
        if (this.f4673a.getGroup$storyly_release().getTitleMinLineCount$storyly_release() == null && this.f4673a.getGroup$storyly_release().getTitleMaxLineCount$storyly_release() == null && this.f4673a.getGroup$storyly_release().getTitleLineCount$storyly_release() == null) {
            this.f4679g.f4339f.setLines(2);
        }
        this.f4679g.f4339f.setTextSize(this.f4673a.getGroup$storyly_release().getTitleTextSize$storyly_release().getFirst().intValue(), (float) this.f4673a.getGroup$storyly_release().getTitleTextSize$storyly_release().getSecond().intValue());
        TextView textView2 = this.f4679g.f4339f;
        Intrinsics.checkNotNullExpressionValue(textView2, "storyGroupViewBinding.stStorylyTitle");
        com.appsamurai.storyly.util.d.a(textView2);
    }

    public final void f() {
        k storylyIconBorder = getStorylyIconBorder();
        k.a aVar = storylyIconBorder.f6436l;
        if (aVar != null) {
            storylyIconBorder.F = true;
            k kVar = aVar.f6452b;
            kVar.f6446v = 360.0f;
            if (!kVar.f6426b) {
                aVar.b().start();
            }
        }
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.f4673a;
    }

    @Nullable
    public final v getStorylyGroupItem$storyly_release() {
        return this.f4674b;
    }

    public void onDetachedFromWindow() {
        CountDownTimer countDownTimer = this.f4685m;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.onDetachedFromWindow();
    }

    public void populateView(@Nullable StoryGroup storyGroup) {
        Triple<? extends StoryGroupSize, Integer, Integer> triple = this.f4678f;
        if (!(triple == null || (triple.getFirst() == this.f4673a.getGroup$storyly_release().getSize$storyly_release() && triple.getSecond().intValue() == this.f4673a.getGroup$storyly_release().getIconWidth$storyly_release() && triple.getThird().intValue() == this.f4673a.getGroup$storyly_release().getIconHeight$storyly_release()))) {
            e();
            d();
            int a2 = a();
            b();
            removeAllViews();
            addView(this.f4679g.f4334a, new FrameLayout.LayoutParams(a2, -1));
        }
        this.f4678f = new Triple<>(this.f4673a.getGroup$storyly_release().getSize$storyly_release(), Integer.valueOf(this.f4673a.getGroup$storyly_release().getIconWidth$storyly_release()), Integer.valueOf(this.f4673a.getGroup$storyly_release().getIconHeight$storyly_release()));
        k storylyIconBorder = getStorylyIconBorder();
        m0 storylyStyle$storyly_release = this.f4673a.getStorylyStyle$storyly_release();
        StoryGroupAnimation storyGroupAnimation = storylyStyle$storyly_release == null ? null : storylyStyle$storyly_release.f3867f;
        if (storyGroupAnimation == null) {
            storyGroupAnimation = this.f4673a.getGroup$storyly_release().getIconBorderAnimation$storyly_release();
        }
        storylyIconBorder.setTheme(storyGroupAnimation);
        Glide.with(getContext().getApplicationContext()).clear((View) getStorylyIcon());
        if (storyGroup == null) {
            this.f4679g.f4339f.setText("");
            getStorylyIconBorder().setBorderColor$storyly_release(CollectionsKt.listOf(0, 0));
            this.f4679g.f4337d.setVisibility(4);
            return;
        }
        this.f4679g.f4339f.setText(storyGroup.getTitle());
        ((RequestBuilder) Glide.with(getContext().getApplicationContext()).load(getIconPath()).listener(new e(this, storyGroup)).centerCrop()).into((ImageView) getStorylyIcon());
    }

    public final void setStorylyGroupItem$storyly_release(@Nullable v vVar) {
        this.f4674b = vVar;
    }

    public final void setStorylyIconGroupAnimation$storyly_release(@NotNull StoryGroupAnimation storyGroupAnimation) {
        Intrinsics.checkNotNullParameter(storyGroupAnimation, "theme");
        getStorylyIconBorder().setTheme(storyGroupAnimation);
    }

    public final int a() {
        getAvatarCardView().addView(getStorylyIcon());
        FrameLayout groupIconWrapper = getGroupIconWrapper();
        CardView avatarCardView = getAvatarCardView();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        int i3 = this.f4675c + this.f4676d;
        layoutParams.setMargins(i3, i3, i3, i3);
        Unit unit = Unit.INSTANCE;
        groupIconWrapper.addView(avatarCardView, layoutParams);
        getGroupIconWrapper().addView(getStorylyIconBorder());
        getStorylyIconBorder().setAvatarBackgroundColor$storyly_release(0);
        this.f4679g.f4336c.removeAllViews();
        this.f4679g.f4336c.addView(getGroupIconWrapper(), 0, new ViewGroup.LayoutParams(this.f4673a.getGroup$storyly_release().getIconWidth$storyly_release(), this.f4673a.getGroup$storyly_release().getIconHeight$storyly_release()));
        return this.f4673a.getGroup$storyly_release().getIconWidth$storyly_release();
    }

    public final void b() {
        float dimension;
        Integer num;
        this.f4679g.f4337d.setVisibility(8);
        int i3 = C0014a.$EnumSwitchMapping$0[this.f4673a.getGroup$storyly_release().getSize$storyly_release().ordinal()];
        if (i3 == 1) {
            this.f4679g.f4337d.setPadding(0, 0, getContext().getResources().getDimensionPixelSize(R.dimen.st_story_group_pin_icon_small_horizontal_padding), getContext().getResources().getDimensionPixelSize(R.dimen.st_story_group_pin_icon_small_vertical_padding));
            dimension = getContext().getResources().getDimension(R.dimen.st_story_group_pin_size_small);
        } else if (i3 != 2) {
            this.f4679g.f4337d.setPadding(0, 0, getContext().getResources().getDimensionPixelSize(R.dimen.st_story_group_pin_icon_large_horizontal_padding), getContext().getResources().getDimensionPixelSize(R.dimen.st_story_group_pin_icon_large_vertical_padding));
            dimension = getContext().getResources().getDimension(R.dimen.st_story_group_pin_size_large);
        } else {
            Resources resources = getContext().getResources();
            int i4 = R.dimen.st_story_group_pin_size_large;
            double iconCornerRadius$storyly_release = (double) this.f4673a.getGroup$storyly_release().getIconCornerRadius$storyly_release();
            int dimension2 = ((int) resources.getDimension(i4)) / 2;
            this.f4679g.f4337d.setPadding(0, 0, ((int) (iconCornerRadius$storyly_release - (Math.cos(5.497787143782138d) * iconCornerRadius$storyly_release))) - dimension2, ((int) (iconCornerRadius$storyly_release - (Math.sin(0.7853981633974483d) * iconCornerRadius$storyly_release))) - dimension2);
            dimension = getContext().getResources().getDimension(i4);
        }
        int i5 = (int) dimension;
        getPinIcon().setImageResource(R.drawable.st_pin_icon);
        k pinIcon = getPinIcon();
        m0 storylyStyle$storyly_release = this.f4673a.getStorylyStyle$storyly_release();
        if (storylyStyle$storyly_release == null) {
            num = null;
        } else {
            num = storylyStyle$storyly_release.f3866e;
        }
        pinIcon.setAvatarBackgroundColor$storyly_release(num == null ? this.f4673a.getGroup$storyly_release().getPinIconColor$storyly_release() : num.intValue());
        this.f4679g.f4337d.removeAllViews();
        this.f4679g.f4337d.addView(getPinIcon(), i5, i5);
    }

    public final void c() {
        k storylyIconBorder = getStorylyIconBorder();
        storylyIconBorder.F = false;
        k.a aVar = storylyIconBorder.f6436l;
        if (aVar != null) {
            aVar.a();
        }
    }

    public final int a(StoryGroup storyGroup) {
        StoryGroupStyle style;
        Integer num = null;
        if (Intrinsics.areEqual((Object) storyGroup == null ? null : Boolean.valueOf(storyGroup.getSeen()), (Object) Boolean.TRUE)) {
            m0 storylyStyle$storyly_release = this.f4673a.getStorylyStyle$storyly_release();
            if (storylyStyle$storyly_release != null) {
                num = storylyStyle$storyly_release.f3865d;
            }
            return num == null ? this.f4673a.getGroup$storyly_release().getTitleSeenColor$storyly_release() : num.intValue();
        }
        Integer textUnseenColor = (storyGroup == null || (style = storyGroup.getStyle()) == null) ? null : style.getTextUnseenColor();
        if (textUnseenColor == null) {
            m0 storylyStyle$storyly_release2 = this.f4673a.getStorylyStyle$storyly_release();
            if (storylyStyle$storyly_release2 != null) {
                num = storylyStyle$storyly_release2.f3864c;
            }
            if (num == null) {
                return this.f4673a.getGroup$storyly_release().getTitleNotSeenColor$storyly_release();
            }
            textUnseenColor = num;
        }
        return textUnseenColor.intValue();
    }
}
