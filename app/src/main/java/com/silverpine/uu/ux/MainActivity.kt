package com.silverpine.uu.ux

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.silverpine.uu.ux.viewanimator.AnimationEffect
import com.silverpine.uu.ux.viewanimator.UUViewAnimator
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    var animators = ArrayList<AnimationEffect>()
    var animIndex = 0
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.animated_text)

        animators.addAll(arrayOf(AnimationEffect.Pulse, AnimationEffect.Wobble, AnimationEffect.Shake, AnimationEffect.Flash, AnimationEffect.Bounce, AnimationEffect.ZoomIn, AnimationEffect.Tada))
        //Loop over sample AnimationEffects
        Timer().schedule(0, 5000) {
            runOnUiThread {
                playAnimation(text)
            }

            animIndex++

            if(animIndex > animators.count() - 1) {
                animIndex = 0
            }
        }
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

    private fun playAnimation(view: View) {
        UUViewAnimator.getComposer(animators[animIndex])
            .duration(2000)
            .repeat(1)
            .interpolate(AccelerateDecelerateInterpolator())
            .withListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) { }

                override fun onAnimationEnd(animation: Animator) { }

                override fun onAnimationCancel(animation: Animator) { }

                override fun onAnimationRepeat(animation: Animator) { }
            })
            .playOn(view)
    }
}