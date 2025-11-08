package com.google.android.recaptcha;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/google/android/recaptcha/RecaptchaDefinitions;", "", "()V", "DEFAULT_TIMEOUT_EXECUTE", "", "DEFAULT_TIMEOUT_INIT", "MIN_TIMEOUT_EXECUTE", "MIN_TIMEOUT_INIT", "java.com.google.android.libraries.abuse.recaptcha.enterprise.public_public"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RecaptchaDefinitions {
    public static final long DEFAULT_TIMEOUT_EXECUTE = 10000;
    public static final long DEFAULT_TIMEOUT_INIT = 10000;
    @NotNull
    public static final RecaptchaDefinitions INSTANCE = new RecaptchaDefinitions();
    public static final long MIN_TIMEOUT_EXECUTE = 5000;
    public static final long MIN_TIMEOUT_INIT = 5000;

    private RecaptchaDefinitions() {
    }
}
