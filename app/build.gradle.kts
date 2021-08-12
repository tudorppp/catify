plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    buildToolsVersion(Config.buildToolsVersion)

    defaultConfig {
        applicationId("com.example.baseproject")
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode(Config.versionCode)
        versionName(Config.versionName)

        testInstrumentationRunner(Config.androidTestInstrumentationRunner)
    }

    buildTypes {
        getByName(BuildType.Release.getName()) {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}

dependencies {
    implementation(project(":core"))

    implementation("androidx.core:core-ktx:${Version.AndroidX.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Version.AndroidX.appCompat}")
    implementation("androidx.constraintlayout:constraintlayout:${Version.AndroidX.constraintLayout}")

    implementation("androidx.navigation:navigation-fragment-ktx:${Version.AndroidX.navigationComponent}")
    implementation("androidx.navigation:navigation-ui-ktx:${Version.AndroidX.navigationComponent}")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Version.AndroidX.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.AndroidX.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${Version.AndroidX.lifecycle}")
    implementation("androidx.security:security-crypto:${Version.AndroidX.securityCrypto}")
    
    implementation("androidx.fragment:fragment-testing:${Version.AndroidX.coreKtx}")

    implementation("com.google.android.material:material:${Version.materialComponents}")

    implementation("io.insert-koin:koin-androidx-scope:${Version.koin}")
    implementation("io.insert-koin:koin-androidx-viewmodel:${Version.koin}")

    implementation("io.reactivex.rxjava3:rxandroid:${Version.RXJava}")

    implementation("com.github.bumptech.glide:glide:${Version.glide}")
    implementation("com.github.bumptech.glide:annotations:${Version.glide}")
    annotationProcessor("com.github.bumptech.glide:compiler:${Version.glide}")


    androidTestImplementation("androidx.arch.core:core-testing:${Version.AndroidX.testArch}")
    androidTestImplementation("androidx.test.ext:junit-ktx:${Version.Test.junitKtx}")
    androidTestImplementation("org.assertj:assertj-core:${Version.Test.assertJCore}")

    androidTestImplementation("androidx.navigation:navigation-testing:${Version.AndroidX.navigationComponent}")
    androidTestImplementation("com.google.truth:truth:${Version.Test.truth}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Version.Test.espresso}")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:${Version.Test.espresso}")
    androidTestImplementation("androidx.test.espresso:espresso-intents:${Version.Test.espresso}")
}