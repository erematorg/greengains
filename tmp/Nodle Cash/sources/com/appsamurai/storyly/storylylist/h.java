package com.appsamurai.storyly.storylylist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.config.styling.group.StoryGroupView;
import com.appsamurai.storyly.config.styling.group.StoryGroupViewFactory;
import com.appsamurai.storyly.data.v;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class h extends FrameLayout {

    /* renamed from: d  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f4713d = {androidx.compose.ui.autofill.a.m(h.class, "storylyGroupItem", "getStorylyGroupItem()Lcom/appsamurai/storyly/data/StorylyGroupItem;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f4714a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public StoryGroupView f4715b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final ReadWriteProperty f4716c = new a((Object) null, (Object) null, this);

    public static final class a extends ObservableProperty<v> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ h f4717a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Object obj, Object obj2, h hVar) {
            super(null);
            this.f4717a = hVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, v vVar, v vVar2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            v vVar3 = vVar2;
            v vVar4 = vVar;
            StoryGroupView storyGroupView$storyly_release = this.f4717a.getStoryGroupView$storyly_release();
            if (storyGroupView$storyly_release != null) {
                v storylyGroupItem = this.f4717a.getStorylyGroupItem();
                storyGroupView$storyly_release.populateView(storylyGroupItem == null ? null : storylyGroupItem.d());
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public h(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3, @NotNull StorylyConfig storylyConfig) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f4714a = storylyConfig;
        Delegates delegates = Delegates.INSTANCE;
        StoryGroupViewFactory groupViewFactory$storyly_release = storylyConfig.getGroup$storyly_release().getGroupViewFactory$storyly_release();
        StoryGroupView createView = (groupViewFactory$storyly_release == null ? new c(context, storylyConfig) : groupViewFactory$storyly_release).createView();
        this.f4715b = createView;
        addView(createView, new FrameLayout.LayoutParams(-2, -2));
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.f4714a;
    }

    @Nullable
    public final StoryGroupView getStoryGroupView$storyly_release() {
        return this.f4715b;
    }

    @Nullable
    public final v getStorylyGroupItem() {
        return (v) this.f4716c.getValue(this, f4713d[0]);
    }

    public final void setStoryGroupView$storyly_release(@Nullable StoryGroupView storyGroupView) {
        this.f4715b = storyGroupView;
    }

    public final void setStorylyGroupItem(@Nullable v vVar) {
        this.f4716c.setValue(this, f4713d[0], vVar);
    }
}
