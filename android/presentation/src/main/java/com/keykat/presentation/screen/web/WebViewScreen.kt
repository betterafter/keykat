package com.keykat.presentation.screen.web

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Build
import android.view.MotionEvent
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@SuppressLint("SetJavaScriptEnabled", "ClickableViewAccessibility")
@Composable
fun WebViewScreen(
    navController: NavController,
    url: String,
) {
    var webView: WebView? = null

    BackHandler(enabled = true) {
        if (webView != null && webView!!.canGoBack()) {
            webView!!.goBack()
        } else {
            navController.popBackStack()
            webView?.destroy()
        }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .nestedScroll(remember { object : NestedScrollConnection {} }),

        factory = {
            WebView(it).apply {
                settings.apply {
                    // 필수 설정
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                    mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
                    userAgentString =
                        "Mozilla/5.0 (Linux; Android ${Build.VERSION.RELEASE}) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Mobile Safari/537.36"
                    isVerticalScrollBarEnabled = true
                }
                isNestedScrollingEnabled = true
                webViewClient =
                    object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            request?.url?.let { uri ->
                                view?.loadUrl(uri.toString())
                                return true
                            }

                            return super.shouldOverrideUrlLoading(view, request)
                        }
                    }
                setOnTouchListener { v, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            println("Action down")
                            // WebView 터치 시 부모 뷰의 스크롤 이벤트 차단
                            v.parent.requestDisallowInterceptTouchEvent(true)
                        }

                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                            // 터치 해제 시 부모 뷰의 스크롤 이벤트 허용
                            v.parent.requestDisallowInterceptTouchEvent(false)
                        }
                    }
                    v.onTouchEvent(event)
                }

            }
        }, update = {
            webView = it
            it.loadUrl(url)
        }
    )
}