package com.google.android.material.timepicker;

import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

class TimePickerTextInputKeyController implements TextView.OnEditorActionListener, View.OnKeyListener {
    private final ChipTextInputComboView hourLayoutComboView;
    private boolean keyListenerRunning = false;
    private final ChipTextInputComboView minuteLayoutComboView;
    private final TimeModel time;

    public TimePickerTextInputKeyController(ChipTextInputComboView chipTextInputComboView, ChipTextInputComboView chipTextInputComboView2, TimeModel timeModel) {
        this.hourLayoutComboView = chipTextInputComboView;
        this.minuteLayoutComboView = chipTextInputComboView2;
        this.time = timeModel;
    }

    private void clearPrefilledText(EditText editText) {
        if (editText.getSelectionStart() == 0 && editText.length() == 2) {
            editText.getText().clear();
        }
    }

    private void moveSelection(int i3) {
        boolean z2 = false;
        this.minuteLayoutComboView.setChecked(i3 == 12);
        ChipTextInputComboView chipTextInputComboView = this.hourLayoutComboView;
        if (i3 == 10) {
            z2 = true;
        }
        chipTextInputComboView.setChecked(z2);
        this.time.selection = i3;
    }

    private boolean onHourKeyPress(int i3, KeyEvent keyEvent, EditText editText) {
        Editable text = editText.getText();
        if (text == null) {
            return false;
        }
        if (i3 >= 7 && i3 <= 16 && keyEvent.getAction() == 1 && editText.getSelectionStart() == 2 && text.length() == 2) {
            moveSelection(12);
            return true;
        }
        clearPrefilledText(editText);
        return false;
    }

    private boolean onMinuteKeyPress(int i3, KeyEvent keyEvent, EditText editText) {
        if (i3 == 67 && keyEvent.getAction() == 0 && TextUtils.isEmpty(editText.getText())) {
            moveSelection(10);
            return true;
        }
        clearPrefilledText(editText);
        return false;
    }

    public void bind() {
        TextInputLayout textInput = this.hourLayoutComboView.getTextInput();
        TextInputLayout textInput2 = this.minuteLayoutComboView.getTextInput();
        EditText editText = textInput.getEditText();
        EditText editText2 = textInput2.getEditText();
        editText.setImeOptions(268435461);
        editText2.setImeOptions(268435462);
        editText.setOnEditorActionListener(this);
        editText.setOnKeyListener(this);
        editText2.setOnKeyListener(this);
    }

    public boolean onEditorAction(TextView textView, int i3, KeyEvent keyEvent) {
        boolean z2 = i3 == 5;
        if (z2) {
            moveSelection(12);
        }
        return z2;
    }

    public boolean onKey(View view, int i3, KeyEvent keyEvent) {
        if (this.keyListenerRunning) {
            return false;
        }
        this.keyListenerRunning = true;
        EditText editText = (EditText) view;
        boolean onMinuteKeyPress = this.time.selection == 12 ? onMinuteKeyPress(i3, keyEvent, editText) : onHourKeyPress(i3, keyEvent, editText);
        this.keyListenerRunning = false;
        return onMinuteKeyPress;
    }
}
