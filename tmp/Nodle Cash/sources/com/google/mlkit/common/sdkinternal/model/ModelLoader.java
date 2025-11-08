package com.google.mlkit.common.sdkinternal.model;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.ktx.BuildConfig;
import com.google.mlkit.common.MlKitException;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;

@WorkerThread
@KeepForSdk
public class ModelLoader {
    private static final GmsLogger zza = new GmsLogger("ModelLoader", "");
    @KeepForSdk
    @Nullable
    public final LocalModelLoader localModelLoader;
    @NonNull
    @KeepForSdk
    protected ModelLoadingState modelLoadingState = ModelLoadingState.NO_MODEL_LOADED;
    @VisibleForTesting
    @KeepForSdk
    @Nullable
    public final RemoteModelLoader remoteModelLoader;
    @NonNull
    private final ModelLoadingLogger zzb;

    @KeepForSdk
    public interface ModelContentHandler {
        @KeepForSdk
        void constructModel(@NonNull MappedByteBuffer mappedByteBuffer) throws MlKitException;
    }

    @KeepForSdk
    public interface ModelLoadingLogger {
        @KeepForSdk
        void logErrorCodes(@NonNull List<Integer> list);
    }

    @KeepForSdk
    public enum ModelLoadingState {
        NO_MODEL_LOADED,
        REMOTE_MODEL_LOADED,
        LOCAL_MODEL_LOADED
    }

    @KeepForSdk
    public ModelLoader(@Nullable RemoteModelLoader remoteModelLoader2, @Nullable LocalModelLoader localModelLoader2, @NonNull ModelLoadingLogger modelLoadingLogger) {
        boolean z2 = true;
        if (remoteModelLoader2 == null && localModelLoader2 == null) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "At least one of RemoteModelLoader or LocalModelLoader must be non-null.");
        Preconditions.checkNotNull(modelLoadingLogger);
        this.remoteModelLoader = remoteModelLoader2;
        this.localModelLoader = localModelLoader2;
        this.zzb = modelLoadingLogger;
    }

    private final String zza() {
        LocalModelLoader localModelLoader2 = this.localModelLoader;
        String str = null;
        if (localModelLoader2 != null) {
            if (localModelLoader2.getLocalModel().getAssetFilePath() != null) {
                str = this.localModelLoader.getLocalModel().getAssetFilePath();
            } else if (this.localModelLoader.getLocalModel().getAbsoluteFilePath() != null) {
                str = this.localModelLoader.getLocalModel().getAbsoluteFilePath();
            } else if (this.localModelLoader.getLocalModel().getUri() != null) {
                str = ((Uri) Preconditions.checkNotNull(this.localModelLoader.getLocalModel().getUri())).toString();
            }
        }
        RemoteModelLoader remoteModelLoader2 = this.remoteModelLoader;
        return C0118y.g("Local model path: ", str, ". Remote model name: ", remoteModelLoader2 == null ? BuildConfig.VERSION_NAME : remoteModelLoader2.getRemoteModel().getUniqueModelNameForPersist(), ". ");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0028, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean zzb(com.google.mlkit.common.sdkinternal.model.ModelLoader.ModelContentHandler r2, java.util.List r3) throws com.google.mlkit.common.MlKitException {
        /*
            r1 = this;
            monitor-enter(r1)
            com.google.mlkit.common.sdkinternal.model.LocalModelLoader r0 = r1.localModelLoader     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x0027
            java.nio.MappedByteBuffer r0 = r0.load()     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x0027
            r2.constructModel(r0)     // Catch:{ RuntimeException -> 0x001c }
            com.google.android.gms.common.internal.GmsLogger r2 = zza     // Catch:{ all -> 0x001a }
            java.lang.String r3 = "ModelLoader"
            java.lang.String r0 = "Local model source is loaded successfully"
            r2.d(r3, r0)     // Catch:{ all -> 0x001a }
            monitor-exit(r1)
            r1 = 1
            return r1
        L_0x001a:
            r2 = move-exception
            goto L_0x002a
        L_0x001c:
            r2 = move-exception
            r0 = 18
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x001a }
            r3.add(r0)     // Catch:{ all -> 0x001a }
            throw r2     // Catch:{ all -> 0x001a }
        L_0x0027:
            monitor-exit(r1)
            r1 = 0
            return r1
        L_0x002a:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.ModelLoader.zzb(com.google.mlkit.common.sdkinternal.model.ModelLoader$ModelContentHandler, java.util.List):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean zzc(com.google.mlkit.common.sdkinternal.model.ModelLoader.ModelContentHandler r4, java.util.List r5) throws com.google.mlkit.common.MlKitException {
        /*
            r3 = this;
            monitor-enter(r3)
            com.google.mlkit.common.sdkinternal.model.RemoteModelLoader r0 = r3.remoteModelLoader     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x004e
            java.nio.MappedByteBuffer r0 = r0.load()     // Catch:{ MlKitException -> 0x003a }
            if (r0 == 0) goto L_0x0027
            r4.constructModel(r0)     // Catch:{ RuntimeException -> 0x001c }
            com.google.android.gms.common.internal.GmsLogger r4 = zza     // Catch:{ all -> 0x001a }
            java.lang.String r5 = "ModelLoader"
            java.lang.String r0 = "Remote model source is loaded successfully"
            r4.d(r5, r0)     // Catch:{ all -> 0x001a }
            monitor-exit(r3)
            r3 = 1
            return r3
        L_0x001a:
            r4 = move-exception
            goto L_0x0051
        L_0x001c:
            r4 = move-exception
            r0 = 19
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x001a }
            r5.add(r0)     // Catch:{ all -> 0x001a }
            throw r4     // Catch:{ all -> 0x001a }
        L_0x0027:
            com.google.android.gms.common.internal.GmsLogger r4 = zza     // Catch:{ all -> 0x001a }
            java.lang.String r0 = "ModelLoader"
            java.lang.String r1 = "Remote model source can NOT be loaded, try local model."
            r4.d(r0, r1)     // Catch:{ all -> 0x001a }
            r4 = 21
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x001a }
            r5.add(r4)     // Catch:{ all -> 0x001a }
            goto L_0x004e
        L_0x003a:
            r4 = move-exception
            com.google.android.gms.common.internal.GmsLogger r0 = zza     // Catch:{ all -> 0x001a }
            java.lang.String r1 = "ModelLoader"
            java.lang.String r2 = "Remote model source can NOT be loaded, try local model."
            r0.d(r1, r2)     // Catch:{ all -> 0x001a }
            r0 = 20
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x001a }
            r5.add(r0)     // Catch:{ all -> 0x001a }
            throw r4     // Catch:{ all -> 0x001a }
        L_0x004e:
            monitor-exit(r3)
            r3 = 0
            return r3
        L_0x0051:
            monitor-exit(r3)     // Catch:{ all -> 0x001a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.ModelLoader.zzc(com.google.mlkit.common.sdkinternal.model.ModelLoader$ModelContentHandler, java.util.List):boolean");
    }

    @KeepForSdk
    public synchronized boolean isRemoteModelLoaded() {
        return this.modelLoadingState == ModelLoadingState.REMOTE_MODEL_LOADED;
    }

    @KeepForSdk
    public synchronized void loadWithModelContentHandler(@NonNull ModelContentHandler modelContentHandler) throws MlKitException {
        Exception exc;
        boolean z2;
        ArrayList arrayList = new ArrayList();
        boolean z3 = false;
        Exception e3 = null;
        try {
            z2 = zzc(modelContentHandler, arrayList);
            exc = null;
        } catch (Exception e4) {
            exc = e4;
            z2 = false;
        }
        if (z2) {
            this.zzb.logErrorCodes(arrayList);
            this.modelLoadingState = ModelLoadingState.REMOTE_MODEL_LOADED;
            return;
        }
        try {
            z3 = zzb(modelContentHandler, arrayList);
        } catch (Exception e5) {
            e3 = e5;
        }
        if (z3) {
            this.zzb.logErrorCodes(arrayList);
            this.modelLoadingState = ModelLoadingState.LOCAL_MODEL_LOADED;
            return;
        }
        arrayList.add(17);
        this.zzb.logErrorCodes(arrayList);
        this.modelLoadingState = ModelLoadingState.NO_MODEL_LOADED;
        if (exc != null) {
            throw new MlKitException("Remote model load failed with the model options: ".concat(String.valueOf(zza())), 14, exc);
        } else if (e3 != null) {
            throw new MlKitException("Local model load failed with the model options: ".concat(String.valueOf(zza())), 14, e3);
        } else {
            throw new MlKitException("Cannot load any model with the model options: ".concat(String.valueOf(zza())), 14);
        }
    }
}
