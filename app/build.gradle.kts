plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.projectprm392"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.projectprm392"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation("androidx.navigation:navigation-fragment:2.7.4")
    implementation("androidx.navigation:navigation-ui:2.7.4")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("androidx.room:room-runtime:2.2.5")
    annotationProcessor ("androidx.room:room-compiler:2.2.5")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.facebook.stetho:stetho:1.6.0")
    implementation ("com.facebook.stetho:stetho-okhttp3:1.6.0")
    implementation ("com.squareup.picasso:picasso:2.8")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}