package com.example.mysololife.board

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.mysololife.R
import com.example.mysololife.databinding.ActivityBoardEditBinding
import com.example.mysololife.databinding.ActivityBoardWriteBinding
import com.example.mysololife.utils.FBAuth
import com.example.mysololife.utils.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.lang.Exception

class BoardEditActivity : AppCompatActivity() {

	private lateinit var key: String

	private lateinit var binding: ActivityBoardEditBinding

	private val TAG = BoardEditActivity::class.java.simpleName

	private lateinit var writerUid : String

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = DataBindingUtil.setContentView(this,R.layout.activity_board_edit)

		key = intent.getStringExtra("key").toString()
		getBoardData(key)
		getImageData(key)

		binding.editBtn.setOnClickListener {
			editBoardData(key)
		}

	}

	private fun editBoardData(key: String){
		FBRef.boardRef
			.child(key)
			.setValue(
				BoardModel(binding.titleArea.text.toString(),
				binding.contentArea.text.toString(),
				writerUid,
				FBAuth.getTime())
			)
		Toast.makeText(this,"수정완료", Toast.LENGTH_LONG).show()

		finish()
	}

	private fun getImageData(key : String){

		val storageReference = Firebase.storage.reference.child(key+".png")


		val imageViewFromFB = binding.imageArea

		storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener{ task ->
			if(task.isSuccessful){

				Glide.with(this)
					.load(task.result)
					.into(imageViewFromFB)
			}else{

			}
		})
	}

	private fun getBoardData(key : String){

		val postListener = object : ValueEventListener {
			override fun onDataChange(dataSnapshot: DataSnapshot) {

				val dataModel = dataSnapshot.getValue(BoardModel::class.java)

				binding.titleArea.setText(dataModel?.title)
				binding.contentArea.setText(dataModel?.content)
				writerUid = dataModel!!.uid
			}

			override fun onCancelled(databaseError: DatabaseError) {
				// Getting Post failed, log a message
				Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
			}
		}
		FBRef.boardRef.child(key).addValueEventListener(postListener)

	}
}