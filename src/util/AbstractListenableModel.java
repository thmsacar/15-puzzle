package util;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListenableModel implements ListenableModel {

    private final List<ModelListener> listeners = new ArrayList<>();

    public void addListener(ModelListener l) {
        listeners.add(l);
    }

    public void removeListener(ModelListener l) {
        listeners.remove(l);
    }

    protected void fireChange() {
        for (ModelListener l : listeners) {
            l.updateModel();
        }
    }

}
