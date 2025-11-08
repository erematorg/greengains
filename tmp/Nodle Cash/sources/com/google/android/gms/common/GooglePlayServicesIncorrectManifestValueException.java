package com.google.android.gms.common;

import androidx.camera.core.impl.i;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class GooglePlayServicesIncorrectManifestValueException extends GooglePlayServicesManifestException {
    public GooglePlayServicesIncorrectManifestValueException(int i3) {
        super(i3, i.a(GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE, i3, "The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ", " but found ", ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />"));
    }
}
