// Top-level build file for the whole project

// Plugin management for sub-projects/modules
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// Keep the buildscript dependencies if needed, but REMOVE the repositories block
buildscript {
    dependencies {
        // Google services plugin for Firebase
        classpath("com.google.gms:google-services:4.4.4")
    }
}


// Optional: configuration for tasks across all subprojects
subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}