/*buildscript {
    repositories{
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
    }
}
allprojects{
    repositories{
        google()
        mavenCentral()
    }
}*/
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.android.tools.build:gradle:7.3.0")
        classpath("com.google.firebase:perf-plugin:1.4.2")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
    id("com.android.test") version "8.1.2" apply false
}