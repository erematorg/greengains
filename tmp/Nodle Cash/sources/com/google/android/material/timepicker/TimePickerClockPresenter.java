package com.google.android.material.timepicker;

import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.material.R;
import com.google.android.material.timepicker.ClockHandView;
import com.google.android.material.timepicker.TimePickerView;

class TimePickerClockPresenter implements ClockHandView.OnRotateListener, TimePickerView.OnSelectionChange, TimePickerView.OnPeriodChangeListener, ClockHandView.OnActionUpListener, TimePickerPresenter {
    private static final int DEGREES_PER_HOUR = 30;
    private static final int DEGREES_PER_MINUTE = 6;
    private static final String[] HOUR_CLOCK_24_VALUES = {"00", "1", "2", ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    private static final String[] HOUR_CLOCK_VALUES = {"12", "1", "2", ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9", "10", "11"};
    private static final String[] MINUTE_CLOCK_VALUES = {"00", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
    private boolean broadcasting = false;
    private float hourRotation;
    private float minuteRotation;
    /* access modifiers changed from: private */
    public final TimeModel time;
    private final TimePickerView timePickerView;

    public TimePickerClockPresenter(TimePickerView timePickerView2, TimeModel timeModel) {
        this.timePickerView = timePickerView2;
        this.time = timeModel;
        initialize();
    }

    private String[] getHourClockValues() {
        return this.time.format == 1 ? HOUR_CLOCK_24_VALUES : HOUR_CLOCK_VALUES;
    }

    private int getHourRotation() {
        return (this.time.getHourForDisplay() * 30) % 360;
    }

    private void performHapticFeedback(int i3, int i4) {
        TimeModel timeModel = this.time;
        if (timeModel.minute != i4 || timeModel.hour != i3) {
            this.timePickerView.performHapticFeedback(4);
        }
    }

    private void updateCurrentLevel() {
        TimeModel timeModel = this.time;
        int i3 = 1;
        if (timeModel.selection == 10 && timeModel.format == 1 && timeModel.hour >= 12) {
            i3 = 2;
        }
        this.timePickerView.setCurrentLevel(i3);
    }

    private void updateTime() {
        TimePickerView timePickerView2 = this.timePickerView;
        TimeModel timeModel = this.time;
        timePickerView2.updateTime(timeModel.period, timeModel.getHourForDisplay(), this.time.minute);
    }

    private void updateValues() {
        updateValues(HOUR_CLOCK_VALUES, TimeModel.NUMBER_FORMAT);
        updateValues(MINUTE_CLOCK_VALUES, TimeModel.ZERO_LEADING_NUMBER_FORMAT);
    }

    public void hide() {
        this.timePickerView.setVisibility(8);
    }

    public void initialize() {
        if (this.time.format == 0) {
            this.timePickerView.showToggle();
        }
        this.timePickerView.addOnRotateListener(this);
        this.timePickerView.setOnSelectionChangeListener(this);
        this.timePickerView.setOnPeriodChangeListener(this);
        this.timePickerView.setOnActionUpListener(this);
        updateValues();
        invalidate();
    }

    public void invalidate() {
        this.hourRotation = (float) getHourRotation();
        TimeModel timeModel = this.time;
        this.minuteRotation = (float) (timeModel.minute * 6);
        setSelection(timeModel.selection, false);
        updateTime();
    }

    public void onActionUp(float f2, boolean z2) {
        this.broadcasting = true;
        TimeModel timeModel = this.time;
        int i3 = timeModel.minute;
        int i4 = timeModel.hour;
        if (timeModel.selection == 10) {
            this.timePickerView.setHandRotation(this.hourRotation, false);
            AccessibilityManager accessibilityManager = (AccessibilityManager) ContextCompat.getSystemService(this.timePickerView.getContext(), AccessibilityManager.class);
            if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
                setSelection(12, true);
            }
        } else {
            int round = Math.round(f2);
            if (!z2) {
                this.time.setMinute(((round + 15) / 30) * 5);
                this.minuteRotation = (float) (this.time.minute * 6);
            }
            this.timePickerView.setHandRotation(this.minuteRotation, z2);
        }
        this.broadcasting = false;
        updateTime();
        performHapticFeedback(i4, i3);
    }

    public void onPeriodChange(int i3) {
        this.time.setPeriod(i3);
    }

    public void onRotate(float f2, boolean z2) {
        if (!this.broadcasting) {
            TimeModel timeModel = this.time;
            int i3 = timeModel.hour;
            int i4 = timeModel.minute;
            int round = Math.round(f2);
            TimeModel timeModel2 = this.time;
            if (timeModel2.selection == 12) {
                timeModel2.setMinute((round + 3) / 6);
                this.minuteRotation = (float) Math.floor((double) (this.time.minute * 6));
            } else {
                int i5 = (round + 15) / 30;
                if (timeModel2.format == 1) {
                    i5 %= 12;
                    if (this.timePickerView.getCurrentLevel() == 2) {
                        i5 += 12;
                    }
                }
                this.time.setHour(i5);
                this.hourRotation = (float) getHourRotation();
            }
            if (!z2) {
                updateTime();
                performHapticFeedback(i3, i4);
            }
        }
    }

    public void onSelectionChanged(int i3) {
        setSelection(i3, true);
    }

    public void setSelection(int i3, boolean z2) {
        boolean z3 = i3 == 12;
        this.timePickerView.setAnimateOnTouchUp(z3);
        this.time.selection = i3;
        this.timePickerView.setValues(z3 ? MINUTE_CLOCK_VALUES : getHourClockValues(), z3 ? R.string.material_minute_suffix : this.time.getHourContentDescriptionResId());
        updateCurrentLevel();
        this.timePickerView.setHandRotation(z3 ? this.minuteRotation : this.hourRotation, z2);
        this.timePickerView.setActiveSelection(i3);
        this.timePickerView.setMinuteHourDelegate(new ClickActionDelegate(this.timePickerView.getContext(), R.string.material_hour_selection) {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setContentDescription(view.getResources().getString(TimePickerClockPresenter.this.time.getHourContentDescriptionResId(), new Object[]{String.valueOf(TimePickerClockPresenter.this.time.getHourForDisplay())}));
            }
        });
        this.timePickerView.setHourClickDelegate(new ClickActionDelegate(this.timePickerView.getContext(), R.string.material_minute_selection) {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setContentDescription(view.getResources().getString(R.string.material_minute_suffix, new Object[]{String.valueOf(TimePickerClockPresenter.this.time.minute)}));
            }
        });
    }

    public void show() {
        this.timePickerView.setVisibility(0);
    }

    private void updateValues(String[] strArr, String str) {
        for (int i3 = 0; i3 < strArr.length; i3++) {
            strArr[i3] = TimeModel.formatText(this.timePickerView.getResources(), strArr[i3], str);
        }
    }
}
