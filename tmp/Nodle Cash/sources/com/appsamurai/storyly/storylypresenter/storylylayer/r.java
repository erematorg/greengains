package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.view.OneShotPreDrawListener;
import com.appsamurai.storyly.data.q0;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.firebase.messaging.Constants;
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
public final class r extends o1 {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final z f5958g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final v f5959h;

    /* renamed from: i  reason: collision with root package name */
    public Function0<Unit> f5960i;

    /* renamed from: j  reason: collision with root package name */
    public Function0<Unit> f5961j;

    /* renamed from: k  reason: collision with root package name */
    public Function1<? super Integer, Unit> f5962k;

    /* renamed from: l  reason: collision with root package name */
    public Function1<? super Long, Unit> f5963l;

    /* renamed from: m  reason: collision with root package name */
    public q0 f5964m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f5965n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public final Lazy f5966o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    public ExoPlayer f5967p;
    @Nullable

    /* renamed from: q  reason: collision with root package name */
    public VideoSize f5968q;

    /* renamed from: r  reason: collision with root package name */
    public int f5969r = 1;

    /* renamed from: s  reason: collision with root package name */
    public long f5970s;
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public final Lazy f5971t = LazyKt.lazy(g.f5981a);
    @NotNull

    /* renamed from: u  reason: collision with root package name */
    public final Lazy f5972u = LazyKt.lazy(new h(this));

    public final class a extends TextureView {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ r f5973a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(@NotNull r rVar, Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(rVar, "this$0");
            Intrinsics.checkNotNullParameter(context, "context");
            this.f5973a = rVar;
        }

        public void onMeasure(int i3, int i4) {
            VideoSize videoSize = this.f5973a.f5968q;
            if (videoSize == null) {
                super.onMeasure(i3, i4);
                return;
            }
            int min = Math.min(View.MeasureSpec.getSize(i3), this.f5973a.getMeasuredWidth());
            int min2 = Math.min(View.MeasureSpec.getSize(i4), this.f5973a.getMeasuredHeight());
            double d2 = ((double) videoSize.height) / ((double) videoSize.width);
            int i5 = (int) (((double) min) * d2);
            if (min2 > i5) {
                min2 = i5;
            } else {
                min = (int) (((double) min2) / d2);
            }
            setMeasuredDimension(min, min2);
        }
    }

    public /* synthetic */ class b {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[q0.b.values().length];
            iArr[1] = 1;
            iArr[0] = 2;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[q0.c.values().length];
            iArr2[1] = 1;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final class c implements Player.Listener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ r f5974a;

        public static final class a extends AnimatorListenerAdapter {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ r f5975a;

            public a(r rVar) {
                this.f5975a = rVar;
            }

            public void onAnimationEnd(@NotNull Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animation");
                this.f5975a.getThumbnailView().setVisibility(8);
                this.f5975a.getThumbnailView().setAlpha(1.0f);
            }
        }

        public c(r rVar) {
            this.f5974a = rVar;
        }

        public void onPlaybackStateChanged(int i3) {
            if (i3 == 2) {
                r rVar = this.f5974a;
                if (rVar.f5969r == 3) {
                    rVar.getOnBufferStart$storyly_release().invoke();
                }
            } else if (i3 == 3) {
                r rVar2 = this.f5974a;
                int i4 = rVar2.f5969r;
                if (i4 == 1) {
                    Function1<Integer, Unit> onVideoReady$storyly_release = rVar2.getOnVideoReady$storyly_release();
                    ExoPlayer exoPlayer = this.f5974a.f5967p;
                    onVideoReady$storyly_release.invoke(exoPlayer == null ? null : Integer.valueOf((int) exoPlayer.getContentDuration()));
                    this.f5974a.getTimerHandler().postDelayed(this.f5974a.getTimerRunnable(), 200);
                } else if (i4 == 2) {
                    rVar2.getOnBufferEnd$storyly_release().invoke();
                }
            } else if (i3 == 4) {
                this.f5974a.getTimerHandler().removeCallbacks(this.f5974a.getTimerRunnable());
            }
            this.f5974a.f5969r = i3;
        }

        public void onPlayerError(@NotNull PlaybackException playbackException) {
            Intrinsics.checkNotNullParameter(playbackException, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
            this.f5974a.getOnLayerLoadFail$storyly_release().invoke();
        }

        public void onRenderedFirstFrame() {
            this.f5974a.getThumbnailView().animate().alpha(0.0f).setDuration(200).setListener(new a(this.f5974a));
        }

        public void onVideoSizeChanged(@NotNull VideoSize videoSize) {
            Intrinsics.checkNotNullParameter(videoSize, "videoSize");
            r rVar = this.f5974a;
            if (rVar.f5968q == null) {
                rVar.f5968q = videoSize;
                rVar.getTextureView().requestLayout();
            }
        }
    }

    public static final class d implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ r f5976a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ Context f5977b;

        public d(View view, r rVar, Context context) {
            this.f5976a = rVar;
            this.f5977b = context;
        }

        public final void run() {
            String str;
            q0 q0Var = this.f5976a.f5964m;
            q0 q0Var2 = null;
            if (q0Var == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                q0Var = null;
            }
            int ordinal = q0Var.f4152g.ordinal();
            if (ordinal == 0) {
                q0 q0Var3 = this.f5976a.f5964m;
                if (q0Var3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                } else {
                    q0Var2 = q0Var3;
                }
                str = q0Var2.f4148c;
            } else if (ordinal == 1) {
                String str2 = this.f5976a.getStorylyGroupItem().f4223c;
                q0 q0Var4 = this.f5976a.f5964m;
                if (q0Var4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
                } else {
                    q0Var2 = q0Var4;
                }
                str = Intrinsics.stringPlus(str2, q0Var2.f4149d);
            } else {
                return;
            }
            Glide.with(this.f5977b.getApplicationContext()).load(str).transition(DrawableTransitionOptions.withCrossFade(100)).into(this.f5976a.getThumbnailView());
        }
    }

    public static final class e extends Lambda implements Function0<a> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ r f5978a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ Context f5979b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(r rVar, Context context) {
            super(0);
            this.f5978a = rVar;
            this.f5979b = context;
        }

        public Object invoke() {
            a aVar = new a(this.f5978a, this.f5979b);
            aVar.setEnabled(false);
            return aVar;
        }
    }

    public static final class f extends Lambda implements Function0<ImageView> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5980a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Context context) {
            super(0);
            this.f5980a = context;
        }

        public Object invoke() {
            ImageView imageView = new ImageView(this.f5980a);
            imageView.setEnabled(false);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return imageView;
        }
    }

    public static final class g extends Lambda implements Function0<Handler> {

        /* renamed from: a  reason: collision with root package name */
        public static final g f5981a = new g();

        public g() {
            super(0);
        }

        public Object invoke() {
            return new Handler(Looper.getMainLooper());
        }
    }

    public static final class h extends Lambda implements Function0<t> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ r f5982a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(r rVar) {
            super(0);
            this.f5982a = rVar;
        }

        public Object invoke() {
            return new t(this.f5982a);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public r(@NotNull Context context, @NotNull z zVar, @NotNull v vVar) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(zVar, "storylyItem");
        Intrinsics.checkNotNullParameter(vVar, "storylyGroupItem");
        this.f5958g = zVar;
        this.f5959h = vVar;
        this.f5965n = LazyKt.lazy(new f(context));
        this.f5966o = LazyKt.lazy(new e(this, context));
        a textureView = getTextureView();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        Unit unit = Unit.INSTANCE;
        addView(textureView, layoutParams);
        ImageView thumbnailView = getThumbnailView();
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.gravity = 17;
        addView(thumbnailView, layoutParams2);
        Intrinsics.checkExpressionValueIsNotNull(OneShotPreDrawListener.add(this, new d(this, this, context)), "OneShotPreDrawListener.add(this) { action(this) }");
    }

    /* access modifiers changed from: private */
    public final a getTextureView() {
        return (a) this.f5966o.getValue();
    }

    /* access modifiers changed from: private */
    public final ImageView getThumbnailView() {
        return (ImageView) this.f5965n.getValue();
    }

    /* access modifiers changed from: private */
    public final Handler getTimerHandler() {
        return (Handler) this.f5971t.getValue();
    }

    /* access modifiers changed from: private */
    public final Runnable getTimerRunnable() {
        return (Runnable) this.f5972u.getValue();
    }

    public void e() {
        ExoPlayer exoPlayer = this.f5967p;
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(true);
        }
    }

    @Nullable
    public Bitmap getCurrentBitmap$storyly_release() {
        return getTextureView().getBitmap();
    }

    @NotNull
    public final Function0<Unit> getOnBufferEnd$storyly_release() {
        Function0<Unit> function0 = this.f5961j;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onBufferEnd");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnBufferStart$storyly_release() {
        Function0<Unit> function0 = this.f5960i;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onBufferStart");
        return null;
    }

    @NotNull
    public final Function1<Long, Unit> getOnSessionTimeUpdated$storyly_release() {
        Function1<? super Long, Unit> function1 = this.f5963l;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onSessionTimeUpdated");
        return null;
    }

    @NotNull
    public final Function1<Integer, Unit> getOnVideoReady$storyly_release() {
        Function1<? super Integer, Unit> function1 = this.f5962k;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoReady");
        return null;
    }

    @NotNull
    public final v getStorylyGroupItem() {
        return this.f5959h;
    }

    @NotNull
    public final z getStorylyItem() {
        return this.f5958g;
    }

    public final void setOnBufferEnd$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5961j = function0;
    }

    public final void setOnBufferStart$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5960i = function0;
    }

    public final void setOnSessionTimeUpdated$storyly_release(@NotNull Function1<? super Long, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5963l = function1;
    }

    public final void setOnVideoReady$storyly_release(@NotNull Function1<? super Integer, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5962k = function1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.data.b0 r6) {
        /*
            r5 = this;
            java.lang.String r0 = "storylyLayerItem"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            com.appsamurai.storyly.data.a0 r0 = r6.f3615j
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.q0
            r2 = 0
            if (r1 == 0) goto L_0x0010
            com.appsamurai.storyly.data.q0 r0 = (com.appsamurai.storyly.data.q0) r0
            goto L_0x0011
        L_0x0010:
            r0 = r2
        L_0x0011:
            if (r0 != 0) goto L_0x0014
            return
        L_0x0014:
            r5.f5964m = r0
            r5.setStorylyLayerItem$storyly_release(r6)
            float r6 = r6.f3613h
            r5.setRotation(r6)
            com.appsamurai.storyly.data.v r6 = r5.f5959h
            com.appsamurai.storyly.data.q0 r0 = r5.f5964m
            java.lang.String r1 = "storylyLayer"
            if (r0 != 0) goto L_0x002b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x002b:
            com.appsamurai.storyly.data.q0$b r0 = r0.f4152g
            int r0 = r0.ordinal()
            r3 = 1
            if (r0 == 0) goto L_0x004f
            if (r0 == r3) goto L_0x003e
            kotlin.jvm.functions.Function0 r6 = r5.getOnLayerLoad$storyly_release()
            r6.invoke()
            goto L_0x0075
        L_0x003e:
            java.lang.String r6 = r6.f4223c
            com.appsamurai.storyly.data.q0 r0 = r5.f5964m
            if (r0 != 0) goto L_0x0048
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x0048:
            java.lang.String r0 = r0.f4149d
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r6, r0)
            goto L_0x0059
        L_0x004f:
            com.appsamurai.storyly.data.q0 r6 = r5.f5964m
            if (r6 != 0) goto L_0x0057
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r6 = r2
        L_0x0057:
            java.lang.String r6 = r6.f4148c
        L_0x0059:
            android.content.Context r0 = r5.getContext()
            android.content.Context r0 = r0.getApplicationContext()
            com.bumptech.glide.RequestManager r0 = com.bumptech.glide.Glide.with((android.content.Context) r0)
            com.bumptech.glide.RequestBuilder r6 = r0.load((java.lang.String) r6)
            com.appsamurai.storyly.storylypresenter.storylylayer.s r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.s
            r0.<init>(r5)
            com.bumptech.glide.RequestBuilder r6 = r6.listener(r0)
            r6.preload()
        L_0x0075:
            com.appsamurai.storyly.exoplayer2.core.ExoPlayer$Builder r6 = new com.appsamurai.storyly.exoplayer2.core.ExoPlayer$Builder
            android.content.Context r0 = r5.getContext()
            r6.<init>(r0)
            com.appsamurai.storyly.exoplayer2.core.ExoPlayer r6 = r6.build()
            r5.f5967p = r6
            com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes$Builder r6 = new com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes$Builder
            r6.<init>()
            com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes$Builder r6 = r6.setUsage(r3)
            r0 = 3
            com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes$Builder r6 = r6.setContentType(r0)
            com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes r6 = r6.build()
            java.lang.String r0 = "Builder()\n            .s…VIE)\n            .build()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            com.appsamurai.storyly.exoplayer2.core.ExoPlayer r0 = r5.f5967p
            if (r0 != 0) goto L_0x00a0
            goto L_0x00a3
        L_0x00a0:
            r0.setAudioAttributes(r6, r3)
        L_0x00a3:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "Storyly/2.4.0 (Linux;Android "
            r6.<init>(r0)
            java.lang.String r0 = android.os.Build.VERSION.RELEASE
            r6.append(r0)
            java.lang.String r0 = ") Player/2.18.0"
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.content.Context r0 = r5.getContext()
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DefaultHttpDataSource$Factory r4 = new com.appsamurai.storyly.exoplayer2.datasource.upstream.DefaultHttpDataSource$Factory
            r4.<init>()
            r4.setUserAgent(r6)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DefaultDataSource$Factory r6 = new com.appsamurai.storyly.exoplayer2.datasource.upstream.DefaultDataSource$Factory
            r6.<init>(r0, r4)
            com.appsamurai.storyly.data.q0 r0 = r5.f5964m
            if (r0 != 0) goto L_0x00d3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x00d3:
            com.appsamurai.storyly.data.q0$c r0 = r0.f4151f
            int[] r4 = com.appsamurai.storyly.storylypresenter.storylylayer.r.b.$EnumSwitchMapping$1
            int r0 = r0.ordinal()
            r0 = r4[r0]
            if (r0 != r3) goto L_0x00f3
            com.appsamurai.storyly.data.v r0 = r5.f5959h
            java.lang.String r0 = r0.f4223c
            com.appsamurai.storyly.data.q0 r4 = r5.f5964m
            if (r4 != 0) goto L_0x00eb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x00ec
        L_0x00eb:
            r2 = r4
        L_0x00ec:
            java.lang.String r1 = r2.f4147b
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r1)
            goto L_0x00fe
        L_0x00f3:
            com.appsamurai.storyly.data.q0 r0 = r5.f5964m
            if (r0 != 0) goto L_0x00fb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x00fc
        L_0x00fb:
            r2 = r0
        L_0x00fc:
            java.lang.String r0 = r2.f4146a
        L_0x00fe:
            android.net.Uri r0 = android.net.Uri.parse(r0)
            com.appsamurai.storyly.exoplayer2.common.MediaItem r1 = com.appsamurai.storyly.exoplayer2.common.MediaItem.fromUri((android.net.Uri) r0)
            java.lang.String r2 = "fromUri(videoUrl)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r0 = r0.getPath()
            if (r0 != 0) goto L_0x0112
            goto L_0x0124
        L_0x0112:
            java.lang.String r2 = "m3u8"
            boolean r0 = kotlin.text.StringsKt__StringsJVMKt.endsWith$default(r0, r2, false, 2, (java.lang.Object) null)
            if (r0 != r3) goto L_0x0124
            com.appsamurai.storyly.exoplayer2.hls.HlsMediaSource$Factory r0 = new com.appsamurai.storyly.exoplayer2.hls.HlsMediaSource$Factory
            r0.<init>((com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource.Factory) r6)
            com.appsamurai.storyly.exoplayer2.hls.HlsMediaSource r6 = r0.createMediaSource((com.appsamurai.storyly.exoplayer2.common.MediaItem) r1)
            goto L_0x012d
        L_0x0124:
            com.appsamurai.storyly.exoplayer2.core.source.ProgressiveMediaSource$Factory r0 = new com.appsamurai.storyly.exoplayer2.core.source.ProgressiveMediaSource$Factory
            r0.<init>(r6)
            com.appsamurai.storyly.exoplayer2.core.source.ProgressiveMediaSource r6 = r0.createMediaSource((com.appsamurai.storyly.exoplayer2.common.MediaItem) r1)
        L_0x012d:
            com.appsamurai.storyly.exoplayer2.core.ExoPlayer r0 = r5.f5967p
            if (r0 != 0) goto L_0x0132
            goto L_0x0137
        L_0x0132:
            r1 = 1065353216(0x3f800000, float:1.0)
            r0.setVolume(r1)
        L_0x0137:
            com.appsamurai.storyly.exoplayer2.core.ExoPlayer r0 = r5.f5967p
            if (r0 != 0) goto L_0x013c
            goto L_0x013f
        L_0x013c:
            r0.setMediaSource(r6)
        L_0x013f:
            com.appsamurai.storyly.exoplayer2.core.ExoPlayer r6 = r5.f5967p
            if (r6 != 0) goto L_0x0144
            goto L_0x0147
        L_0x0144:
            r6.prepare()
        L_0x0147:
            com.appsamurai.storyly.exoplayer2.core.ExoPlayer r6 = r5.f5967p
            if (r6 != 0) goto L_0x014c
            goto L_0x0154
        L_0x014c:
            com.appsamurai.storyly.storylypresenter.storylylayer.r$c r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.r$c
            r0.<init>(r5)
            r6.addListener(r0)
        L_0x0154:
            com.appsamurai.storyly.exoplayer2.core.ExoPlayer r6 = r5.f5967p
            if (r6 != 0) goto L_0x0159
            goto L_0x0160
        L_0x0159:
            com.appsamurai.storyly.storylypresenter.storylylayer.r$a r5 = r5.getTextureView()
            r6.setVideoTextureView(r5)
        L_0x0160:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.r.a(com.appsamurai.storyly.data.b0):void");
    }

    public void b() {
        ExoPlayer exoPlayer = this.f5967p;
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
        }
    }

    public void c() {
        ExoPlayer exoPlayer;
        getTimerHandler().removeCallbacks(getTimerRunnable());
        ExoPlayer exoPlayer2 = this.f5967p;
        if (!(exoPlayer2 == null || !exoPlayer2.isPlaying() || (exoPlayer = this.f5967p) == null)) {
            exoPlayer.stop();
        }
        this.f5968q = null;
        ExoPlayer exoPlayer3 = this.f5967p;
        if (exoPlayer3 != null) {
            exoPlayer3.release();
        }
        this.f5967p = null;
        Glide.with(getContext().getApplicationContext()).clear((View) getThumbnailView());
        getThumbnailView().setVisibility(4);
    }

    public void b(long j2) {
        ExoPlayer exoPlayer = this.f5967p;
        if (exoPlayer != null) {
            exoPlayer.seekTo(j2);
        }
    }

    public void a(@NotNull d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "safeFrame");
        float f2 = (float) 100;
        setLayoutParams(o1.a(this, new FrameLayout.LayoutParams(A.a.a(getStorylyLayerItem$storyly_release().f3609d, f2, dVar.b()), A.a.a(getStorylyLayerItem$storyly_release().f3610e, f2, dVar.a())), getStorylyLayerItem$storyly_release().b().x, getStorylyLayerItem$storyly_release().b().y, 0.0f, 0.0f, 24, (Object) null));
    }

    public void a(long j2) {
        ExoPlayer exoPlayer = this.f5967p;
        if (exoPlayer != null) {
            exoPlayer.seekTo(Math.max(exoPlayer.getCurrentPosition() + j2, 0));
        }
    }
}
