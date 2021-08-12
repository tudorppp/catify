plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        testInstrumentationRunner(Config.androidTestInstrumentationRunner)
        consumerProguardFiles(Config.proguardConsumerRules)
    }

    buildTypes {
        getByName(BuildType.Release.getName()) {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }

    packagingOptions {
        exclude("META-INF/koin-core.kotlin_module")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${ProjectLevelVersions.kotlinVersion}")

    implementation("io.insert-koin:koin-core:${Version.koin}")
    api("io.insert-koin:koin-android:${Version.koin}")

    api("io.reactivex.rxjava3:rxjava:${Version.RXJava}")

    implementation("com.squareup.retrofit2:retrofit:${Version.retrofit}")
    implementation("com.squareup.retrofit2:adapter-rxjava3:${Version.retrofit}")
    implementation("com.squareup.okhttp3:okhttp:${Version.okHttp}")
    implementation("com.squareup.retrofit2:converter-gson:${Version.gson}")

    api("androidx.paging:paging-runtime:${Version.AndroidX.paging}")
    api("androidx.paging:paging-rxjava3:${Version.AndroidX.paging}")


    testImplementation("junit:junit:${Version.Test.junit}")
    testImplementation("io.mockk:mockk:${Version.Test.mockk}")
    testImplementation("org.assertj:assertj-core:${Version.Test.assertJCore}")
    testImplementation("io.insert-koin:koin-test:${Version.Test.koinTesting}")

    androidTestImplementation("androidx.test.ext:junit-ktx:${Version.Test.junitKtx}")
    androidTestImplementation("io.insert-koin:koin-test:${Version.Test.koinTesting}")
    androidTestImplementation("org.assertj:assertj-core:${Version.Test.assertJCore}")
    androidTestImplementation("androidx.test:runner:${Version.Test.testRunner}")
}