package com.silverpine.viewanimator.effects

import android.animation.ObjectAnimator
import android.view.View
import com.silverpine.viewanimator.BaseViewAnimator

class TadaEffect : BaseViewAnimator() {
    override fun prepare(target: View) {
        getAnimatorAgent().playTogether(
            ObjectAnimator.ofFloat(
                target,
                View.SCALE_X,
                1f,
                0.9f,
                0.9f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1f
            ),
            ObjectAnimator.ofFloat(
                target,
                View.SCALE_Y,
                1f,
                0.9f,
                0.9f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1f
            ),
            ObjectAnimator.ofFloat(target, View.ROTATION, 0f, -3f, -3f, 3f, -3f, 3f, -3f, 3f, -3f, 0f)
        )
    }
}