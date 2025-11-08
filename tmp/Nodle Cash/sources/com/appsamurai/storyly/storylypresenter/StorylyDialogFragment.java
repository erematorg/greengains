package com.appsamurai.storyly.storylypresenter;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Keep;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import com.appsamurai.storyly.R;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b(\u0010)J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J$\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0012\u001a\u00020\u0004J\u0006\u0010\u0013\u001a\u00020\u0004R,\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u00148\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR,\u0010\u001c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u00148\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u0016\u0010!\u001a\u00020\u00158B@\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0016\u0010$\u001a\u00020\f8B@\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0016\u0010&\u001a\u00020%8B@\u0002X\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006*"}, d2 = {"Lcom/appsamurai/storyly/storylypresenter/StorylyDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "Landroid/app/Dialog;", "onCreateDialog", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "onDestroyView", "Landroidx/fragment/app/Fragment;", "fragment", "showExternalFragment", "dismissAllFragments", "dismissLatestFragment", "Ljava/lang/ref/WeakReference;", "Lcom/appsamurai/storyly/storylypresenter/b;", "internalStorylyDialog", "Ljava/lang/ref/WeakReference;", "getInternalStorylyDialog$storyly_release", "()Ljava/lang/ref/WeakReference;", "setInternalStorylyDialog$storyly_release", "(Ljava/lang/ref/WeakReference;)V", "internalRootView", "getInternalRootView$storyly_release", "setInternalRootView$storyly_release", "getStorylyDialog", "()Lcom/appsamurai/storyly/storylypresenter/b;", "storylyDialog", "getRootView", "()Landroid/view/View;", "rootView", "", "isParentActivityRunning", "()Z", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyDialogFragment extends DialogFragment {
    @Nullable
    private WeakReference<View> internalRootView;
    @Nullable
    private WeakReference<b> internalStorylyDialog;

    private final View getRootView() {
        WeakReference<View> weakReference = this.internalRootView;
        View view = weakReference == null ? null : weakReference.get();
        Intrinsics.checkNotNull(view);
        Intrinsics.checkNotNullExpressionValue(view, "internalRootView?.get()!!");
        return view;
    }

    private final b getStorylyDialog() {
        WeakReference<b> weakReference = this.internalStorylyDialog;
        b bVar = weakReference == null ? null : weakReference.get();
        Intrinsics.checkNotNull(bVar);
        Intrinsics.checkNotNullExpressionValue(bVar, "internalStorylyDialog?.get()!!");
        return bVar;
    }

    private final boolean isParentActivityRunning() {
        Lifecycle lifecycle;
        Lifecycle.State currentState;
        FragmentActivity activity = getActivity();
        if (activity == null || (lifecycle = activity.getLifecycle()) == null || (currentState = lifecycle.getCurrentState()) == null) {
            return false;
        }
        return currentState.isAtLeast(Lifecycle.State.RESUMED);
    }

    public final void dismissAllFragments() {
        if (isAdded() && isParentActivityRunning() && !getChildFragmentManager().getFragments().isEmpty()) {
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            Intrinsics.checkNotNullExpressionValue(fragments, "childFragmentManager.fragments");
            for (Fragment remove : fragments) {
                beginTransaction.remove(remove);
            }
            beginTransaction.commit();
            if (getStorylyDialog().isShowing()) {
                getStorylyDialog().c();
            }
        }
    }

    public final void dismissLatestFragment() {
        if (isAdded() && isParentActivityRunning()) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            Intrinsics.checkNotNullExpressionValue(fragments, "childFragmentManager.fragments");
            Fragment fragment = (Fragment) CollectionsKt.lastOrNull(fragments);
            if (fragment != null) {
                getChildFragmentManager().beginTransaction().remove(fragment).commitNow();
                if (getChildFragmentManager().getFragments().size() == 0) {
                    getStorylyDialog().c();
                }
            }
        }
    }

    @Nullable
    public final WeakReference<View> getInternalRootView$storyly_release() {
        return this.internalRootView;
    }

    @Nullable
    public final WeakReference<b> getInternalStorylyDialog$storyly_release() {
        return this.internalStorylyDialog;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (this.internalStorylyDialog == null) {
            dismiss();
        }
    }

    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        return getStorylyDialog();
    }

    @NotNull
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        getStorylyDialog().setContentView(R.layout.st_empty_dialog);
        return getRootView();
    }

    public void onDestroyView() {
        this.internalStorylyDialog = null;
        this.internalRootView = null;
        super.onDestroyView();
    }

    public final void setInternalRootView$storyly_release(@Nullable WeakReference<View> weakReference) {
        this.internalRootView = weakReference;
    }

    public final void setInternalStorylyDialog$storyly_release(@Nullable WeakReference<b> weakReference) {
        this.internalStorylyDialog = weakReference;
    }

    public final void showExternalFragment(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        if (isAdded() && isParentActivityRunning()) {
            b storylyDialog = getStorylyDialog();
            storylyDialog.b().d();
            storylyDialog.b().a();
            getChildFragmentManager().beginTransaction().addToBackStack((String) null).add(getStorylyDialog().f4752g.f4384b.getId(), fragment).commit();
        }
    }
}
