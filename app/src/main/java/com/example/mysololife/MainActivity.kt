package com.example.mysololife

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mysololife.auth.IntroActivity
import com.example.mysololife.setting.SettingActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {

		auth = Firebase.auth

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		findViewById<ImageView>(R.id.settingBtn).setOnClickListener {
			val intent = Intent(this, SettingActivity::class.java)
			startActivity(intent)
		}




	}
}