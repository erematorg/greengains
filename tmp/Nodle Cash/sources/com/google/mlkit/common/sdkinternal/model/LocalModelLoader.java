package com.google.mlkit.common.sdkinternal.model;

import A.a;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzi;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.LocalModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@KeepForSdk
public class LocalModelLoader {
    private MappedByteBuffer zza;
    private final Context zzb;
    private final LocalModel zzc;

    @KeepForSdk
    public LocalModelLoader(@NonNull Context context, @NonNull LocalModel localModel) {
        this.zzb = context;
        this.zzc = localModel;
    }

    @NonNull
    @KeepForSdk
    public LocalModel getLocalModel() {
        return this.zzc;
    }

    @WorkerThread
    @NonNull
    @KeepForSdk
    public MappedByteBuffer load() throws MlKitException {
        AssetFileDescriptor zza2;
        FileChannel channel;
        AssetFileDescriptor openFd;
        FileChannel channel2;
        RandomAccessFile randomAccessFile;
        FileChannel channel3;
        Preconditions.checkNotNull(this.zzb, "Context can not be null");
        Preconditions.checkNotNull(this.zzc, "Model source can not be null");
        MappedByteBuffer mappedByteBuffer = this.zza;
        if (mappedByteBuffer != null) {
            return mappedByteBuffer;
        }
        LocalModel localModel = this.zzc;
        String absoluteFilePath = localModel.getAbsoluteFilePath();
        String assetFilePath = localModel.getAssetFilePath();
        Uri uri = localModel.getUri();
        if (absoluteFilePath != null) {
            try {
                randomAccessFile = new RandomAccessFile(absoluteFilePath, "r");
                channel3 = randomAccessFile.getChannel();
                this.zza = channel3.map(FileChannel.MapMode.READ_ONLY, 0, channel3.size());
                channel3.close();
                randomAccessFile.close();
            } catch (IOException e3) {
                throw new MlKitException("Can not open the local file: ".concat(String.valueOf(this.zzc.getAbsoluteFilePath())), 14, e3);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else if (assetFilePath != null) {
            try {
                openFd = this.zzb.getAssets().openFd(assetFilePath);
                channel2 = new FileInputStream(openFd.getFileDescriptor()).getChannel();
                FileChannel fileChannel = channel2;
                this.zza = fileChannel.map(FileChannel.MapMode.READ_ONLY, openFd.getStartOffset(), openFd.getDeclaredLength());
                channel2.close();
                openFd.close();
            } catch (IOException e4) {
                throw new MlKitException(a.l("Can not load the file from asset: ", assetFilePath, ". Please double check your asset file name and ensure it's not compressed. See documentation for details how to use aaptOptions to skip file compression"), 14, e4);
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else if (uri != null) {
            try {
                zza2 = zzi.zza(this.zzb, uri, "r");
                channel = zza2.createInputStream().getChannel();
                FileChannel fileChannel2 = channel;
                this.zza = fileChannel2.map(FileChannel.MapMode.READ_ONLY, zza2.getStartOffset(), zza2.getLength());
                channel.close();
                zza2.close();
            } catch (IOException e5) {
                throw new MlKitException("Can not load the file from URI: ".concat(uri.toString()), 14, e5);
            } catch (Throwable th3) {
                th.addSuppressed(th3);
            }
        } else {
            throw new MlKitException("Can not load the model. One of filePath, assetFilePath or URI must be set for the model.", 14);
        }
        return this.zza;
        throw th;
        throw th;
        throw th;
        throw th;
        throw th;
        throw th;
    }
}
