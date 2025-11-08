package com.appsamurai.storyly.exoplayer2.core.drm;

import android.media.MediaDrmException;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm;
import com.appsamurai.storyly.exoplayer2.decoder.CryptoConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresApi(18)
public final class DummyExoMediaDrm implements ExoMediaDrm {
    public static DummyExoMediaDrm getInstance() {
        return new DummyExoMediaDrm();
    }

    public void acquire() {
    }

    public void closeSession(byte[] bArr) {
    }

    public CryptoConfig createCryptoConfig(byte[] bArr) {
        throw new IllegalStateException();
    }

    public int getCryptoType() {
        return 1;
    }

    public ExoMediaDrm.KeyRequest getKeyRequest(byte[] bArr, @Nullable List<DrmInitData.SchemeData> list, int i3, @Nullable HashMap<String, String> hashMap) {
        throw new IllegalStateException();
    }

    @Nullable
    public PersistableBundle getMetrics() {
        return null;
    }

    public byte[] getPropertyByteArray(String str) {
        return Util.EMPTY_BYTE_ARRAY;
    }

    public String getPropertyString(String str) {
        return "";
    }

    public ExoMediaDrm.ProvisionRequest getProvisionRequest() {
        throw new IllegalStateException();
    }

    public byte[] openSession() throws MediaDrmException {
        throw new MediaDrmException("Attempting to open a session using a dummy ExoMediaDrm.");
    }

    @Nullable
    public byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) {
        throw new IllegalStateException();
    }

    public void provideProvisionResponse(byte[] bArr) {
        throw new IllegalStateException();
    }

    public Map<String, String> queryKeyStatus(byte[] bArr) {
        throw new IllegalStateException();
    }

    public void release() {
    }

    public boolean requiresSecureDecoder(byte[] bArr, String str) {
        throw new IllegalStateException();
    }

    public void restoreKeys(byte[] bArr, byte[] bArr2) {
        throw new IllegalStateException();
    }

    public void setOnEventListener(@Nullable ExoMediaDrm.OnEventListener onEventListener) {
    }

    public void setOnExpirationUpdateListener(@Nullable ExoMediaDrm.OnExpirationUpdateListener onExpirationUpdateListener) {
    }

    public void setOnKeyStatusChangeListener(@Nullable ExoMediaDrm.OnKeyStatusChangeListener onKeyStatusChangeListener) {
    }

    public void setPropertyByteArray(String str, byte[] bArr) {
    }

    public void setPropertyString(String str, String str2) {
    }
}
