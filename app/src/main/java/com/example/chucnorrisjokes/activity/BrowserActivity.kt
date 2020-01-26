package com.example.chucnorrisjokes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.chucnorrisjokes.utils.WebClient
import com.example.chucnorrisjokes.R







class BrowserActivity : AppCompatActivity() {

    private lateinit var browserView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        browserView = findViewById(R.id.webView)
        if (savedInstanceState != null){
            browserView.restoreState(savedInstanceState.getBundle("webViewState"))
        }
        else{
            browserView.webViewClient = WebClient()
            browserView.settings.javaScriptEnabled = true
            browserView.loadUrl("http://www.icndb.com/api/")
        }


    }

    override fun onBackPressed() {
        if (browserView.canGoBack()) {
            browserView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(saveBundle: Bundle) {
        super.onSaveInstanceState(saveBundle)
        val bundle = Bundle()
        browserView.saveState(bundle)
        saveBundle.putBundle("webViewState", bundle)
    }
}
