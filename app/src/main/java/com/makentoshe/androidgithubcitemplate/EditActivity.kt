package com.makentoshe.androidgithubcitemplate

import android.Manifest
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.makentoshe.androidgithubcitemplate.items.Task
import com.makentoshe.androidgithubcitemplate.items.TaskDatabase
import dev.sasikanth.colorsheet.ColorSheet
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.note_layout.*
import java.util.*


class EditActivity : AppCompatActivity() {
    var date: Long = 0
    var tImage: String = ""
    var isPinned = false
    var tBookmark: Int = 0
    var dateAndTime: Calendar = Calendar.getInstance()
    var isOpen = false
    var isAlarmOpen = false
    lateinit var noteImage: ImageView
    private var REQUEST_CODE_PERMISSION_READ_CONTACTS = 0

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_PERMISSION_READ_CONTACTS -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT)
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    fun addItem() {
        var db = TaskDatabase.getDatabase(application)
        var taskDao = db.taskDao()
        var task = Task()
        if ((editTitle.text.toString() == "") and (textField.text.toString() == "")) else {
            task.title = editTitle.text.toString()
            task.text = textField.text.toString()
            task.date = date
            task.image = tImage
            Log.v("PinStatusAdd", isPinned.toString())
            task.pin = isPinned
            task.bookmark = tBookmark
            taskDao.insert(task)
            taskDao.update(task)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val pref =
            applicationContext.getSharedPreferences("MyPref", 0) // 0 - for private mod

        if (pref.getBoolean("switch", false)) {

            val theme: Resources.Theme = super.getTheme()
            theme.applyStyle(com.makentoshe.androidgithubcitemplate.R.style.AppTheme_Dark, true)

        } else {

            val theme: Resources.Theme = super.getTheme()
            theme.applyStyle(com.makentoshe.androidgithubcitemplate.R.style.AppTheme, true)

        }
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.title = "Edit"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_edit)
        noteImage = findViewById(R.id.imageField)
        val colors: IntArray = IntArray(5)
        colors[0] = 0xffff8585.toInt()
        colors[1] = 0xffffeca8.toInt()
        colors[2] = 0xffbeff85.toInt()
        colors[3] = 0xffa8fcff.toInt()
        colors[4] = 0xffe8a8ff.toInt()
        ColorSheet().cornerRadius(10)
        Pin.setOnClickListener {
            isPinned = !isPinned
            if (isPinned) ImageViewCompat.setImageTintList(
                Pin,
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.colorPrimary
                    )
                )
            ) else
                ImageViewCompat.setImageTintList(
                    Pin,
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.textColorSecondary
                        )
                    )
                )
            Log.v("PinStatus", isPinned.toString())
        }

        SetAttachment.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                when {
                    ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        var camImagesIntent = Intent(Intent.ACTION_GET_CONTENT)
                        camImagesIntent.type = "image/*"
                        camImagesIntent.action = Intent.ACTION_GET_CONTENT
                        startActivityForResult(
                            Intent.createChooser(
                                camImagesIntent,
                                "Select Picture"
                            ), 1

                        )

                    }

                    shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                        // In an educational UI, explain to the user why your app requires this
                        // permission for a specific feature to behave as expected. In this UI,
                        // include a "cancel" or "no thanks" button that allows the user to
                        // continue using your app without granting the permission.
                    }
                    else -> {
                        // You can directly ask for the permission.
                        requestPermissions(
                            Array<String>(1) { Manifest.permission.READ_EXTERNAL_STORAGE },
                            REQUEST_CODE_PERMISSION_READ_CONTACTS
                        )
                        Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT)
                    }
                }

            } else {
                var camImagesIntent = Intent(Intent.ACTION_GET_CONTENT)
                camImagesIntent.type = "image/*"
                camImagesIntent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(
                        camImagesIntent,
                        "Select Picture"
                    ), 1

                )
                }

        }
        imageField.setOnClickListener{
            tImage = ""
            imageField.setImageURI(Uri.parse(""))
        }
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
        val close = AnimationUtils.loadAnimation(this, R.anim.datetime_close)
        val open = AnimationUtils.loadAnimation(this, R.anim.datetime_open)
        val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        val fabRClockWise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
        val fabRAntiClockWise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)
        SetAlarm.setOnClickListener {
            if (isAlarmOpen) {
                DateTimeLayout.startAnimation(close)
                DateTimeLayout.visibility = View.GONE
                SetDate.text = "DATE"
                SetTime.text = "TIME"
                SetDate.isEnabled = false
                SetTime.isEnabled = false
                SetTimeDone.isEnabled = false
                SetTimeCancel.isEnabled = false
                SetTime.strokeColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.textColorSecondary
                    )
                )
                SetDate.strokeColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.textColorSecondary
                    )
                )
                isAlarmOpen = !isAlarmOpen
            } else {

                DateTimeLayout.startAnimation(open)
                DateTimeLayout.visibility = View.VISIBLE
                SetDate.isEnabled = true
                SetTime.isEnabled = true
                SetTimeDone.isEnabled = true
                SetTimeCancel.isEnabled = true
                isAlarmOpen = !isAlarmOpen
            }
            val d =
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    dateAndTime.set(Calendar.YEAR, year)
                    dateAndTime.set(Calendar.MONTH, monthOfYear)
                    dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    var text: String = ""
                    text =
                        text + dateAndTime[Calendar.DAY_OF_MONTH].toString() + "." + (dateAndTime[Calendar.MONTH] + 1).toString() + "." + dateAndTime[Calendar.YEAR].toString()
                    SetDate.text = text
                    SetDate.strokeColor = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.textColorSecondary
                        )
                    )
                }
            SetDate.setOnClickListener {
                DatePickerDialog(
                    this@EditActivity, R.style.DialogTheme, d,
                    dateAndTime.get(Calendar.YEAR),
                    dateAndTime.get(Calendar.MONTH),
                    dateAndTime.get(Calendar.DAY_OF_MONTH)
                )
                    .show()
            }
            val t =
                OnTimeSetListener { view, hourOfDay, minute ->
                    dateAndTime[Calendar.HOUR_OF_DAY] = hourOfDay
                    dateAndTime[Calendar.MINUTE] = minute
                    var text: String = ""
                    text =
                        text + dateAndTime[Calendar.HOUR_OF_DAY].toString() + ":" + if (dateAndTime[Calendar.MINUTE] < 10) "0" + dateAndTime[Calendar.MINUTE].toString() else dateAndTime[Calendar.MINUTE].toString()
                    SetTime.text = text
                    SetTime.strokeColor = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.textColorSecondary
                        )
                    )
                }
            SetTime.setOnClickListener {
                TimePickerDialog(
                    this@EditActivity, R.style.DialogTheme, t,
                    dateAndTime[Calendar.HOUR_OF_DAY],
                    dateAndTime[Calendar.MINUTE], true
                )
                    .show()
            }
            SetTimeDone.setOnClickListener {
                var currentCal = Calendar.getInstance()
                if (SetDate.text == "DATE") {
                    SetDate.strokeColor = ColorStateList.valueOf(0xFFFF0000.toInt())
                }
                if (SetTime.text == "TIME") {
                    SetTime.strokeColor = ColorStateList.valueOf(0xFFFF0000.toInt())
                }
                if ((SetDate.text != "DATE") and (SetTime.text != "TIME")) {
                    if (dateAndTime.timeInMillis < currentCal.timeInMillis) {
                        if ((dateAndTime[Calendar.DAY_OF_MONTH] < currentCal[Calendar.DAY_OF_MONTH]) or (dateAndTime[Calendar.MONTH] < currentCal[Calendar.MONTH]) or (dateAndTime[Calendar.YEAR] < currentCal[Calendar.YEAR])) {
                            SetDate.strokeColor = ColorStateList.valueOf(0xFFFF0000.toInt())
                        } else if (dateAndTime[Calendar.HOUR_OF_DAY] < currentCal[Calendar.MINUTE]) {
                            SetTime.strokeColor = ColorStateList.valueOf(0xFFFF0000.toInt())
                        }
                    } else {
                        DateTimeLayout.visibility = View.GONE
                        ImageViewCompat.setImageTintList(
                            SetAlarm,
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    applicationContext,
                                    R.color.colorPrimary
                                )
                            )
                        )
                        date = dateAndTime.timeInMillis
                        DateTimeLayout.startAnimation(close)
                        DateTimeLayout.visibility = View.GONE
                        SetDate.isEnabled = false
                        SetTime.isEnabled = false
                        SetTimeDone.isEnabled = false
                        SetTimeCancel.isEnabled = false
                        SetDate.text = "DATE"
                        SetTime.text = "TIME"
                        isAlarmOpen = !isAlarmOpen
                    }
                }
            }
            SetTimeCancel.setOnClickListener {
                DateTimeLayout.startAnimation(close)
                SetDate.text = "DATE"
                SetTime.text = "TIME"
                date = 0
                SetDate.isEnabled = false
                SetTime.isEnabled = false
                SetTimeDone.isEnabled = false
                SetTimeCancel.isEnabled = false
                DateTimeLayout.visibility = View.GONE
                ImageViewCompat.setImageTintList(
                    SetAlarm,
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.textColorSecondary
                        )
                    )
                )
                SetTime.strokeColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.textColorSecondary
                    )
                )
                SetDate.strokeColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.textColorSecondary
                    )
                )
                isAlarmOpen = !isAlarmOpen
            }
        }

        SetColor.setOnClickListener {
            var colorCurrent = ColorSheet.NO_COLOR
            if (SetColor.imageTintList != ColorStateList.valueOf(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.textColorSecondary
                    )
                )
            )
                colorCurrent = SetColor.imageTintList!!.defaultColor

            ColorSheet().colorPicker(
                colors = colors,
                selectedColor = colorCurrent,
                noColorOption = true,
                listener = { color ->
                    colorCurrent = color
                    setColor(color)
                    // Handle color
                })
                .show(supportFragmentManager)

        }

        add_button.setOnClickListener {

            if (isOpen) {
                _1_.startAnimation(fabClose)
                _2_.startAnimation(fabClose)
                _3_.startAnimation(fabClose)
                add_button.startAnimation(fabRClockWise)
                _1_.isEnabled = false
                _2_.isEnabled = false
                _3_.isEnabled = false

                isOpen = false
            } else {
                _1_.startAnimation(fabOpen)
                _2_.startAnimation(fabOpen)
                _3_.startAnimation(fabOpen)
                add_button.startAnimation(fabRAntiClockWise)

                _1_.isEnabled = true
                _2_.isEnabled = true
                _3_.isEnabled = true

                isOpen = true

            }
            _1_.setOnClickListener { Toast.makeText(this, "_1_", Toast.LENGTH_SHORT).show() }
            _2_.setOnClickListener { Toast.makeText(this, "_2_", Toast.LENGTH_SHORT).show() }
            _3_.setOnClickListener { Toast.makeText(this, "_3_", Toast.LENGTH_SHORT).show() }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && null != data) {
            tImage = data.data.toString()
            Glide
                .with(this)
                .load(Uri.parse(tImage))
                .into(noteImage)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Delete -> {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.edit_menu_upper, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        addItem()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }

    override fun onBackPressed() {
        addItem()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    private fun setColor(color: Int) {
        if (color == ColorSheet.NO_COLOR) {
            tBookmark = 0
            ImageViewCompat.setImageTintList(
                SetColor,
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.textColorSecondary
                    )
                )
            )
        } else {
            tBookmark = color
            ImageViewCompat.setImageTintList(SetColor, ColorStateList.valueOf(color))
        }
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



