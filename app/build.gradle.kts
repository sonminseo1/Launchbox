plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	id("com.google.gms.google-services")
	id("kotlin-kapt")
}
android {
	namespace = "com.example.mysololife"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.example.mysololife"
		minSdk = 30
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

		vectorDrawables.useSupportLibrary = true
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

	buildFeatures{
		dataBinding = true
		viewBinding = true
	}
}

dependencies {

	implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	implementation(libs.firebase.auth)
	implementation(libs.androidx.navigation.fragment.ktx)
	implementation(libs.androidx.navigation.ui.ktx)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)

	implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
	implementation("com.google.firebase:firebase-analytics")

	implementation("com.google.firebase:firebase-auth-ktx")

	implementation("com.github.bumptech.glide:glide:4.12.0")
	annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
	implementation("com.google.firebase:firebase-database-ktx")

	implementation("com.google.firebase:firebase-storage")
	implementation("com.firebaseui:firebase-ui-storage:7.2.0")
	implementation("com.github.prolificinteractive:material-calendarview:2.0.1")

}

dependencies {

	val lifecycle_version = "2.8.4"

	implementation("androidx.viewpager2:viewpager2:1.1.0")

	// ViewModel
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

	// LiveData
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

	val room_version = "2.6.1"

	implementation("androidx.room:room-runtime:$room_version")
	annotationProcessor("androidx.room:room-compiler:$room_version")
	kapt("androidx.room:room-compiler:$room_version")
	implementation("androidx.room:room-ktx:$room_version")
	implementation ("androidx.room:room-testing:$room_version")
	implementation("androidx.fragment:fragment-ktx:1.1.0")



}
