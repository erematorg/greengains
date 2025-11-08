package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new Parcelable.Creator<Month>() {
        @NonNull
        public Month createFromParcel(@NonNull Parcel parcel) {
            return Month.create(parcel.readInt(), parcel.readInt());
        }

        @NonNull
        public Month[] newArray(int i3) {
            return new Month[i3];
        }
    };
    final int daysInMonth;
    final int daysInWeek;
    @NonNull
    private final Calendar firstOfMonth;
    @Nullable
    private String longName;
    final int month;
    final long timeInMillis;
    final int year;

    private Month(@NonNull Calendar calendar) {
        calendar.set(5, 1);
        Calendar dayCopy = UtcDates.getDayCopy(calendar);
        this.firstOfMonth = dayCopy;
        this.month = dayCopy.get(2);
        this.year = dayCopy.get(1);
        this.daysInWeek = dayCopy.getMaximum(7);
        this.daysInMonth = dayCopy.getActualMaximum(5);
        this.timeInMillis = dayCopy.getTimeInMillis();
    }

    @NonNull
    public static Month create(long j2) {
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.setTimeInMillis(j2);
        return new Month(utcCalendar);
    }

    @NonNull
    public static Month current() {
        return new Month(UtcDates.getTodayCalendar());
    }

    public int daysFromStartOfWeekToFirstOfMonth(int i3) {
        int i4 = this.firstOfMonth.get(7);
        if (i3 <= 0) {
            i3 = this.firstOfMonth.getFirstDayOfWeek();
        }
        int i5 = i4 - i3;
        return i5 < 0 ? i5 + this.daysInWeek : i5;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month2 = (Month) obj;
        return this.month == month2.month && this.year == month2.year;
    }

    public long getDay(int i3) {
        Calendar dayCopy = UtcDates.getDayCopy(this.firstOfMonth);
        dayCopy.set(5, i3);
        return dayCopy.getTimeInMillis();
    }

    public int getDayOfMonth(long j2) {
        Calendar dayCopy = UtcDates.getDayCopy(this.firstOfMonth);
        dayCopy.setTimeInMillis(j2);
        return dayCopy.get(5);
    }

    @NonNull
    public String getLongName() {
        if (this.longName == null) {
            this.longName = DateStrings.getYearMonth(this.firstOfMonth.getTimeInMillis());
        }
        return this.longName;
    }

    public long getStableId() {
        return this.firstOfMonth.getTimeInMillis();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.month), Integer.valueOf(this.year)});
    }

    @NonNull
    public Month monthsLater(int i3) {
        Calendar dayCopy = UtcDates.getDayCopy(this.firstOfMonth);
        dayCopy.add(2, i3);
        return new Month(dayCopy);
    }

    public int monthsUntil(@NonNull Month month2) {
        if (this.firstOfMonth instanceof GregorianCalendar) {
            return (month2.month - this.month) + ((month2.year - this.year) * 12);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        parcel.writeInt(this.year);
        parcel.writeInt(this.month);
    }

    public int compareTo(@NonNull Month month2) {
        return this.firstOfMonth.compareTo(month2.firstOfMonth);
    }

    @NonNull
    public static Month create(int i3, int i4) {
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.set(1, i3);
        utcCalendar.set(2, i4);
        return new Month(utcCalendar);
    }
}
