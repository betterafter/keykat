package com.keykat.presentation.screen.common

import android.content.res.Resources
import androidx.compose.ui.unit.Dp

const val navigationHeight = 72F

val Float.toPx get() = this * Resources.getSystem().displayMetrics.density
val Float.toDp get() = this / Resources.getSystem().displayMetrics.density

val Int.toPx get() = this * Resources.getSystem().displayMetrics.density.toInt()
val Int.toDp get() = this / Resources.getSystem().displayMetrics.density.toInt()

val Dp.toPx get() = this * Resources.getSystem().displayMetrics.density.toInt()