package com.example.mysololife.calendar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoViewModel(application: Application) : AndroidViewModel(application) {
	val readAllData : LiveData<List<Memo>>
	val readDoneData : LiveData<List<Memo>>
	private val repository : MemoRepository

	// get set
	private var _currentData = MutableLiveData<List<Memo>>()
	val currentData : LiveData<List<Memo>>
		get() = _currentData

	init{
		val memoDao = MemoDatabase.getDatabase(application)!!.memoDao()
		repository = MemoRepository(memoDao)
		readAllData = repository.readAllData.asLiveData()
		readDoneData = repository.readDoneData.asLiveData()
	}

	fun addMemo(memo : Memo){
		viewModelScope.launch(Dispatchers.IO) {
			repository.addMemo(memo)
		}
	}

	fun updateMemo(memo : Memo){
		viewModelScope.launch(Dispatchers.IO) {
			repository.updateMemo(memo)
		}
	}

	fun deleteMemo(memo : Memo){
		viewModelScope.launch(Dispatchers.IO) {
			repository.deleteMemo(memo)
		}
	}

	fun readDateData(year : Int, month : Int, day : Int) {
		viewModelScope.launch(Dispatchers.IO) {
			val tmp = repository.readDateData(year, month, day)
			_currentData.postValue(tmp)
		}
	}
}