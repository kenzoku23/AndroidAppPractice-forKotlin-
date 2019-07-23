package io.github.kenzoku23.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewDebug
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {

    //companionObject内で定数を宣言するとクラスが読み込まれた時点で初期化される。
    companion object {
        private val FROM = arrayOf("name", "price")
        private val TO = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)
    }
    private val _menuList: MutableList<MutableMap<String, Any>> by lazy { createTeishokuList() }
    private val lvMenu: ListView by lazy { findViewById<ListView>(R.id.lvMenu) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvMenu.adapter = SimpleAdapter(applicationContext, _menuList, R.layout.row,  FROM, TO)
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        menuList.add(mutableMapOf("name" to "ラーメン",
                "price" to "750",
                "desc" to "職人の汗がたっぷり染み込んだ逸品です。"))
        menuList.add(mutableMapOf("name" to "味噌ラーメン",
                "price" to "850",
                "desc" to "味噌汁が嫌いな職人が何とか形にしました。"))
        return menuList
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
            val item = parent.getItemAtPosition(pos) as MutableMap<String, Any>
            val intent = Intent(applicationContext, MenuThanksActivity::class.java)
            intent.putExtra("name", item["name"] as String)
            intent.putExtra("price", item["price"] as String + "円")
            startActivity(intent)
        }
    }
}
