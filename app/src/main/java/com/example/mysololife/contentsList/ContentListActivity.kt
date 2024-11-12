package com.example.mysololife.contentsList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysololife.R
import com.example.mysololife.utils.FBAuth
import com.example.mysololife.utils.FBRef
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ContentListActivity : AppCompatActivity() {

	lateinit var myRef : DatabaseReference

	val bookmarkIdList = mutableListOf<String>()

	lateinit var rvAdapter: ContentRVAdapter

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_content_list)

		val items = ArrayList<ContentModel>()
		val itemKeyList = ArrayList<String>()

		rvAdapter = ContentRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)

		val database = Firebase.database

		val category = intent.getStringExtra("category")



		if(category == "category1") {
			myRef = database.getReference("contents")
		}
		if(category == "category2"){
			myRef = database.getReference("contents2")
		}
		if(category == "category3"){
			myRef = database.getReference("contents3")
		}
		if(category == "category4"){
			myRef = database.getReference("contents4")
		}
		if(category == "category5"){
			myRef = database.getReference("contents5")
		}
		if(category == "category6"){
			myRef = database.getReference("contents6")
		}
		if(category == "category7"){
			myRef = database.getReference("contents7")
		}
		if(category == "category8"){
			myRef = database.getReference("contents8")
		}
		if(category == "category9"){
			myRef = database.getReference("contents9")
		}
		if(category == "category10"){
			myRef = database.getReference("contents10")
		}
		if(category == "category11"){
			myRef = database.getReference("contents11")
		}
		if(category == "category12"){
			myRef = database.getReference("contents12")
		}
		if(category == "category13"){
			myRef = database.getReference("contents13")
		}
		if(category == "category14"){
			myRef = database.getReference("contents14")
		}

		val postListener = object : ValueEventListener {
			override fun onDataChange(dataSnapshot: DataSnapshot) {

				for(dataModel in dataSnapshot.children){
					Log.d("ContentListActivity", dataModel.toString())
					Log.d("ContentListActivity", dataModel.key.toString())
					val item = dataModel.getValue(ContentModel::class.java)
					items.add(item!!)
					itemKeyList.add(dataModel.key.toString())
				}
				rvAdapter.notifyDataSetChanged()
				Log.d("ContentListActivity", items.toString())
			}

			override fun onCancelled(databaseError: DatabaseError) {
				// Getting Post failed, log a message
				Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
			}
		}
		myRef.addValueEventListener(postListener)

		val rv : RecyclerView = findViewById(R.id.rv)

		rv.adapter = rvAdapter

		rv.layoutManager = GridLayoutManager(this,2)

		getBookmarkData()

	}

	private fun getBookmarkData(){

		val postListener = object : ValueEventListener {
			override fun onDataChange(dataSnapshot: DataSnapshot) {

				for(dataModel in dataSnapshot.children){
					bookmarkIdList.add(dataModel.key.toString())
				}
				rvAdapter.notifyDataSetChanged()
			}

			override fun onCancelled(databaseError: DatabaseError) {
				// Getting Post failed, log a message
				Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
			}
		}
		FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
	}



}