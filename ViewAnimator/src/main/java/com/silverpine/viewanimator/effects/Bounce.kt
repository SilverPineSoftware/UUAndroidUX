package com.silverpine.viewanimator.effects

import android.animation.ObjectAnimator
import android.view.View

import com.silverpine.viewanimator.BaseViewAnimator

class Bounce : BaseViewAnimator() {
    override fun prepare(target: View) {
        getAnimatorAgent().playTogether(
            ObjectAnimator.ofFloat(target, View.TRANSLATION_Y, 0f, 0f, -30f, 0f, -15f, 0f, 0f)
        )
    }
}