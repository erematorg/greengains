package com.appsamurai.storyly.storylypresenter.product.variant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.managers.product.STRProductVariant;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@SuppressLint({"ViewConstructor"})
public final class e extends LinearLayout {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f5284a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Function1<STRProductVariant, Unit> f5285b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public List<d> f5286c = new ArrayList();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(@NotNull Context context, @NotNull StorylyConfig storylyConfig, @NotNull Function1<? super STRProductVariant, Unit> function1) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(function1, "onVariantSelection");
        this.f5284a = storylyConfig;
        this.f5285b = function1;
        setOrientation(1);
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.f5284a;
    }

    @NotNull
    public final Function1<STRProductVariant, Unit> getOnVariantSelection() {
        return this.f5285b;
    }

    public final void setSelectionState(boolean z2) {
        for (d clickEnabled$storyly_release : this.f5286c) {
            clickEnabled$storyly_release.setClickEnabled$storyly_release(z2);
        }
    }
}
