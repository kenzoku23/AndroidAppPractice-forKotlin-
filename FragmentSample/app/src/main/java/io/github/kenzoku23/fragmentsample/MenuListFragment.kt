package io.github.kenzoku23.fragmentsample


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter

class MenuListFragment : Fragment() {

    private lateinit var lvMenu: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //fragment画面を生成
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        //fragmentクラスにはfindViewByIdがないため、inflateしたviewから使用する。
        lvMenu = view.findViewById(R.id.lvMenu)

        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        menuList.add(mutableMapOf("menu" to "ラーメン", "price" to "700円"))
        menuList.add(mutableMapOf("menu" to "豚骨ラーメン", "price" to "700円"))
        menuList.add(mutableMapOf("menu" to "味噌ラーメン", "price" to "800円"))

        lvMenu.adapter = SimpleAdapter(
            activity, //fragmentはコンテキストにはなりえないため、activity(現在所属する画面のアクティビティ)を指定する。
            menuList,
            android.R.layout.simple_expandable_list_item_2,
            arrayOf("menu", "price"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        // Inflate the layout for this fragment
        return view
    }


}
