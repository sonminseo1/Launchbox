package com.example.mysololife.calendar.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.mysololife.R

class MyCustomDialog(context: FragmentActivity?, myInterface: MyCustomDialogInterface) : Dialog(
	context!!
) {

	// 액티비티에서 인터페이스를 받아옴
	private var myCustomDialogInterface: MyCustomDialogInterface = myInterface

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.layout_dialog)

		var okButton : Button = findViewById(R.id.okButton)
		var cancelButton : Button = findViewById(R.id.cancelButton)
		var memoEditView : EditText = findViewById(R.id.memoEditView)

		// 배경 투명하게 바꿔줌
		window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

		okButton.setOnClickListener {
			val content = memoEditView.text.toString()

			// 입력하지 않았을 때
			if ( TextUtils.isEmpty(content)){
				Toast.makeText(context, "메모를 입력해주세요.", Toast.LENGTH_SHORT).show()
			}

			// 입력 창이 비어 있지 않을 때
			else{
				// 메모를 추가해줌
				myCustomDialogInterface.onOkButtonClicked(content)
				dismiss()
			}
		}

		// 취소 버튼 클릭 시 종료
		cancelButton.setOnClickListener { dismiss()}
	}
}