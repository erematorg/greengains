package com.appsamurai.storyly.storylypresenter.storylycenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBindings;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryType;
import com.appsamurai.storyly.data.z;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a {

    /* renamed from: e  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5332e = {androidx.compose.ui.autofill.a.m(a.class, "storylyItem", "getStorylyItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyItem;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ViewGroup f5333a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public C0031a f5334b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public Handler f5335c = new Handler(Looper.getMainLooper());
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final ReadWriteProperty f5336d;

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylycenter.a$a  reason: collision with other inner class name */
    public static class C0031a {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ViewGroup f5337a;

        public C0031a(@NotNull ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, TtmlNode.TAG_LAYOUT);
            this.f5337a = viewGroup;
        }

        public void a() {
            throw null;
        }

        public void a(long j2) {
            throw null;
        }
    }

    public /* synthetic */ class c {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StoryType.values().length];
            iArr[StoryType.LongVideo.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final class d extends ObservableProperty<z> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f5342a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Object obj, Object obj2, a aVar) {
            super(null);
            this.f5342a = aVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, z zVar, z zVar2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            z zVar3 = zVar2;
            z zVar4 = zVar;
            this.f5342a.f5333a.setVisibility(8);
            if (zVar3 != null) {
                this.f5342a.f5333a.removeAllViews();
                a aVar = this.f5342a;
                z zVar5 = (z) aVar.f5336d.getValue(aVar, a.f5332e[0]);
                b bVar = null;
                StoryType storyType = zVar5 == null ? null : zVar5.f4307f;
                if ((storyType == null ? -1 : c.$EnumSwitchMapping$0[storyType.ordinal()]) == 1) {
                    Context context = aVar.f5333a.getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "holder.context");
                    View inflate = LayoutInflater.from(aVar.f5333a.getContext()).inflate(R.layout.st_long_video_center_view, (ViewGroup) null, false);
                    int i3 = R.id.st_seek_backward_text;
                    TextView textView = (TextView) ViewBindings.findChildViewById(inflate, i3);
                    if (textView != null) {
                        i3 = R.id.st_seek_forward_text;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(inflate, i3);
                        if (textView2 != null) {
                            com.appsamurai.storyly.databinding.d dVar = new com.appsamurai.storyly.databinding.d((RelativeLayout) inflate, textView, textView2);
                            Intrinsics.checkNotNullExpressionValue(dVar, "inflate(LayoutInflater.from(holder.context))");
                            bVar = new b(aVar, context, dVar);
                        }
                    }
                    throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i3)));
                }
                if (bVar != null) {
                    aVar.f5334b = bVar;
                    a aVar2 = this.f5342a;
                    C0031a aVar3 = aVar2.f5334b;
                    if (aVar3 != null) {
                        aVar2.f5333a.addView(aVar3.f5337a);
                    }
                }
            }
        }
    }

    public a(@NotNull ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "holder");
        this.f5333a = viewGroup;
        Delegates delegates = Delegates.INSTANCE;
        this.f5336d = new d((Object) null, (Object) null, this);
    }

    public static final void c(a aVar) {
        Intrinsics.checkNotNullParameter(aVar, "this$0");
        aVar.a();
    }

    public final void a() {
        this.f5333a.animate().cancel();
        this.f5333a.animate().alpha(0.0f).setDuration(400).withEndAction(new D0.a(this, 1));
    }

    public final void b() {
        this.f5335c.removeCallbacksAndMessages((Object) null);
        this.f5333a.animate().cancel();
        this.f5333a.setAlpha(0.0f);
        this.f5333a.setVisibility(0);
        this.f5333a.animate().alpha(1.0f).setDuration(400).withEndAction(new D0.a(this, 0));
    }

    public final class b extends C0031a {
        @NotNull

        /* renamed from: b  reason: collision with root package name */
        public final com.appsamurai.storyly.databinding.d f5338b;

        /* renamed from: c  reason: collision with root package name */
        public int f5339c;
        @Nullable

        /* renamed from: d  reason: collision with root package name */
        public Boolean f5340d;

        /* renamed from: e  reason: collision with root package name */
        public final /* synthetic */ a f5341e;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public b(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.storylypresenter.storylycenter.a r4, @org.jetbrains.annotations.NotNull android.content.Context r5, com.appsamurai.storyly.databinding.d r6) {
            /*
                r3 = this;
                java.lang.String r0 = "this$0"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                java.lang.String r0 = "binding"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                r3.f5341e = r4
                android.widget.RelativeLayout r4 = r6.a()
                java.lang.String r0 = "binding.root"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
                r3.<init>(r4)
                r3.f5338b = r6
                int r3 = com.appsamurai.storyly.R.drawable.st_video_fast_forward_right
                android.graphics.drawable.Drawable r3 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r5, r3)
                int r4 = com.appsamurai.storyly.R.drawable.st_video_fast_forward_left
                android.graphics.drawable.Drawable r4 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r5, r4)
                android.widget.TextView r5 = r6.f4342c
                android.widget.RelativeLayout r1 = r6.a()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
                boolean r1 = com.appsamurai.storyly.util.l.a(r1)
                if (r1 == 0) goto L_0x003d
                r1 = r3
                goto L_0x003e
            L_0x003d:
                r1 = r4
            L_0x003e:
                r2 = 0
                r5.setCompoundDrawablesWithIntrinsicBounds(r2, r1, r2, r2)
                android.widget.TextView r5 = r6.f4341b
                android.widget.RelativeLayout r6 = r6.a()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
                boolean r6 = com.appsamurai.storyly.util.l.a(r6)
                if (r6 == 0) goto L_0x0052
                r3 = r4
            L_0x0052:
                r5.setCompoundDrawablesWithIntrinsicBounds(r2, r3, r2, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylycenter.a.b.<init>(com.appsamurai.storyly.storylypresenter.storylycenter.a, android.content.Context, com.appsamurai.storyly.databinding.d):void");
        }

        public void a() {
            this.f5339c = 0;
            this.f5340d = null;
        }

        public void a(long j2) {
            int i3 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
            boolean z2 = i3 <= 0;
            Boolean bool = this.f5340d;
            if (bool == null) {
                a aVar = this.f5341e;
                this.f5339c = 10;
                z zVar = (z) aVar.f5336d.getValue(aVar, a.f5332e[0]);
                this.f5340d = Boolean.valueOf(z2);
            } else {
                if (bool.booleanValue() == z2) {
                    this.f5339c += 10;
                } else {
                    this.f5339c = 10;
                }
                this.f5340d = Boolean.valueOf(z2);
            }
            if (i3 <= 0) {
                this.f5338b.f4341b.setVisibility(0);
                this.f5338b.f4342c.setVisibility(8);
            } else {
                this.f5338b.f4341b.setVisibility(8);
                this.f5338b.f4342c.setVisibility(0);
            }
            if (i3 <= 0) {
                this.f5338b.f4341b.setText(this.f5337a.getContext().getResources().getString(R.string.st_video_backward_forward_info, new Object[]{Integer.valueOf(this.f5339c)}));
            } else {
                this.f5338b.f4342c.setText(this.f5337a.getContext().getResources().getString(R.string.st_video_backward_forward_info, new Object[]{Integer.valueOf(this.f5339c)}));
            }
        }
    }

    public static final void a(a aVar) {
        Intrinsics.checkNotNullParameter(aVar, "this$0");
        aVar.f5333a.setVisibility(8);
        C0031a aVar2 = aVar.f5334b;
        if (aVar2 != null) {
            aVar2.a();
        }
    }

    public static final void b(a aVar) {
        Intrinsics.checkNotNullParameter(aVar, "this$0");
        aVar.f5335c.removeCallbacksAndMessages((Object) null);
        aVar.f5335c.postDelayed(new D0.a(aVar, 2), 1200);
    }
}
