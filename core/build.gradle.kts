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

    //Dagger
    implementation("io.insert-koin:koin-core:${AppModuleVersions.koin}")
    api("io.insert-koin:koin-android:${AppModuleVersions.koin}")

    //RxJava
    api("io.reactivex.rxjava3:rxjava:${CoreModuleVersions.RXJava}")

    //Networking
    implementation("com.squareup.retrofit2:retrofit:${CoreModuleVersions.retrofit}")
    implementation("com.squareup.retrofit2:adapter-rxjava3:${CoreModuleVersions.retrofit}")
    implementation("com.squareup.okhttp3:okhttp:${CoreModuleVersions.okHttp}")
    implementation("com.squareup.retrofit2:converter-gson:${CoreModuleVersions.gson}")

    //Paging
    api("androidx.paging:paging-runtime:${AppModuleVersions.AndroidX.paging}")
    api("androidx.paging:paging-rxjava3:${AppModuleVersions.AndroidX.paging}")

    //Testing

    testImplementation("junit:junit:${CoreModuleVersions.junit}")
    testImplementation("io.mockk:mockk:${CoreModuleVersions.mockk}")
    testImplementation("org.assertj:assertj-core:${CoreModuleVersions.assertJCore}")
    testImplementation("io.insert-koin:koin-test:${CoreModuleVersions.koinTesting}")

    androidTestImplementation("androidx.test.ext:junit-ktx:${CoreModuleVersions.junitKtx}")
    androidTestImplementation("io.insert-koin:koin-test:${CoreModuleVersions.koinTesting}")
    androidTestImplementation("org.assertj:assertj-core:${CoreModuleVersions.assertJCore}")
    androidTestImplementation("androidx.test:runner:${AppModuleVersions.testRunner}")
}