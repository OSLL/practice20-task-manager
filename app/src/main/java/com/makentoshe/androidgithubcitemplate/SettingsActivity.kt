package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.content.res.Resources
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

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            val theme: Resources.Theme = super.getTheme()
            theme.applyStyle(com.makentoshe.androidgithubcitemplate.R.style.AppTheme_Dark, true)

        } else {

            val theme: Resources.Theme = super.getTheme()
            theme.applyStyle(com.makentoshe.androidgithubcitemplate.R.style.AppTheme, true)

        }

        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.title = "Profile"
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

        var myswitch = findViewById<Switch>(com.makentoshe.androidgithubcitemplate.R.id.switch_1)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            myswitch.isChecked = true
        }

        myswitch.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                Toast.makeText(
                    this@SettingsActivity,
                    "The Switch is " + (if (isChecked) "on" else "off"),
                    Toast.LENGTH_SHORT
                ).show()
                if (isChecked) {
                    //do stuff when Switch is ON
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    recreate()
                } else {
                    //do stuff when Switch if OFF
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    recreate()
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(
            com.makentoshe.androidgithubcitemplate.R.anim.slidein2,
            com.makentoshe.androidgithubcitemplate.R.anim.slideout2
        )
        finish()
        return true
    }

    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(
            com.makentoshe.androidgithubcitemplate.R.anim.slidein2,
            com.makentoshe.androidgithubcitemplate.R.anim.slideout2
        )
        finish()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        TODO("Not yet implemented")
    }
}