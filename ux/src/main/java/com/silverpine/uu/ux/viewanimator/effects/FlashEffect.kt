package com.silverpine.uu.ux.viewanimator.effects

import android.animation.ObjectAnimator
import android.view.View

import com.silverpine.uu.ux.viewanimator.BaseViewAnimator

class FlashEffect : BaseViewAnimator() {
    override fun prepare(target: View) {
        getAnimatorAgent().playTogether(
            ObjectAnimator.ofFloat(target, View.ALPHA, 1f, 0f, 1f, 0f, 1f)
        )
    }
}