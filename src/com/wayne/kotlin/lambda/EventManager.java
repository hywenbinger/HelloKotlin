package com.wayne.kotlin.lambda;

import java.util.HashSet;

public class EventManager {

    private HashSet<OnEventListener> onEventListeners = new HashSet<>();

    public interface OnEventListener {
        void onEvent(int event);
    }

    public void addEventListener(OnEventListener listener) {
        onEventListeners.add(listener);
        System.out.println("addEventListener, size = " + onEventListeners.size());
    }

    public void removeEventListener(OnEventListener listener) {
        onEventListeners.remove(listener);
        System.out.println("removeEventListener, size = " + onEventListeners.size());
    }

}
