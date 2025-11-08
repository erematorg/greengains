package com.google.maps.android.collections;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.browser.trusted.c;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.collections.MapObjectManager.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class MapObjectManager<O, C extends Collection> {
    protected final Map<O, C> mAllObjects = new HashMap();
    protected final GoogleMap mMap;
    private final Map<String, C> mNamedCollections = new HashMap();

    public class Collection {
        private final Set<O> mObjects = new HashSet();

        public Collection() {
        }

        public void add(O o3) {
            this.mObjects.add(o3);
            MapObjectManager.this.mAllObjects.put(o3, this);
        }

        public void clear() {
            for (O next : this.mObjects) {
                MapObjectManager.this.removeObjectFromMap(next);
                MapObjectManager.this.mAllObjects.remove(next);
            }
            this.mObjects.clear();
        }

        public java.util.Collection<O> getObjects() {
            return Collections.unmodifiableCollection(this.mObjects);
        }

        public boolean remove(O o3) {
            if (!this.mObjects.remove(o3)) {
                return false;
            }
            MapObjectManager.this.mAllObjects.remove(o3);
            MapObjectManager.this.removeObjectFromMap(o3);
            return true;
        }
    }

    public MapObjectManager(@NonNull GoogleMap googleMap) {
        this.mMap = googleMap;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                MapObjectManager.this.setListenersOnUiThread();
            }
        });
    }

    public C getCollection(String str) {
        return (Collection) this.mNamedCollections.get(str);
    }

    public abstract C newCollection();

    public C newCollection(String str) {
        if (this.mNamedCollections.get(str) == null) {
            C newCollection = newCollection();
            this.mNamedCollections.put(str, newCollection);
            return newCollection;
        }
        throw new IllegalArgumentException(c.a("collection id is not unique: ", str));
    }

    public boolean remove(O o3) {
        Collection collection = (Collection) this.mAllObjects.get(o3);
        return collection != null && collection.remove(o3);
    }

    public abstract void removeObjectFromMap(O o3);

    public abstract void setListenersOnUiThread();
}
