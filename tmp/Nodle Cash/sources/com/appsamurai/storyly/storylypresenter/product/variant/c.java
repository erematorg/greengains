package com.appsamurai.storyly.storylypresenter.product.variant;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.config.StorylyConfig;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class c extends RecyclerView.Adapter<a> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f5275a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public String f5276b = "";

    /* renamed from: c  reason: collision with root package name */
    public int f5277c;

    public static final class a extends RecyclerView.ViewHolder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5278a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(@NotNull View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.f5278a = (TextView) view;
        }
    }

    public c(@NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5275a = storylyConfig;
    }

    public int getItemCount() {
        return 1;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i3) {
        a aVar = (a) viewHolder;
        Intrinsics.checkNotNullParameter(aVar, "holder");
        String str = this.f5276b;
        aVar.getClass();
        Intrinsics.checkNotNullParameter(str, "headerText");
        TextView textView = aVar.f5278a;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i3) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        TextView textView = new TextView(viewGroup.getContext());
        textView.setTextSize(0, (float) (((double) this.f5277c) * 0.35d));
        textView.setGravity(17);
        textView.setAllCaps(true);
        textView.setTypeface(this.f5275a.getStory$storyly_release().getInteractiveTypeface$storyly_release());
        int i4 = this.f5277c;
        Class cls = Integer.TYPE;
        ViewGroup.LayoutParams newInstance = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, Integer.valueOf(i4)});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        Unit unit = Unit.INSTANCE;
        textView.setLayoutParams(newInstance);
        return new a(textView);
    }
}
