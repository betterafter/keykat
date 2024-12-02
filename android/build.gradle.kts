// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp.android) apply false
    alias(libs.plugins.safeargs.kotlin) apply false
    alias(libs.plugins.dagger.hilt) apply false
}