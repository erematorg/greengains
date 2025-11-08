package com.phrase.android.sdk.inject;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.annotation.MenuRes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\r"}, d2 = {"Lcom/phrase/android/sdk/inject/PhraseMenuInflater;", "Landroid/view/MenuInflater;", "", "menuRes", "Landroid/view/Menu;", "menu", "", "inflate", "Landroid/content/Context;", "context", "baseInflater", "<init>", "(Landroid/content/Context;Landroid/view/MenuInflater;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhraseMenuInflater extends MenuInflater {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final MenuInflater f7263a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final PhraseMenuParser f7264b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PhraseMenuInflater(@NotNull Context context, @NotNull MenuInflater menuInflater) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(menuInflater, "baseInflater");
        this.f7263a = menuInflater;
        this.f7264b = new PhraseMenuParser(context);
    }

    public void inflate(@MenuRes int i3, @NotNull Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.f7263a.inflate(i3, menu);
        this.f7264b.parseAndApply(i3, menu);
    }
}
