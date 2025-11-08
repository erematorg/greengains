package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;

public class zzer extends IOException {
    public zzer(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    public zzer(String str) {
        super(str);
    }
}
