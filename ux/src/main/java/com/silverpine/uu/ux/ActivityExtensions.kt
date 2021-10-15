package com.silverpine.uu.ux

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun Activity.uuOpenSystemSettings()
{
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivity(intent)
}