package com.silverpine.uu.ux

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonTapped(v: View)
    {
        uuPrompt(title = R.string.dialog_title,
        message = R.string.dialog_message,
        positiveButtonTextId = R.string.dialog_button_one,
        negativeButtonTextId = R.string.dialog_button_two,
        cancelable = true,
        positiveAction = { Log.d("test", "Clicked positive button") },
        negativeAction = { Log.d("test", "Clicked negative button") })

    }
}