package com.example.mysololife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mysololife.R
import com.example.mysololife.calendar.Memo
import com.example.mysololife.calendar.MemoViewModel
import com.example.mysololife.calendar.TodoAdapter
import com.example.mysololife.calendar.dialog.MyCustomDialog
import com.example.mysololife.calendar.dialog.MyCustomDialogInterface

import com.example.mysololife.databinding.FragmentScheduleBinding
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.Calendar


class ScheduleFragment : Fragment(), MyCustomDialogInterface {

	private lateinit var binding: FragmentScheduleBinding
	private val memoViewModel: MemoViewModel by viewModels() // 뷰모델 연결
	private val adapter : TodoAdapter by lazy { TodoAdapter(memoViewModel) } // 어댑터 선언

	private var year : Int = 0
	private var month : Int = 0
	private var day : Int = 0


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)

		adapter.setHasStableIds(true)

		// 아이템을 가로로 하나씩 보여주고 어댑터 연결
		binding!!.calendarRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
		binding!!.calendarRecyclerview.adapter = adapter


		binding!!.calendarView.setOnDateChangeListener { _, year, month, day ->
			// 날짜 선택시 그 날의 정보 할당
			this.year = year
			this.month = month+1
			this.day = day

			binding!!.calendarDateText.text = "${this.year}/${this.month}/${this.day}"

			// 해당 날짜 데이터를 불러옴 (currentData 변경)
			memoViewModel.readDateData(this.year,this.month,this.day)
		}

		// 메모 데이터가 수정되었을 경우 날짜 데이터를 불러옴 (currentData 변경)
		memoViewModel.readAllData.observe(viewLifecycleOwner, {
			memoViewModel.readDateData(year, month, day)
		})

		// 현재 날짜 데이터 리스트(currentData) 관찰하여 변경시 어댑터에 전달해줌
		memoViewModel.currentData.observe(viewLifecycleOwner, Observer {
			adapter.setData(it)
			Log.d("test5", "onCreateView: gg")
		})

		// Fab 클릭시 다이얼로그 띄움
		binding!!.calendarDialogButton.setOnClickListener {
			if(year == 0) {
				Toast.makeText(activity, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()
			}
			else {
				onFabClicked()
			}
		}

		//페이지 이동
		binding.homeTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_scheduleFragment_to_homeFragment)
		}

		binding.talkTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_scheduleFragment_to_talkFragment)
		}

		binding.bookmarkTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_scheduleFragment_to_bookmarkFragment)
		}

		binding.tipTap.setOnClickListener {
			it.findNavController().navigate(R.id.action_scheduleFragment_to_tipFragment)
		}

		return binding.root

	}
	// Fab 클릭시 사용되는 함수
	private fun onFabClicked(){
		val myCustomDialog = MyCustomDialog(activity,this)
		myCustomDialog.show()
	}

	// 프래그먼트는 뷰보다 오래 지속 . 프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리
	override fun onDestroyView() {
		super.onDestroyView()
	}

	override fun onOkButtonClicked(content: String) {
		// 선택된 날짜로 메모를 추가해줌
		val memo = Memo(0,false, content, year, month, day)
		memoViewModel.addMemo(memo)
		Toast.makeText(activity, "추가", Toast.LENGTH_SHORT).show()
	}
}