package com.google.android.recaptcha;

import A.a;
import androidx.annotation.NonNull;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/google/android/recaptcha/RecaptchaAction;", "", "action", "", "(Ljava/lang/String;)V", "getAction", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "java.com.google.android.libraries.abuse.recaptcha.enterprise.public_public"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RecaptchaAction {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    @JvmField
    public static final RecaptchaAction LOGIN = new RecaptchaAction(FirebaseAnalytics.Event.LOGIN);
    @NotNull
    @JvmField
    public static final RecaptchaAction SIGNUP = new RecaptchaAction("signup");
    @NotNull
    private final String action;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/google/android/recaptcha/RecaptchaAction$Companion;", "", "()V", "LOGIN", "Lcom/google/android/recaptcha/RecaptchaAction;", "SIGNUP", "custom", "customAction", "", "java.com.google.android.libraries.abuse.recaptcha.enterprise.public_public"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final RecaptchaAction custom(@NonNull String str) {
            return new RecaptchaAction(str, (DefaultConstructorMarker) null);
        }

        public /* synthetic */ Companion(@NonNull DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private RecaptchaAction(String str) {
        this.action = str;
    }

    @NonNull
    public static /* synthetic */ RecaptchaAction copy$default(@NonNull RecaptchaAction recaptchaAction, @NonNull String str, int i3, @NonNull Object obj) {
        if ((i3 & 1) != 0) {
            str = recaptchaAction.action;
        }
        return recaptchaAction.copy(str);
    }

    @JvmStatic
    @NotNull
    public static final RecaptchaAction custom(@NonNull String str) {
        return Companion.custom(str);
    }

    @NotNull
    public final String component1() {
        return this.action;
    }

    @NotNull
    public final RecaptchaAction copy(@NonNull String str) {
        return new RecaptchaAction(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RecaptchaAction) && Intrinsics.areEqual((Object) this.action, (Object) ((RecaptchaAction) obj).action);
    }

    @NotNull
    public final String getAction() {
        return this.action;
    }

    public int hashCode() {
        return this.action.hashCode();
    }

    @NotNull
    public String toString() {
        return a.n(new StringBuilder("RecaptchaAction(action="), this.action, ")");
    }

    public /* synthetic */ RecaptchaAction(@NonNull String str, @NonNull DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
