package io.github.kenzoku23.databasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private val _helper = DatabaseHelper(this@MainActivity)
    private var cocktailId: Long = -1
    private var cocktailName = ""

    private val lvCocktail: ListView by lazy { findViewById<ListView>(R.id.lvCocktail) }
    private val tvCocktailName: TextView by lazy { findViewById<TextView>(R.id.tvCocktailName) }
    private val etNote: EditText by lazy { findViewById<EditText>(R.id.etNote) }
    private val btnSave: Button by lazy { findViewById<Button>(R.id.btnSave) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvCocktail.onItemClickListener = ListItemClickListener()
    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }

    //xmlから参照するには、private属性を外す必要がある。
    fun onSaveButtonClick(view: View) {
        val note = etNote.text.toString()
        val db = _helper.writableDatabase

        val sqlDelete = "DELETE FROM cocktailmemos WHERE _id = ?"
        var stmt = db.compileStatement(sqlDelete)
        stmt.bindLong(1, cocktailId)
        stmt.executeUpdateDelete()

        val sqlInsert = "INSERT INTO cocktailmemos (_id, name, note) VALUES (?, ?, ?)"
        stmt = db.compileStatement(sqlInsert)
        stmt.bindLong(1, cocktailId)
        stmt.bindString(2, cocktailName)
        stmt.bindString(3, note)
        stmt.executeInsert()

        etNote.setText("")
        tvCocktailName.text = getString(R.string.tv_name)
        btnSave.isEnabled = false
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(adapter: AdapterView<*>, view: View, pos: Int, id: Long) {
            cocktailId = id
            cocktailName = adapter.getItemAtPosition(pos) as String
            tvCocktailName.text = cocktailName
            btnSave.isEnabled = true

            val db = _helper.writableDatabase
            val sqlSelect = "SELECT * FROM cocktailmemos WHERE _id = $cocktailId"

            //deleteやinsert同様、selectもrawQueryの第2引数でbindをすることはできるが、型がStringの配列なので直接埋め込んだ方が楽
            val cursor = db.rawQuery(sqlSelect, null)
            var note = ""
            while (cursor.moveToNext()) {
                val idxNote = cursor.getColumnIndex("note")
                note = cursor.getString(idxNote)
            }
            etNote.setText(note)
        }
    }
}
