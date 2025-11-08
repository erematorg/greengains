package com.google.android.material.datepicker;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import com.google.android.material.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

class DateStrings {
    private DateStrings() {
    }

    public static Pair<String, String> getDateRangeString(@Nullable Long l2, @Nullable Long l3) {
        return getDateRangeString(l2, l3, (SimpleDateFormat) null);
    }

    public static String getDateString(long j2) {
        return getDateString(j2, (SimpleDateFormat) null);
    }

    public static String getDayContentDescription(Context context, long j2, boolean z2, boolean z3, boolean z4) {
        String optionalYearMonthDayOfWeekDay = getOptionalYearMonthDayOfWeekDay(j2);
        if (z2) {
            optionalYearMonthDayOfWeekDay = String.format(context.getString(R.string.mtrl_picker_today_description), new Object[]{optionalYearMonthDayOfWeekDay});
        }
        return z3 ? String.format(context.getString(R.string.mtrl_picker_start_date_description), new Object[]{optionalYearMonthDayOfWeekDay}) : z4 ? String.format(context.getString(R.string.mtrl_picker_end_date_description), new Object[]{optionalYearMonthDayOfWeekDay}) : optionalYearMonthDayOfWeekDay;
    }

    public static String getMonthDay(long j2) {
        return getMonthDay(j2, Locale.getDefault());
    }

    public static String getMonthDayOfWeekDay(long j2) {
        return getMonthDayOfWeekDay(j2, Locale.getDefault());
    }

    public static String getOptionalYearMonthDayOfWeekDay(long j2) {
        return isDateWithinCurrentYear(j2) ? getMonthDayOfWeekDay(j2) : getYearMonthDayOfWeekDay(j2);
    }

    public static String getYearContentDescription(Context context, int i3) {
        return UtcDates.getTodayCalendar().get(1) == i3 ? String.format(context.getString(R.string.mtrl_picker_navigate_to_current_year_description), new Object[]{Integer.valueOf(i3)}) : String.format(context.getString(R.string.mtrl_picker_navigate_to_year_description), new Object[]{Integer.valueOf(i3)});
    }

    public static String getYearMonth(long j2) {
        return UtcDates.getYearMonthFormat(Locale.getDefault()).format(new Date(j2));
    }

    public static String getYearMonthDay(long j2) {
        return getYearMonthDay(j2, Locale.getDefault());
    }

    public static String getYearMonthDayOfWeekDay(long j2) {
        return getYearMonthDayOfWeekDay(j2, Locale.getDefault());
    }

    private static boolean isDateWithinCurrentYear(long j2) {
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.setTimeInMillis(j2);
        return todayCalendar.get(1) == utcCalendar.get(1);
    }

    public static Pair<String, String> getDateRangeString(@Nullable Long l2, @Nullable Long l3, @Nullable SimpleDateFormat simpleDateFormat) {
        if (l2 == null && l3 == null) {
            return Pair.create(null, null);
        }
        if (l2 == null) {
            return Pair.create(null, getDateString(l3.longValue(), simpleDateFormat));
        }
        if (l3 == null) {
            return Pair.create(getDateString(l2.longValue(), simpleDateFormat), null);
        }
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.setTimeInMillis(l2.longValue());
        Calendar utcCalendar2 = UtcDates.getUtcCalendar();
        utcCalendar2.setTimeInMillis(l3.longValue());
        if (simpleDateFormat != null) {
            return Pair.create(simpleDateFormat.format(new Date(l2.longValue())), simpleDateFormat.format(new Date(l3.longValue())));
        } else if (utcCalendar.get(1) != utcCalendar2.get(1)) {
            return Pair.create(getYearMonthDay(l2.longValue(), Locale.getDefault()), getYearMonthDay(l3.longValue(), Locale.getDefault()));
        } else {
            if (utcCalendar.get(1) == todayCalendar.get(1)) {
                return Pair.create(getMonthDay(l2.longValue(), Locale.getDefault()), getMonthDay(l3.longValue(), Locale.getDefault()));
            }
            return Pair.create(getMonthDay(l2.longValue(), Locale.getDefault()), getYearMonthDay(l3.longValue(), Locale.getDefault()));
        }
    }

    public static String getDateString(long j2, @Nullable SimpleDateFormat simpleDateFormat) {
        if (simpleDateFormat != null) {
            return simpleDateFormat.format(new Date(j2));
        }
        if (isDateWithinCurrentYear(j2)) {
            return getMonthDay(j2);
        }
        return getYearMonthDay(j2);
    }

    public static String getMonthDay(long j2, Locale locale) {
        return UtcDates.getAbbrMonthDayFormat(locale).format(new Date(j2));
    }

    public static String getMonthDayOfWeekDay(long j2, Locale locale) {
        return UtcDates.getMonthWeekdayDayFormat(locale).format(new Date(j2));
    }

    public static String getYearMonthDay(long j2, Locale locale) {
        return UtcDates.getYearAbbrMonthDayFormat(locale).format(new Date(j2));
    }

    public static String getYearMonthDayOfWeekDay(long j2, Locale locale) {
        return UtcDates.getYearMonthWeekdayDayFormat(locale).format(new Date(j2));
    }
}
