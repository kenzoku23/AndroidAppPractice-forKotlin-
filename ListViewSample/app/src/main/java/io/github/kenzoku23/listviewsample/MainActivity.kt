package io.github.kenzoku23.listviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    val lvMenu: ListView by lazy { findViewById<ListView>(R.id.lvMenu) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        //parent: AdapterView<*>? イベントの発生したリスト
        //view: View? イベントの発生した行のview
        //pos: Int イベントの発生した行番号0から
        //id: Long
        override fun onItemClick(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            //トーストの実装
            Toast.makeText(
                applicationContext, //トーストを表示させるオブジェクト
                getString(R.string.toast_selectMenu) + parent?.getItemAtPosition(pos).toString(), //表示する文字列
                Toast.LENGTH_LONG //トーストの長さ
            ).show()
        }
    }
}
