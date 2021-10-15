package com.silverpine.uu.ux

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes

fun Context.uuPrompt(
    @StringRes title: Int = -1,
    @StringRes message: Int,
    @StringRes positiveButtonTextId: Int,
    @StringRes negativeButtonTextId: Int = -1,
    cancelable: Boolean = true,
    positiveAction: () -> Unit = { },
    negativeAction: () -> Unit = { })
{
    val builder = AlertDialog.Builder(this)
    builder.setCancelable(cancelable)

    if (title != -1)
    {
        builder.setTitle(title)
    }

    if (message != -1)
    {
        builder.setMessage(message)
    }

    if (positiveButtonTextId != -1)
    {
        builder.setPositiveButton(positiveButtonTextId)
        { dialog, _ ->
            dialog.cancel()
            positiveAction()
        }
    }

    if (negativeButtonTextId != -1)
    {
        builder.setNegativeButton(negativeButtonTextId)
        { dialog, _ ->
            dialog.cancel()
            negativeAction()
        }
    }

    val dialog = builder.create()
    dialog.show()
}