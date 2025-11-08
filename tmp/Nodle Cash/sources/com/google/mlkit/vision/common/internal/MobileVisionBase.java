package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_common.zzlx;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.vision.common.InputImage;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@KeepForSdk
public class MobileVisionBase<DetectionResultT> implements Closeable, LifecycleObserver {
    public static final /* synthetic */ int zza = 0;
    private static final GmsLogger zzb = new GmsLogger("MobileVisionBase", "");
    private final AtomicBoolean zzc = new AtomicBoolean(false);
    private final MLTask zzd;
    private final CancellationTokenSource zze;
    private final Executor zzf;
    private final Task zzg;

    @KeepForSdk
    public MobileVisionBase(@NonNull MLTask<DetectionResultT, InputImage> mLTask, @NonNull Executor executor) {
        this.zzd = mLTask;
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        this.zze = cancellationTokenSource;
        this.zzf = executor;
        mLTask.pin();
        this.zzg = mLTask.callAfterLoad(executor, zzb.zza, cancellationTokenSource.getToken()).addOnFailureListener(zzc.zza);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @KeepForSdk
    public synchronized void close() {
        if (!this.zzc.getAndSet(true)) {
            this.zze.cancel();
            this.zzd.unpin(this.zzf);
        }
    }

    @NonNull
    @KeepForSdk
    public synchronized Task<Void> closeWithTask() {
        if (!this.zzc.getAndSet(true)) {
            this.zze.cancel();
            return this.zzd.unpinWithTask(this.zzf);
        }
        return Tasks.forResult(null);
    }

    @NonNull
    @KeepForSdk
    public synchronized Task<Void> getInitTaskBase() {
        return this.zzg;
    }

    @NonNull
    @KeepForSdk
    public Task<DetectionResultT> process(@NonNull Bitmap bitmap, int i3) {
        return processBase(InputImage.fromBitmap(bitmap, i3));
    }

    @NonNull
    @KeepForSdk
    public synchronized Task<DetectionResultT> processBase(@NonNull MlImage mlImage) {
        Preconditions.checkNotNull(mlImage, "MlImage can not be null");
        if (this.zzc.get()) {
            return Tasks.forException(new MlKitException("This detector is already closed!", 14));
        } else if (mlImage.getWidth() < 32 || mlImage.getHeight() < 32) {
            return Tasks.forException(new MlKitException("MlImage width and height should be at least 32!", 3));
        } else {
            mlImage.getInternal().acquire();
            return this.zzd.callAfterLoad(this.zzf, new zzd(this, mlImage), this.zze.getToken()).addOnCompleteListener(new zze(mlImage));
        }
    }

    public final /* synthetic */ Object zza(InputImage inputImage) throws Exception {
        Class<Throwable> cls = Throwable.class;
        zzlx zze2 = zzlx.zze("detectorTaskWithResource#run");
        zze2.zzb();
        try {
            Object run = this.zzd.run(inputImage);
            zze2.close();
            return run;
        } catch (Throwable th) {
            try {
                cls.getDeclaredMethod("addSuppressed", new Class[]{cls}).invoke(th, new Object[]{th});
            } catch (Exception unused) {
            }
        }
        throw th;
    }

    public final /* synthetic */ Object zzb(MlImage mlImage) throws Exception {
        InputImage convertMlImagetoInputImage = CommonConvertUtils.convertMlImagetoInputImage(mlImage);
        if (convertMlImagetoInputImage != null) {
            return this.zzd.run(convertMlImagetoInputImage);
        }
        throw new MlKitException("Current type of MlImage is not supported.", 13);
    }

    @NonNull
    @KeepForSdk
    public Task<DetectionResultT> process(@NonNull Image image, int i3) {
        return processBase(InputImage.fromMediaImage(image, i3));
    }

    @NonNull
    @KeepForSdk
    public Task<DetectionResultT> process(@NonNull Image image, int i3, @NonNull Matrix matrix) {
        return processBase(InputImage.fromMediaImage(image, i3, matrix));
    }

    @NonNull
    @KeepForSdk
    public Task<DetectionResultT> process(@NonNull ByteBuffer byteBuffer, int i3, int i4, int i5, int i6) {
        return processBase(InputImage.fromByteBuffer(byteBuffer, i3, i4, i5, i6));
    }

    @NonNull
    @KeepForSdk
    public synchronized Task<DetectionResultT> processBase(@NonNull InputImage inputImage) {
        Preconditions.checkNotNull(inputImage, "InputImage can not be null");
        if (this.zzc.get()) {
            return Tasks.forException(new MlKitException("This detector is already closed!", 14));
        } else if (inputImage.getWidth() < 32 || inputImage.getHeight() < 32) {
            return Tasks.forException(new MlKitException("InputImage width and height should be at least 32!", 3));
        } else {
            return this.zzd.callAfterLoad(this.zzf, new zza(this, inputImage), this.zze.getToken());
        }
    }
}
