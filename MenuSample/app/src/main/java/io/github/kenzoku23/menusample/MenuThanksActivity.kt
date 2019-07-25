package io.github.kenzoku23.menusample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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

        //戻るメニューをアクションバーに設定する。
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
