package com.google.android.recaptcha.internal;

public enum zzne implements zziv {
    UNKNOWN(0),
    INIT_NATIVE(1),
    INIT_NETWORK(2),
    INIT_NETWORK_MRI_ACTION(18),
    INIT_DOWNLOAD_JS(19),
    INIT_JS(3),
    INIT_TOTAL(4),
    VALIDATE_INPUT(20),
    DOWNLOAD_JS(21),
    SAVE_CACHE_JS(22),
    LOAD_CACHE_JS(23),
    LOAD_WEBVIEW(24),
    EXECUTE_NATIVE(5),
    EXECUTE_JS(6),
    EXECUTE_TOTAL(7),
    COLLECT_SIGNALS(25),
    FETCH_TOKEN(26),
    POST_EXECUTE(27),
    CHALLENGE_ACCOUNT_NATIVE(8),
    CHALLENGE_ACCOUNT_JS(9),
    CHALLENGE_ACCOUNT_TOTAL(10),
    VERIFY_PIN_NATIVE(11),
    VERIFY_PIN_JS(12),
    VERIFY_PIN_TOTAL(13),
    RUN_PROGRAM(14),
    FETCH_ALLOWLIST(15),
    JS_LOAD(16),
    WEB_VIEW_RELOAD_JS(17),
    UNRECOGNIZED(-1);
    
    private static final zziw zzD = null;
    private final int zzF;

    static {
        zzD = new zznd();
    }

    private zzne(int i3) {
        this.zzF = i3;
    }

    public final String toString() {
        return Integer.toString(zza());
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzF;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
