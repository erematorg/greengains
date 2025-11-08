package com.google.android.gms.common.internal.safeparcel;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import androidx.annotation.NonNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SafeParcelWriter {
    private SafeParcelWriter() {
    }

    public static int beginObjectHeader(@NonNull Parcel parcel) {
        return zza(parcel, 20293);
    }

    public static void finishObjectHeader(@NonNull Parcel parcel, int i3) {
        zzb(parcel, i3);
    }

    public static void writeBigDecimal(@NonNull Parcel parcel, int i3, @NonNull BigDecimal bigDecimal, boolean z2) {
        if (bigDecimal != null) {
            int zza = zza(parcel, i3);
            parcel.writeByteArray(bigDecimal.unscaledValue().toByteArray());
            parcel.writeInt(bigDecimal.scale());
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeBigDecimalArray(@NonNull Parcel parcel, int i3, @NonNull BigDecimal[] bigDecimalArr, boolean z2) {
        if (bigDecimalArr != null) {
            int zza = zza(parcel, i3);
            int length = bigDecimalArr.length;
            parcel.writeInt(length);
            for (int i4 = 0; i4 < length; i4++) {
                parcel.writeByteArray(bigDecimalArr[i4].unscaledValue().toByteArray());
                parcel.writeInt(bigDecimalArr[i4].scale());
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeBigInteger(@NonNull Parcel parcel, int i3, @NonNull BigInteger bigInteger, boolean z2) {
        if (bigInteger != null) {
            int zza = zza(parcel, i3);
            parcel.writeByteArray(bigInteger.toByteArray());
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeBigIntegerArray(@NonNull Parcel parcel, int i3, @NonNull BigInteger[] bigIntegerArr, boolean z2) {
        if (bigIntegerArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeInt(r5);
            for (BigInteger byteArray : bigIntegerArr) {
                parcel.writeByteArray(byteArray.toByteArray());
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeBoolean(@NonNull Parcel parcel, int i3, boolean z2) {
        zzc(parcel, i3, 4);
        parcel.writeInt(z2 ? 1 : 0);
    }

    public static void writeBooleanArray(@NonNull Parcel parcel, int i3, @NonNull boolean[] zArr, boolean z2) {
        if (zArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeBooleanArray(zArr);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeBooleanList(@NonNull Parcel parcel, int i3, @NonNull List<Boolean> list, boolean z2) {
        if (list != null) {
            int zza = zza(parcel, i3);
            int size = list.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(list.get(i4).booleanValue() ? 1 : 0);
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeBooleanObject(@NonNull Parcel parcel, int i3, @NonNull Boolean bool, boolean z2) {
        if (bool != null) {
            zzc(parcel, i3, 4);
            parcel.writeInt(bool.booleanValue() ? 1 : 0);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeBundle(@NonNull Parcel parcel, int i3, @NonNull Bundle bundle, boolean z2) {
        if (bundle != null) {
            int zza = zza(parcel, i3);
            parcel.writeBundle(bundle);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeByte(@NonNull Parcel parcel, int i3, byte b3) {
        zzc(parcel, i3, 4);
        parcel.writeInt(b3);
    }

    public static void writeByteArray(@NonNull Parcel parcel, int i3, @NonNull byte[] bArr, boolean z2) {
        if (bArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeByteArray(bArr);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeByteArrayArray(@NonNull Parcel parcel, int i3, @NonNull byte[][] bArr, boolean z2) {
        if (bArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeInt(r5);
            for (byte[] writeByteArray : bArr) {
                parcel.writeByteArray(writeByteArray);
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeByteArraySparseArray(@NonNull Parcel parcel, int i3, @NonNull SparseArray<byte[]> sparseArray, boolean z2) {
        if (sparseArray != null) {
            int zza = zza(parcel, i3);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(sparseArray.keyAt(i4));
                parcel.writeByteArray(sparseArray.valueAt(i4));
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeChar(@NonNull Parcel parcel, int i3, char c3) {
        zzc(parcel, i3, 4);
        parcel.writeInt(c3);
    }

    public static void writeCharArray(@NonNull Parcel parcel, int i3, @NonNull char[] cArr, boolean z2) {
        if (cArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeCharArray(cArr);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeDouble(@NonNull Parcel parcel, int i3, double d2) {
        zzc(parcel, i3, 8);
        parcel.writeDouble(d2);
    }

    public static void writeDoubleArray(@NonNull Parcel parcel, int i3, @NonNull double[] dArr, boolean z2) {
        if (dArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeDoubleArray(dArr);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeDoubleList(@NonNull Parcel parcel, int i3, @NonNull List<Double> list, boolean z2) {
        if (list != null) {
            int zza = zza(parcel, i3);
            int size = list.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeDouble(list.get(i4).doubleValue());
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeDoubleObject(@NonNull Parcel parcel, int i3, @NonNull Double d2, boolean z2) {
        if (d2 != null) {
            zzc(parcel, i3, 8);
            parcel.writeDouble(d2.doubleValue());
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeDoubleSparseArray(@NonNull Parcel parcel, int i3, @NonNull SparseArray<Double> sparseArray, boolean z2) {
        if (sparseArray != null) {
            int zza = zza(parcel, i3);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(sparseArray.keyAt(i4));
                parcel.writeDouble(sparseArray.valueAt(i4).doubleValue());
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeFloat(@NonNull Parcel parcel, int i3, float f2) {
        zzc(parcel, i3, 4);
        parcel.writeFloat(f2);
    }

    public static void writeFloatArray(@NonNull Parcel parcel, int i3, @NonNull float[] fArr, boolean z2) {
        if (fArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeFloatArray(fArr);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeFloatList(@NonNull Parcel parcel, int i3, @NonNull List<Float> list, boolean z2) {
        if (list != null) {
            int zza = zza(parcel, i3);
            int size = list.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeFloat(list.get(i4).floatValue());
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeFloatObject(@NonNull Parcel parcel, int i3, @NonNull Float f2, boolean z2) {
        if (f2 != null) {
            zzc(parcel, i3, 4);
            parcel.writeFloat(f2.floatValue());
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeFloatSparseArray(@NonNull Parcel parcel, int i3, @NonNull SparseArray<Float> sparseArray, boolean z2) {
        if (sparseArray != null) {
            int zza = zza(parcel, i3);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(sparseArray.keyAt(i4));
                parcel.writeFloat(sparseArray.valueAt(i4).floatValue());
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeIBinder(@NonNull Parcel parcel, int i3, @NonNull IBinder iBinder, boolean z2) {
        if (iBinder != null) {
            int zza = zza(parcel, i3);
            parcel.writeStrongBinder(iBinder);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeIBinderArray(@NonNull Parcel parcel, int i3, @NonNull IBinder[] iBinderArr, boolean z2) {
        if (iBinderArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeBinderArray(iBinderArr);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeIBinderList(@NonNull Parcel parcel, int i3, @NonNull List<IBinder> list, boolean z2) {
        if (list != null) {
            int zza = zza(parcel, i3);
            parcel.writeBinderList(list);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeIBinderSparseArray(@NonNull Parcel parcel, int i3, @NonNull SparseArray<IBinder> sparseArray, boolean z2) {
        if (sparseArray != null) {
            int zza = zza(parcel, i3);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(sparseArray.keyAt(i4));
                parcel.writeStrongBinder(sparseArray.valueAt(i4));
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeInt(@NonNull Parcel parcel, int i3, int i4) {
        zzc(parcel, i3, 4);
        parcel.writeInt(i4);
    }

    public static void writeIntArray(@NonNull Parcel parcel, int i3, @NonNull int[] iArr, boolean z2) {
        if (iArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeIntArray(iArr);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeIntegerList(@NonNull Parcel parcel, int i3, @NonNull List<Integer> list, boolean z2) {
        if (list != null) {
            int zza = zza(parcel, i3);
            int size = list.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(list.get(i4).intValue());
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeIntegerObject(@NonNull Parcel parcel, int i3, @NonNull Integer num, boolean z2) {
        if (num != null) {
            zzc(parcel, i3, 4);
            parcel.writeInt(num.intValue());
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeList(@NonNull Parcel parcel, int i3, @NonNull List list, boolean z2) {
        if (list != null) {
            int zza = zza(parcel, i3);
            parcel.writeList(list);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeLong(@NonNull Parcel parcel, int i3, long j2) {
        zzc(parcel, i3, 8);
        parcel.writeLong(j2);
    }

    public static void writeLongArray(@NonNull Parcel parcel, int i3, @NonNull long[] jArr, boolean z2) {
        if (jArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeLongArray(jArr);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeLongList(@NonNull Parcel parcel, int i3, @NonNull List<Long> list, boolean z2) {
        if (list != null) {
            int zza = zza(parcel, i3);
            int size = list.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeLong(list.get(i4).longValue());
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeLongObject(@NonNull Parcel parcel, int i3, @NonNull Long l2, boolean z2) {
        if (l2 != null) {
            zzc(parcel, i3, 8);
            parcel.writeLong(l2.longValue());
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeParcel(@NonNull Parcel parcel, int i3, @NonNull Parcel parcel2, boolean z2) {
        if (parcel2 != null) {
            int zza = zza(parcel, i3);
            parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeParcelArray(@NonNull Parcel parcel, int i3, @NonNull Parcel[] parcelArr, boolean z2) {
        if (parcelArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeInt(r7);
            for (Parcel parcel2 : parcelArr) {
                if (parcel2 != null) {
                    parcel.writeInt(parcel2.dataSize());
                    parcel.appendFrom(parcel2, 0, parcel2.dataSize());
                } else {
                    parcel.writeInt(0);
                }
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeParcelList(@NonNull Parcel parcel, int i3, @NonNull List<Parcel> list, boolean z2) {
        if (list != null) {
            int zza = zza(parcel, i3);
            int size = list.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                Parcel parcel2 = list.get(i4);
                if (parcel2 != null) {
                    parcel.writeInt(parcel2.dataSize());
                    parcel.appendFrom(parcel2, 0, parcel2.dataSize());
                } else {
                    parcel.writeInt(0);
                }
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeParcelSparseArray(@NonNull Parcel parcel, int i3, @NonNull SparseArray<Parcel> sparseArray, boolean z2) {
        if (sparseArray != null) {
            int zza = zza(parcel, i3);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(sparseArray.keyAt(i4));
                Parcel valueAt = sparseArray.valueAt(i4);
                if (valueAt != null) {
                    parcel.writeInt(valueAt.dataSize());
                    parcel.appendFrom(valueAt, 0, valueAt.dataSize());
                } else {
                    parcel.writeInt(0);
                }
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeParcelable(@NonNull Parcel parcel, int i3, @NonNull Parcelable parcelable, int i4, boolean z2) {
        if (parcelable != null) {
            int zza = zza(parcel, i3);
            parcelable.writeToParcel(parcel, i4);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writePendingIntent(@NonNull Parcel parcel, int i3, @NonNull PendingIntent pendingIntent, boolean z2) {
        if (pendingIntent != null) {
            int zza = zza(parcel, i3);
            PendingIntent.writePendingIntentOrNullToParcel(pendingIntent, parcel);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeShort(@NonNull Parcel parcel, int i3, short s3) {
        zzc(parcel, i3, 4);
        parcel.writeInt(s3);
    }

    public static void writeSparseBooleanArray(@NonNull Parcel parcel, int i3, @NonNull SparseBooleanArray sparseBooleanArray, boolean z2) {
        if (sparseBooleanArray != null) {
            int zza = zza(parcel, i3);
            parcel.writeSparseBooleanArray(sparseBooleanArray);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeSparseIntArray(@NonNull Parcel parcel, int i3, @NonNull SparseIntArray sparseIntArray, boolean z2) {
        if (sparseIntArray != null) {
            int zza = zza(parcel, i3);
            int size = sparseIntArray.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(sparseIntArray.keyAt(i4));
                parcel.writeInt(sparseIntArray.valueAt(i4));
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeSparseLongArray(@NonNull Parcel parcel, int i3, @NonNull SparseLongArray sparseLongArray, boolean z2) {
        if (sparseLongArray != null) {
            int zza = zza(parcel, i3);
            int size = sparseLongArray.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(sparseLongArray.keyAt(i4));
                parcel.writeLong(sparseLongArray.valueAt(i4));
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeString(@NonNull Parcel parcel, int i3, @NonNull String str, boolean z2) {
        if (str != null) {
            int zza = zza(parcel, i3);
            parcel.writeString(str);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeStringArray(@NonNull Parcel parcel, int i3, @NonNull String[] strArr, boolean z2) {
        if (strArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeStringArray(strArr);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeStringList(@NonNull Parcel parcel, int i3, @NonNull List<String> list, boolean z2) {
        if (list != null) {
            int zza = zza(parcel, i3);
            parcel.writeStringList(list);
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static void writeStringSparseArray(@NonNull Parcel parcel, int i3, @NonNull SparseArray<String> sparseArray, boolean z2) {
        if (sparseArray != null) {
            int zza = zza(parcel, i3);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(sparseArray.keyAt(i4));
                parcel.writeString(sparseArray.valueAt(i4));
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedArray(@NonNull Parcel parcel, int i3, @NonNull T[] tArr, int i4, boolean z2) {
        if (tArr != null) {
            int zza = zza(parcel, i3);
            parcel.writeInt(r7);
            for (T t2 : tArr) {
                if (t2 == null) {
                    parcel.writeInt(0);
                } else {
                    zzd(parcel, t2, i4);
                }
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedList(@NonNull Parcel parcel, int i3, @NonNull List<T> list, boolean z2) {
        if (list != null) {
            int zza = zza(parcel, i3);
            int size = list.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                Parcelable parcelable = (Parcelable) list.get(i4);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    zzd(parcel, parcelable, 0);
                }
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedSparseArray(@NonNull Parcel parcel, int i3, @NonNull SparseArray<T> sparseArray, boolean z2) {
        if (sparseArray != null) {
            int zza = zza(parcel, i3);
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i4 = 0; i4 < size; i4++) {
                parcel.writeInt(sparseArray.keyAt(i4));
                Parcelable parcelable = (Parcelable) sparseArray.valueAt(i4);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    zzd(parcel, parcelable, 0);
                }
            }
            zzb(parcel, zza);
        } else if (z2) {
            zzc(parcel, i3, 0);
        }
    }

    private static int zza(Parcel parcel, int i3) {
        parcel.writeInt(i3 | -65536);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void zzb(Parcel parcel, int i3) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i3 - 4);
        parcel.writeInt(dataPosition - i3);
        parcel.setDataPosition(dataPosition);
    }

    private static void zzc(Parcel parcel, int i3, int i4) {
        parcel.writeInt(i3 | (i4 << 16));
    }

    private static void zzd(Parcel parcel, Parcelable parcelable, int i3) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i3);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }
}
