package com.appsamurai.storyly;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.provider.FontRequest;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.text.FontRequestEmojiCompatConfig;
import androidx.fragment.app.Fragment;
import com.appsamurai.storyly.PlayMode;
import com.appsamurai.storyly.ad.StorylyAdView;
import com.appsamurai.storyly.ad.StorylyAdViewProvider;
import com.appsamurai.storyly.analytics.StorylyEvent;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.appsamurai.storyly.data.s;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.storylylist.MomentsItem;
import com.appsamurai.storyly.storylylist.StorylyListRecyclerView;
import com.appsamurai.storyly.storylypresenter.StorylyDialogFragment;
import com.appsamurai.storyly.util.a;
import com.reown.android.push.notifications.PushMessagingService;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002,4B(\b\u0007\u0012\u0006\u0010{\u001a\u00020z\u0012\n\b\u0002\u0010}\u001a\u0004\u0018\u00010|\u0012\b\b\u0002\u0010~\u001a\u00020\u0006¢\u0006\u0005\b\u0010\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0019\u0010\b\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\n\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\n\u0010\tJ\u0019\u0010\u000b\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000b\u0010\tJ\u0006\u0010\f\u001a\u00020\u0004J&\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007J\u000e\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0015J\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019J\u0006\u0010\u001c\u001a\u00020\u0004J\u0006\u0010\u001d\u001a\u00020\u0004J\u0014\u0010!\u001a\u00020\u00042\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eJ\u0014\u0010$\u001a\u00020\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u001eJ\u000e\u0010'\u001a\u00020\u00042\u0006\u0010&\u001a\u00020%J\u000e\u0010)\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\rR+\u00102\u001a\u00020*2\u0006\u0010+\u001a\u00020*8F@FX\u0002¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R$\u0010:\u001a\u0004\u0018\u0001038\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010B\u001a\u0004\u0018\u00010;8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR$\u0010J\u001a\u0004\u0018\u00010C8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR$\u0010R\u001a\u0004\u0018\u00010K8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001d\u0010X\u001a\u00020S8B@\u0002X\u0002¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u0010WR\u001d\u0010]\u001a\u00020Y8B@\u0002X\u0002¢\u0006\f\n\u0004\bZ\u0010U\u001a\u0004\b[\u0010\\R\u001d\u0010b\u001a\u00020^8B@\u0002X\u0002¢\u0006\f\n\u0004\b_\u0010U\u001a\u0004\b`\u0010aR\u001d\u0010g\u001a\u00020c8B@\u0002X\u0002¢\u0006\f\n\u0004\bd\u0010U\u001a\u0004\be\u0010fR\u001d\u0010l\u001a\u00020h8B@\u0002X\u0002¢\u0006\f\n\u0004\bi\u0010U\u001a\u0004\bj\u0010kR*\u0010u\u001a\n\u0012\u0004\u0012\u00020n\u0018\u00010m8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\bo\u0010p\u001a\u0004\bq\u0010r\"\u0004\bs\u0010tR\u0016\u0010y\u001a\u00020v8B@\u0002X\u0004¢\u0006\u0006\u001a\u0004\bw\u0010x¨\u0006\u0001"}, d2 = {"Lcom/appsamurai/storyly/StorylyView;", "Landroid/widget/FrameLayout;", "", "hasWindowFocus", "", "onWindowFocusChanged", "", "animationResId", "resumeStory", "(Ljava/lang/Integer;)V", "pauseStory", "closeStory", "refresh", "", "storyGroupId", "storyId", "Lcom/appsamurai/storyly/PlayMode;", "play", "openStory", "Landroid/net/Uri;", "payload", "Landroid/view/View;", "externalActionView", "showExternalActionView", "dismissExternalActionView", "Landroidx/fragment/app/Fragment;", "externalFragment", "showExternalFragment", "dismissExternalFragment", "dismissAllExternalFragment", "", "Lcom/appsamurai/storyly/storylylist/MomentsItem;", "momentsItems", "setMomentsItem", "Lcom/appsamurai/storyly/data/managers/product/STRProductItem;", "products", "hydrateProducts", "Lcom/appsamurai/storyly/data/managers/product/STRCart;", "cart", "updateCart", "contentDescription", "setStorylyContentDescription", "Lcom/appsamurai/storyly/StorylyInit;", "<set-?>", "a", "Lkotlin/properties/ReadWriteProperty;", "getStorylyInit", "()Lcom/appsamurai/storyly/StorylyInit;", "setStorylyInit", "(Lcom/appsamurai/storyly/StorylyInit;)V", "storylyInit", "Lcom/appsamurai/storyly/StorylyListener;", "b", "Lcom/appsamurai/storyly/StorylyListener;", "getStorylyListener", "()Lcom/appsamurai/storyly/StorylyListener;", "setStorylyListener", "(Lcom/appsamurai/storyly/StorylyListener;)V", "storylyListener", "Lcom/appsamurai/storyly/StorylyProductListener;", "c", "Lcom/appsamurai/storyly/StorylyProductListener;", "getStorylyProductListener", "()Lcom/appsamurai/storyly/StorylyProductListener;", "setStorylyProductListener", "(Lcom/appsamurai/storyly/StorylyProductListener;)V", "storylyProductListener", "Lcom/appsamurai/storyly/StorylyMomentsListener;", "d", "Lcom/appsamurai/storyly/StorylyMomentsListener;", "getStorylyMomentsListener", "()Lcom/appsamurai/storyly/StorylyMomentsListener;", "setStorylyMomentsListener", "(Lcom/appsamurai/storyly/StorylyMomentsListener;)V", "storylyMomentsListener", "Lcom/appsamurai/storyly/ad/StorylyAdViewProvider;", "e", "Lcom/appsamurai/storyly/ad/StorylyAdViewProvider;", "getStorylyAdViewProvider", "()Lcom/appsamurai/storyly/ad/StorylyAdViewProvider;", "setStorylyAdViewProvider", "(Lcom/appsamurai/storyly/ad/StorylyAdViewProvider;)V", "storylyAdViewProvider", "Lcom/appsamurai/storyly/data/managers/storage/d;", "f", "Lkotlin/Lazy;", "getSeenStateSharedPreferencesManager", "()Lcom/appsamurai/storyly/data/managers/storage/d;", "seenStateSharedPreferencesManager", "Lcom/appsamurai/storyly/data/managers/ad/b;", "g", "getAdViewManager", "()Lcom/appsamurai/storyly/data/managers/ad/b;", "adViewManager", "Lcom/appsamurai/storyly/data/managers/processing/f;", "h", "getStorylyDataManager", "()Lcom/appsamurai/storyly/data/managers/processing/f;", "storylyDataManager", "Lcom/appsamurai/storyly/analytics/e;", "i", "getStorylyTracker", "()Lcom/appsamurai/storyly/analytics/e;", "storylyTracker", "Lcom/appsamurai/storyly/data/managers/cache/c;", "j", "getStorylyImageCacheManager", "()Lcom/appsamurai/storyly/data/managers/cache/c;", "storylyImageCacheManager", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "n", "Ljava/lang/ref/WeakReference;", "getActivity", "()Ljava/lang/ref/WeakReference;", "setActivity", "(Ljava/lang/ref/WeakReference;)V", "activity", "Lcom/appsamurai/storyly/storylylist/StorylyListRecyclerView;", "getStorylyListRecyclerView", "()Lcom/appsamurai/storyly/storylylist/StorylyListRecyclerView;", "storylyListRecyclerView", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
public final class StorylyView extends FrameLayout {

    /* renamed from: u  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f3411u = {androidx.compose.ui.autofill.a.m(StorylyView.class, "storylyInit", "getStorylyInit()Lcom/appsamurai/storyly/StorylyInit;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ReadWriteProperty f3412a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public StorylyListener f3413b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public StorylyProductListener f3414c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public StorylyMomentsListener f3415d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public StorylyAdViewProvider f3416e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Lazy f3417f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f3418g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f3419h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f3420i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f3421j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public Uri f3422k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public a f3423l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public StorylyListRecyclerView f3424m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public WeakReference<Activity> f3425n;
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    public com.appsamurai.storyly.storylypresenter.b f3426o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    public StorylyDialogFragment f3427p;

    /* renamed from: q  reason: collision with root package name */
    public boolean f3428q;

    /* renamed from: r  reason: collision with root package name */
    public boolean f3429r;
    @Nullable

    /* renamed from: s  reason: collision with root package name */
    public Integer f3430s;
    @Nullable

    /* renamed from: t  reason: collision with root package name */
    public Integer f3431t;

    public static final class a {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f3432a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        public final String f3433b;
        @NotNull

        /* renamed from: c  reason: collision with root package name */
        public final PlayMode f3434c;

        /* renamed from: d  reason: collision with root package name */
        public final boolean f3435d;

        public a(@NotNull String str, @Nullable String str2, @NotNull PlayMode playMode, boolean z2) {
            Intrinsics.checkNotNullParameter(str, "storyGroupId");
            Intrinsics.checkNotNullParameter(playMode, "play");
            this.f3432a = str;
            this.f3433b = str2;
            this.f3434c = playMode;
            this.f3435d = z2;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual((Object) this.f3432a, (Object) aVar.f3432a) && Intrinsics.areEqual((Object) this.f3433b, (Object) aVar.f3433b) && this.f3434c == aVar.f3434c && this.f3435d == aVar.f3435d;
        }

        public int hashCode() {
            int hashCode = this.f3432a.hashCode() * 31;
            String str = this.f3433b;
            int hashCode2 = (this.f3434c.hashCode() + ((hashCode + (str == null ? 0 : str.hashCode())) * 31)) * 31;
            boolean z2 = this.f3435d;
            if (z2) {
                z2 = true;
            }
            return hashCode2 + (z2 ? 1 : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder("OpenStoryRequest(storyGroupId=");
            sb.append(this.f3432a);
            sb.append(", storyId=");
            sb.append(this.f3433b);
            sb.append(", play=");
            sb.append(this.f3434c);
            sb.append(", internalCall=");
            return androidx.camera.core.impl.i.c(sb, this.f3435d, ')');
        }
    }

    public /* synthetic */ class c {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[StorylyEvent.values().length];
            iArr[StorylyEvent.StoryProductUpdated.ordinal()] = 1;
            iArr[StorylyEvent.StoryProductAdded.ordinal()] = 2;
            iArr[StorylyEvent.StoryProductRemoved.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PlayMode.values().length];
            iArr2[PlayMode.StoryGroup.ordinal()] = 1;
            iArr2[PlayMode.Story.ordinal()] = 2;
            iArr2[PlayMode.Default.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final class d extends Lambda implements Function0<com.appsamurai.storyly.data.managers.ad.b> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3438a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(StorylyView storylyView) {
            super(0);
            this.f3438a = storylyView;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.ad.b(new a(this.f3438a), new b(this.f3438a));
        }
    }

    public static final class f extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3441a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ List<STRProductItem> f3442b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(StorylyView storylyView, List<STRProductItem> list) {
            super(0);
            this.f3441a = storylyView;
            this.f3442b = list;
        }

        public Object invoke() {
            com.appsamurai.storyly.data.managers.product.b e3 = this.f3441a.getStorylyDataManager().e();
            Set<STRProductItem> set = CollectionsKt.toSet(this.f3442b);
            e3.getClass();
            Intrinsics.checkNotNullParameter(set, "<set-?>");
            e3.f4042b = set;
            return Unit.INSTANCE;
        }
    }

    public static final class g extends Lambda implements Function0<com.appsamurai.storyly.data.managers.storage.d> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f3443a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context) {
            super(0);
            this.f3443a = context;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.storage.d(this.f3443a, "stryly-seen-state");
        }
    }

    public static final class h extends EmojiCompat.InitCallback {
        public void onFailed(@Nullable Throwable th) {
            a.C0055a.a(com.appsamurai.storyly.util.a.f6249a, Intrinsics.stringPlus("EmojiCompat initialization failed:", th == null ? null : th.getLocalizedMessage()), (String) null, 2);
        }

        public void onInitialized() {
            Intrinsics.checkNotNullParameter("EmojiCompat initialized", PushMessagingService.KEY_MESSAGE);
            Intrinsics.checkNotNullParameter("", PushMessagingService.KEY_TAG);
            Log.d(Intrinsics.stringPlus("[Storyly] ", ""), "EmojiCompat initialized");
        }
    }

    public static final class i extends ObservableProperty<StorylyInit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f3444a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3445b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ Context f3446c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(Object obj, Object obj2, StorylyView storylyView, Context context) {
            super(obj2);
            this.f3444a = obj;
            this.f3445b = storylyView;
            this.f3446c = context;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, StorylyInit storylyInit, StorylyInit storylyInit2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            StorylyInit storylyInit3 = storylyInit2;
            StorylyInit storylyInit4 = storylyInit;
            StorylyView.access$setupView(this.f3445b);
            if (!StringsKt.isBlank(this.f3445b.getStorylyInit().getStorylyId())) {
                this.f3445b.getStorylyInit().getConfig().getProduct$storyly_release().setPriceFormatter$storyly_release(new com.appsamurai.storyly.util.formatter.a(this.f3446c));
                this.f3445b.getStorylyTracker().f3518e = this.f3445b.getStorylyInit();
                com.appsamurai.storyly.data.managers.processing.f access$getStorylyDataManager = this.f3445b.getStorylyDataManager();
                StorylyInit storylyInit5 = this.f3445b.getStorylyInit();
                access$getStorylyDataManager.getClass();
                Intrinsics.checkNotNullParameter(storylyInit5, "<set-?>");
                access$getStorylyDataManager.f3973c.setValue(access$getStorylyDataManager, com.appsamurai.storyly.data.managers.processing.f.f3970y[0], storylyInit5);
                this.f3445b.getStorylyInit().getConfig().setOnDataUpdate$storyly_release(new l(this.f3445b));
                this.f3445b.getStorylyDataManager().e().f4041a.f4038a = this.f3445b.getStorylyInit().getConfig().getProduct$storyly_release().isCartEnabled$storyly_release();
                StorylyView.a(this.f3445b, com.appsamurai.storyly.data.managers.processing.e.StorylyLocalData, (Function0) null, (Function0) null, 6);
                StorylyView storylyView = this.f3445b;
                com.appsamurai.storyly.data.managers.processing.e eVar = com.appsamurai.storyly.data.managers.processing.e.PageData;
                StorylyView.a(storylyView, eVar, (Function0) null, (Function0) null, 6);
                StorylyView.a(this.f3445b, com.appsamurai.storyly.data.managers.processing.e.StorylyData, (Function0) null, (Function0) null, 6);
                StorylyView.a(this.f3445b, eVar, (Function0) null, (Function0) null, 6);
            }
        }
    }

    public static final class j extends Lambda implements Function0<com.appsamurai.storyly.data.managers.processing.f> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f3447a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3448b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public j(Context context, StorylyView storylyView) {
            super(0);
            this.f3447a = context;
            this.f3448b = storylyView;
        }

        public Object invoke() {
            com.appsamurai.storyly.data.managers.processing.f fVar = new com.appsamurai.storyly.data.managers.processing.f(this.f3447a, this.f3448b.getStorylyInit(), this.f3448b.getStorylyTracker());
            StorylyView storylyView = this.f3448b;
            fVar.f3978h = new j(storylyView);
            fVar.f3977g = new m(storylyView, fVar);
            fVar.f3976f = new n(storylyView);
            return fVar;
        }
    }

    public static final class k extends Lambda implements Function0<com.appsamurai.storyly.data.managers.cache.c> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f3449a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public k(Context context) {
            super(0);
            this.f3449a = context;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.cache.c(this.f3449a);
        }
    }

    public static final class l extends Lambda implements Function1<com.appsamurai.storyly.data.managers.processing.e, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3450a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public l(StorylyView storylyView) {
            super(1);
            this.f3450a = storylyView;
        }

        public Object invoke(Object obj) {
            com.appsamurai.storyly.data.managers.processing.e eVar = (com.appsamurai.storyly.data.managers.processing.e) obj;
            Intrinsics.checkNotNullParameter(eVar, "requestType");
            StorylyView.a(this.f3450a, eVar, (Function0) null, (Function0) null, 6);
            StorylyView.a(this.f3450a, com.appsamurai.storyly.data.managers.processing.e.PageData, (Function0) null, (Function0) null, 6);
            return Unit.INSTANCE;
        }
    }

    public static final class m extends Lambda implements Function2<v, Integer, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3451a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public m(StorylyView storylyView) {
            super(2);
            this.f3451a = storylyView;
        }

        public Object invoke(Object obj, Object obj2) {
            int intValue = ((Number) obj2).intValue();
            Intrinsics.checkNotNullParameter((v) obj, "$noName_0");
            StorylyView.access$updateOrientation(this.f3451a, intValue);
            if (this.f3451a.getContext().getResources().getConfiguration().orientation == 1 && !StorylyView.access$isStoryPlaying(this.f3451a)) {
                this.f3451a.f3431t = null;
                StorylyView.a(this.f3451a, intValue, (List) null, (Integer) null, false, 14);
            }
            return Unit.INSTANCE;
        }
    }

    public static final class n extends Lambda implements Function1<Function0<? extends Unit>, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3452a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public n(StorylyView storylyView) {
            super(1);
            this.f3452a = storylyView;
        }

        public Object invoke(Object obj) {
            Function0 function0 = (Function0) obj;
            Intrinsics.checkNotNullParameter(function0, "onMomentsPageLoadEnd");
            this.f3452a.a(com.appsamurai.storyly.data.managers.processing.e.PageData, (Function0<Unit>) null, function0);
            return Unit.INSTANCE;
        }
    }

    public static final class o extends Lambda implements Function0<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3453a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public o(StorylyView storylyView) {
            super(0);
            this.f3453a = storylyView;
        }

        public Object invoke() {
            return Integer.valueOf(this.f3453a.getStorylyDataManager().d().f3943b);
        }
    }

    public static final class p extends Lambda implements Function0<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3454a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public p(StorylyView storylyView) {
            super(0);
            this.f3454a = storylyView;
        }

        public Object invoke() {
            int i3;
            List<v> list;
            com.appsamurai.storyly.data.managers.processing.f access$getStorylyDataManager = this.f3454a.getStorylyDataManager();
            int size = access$getStorylyDataManager.d().f3944c.size();
            s sVar = access$getStorylyDataManager.f3980j;
            if (sVar == null || (list = sVar.f4197a) == null) {
                i3 = 0;
            } else {
                ArrayList arrayList = new ArrayList();
                for (T next : list) {
                    if (((v) next).c()) {
                        arrayList.add(next);
                    }
                }
                i3 = arrayList.size();
            }
            return Integer.valueOf(size - i3);
        }
    }

    public static final class q extends Lambda implements Function0<com.appsamurai.storyly.analytics.e> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f3455a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3456b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public q(Context context, StorylyView storylyView) {
            super(0);
            this.f3455a = context;
            this.f3456b = storylyView;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.analytics.e(this.f3455a, new o(this.f3456b), new p(this.f3456b));
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StorylyView(@NotNull Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public static final void a(StorylyView storylyView, DialogInterface dialogInterface) {
        com.appsamurai.storyly.data.managers.product.a aVar;
        Intrinsics.checkNotNullParameter(storylyView, "this$0");
        if (!storylyView.f3429r) {
            com.appsamurai.storyly.data.managers.storage.d seenStateSharedPreferencesManager = storylyView.getSeenStateSharedPreferencesManager();
            com.appsamurai.storyly.storylypresenter.b bVar = storylyView.f3426o;
            List<v> a2 = bVar == null ? null : bVar.a();
            if (a2 == null) {
                a2 = CollectionsKt.emptyList();
            }
            seenStateSharedPreferencesManager.b(a2);
            com.appsamurai.storyly.data.managers.processing.f.a(storylyView.getStorylyDataManager(), com.appsamurai.storyly.data.managers.processing.e.SeenStateUpdate, (Function0) null, (Function0) null, 6);
            com.appsamurai.storyly.data.managers.ad.b adViewManager = storylyView.getAdViewManager();
            for (StorylyAdView destroy : adViewManager.f3883h) {
                destroy.destroy();
            }
            adViewManager.f3883h.clear();
        }
        com.appsamurai.storyly.storylypresenter.b bVar2 = storylyView.f3426o;
        if (bVar2 != null) {
            com.appsamurai.storyly.storylypresenter.d b3 = bVar2.b();
            com.appsamurai.storyly.storylypresenter.o a3 = b3.a(b3.getSelectedStorylyGroupIndex());
            if (!(a3 == null || (aVar = a3.f5056k) == null)) {
                aVar.f4040c = null;
            }
        }
        StorylyDialogFragment storylyDialogFragment = storylyView.f3427p;
        if (storylyDialogFragment != null) {
            storylyDialogFragment.dismissAllFragments();
        }
        StorylyDialogFragment storylyDialogFragment2 = storylyView.f3427p;
        if (storylyDialogFragment2 != null) {
            storylyDialogFragment2.dismiss();
        }
        storylyView.f3427p = null;
        storylyView.a();
        storylyView.f3428q = false;
        StorylyListener storylyListener = storylyView.f3413b;
        if (storylyListener != null) {
            storylyListener.storylyStoryDismissed(storylyView);
        }
        if (!storylyView.f3429r) {
            storylyView.f3426o = null;
        }
        com.appsamurai.storyly.storylypresenter.b bVar3 = storylyView.f3426o;
        if (bVar3 != null) {
            bVar3.setOnDismissListener((DialogInterface.OnDismissListener) null);
        }
    }

    public static final boolean access$isStoryPlaying(StorylyView storylyView) {
        boolean z2;
        synchronized (storylyView) {
            z2 = true;
            if (!storylyView.f3428q) {
                storylyView.f3428q = true;
                z2 = false;
            }
        }
        return z2;
    }

    public static final void access$onStoryEvent(StorylyView storylyView, StorylyEvent storylyEvent, StoryGroup storyGroup, Story story, StoryComponent storyComponent) {
        StorylyListener storylyListener = storylyView.f3413b;
        if (storylyListener != null) {
            storylyListener.storylyEvent(storylyView, storylyEvent, storyGroup, story, storyComponent);
        }
    }

    public static final void access$onStoryProductEvent(StorylyView storylyView, StorylyEvent storylyEvent, Function1 function1, Function1 function12, STRCart sTRCart, STRCartItem sTRCartItem) {
        storylyView.getClass();
        int i3 = c.$EnumSwitchMapping$0[storylyEvent.ordinal()];
        if (i3 == 1 || i3 == 2 || i3 == 3) {
            c cVar = new c(storylyView, function1);
            StorylyProductListener storylyProductListener = storylyView.f3414c;
            if (storylyProductListener != null) {
                storylyProductListener.storylyUpdateCartEvent(storylyView, storylyEvent, sTRCart, sTRCartItem, cVar, function12);
                return;
            }
            return;
        }
        StorylyProductListener storylyProductListener2 = storylyView.f3414c;
        if (storylyProductListener2 != null) {
            storylyProductListener2.storylyEvent(storylyView, storylyEvent);
        }
    }

    public static final void access$setupView(StorylyView storylyView) {
        storylyView.removeView(storylyView.getStorylyListRecyclerView());
        storylyView.f3424m = null;
        storylyView.addView(storylyView.getStorylyListRecyclerView());
        StorylyListRecyclerView storylyListRecyclerView = storylyView.getStorylyListRecyclerView();
        CharSequence contentDescription = storylyView.getContentDescription();
        if (contentDescription == null) {
            contentDescription = storylyView.getResources().getString(R.string.st_desc_story_bar_default);
        }
        storylyListRecyclerView.setContentDescription(contentDescription);
        Context context = storylyView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        storylyView.f3425n = new WeakReference<>(com.appsamurai.storyly.util.g.a(context));
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void access$updateListGroups(com.appsamurai.storyly.StorylyView r7, java.util.List r8) {
        /*
            r7.getClass()
            r0 = 0
            if (r8 != 0) goto L_0x0008
            r1 = r0
            goto L_0x0029
        L_0x0008:
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r8, 10)
            r1.<init>(r2)
            java.util.Iterator r8 = r8.iterator()
        L_0x0015:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L_0x0029
            java.lang.Object r2 = r8.next()
            com.appsamurai.storyly.data.v r2 = (com.appsamurai.storyly.data.v) r2
            com.appsamurai.storyly.data.v r2 = r2.a()
            r1.add(r2)
            goto L_0x0015
        L_0x0029:
            r8 = 0
            if (r1 != 0) goto L_0x002e
            r1 = r0
            goto L_0x0057
        L_0x002e:
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x0057
            r1 = 4
            kotlin.ranges.IntRange r1 = kotlin.ranges.RangesKt.until((int) r8, (int) r1)
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, 10)
            r2.<init>(r3)
            java.util.Iterator r1 = r1.iterator()
        L_0x0046:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0056
            r3 = r1
            kotlin.collections.IntIterator r3 = (kotlin.collections.IntIterator) r3
            r3.nextInt()
            r2.add(r0)
            goto L_0x0046
        L_0x0056:
            r1 = r2
        L_0x0057:
            if (r1 != 0) goto L_0x005d
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L_0x005d:
            com.appsamurai.storyly.data.managers.processing.f r2 = r7.getStorylyDataManager()
            com.appsamurai.storyly.data.managers.pagination.c r2 = r2.d()
            java.lang.String r2 = r2.f3942a
            com.appsamurai.storyly.storylylist.StorylyListRecyclerView r3 = r7.getStorylyListRecyclerView()
            r3.setPaginationDataStateId$storyly_release(r2)
            com.appsamurai.storyly.storylylist.StorylyListRecyclerView r3 = r7.getStorylyListRecyclerView()
            r3.setStorylyAdapterData$storyly_release(r1)
            com.appsamurai.storyly.data.managers.cache.c r3 = r7.getStorylyImageCacheManager()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r5 = r1.iterator()
        L_0x0082:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0094
            java.lang.Object r6 = r5.next()
            com.appsamurai.storyly.data.v r6 = (com.appsamurai.storyly.data.v) r6
            if (r6 == 0) goto L_0x0082
            r4.add(r6)
            goto L_0x0082
        L_0x0094:
            r3.getClass()
            java.lang.String r5 = "<set-?>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r5)
            kotlin.properties.ReadWriteProperty r5 = r3.f3889c
            kotlin.reflect.KProperty<java.lang.Object>[] r6 = com.appsamurai.storyly.data.managers.cache.c.f3886g
            r6 = r6[r8]
            r5.setValue(r3, r6, r4)
            boolean r3 = r7.f3428q
            if (r3 == 0) goto L_0x0174
            com.appsamurai.storyly.storylypresenter.b r3 = r7.f3426o
            if (r3 != 0) goto L_0x00af
            r3 = r0
            goto L_0x00b1
        L_0x00af:
            java.lang.String r3 = r3.f4751f
        L_0x00b1:
            if (r3 == 0) goto L_0x00b9
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r2)
            if (r2 == 0) goto L_0x0174
        L_0x00b9:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x00c2:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00d4
            java.lang.Object r3 = r1.next()
            com.appsamurai.storyly.data.v r3 = (com.appsamurai.storyly.data.v) r3
            if (r3 == 0) goto L_0x00c2
            r2.add(r3)
            goto L_0x00c2
        L_0x00d4:
            com.appsamurai.storyly.storylypresenter.b r1 = r7.f3426o
            if (r1 != 0) goto L_0x00d9
            goto L_0x00ff
        L_0x00d9:
            java.util.List r1 = r1.a()
            if (r1 != 0) goto L_0x00e0
            goto L_0x00ff
        L_0x00e0:
            java.util.ArrayList r0 = new java.util.ArrayList
            int r3 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, 10)
            r0.<init>(r3)
            java.util.Iterator r1 = r1.iterator()
        L_0x00ed:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00ff
            java.lang.Object r3 = r1.next()
            com.appsamurai.storyly.data.v r3 = (com.appsamurai.storyly.data.v) r3
            java.lang.String r3 = r3.f4221a
            r0.add(r3)
            goto L_0x00ed
        L_0x00ff:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x0108:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0126
            java.lang.Object r3 = r2.next()
            r4 = r3
            com.appsamurai.storyly.data.v r4 = (com.appsamurai.storyly.data.v) r4
            if (r0 != 0) goto L_0x0118
            goto L_0x0122
        L_0x0118:
            java.lang.String r4 = r4.f4221a
            boolean r4 = r0.contains(r4)
            r5 = 1
            if (r4 != r5) goto L_0x0122
            goto L_0x0108
        L_0x0122:
            r1.add(r3)
            goto L_0x0108
        L_0x0126:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.appsamurai.storyly.storylypresenter.b r2 = r7.f3426o
            if (r2 != 0) goto L_0x0130
            goto L_0x013a
        L_0x0130:
            java.util.List r2 = r2.a()
            if (r2 != 0) goto L_0x0137
            goto L_0x013a
        L_0x0137:
            r0.addAll(r2)
        L_0x013a:
            java.util.Iterator r1 = r1.iterator()
        L_0x013e:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x016c
            java.lang.Object r2 = r1.next()
            com.appsamurai.storyly.data.v r2 = (com.appsamurai.storyly.data.v) r2
            java.lang.Integer r3 = r2.f4241u
            if (r3 != 0) goto L_0x0150
            r3 = r8
            goto L_0x0154
        L_0x0150:
            int r3 = r3.intValue()
        L_0x0154:
            int r4 = r0.size()
            if (r3 < r4) goto L_0x015e
            r0.add(r2)
            goto L_0x013e
        L_0x015e:
            java.lang.Integer r3 = r2.f4241u
            if (r3 != 0) goto L_0x0164
            r3 = r8
            goto L_0x0168
        L_0x0164:
            int r3 = r3.intValue()
        L_0x0168:
            r0.add(r3, r2)
            goto L_0x013e
        L_0x016c:
            com.appsamurai.storyly.storylypresenter.b r7 = r7.f3426o
            if (r7 != 0) goto L_0x0171
            goto L_0x0174
        L_0x0171:
            r7.a((java.util.List<com.appsamurai.storyly.data.v>) r0)
        L_0x0174:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.StorylyView.access$updateListGroups(com.appsamurai.storyly.StorylyView, java.util.List):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r0 = r0.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void access$updateOrientation(com.appsamurai.storyly.StorylyView r3, int r4) {
        /*
            java.lang.Integer r0 = r3.f3430s
            r1 = 0
            if (r0 != 0) goto L_0x001c
            java.lang.ref.WeakReference<android.app.Activity> r0 = r3.f3425n
            if (r0 != 0) goto L_0x000a
            goto L_0x0012
        L_0x000a:
            java.lang.Object r0 = r0.get()
            android.app.Activity r0 = (android.app.Activity) r0
            if (r0 != 0) goto L_0x0014
        L_0x0012:
            r0 = r1
            goto L_0x001c
        L_0x0014:
            int r0 = r0.getRequestedOrientation()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L_0x001c:
            r3.f3430s = r0
            android.content.Context r0 = r3.getContext()
            android.content.res.Resources r0 = r0.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.orientation
            r2 = 1
            if (r0 == r2) goto L_0x0049
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3.f3431t = r4
            java.lang.ref.WeakReference<android.app.Activity> r3 = r3.f3425n
            if (r3 != 0) goto L_0x003a
            goto L_0x0041
        L_0x003a:
            java.lang.Object r3 = r3.get()
            r1 = r3
            android.app.Activity r1 = (android.app.Activity) r1
        L_0x0041:
            if (r1 != 0) goto L_0x0044
            goto L_0x005c
        L_0x0044:
            r3 = 7
            r1.setRequestedOrientation(r3)
            goto L_0x005c
        L_0x0049:
            java.lang.ref.WeakReference<android.app.Activity> r3 = r3.f3425n
            if (r3 != 0) goto L_0x004e
            goto L_0x0055
        L_0x004e:
            java.lang.Object r3 = r3.get()
            r1 = r3
            android.app.Activity r1 = (android.app.Activity) r1
        L_0x0055:
            if (r1 != 0) goto L_0x0058
            goto L_0x005c
        L_0x0058:
            r3 = 5
            r1.setRequestedOrientation(r3)
        L_0x005c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.StorylyView.access$updateOrientation(com.appsamurai.storyly.StorylyView, int):void");
    }

    public static /* synthetic */ void closeStory$default(StorylyView storylyView, Integer num, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            num = null;
        }
        storylyView.closeStory(num);
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.data.managers.ad.b getAdViewManager() {
        return (com.appsamurai.storyly.data.managers.ad.b) this.f3418g.getValue();
    }

    private final com.appsamurai.storyly.data.managers.storage.d getSeenStateSharedPreferencesManager() {
        return (com.appsamurai.storyly.data.managers.storage.d) this.f3417f.getValue();
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.data.managers.processing.f getStorylyDataManager() {
        return (com.appsamurai.storyly.data.managers.processing.f) this.f3419h.getValue();
    }

    private final com.appsamurai.storyly.data.managers.cache.c getStorylyImageCacheManager() {
        return (com.appsamurai.storyly.data.managers.cache.c) this.f3421j.getValue();
    }

    /* access modifiers changed from: private */
    public final StorylyListRecyclerView getStorylyListRecyclerView() {
        StorylyListRecyclerView storylyListRecyclerView = this.f3424m;
        if (storylyListRecyclerView != null) {
            return storylyListRecyclerView;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        StorylyListRecyclerView storylyListRecyclerView2 = new StorylyListRecyclerView(context, getStorylyInit().getConfig(), getStorylyTracker());
        storylyListRecyclerView2.setOnStorylyGroupSelected$storyly_release(new m(this));
        storylyListRecyclerView2.setOnScrollStarted$storyly_release(new n(this));
        storylyListRecyclerView2.setRetrieveSessionNotSeenCount$storyly_release(new o(this));
        storylyListRecyclerView2.setRetrieveCombinedGroupSize$storyly_release(new p(this));
        this.f3424m = storylyListRecyclerView2;
        return storylyListRecyclerView2;
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.analytics.e getStorylyTracker() {
        return (com.appsamurai.storyly.analytics.e) this.f3420i.getValue();
    }

    public static /* synthetic */ boolean openStory$default(StorylyView storylyView, String str, String str2, PlayMode playMode, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            str2 = null;
        }
        if ((i3 & 4) != 0) {
            playMode = PlayMode.Default;
        }
        return storylyView.openStory(str, str2, playMode);
    }

    public static /* synthetic */ void pauseStory$default(StorylyView storylyView, Integer num, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            num = null;
        }
        storylyView.pauseStory(num);
    }

    public static /* synthetic */ void resumeStory$default(StorylyView storylyView, Integer num, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            num = null;
        }
        storylyView.resumeStory(num);
    }

    public final void b() {
        try {
            EmojiCompat.get().getLoadState();
        } catch (IllegalStateException unused) {
            EmojiCompat.init(new FontRequestEmojiCompatConfig(getContext(), new FontRequest("com.google.android.gms.fonts", "com.google.android.gms", "Noto Color Emoji Compat", R.array.com_google_android_gms_fonts_certs)).setReplaceAll(true).registerInitCallback(new h()));
        }
    }

    public final void closeStory(@Nullable Integer num) {
        com.appsamurai.storyly.storylypresenter.b bVar = this.f3426o;
        if (bVar != null) {
            bVar.a(false, num);
        }
    }

    public final void dismissAllExternalFragment() {
    }

    public final void dismissExternalActionView() {
        com.appsamurai.storyly.storylypresenter.b bVar;
        View view;
        if (this.f3428q && (bVar = this.f3426o) != null && (view = bVar.f4753h) != null) {
            bVar.f4752g.f4385c.removeView(view);
            bVar.c();
            bVar.f4753h = null;
        }
    }

    public final void dismissExternalFragment() {
    }

    @Nullable
    public final WeakReference<Activity> getActivity() {
        return this.f3425n;
    }

    @Nullable
    public final StorylyAdViewProvider getStorylyAdViewProvider() {
        return this.f3416e;
    }

    @NotNull
    public final StorylyInit getStorylyInit() {
        return (StorylyInit) this.f3412a.getValue(this, f3411u[0]);
    }

    @Nullable
    public final StorylyListener getStorylyListener() {
        return this.f3413b;
    }

    @Nullable
    public final StorylyMomentsListener getStorylyMomentsListener() {
        return this.f3415d;
    }

    @Nullable
    public final StorylyProductListener getStorylyProductListener() {
        return this.f3414c;
    }

    public final void hydrateProducts(@NotNull List<STRProductItem> list) {
        Intrinsics.checkNotNullParameter(list, "products");
        a(this, com.appsamurai.storyly.data.managers.processing.e.ProductDataUpdate, (Function0) new f(this, list), (Function0) null, 4);
    }

    public void onDetachedFromWindow() {
        com.appsamurai.storyly.storylypresenter.b bVar = this.f3426o;
        if (bVar != null) {
            com.appsamurai.storyly.storylypresenter.d b3 = bVar.b();
            com.appsamurai.storyly.storylypresenter.o a2 = b3.a(b3.getSelectedStorylyGroupIndex());
            if (a2 != null) {
                a2.i();
            }
        }
        StorylyListRecyclerView storylyListRecyclerView = this.f3424m;
        if (storylyListRecyclerView != null) {
            storylyListRecyclerView.a();
        }
        super.onDetachedFromWindow();
    }

    public void onRestoreInstanceState(@Nullable Parcelable parcelable) {
        if (parcelable instanceof b) {
            b bVar = (b) parcelable;
            super.onRestoreInstanceState(bVar.getSuperState());
            int i3 = bVar.f3436a;
            this.f3430s = i3 == Integer.MIN_VALUE ? null : Integer.valueOf(i3);
            String str = bVar.f3437b;
            if (str != null && !this.f3429r && !Intrinsics.areEqual((Object) str, (Object) "")) {
                a(str, (String) null, PlayMode.Default, false);
                return;
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Nullable
    public Parcelable onSaveInstanceState() {
        String str;
        b bVar = new b(super.onSaveInstanceState());
        Integer num = this.f3431t;
        if (num != null) {
            v vVar = (v) CollectionsKt.getOrNull(getStorylyListRecyclerView().getStorylyGroupItems$storyly_release(), num.intValue());
            if (vVar == null || (str = vVar.f4221a) == null) {
                str = "";
            }
            bVar.f3437b = str;
        }
        Integer num2 = this.f3430s;
        bVar.f3436a = num2 == null ? Integer.MIN_VALUE : num2.intValue();
        return bVar;
    }

    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        if (!this.f3428q) {
            com.appsamurai.storyly.data.managers.processing.f.a(getStorylyDataManager(), com.appsamurai.storyly.data.managers.processing.e.SeenStateUpdate, (Function0) null, (Function0) null, 6);
        }
    }

    @JvmOverloads
    public final boolean openStory(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "storyGroupId");
        return openStory$default(this, str, (String) null, (PlayMode) null, 6, (Object) null);
    }

    public final void pauseStory(@Nullable Integer num) {
        this.f3429r = true;
        com.appsamurai.storyly.storylypresenter.b bVar = this.f3426o;
        if (bVar != null) {
            bVar.a(true, num);
        }
    }

    public final void refresh() {
        a(this, com.appsamurai.storyly.data.managers.processing.e.StorylyData, (Function0) null, (Function0) null, 6);
        a(this, com.appsamurai.storyly.data.managers.processing.e.PageData, (Function0) null, (Function0) null, 6);
    }

    public final void resumeStory(@Nullable Integer num) {
        Integer selectedStorylyGroupIndex;
        if (this.f3429r) {
            boolean z2 = false;
            this.f3429r = false;
            com.appsamurai.storyly.storylypresenter.b bVar = this.f3426o;
            if (bVar != null) {
                synchronized (this) {
                    if (this.f3428q) {
                        z2 = true;
                    } else {
                        this.f3428q = true;
                    }
                }
                if (!z2 && (selectedStorylyGroupIndex = bVar.b().getSelectedStorylyGroupIndex()) != null) {
                    a(this, selectedStorylyGroupIndex.intValue(), (List) null, num, true, 2);
                }
            }
        }
    }

    public final void setActivity(@Nullable WeakReference<Activity> weakReference) {
        this.f3425n = weakReference;
    }

    public final void setMomentsItem(@NotNull List<MomentsItem> list) {
        Intrinsics.checkNotNullParameter(list, "momentsItems");
        getStorylyListRecyclerView().setMomentsAdapterData$storyly_release(list);
    }

    public final void setStorylyAdViewProvider(@Nullable StorylyAdViewProvider storylyAdViewProvider) {
        this.f3416e = storylyAdViewProvider;
    }

    public final void setStorylyContentDescription(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "contentDescription");
        getStorylyListRecyclerView().setContentDescription(str);
    }

    public final void setStorylyInit(@NotNull StorylyInit storylyInit) {
        Intrinsics.checkNotNullParameter(storylyInit, "<set-?>");
        this.f3412a.setValue(this, f3411u[0], storylyInit);
    }

    public final void setStorylyListener(@Nullable StorylyListener storylyListener) {
        this.f3413b = storylyListener;
    }

    public final void setStorylyMomentsListener(@Nullable StorylyMomentsListener storylyMomentsListener) {
        this.f3415d = storylyMomentsListener;
    }

    public final void setStorylyProductListener(@Nullable StorylyProductListener storylyProductListener) {
        this.f3414c = storylyProductListener;
    }

    public final void showExternalActionView(@NotNull View view) {
        com.appsamurai.storyly.storylypresenter.b bVar;
        Intrinsics.checkNotNullParameter(view, "externalActionView");
        if (this.f3428q && (bVar = this.f3426o) != null) {
            Intrinsics.checkNotNullParameter(view, "externalActionView");
            if (bVar.f4753h == null) {
                bVar.f4753h = view;
                bVar.b().d();
                bVar.b().a();
                bVar.f4752g.f4385c.addView(view, -1, -1);
            }
        }
    }

    public final void showExternalFragment(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "externalFragment");
    }

    public final void updateCart(@NotNull STRCart sTRCart) {
        Intrinsics.checkNotNullParameter(sTRCart, "cart");
        com.appsamurai.storyly.data.managers.product.a aVar = getStorylyDataManager().e().f4041a;
        aVar.f4039b = sTRCart;
        Function1<? super STRCart, Unit> function1 = aVar.f4040c;
        if (function1 != null) {
            function1.invoke(sTRCart);
        }
    }

    public static final class e extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyView f3439a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ Function0<Unit> f3440b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(StorylyView storylyView, Function0<Unit> function0) {
            super(0);
            this.f3439a = storylyView;
            this.f3440b = function0;
        }

        public final void a() {
            StorylyView storylyView = this.f3439a;
            storylyView.post(new C0.d(storylyView, 5));
            Function0<Unit> function0 = this.f3440b;
            if (function0 != null) {
                function0.invoke();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            a();
            return Unit.INSTANCE;
        }

        public static final void a(StorylyView storylyView) {
            Intrinsics.checkNotNullParameter(storylyView, "this$0");
            storylyView.getStorylyListRecyclerView().scrollToPosition(0);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StorylyView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @JvmOverloads
    public final boolean openStory(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, "storyGroupId");
        return openStory$default(this, str, str2, (PlayMode) null, 4, (Object) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ StorylyView(Context context, AttributeSet attributeSet, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i3);
    }

    @JvmOverloads
    public final boolean openStory(@NotNull String str, @Nullable String str2, @NotNull PlayMode playMode) {
        Intrinsics.checkNotNullParameter(str, "storyGroupId");
        Intrinsics.checkNotNullParameter(playMode, "play");
        return a(str, str2, playMode, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StorylyView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Delegates delegates = Delegates.INSTANCE;
        StorylyInit storylyInit = new StorylyInit("", (StorylyConfig) null, 2, (DefaultConstructorMarker) null);
        this.f3412a = new i(storylyInit, storylyInit, this, context);
        this.f3417f = LazyKt.lazy(new g(context));
        this.f3418g = LazyKt.lazy(new d(this));
        this.f3419h = LazyKt.lazy(new j(context, this));
        this.f3420i = LazyKt.lazy(new q(context, this));
        this.f3421j = LazyKt.lazy(new k(context));
        setMotionEventSplittingEnabled(false);
        b();
    }

    public final boolean openStory(@NotNull Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "payload");
        UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer(uri.toString());
        String value = urlQuerySanitizer.getValue("g");
        String str = null;
        if (value == null) {
            value = null;
        }
        if (value == null) {
            return false;
        }
        String value2 = urlQuerySanitizer.getValue("s");
        if (value2 != null) {
            str = value2;
        }
        PlayMode.Companion companion = PlayMode.Companion;
        String value3 = urlQuerySanitizer.getValue("play");
        if (value3 == null) {
            value3 = "default";
        }
        PlayMode fromValue = companion.getFromValue(value3);
        this.f3422k = uri;
        return openStory(value, str, fromValue);
    }

    public static final class b extends View.BaseSavedState {
        @NotNull
        public static final a CREATOR = new a();

        /* renamed from: a  reason: collision with root package name */
        public int f3436a = -1;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        public String f3437b = "";

        public static final class a implements Parcelable.Creator<b> {
            public Object createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new b(parcel);
            }

            public Object[] newArray(int i3) {
                return new b[i3];
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(@NotNull Parcel parcel) {
            super(parcel);
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            this.f3436a = parcel.readInt();
            this.f3437b = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(@NotNull Parcel parcel, int i3) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            super.writeToParcel(parcel, i3);
            parcel.writeString(this.f3437b);
            parcel.writeInt(this.f3436a);
        }

        public b(@Nullable Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static /* synthetic */ void a(StorylyView storylyView, com.appsamurai.storyly.data.managers.processing.e eVar, Function0 function0, Function0 function02, int i3) {
        if ((i3 & 2) != 0) {
            function0 = null;
        }
        if ((i3 & 4) != 0) {
            function02 = null;
        }
        storylyView.a(eVar, function0, function02);
    }

    public final void a(com.appsamurai.storyly.data.managers.processing.e eVar, Function0<Unit> function0, Function0<Unit> function02) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = function0;
        if (eVar != com.appsamurai.storyly.data.managers.processing.e.PageData) {
            objectRef.element = new e(this, function0);
        }
        new Handler(getContext().getMainLooper()).post(new K0.a(3, this, eVar, objectRef, function02));
    }

    public static final void a(StorylyView storylyView, com.appsamurai.storyly.data.managers.processing.e eVar, Ref.ObjectRef objectRef, Function0 function0) {
        Intrinsics.checkNotNullParameter(storylyView, "this$0");
        Intrinsics.checkNotNullParameter(eVar, "$requestType");
        Intrinsics.checkNotNullParameter(objectRef, "$onStartFunc");
        storylyView.getStorylyDataManager().a(eVar, (Function0) objectRef.element, function0);
    }

    public final void a() {
        Integer num = this.f3430s;
        if (num != null) {
            int intValue = num.intValue();
            WeakReference<Activity> activity = getActivity();
            Activity activity2 = activity == null ? null : activity.get();
            if (activity2 != null) {
                activity2.setRequestedOrientation(intValue);
            }
        }
        this.f3430s = null;
    }

    public static /* synthetic */ void a(StorylyView storylyView, int i3, List list, Integer num, boolean z2, int i4) {
        if ((i4 & 2) != 0) {
            list = null;
        }
        if ((i4 & 4) != 0) {
            num = null;
        }
        if ((i4 & 8) != 0) {
            z2 = false;
        }
        storylyView.a(i3, (List<v>) list, num, z2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0161  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r19, java.util.List<com.appsamurai.storyly.data.v> r20, java.lang.Integer r21, boolean r22) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = 1
            r3 = 0
            r0.f3429r = r3
            r4 = -1
            r5 = 2
            r6 = 0
            if (r1 != r4) goto L_0x0018
            com.appsamurai.storyly.util.a$a r0 = com.appsamurai.storyly.util.a.f6249a
            java.lang.String r1 = "Invalid index to show story."
            com.appsamurai.storyly.util.a.C0055a.b(r0, r1, r6, r5)
            java.lang.Thread.dumpStack()
            return
        L_0x0018:
            com.appsamurai.storyly.storylypresenter.b r4 = r0.f3426o
            if (r4 != 0) goto L_0x0090
            com.appsamurai.storyly.storylypresenter.b r4 = new com.appsamurai.storyly.storylypresenter.b
            java.lang.ref.WeakReference<android.app.Activity> r7 = r0.f3425n
            if (r7 != 0) goto L_0x0024
            r7 = r6
            goto L_0x002a
        L_0x0024:
            java.lang.Object r7 = r7.get()
            android.app.Activity r7 = (android.app.Activity) r7
        L_0x002a:
            if (r7 != 0) goto L_0x0030
            android.content.Context r7 = r18.getContext()
        L_0x0030:
            r8 = r7
            java.lang.String r7 = "activity?.get() ?: context"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r7)
            int r9 = com.appsamurai.storyly.R.style.StorylyConfig
            com.appsamurai.storyly.analytics.e r10 = r18.getStorylyTracker()
            com.appsamurai.storyly.StorylyInit r7 = r18.getStorylyInit()
            com.appsamurai.storyly.config.StorylyConfig r11 = r7.getConfig()
            com.appsamurai.storyly.data.managers.cache.c r12 = r18.getStorylyImageCacheManager()
            com.appsamurai.storyly.d r13 = new com.appsamurai.storyly.d
            r13.<init>(r0)
            com.appsamurai.storyly.e r14 = new com.appsamurai.storyly.e
            r14.<init>(r0)
            com.appsamurai.storyly.f r15 = new com.appsamurai.storyly.f
            r15.<init>(r0)
            com.appsamurai.storyly.g r7 = new com.appsamurai.storyly.g
            r7.<init>(r0)
            com.appsamurai.storyly.h r3 = new com.appsamurai.storyly.h
            com.appsamurai.storyly.data.managers.processing.f r16 = r18.getStorylyDataManager()
            com.appsamurai.storyly.data.managers.conditional.b r5 = r16.b()
            r3.<init>(r5)
            r5 = r7
            r7 = r4
            r16 = r5
            r17 = r3
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            com.appsamurai.storyly.data.managers.processing.f r3 = r18.getStorylyDataManager()
            com.appsamurai.storyly.data.managers.product.b r3 = r3.e()
            com.appsamurai.storyly.data.managers.product.a r3 = r3.f4041a
            kotlin.properties.ReadWriteProperty r5 = r4.f4750e
            kotlin.reflect.KProperty<java.lang.Object>[] r7 = com.appsamurai.storyly.storylypresenter.b.f4745k
            r7 = r7[r2]
            r5.setValue(r4, r7, r3)
            com.appsamurai.storyly.i r3 = new com.appsamurai.storyly.i
            r3.<init>(r4, r0)
            r4.f4748c = r3
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r0.f3426o = r4
        L_0x0090:
            if (r22 != 0) goto L_0x0095
            r3 = r20
            goto L_0x009f
        L_0x0095:
            com.appsamurai.storyly.storylypresenter.b r3 = r0.f3426o
            if (r3 != 0) goto L_0x009b
            r3 = r6
            goto L_0x009f
        L_0x009b:
            java.util.List r3 = r3.a()
        L_0x009f:
            if (r3 != 0) goto L_0x00ad
            com.appsamurai.storyly.storylylist.StorylyListRecyclerView r3 = r18.getStorylyListRecyclerView()
            java.util.List r3 = r3.getStorylyGroupItems$storyly_release()
            java.util.List r3 = kotlin.collections.CollectionsKt.filterNotNull(r3)
        L_0x00ad:
            com.appsamurai.storyly.data.managers.ad.b r4 = r18.getAdViewManager()
            com.appsamurai.storyly.data.managers.processing.f r5 = r18.getStorylyDataManager()
            com.appsamurai.storyly.data.s r5 = r5.f3980j
            if (r5 != 0) goto L_0x00bb
            r5 = r6
            goto L_0x00bd
        L_0x00bb:
            com.appsamurai.storyly.data.a r5 = r5.f4198b
        L_0x00bd:
            r4.getClass()
            java.lang.String r7 = "storylyGroupItems"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r7)
            if (r5 != 0) goto L_0x00c9
            goto L_0x0102
        L_0x00c9:
            r4.f3881f = r5
            r4.f3878c = r3
            r4.f3879d = r1
            r4.f3880e = r1
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r4.f3882g = r7
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r4.f3883h = r7
            int r7 = r5.f3591a
            int r7 = r7 + r1
            r8 = r7
            r7 = 0
        L_0x00e4:
            int r9 = r5.f3593c
            if (r7 >= r9) goto L_0x0102
            int r9 = r3.size()
            if (r8 >= r9) goto L_0x0102
            java.util.List<java.lang.String> r9 = r4.f3882g
            int r10 = r8 + -1
            java.lang.Object r10 = r3.get(r10)
            com.appsamurai.storyly.data.v r10 = (com.appsamurai.storyly.data.v) r10
            java.lang.String r10 = r10.f4221a
            r9.add(r10)
            int r9 = r5.f3592b
            int r8 = r8 + r9
            int r7 = r7 + r2
            goto L_0x00e4
        L_0x0102:
            if (r21 != 0) goto L_0x0105
            goto L_0x0114
        L_0x0105:
            int r2 = r21.intValue()
            com.appsamurai.storyly.storylypresenter.b r4 = r0.f3426o
            if (r4 != 0) goto L_0x010e
            goto L_0x0114
        L_0x010e:
            android.view.Window r4 = r4.getWindow()
            if (r4 != 0) goto L_0x0116
        L_0x0114:
            r2 = r6
            goto L_0x011b
        L_0x0116:
            r4.setWindowAnimations(r2)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
        L_0x011b:
            if (r2 != 0) goto L_0x012e
            com.appsamurai.storyly.storylypresenter.b r2 = r0.f3426o
            if (r2 != 0) goto L_0x0122
            goto L_0x012e
        L_0x0122:
            android.view.Window r2 = r2.getWindow()
            if (r2 != 0) goto L_0x0129
            goto L_0x012e
        L_0x0129:
            int r4 = com.appsamurai.storyly.R.style.StorylyDialogWindowAnimation
            r2.setWindowAnimations(r4)
        L_0x012e:
            com.appsamurai.storyly.storylypresenter.b r2 = r0.f3426o
            if (r2 != 0) goto L_0x0133
            goto L_0x013d
        L_0x0133:
            S.a r4 = new S.a
            r5 = r20
            r4.<init>(r0, r5, r3, r1)
            r2.setOnShowListener(r4)
        L_0x013d:
            com.appsamurai.storyly.storylypresenter.b r1 = r0.f3426o
            if (r1 != 0) goto L_0x0142
            goto L_0x014a
        L_0x0142:
            S.b r2 = new S.b
            r2.<init>(r0)
            r1.setOnDismissListener(r2)
        L_0x014a:
            java.lang.ref.WeakReference<android.app.Activity> r1 = r0.f3425n
            if (r1 != 0) goto L_0x0150
            r1 = r6
            goto L_0x0156
        L_0x0150:
            java.lang.Object r1 = r1.get()
            android.app.Activity r1 = (android.app.Activity) r1
        L_0x0156:
            if (r1 != 0) goto L_0x0161
            com.appsamurai.storyly.util.a$a r0 = com.appsamurai.storyly.util.a.f6249a
            java.lang.String r1 = "WeakReference does not hold an Activity"
            r2 = 2
            com.appsamurai.storyly.util.a.C0055a.a(r0, r1, r6, r2)
            return
        L_0x0161:
            boolean r2 = r1.isDestroyed()
            if (r2 != 0) goto L_0x01b2
            boolean r2 = r1.isFinishing()
            if (r2 == 0) goto L_0x016e
            goto L_0x01b2
        L_0x016e:
            boolean r2 = r1 instanceof androidx.fragment.app.FragmentActivity
            if (r2 == 0) goto L_0x01a9
            com.appsamurai.storyly.storylypresenter.StorylyDialogFragment r2 = new com.appsamurai.storyly.storylypresenter.StorylyDialogFragment
            r2.<init>()
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference
            com.appsamurai.storyly.storylypresenter.b r4 = r0.f3426o
            r3.<init>(r4)
            r2.setInternalStorylyDialog$storyly_release(r3)
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference
            com.appsamurai.storyly.storylypresenter.b r4 = r0.f3426o
            if (r4 != 0) goto L_0x0188
            goto L_0x018f
        L_0x0188:
            com.appsamurai.storyly.databinding.k r4 = r4.f4752g
            if (r4 != 0) goto L_0x018d
            goto L_0x018f
        L_0x018d:
            android.widget.FrameLayout r6 = r4.f4383a
        L_0x018f:
            r3.<init>(r6)
            r2.setInternalRootView$storyly_release(r3)
            androidx.fragment.app.FragmentActivity r1 = (androidx.fragment.app.FragmentActivity) r1
            androidx.fragment.app.FragmentManager r1 = r1.getSupportFragmentManager()
            androidx.fragment.app.FragmentTransaction r1 = r1.beginTransaction()
            java.lang.String r3 = "StorylyDialogFragment"
            r2.show((androidx.fragment.app.FragmentTransaction) r1, (java.lang.String) r3)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            r0.f3427p = r2
            goto L_0x01b1
        L_0x01a9:
            com.appsamurai.storyly.storylypresenter.b r0 = r0.f3426o
            if (r0 != 0) goto L_0x01ae
            goto L_0x01b1
        L_0x01ae:
            r0.show()
        L_0x01b1:
            return
        L_0x01b2:
            com.appsamurai.storyly.util.a$a r2 = com.appsamurai.storyly.util.a.f6249a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Activity states are isDestroyed:"
            r3.<init>(r4)
            boolean r4 = r1.isDestroyed()
            r3.append(r4)
            java.lang.String r4 = " isFinishing:"
            r3.append(r4)
            boolean r1 = r1.isFinishing()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r3 = 2
            com.appsamurai.storyly.util.a.C0055a.b(r2, r1, r6, r3)
            r18.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.StorylyView.a(int, java.util.List, java.lang.Integer, boolean):void");
    }

    public static final void a(StorylyView storylyView, List list, List list2, int i3, DialogInterface dialogInterface) {
        String str;
        Intrinsics.checkNotNullParameter(storylyView, "this$0");
        Intrinsics.checkNotNullParameter(list2, "$groupItems");
        StorylyListener storylyListener = storylyView.getStorylyListener();
        if (storylyListener != null) {
            storylyListener.storylyStoryShown(storylyView);
        }
        com.appsamurai.storyly.storylypresenter.b bVar = storylyView.f3426o;
        if (bVar != null) {
            if (list == null) {
                str = storylyView.getStorylyDataManager().d().f3942a;
            } else {
                str = UUID.randomUUID().toString();
            }
            bVar.f4751f = str;
        }
        com.appsamurai.storyly.storylypresenter.b bVar2 = storylyView.f3426o;
        if (bVar2 != null) {
            bVar2.a((List<v>) CollectionsKt.toMutableList(list2));
        }
        com.appsamurai.storyly.storylypresenter.b bVar3 = storylyView.f3426o;
        if (bVar3 != null) {
            bVar3.a(Integer.valueOf(i3));
        }
        com.appsamurai.storyly.storylypresenter.b bVar4 = storylyView.f3426o;
        if (bVar4 != null) {
            bVar4.setOnShowListener((DialogInterface.OnShowListener) null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0143  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(java.lang.String r26, java.lang.String r27, com.appsamurai.storyly.PlayMode r28, boolean r29) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r27
            r3 = r28
            r4 = r29
            com.appsamurai.storyly.StorylyInit r5 = r25.getStorylyInit()
            java.lang.String r5 = r5.getStorylyId()
            boolean r5 = kotlin.text.StringsKt.isBlank(r5)
            r6 = 1
            if (r5 != 0) goto L_0x0194
            com.appsamurai.storyly.data.managers.processing.f r5 = r25.getStorylyDataManager()
            com.appsamurai.storyly.data.s r5 = r5.f3980j
            if (r5 != 0) goto L_0x0023
            goto L_0x0194
        L_0x0023:
            com.appsamurai.storyly.data.managers.processing.f r5 = r25.getStorylyDataManager()
            java.util.List r7 = r5.a()
            if (r7 != 0) goto L_0x0031
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0031:
            com.appsamurai.storyly.data.managers.storage.d r8 = r5.h()
            java.util.List r7 = r8.a(r7)
            com.appsamurai.storyly.data.managers.processing.q r5 = r5.k()
            java.util.List r5 = r5.a(r7)
            boolean r7 = r5.isEmpty()
            r8 = 0
            if (r7 == 0) goto L_0x0053
            com.appsamurai.storyly.StorylyListener r1 = r0.f3413b
            if (r1 != 0) goto L_0x004d
            goto L_0x0052
        L_0x004d:
            java.lang.String r2 = "Storyly cannot be played due to empty data"
            r1.storylyStoryShowFailed(r0, r2)
        L_0x0052:
            return r8
        L_0x0053:
            boolean r7 = r0.f3428q
            if (r7 == 0) goto L_0x0062
            com.appsamurai.storyly.StorylyListener r1 = r0.f3413b
            if (r1 != 0) goto L_0x005c
            goto L_0x0061
        L_0x005c:
            java.lang.String r2 = "Storyly is already showing"
            r1.storylyStoryShowFailed(r0, r2)
        L_0x0061:
            return r8
        L_0x0062:
            java.lang.Iterable r7 = kotlin.collections.CollectionsKt.withIndex(r5)
            java.util.Iterator r7 = r7.iterator()
        L_0x006a:
            boolean r9 = r7.hasNext()
            r10 = 0
            if (r9 == 0) goto L_0x0087
            java.lang.Object r9 = r7.next()
            r11 = r9
            kotlin.collections.IndexedValue r11 = (kotlin.collections.IndexedValue) r11
            java.lang.Object r11 = r11.getValue()
            com.appsamurai.storyly.data.v r11 = (com.appsamurai.storyly.data.v) r11
            java.lang.String r11 = r11.f4221a
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r1)
            if (r11 == 0) goto L_0x006a
            goto L_0x0088
        L_0x0087:
            r9 = r10
        L_0x0088:
            kotlin.collections.IndexedValue r9 = (kotlin.collections.IndexedValue) r9
            if (r9 != 0) goto L_0x0097
            com.appsamurai.storyly.StorylyListener r1 = r0.f3413b
            if (r1 != 0) goto L_0x0091
            goto L_0x0096
        L_0x0091:
            java.lang.String r2 = "Storyly cannot be played due to invalid/inactive story group"
            r1.storylyStoryShowFailed(r0, r2)
        L_0x0096:
            return r8
        L_0x0097:
            int r1 = r9.component1()
            java.lang.Object r7 = r9.component2()
            r13 = r7
            com.appsamurai.storyly.data.v r13 = (com.appsamurai.storyly.data.v) r13
            java.util.List<com.appsamurai.storyly.data.z> r7 = r13.f4226f
            java.lang.Iterable r7 = kotlin.collections.CollectionsKt.withIndex(r7)
            java.util.Iterator r7 = r7.iterator()
        L_0x00ac:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x00c8
            java.lang.Object r9 = r7.next()
            r11 = r9
            kotlin.collections.IndexedValue r11 = (kotlin.collections.IndexedValue) r11
            java.lang.Object r11 = r11.getValue()
            com.appsamurai.storyly.data.z r11 = (com.appsamurai.storyly.data.z) r11
            java.lang.String r11 = r11.f4302a
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r2)
            if (r11 == 0) goto L_0x00ac
            goto L_0x00c9
        L_0x00c8:
            r9 = r10
        L_0x00c9:
            kotlin.collections.IndexedValue r9 = (kotlin.collections.IndexedValue) r9
            if (r9 != 0) goto L_0x00f3
            if (r2 != 0) goto L_0x00e8
            com.appsamurai.storyly.PlayMode r2 = com.appsamurai.storyly.PlayMode.Story
            if (r3 != r2) goto L_0x00d4
            goto L_0x00e8
        L_0x00d4:
            kotlin.collections.IndexedValue r9 = new kotlin.collections.IndexedValue
            int r2 = r13.b()
            java.util.List<com.appsamurai.storyly.data.z> r7 = r13.f4226f
            int r11 = r13.b()
            java.lang.Object r7 = r7.get(r11)
            r9.<init>(r2, r7)
            goto L_0x00f3
        L_0x00e8:
            com.appsamurai.storyly.StorylyListener r1 = r0.f3413b
            if (r1 != 0) goto L_0x00ed
            goto L_0x00f2
        L_0x00ed:
            java.lang.String r2 = "Storyly cannot be played due to invalid/inactive story"
            r1.storylyStoryShowFailed(r0, r2)
        L_0x00f2:
            return r8
        L_0x00f3:
            int r2 = r9.component1()
            java.lang.Object r7 = r9.component2()
            r14 = r7
            com.appsamurai.storyly.data.z r14 = (com.appsamurai.storyly.data.z) r14
            int[] r7 = com.appsamurai.storyly.StorylyView.c.$EnumSwitchMapping$1
            int r3 = r28.ordinal()
            r3 = r7[r3]
            if (r3 == r6) goto L_0x0134
            r7 = 2
            if (r3 == r7) goto L_0x0119
            r7 = 3
            if (r3 == r7) goto L_0x0111
            r2 = r5
        L_0x010f:
            r1 = r8
            goto L_0x013f
        L_0x0111:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r13.f4238r = r2
            r2 = r5
            goto L_0x013f
        L_0x0119:
            com.appsamurai.storyly.data.z[] r1 = new com.appsamurai.storyly.data.z[]{r14}
            java.util.List r1 = kotlin.collections.CollectionsKt.mutableListOf(r1)
            java.lang.String r2 = "<set-?>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r13.f4226f = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)
            r13.f4238r = r1
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r13)
        L_0x0132:
            r2 = r1
            goto L_0x010f
        L_0x0134:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r13.f4238r = r1
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r13)
            goto L_0x0132
        L_0x013f:
            r0.f3423l = r10
            if (r4 != 0) goto L_0x018a
            com.appsamurai.storyly.StorylyInit r3 = r25.getStorylyInit()
            com.appsamurai.storyly.config.StorylyConfig r3 = r3.getConfig()
            kotlinx.serialization.json.JsonObject r17 = com.appsamurai.storyly.analytics.f.a(r2, r13, r3)
            android.net.Uri r3 = r0.f3422k
            if (r3 == 0) goto L_0x0170
            com.appsamurai.storyly.analytics.e r11 = r25.getStorylyTracker()
            com.appsamurai.storyly.analytics.a r12 = com.appsamurai.storyly.analytics.a.DeepLinkOpened
            r21 = 0
            r22 = 0
            r15 = 0
            r16 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r23 = 0
            r24 = 4056(0xfd8, float:5.684E-42)
            com.appsamurai.storyly.analytics.e.a(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            r0.f3422k = r10
            goto L_0x018a
        L_0x0170:
            com.appsamurai.storyly.analytics.e r11 = r25.getStorylyTracker()
            com.appsamurai.storyly.analytics.a r12 = com.appsamurai.storyly.analytics.a.ProgrammaticallySelected
            r21 = 0
            r22 = 0
            r15 = 0
            r16 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r23 = 0
            r24 = 4056(0xfd8, float:5.684E-42)
            com.appsamurai.storyly.analytics.e.a(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
        L_0x018a:
            r5 = 12
            r3 = 0
            r4 = 0
            r0 = r25
            a(r0, r1, r2, r3, r4, r5)
            return r6
        L_0x0194:
            com.appsamurai.storyly.StorylyView$a r5 = new com.appsamurai.storyly.StorylyView$a
            r5.<init>(r1, r2, r3, r4)
            r0.f3423l = r5
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.StorylyView.a(java.lang.String, java.lang.String, com.appsamurai.storyly.PlayMode, boolean):boolean");
    }
}
