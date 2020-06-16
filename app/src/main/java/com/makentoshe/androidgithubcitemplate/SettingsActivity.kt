package com.makentoshe.androidgithubcitemplate

import android.R
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.title = "Profile"
        actionBar?.setBackgroundDrawable(ColorDrawable(0xff6bbaff.toInt()))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    com.makentoshe.androidgithubcitemplate.R.id.HomeButton -> {
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(com.makentoshe.androidgithubcitemplate.R.anim.slidein2, com.makentoshe.androidgithubcitemplate.R.anim.slideout2)
                        finish()
                        true
                    }
                    com.makentoshe.androidgithubcitemplate.R.id.StatsButton -> {
                        intent = Intent(this, StatsActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(com.makentoshe.androidgithubcitemplate.R.anim.slidein2, com.makentoshe.androidgithubcitemplate.R.anim.slideout2)
                        finish()

                        true
                    }
                    com.makentoshe.androidgithubcitemplate.R.id.ProfileButton -> {
                        //goto up
                        true
                    }
                }
                false
            }

        setContentView(com.makentoshe.androidgithubcitemplate.R.layout.activity_settings)
        bottom_navigation.selectedItemId = com.makentoshe.androidgithubcitemplate.R.id.ProfileButton
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(com.makentoshe.androidgithubcitemplate.R.anim.slidein2, com.makentoshe.androidgithubcitemplate.R.anim.slideout2)
        finish()
        return true
    }

    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(com.makentoshe.androidgithubcitemplate.R.anim.slidein2, com.makentoshe.androidgithubcitemplate.R.anim.slideout2)
        finish()
    }
}