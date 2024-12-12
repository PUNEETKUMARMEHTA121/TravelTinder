// Define the versions as top-level properties
val kotlinVersion = "1.7.20"
val materialVersion = "1.7.0"
val hiltVersion = "2.42"
val hiltCompilerVersion = "1.0.0"
val assistedInjectVersion = "0.6.0"
val navigationVersion = "2.5.3"
val coroutinesVersion = "1.5.1"
val activityVersion = "1.6.1"
val fragmentVersion = "1.5.4"
val lifecycleVersion = "2.2.0"
val glideVersion = "4.13.0"
val workVersion = "2.1.0"
val recyclerViewVersion = "1.2.0-beta01"
val dokkaVersion = "1.6.0"
val composeVersion = "1.2.0-beta02"
val firebaseBomVersion = "31.0.3"
val realmAdapterVersion = "4.0.0"

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.0-beta02")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.20")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.8")
        classpath("com.google.gms:google-services:4.4.2")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
}

allprojects {
    repositories {
//        maven { url "https://jitpack.io" }
        jcenter()
        google()
        mavenCentral()
    }
}
