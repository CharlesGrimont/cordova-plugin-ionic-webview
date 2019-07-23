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

            // Many Samsung phones don't show decimal points on html number inputs by default.
            if ((outAttrs.inputType & InputType.TYPE_CLASS_NUMBER) == InputType.TYPE_CLASS_NUMBER)
                outAttrs.inputType |= InputType.TYPE_NUMBER_FLAG_DECIMAL;

            return connection;
        }
    }

    /** Used when created via reflection. */
    public IonicWebViewEngine(Context context, CordovaPreferences preferences) {
        this(new IonicWebViewEngine(context), preferences);
    }

    public IonicWebViewEngine(SystemWebView webView) {    
        super(webView);
        webView.loadUrl("javascript:(function() { " +
              "window.C8O_SPECIAL_WEBVIEW = true;" +
              "})()");
    }
    public IonicWebViewEngine(SystemWebView webView, CordovaPreferences preferences) {
        super(webView, preferences);
        webView.loadUrl("javascript:(function() { " +
              "window.C8O_SPECIAL_WEBVIEW = true;" +
              "})()");
    }
}