package com.github.mikephil.charting.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.Utils;

public class Entry extends BaseEntry implements Parcelable {
    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>() {
        public Entry createFromParcel(Parcel parcel) {
            return new Entry(parcel);
        }

        public Entry[] newArray(int i3) {
            return new Entry[i3];
        }
    };

    /* renamed from: x  reason: collision with root package name */
    private float f6568x;

    public Entry() {
        this.f6568x = 0.0f;
    }

    public Entry copy() {
        return new Entry(this.f6568x, getY(), getData());
    }

    public int describeContents() {
        return 0;
    }

    public boolean equalTo(Entry entry) {
        if (entry == null || entry.getData() != getData()) {
            return false;
        }
        float abs = Math.abs(entry.f6568x - this.f6568x);
        float f2 = Utils.FLOAT_EPSILON;
        return abs <= f2 && Math.abs(entry.getY() - getY()) <= f2;
    }

    public float getX() {
        return this.f6568x;
    }

    public void setX(float f2) {
        this.f6568x = f2;
    }

    public String toString() {
        return "Entry, x: " + this.f6568x + " y: " + getY();
    }

    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeFloat(this.f6568x);
        parcel.writeFloat(getY());
        if (getData() == null) {
            parcel.writeInt(0);
        } else if (getData() instanceof Parcelable) {
            parcel.writeInt(1);
            parcel.writeParcelable((Parcelable) getData(), i3);
        } else {
            throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
        }
    }

    public Entry(float f2, float f3) {
        super(f3);
        this.f6568x = f2;
    }

    public Entry(float f2, float f3, Object obj) {
        super(f3, obj);
        this.f6568x = f2;
    }

    public Entry(float f2, float f3, Drawable drawable) {
        super(f3, drawable);
        this.f6568x = f2;
    }

    public Entry(float f2, float f3, Drawable drawable, Object obj) {
        super(f3, drawable, obj);
        this.f6568x = f2;
    }

    public Entry(Parcel parcel) {
        this.f6568x = 0.0f;
        this.f6568x = parcel.readFloat();
        setY(parcel.readFloat());
        if (parcel.readInt() == 1) {
            setData(parcel.readParcelable(Object.class.getClassLoader()));
        }
    }
}
