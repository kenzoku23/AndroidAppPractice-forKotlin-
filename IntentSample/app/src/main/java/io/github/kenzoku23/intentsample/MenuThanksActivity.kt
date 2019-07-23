package io.github.kenzoku23.intentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MenuThanksActivity : AppCompatActivity() {

    private val tvMenuName: TextView by lazy { findViewById<TextView>(R.id.tvMenuName) }
    private val tvMenuPrice: TextView by lazy { findViewById<TextView>(R.id.tvMenuPrice) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)

        //intentから渡ってきた値を受け取る。
        tvMenuName.text = intent.getStringExtra("name")
        tvMenuPrice.text = intent.getStringExtra("price")
    }

    fun onBackButtonClick(view: View){
        //finishは自身を終了させる。
        //→呼び出し先の画面が表に出てくる。
        finish()
    }
}
