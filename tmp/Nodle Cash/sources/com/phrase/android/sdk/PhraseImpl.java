package com.phrase.android.sdk;

import K0.a;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.phrase.android.sdk.repo.PhraseApi;
import com.phrase.android.sdk.repo.PhraseApiResult;
import com.phrase.android.sdk.repo.PhraseData;
import com.phrase.android.sdk.repo.PhraseDiskCache;
import com.phrase.android.sdk.repo.PhrasePrefs;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.signature.SignatureVisitor;

@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0017\u001a\u00020\u0010\u0012\u0006\u0010\u0018\u001a\u00020\u0010¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u0005\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\u001b\u0010\n\u001a\u00020\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000f\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Lcom/phrase/android/sdk/PhraseImpl;", "", "", "loadDiskCache$sdk_release", "()V", "loadDiskCache", "Lcom/phrase/android/sdk/TranslationsSyncCallback;", "callback", "updateTranslations$sdk_release", "(Lcom/phrase/android/sdk/TranslationsSyncCallback;)V", "updateTranslations", "", "milliseconds", "setTimeout$sdk_release", "(I)V", "setTimeout", "", "version", "setAppVersion$sdk_release", "(Ljava/lang/String;)V", "setAppVersion", "Landroid/content/Context;", "context", "distribution", "environment", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhraseImpl {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f7246a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f7247b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Handler f7248c = new Handler(Looper.getMainLooper());

    /* renamed from: d  reason: collision with root package name */
    public final ExecutorService f7249d = Executors.newSingleThreadExecutor();
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final PhrasePrefs f7250e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final PhraseDiskCache f7251f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final PhraseApi f7252g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public final String f7253h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public String f7254i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public Integer f7255j;

    public PhraseImpl(@NotNull Context context, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "distribution");
        Intrinsics.checkNotNullParameter(str2, "environment");
        this.f7246a = str;
        this.f7247b = str2;
        this.f7250e = new PhrasePrefs(context);
        PhraseDiskCache phraseDiskCache = new PhraseDiskCache(context);
        this.f7251f = phraseDiskCache;
        this.f7252g = new PhraseApi(str, str2, phraseDiskCache);
        this.f7253h = UtilsKt.getAppVersion(context);
        loadDiskCache$sdk_release();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        context.registerReceiver(new PhraseImpl$receiver$1(this), intentFilter);
    }

    public static final void a(PhraseImpl phraseImpl, LocaleBundle localeBundle, String str, TranslationsSyncCallback translationsSyncCallback) {
        PhraseImpl phraseImpl2 = phraseImpl;
        String str2 = str;
        Intrinsics.checkNotNullParameter(phraseImpl, "this$0");
        LocaleBundle localeBundle2 = localeBundle;
        Intrinsics.checkNotNullParameter(localeBundle, "$localeBundle");
        Intrinsics.checkNotNullParameter(str2, "$localeHash");
        PhraseApi phraseApi = phraseImpl2.f7252g;
        String localeCode = localeBundle.getLocaleCode();
        String uniqueID = phraseImpl2.f7250e.getUniqueID();
        String lastUpdate = phraseImpl2.f7250e.getLastUpdate();
        String version = phraseImpl2.f7250e.getVersion(str2);
        String str3 = phraseImpl2.f7254i;
        if (str3 == null) {
            str3 = phraseImpl2.f7253h;
        }
        phraseImpl2.f7248c.post(new a(2, phraseApi.fetchLocale$sdk_release(localeCode, str, uniqueID, BuildConfig.PHRASE_LIB_VERSION, lastUpdate, version, str3, phraseImpl2.f7255j), phraseImpl, str, translationsSyncCallback));
    }

    public static /* synthetic */ void updateTranslations$sdk_release$default(PhraseImpl phraseImpl, TranslationsSyncCallback translationsSyncCallback, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            translationsSyncCallback = null;
        }
        phraseImpl.updateTranslations$sdk_release(translationsSyncCallback);
    }

    public final void loadDiskCache$sdk_release() {
        Phrase phrase = Phrase.INSTANCE;
        LocaleBundle localeBundle = new LocaleBundle(phrase.getCustomLocaleCode$sdk_release());
        String localeCode = localeBundle.getLocaleCode();
        PhraseData orNull = this.f7251f.getOrNull(UtilsKt.sha512(this.f7246a + SignatureVisitor.SUPER + this.f7247b + SignatureVisitor.SUPER + localeCode));
        if (orNull != null) {
            phrase.setTranslateRepository$sdk_release(new TranslateRepositoryImpl(orNull, localeBundle));
        }
    }

    public final void setAppVersion$sdk_release(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "version");
        this.f7254i = str;
    }

    public final void setTimeout$sdk_release(int i3) {
        this.f7255j = Integer.valueOf(i3);
    }

    public final void updateTranslations$sdk_release(@Nullable TranslationsSyncCallback translationsSyncCallback) {
        LocaleBundle localeBundle = new LocaleBundle(Phrase.INSTANCE.getCustomLocaleCode$sdk_release());
        String localeCode = localeBundle.getLocaleCode();
        this.f7249d.submit(new a(1, this, localeBundle, UtilsKt.sha512(this.f7246a + SignatureVisitor.SUPER + this.f7247b + SignatureVisitor.SUPER + localeCode), translationsSyncCallback));
    }

    public static final void a(PhraseApiResult phraseApiResult, PhraseImpl phraseImpl, String str, TranslationsSyncCallback translationsSyncCallback) {
        Intrinsics.checkNotNullParameter(phraseApiResult, "$apiResult");
        Intrinsics.checkNotNullParameter(phraseImpl, "this$0");
        Intrinsics.checkNotNullParameter(str, "$localeHash");
        if (phraseApiResult instanceof PhraseApiResult.Data) {
            phraseImpl.f7250e.setVersion(str, ((PhraseApiResult.Data) phraseApiResult).getVersion());
            phraseImpl.f7250e.setLastUpdate();
            if (translationsSyncCallback != null) {
                translationsSyncCallback.onSuccess(true);
            }
        } else if (phraseApiResult instanceof PhraseApiResult.NotModified) {
            if (translationsSyncCallback != null) {
                translationsSyncCallback.onSuccess(false);
            }
        } else if ((phraseApiResult instanceof PhraseApiResult.Error) && translationsSyncCallback != null) {
            translationsSyncCallback.onFailure();
        }
    }
}
