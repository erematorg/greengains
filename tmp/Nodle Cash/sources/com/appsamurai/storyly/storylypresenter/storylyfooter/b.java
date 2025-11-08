package com.appsamurai.storyly.storylypresenter.storylyfooter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.util.m;
import com.appsamurai.storyly.util.ui.k;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
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
public final class b extends FrameLayout {

    /* renamed from: j  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5393j;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ReadWriteProperty f5394a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final ReadWriteProperty f5395b = new h((Object) null, (Object) null, this);
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Lazy f5396c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Lazy f5397d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final Lazy f5398e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Lazy f5399f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f5400g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f5401h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f5402i;

    public static final class a extends Lambda implements Function0<k> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5403a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyConfig f5404b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, StorylyConfig storylyConfig) {
            super(0);
            this.f5403a = context;
            this.f5404b = storylyConfig;
        }

        public Object invoke() {
            k kVar = new k(this.f5403a, this.f5404b, false, 4);
            kVar.setAnalyticsAvatarBorder$storyly_release(Color.parseColor("#515151"));
            return kVar;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylyfooter.b$b  reason: collision with other inner class name */
    public static final class C0037b extends Lambda implements Function0<FrameLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5405a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5406b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0037b(Context context, b bVar) {
            super(0);
            this.f5405a = context;
            this.f5406b = bVar;
        }

        public Object invoke() {
            FrameLayout frameLayout = new FrameLayout(this.f5405a);
            b bVar = this.f5406b;
            frameLayout.setVisibility(8);
            frameLayout.addView(bVar.getFirstUserImageView(), new LinearLayout.LayoutParams(m.a((Number) 20), -1));
            k d2 = bVar.getSecondUserImageView();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(m.a((Number) 20), -1);
            layoutParams.setMarginStart(m.a((Number) 8));
            Unit unit = Unit.INSTANCE;
            frameLayout.addView(d2, layoutParams);
            k e3 = bVar.getThirdUserImageView();
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(m.a((Number) 20), -1);
            layoutParams2.setMarginStart(m.a((Number) 16));
            frameLayout.addView(e3, layoutParams2);
            return frameLayout;
        }
    }

    public static final class c extends Lambda implements Function0<LinearLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5407a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5407a = context;
        }

        public Object invoke() {
            LinearLayout linearLayout = new LinearLayout(this.f5407a);
            linearLayout.setGravity(16);
            linearLayout.setBackgroundResource(R.drawable.st_moments_analytics_button_background);
            linearLayout.setOrientation(0);
            return linearLayout;
        }
    }

    public static final class d extends Lambda implements Function0<TextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5408a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5408a = context;
        }

        public Object invoke() {
            TextView textView = new TextView(this.f5408a);
            textView.setMaxLines(1);
            textView.setTextColor(-1);
            textView.setTextSize(1, 14.0f);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setLineSpacing((float) m.a((Number) Double.valueOf(4.83d)), 1.0f);
            textView.setVisibility(8);
            return textView;
        }
    }

    public static final class e extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5409a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyConfig f5410b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context, StorylyConfig storylyConfig) {
            super(0);
            this.f5409a = context;
            this.f5410b = storylyConfig;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5409a);
            StorylyConfig storylyConfig = this.f5410b;
            Context context = this.f5409a;
            appCompatImageView.setMaxWidth(m.a((Number) 18));
            appCompatImageView.setMaxHeight(m.a((Number) 18));
            appCompatImageView.setAdjustViewBounds(true);
            Drawable storyUnlikeIcon$storyly_release = storylyConfig.getMoments$storyly_release().getIconStyling$storyly_release().getStoryUnlikeIcon$storyly_release();
            if (storyUnlikeIcon$storyly_release == null) {
                storyUnlikeIcon$storyly_release = AppCompatResources.getDrawable(context, R.drawable.st_moments_analytics_unlike);
            }
            appCompatImageView.setImageDrawable(storyUnlikeIcon$storyly_release);
            return appCompatImageView;
        }
    }

    public static final class f extends Lambda implements Function0<k> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5411a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyConfig f5412b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context, StorylyConfig storylyConfig) {
            super(0);
            this.f5411a = context;
            this.f5412b = storylyConfig;
        }

        public Object invoke() {
            k kVar = new k(this.f5411a, this.f5412b, false, 4);
            kVar.setAnalyticsAvatarBorder$storyly_release(Color.parseColor("#515151"));
            return kVar;
        }
    }

    public static final class g extends ObservableProperty<Boolean> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f5413a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyConfig f5414b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ Context f5415c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Object obj, Object obj2, b bVar, StorylyConfig storylyConfig, Context context) {
            super(obj2);
            this.f5413a = bVar;
            this.f5414b = storylyConfig;
            this.f5415c = context;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Boolean bool, Boolean bool2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            boolean booleanValue = bool2.booleanValue();
            bool.getClass();
            if (booleanValue) {
                AppCompatImageView c3 = this.f5413a.getLikeIcon();
                Drawable storyLikeIcon$storyly_release = this.f5414b.getMoments$storyly_release().getIconStyling$storyly_release().getStoryLikeIcon$storyly_release();
                if (storyLikeIcon$storyly_release == null) {
                    storyLikeIcon$storyly_release = AppCompatResources.getDrawable(this.f5415c, R.drawable.st_moments_analytics_like);
                }
                c3.setImageDrawable(storyLikeIcon$storyly_release);
                return;
            }
            AppCompatImageView c4 = this.f5413a.getLikeIcon();
            Drawable storyUnlikeIcon$storyly_release = this.f5414b.getMoments$storyly_release().getIconStyling$storyly_release().getStoryUnlikeIcon$storyly_release();
            if (storyUnlikeIcon$storyly_release == null) {
                storyUnlikeIcon$storyly_release = AppCompatResources.getDrawable(this.f5415c, R.drawable.st_moments_analytics_unlike);
            }
            c4.setImageDrawable(storyUnlikeIcon$storyly_release);
        }
    }

    public static final class h extends ObservableProperty<com.appsamurai.storyly.analytics.b> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f5416a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Object obj, Object obj2, b bVar) {
            super(null);
            this.f5416a = bVar;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0099, code lost:
            r3 = r3.f3503b;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void afterChange(@org.jetbrains.annotations.NotNull kotlin.reflect.KProperty<?> r6, com.appsamurai.storyly.analytics.b r7, com.appsamurai.storyly.analytics.b r8) {
            /*
                r5 = this;
                java.lang.String r0 = "property"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                com.appsamurai.storyly.analytics.b r8 = (com.appsamurai.storyly.analytics.b) r8
                com.appsamurai.storyly.analytics.b r7 = (com.appsamurai.storyly.analytics.b) r7
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                com.appsamurai.storyly.analytics.b r6 = r6.getLikeStats$storyly_release()
                r7 = 8
                r8 = 5
                java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
                r0 = 0
                r1 = 0
                r2 = -2
                if (r6 != 0) goto L_0x001d
                goto L_0x011c
            L_0x001d:
                java.lang.Integer r6 = r6.f3503b
                if (r6 != 0) goto L_0x0023
                goto L_0x011c
            L_0x0023:
                int r6 = r6.intValue()
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r3 = r5.f5416a
                boolean r3 = r3.getLikeStatus$storyly_release()
                if (r3 == 0) goto L_0x0069
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r3 = r5.f5416a
                com.appsamurai.storyly.analytics.b r3 = r3.getLikeStats$storyly_release()
                if (r3 != 0) goto L_0x0038
                goto L_0x0044
            L_0x0038:
                java.util.List<com.appsamurai.storyly.analytics.c> r3 = r3.f3504c
                if (r3 != 0) goto L_0x003d
                goto L_0x0044
            L_0x003d:
                int r3 = r3.size()
                if (r6 != r3) goto L_0x0044
                goto L_0x0069
            L_0x0044:
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                com.appsamurai.storyly.analytics.b r6 = r6.getLikeStats$storyly_release()
                if (r6 != 0) goto L_0x004d
                goto L_0x00ab
            L_0x004d:
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r3 = r5.f5416a
                com.appsamurai.storyly.analytics.b r3 = r3.getLikeStats$storyly_release()
                if (r3 != 0) goto L_0x0056
                goto L_0x005a
            L_0x0056:
                java.lang.Integer r3 = r3.f3503b
                if (r3 != 0) goto L_0x005c
            L_0x005a:
                r3 = r0
                goto L_0x0066
            L_0x005c:
                int r3 = r3.intValue()
                int r3 = r3 + 1
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            L_0x0066:
                r6.f3503b = r3
                goto L_0x00ab
            L_0x0069:
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r3 = r5.f5416a
                boolean r3 = r3.getLikeStatus$storyly_release()
                if (r3 != 0) goto L_0x00ab
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r3 = r5.f5416a
                com.appsamurai.storyly.analytics.b r3 = r3.getLikeStats$storyly_release()
                if (r3 != 0) goto L_0x007a
                goto L_0x00ab
            L_0x007a:
                java.util.List<com.appsamurai.storyly.analytics.c> r3 = r3.f3504c
                if (r3 != 0) goto L_0x007f
                goto L_0x00ab
            L_0x007f:
                int r3 = r3.size()
                if (r6 != r3) goto L_0x00ab
                if (r6 <= 0) goto L_0x00ab
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                com.appsamurai.storyly.analytics.b r6 = r6.getLikeStats$storyly_release()
                if (r6 != 0) goto L_0x0090
                goto L_0x00ab
            L_0x0090:
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r3 = r5.f5416a
                com.appsamurai.storyly.analytics.b r3 = r3.getLikeStats$storyly_release()
                if (r3 != 0) goto L_0x0099
                goto L_0x009d
            L_0x0099:
                java.lang.Integer r3 = r3.f3503b
                if (r3 != 0) goto L_0x009f
            L_0x009d:
                r3 = r0
                goto L_0x00a9
            L_0x009f:
                int r3 = r3.intValue()
                int r3 = r3 + -1
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            L_0x00a9:
                r6.f3503b = r3
            L_0x00ab:
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                com.appsamurai.storyly.analytics.b r6 = r6.getLikeStats$storyly_release()
                if (r6 != 0) goto L_0x00b4
                goto L_0x00b8
            L_0x00b4:
                java.lang.Integer r6 = r6.f3503b
                if (r6 != 0) goto L_0x00ba
            L_0x00b8:
                r6 = r1
                goto L_0x00be
            L_0x00ba:
                int r6 = r6.intValue()
            L_0x00be:
                if (r6 <= 0) goto L_0x00fe
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                androidx.appcompat.widget.AppCompatImageView r6 = r6.getLikeIcon()
                android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
                r3.<init>(r2, r2)
                int r4 = com.appsamurai.storyly.util.m.a((java.lang.Number) r8)
                r3.setMarginEnd(r4)
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                r6.setLayoutParams(r3)
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                android.widget.TextView r6 = r6.getLikeCountText()
                r6.setVisibility(r1)
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                android.widget.TextView r6 = r6.getLikeCountText()
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r3 = r5.f5416a
                com.appsamurai.storyly.analytics.b r3 = r3.getLikeStats$storyly_release()
                if (r3 != 0) goto L_0x00ef
                goto L_0x00f1
            L_0x00ef:
                java.lang.Integer r0 = r3.f3503b
            L_0x00f1:
                java.lang.String r0 = java.lang.String.valueOf(r0)
                r6.setText(r0)
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                com.appsamurai.storyly.storylypresenter.storylyfooter.b.f(r6)
                goto L_0x011a
            L_0x00fe:
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                androidx.appcompat.widget.AppCompatImageView r6 = r6.getLikeIcon()
                android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
                r0.<init>(r2, r2)
                r0.setMarginEnd(r1)
                kotlin.Unit r3 = kotlin.Unit.INSTANCE
                r6.setLayoutParams(r0)
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                android.widget.TextView r6 = r6.getLikeCountText()
                r6.setVisibility(r7)
            L_0x011a:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
            L_0x011c:
                if (r0 != 0) goto L_0x016e
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                boolean r6 = r6.getLikeStatus$storyly_release()
                if (r6 == 0) goto L_0x0152
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                androidx.appcompat.widget.AppCompatImageView r6 = r6.getLikeIcon()
                android.widget.LinearLayout$LayoutParams r7 = new android.widget.LinearLayout$LayoutParams
                r7.<init>(r2, r2)
                int r8 = com.appsamurai.storyly.util.m.a((java.lang.Number) r8)
                r7.setMarginEnd(r8)
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                r6.setLayoutParams(r7)
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                android.widget.TextView r6 = r6.getLikeCountText()
                r6.setVisibility(r1)
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r5 = r5.f5416a
                android.widget.TextView r5 = r5.getLikeCountText()
                java.lang.String r6 = "1"
                r5.setText(r6)
                goto L_0x016e
            L_0x0152:
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r6 = r5.f5416a
                androidx.appcompat.widget.AppCompatImageView r6 = r6.getLikeIcon()
                android.widget.LinearLayout$LayoutParams r8 = new android.widget.LinearLayout$LayoutParams
                r8.<init>(r2, r2)
                r8.setMarginEnd(r1)
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                r6.setLayoutParams(r8)
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r5 = r5.f5416a
                android.widget.TextView r5 = r5.getLikeCountText()
                r5.setVisibility(r7)
            L_0x016e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylyfooter.b.h.afterChange(kotlin.reflect.KProperty, java.lang.Object, java.lang.Object):void");
        }
    }

    public static final class i extends Lambda implements Function0<k> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5417a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyConfig f5418b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(Context context, StorylyConfig storylyConfig) {
            super(0);
            this.f5417a = context;
            this.f5418b = storylyConfig;
        }

        public Object invoke() {
            k kVar = new k(this.f5417a, this.f5418b, false, 4);
            kVar.setAnalyticsAvatarBorder$storyly_release(Color.parseColor("#515151"));
            return kVar;
        }
    }

    static {
        Class<b> cls = b.class;
        f5393j = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "likeStatus", "getLikeStatus$storyly_release()Z", 0), androidx.compose.ui.autofill.a.m(cls, "likeStats", "getLikeStats$storyly_release()Lcom/appsamurai/storyly/analytics/MomentsAnalytic;", 0)};
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Delegates delegates = Delegates.INSTANCE;
        Boolean bool = Boolean.FALSE;
        this.f5394a = new g(bool, bool, this, storylyConfig, context);
        this.f5396c = LazyKt.lazy(new c(context));
        this.f5397d = LazyKt.lazy(new e(context, storylyConfig));
        this.f5398e = LazyKt.lazy(new d(context));
        this.f5399f = LazyKt.lazy(new a(context, storylyConfig));
        this.f5400g = LazyKt.lazy(new f(context, storylyConfig));
        this.f5401h = LazyKt.lazy(new i(context, storylyConfig));
        this.f5402i = LazyKt.lazy(new C0037b(context, this));
        setClickable(true);
        addView(getLayerView(), new FrameLayout.LayoutParams(-2, -2));
        getLayerView().addView(getLikeIcon(), new LinearLayout.LayoutParams(m.a((Number) 18), m.a((Number) 18)));
        getLayerView().addView(getLikeCountText(), new LinearLayout.LayoutParams(-2, -2));
        LinearLayout layerView = getLayerView();
        FrameLayout lastLikedUsersContainer = getLastLikedUsersContainer();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.setMarginStart(m.a((Number) 10));
        Unit unit = Unit.INSTANCE;
        layerView.addView(lastLikedUsersContainer, layoutParams);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        r0 = r0.f3504c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void f(com.appsamurai.storyly.storylypresenter.storylyfooter.b r5) {
        /*
            android.widget.FrameLayout r0 = r5.getLastLikedUsersContainer()
            r1 = 8
            r0.setVisibility(r1)
            com.appsamurai.storyly.analytics.b r0 = r5.getLikeStats$storyly_release()
            if (r0 != 0) goto L_0x0011
            goto L_0x00fe
        L_0x0011:
            java.lang.Integer r0 = r0.f3503b
            if (r0 != 0) goto L_0x0017
            goto L_0x00fe
        L_0x0017:
            int r0 = r0.intValue()
            r1 = 3
            if (r0 < r1) goto L_0x00fe
            com.appsamurai.storyly.analytics.b r0 = r5.getLikeStats$storyly_release()
            r2 = 0
            if (r0 != 0) goto L_0x0026
            goto L_0x002a
        L_0x0026:
            java.util.List<com.appsamurai.storyly.analytics.c> r0 = r0.f3504c
            if (r0 != 0) goto L_0x002c
        L_0x002a:
            r0 = r2
            goto L_0x0030
        L_0x002c:
            int r0 = r0.size()
        L_0x0030:
            if (r0 < r1) goto L_0x00fe
            android.widget.FrameLayout r0 = r5.getLastLikedUsersContainer()
            r0.setVisibility(r2)
            com.appsamurai.storyly.analytics.b r0 = r5.getLikeStats$storyly_release()
            r3 = 0
            if (r0 != 0) goto L_0x0041
            goto L_0x004c
        L_0x0041:
            java.util.List<com.appsamurai.storyly.analytics.c> r0 = r0.f3504c
            if (r0 != 0) goto L_0x0046
            goto L_0x004c
        L_0x0046:
            java.util.List r0 = kotlin.collections.CollectionsKt.takeLast(r0, r1)
            if (r0 != 0) goto L_0x004e
        L_0x004c:
            r0 = r3
            goto L_0x0052
        L_0x004e:
            java.util.List r0 = kotlin.collections.CollectionsKt.toMutableList(r0)
        L_0x0052:
            boolean r1 = r5.getLikeStatus$storyly_release()
            r4 = 2
            if (r1 == 0) goto L_0x008c
            com.appsamurai.storyly.analytics.b r0 = r5.getLikeStats$storyly_release()
            if (r0 != 0) goto L_0x0060
            goto L_0x006b
        L_0x0060:
            java.util.List<com.appsamurai.storyly.analytics.c> r0 = r0.f3504c
            if (r0 != 0) goto L_0x0065
            goto L_0x006b
        L_0x0065:
            java.util.List r0 = kotlin.collections.CollectionsKt.takeLast(r0, r4)
            if (r0 != 0) goto L_0x006d
        L_0x006b:
            r0 = r3
            goto L_0x0071
        L_0x006d:
            java.util.List r0 = kotlin.collections.CollectionsKt.toMutableList(r0)
        L_0x0071:
            com.appsamurai.storyly.analytics.b r1 = r5.getLikeStats$storyly_release()
            if (r1 != 0) goto L_0x0078
            goto L_0x008c
        L_0x0078:
            java.util.List<com.appsamurai.storyly.analytics.c> r1 = r1.f3504c
            if (r1 != 0) goto L_0x007d
            goto L_0x008c
        L_0x007d:
            java.lang.Object r1 = kotlin.collections.CollectionsKt.getOrNull(r1, r2)
            com.appsamurai.storyly.analytics.c r1 = (com.appsamurai.storyly.analytics.c) r1
            if (r1 != 0) goto L_0x0086
            goto L_0x008c
        L_0x0086:
            if (r0 != 0) goto L_0x0089
            goto L_0x008c
        L_0x0089:
            r0.add(r1)
        L_0x008c:
            android.content.Context r1 = r5.getContext()
            android.content.Context r1 = r1.getApplicationContext()
            com.bumptech.glide.RequestManager r1 = com.bumptech.glide.Glide.with((android.content.Context) r1)
            if (r0 != 0) goto L_0x009b
            goto L_0x00a3
        L_0x009b:
            java.lang.Object r2 = kotlin.collections.CollectionsKt.getOrNull(r0, r2)
            com.appsamurai.storyly.analytics.c r2 = (com.appsamurai.storyly.analytics.c) r2
            if (r2 != 0) goto L_0x00a5
        L_0x00a3:
            r2 = r3
            goto L_0x00a7
        L_0x00a5:
            java.lang.String r2 = r2.f3507a
        L_0x00a7:
            com.bumptech.glide.RequestBuilder r1 = r1.load((java.lang.String) r2)
            com.appsamurai.storyly.util.ui.k r2 = r5.getFirstUserImageView()
            r1.into((android.widget.ImageView) r2)
            android.content.Context r1 = r5.getContext()
            android.content.Context r1 = r1.getApplicationContext()
            com.bumptech.glide.RequestManager r1 = com.bumptech.glide.Glide.with((android.content.Context) r1)
            if (r0 != 0) goto L_0x00c1
            goto L_0x00ca
        L_0x00c1:
            r2 = 1
            java.lang.Object r2 = kotlin.collections.CollectionsKt.getOrNull(r0, r2)
            com.appsamurai.storyly.analytics.c r2 = (com.appsamurai.storyly.analytics.c) r2
            if (r2 != 0) goto L_0x00cc
        L_0x00ca:
            r2 = r3
            goto L_0x00ce
        L_0x00cc:
            java.lang.String r2 = r2.f3507a
        L_0x00ce:
            com.bumptech.glide.RequestBuilder r1 = r1.load((java.lang.String) r2)
            com.appsamurai.storyly.util.ui.k r2 = r5.getSecondUserImageView()
            r1.into((android.widget.ImageView) r2)
            android.content.Context r1 = r5.getContext()
            android.content.Context r1 = r1.getApplicationContext()
            com.bumptech.glide.RequestManager r1 = com.bumptech.glide.Glide.with((android.content.Context) r1)
            if (r0 != 0) goto L_0x00e8
            goto L_0x00f3
        L_0x00e8:
            java.lang.Object r0 = kotlin.collections.CollectionsKt.getOrNull(r0, r4)
            com.appsamurai.storyly.analytics.c r0 = (com.appsamurai.storyly.analytics.c) r0
            if (r0 != 0) goto L_0x00f1
            goto L_0x00f3
        L_0x00f1:
            java.lang.String r3 = r0.f3507a
        L_0x00f3:
            com.bumptech.glide.RequestBuilder r0 = r1.load((java.lang.String) r3)
            com.appsamurai.storyly.util.ui.k r5 = r5.getThirdUserImageView()
            r0.into((android.widget.ImageView) r5)
        L_0x00fe:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylyfooter.b.f(com.appsamurai.storyly.storylypresenter.storylyfooter.b):void");
    }

    /* access modifiers changed from: private */
    public final k getFirstUserImageView() {
        return (k) this.f5399f.getValue();
    }

    private final FrameLayout getLastLikedUsersContainer() {
        return (FrameLayout) this.f5402i.getValue();
    }

    private final LinearLayout getLayerView() {
        return (LinearLayout) this.f5396c.getValue();
    }

    /* access modifiers changed from: private */
    public final TextView getLikeCountText() {
        return (TextView) this.f5398e.getValue();
    }

    /* access modifiers changed from: private */
    public final AppCompatImageView getLikeIcon() {
        return (AppCompatImageView) this.f5397d.getValue();
    }

    /* access modifiers changed from: private */
    public final k getSecondUserImageView() {
        return (k) this.f5400g.getValue();
    }

    /* access modifiers changed from: private */
    public final k getThirdUserImageView() {
        return (k) this.f5401h.getValue();
    }

    @Nullable
    public final com.appsamurai.storyly.analytics.b getLikeStats$storyly_release() {
        return (com.appsamurai.storyly.analytics.b) this.f5395b.getValue(this, f5393j[1]);
    }

    public final boolean getLikeStatus$storyly_release() {
        return ((Boolean) this.f5394a.getValue(this, f5393j[0])).booleanValue();
    }

    public final void setLikeStats$storyly_release(@Nullable com.appsamurai.storyly.analytics.b bVar) {
        this.f5395b.setValue(this, f5393j[1], bVar);
    }

    public final void setLikeStatus$storyly_release(boolean z2) {
        this.f5394a.setValue(this, f5393j[0], Boolean.valueOf(z2));
    }

    public final void a() {
        setLikeStats$storyly_release((com.appsamurai.storyly.analytics.b) null);
        getLikeCountText().setVisibility(8);
        getLastLikedUsersContainer().setVisibility(8);
    }
}
