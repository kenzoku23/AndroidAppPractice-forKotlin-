package io.github.kenzoku23.intentsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {

    private val lvMenu: ListView by lazy { findViewById<ListView>(R.id.lvMenu) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        menuList.add(mutableMapOf("name" to "ラーメン", "price" to "700円"))
        menuList.add(mutableMapOf("name" to "味噌ラーメン", "price" to "750円"))
        menuList.add(mutableMapOf("name" to "塩ラーメン", "price" to "700円"))

        //simple_expandable_list_item_2にはtextViewが2つ埋め込まれており、そのidがandroid.R.id.text1, android.R.id.text2
        //
        lvMenu.adapter = SimpleAdapter(
            applicationContext, //コンテキスト
            menuList, //リストデータ
            android.R.layout.simple_expandable_list_item_2, //リストのレイアウトid
            arrayOf("name", "price"), //リストのviewに割り当てるMapキー
            intArrayOf(android.R.id.text1, android.R.id.text2) //↑のキーに対応したデータを割り当てるview
        )
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
            val item = parent.getItemAtPosition(pos) as MutableMap<String, String>


            val intent = Intent(
                applicationContext, //コンテキスト
                MenuThanksActivity::class.java //起動するactivity
            )                                  //::class.javaでjavaのクラスとしてkotlinのクラスを指定できる。
            //遷移先のactivityに値を渡す。
            intent.putExtra("name" //キー
                , item["name"] //値
            )
            intent.putExtra("price", item["price"])
            startActivity(intent)
        }
    }
}
