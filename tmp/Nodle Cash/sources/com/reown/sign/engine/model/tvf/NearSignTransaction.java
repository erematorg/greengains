package com.reown.sign.engine.model.tvf;

import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.JsonClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/reown/sign/engine/model/tvf/NearSignTransaction;", "", "<init>", "()V", "calculateTxID", "", "bufferData", "Lcom/reown/sign/engine/model/tvf/NearSignTransaction$BufferData;", "BufferData", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NearSignTransaction {
    @NotNull
    public static final NearSignTransaction INSTANCE = new NearSignTransaction();

    @JsonClass(generateAdapter = true)
    @SourceDebugExtension({"SMAP\nNear.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Near.kt\ncom/reown/sign/engine/model/tvf/NearSignTransaction$BufferData\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,52:1\n1563#2:53\n1634#2,3:54\n1056#2:57\n1563#2:58\n1634#2,3:59\n*S KotlinDebug\n*F\n+ 1 Near.kt\ncom/reown/sign/engine/model/tvf/NearSignTransaction$BufferData\n*L\n17#1:53\n17#1:54,3\n27#1:57\n28#1:58\n28#1:59,3\n*E\n"})
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0001HÆ\u0003J\u001f\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/tvf/NearSignTransaction$BufferData;", "", "type", "", "data", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "getType", "()Ljava/lang/String;", "getData", "()Ljava/lang/Object;", "toByteArray", "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class BufferData {
        @NotNull
        private final Object data;
        @Nullable
        private final String type;

        public BufferData(@Nullable String str, @NotNull Object obj) {
            Intrinsics.checkNotNullParameter(obj, "data");
            this.type = str;
            this.data = obj;
        }

        public static /* synthetic */ BufferData copy$default(BufferData bufferData, String str, Object obj, int i3, Object obj2) {
            if ((i3 & 1) != 0) {
                str = bufferData.type;
            }
            if ((i3 & 2) != 0) {
                obj = bufferData.data;
            }
            return bufferData.copy(str, obj);
        }

        @Nullable
        public final String component1() {
            return this.type;
        }

        @NotNull
        public final Object component2() {
            return this.data;
        }

        @NotNull
        public final BufferData copy(@Nullable String str, @NotNull Object obj) {
            Intrinsics.checkNotNullParameter(obj, "data");
            return new BufferData(str, obj);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BufferData)) {
                return false;
            }
            BufferData bufferData = (BufferData) obj;
            return Intrinsics.areEqual((Object) this.type, (Object) bufferData.type) && Intrinsics.areEqual(this.data, bufferData.data);
        }

        @NotNull
        public final Object getData() {
            return this.data;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            String str = this.type;
            return this.data.hashCode() + ((str == null ? 0 : str.hashCode()) * 31);
        }

        @NotNull
        public final byte[] toByteArray() {
            Object obj = this.data;
            Class<?> cls = null;
            if (obj instanceof List) {
                Iterable iterable = (Iterable) obj;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
                for (Object next : iterable) {
                    if (next instanceof Number) {
                        arrayList.add(Byte.valueOf((byte) ((Number) next).intValue()));
                    } else {
                        if (next != null) {
                            cls = next.getClass();
                        }
                        throw new IllegalArgumentException(b.k("Invalid number type: ", cls));
                    }
                }
                return CollectionsKt.toByteArray(arrayList);
            } else if (obj instanceof Map) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, *>");
                Iterable<Map.Entry> sortedWith = CollectionsKt.sortedWith(((Map) obj).entrySet(), new NearSignTransaction$BufferData$toByteArray$$inlined$sortedBy$1());
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(sortedWith, 10));
                for (Map.Entry value : sortedWith) {
                    Object value2 = value.getValue();
                    if (value2 instanceof Number) {
                        arrayList2.add(Byte.valueOf((byte) ((Number) value2).intValue()));
                    } else {
                        if (value2 != null) {
                            cls = value2.getClass();
                        }
                        throw new IllegalArgumentException(b.k("Invalid number type: ", cls));
                    }
                }
                return CollectionsKt.toByteArray(arrayList2);
            } else {
                throw new IllegalArgumentException(b.k("Unsupported data format: ", this.data.getClass()));
            }
        }

        @NotNull
        public String toString() {
            String str = this.type;
            Object obj = this.data;
            return "BufferData(type=" + str + ", data=" + obj + ")";
        }
    }

    private NearSignTransaction() {
    }

    @NotNull
    public final String calculateTxID(@NotNull BufferData bufferData) {
        Intrinsics.checkNotNullParameter(bufferData, "bufferData");
        byte[] byteArray = bufferData.toByteArray();
        SHA256Digest sHA256Digest = new SHA256Digest();
        byte[] bArr = new byte[sHA256Digest.getDigestSize()];
        sHA256Digest.update(byteArray, 0, byteArray.length);
        sHA256Digest.doFinal(bArr, 0);
        return TVF.Companion.toBase58(bArr);
    }
}
