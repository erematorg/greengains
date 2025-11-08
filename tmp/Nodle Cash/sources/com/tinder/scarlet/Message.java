package com.tinder.scarlet;

import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/tinder/scarlet/Message;", "", "()V", "Bytes", "Text", "Lcom/tinder/scarlet/Message$Bytes;", "Lcom/tinder/scarlet/Message$Text;", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class Message {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/tinder/scarlet/Message$Bytes;", "Lcom/tinder/scarlet/Message;", "value", "", "([B)V", "getValue", "()[B", "component1", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Bytes extends Message {
        @NotNull
        private final byte[] value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Bytes(@NotNull byte[] bArr) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(bArr, "value");
            this.value = bArr;
        }

        @NotNull
        public final byte[] component1() {
            return this.value;
        }

        @NotNull
        public final byte[] getValue() {
            return this.value;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/tinder/scarlet/Message$Text;", "Lcom/tinder/scarlet/Message;", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "scarlet-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Text extends Message {
        @NotNull
        private final String value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Text(@NotNull String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "value");
            this.value = str;
        }

        public static /* synthetic */ Text copy$default(Text text, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = text.value;
            }
            return text.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.value;
        }

        @NotNull
        public final Text copy(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "value");
            return new Text(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Text) && Intrinsics.areEqual((Object) this.value, (Object) ((Text) obj).value);
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        @NotNull
        public String toString() {
            return a.o(')', this.value, new StringBuilder("Text(value="));
        }
    }

    public /* synthetic */ Message(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private Message() {
    }
}
