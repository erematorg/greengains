package com.vmadalin.easypermissions.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;
import com.vmadalin.easypermissions.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0017BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0016J\u0006\u0010\u0016\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/vmadalin/easypermissions/dialogs/SettingsDialog;", "Landroid/content/DialogInterface$OnClickListener;", "context", "Landroid/content/Context;", "theme", "", "requestCode", "openOnNewTask", "", "title", "", "rationale", "positiveButtonText", "negativeButtonText", "(Landroid/content/Context;IIZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "dialog", "Landroidx/appcompat/app/AlertDialog;", "onClick", "", "dialogInterface", "Landroid/content/DialogInterface;", "buttonType", "show", "Builder", "easypermissions-ktx_release"}, k = 1, mv = {1, 4, 2})
public final class SettingsDialog implements DialogInterface.OnClickListener {
    private final Context context;
    private AlertDialog dialog;
    private String negativeButtonText;
    private boolean openOnNewTask;
    private String positiveButtonText;
    private String rationale;
    private int requestCode;
    private int theme;
    private String title;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0005\u001a\u00020\u00002\b\b\u0001\u0010\u0011\u001a\u00020\fJ\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\u00002\b\b\u0001\u0010\u0011\u001a\u00020\fJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0006J\u0010\u0010\n\u001a\u00020\u00002\b\b\u0001\u0010\u0011\u001a\u00020\fJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\fJ\u0010\u0010\u000e\u001a\u00020\u00002\b\b\u0001\u0010\u0011\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\f8\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/vmadalin/easypermissions/dialogs/SettingsDialog$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "negativeButtonText", "", "openOnNewTask", "", "positiveButtonText", "rationale", "requestCode", "", "theme", "title", "build", "Lcom/vmadalin/easypermissions/dialogs/SettingsDialog;", "resId", "easypermissions-ktx_release"}, k = 1, mv = {1, 4, 2})
    public static final class Builder {
        private Context context;
        private String negativeButtonText;
        private boolean openOnNewTask;
        private String positiveButtonText;
        private String rationale;
        private int requestCode = 16061;
        @StyleRes
        private int theme;
        private String title;

        public Builder(@NotNull Context context2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
            String string = context2.getString(R.string.title_settings_dialog);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.title_settings_dialog)");
            this.title = string;
            String string2 = this.context.getString(R.string.rationale_ask_again);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.rationale_ask_again)");
            this.rationale = string2;
            String string3 = this.context.getString(17039370);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(android.R.string.ok)");
            this.positiveButtonText = string3;
            String string4 = this.context.getString(17039360);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(android.R.string.cancel)");
            this.negativeButtonText = string4;
        }

        @NotNull
        public final SettingsDialog build() {
            return new SettingsDialog(this.context, this.theme, this.requestCode, this.openOnNewTask, this.title, this.rationale, this.positiveButtonText, this.negativeButtonText);
        }

        @NotNull
        public final Builder negativeButtonText(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "negativeButtonText");
            this.negativeButtonText = str;
            return this;
        }

        @NotNull
        public final Builder openOnNewTask(boolean z2) {
            this.openOnNewTask = z2;
            return this;
        }

        @NotNull
        public final Builder positiveButtonText(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "positiveButtonText");
            this.positiveButtonText = str;
            return this;
        }

        @NotNull
        public final Builder rationale(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "rationale");
            this.rationale = str;
            return this;
        }

        @NotNull
        public final Builder requestCode(int i3) {
            this.requestCode = i3;
            return this;
        }

        @NotNull
        public final Builder theme(@StyleRes int i3) {
            this.theme = i3;
            return this;
        }

        @NotNull
        public final Builder title(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "title");
            this.title = str;
            return this;
        }

        @NotNull
        public final Builder negativeButtonText(@StringRes int i3) {
            String string = this.context.getString(i3);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(resId)");
            this.negativeButtonText = string;
            return this;
        }

        @NotNull
        public final Builder positiveButtonText(@StringRes int i3) {
            String string = this.context.getString(i3);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(resId)");
            this.positiveButtonText = string;
            return this;
        }

        @NotNull
        public final Builder rationale(@StringRes int i3) {
            String string = this.context.getString(i3);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(resId)");
            this.rationale = string;
            return this;
        }

        @NotNull
        public final Builder title(@StringRes int i3) {
            String string = this.context.getString(i3);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(resId)");
            this.title = string;
            return this;
        }
    }

    public SettingsDialog(@NotNull Context context2, @StyleRes int i3, int i4, boolean z2, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.theme = i3;
        this.requestCode = i4;
        this.openOnNewTask = z2;
        this.title = str;
        this.rationale = str2;
        this.positiveButtonText = str3;
        this.negativeButtonText = str4;
    }

    public void onClick(@Nullable DialogInterface dialogInterface, int i3) {
        int i4 = 0;
        if (i3 == -2) {
            Context context2 = this.context;
            if (context2 instanceof Activity) {
                ((Activity) context2).setResult(0);
            }
            AlertDialog alertDialog = this.dialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        } else if (i3 == -1) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", this.context.getPackageName(), (String) null));
            if (this.openOnNewTask) {
                i4 = 268435456;
            }
            intent.setFlags(i4);
            Context context3 = this.context;
            if (context3 instanceof Activity) {
                ((Activity) context3).startActivityForResult(intent, this.requestCode);
            }
        }
    }

    public final void show() {
        this.dialog = new AlertDialog.Builder(this.context, this.theme).setCancelable(false).setTitle((CharSequence) this.title).setMessage((CharSequence) this.rationale).setPositiveButton((CharSequence) this.positiveButtonText, (DialogInterface.OnClickListener) this).setNegativeButton((CharSequence) this.negativeButtonText, (DialogInterface.OnClickListener) this).show();
    }
}
