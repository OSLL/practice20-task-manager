package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class SettingsActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppTheme_Dark)
        } else { setTheme(R.style.AppTheme) }

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
                        overridePendingTransition(
                            com.makentoshe.androidgithubcitemplate.R.anim.slidein2,
                            com.makentoshe.androidgithubcitemplate.R.anim.slideout2
                        )
                        finish()
                        true
                    }
                    com.makentoshe.androidgithubcitemplate.R.id.StatsButton -> {
                        intent = Intent(this, StatsActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(
                            com.makentoshe.androidgithubcitemplate.R.anim.slidein2,
                            com.makentoshe.androidgithubcitemplate.R.anim.slideout2
                        )
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

        val switch : Switch = findViewById(R.id.switch_1)

        if (switch != null) {
            switch.setOnCheckedChangeListener(this);
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton? , isChecked: Boolean ) {
        Toast.makeText(this, "The Switch is " + (if (isChecked) "on" else "off"), Toast.LENGTH_SHORT).show()
        if(isChecked) {
            //do stuff when Switch is ON
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            restartApp()
        } else {
            //do stuff when Switch if OFF
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            restartApp()
        }
    }

    fun restartApp() {
        intent = Intent(applicationContext, SettingsActivity::class.java)
        startActivity(intent)
        finish()
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