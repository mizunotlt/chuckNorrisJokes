package com.example.chucnorrisjokes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.chucnorrisjokes.R
import com.example.chucnorrisjokes.utils.WebClient




class BrowserFragment : Fragment(){

    companion object {
        fun newInstance() = BrowserFragment()
    }

    private lateinit var browserView: WebView
    lateinit var viewBrowser: View
    private var webViewBundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBrowser = inflater.inflate(R.layout.browser_fragment, container, false)
        browserView = viewBrowser.findViewById(R.id.webView)
        browserView.webViewClient = WebClient()
        if( webViewBundle != null){
            browserView.restoreState(webViewBundle)
        }
        else {
            if (savedInstanceState != null ){
                browserView.restoreState(savedInstanceState)
            }
            else{
                browserView.settings.javaScriptEnabled = true
                browserView.loadUrl("http://www.icndb.com/api/")
            }
        }
        return viewBrowser
    }

    fun onBackPressed(){
        if (browserView.canGoBack())
            browserView.goBack()
    }

    override fun onSaveInstanceState(saveBundle: Bundle) {
        super.onSaveInstanceState(saveBundle)
        browserView.saveState(saveBundle)
    }

    override fun onPause() {
        super.onPause()
        super.onPause()
        webViewBundle = Bundle()
        browserView.saveState(webViewBundle)
    }
}
