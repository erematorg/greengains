package com.google.mlkit.vision.barcode;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.interfaces.Detector;
import java.util.List;

public interface BarcodeScanner extends Detector<List<Barcode>>, OptionalModuleApi {
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void close();

    @NonNull
    Task<List<Barcode>> process(@NonNull MlImage mlImage);

    @NonNull
    Task<List<Barcode>> process(@NonNull InputImage inputImage);
}
