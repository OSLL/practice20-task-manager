package com.makentoshe.androidgithubcitemplate

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView

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

    /* fun acceptTheNote(view: View){
         val acceptIntent = Intent(this, MainActivity::class.java)
         val titleString = editTitle.text.toString()
         val noteString = textField.text.toString()
         acceptIntent.putExtra(MainActivity.acceptedTitle, titleString)
         acceptIntent.putExtra(MainActivity.acceptedNote, noteString)
         startActivity(acceptIntent)
     } */



}



