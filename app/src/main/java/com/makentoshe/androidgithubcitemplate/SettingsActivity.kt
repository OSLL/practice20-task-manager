package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.title = "Profile"
        actionBar?.setBackgroundDrawable(ColorDrawable(0xff6bbaff.toInt()))
        actionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.HomeButton -> {
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slidein2, R.anim.slideout2)
                        finish()
                        true
                    }
                    R.id.StatsButton -> {
                        intent = Intent(this, StatsActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slidein2, R.anim.slideout2)
                        finish()

                        true
                    }
                    R.id.ProfileButton -> {
                        //goto up
                        true
                    }
                }
                false
            }

        setContentView(R.layout.activity_settings)
        bottom_navigation.selectedItemId = R.id.ProfileButton
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slidein2, R.anim.slideout2)
        finish()
        return true
    }

    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slidein2, R.anim.slideout2)
        finish()
    }
}