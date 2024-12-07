package com.keykat.presentation.screen.web

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.MotionEvent
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@SuppressLint("SetJavaScriptEnabled", "ClickableViewAccessibility")
@Composable
fun WebViewScreen(
    navController: NavController,
    url: String,
) {
    var webView: WebView? = null
    val currentContext = LocalContext.current
    val uri = Uri.parse(url)


    if (uri.scheme != "http" && uri.scheme != "https") {
        LaunchedEffect(url) {
            currentContext.startActivity(Intent(Intent.ACTION_VIEW, uri))
            navController.popBackStack()
        }

        return
    }

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
            .background(Color.White)
            .nestedScroll(remember { object : NestedScrollConnection {} }),

        factory = {
            WebView(it).apply {
                settings.apply {
                    // 필수 설정
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                    mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
                    isVerticalScrollBarEnabled = true
                }
                isNestedScrollingEnabled = true
                webViewClient =
                    object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            val requestUrl = request?.url?.toString()
                            Log.d("WebViewScreen", "Request URL: $requestUrl")

                            request?.url?.let { uri ->
                                Log.d("WebViewScreen", "Scheme: ${uri.scheme}")

                                return when {
                                    uri.scheme == "http" || uri.scheme == "https" -> {
                                        view?.loadUrl(uri.toString())
                                        true
                                    }

                                    else -> try {
                                        Log.d("WebViewScreen", "Opening external app for URI: $uri")
                                        currentContext.startActivity(
                                            Intent(
                                                Intent.ACTION_VIEW,
                                                uri
                                            )
                                        )
                                        true
                                    } catch (e: ActivityNotFoundException) {
                                        Log.e("WebViewScreen", "Activity not found: ${e.message}")
                                        false
                                    }
                                }
                            }

                            return false
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