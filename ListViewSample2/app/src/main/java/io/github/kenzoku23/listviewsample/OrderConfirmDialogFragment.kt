package io.github.kenzoku23.listviewsample

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class OrderConfirmDialogFragment : DialogFragment() {
    //ダイアログの実装
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setTitle(R.string.dialog_title)//タイトル(任意)
        dialogBuilder.setMessage(R.string.dialog_msg)//コンテンツエリア
        dialogBuilder.setPositiveButton(R.string.dialog_btn_ok, DialogButtonClickListener())//アクションボタン(1つは必須)
        dialogBuilder.setNegativeButton(R.string.dialog_btn_ng, DialogButtonClickListener())//アクションボタン
        dialogBuilder.setNeutralButton(R.string.dialog_btn_nu, DialogButtonClickListener())//アクションボタン
        return dialogBuilder.create()
    }

    private inner class DialogButtonClickListener : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            val msg = when (which) {
                DialogInterface.BUTTON_POSITIVE -> getString(R.string.dialog_toast_ok)
                DialogInterface.BUTTON_NEGATIVE -> getString(R.string.dialog_toast_ng)
                DialogInterface.BUTTON_NEUTRAL -> getString(R.string.dialog_toast_nu)
                else -> ""
            }
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        }

    }
}