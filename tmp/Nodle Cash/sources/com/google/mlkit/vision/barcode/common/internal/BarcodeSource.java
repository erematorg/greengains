package com.google.mlkit.vision.barcode.common.internal;

import android.graphics.Point;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.vision.barcode.common.Barcode;

@KeepForSdk
public interface BarcodeSource {
    @KeepForSdk
    @Nullable
    Rect getBoundingBox();

    @KeepForSdk
    @Nullable
    Barcode.CalendarEvent getCalendarEvent();

    @KeepForSdk
    @Nullable
    Barcode.ContactInfo getContactInfo();

    @KeepForSdk
    @Nullable
    Point[] getCornerPoints();

    @KeepForSdk
    @Nullable
    String getDisplayValue();

    @KeepForSdk
    @Nullable
    Barcode.DriverLicense getDriverLicense();

    @KeepForSdk
    @Nullable
    Barcode.Email getEmail();

    @KeepForSdk
    int getFormat();

    @KeepForSdk
    @Nullable
    Barcode.GeoPoint getGeoPoint();

    @KeepForSdk
    @Nullable
    Barcode.Phone getPhone();

    @KeepForSdk
    @Nullable
    byte[] getRawBytes();

    @KeepForSdk
    @Nullable
    String getRawValue();

    @KeepForSdk
    @Nullable
    Barcode.Sms getSms();

    @KeepForSdk
    @Nullable
    Barcode.UrlBookmark getUrl();

    @KeepForSdk
    int getValueType();

    @KeepForSdk
    @Nullable
    Barcode.WiFi getWifi();
}
