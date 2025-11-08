package com.appsamurai.storyly.storylypresenter.share;

import C0.c;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.ShareType;
import com.appsamurai.storyly.StoryType;
import com.appsamurai.storyly.config.StorylyShareConfig;
import com.appsamurai.storyly.databinding.h;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class d extends BottomSheetDialog {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final ShareType f5316a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final StoryType f5317b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final StorylyShareConfig f5318c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public h f5319d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public a f5320e = new a();
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public Function1<? super c, Unit> f5321f;

    public static final class a extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5322a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(d dVar) {
            super(0);
            this.f5322a = dVar;
        }

        public final void a() {
            new Handler(Looper.getMainLooper()).postDelayed(new C0.d(this.f5322a, 0), 600);
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            a();
            return Unit.INSTANCE;
        }

        public static final void a(d dVar) {
            Intrinsics.checkNotNullParameter(dVar, "this$0");
            dVar.dismiss();
        }
    }

    public static final class b extends Lambda implements Function1<b, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f5323a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(d dVar) {
            super(1);
            this.f5323a = dVar;
        }

        public Object invoke(Object obj) {
            b bVar = (b) obj;
            Intrinsics.checkNotNullParameter(bVar, "it");
            Function1<? super c, Unit> function1 = this.f5323a.f5321f;
            if (function1 != null) {
                function1.invoke(bVar.f5306c);
            }
            this.f5323a.dismiss();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(@Nullable ShareType shareType, @Nullable StoryType storyType, @NotNull Context context, @NotNull StorylyShareConfig storylyShareConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "sheetContext");
        Intrinsics.checkNotNullParameter(storylyShareConfig, "shareConfig");
        this.f5316a = shareType;
        this.f5317b = storyType;
        this.f5318c = storylyShareConfig;
        h a2 = h.a(LayoutInflater.from(getContext()));
        Intrinsics.checkNotNullExpressionValue(a2, "inflate(\n        LayoutI…later.from(context)\n    )");
        this.f5319d = a2;
        setContentView((View) this.f5319d.a());
    }

    public static final void a(d dVar, View view) {
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        Function1<? super c, Unit> function1 = dVar.f5321f;
        if (function1 != null) {
            function1.invoke(c.CopyLink);
        }
        a aVar = new a(dVar);
        TextView textView = dVar.f5319d.f4364e;
        Intrinsics.checkNotNullExpressionValue(textView, "this");
        a(dVar, textView, 300, 0, new f(textView, dVar, 300, aVar), 4);
        ImageView imageView = dVar.f5319d.f4362c;
        Intrinsics.checkNotNullExpressionValue(imageView, "this");
        a(dVar, imageView, 300, 0, new g(imageView, dVar, 300), 4);
    }

    public static final void b(d dVar, View view) {
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        Function1<? super c, Unit> function1 = dVar.f5321f;
        if (function1 != null) {
            function1.invoke(c.ShareLinkVia);
        }
        dVar.dismiss();
    }

    public static final void c(d dVar, View view) {
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        Function1<? super c, Unit> function1 = dVar.f5321f;
        if (function1 != null) {
            function1.invoke(c.ShareScreenshotVia);
        }
        dVar.dismiss();
    }

    public static final void d(d dVar, View view) {
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        dVar.dismiss();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        StoryType storyType = this.f5317b;
        StoryType storyType2 = StoryType.Video;
        if (storyType == storyType2) {
            this.f5319d.f4367h.setVisibility(8);
            LinearLayout linearLayout = this.f5319d.f4366g;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.stShareLinkViaLayout");
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            if (layoutParams != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.setMargins(0, 24, 0, 0);
                linearLayout.setLayoutParams(marginLayoutParams);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
        }
        if (this.f5316a == ShareType.Screenshot) {
            this.f5319d.f4363d.setVisibility(8);
            this.f5319d.f4366g.setVisibility(8);
        }
        this.f5319d.f4363d.setOnClickListener(new c(this, 0));
        this.f5319d.f4366g.setOnClickListener(new c(this, 1));
        this.f5319d.f4367h.setOnClickListener(new c(this, 2));
        this.f5319d.f4361b.setOnClickListener(new c(this, 3));
        RecyclerView recyclerView = this.f5319d.f4365f;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 0, false));
        recyclerView.setAdapter(this.f5320e);
        this.f5320e.f5302b = new b(this);
        a aVar = this.f5320e;
        ArrayList arrayList = new ArrayList();
        if (a(com.appsamurai.storyly.util.share.b.a("com.instagram.android", (String) null, 2)) && this.f5316a == ShareType.Link) {
            arrayList.add(new b(R.drawable.st_insta_direct, "Instagram Direct", c.InstagramDirect));
        }
        if (!(!a(com.appsamurai.storyly.util.share.b.a("com.instagram.android", "image/jpeg")) || this.f5317b == storyType2 || this.f5318c.getFacebookAppID$storyly_release() == null)) {
            arrayList.add(new b(R.drawable.st_insta_stories, "Instagram Stories", c.InstagramStories));
        }
        if (a(com.appsamurai.storyly.util.share.b.a("com.whatsapp", (String) null, 2)) && this.f5316a == ShareType.Link) {
            arrayList.add(new b(R.drawable.st_whatsapp, "WhatsApp", c.WhatsApp));
        }
        if (a(com.appsamurai.storyly.util.share.b.a("com.twitter.android", (String) null, 2)) && this.f5316a == ShareType.Link) {
            arrayList.add(new b(R.drawable.st_twitter, "Twitter", c.Twitter));
        }
        if (a(com.appsamurai.storyly.util.share.b.a("com.facebook.katana", (String) null, 2)) && this.f5316a == ShareType.Link) {
            arrayList.add(new b(R.drawable.st_facebook, "Facebook", c.Facebook));
        }
        aVar.getClass();
        Intrinsics.checkNotNullParameter(arrayList, FirebaseAnalytics.Param.ITEMS);
        aVar.f5301a = arrayList;
        aVar.notifyDataSetChanged();
    }

    public final boolean a(Intent intent) {
        ActivityInfo resolveActivityInfo = intent.resolveActivityInfo(getContext().getPackageManager(), 65536);
        if (resolveActivityInfo == null) {
            return false;
        }
        return resolveActivityInfo.exported;
    }

    public static /* synthetic */ void a(d dVar, View view, long j2, int i3, Function0 function0, int i4) {
        if ((i4 & 2) != 0) {
            j2 = 300;
        }
        long j3 = j2;
        if ((i4 & 4) != 0) {
            i3 = 4;
        }
        int i5 = i3;
        if ((i4 & 8) != 0) {
            function0 = null;
        }
        dVar.a(view, j3, i5, function0);
    }

    public final void a(View view, long j2, int i3, Function0<Unit> function0) {
        view.animate().alpha(0.0f).setDuration(j2).withEndAction(new C0.a(i3, 0, view, function0));
    }

    public static final void a(View view, int i3, Function0 function0) {
        Intrinsics.checkNotNullParameter(view, "$view");
        view.setVisibility(i3);
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void a(View view, long j2, Function0<Unit> function0) {
        view.setAlpha(0.0f);
        view.setVisibility(0);
        view.animate().alpha(1.0f).setDuration(j2).withEndAction(new C0.b(function0, 0));
    }

    public static final void a(Function0 function0) {
        if (function0 != null) {
            function0.invoke();
        }
    }
}
