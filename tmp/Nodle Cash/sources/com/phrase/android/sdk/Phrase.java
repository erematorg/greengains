package com.phrase.android.sdk;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.PhraseAppCompatDelegate;
import androidx.appcompat.app.PhraseBaseAppCompatDelegate;
import com.phrase.android.sdk.inject.PhraseContextWrapper;
import com.phrase.android.sdk.inject.PhraseReflection;
import com.phrase.android.sdk.repo.PhraseApiKt;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b0\u00101J*\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0007J \u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007J\u0012\u0010\n\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0007J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0004H\u0007J\b\u0010\u000e\u001a\u00020\u0004H\u0007J\u0014\u0010\u0011\u001a\u00020\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007J\b\u0010\u0011\u001a\u00020\bH\u0007J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0012H\u0007J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0004H\u0007J\u0018\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0007J\u0010\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\b\u0010\u001e\u001a\u00020\u001dH\u0007J\u0011\u0010\"\u001a\u0004\u0018\u00010\u001fH\u0000¢\u0006\u0004\b \u0010!R$\u0010(\u001a\u0004\u0018\u00010\u001f8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010!\"\u0004\b&\u0010'R$\u0010/\u001a\u0004\u0018\u00010\u00048\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u00062"}, d2 = {"Lcom/phrase/android/sdk/Phrase;", "", "Landroid/content/Context;", "context", "", "distribution", "environment", "localeCode", "", "setup", "setLocaleCode", "getLocaleCode", "customHost", "setHost", "getHost", "Lcom/phrase/android/sdk/TranslationsSyncCallback;", "callback", "updateTranslations", "", "milliseconds", "setTimeout", "version", "setAppVersion", "Landroidx/appcompat/app/AppCompatActivity;", "activity", "Landroidx/appcompat/app/AppCompatDelegate;", "superDelegate", "getDelegate", "wrapApplicationContext", "Lcom/phrase/android/sdk/PhraseTranslations;", "getTranslations", "Lcom/phrase/android/sdk/TranslateRepository;", "getRepository$sdk_release", "()Lcom/phrase/android/sdk/TranslateRepository;", "getRepository", "c", "Lcom/phrase/android/sdk/TranslateRepository;", "getTranslateRepository$sdk_release", "setTranslateRepository$sdk_release", "(Lcom/phrase/android/sdk/TranslateRepository;)V", "translateRepository", "d", "Ljava/lang/String;", "getCustomLocaleCode$sdk_release", "()Ljava/lang/String;", "setCustomLocaleCode$sdk_release", "(Ljava/lang/String;)V", "customLocaleCode", "<init>", "()V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class Phrase {
    @NotNull
    public static final Phrase INSTANCE = new Phrase();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static PhraseImpl f7239a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public static final WeakHashMap<AppCompatActivity, WeakReference<PhraseBaseAppCompatDelegate>> f7240b = new WeakHashMap<>();
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public static TranslateRepository f7241c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public static String f7242d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public static String f7243e = PhraseApiKt.BASE_URL;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public static final PhraseTranslationsImpl f7244f = new PhraseTranslationsImpl();

    public static final class a extends Lambda implements Function1<Context, Context> {

        /* renamed from: a  reason: collision with root package name */
        public static final a f7245a = new a();

        public a() {
            super(1);
        }

        public final Object invoke(Object obj) {
            Context context = (Context) obj;
            Intrinsics.checkNotNullParameter(context, "context");
            return Phrase.access$wrapInternal(Phrase.INSTANCE, context);
        }
    }

    private Phrase() {
    }

    public static final Context access$wrapInternal(Phrase phrase, Context context) {
        phrase.getClass();
        return context instanceof PhraseContextWrapper ? context : new PhraseContextWrapper(context);
    }

    @JvmStatic
    @NotNull
    public static final AppCompatDelegate getDelegate(@NotNull AppCompatActivity appCompatActivity, @NotNull AppCompatDelegate appCompatDelegate) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "activity");
        Intrinsics.checkNotNullParameter(appCompatDelegate, "superDelegate");
        WeakHashMap<AppCompatActivity, WeakReference<PhraseBaseAppCompatDelegate>> weakHashMap = f7240b;
        WeakReference weakReference = weakHashMap.get(appCompatActivity);
        PhraseBaseAppCompatDelegate phraseBaseAppCompatDelegate = weakReference != null ? (PhraseBaseAppCompatDelegate) weakReference.get() : null;
        if (phraseBaseAppCompatDelegate != null) {
            return phraseBaseAppCompatDelegate;
        }
        PhraseAppCompatDelegate phraseAppCompatDelegate = new PhraseAppCompatDelegate(appCompatDelegate, appCompatActivity, a.f7245a);
        weakHashMap.put(appCompatActivity, new WeakReference(phraseAppCompatDelegate));
        return phraseAppCompatDelegate;
    }

    @JvmStatic
    @NotNull
    public static final String getHost() {
        return f7243e;
    }

    @JvmStatic
    @Nullable
    public static final String getLocaleCode() {
        return f7242d;
    }

    @JvmStatic
    @NotNull
    public static final PhraseTranslations getTranslations() {
        return f7244f;
    }

    @JvmStatic
    public static final void setAppVersion(@NotNull String str) {
        Unit unit;
        Intrinsics.checkNotNullParameter(str, "version");
        PhraseImpl phraseImpl = f7239a;
        if (phraseImpl != null) {
            phraseImpl.setAppVersion$sdk_release(str);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            UtilsKt.phraseLog("Phrase has not been initialized");
        }
    }

    @JvmStatic
    public static final void setHost(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "customHost");
        f7243e = str;
    }

    @JvmStatic
    public static final void setLocaleCode(@Nullable String str) {
        Unit unit;
        f7242d = str;
        PhraseImpl phraseImpl = f7239a;
        if (phraseImpl != null) {
            phraseImpl.loadDiskCache$sdk_release();
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            UtilsKt.phraseLog("Phrase has not been initialized");
        }
    }

    @JvmStatic
    public static final void setTimeout(int i3) {
        Unit unit;
        PhraseImpl phraseImpl = f7239a;
        if (phraseImpl != null) {
            phraseImpl.setTimeout$sdk_release(i3);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            UtilsKt.phraseLog("Phrase has not been initialized");
        }
    }

    @JvmStatic
    public static final void setup(@NotNull Context context, @NotNull String str, @NotNull String str2, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "distribution");
        Intrinsics.checkNotNullParameter(str2, "environment");
        if (str.length() == 0 || str2.length() == 0) {
            UtilsKt.phraseLog("Distribution and environment cannot be empty");
        }
        if (f7239a != null) {
            UtilsKt.phraseLog("Phrase has been already initialized");
            return;
        }
        f7242d = str3;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        f7239a = new PhraseImpl(applicationContext, str, str2);
    }

    @JvmStatic
    public static final void updateTranslations(@Nullable TranslationsSyncCallback translationsSyncCallback) {
        Unit unit;
        PhraseImpl phraseImpl = f7239a;
        if (phraseImpl != null) {
            phraseImpl.updateTranslations$sdk_release(translationsSyncCallback);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            UtilsKt.phraseLog("Phrase has not been initialized");
        }
    }

    public static /* synthetic */ void updateTranslations$default(TranslationsSyncCallback translationsSyncCallback, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            translationsSyncCallback = null;
        }
        updateTranslations(translationsSyncCallback);
    }

    @JvmStatic
    @NotNull
    public static final Context wrapApplicationContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PhraseReflection.INSTANCE.injectResources$sdk_release(context);
        return context;
    }

    @Nullable
    public final String getCustomLocaleCode$sdk_release() {
        return f7242d;
    }

    @Nullable
    public final TranslateRepository getRepository$sdk_release() {
        return f7241c;
    }

    @Nullable
    public final TranslateRepository getTranslateRepository$sdk_release() {
        return f7241c;
    }

    public final void setCustomLocaleCode$sdk_release(@Nullable String str) {
        f7242d = str;
    }

    public final void setTranslateRepository$sdk_release(@Nullable TranslateRepository translateRepository) {
        f7241c = translateRepository;
    }

    @JvmStatic
    public static final void updateTranslations() {
        updateTranslations((TranslationsSyncCallback) null);
    }

    @JvmStatic
    public static final void setup(@NotNull Context context, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "distribution");
        Intrinsics.checkNotNullParameter(str2, "environment");
        setup(context, str, str2, (String) null);
    }
}
