package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.makentoshe.androidgithubcitemplate.items.Task
import com.makentoshe.androidgithubcitemplate.items.TaskDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_layout.*

class EditActivity : AppCompatActivity() {
    var Title: String = ""
    var Text: String = ""
    var Date: Long = 0
    var Image: String = ""
    var Pin: Boolean = false
    var Bookmark: Int = 0

    var isOpen = false
    var isPinned = false
    //fun pinToggle()
    fun addItem(isPinned: Boolean) {
        var db = TaskDatabase.getDatabase(application)
        var taskDao = db.taskDao()
        var task = Task()
        if (editTitle.text.toString().equals("") and textField.text.toString().equals("")) else {
            task.title=editTitle.text.toString()
            task.text=textField.text.toString()
            task.date=1//TODO
            task.image="a"//TODO
            task.pin=isPinned
            task.bookmark=1//TODO
            taskDao.insert(task)
            taskDao.update(task)
        }
    }
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

        val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        val fabRClockWise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
        val fabRAntiClockWise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)

        add_button.setOnClickListener{

            if (isOpen){
                _1_.startAnimation(fabClose)
                _2_.startAnimation(fabClose)
                _3_.startAnimation(fabClose)
                add_button.startAnimation(fabRClockWise)

                isOpen = false
            }

            else{
                _1_.startAnimation(fabOpen)
                _2_.startAnimation(fabOpen)
                _3_.startAnimation(fabOpen)
                add_button.startAnimation(fabRAntiClockWise)

                _1_.isClickable
                _2_.isClickable
                _3_.isClickable

                isOpen = true

            }
            _1_.setOnClickListener{Toast.makeText(this, "_1_", Toast.LENGTH_SHORT).show()}
            _2_.setOnClickListener{Toast.makeText(this, "_2_", Toast.LENGTH_SHORT).show()}
            _3_.setOnClickListener{Toast.makeText(this, "_3_", Toast.LENGTH_SHORT).show()}
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.edit_menu_upper, menu)
        return true
    }

        override fun onSupportNavigateUp(): Boolean {
        addItem(isPinned)
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }
    override fun onBackPressed() {
        addItem(isPinned)
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

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



