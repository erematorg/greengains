package com.phrase.android.sdk.inject;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"Lcom/phrase/android/sdk/inject/PhraseContextWrapper;", "Landroid/content/ContextWrapper;", "Landroid/content/res/Resources;", "getResources", "Landroid/content/Context;", "base", "<init>", "(Landroid/content/Context;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhraseContextWrapper extends ContextWrapper {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Lazy f7261a = LazyKt.lazy(new a(this));

    public static final class a extends Lambda implements Function0<PhraseResources> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ PhraseContextWrapper f7262a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(PhraseContextWrapper phraseContextWrapper) {
            super(0);
            this.f7262a = phraseContextWrapper;
        }

        public final Object invoke() {
            Resources access$getResources$s54116356 = PhraseContextWrapper.super.getResources();
            if (access$getResources$s54116356 instanceof PhraseResources) {
                return (PhraseResources) access$getResources$s54116356;
            }
            Intrinsics.checkNotNullExpressionValue(access$getResources$s54116356, "s");
            return new PhraseResources(access$getResources$s54116356);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PhraseContextWrapper(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, TtmlNode.RUBY_BASE);
    }

    @NotNull
    public Resources getResources() {
        return (Resources) this.f7261a.getValue();
    }
}
