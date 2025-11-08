package com.reown.android.internal.common.signing.signature;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.reown.util.UtilFunctionsKt;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/reown/android/internal/common/signing/signature/Signature;", "", "v", "", "r", "s", "<init>", "([B[B[B)V", "getV", "()[B", "getR", "getS", "equals", "", "other", "hashCode", "", "component1", "component2", "component3", "copy", "toString", "", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Signature {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    private final byte[] f7347r;
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    private final byte[] f7348s;
    @NotNull

    /* renamed from: v  reason: collision with root package name */
    private final byte[] f7349v;

    @SourceDebugExtension({"SMAP\nSignature.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Signature.kt\ncom/reown/android/internal/common/signing/signature/Signature$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,63:1\n1#2:64\n*E\n"})
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/internal/common/signing/signature/Signature$Companion;", "", "<init>", "()V", "fromString", "Lcom/reown/android/internal/common/signing/signature/Signature;", "string", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Signature fromString(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "string");
            String guaranteeNoHexPrefix = Intrinsics.checkNotNullParameter(str, "<this>");
            if (StringsKt___StringsKt.chunked(guaranteeNoHexPrefix, 64).size() == 3) {
                List n2 = StringsKt___StringsKt.chunked(guaranteeNoHexPrefix, 64);
                String str2 = (String) n2.get(0);
                String str3 = (String) n2.get(1);
                int parseUnsignedInt = Integer.parseUnsignedInt((String) n2.get(2), 16);
                if (parseUnsignedInt < 27) {
                    parseUnsignedInt += 27;
                }
                String hexString = Integer.toHexString(parseUnsignedInt);
                Intrinsics.checkNotNull(hexString);
                return new Signature(UtilFunctionsKt.hexToBytes(hexString), UtilFunctionsKt.hexToBytes(str2), UtilFunctionsKt.hexToBytes(str3));
            }
            throw new IllegalStateException("Check failed.");
        }

        private Companion() {
        }
    }

    public Signature(@NotNull byte[] bArr, @NotNull byte[] bArr2, @NotNull byte[] bArr3) {
        Intrinsics.checkNotNullParameter(bArr, "v");
        Intrinsics.checkNotNullParameter(bArr2, "r");
        Intrinsics.checkNotNullParameter(bArr3, "s");
        this.f7349v = bArr;
        this.f7347r = bArr2;
        this.f7348s = bArr3;
    }

    public static /* synthetic */ Signature copy$default(Signature signature, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            bArr = signature.f7349v;
        }
        if ((i3 & 2) != 0) {
            bArr2 = signature.f7347r;
        }
        if ((i3 & 4) != 0) {
            bArr3 = signature.f7348s;
        }
        return signature.copy(bArr, bArr2, bArr3);
    }

    @NotNull
    public final byte[] component1() {
        return this.f7349v;
    }

    @NotNull
    public final byte[] component2() {
        return this.f7347r;
    }

    @NotNull
    public final byte[] component3() {
        return this.f7348s;
    }

    @NotNull
    public final Signature copy(@NotNull byte[] bArr, @NotNull byte[] bArr2, @NotNull byte[] bArr3) {
        Intrinsics.checkNotNullParameter(bArr, "v");
        Intrinsics.checkNotNullParameter(bArr2, "r");
        Intrinsics.checkNotNullParameter(bArr3, "s");
        return new Signature(bArr, bArr2, bArr3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (Intrinsics.areEqual((Object) Signature.class, (Object) obj.getClass()) && (obj instanceof Signature)) {
                Signature signature = (Signature) obj;
                if (!Arrays.equals(this.f7349v, signature.f7349v) || !Arrays.equals(this.f7347r, signature.f7347r) || Arrays.equals(this.f7348s, signature.f7348s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @NotNull
    public final byte[] getR() {
        return this.f7347r;
    }

    @NotNull
    public final byte[] getS() {
        return this.f7348s;
    }

    @NotNull
    public final byte[] getV() {
        return this.f7349v;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f7348s) + a.d(this.f7347r, Arrays.hashCode(this.f7349v) * 31, 31);
    }

    @NotNull
    public String toString() {
        String arrays = Arrays.toString(this.f7349v);
        String arrays2 = Arrays.toString(this.f7347r);
        return a.n(C0118y.l("Signature(v=", arrays, ", r=", arrays2, ", s="), Arrays.toString(this.f7348s), ")");
    }
}
