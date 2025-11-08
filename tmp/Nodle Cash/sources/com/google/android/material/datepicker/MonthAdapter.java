package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;
import java.util.Collection;

class MonthAdapter extends BaseAdapter {
    private static final int MAXIMUM_GRID_CELLS = ((UtcDates.getUtcCalendar().getMaximum(7) + UtcDates.getUtcCalendar().getMaximum(5)) - 1);
    static final int MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
    private static final int NO_DAY_NUMBER = -1;
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final DateSelector<?> dateSelector;
    @Nullable
    final DayViewDecorator dayViewDecorator;
    final Month month;
    private Collection<Long> previouslySelectedDates;

    public MonthAdapter(Month month2, DateSelector<?> dateSelector2, CalendarConstraints calendarConstraints2, @Nullable DayViewDecorator dayViewDecorator2) {
        this.month = month2;
        this.dateSelector = dateSelector2;
        this.calendarConstraints = calendarConstraints2;
        this.dayViewDecorator = dayViewDecorator2;
        this.previouslySelectedDates = dateSelector2.getSelectedDays();
    }

    private String getDayContentDescription(Context context, long j2) {
        return DateStrings.getDayContentDescription(context, j2, isToday(j2), isStartOfRange(j2), isEndOfRange(j2));
    }

    private void initializeStyles(Context context) {
        if (this.calendarStyle == null) {
            this.calendarStyle = new CalendarStyle(context);
        }
    }

    private boolean isSelected(long j2) {
        for (Long longValue : this.dateSelector.getSelectedDays()) {
            if (UtcDates.canonicalYearMonthDay(j2) == UtcDates.canonicalYearMonthDay(longValue.longValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isToday(long j2) {
        return UtcDates.getTodayCalendar().getTimeInMillis() == j2;
    }

    private void updateSelectedState(@Nullable TextView textView, long j2, int i3) {
        boolean z2;
        CalendarItemStyle calendarItemStyle;
        TextView textView2 = textView;
        long j3 = j2;
        if (textView2 != null) {
            Context context = textView.getContext();
            String dayContentDescription = getDayContentDescription(context, j3);
            textView2.setContentDescription(dayContentDescription);
            boolean isValid = this.calendarConstraints.getDateValidator().isValid(j3);
            if (isValid) {
                textView2.setEnabled(true);
                boolean isSelected = isSelected(j3);
                textView2.setSelected(isSelected);
                calendarItemStyle = isSelected ? this.calendarStyle.selectedDay : isToday(j3) ? this.calendarStyle.todayDay : this.calendarStyle.day;
                z2 = isSelected;
            } else {
                textView2.setEnabled(false);
                z2 = false;
                calendarItemStyle = this.calendarStyle.invalidDay;
            }
            DayViewDecorator dayViewDecorator2 = this.dayViewDecorator;
            if (dayViewDecorator2 == null || i3 == -1) {
                calendarItemStyle.styleItem(textView2);
                return;
            }
            Month month2 = this.month;
            int i4 = month2.year;
            int i5 = month2.month;
            Context context2 = context;
            int i6 = i4;
            int i7 = i3;
            boolean z3 = isValid;
            int i8 = i5;
            ColorStateList backgroundColor = dayViewDecorator2.getBackgroundColor(context2, i6, i5, i7, z3, z2);
            boolean z4 = z2;
            calendarItemStyle.styleItem(textView2, backgroundColor, this.dayViewDecorator.getTextColor(context2, i6, i8, i7, z3, z4));
            Drawable compoundDrawableLeft = this.dayViewDecorator.getCompoundDrawableLeft(context2, i6, i8, i7, z3, z4);
            Drawable compoundDrawableTop = this.dayViewDecorator.getCompoundDrawableTop(context2, i6, i8, i7, z3, z4);
            String str = dayContentDescription;
            Drawable compoundDrawableRight = this.dayViewDecorator.getCompoundDrawableRight(context2, i6, i8, i7, z3, z4);
            boolean z5 = z2;
            textView2.setCompoundDrawables(compoundDrawableLeft, compoundDrawableTop, compoundDrawableRight, this.dayViewDecorator.getCompoundDrawableBottom(context2, i6, i8, i7, z3, z5));
            textView2.setContentDescription(this.dayViewDecorator.getContentDescription(context2, i6, i8, i7, z3, z5, str));
        }
    }

    private void updateSelectedStateForDate(MaterialCalendarGridView materialCalendarGridView, long j2) {
        if (Month.create(j2).equals(this.month)) {
            int dayOfMonth = this.month.getDayOfMonth(j2);
            updateSelectedState((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().dayToPosition(dayOfMonth) - materialCalendarGridView.getFirstVisiblePosition()), j2, dayOfMonth);
        }
    }

    public int dayToPosition(int i3) {
        return firstPositionInMonth() + (i3 - 1);
    }

    public int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth(this.calendarConstraints.getFirstDayOfWeek());
    }

    public int getCount() {
        return MAXIMUM_GRID_CELLS;
    }

    public long getItemId(int i3) {
        return (long) (i3 / this.month.daysInWeek);
    }

    public boolean hasStableIds() {
        return true;
    }

    @VisibleForTesting
    public boolean isEndOfRange(long j2) {
        for (Pair<Long, Long> pair : this.dateSelector.getSelectedRanges()) {
            S s3 = pair.second;
            if (s3 != null && ((Long) s3).longValue() == j2) {
                return true;
            }
        }
        return false;
    }

    public boolean isFirstInRow(int i3) {
        return i3 % this.month.daysInWeek == 0;
    }

    public boolean isLastInRow(int i3) {
        return (i3 + 1) % this.month.daysInWeek == 0;
    }

    @VisibleForTesting
    public boolean isStartOfRange(long j2) {
        for (Pair<Long, Long> pair : this.dateSelector.getSelectedRanges()) {
            F f2 = pair.first;
            if (f2 != null && ((Long) f2).longValue() == j2) {
                return true;
            }
        }
        return false;
    }

    public int lastPositionInMonth() {
        return (firstPositionInMonth() + this.month.daysInMonth) - 1;
    }

    public int positionToDay(int i3) {
        return (i3 - firstPositionInMonth()) + 1;
    }

    public void updateSelectedStates(MaterialCalendarGridView materialCalendarGridView) {
        for (Long longValue : this.previouslySelectedDates) {
            updateSelectedStateForDate(materialCalendarGridView, longValue.longValue());
        }
        DateSelector<?> dateSelector2 = this.dateSelector;
        if (dateSelector2 != null) {
            for (Long longValue2 : dateSelector2.getSelectedDays()) {
                updateSelectedStateForDate(materialCalendarGridView, longValue2.longValue());
            }
            this.previouslySelectedDates = this.dateSelector.getSelectedDays();
        }
    }

    public boolean withinMonth(int i3) {
        return i3 >= firstPositionInMonth() && i3 <= lastPositionInMonth();
    }

    @Nullable
    public Long getItem(int i3) {
        if (i3 < firstPositionInMonth() || i3 > lastPositionInMonth()) {
            return null;
        }
        return Long.valueOf(this.month.getDay(positionToDay(i3)));
    }

    /* JADX WARNING: type inference failed for: r7v9, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0063 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0064  */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.widget.TextView getView(int r6, @androidx.annotation.Nullable android.view.View r7, @androidx.annotation.NonNull android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            r5.initializeStyles(r0)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L_0x001e
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = com.google.android.material.R.layout.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L_0x001e:
            int r7 = r5.firstPositionInMonth()
            int r7 = r6 - r7
            if (r7 < 0) goto L_0x0054
            com.google.android.material.datepicker.Month r8 = r5.month
            int r2 = r8.daysInMonth
            if (r7 < r2) goto L_0x002d
            goto L_0x0054
        L_0x002d:
            r2 = 1
            int r7 = r7 + r2
            r0.setTag(r8)
            android.content.res.Resources r8 = r0.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r4 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r4, r3)
            r0.setText(r8)
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L_0x005d
        L_0x0054:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
            r7 = -1
        L_0x005d:
            java.lang.Long r6 = r5.getItem((int) r6)
            if (r6 != 0) goto L_0x0064
            return r0
        L_0x0064:
            long r1 = r6.longValue()
            r5.updateSelectedState(r0, r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.MonthAdapter.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }
}
