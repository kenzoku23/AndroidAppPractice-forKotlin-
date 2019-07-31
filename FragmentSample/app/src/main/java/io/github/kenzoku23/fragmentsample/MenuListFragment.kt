package io.github.kenzoku23.fragmentsample


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.SimpleAdapter

class MenuListFragment : Fragment() {

    private lateinit var lvMenu: ListView

    private var isLayoutXlarge = true

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

        lvMenu.onItemClickListener = ListItemClickListener()

        return view
    }


    //画面判定をアクティビティの状態によって判定している。
    //onActivityCreatedはアクティビティが生成された後に呼び出されるので、今回のようなアクティビティの状態による画面の判定に適している。
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //タブレットかどうかの判定
        //画面にmenuThanksFrameが存在しないならスマホ画面
        if (activity?.findViewById<FrameLayout>(R.id.menuThanksFrame) == null)
            isLayoutXlarge = false
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(adapter: AdapterView<*>, view: View, pos: Int, id: Long) {
            val menu = adapter.getItemAtPosition(pos) as MutableMap<String, String>

            val bundle = Bundle()
            bundle.putString("menuName", menu["menu"])
            bundle.putString("menuPrice", menu["price"])

            if (isLayoutXlarge) {
                //フラグメントのトランザクション
                //フラグメントの追加、削除をソース内で動的に行うことができる。
                val transaction = fragmentManager?.beginTransaction()   //トランザクションの生成
                val menuThanksFragment = MenuThanksFragment()   //追加や削除を行うフラグメントの生成
                menuThanksFragment.arguments = bundle
                //第1引数の画面部品に第2引数のフラグメントを新たに置く
                //追加ならadd、別のフラグメントと置き換えならreplace
                transaction?.replace(R.id.menuThanksFrame, menuThanksFragment)
                transaction?.commit()   //コミットで反映
            } else {
                val intent = Intent(activity, MenuThanksActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }


}
