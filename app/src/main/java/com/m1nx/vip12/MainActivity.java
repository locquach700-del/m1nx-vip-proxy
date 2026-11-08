package com.m1nx.vip12;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setAllowFileAccessFromFileURLs(true);
        ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        ws.setUserAgentString("M1NX-VIP/3.0 Android");

        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new NativeBridge(), "NativeBridge");
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    class NativeBridge {
        @JavascriptInterface
        public void copyText(String text) {
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText("M1NX Key", text));
            runOnUiThread(() -> Toast.makeText(MainActivity.this,
                "\u2713 Da sao chep: " + text.substring(0, Math.min(9, text.length())) + "...",
                Toast.LENGTH_SHORT).show());
        }

        @JavascriptInterface
        public String generateKey() {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            Random r = new Random();
            StringBuilder sb = new StringBuilder();
            for (int seg = 0; seg < 4; seg++) {
                if (seg > 0) sb.append('-');
                for (int i = 0; i < 4; i++)
                    sb.append(chars.charAt(r.nextInt(chars.length())));
            }
            return sb.toString();
        }

        @JavascriptInterface
        public void showToast(String msg) {
            runOnUiThread(() -> Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show());
        }
    }
}
