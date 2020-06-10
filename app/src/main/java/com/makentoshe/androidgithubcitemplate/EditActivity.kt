package com.makentoshe.androidgithubcitemplate

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.title = "Edit"
        actionBar?.setDisplayHomeAsUpEnabled(true);
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
    override fun onSupportNavigateUp():Boolean {
        finish()
        return true
    }
    }



