
package com.hisense.smarthome.sdk.http;

import com.hisense.smarthome.sdk.network.RequestQueue;
import com.hisense.smarthome.sdk.network.toolbox.Volley;

public class CustomRequestQueue {
    private static RequestQueue requestQueue = null;

    private CustomRequestQueue() {
    }

    public static RequestQueue getRequestQueue() {
        synchronized (CustomRequestQueue.class) {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(null);
            }
        }
        return requestQueue;
    }
}
