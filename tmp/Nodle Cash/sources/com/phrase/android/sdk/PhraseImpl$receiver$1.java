package com.phrase.android.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Locale;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"com/phrase/android/sdk/PhraseImpl$receiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "sdk_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PhraseImpl$receiver$1 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PhraseImpl f7256a;

    public PhraseImpl$receiver$1(PhraseImpl phraseImpl) {
        this.f7256a = phraseImpl;
    }

    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        UtilsKt.phraseLog("Locale changed: " + Locale.getDefault());
        this.f7256a.loadDiskCache$sdk_release();
        PhraseImpl.updateTranslations$sdk_release$default(this.f7256a, (TranslationsSyncCallback) null, 1, (Object) null);
    }
}
