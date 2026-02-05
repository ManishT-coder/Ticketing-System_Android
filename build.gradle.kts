// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // This tells Gradle the versions, but doesn't "apply" them to the root project
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
}