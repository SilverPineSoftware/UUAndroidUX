package com.silverpine.uu.ux

import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes

/**
 * UUMenuHandler provides a simple and easy way to create programmatic menu's and map
 * runnable actions to them.
 *
 * @sample
 *
class SomeActivity : AppCompatActivity()
{
    private var menuHandler: UUMenuHandler? = null

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        val handler = UUMenuHandler(menu)
        populateMenu(handler)
        menuHandler = handler
        return true
    }

    open fun populateMenu(menuHandler: UUMenuHandler)
    {
        menuHandler.add(R.string.some_text_resource)
        {
        // Code to do something on menu click
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        menuHandler?.let()
        {
            return it.handleMenuClick(item)
        }
        ?: run()
        {
            return false
        }
    }
}

 */

class UUMenuHandler(private val menu: Menu)
{
    private val handlers: HashMap<Int, Runnable> = HashMap()
    private var lastId = 0

    fun add(title: String, action: Runnable): MenuItem
    {
        val id = addHandler(action)
        return menu.add(0, id, 0, title)
    }

    fun add(@StringRes titleResource: Int, action: Runnable): MenuItem
    {
        val id = addHandler(action)
        return menu.add(0, id, 0, titleResource)
    }

    private fun addHandler(action: Runnable): Int
    {
        lastId++
        handlers[lastId] = action
        return lastId
    }

    fun handleMenuClick(item: MenuItem):  Boolean
    {
        val handler = handlers[item.itemId]

        return if (handler != null)
        {
            handler.run()
            true
        }
        else
        {
            false
        }
    }
}

fun MenuItem.uuSetAsActionAlways()
{
    setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS or MenuItem.SHOW_AS_ACTION_WITH_TEXT)
}







