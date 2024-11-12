package com.example.mysololife.calendar

import kotlinx.coroutines.flow.Flow

class MemoRepository(private val memoDao: MemoDao) {
	val readAllData : Flow<List<Memo>> = memoDao.readAllData()
	val readDoneData : Flow<List<Memo>> = memoDao.readDoneData()

	suspend fun addMemo(memo: Memo){
		memoDao.addMemo(memo)
	}

	suspend fun updateMemo(memo: Memo){
		memoDao.updateMemo(memo)
	}

	suspend fun deleteMemo(memo: Memo){
		memoDao.deleteMemo(memo)
	}

	fun readDateData(year : Int, month : Int, day : Int): List<Memo> {
		return memoDao.readDateData(year, month, day)
	}
}