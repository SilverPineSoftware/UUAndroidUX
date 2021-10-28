package com.silverpine.viewanimator.effects

import android.animation.ObjectAnimator
import android.view.View

import com.silverpine.viewanimator.BaseViewAnimator

class ZoomIn : BaseViewAnimator() {
    override fun prepare(target: View) {
        getAnimatorAgent().playTogether(
            ObjectAnimator.ofFloat(target, View.SCALE_X, 0.45f, 1f),
            ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.45f, 1f),
            ObjectAnimator.ofFloat(target,  View.ALPHA, 0f, 1f)
        )
    }
}