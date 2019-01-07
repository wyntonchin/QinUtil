/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hisense.smarthome.sdk.network.toolbox;

import com.hisense.smarthome.sdk.network.NetworkResponse;
import com.hisense.smarthome.sdk.network.ParseError;
import com.hisense.smarthome.sdk.network.Request;
import com.hisense.smarthome.sdk.network.Response;
import com.hisense.smarthome.sdk.network.Response.ErrorListener;
import com.hisense.smarthome.sdk.network.Response.Listener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;

/**
 * A canned request for retrieving the response body at a given URL as a String.
 */
public class StringRequest extends Request<String> {
    private Listener<String> mListener;

    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public StringRequest(int method, String url, Listener<String> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    /**
     * Creates a new GET request.
     *
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public StringRequest(String url, Listener<String> listener, ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        mListener = null;
    }

    @Override
    protected void deliverResponse(String response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed = "";
        String encoding = response.headers.get("Content-Encoding");
        if (encoding != null && encoding.trim().toLowerCase().contains("gzip")) {
            GZIPInputStream gStream = null;
            ByteArrayOutputStream outStream = null;
            try {
                gStream = new GZIPInputStream(new ByteArrayInputStream(response.data));
                outStream = new ByteArrayOutputStream();
                byte[] data = new byte[4096];
                int count = -1;
                while ((count = gStream.read(data, 0, 4096)) != -1) {
                    outStream.write(data, 0, count);
                }
                data = null;
                parsed = new String(outStream.toByteArray(),
                        HttpHeaderParser.parseCharset(response.headers));
            } catch (IOException e) {
                return Response.error(new ParseError());
            } finally {
                if (outStream != null) {
                    try {
                        outStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (gStream != null) {
                    try {
                        gStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            try {
                parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}
