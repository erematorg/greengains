package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import jnr.ffi.provider.jffi.JNINativeInterface;

final class zzag implements zzel {
    static final zzel zza = new zzag();

    private zzag() {
    }

    public final boolean zza(int i3) {
        if (i3 == 129 || i3 == 161 || i3 == 209 || i3 == 2705 || i3 == 20753 || i3 == 20769 || i3 == 215 || i3 == 216 || i3 == 1297 || i3 == 1298) {
            return true;
        }
        switch (i3) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return true;
            default:
                switch (i3) {
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        return true;
                    default:
                        switch (i3) {
                            case 81:
                            case 82:
                            case 83:
                            case 84:
                            case 85:
                                return true;
                            default:
                                switch (i3) {
                                    case 163:
                                    case 164:
                                    case 165:
                                    case 166:
                                    case 167:
                                    case 168:
                                    case 169:
                                        return true;
                                    default:
                                        switch (i3) {
                                            case 211:
                                            case JNINativeInterface.SetLongArrayRegion /*212*/:
                                            case JNINativeInterface.SetFloatArrayRegion /*213*/:
                                                return true;
                                            default:
                                                return false;
                                        }
                                }
                        }
                }
        }
    }
}
