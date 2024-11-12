package com.example.mysololife.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.setContentView
import com.example.mysololife.MainActivity
import com.example.mysololife.R
import com.example.mysololife.databinding.ActivityIntroBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class IntroActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	private lateinit var binding : ActivityIntroBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		auth = Firebase.auth

		binding = DataBindingUtil.setContentView(this,R.layout.activity_intro)

		binding.loginBtn.setOnClickListener{
			val intent = Intent(this, LoginActivity::class.java)
			startActivity(intent)
		}

		binding.joinBtn.setOnClickListener {
			val intent = Intent(this, JoinActivity::class.java)
			startActivity(intent)
		}

		binding.noAccountBtn.setOnClickListener {

			auth.signInAnonymously()
				.addOnCompleteListener(this) { task ->
					if (task.isSuccessful) {

						Toast.makeText(this,"로그인 성공", Toast.LENGTH_LONG).show()

						val intent = Intent(this, MainActivity::class.java)
						intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
						startActivity(intent)

					} else {

						Toast.makeText(this,"로그인 실패", Toast.LENGTH_LONG).show()


					}
				}


		}
	}
}