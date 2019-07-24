package com.ionicframework.cordova.webview;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;

public class IonicWebViewEngine extends SystemWebViewEngine {
    public static class HackedWebView extends SystemWebView {
        public HackedWebView(Context context) {
            super(context);
        }
        public HackedWebView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
            InputConnection connection = super.onCreateInputConnection(outAttrs);

            if((outAttrs.inputType & EditorInfo.TYPE_MASK_CLASS) == EditorInfo.TYPE_CLASS_TEXT){
                outAttrs.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD; //This is the only flag that works on Samsung devices to remove auto-suggestions
            }

            return connection;
        }
    }

    /** Used when created via reflection. */
    public IonicWebViewEngine(Context context, CordovaPreferences preferences) {
        this(new HackedWebView(context), preferences);
    }

    public IonicWebViewEngine(SystemWebView webView) {    
        super(webView);
    }
    public IonicWebViewEngine(SystemWebView webView, CordovaPreferences preferences) {
        super(webView, preferences);
    }
}