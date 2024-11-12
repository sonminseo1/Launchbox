package com.example.mysololife.utils

import com.google.firebase.Firebase
import com.google.firebase.database.database

class FBRef {

	companion object{
		private val database = Firebase.database

		val category1 = database.getReference("contents")
		val bookmarkRef = database.getReference("bookmark_list")
		val boardRef = database.getReference("board")
		val commentRef = database.getReference("comment")
	}
}