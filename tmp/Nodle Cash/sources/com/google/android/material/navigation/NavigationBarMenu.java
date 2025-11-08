package com.google.android.material.navigation;

import android.content.Context;
import android.support.v4.media.session.a;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class NavigationBarMenu extends MenuBuilder {
    private final int maxItemCount;
    @NonNull
    private final Class<?> viewClass;

    public NavigationBarMenu(@NonNull Context context, @NonNull Class<?> cls, int i3) {
        super(context);
        this.viewClass = cls;
        this.maxItemCount = i3;
    }

    @NonNull
    public MenuItem addInternal(int i3, int i4, int i5, @NonNull CharSequence charSequence) {
        if (size() + 1 <= this.maxItemCount) {
            stopDispatchingItemsChanged();
            MenuItem addInternal = super.addInternal(i3, i4, i5, charSequence);
            if (addInternal instanceof MenuItemImpl) {
                ((MenuItemImpl) addInternal).setExclusiveCheckable(true);
            }
            startDispatchingItemsChanged();
            return addInternal;
        }
        String simpleName = this.viewClass.getSimpleName();
        StringBuilder w2 = a.w("Maximum number of items supported by ", simpleName, " is ");
        w2.append(this.maxItemCount);
        w2.append(". Limit can be checked with ");
        w2.append(simpleName);
        w2.append("#getMaxItemCount()");
        throw new IllegalArgumentException(w2.toString());
    }

    @NonNull
    public SubMenu addSubMenu(int i3, int i4, int i5, @NonNull CharSequence charSequence) {
        throw new UnsupportedOperationException(this.viewClass.getSimpleName().concat(" does not support submenus"));
    }

    public int getMaxItemCount() {
        return this.maxItemCount;
    }
}
