package com.google.android.libraries.barhopper;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzer;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import java.io.Closeable;
import java.nio.ByteBuffer;

public class BarhopperV3 implements Closeable {
    private static final long NULLPTR = 0;
    private static final String TAG = "BarhopperV3";
    private long nativePointer;

    public BarhopperV3() {
        System.loadLibrary("barhopper_v3");
    }

    private native void closeNative(long j2);

    private native long createNative();

    private native long createNativeWithClientOptions(byte[] bArr);

    private native byte[] recognizeBitmapNative(long j2, Bitmap bitmap, RecognitionOptions recognitionOptions);

    private native byte[] recognizeBufferNative(long j2, int i3, int i4, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    private native byte[] recognizeNative(long j2, int i3, int i4, byte[] bArr, RecognitionOptions recognitionOptions);

    private native byte[] recognizeStridedBufferNative(long j2, int i3, int i4, int i5, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    private native byte[] recognizeStridedNative(long j2, int i3, int i4, int i5, byte[] bArr, RecognitionOptions recognitionOptions);

    private static BarhopperProto$BarhopperResponse toProto(byte[] bArr) {
        bArr.getClass();
        try {
            return BarhopperProto$BarhopperResponse.zzb(bArr, zzds.zza());
        } catch (zzer e3) {
            throw new IllegalStateException("Received unexpected BarhopperResponse buffer: {0}", e3);
        }
    }

    public void close() {
        long j2 = this.nativePointer;
        if (j2 != 0) {
            closeNative(j2);
            this.nativePointer = 0;
        }
    }

    public void create() {
        if (this.nativePointer != 0) {
            Log.w(TAG, "Native pointer already exists.");
            return;
        }
        long createNative = createNative();
        this.nativePointer = createNative;
        if (createNative == 0) {
            throw new IllegalStateException("Failed to create native pointer.");
        }
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i3, int i4, int i5, @NonNull ByteBuffer byteBuffer, @NonNull RecognitionOptions recognitionOptions) {
        long j2 = this.nativePointer;
        if (j2 != 0) {
            return toProto(recognizeStridedBufferNative(j2, i3, i4, i5, byteBuffer, recognitionOptions));
        }
        throw new IllegalStateException("Native pointer does not exist.");
    }

    public void create(@NonNull BarhopperV3Options barhopperV3Options) {
        if (this.nativePointer != 0) {
            Log.w(TAG, "Native pointer already exists.");
            return;
        }
        long createNativeWithClientOptions = createNativeWithClientOptions(barhopperV3Options.zzD());
        this.nativePointer = createNativeWithClientOptions;
        if (createNativeWithClientOptions == 0) {
            throw new IllegalArgumentException("Failed to create native pointer with client options.");
        }
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i3, int i4, int i5, @NonNull byte[] bArr, @NonNull RecognitionOptions recognitionOptions) {
        long j2 = this.nativePointer;
        if (j2 != 0) {
            return toProto(recognizeStridedNative(j2, i3, i4, i5, bArr, recognitionOptions));
        }
        throw new IllegalStateException("Native pointer does not exist.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i3, int i4, @NonNull ByteBuffer byteBuffer, @NonNull RecognitionOptions recognitionOptions) {
        long j2 = this.nativePointer;
        if (j2 != 0) {
            return toProto(recognizeBufferNative(j2, i3, i4, byteBuffer, recognitionOptions));
        }
        throw new IllegalStateException("Native pointer does not exist.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i3, int i4, @NonNull byte[] bArr, @NonNull RecognitionOptions recognitionOptions) {
        long j2 = this.nativePointer;
        if (j2 != 0) {
            return toProto(recognizeNative(j2, i3, i4, bArr, recognitionOptions));
        }
        throw new IllegalStateException("Native pointer does not exist.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(@NonNull Bitmap bitmap, @NonNull RecognitionOptions recognitionOptions) {
        if (this.nativePointer != 0) {
            Bitmap.Config config = bitmap.getConfig();
            Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
            if (config != config2) {
                Log.d(TAG, "Input bitmap config is not ARGB_8888. Converting it to ARGB_8888 from ".concat(String.valueOf(bitmap.getConfig())));
                bitmap = bitmap.copy(config2, bitmap.isMutable());
            }
            return toProto(recognizeBitmapNative(this.nativePointer, bitmap, recognitionOptions));
        }
        throw new IllegalStateException("Native pointer does not exist.");
    }
}
