package com.example.mysololife

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mysololife.auth.IntroActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import java.util.logging.Handler

class SplashActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		auth = Firebase.auth
		setContentView(R.layout.activity_splash)

		if(auth.currentUser?.uid == null){
			Log.d("SplashActivity", "null")
			android.os.Handler().postDelayed({
				startActivity(Intent(this, IntroActivity::class.java))
				finish()
			},3000)
		}else{
			Log.d("SplashActivity", "not null")
			android.os.Handler().postDelayed({
				startActivity(Intent(this, MainActivity::class.java))
				finish()
			},3000)
		}

	}
}