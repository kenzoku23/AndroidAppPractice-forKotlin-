package io.github.kenzoku23.fragmentsample


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class MenuThanksFragment : Fragment() {

    private lateinit var tvMenuName: TextView
    private lateinit var tvMenuPrice: TextView
    private lateinit var btBackButton: Button

    private var isLayoutXlarge = true

    //タブレットの場合、fragmentMenuListは既に存在している。
    //つまり、フラグメントの生成より前から存在しているため、onCreateで判定を行う。
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //タブレットかどうかの判定
        //同一アクティビティにfragmentMenuListがあるかで判断
        if (fragmentManager?.findFragmentById(R.id.fragmentMenuList) == null)
            isLayoutXlarge = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_thanks, container, false)
        tvMenuName = view.findViewById(R.id.tvMenuName)
        tvMenuPrice = view.findViewById(R.id.tvMenuPrice)
        btBackButton = view.findViewById(R.id.btBackButton)

        val extras = if (isLayoutXlarge) {
            arguments
        } else {
            activity?.intent?.extras
        }
        tvMenuName.text = extras?.getString("menuName")
        tvMenuPrice.text = extras?.getString("menuPrice")

        btBackButton.setOnClickListener(ButtonClickListener())
        return view
    }

    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            if (isLayoutXlarge) {
                val transaction = fragmentManager?.beginTransaction()
                //削除するときはremove
                //this@～で～クラスのインスタンスということを表している。
                transaction?.remove(this@MenuThanksFragment)
                transaction?.commit()
            } else {
                activity?.finish()
            }
        }
    }

}
