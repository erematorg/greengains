package com.google.zxing.qrcode.encoder;

import androidx.constraintlayout.core.state.b;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;

public final class QRCode {
    public static final int NUM_MASK_PATTERNS = 8;
    private ErrorCorrectionLevel ecLevel;
    private int maskPattern = -1;
    private ByteMatrix matrix;
    private Mode mode;
    private Version version;

    public static boolean isValidMaskPattern(int i3) {
        return i3 >= 0 && i3 < 8;
    }

    public ErrorCorrectionLevel getECLevel() {
        return this.ecLevel;
    }

    public int getMaskPattern() {
        return this.maskPattern;
    }

    public ByteMatrix getMatrix() {
        return this.matrix;
    }

    public Mode getMode() {
        return this.mode;
    }

    public Version getVersion() {
        return this.version;
    }

    public void setECLevel(ErrorCorrectionLevel errorCorrectionLevel) {
        this.ecLevel = errorCorrectionLevel;
    }

    public void setMaskPattern(int i3) {
        this.maskPattern = i3;
    }

    public void setMatrix(ByteMatrix byteMatrix) {
        this.matrix = byteMatrix;
    }

    public void setMode(Mode mode2) {
        this.mode = mode2;
    }

    public void setVersion(Version version2) {
        this.version = version2;
    }

    public String toString() {
        StringBuilder o3 = b.o(200, "<<\n mode: ");
        o3.append(this.mode);
        o3.append("\n ecLevel: ");
        o3.append(this.ecLevel);
        o3.append("\n version: ");
        o3.append(this.version);
        o3.append("\n maskPattern: ");
        o3.append(this.maskPattern);
        if (this.matrix == null) {
            o3.append("\n matrix: null\n");
        } else {
            o3.append("\n matrix:\n");
            o3.append(this.matrix);
        }
        o3.append(">>\n");
        return o3.toString();
    }
}
