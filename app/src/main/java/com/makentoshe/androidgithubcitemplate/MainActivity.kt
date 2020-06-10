package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        bigblackbtn.setOnClickListener {
            intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
            //Toast.makeText(this, "AAAAAAAAA", Toast.LENGTH_SHORT ).show()
        }
    }
}
