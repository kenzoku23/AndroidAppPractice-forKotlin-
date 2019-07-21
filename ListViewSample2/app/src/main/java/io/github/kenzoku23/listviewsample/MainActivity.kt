package io.github.kenzoku23.listviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    val lvMenu: ListView by lazy { findViewById<ListView>(R.id.lvMenu) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //アクティビティでリストを生成する方法
        var menuList = mutableListOf("唐揚げ定食", "ラーメン")
        lvMenu.adapter = ArrayAdapter(
            applicationContext, //コンテキスト
            android.R.layout.simple_list_item_1, //レイアウトのリソースid
            menuList //実装するリストデータ
        )
        lvMenu.onItemClickListener = ListitemClickListener()
    }

    private inner class ListitemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            val dialogFragment = OrderConfirmDialogFragment()
            dialogFragment.show(supportFragmentManager, "OrderConfirmDialogFragment")
        }
    }
}
