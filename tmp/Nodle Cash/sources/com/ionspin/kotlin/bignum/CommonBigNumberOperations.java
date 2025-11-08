package com.ionspin.kotlin.bignum;

import com.ionspin.kotlin.bignum.BigNumber;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b`\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003J\u0016\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\tJ\u0016\u0010\u0004\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\fJ\u0016\u0010\u0004\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H&J\r\u0010\u0015\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\tJ\u0016\u0010\u0017\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\fJ\u0016\u0010\u0017\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\tJ\u0016\u0010\u0018\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\fJ\u0016\u0010\u0018\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\tJ\u0016\u0010\u0019\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\fJ\u0016\u0010\u0019\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010\u0012J\u0016\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0006J\u0016\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\tJ\u0016\u0010\u001a\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\fJ\u0016\u0010\u001a\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010\u0012¨\u0006\u001b"}, d2 = {"Lcom/ionspin/kotlin/bignum/CommonBigNumberOperations;", "BigType", "Lcom/ionspin/kotlin/bignum/BigNumber;", "", "div", "other", "(Lcom/ionspin/kotlin/bignum/BigNumber;)Lcom/ionspin/kotlin/bignum/BigNumber;", "byte", "", "(B)Lcom/ionspin/kotlin/bignum/BigNumber;", "int", "", "(I)Lcom/ionspin/kotlin/bignum/BigNumber;", "long", "", "(J)Lcom/ionspin/kotlin/bignum/BigNumber;", "short", "", "(S)Lcom/ionspin/kotlin/bignum/BigNumber;", "getCreator", "Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "getInstance", "()Lcom/ionspin/kotlin/bignum/BigNumber;", "minus", "plus", "rem", "times", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface CommonBigNumberOperations<BigType extends BigNumber<BigType>> {

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType div(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, @NotNull BigType bigtype) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            Intrinsics.checkNotNullParameter(bigtype, "other");
            return commonBigNumberOperations.getInstance().divide(bigtype);
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType minus(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, @NotNull BigType bigtype) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            Intrinsics.checkNotNullParameter(bigtype, "other");
            return commonBigNumberOperations.getInstance().subtract(bigtype);
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType plus(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, @NotNull BigType bigtype) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            Intrinsics.checkNotNullParameter(bigtype, "other");
            return commonBigNumberOperations.getInstance().add(bigtype);
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType rem(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, @NotNull BigType bigtype) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            Intrinsics.checkNotNullParameter(bigtype, "other");
            return commonBigNumberOperations.getInstance().remainder(bigtype);
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType times(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, @NotNull BigType bigtype) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            Intrinsics.checkNotNullParameter(bigtype, "other");
            return commonBigNumberOperations.getInstance().multiply(bigtype);
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType div(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, int i3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().divide((BigNumber) commonBigNumberOperations.getCreator().fromInt(i3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType minus(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, int i3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().subtract((BigNumber) commonBigNumberOperations.getCreator().fromInt(i3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType plus(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, int i3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().add((BigNumber) commonBigNumberOperations.getCreator().fromInt(i3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType rem(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, int i3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().remainder((BigNumber) commonBigNumberOperations.getCreator().fromInt(i3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType times(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, int i3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().multiply((BigNumber) commonBigNumberOperations.getCreator().fromInt(i3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType div(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, long j2) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().divide((BigNumber) commonBigNumberOperations.getCreator().fromLong(j2));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType minus(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, long j2) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().subtract((BigNumber) commonBigNumberOperations.getCreator().fromLong(j2));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType plus(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, long j2) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().add((BigNumber) commonBigNumberOperations.getCreator().fromLong(j2));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType rem(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, long j2) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().remainder((BigNumber) commonBigNumberOperations.getCreator().fromLong(j2));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType times(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, long j2) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().multiply((BigNumber) commonBigNumberOperations.getCreator().fromLong(j2));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType div(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, short s3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().divide((BigNumber) commonBigNumberOperations.getCreator().fromShort(s3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType minus(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, short s3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().subtract((BigNumber) commonBigNumberOperations.getCreator().fromShort(s3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType plus(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, short s3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().add((BigNumber) commonBigNumberOperations.getCreator().fromShort(s3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType rem(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, short s3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().remainder((BigNumber) commonBigNumberOperations.getCreator().fromShort(s3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType times(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, short s3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().multiply((BigNumber) commonBigNumberOperations.getCreator().fromShort(s3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType div(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, byte b3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().divide((BigNumber) commonBigNumberOperations.getCreator().fromByte(b3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType minus(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, byte b3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().subtract((BigNumber) commonBigNumberOperations.getCreator().fromByte(b3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType plus(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, byte b3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().add((BigNumber) commonBigNumberOperations.getCreator().fromByte(b3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType rem(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, byte b3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().remainder((BigNumber) commonBigNumberOperations.getCreator().fromByte(b3));
        }

        @NotNull
        public static <BigType extends BigNumber<BigType>> BigType times(@NotNull CommonBigNumberOperations<BigType> commonBigNumberOperations, byte b3) {
            Intrinsics.checkNotNullParameter(commonBigNumberOperations, "this");
            return commonBigNumberOperations.getInstance().multiply((BigNumber) commonBigNumberOperations.getCreator().fromByte(b3));
        }
    }

    @NotNull
    BigType div(byte b3);

    @NotNull
    BigType div(int i3);

    @NotNull
    BigType div(long j2);

    @NotNull
    BigType div(@NotNull BigType bigtype);

    @NotNull
    BigType div(short s3);

    @NotNull
    BigNumber.Creator<BigType> getCreator();

    @NotNull
    BigType getInstance();

    @NotNull
    BigType minus(byte b3);

    @NotNull
    BigType minus(int i3);

    @NotNull
    BigType minus(long j2);

    @NotNull
    BigType minus(@NotNull BigType bigtype);

    @NotNull
    BigType minus(short s3);

    @NotNull
    BigType plus(byte b3);

    @NotNull
    BigType plus(int i3);

    @NotNull
    BigType plus(long j2);

    @NotNull
    BigType plus(@NotNull BigType bigtype);

    @NotNull
    BigType plus(short s3);

    @NotNull
    BigType rem(byte b3);

    @NotNull
    BigType rem(int i3);

    @NotNull
    BigType rem(long j2);

    @NotNull
    BigType rem(@NotNull BigType bigtype);

    @NotNull
    BigType rem(short s3);

    @NotNull
    BigType times(byte b3);

    @NotNull
    BigType times(int i3);

    @NotNull
    BigType times(long j2);

    @NotNull
    BigType times(@NotNull BigType bigtype);

    @NotNull
    BigType times(short s3);
}
