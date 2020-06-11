package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_menu_lower.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.title = "Edit"
        actionBar?.setBackgroundDrawable(ColorDrawable(0xff6bbaff.toInt()))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_edit)

        // titlenote.addTextChangedListener(object: TextWatcher{
        //override fun afterTextChanged(s: Editable) {
        //    if (s.filter {char-> char.isDigit()}.isNotEmpty()) {
        //        Toast.makeText(this@EditActivity, "AAAAAAAAA", Toast.LENGTH_SHORT ).show()
        //    }
        //}
//
        //override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //    //TODO("Not yet implemented")
        // }
//
        //  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //     //TODO("Not yet implemented")
        // }
        //      })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.edit_menu_upper, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}



