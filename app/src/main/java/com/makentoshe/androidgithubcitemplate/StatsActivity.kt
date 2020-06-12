package com.makentoshe.androidgithubcitemplate

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

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }
}