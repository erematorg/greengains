package com.google.android.material.datepicker;

import android.widget.BaseAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Calendar;

class DaysOfWeekAdapter extends BaseAdapter {
    private static final int CALENDAR_DAY_STYLE = 4;
    private static final int NARROW_FORMAT = 4;
    @NonNull
    private final Calendar calendar;
    private final int daysInWeek;
    private final int firstDayOfWeek;

    public DaysOfWeekAdapter() {
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        this.calendar = utcCalendar;
        this.daysInWeek = utcCalendar.getMaximum(7);
        this.firstDayOfWeek = utcCalendar.getFirstDayOfWeek();
    }

    private int positionToDayOfWeek(int i3) {
        int i4 = i3 + this.firstDayOfWeek;
        int i5 = this.daysInWeek;
        return i4 > i5 ? i4 - i5 : i4;
    }

    public int getCount() {
        return this.daysInWeek;
    }

    public long getItemId(int i3) {
        return 0;
    }

    /* JADX WARNING: type inference failed for: r5v7, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    @android.annotation.SuppressLint({"WrongConstant"})
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r4, @androidx.annotation.Nullable android.view.View r5, @androidx.annotation.NonNull android.view.ViewGroup r6) {
        /*
            r3 = this;
            r0 = r5
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r5 != 0) goto L_0x0017
            android.content.Context r5 = r6.getContext()
            android.view.LayoutInflater r5 = android.view.LayoutInflater.from(r5)
            int r0 = com.google.android.material.R.layout.mtrl_calendar_day_of_week
            r1 = 0
            android.view.View r5 = r5.inflate(r0, r6, r1)
            r0 = r5
            android.widget.TextView r0 = (android.widget.TextView) r0
        L_0x0017:
            java.util.Calendar r5 = r3.calendar
            int r4 = r3.positionToDayOfWeek(r4)
            r1 = 7
            r5.set(r1, r4)
            android.content.res.Resources r4 = r0.getResources()
            android.content.res.Configuration r4 = r4.getConfiguration()
            java.util.Locale r4 = r4.locale
            java.util.Calendar r5 = r3.calendar
            int r2 = CALENDAR_DAY_STYLE
            java.lang.String r4 = r5.getDisplayName(r1, r2, r4)
            r0.setText(r4)
            android.content.Context r4 = r6.getContext()
            int r5 = com.google.android.material.R.string.mtrl_picker_day_of_week_column_header
            java.lang.String r4 = r4.getString(r5)
            java.util.Calendar r3 = r3.calendar
            r5 = 2
            java.util.Locale r6 = java.util.Locale.getDefault()
            java.lang.String r3 = r3.getDisplayName(r1, r5, r6)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r0.setContentDescription(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.DaysOfWeekAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Nullable
    public Integer getItem(int i3) {
        if (i3 >= this.daysInWeek) {
            return null;
        }
        return Integer.valueOf(positionToDayOfWeek(i3));
    }

    public DaysOfWeekAdapter(int i3) {
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        this.calendar = utcCalendar;
        this.daysInWeek = utcCalendar.getMaximum(7);
        this.firstDayOfWeek = i3;
    }
}
