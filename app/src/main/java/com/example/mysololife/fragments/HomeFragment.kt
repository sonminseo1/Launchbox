package com.example.mysololife.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysololife.R
import com.example.mysololife.board.BoardInsideActivity
import com.example.mysololife.board.BoardListLVAdapter
import com.example.mysololife.board.BoardModel
import com.example.mysololife.contentsList.BookmarkRVAdapter
import com.example.mysololife.contentsList.ContentModel
import com.example.mysololife.databinding.FragmentHomeBinding
import com.example.mysololife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

	private lateinit var binding : FragmentHomeBinding

	private val TAG = HomeFragment::class.java.simpleName

	private val boardDataList = mutableListOf<BoardModel>()
	private val boardKeyList = mutableListOf<String>()

	private lateinit var boardRVAdapter: BoardListLVAdapter


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		Log.d("HomeFragment", "onCreateView")
		binding = inflate(inflater, R.layout.fragment_home, container, false)

		binding.scheduleTap.setOnClickListener{
			it.findNavController().navigate(R.id.action_homeFragment_to_scheduleFragment)
		}

		binding.talkTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_talkFragment)
		}

		binding.bookmarkTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_bookmarkFragment)
		}

		binding.tipTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_homeFragment_to_tipFragment)
		}

		boardRVAdapter = BoardListLVAdapter(boardDataList)
		binding. boardListView.adapter = boardRVAdapter

		binding.boardListView.setOnItemClickListener { parent, view, position, id ->

			//두번쨰 방법으로는 Firebase에 있는 Board에 대한 데이터의 id를 기반으로 다시 데이터를 받아오는 방법
			val intent = Intent(context, BoardInsideActivity::class.java)
			intent.putExtra("key",boardKeyList[position])
			startActivity(intent)

		}

		binding.imageButton1.setOnClickListener {
			val url = "https://spaceflightnow.com/launch-schedule/"
			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse(url)
			startActivity(intent)
		}
		binding.imageButton2.setOnClickListener {
			val url = "https://www.youtube.com/@SpaceX"
			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse(url)
			startActivity(intent)
		}
		binding.imageButton3.setOnClickListener {
			val url = "https://www.youtube.com/@NASA"
			val intent = Intent(Intent.ACTION_VIEW)
			intent.data = Uri.parse(url)
			startActivity(intent)
		}


		getFBBoardData()
		return binding.root
	}

	private fun getFBBoardData(){
		val postListener = object : ValueEventListener {
			override fun onDataChange(dataSnapshot: DataSnapshot) {

				boardDataList.clear()

				for(dataModel in dataSnapshot.children){

					Log.d(TAG,dataModel.toString())
					dataModel.key

					val item = dataModel.getValue(BoardModel::class.java)
					boardDataList.add(item!!)
					boardKeyList.add(dataModel.key.toString())

				}
				boardKeyList.reverse()
				boardDataList.reverse()

				boardRVAdapter.notifyDataSetChanged()

				Log.d(TAG, boardDataList.toString())

			}

			override fun onCancelled(databaseError: DatabaseError) {
				// Getting Post failed, log a message
				Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
			}
		}
		FBRef.boardRef.addValueEventListener(postListener)
	}

}