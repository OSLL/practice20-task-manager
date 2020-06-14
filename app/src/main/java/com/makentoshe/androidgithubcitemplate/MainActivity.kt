package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.HomeButton -> {
                        //goto up
                        true
                    }
                    R.id.StatsButton -> {
                        intent = Intent(this, StatsActivity::class.java)
                        startActivity(intent)
                        finish()
                        overridePendingTransition(0, 0)
                        true
                    }
                    R.id.ProfileButton -> {
                        intent = Intent(this, SettingsActivity::class.java)
                        startActivity(intent)
                        finish()
                        overridePendingTransition(0, 0)
                        true
                    }
                }
                false
            }
         fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent) {
            super.onActivityResult(requestCode, resultCode, data)
            finish()
        }

        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        floating_action_button.setOnClickListener {
            intent = Intent(this, EditActivity::class.java)
            //intent.setFlags(FAB_ALIGNMENT_MODE_END)
            startActivity(intent)
            //Toast.makeText(this, "AAAAAAAAA", Toast.LENGTH_SHORT ).show()
            bottom_navigation.selectedItemId = R.id.HomeButton
            bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }

        acceptedNote()
    }

    fun EditNote(view: View){
        val editIntent = Intent(this, EditActivity::class.java)
        startActivity(editIntent)

    }


    companion object {
        const val acceptedTitle = "acceptedTitle"
        const val acceptedNote = "acceptedNote"
    }

    fun acceptedNote(){
        val acceptedTitle = intent.getStringExtra(acceptedTitle)
        val acceptedNote = intent.getStringExtra(acceptedNote)
        textView2.text = acceptedTitle
        textView3.text = acceptedNote
    }

}
