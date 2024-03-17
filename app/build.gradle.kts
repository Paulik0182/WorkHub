plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-android")
    id("kotlin-kapt")
//    id("com.google.devtools.ksp")
}

var GOOGLE_MAPS_API_KEY_VALUE = "GOOGLE_MAPS_API_KEY"

android {
    namespace = "com.nayya.workhub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nayya.workhub"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//        val localProps = Properties()
//        localProps.load(FileInputStream(file("../local.properties")))
//
//        val apiKey = localProps.getProperty("apiKey")
//        buildConfigField("String", "API_KEY", apiKey)
        //BuildConfig.API_KEY - в код


//        manifestPlaceholders.put("GOOGLE_MAPS_API_KEY",
//            localProps.getProperty("GOOGLE_MAPS_API_KEY")
//        )

        resValue("string", "GOOGLE_MAPS_API_KEY", GOOGLE_MAPS_API_KEY_VALUE)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Android X
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // Android X Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // recyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // viewModel
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    val lifecycle_version = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${lifecycle_version}")

    // Network
    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.9.0")

    // cardView
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.cardview:cardview:1.0.0")

    // Material
    implementation("com.google.android.material:material:1.11.0")

    // ROOM
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    //    kapt 'androidx.room:room-compiler:2.5.0'
    kapt("org.xerial:sqlite-jdbc:3.41.2.2") // для процессоров М1

    // SharedPreferences
    implementation("androidx.preference:preference-ktx:1.2.1")

    // glide
    implementation("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.2")

    // FlexboxLayout
    implementation("com.google.android.flexbox:flexbox:3.0.0")

    // Google maps
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}