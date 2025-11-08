package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.ViewCompat;
import androidx.emoji.widget.EmojiTextView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

public final class b extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    public boolean f5598a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Lazy f5599b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Lazy f5600c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Lazy f5601d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final Lazy f5602e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Lazy f5603f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f5604g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f5605h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f5606i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f5607j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Lazy f5608k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final Lazy f5609l;

    public static final class a extends Lambda implements Function0<EmojiTextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5610a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f5610a = context;
        }

        public Object invoke() {
            EmojiTextView emojiTextView = new EmojiTextView(this.f5610a);
            emojiTextView.setAlpha(0.0f);
            emojiTextView.setTextColor(-1);
            emojiTextView.setGravity(16);
            return emojiTextView;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylylayer.b$b  reason: collision with other inner class name */
    public static final class C0049b extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5611a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0049b(Context context) {
            super(0);
            this.f5611a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5611a);
            appCompatImageView.setClickable(false);
            return appCompatImageView;
        }
    }

    public static final class c extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5612a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Context context) {
            super(0);
            this.f5612a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5612a);
            appCompatImageView.setClickable(false);
            appCompatImageView.setAlpha(0.0f);
            return appCompatImageView;
        }
    }

    public static final class d extends Lambda implements Function0<AppCompatImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5613a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5613a = context;
        }

        public Object invoke() {
            AppCompatImageView appCompatImageView = new AppCompatImageView(this.f5613a);
            appCompatImageView.setClickable(false);
            return appCompatImageView;
        }
    }

    public static final class e extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5614a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Context context) {
            super(0);
            this.f5614a = context;
        }

        public Object invoke() {
            return new ImageView(this.f5614a);
        }
    }

    public static final class f extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5615a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f5615a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5615a);
            imageView.setAlpha(0.0f);
            return imageView;
        }
    }

    public static final class g extends Lambda implements Function0<RelativeLayout> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5616a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f5616a = context;
        }

        public Object invoke() {
            RelativeLayout relativeLayout = new RelativeLayout(this.f5616a);
            relativeLayout.setId(View.generateViewId());
            return relativeLayout;
        }
    }

    public static final class h extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5617a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Context context) {
            super(0);
            this.f5617a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5617a);
            imageView.setAlpha(0.0f);
            return imageView;
        }
    }

    public static final class i extends Lambda implements Function0<TextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5618a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(Context context) {
            super(0);
            this.f5618a = context;
        }

        public Object invoke() {
            TextView textView = new TextView(this.f5618a);
            textView.setMaxLines(2);
            textView.setTextAlignment(4);
            textView.setTextColor(-1);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setGravity(80);
            textView.setImportantForAccessibility(2);
            return textView;
        }
    }

    public static final class j extends Lambda implements Function0<TextView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5619a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public j(Context context) {
            super(0);
            this.f5619a = context;
        }

        public Object invoke() {
            TextView textView = new TextView(this.f5619a);
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            textView.setImportantForAccessibility(2);
            return textView;
        }
    }

    public static final class k extends Lambda implements Function0<View> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5620a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public k(Context context) {
            super(0);
            this.f5620a = context;
        }

        public Object invoke() {
            return new View(this.f5620a);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5599b = LazyKt.lazy(new d(context));
        this.f5600c = LazyKt.lazy(new e(context));
        this.f5601d = LazyKt.lazy(new f(context));
        this.f5602e = LazyKt.lazy(new h(context));
        this.f5603f = LazyKt.lazy(new g(context));
        this.f5604g = LazyKt.lazy(new C0049b(context));
        this.f5605h = LazyKt.lazy(new c(context));
        this.f5606i = LazyKt.lazy(new i(context));
        this.f5607j = LazyKt.lazy(new a(context));
        this.f5608k = LazyKt.lazy(new k(context));
        this.f5609l = LazyKt.lazy(new j(context));
    }

    @NotNull
    public final EmojiTextView getEmojiView() {
        return (EmojiTextView) this.f5607j.getValue();
    }

    @NotNull
    public final AppCompatImageView getOptionChoiceImage() {
        return (AppCompatImageView) this.f5604g.getValue();
    }

    @NotNull
    public final AppCompatImageView getOptionChoiceResultImage() {
        return (AppCompatImageView) this.f5605h.getValue();
    }

    @NotNull
    public final AppCompatImageView getOptionImage() {
        return (AppCompatImageView) this.f5599b.getValue();
    }

    @NotNull
    public final ImageView getOptionImageBGDrawable() {
        return (ImageView) this.f5600c.getValue();
    }

    @NotNull
    public final ImageView getOptionImageBorderDrawable() {
        return (ImageView) this.f5601d.getValue();
    }

    @NotNull
    public final RelativeLayout getOptionImageContainer() {
        return (RelativeLayout) this.f5603f.getValue();
    }

    @NotNull
    public final ImageView getOptionImageWrongBgDrawable() {
        return (ImageView) this.f5602e.getValue();
    }

    @NotNull
    public final TextView getOptionText() {
        return (TextView) this.f5606i.getValue();
    }

    @NotNull
    public final TextView getPercentageText() {
        return (TextView) this.f5609l.getValue();
    }

    @NotNull
    public final View getPollResultAnimatedBar() {
        return (View) this.f5608k.getValue();
    }

    public final void setRightAnswer(boolean z2) {
        this.f5598a = z2;
    }
}
