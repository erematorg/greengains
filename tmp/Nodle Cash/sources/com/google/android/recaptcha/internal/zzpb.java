package com.google.android.recaptcha.internal;

public enum zzpb implements zziv {
    JS_CODE_UNSPECIFIED(0),
    JS_CODE_SUCCESS(1),
    JS_NETWORK_ERROR(2),
    JS_INTERNAL_ERROR(3),
    JS_INVALID_SITE_KEY(4),
    JS_INVALID_SITE_KEY_TYPE(5),
    JS_3P_APP_PACKAGE_NAME_NOT_ALLOWED(6),
    JS_INVALID_ACTION(7),
    JS_THIRD_PARTY_APP_PACKAGE_NAME_NOT_ALLOWED(8),
    JS_PROGRAM_ERROR(9),
    UNRECOGNIZED(-1);
    
    private static final zziw zzl = null;
    private final int zzn;

    static {
        zzl = new zzpa();
    }

    private zzpb(int i3) {
        this.zzn = i3;
    }

    public static zzpb zzb(int i3) {
        switch (i3) {
            case 0:
                return JS_CODE_UNSPECIFIED;
            case 1:
                return JS_CODE_SUCCESS;
            case 2:
                return JS_NETWORK_ERROR;
            case 3:
                return JS_INTERNAL_ERROR;
            case 4:
                return JS_INVALID_SITE_KEY;
            case 5:
                return JS_INVALID_SITE_KEY_TYPE;
            case 6:
                return JS_3P_APP_PACKAGE_NAME_NOT_ALLOWED;
            case 7:
                return JS_INVALID_ACTION;
            case 8:
                return JS_THIRD_PARTY_APP_PACKAGE_NAME_NOT_ALLOWED;
            case 9:
                return JS_PROGRAM_ERROR;
            default:
                return null;
        }
    }

    public final String toString() {
        return Integer.toString(zza());
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzn;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
