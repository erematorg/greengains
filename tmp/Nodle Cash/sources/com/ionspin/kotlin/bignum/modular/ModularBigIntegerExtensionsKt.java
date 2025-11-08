package com.ionspin.kotlin.bignum.modular;

import com.ionspin.kotlin.bignum.BigNumber;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r\u001a\u0018\u0010\u000b\u001a\u00020\u0001*\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r\u001a\u0018\u0010\u000b\u001a\u00020\u0001*\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r\u001a\u0018\u0010\u000b\u001a\u00020\u0001*\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r\u001a\"\u0010\u000b\u001a\u00020\u0001*\u00020\u000e2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0004¨\u0006\u0010"}, d2 = {"div", "Lcom/ionspin/kotlin/bignum/modular/ModularBigInteger;", "", "other", "", "", "", "minus", "plus", "rem", "times", "toModularBigInteger", "creator", "Lcom/ionspin/kotlin/bignum/BigNumber$Creator;", "", "base", "bignum"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class ModularBigIntegerExtensionsKt {
    @NotNull
    public static final ModularBigInteger div(long j2, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(j2, modularBigInteger.getCreator()).div((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger minus(long j2, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(j2, modularBigInteger.getCreator()).minus((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger plus(long j2, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(j2, modularBigInteger.getCreator()).plus((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger rem(long j2, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return toModularBigInteger(j2, modularBigInteger.getCreator()).rem(modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger times(long j2, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(j2, modularBigInteger.getCreator()).times((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger toModularBigInteger(long j2, @NotNull BigNumber.Creator<ModularBigInteger> creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        return creator.fromLong(j2);
    }

    public static /* synthetic */ ModularBigInteger toModularBigInteger$default(String str, BigNumber.Creator creator, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i3 = 10;
        }
        return toModularBigInteger(str, creator, i3);
    }

    @NotNull
    public static final ModularBigInteger div(int i3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(i3, modularBigInteger.getCreator()).div((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger minus(int i3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(i3, modularBigInteger.getCreator()).minus((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger plus(int i3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(i3, modularBigInteger.getCreator()).plus((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger rem(int i3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return toModularBigInteger(i3, modularBigInteger.getCreator()).rem(modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger times(int i3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(i3, modularBigInteger.getCreator()).times((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger toModularBigInteger(int i3, @NotNull BigNumber.Creator<ModularBigInteger> creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        return creator.fromInt(i3);
    }

    @NotNull
    public static final ModularBigInteger div(short s3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(s3, modularBigInteger.getCreator()).div((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger minus(short s3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(s3, modularBigInteger.getCreator()).minus((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger plus(short s3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(s3, modularBigInteger.getCreator()).plus((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger rem(short s3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return toModularBigInteger(s3, modularBigInteger.getCreator()).rem(modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger times(short s3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(s3, modularBigInteger.getCreator()).times((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger toModularBigInteger(short s3, @NotNull BigNumber.Creator<ModularBigInteger> creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        return creator.fromShort(s3);
    }

    @NotNull
    public static final ModularBigInteger div(byte b3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(b3, modularBigInteger.getCreator()).div((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger minus(byte b3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(b3, modularBigInteger.getCreator()).minus((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger plus(byte b3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(b3, modularBigInteger.getCreator()).plus((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger rem(byte b3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return toModularBigInteger(b3, modularBigInteger.getCreator()).rem(modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger times(byte b3, @NotNull ModularBigInteger modularBigInteger) {
        Intrinsics.checkNotNullParameter(modularBigInteger, "other");
        return (ModularBigInteger) toModularBigInteger(b3, modularBigInteger.getCreator()).times((BigNumber) modularBigInteger);
    }

    @NotNull
    public static final ModularBigInteger toModularBigInteger(byte b3, @NotNull BigNumber.Creator<ModularBigInteger> creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        return creator.fromByte(b3);
    }

    @NotNull
    public static final ModularBigInteger toModularBigInteger(@NotNull String str, @NotNull BigNumber.Creator<ModularBigInteger> creator, int i3) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(creator, "creator");
        return creator.parseString(str, i3);
    }
}
