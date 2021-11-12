package com.silverpine.uu.ux.viewanimator.effects

import android.animation.ObjectAnimator
import android.view.View

import com.silverpine.uu.ux.viewanimator.BaseViewAnimator

class ZoomInEffect : BaseViewAnimator() {
    override fun prepare(target: View) {
        getAnimatorAgent().playTogether(
            ObjectAnimator.ofFloat(target, View.SCALE_X, 0.45f, 1f),
            ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.45f, 1f),
            ObjectAnimator.ofFloat(target,  View.ALPHA, 0f, 1f)
        )
    }
}