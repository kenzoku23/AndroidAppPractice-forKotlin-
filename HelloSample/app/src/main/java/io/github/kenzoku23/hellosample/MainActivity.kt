package io.github.kenzoku23.hellosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

//AppCompatActivityにはAPIの下位互換等を実現するサポートライブラリ
class MainActivity : AppCompatActivity() {

    val etName by lazy { findViewById<EditText>(R.id.etName) }
    val tvOutput by lazy { findViewById<TextView>(R.id.tvOutput) }
    val btClick by lazy { findViewById<Button>(R.id.btClick) }
    val btCear by lazy { findViewById<Button>(R.id.btClear) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Rとはxmlに記載したstringやファイルを識別する為のid(Int)がまとめられているクラス
        setContentView(R.layout.activity_main)

        btClick.setOnClickListener { tvOutput.text = etName.text.toString() }
        btCear.setOnClickListener { tvOutput.text = "" }
    }
}
