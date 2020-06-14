package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.makentoshe.androidgithubcitemplate.stats.SectionsPagerAdapter
import android.widget.Toast;
import android.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_stats.*
import kotlinx.android.synthetic.main.activity_stats.bottom_navigation
import java.lang.Override as Override1

class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
                actionBar?.title = "Statistics"
        actionBar?.setBackgroundDrawable(ColorDrawable(0xff6bbaff.toInt()))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_stats)
        title = null;
        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.HomeButton -> {
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slidein2, R.anim.slideout2);
                        finish()
                        true
                    }
                    R.id.StatsButton -> {

                        true
                    }
                    R.id.ProfileButton -> {
                        intent = Intent(this, SettingsActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slidein, R.anim.slideout);
                        finish()

                        true
                    }
                }
                false
            }
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        bottom_navigation.selectedItemId = R.id.StatsButton
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }
    override fun onSupportNavigateUp(): Boolean {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slidein2, R.anim.slideout2);
        finish()
        return true
    }
    override fun onBackPressed() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slidein2, R.anim.slideout2);
        finish()

    }
}