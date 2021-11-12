package com.silverpine.uu.ux.viewanimator.effects

import android.animation.ObjectAnimator
import android.view.View
import com.silverpine.uu.ux.viewanimator.BaseViewAnimator


class WobbleEffect : BaseViewAnimator() {
    override fun prepare(target: View) {
        val width: Int = target?.width ?: 0
        val one = (width / 100.0).toFloat()
        getAnimatorAgent().playTogether(
            ObjectAnimator.ofFloat(
                target,
                View.TRANSLATION_X,
                0f * one,
                -25f * one,
                20f * one,
                -15f * one,
                10f * one,
                -5f * one,
                0f * one,
                0f
            ),
            ObjectAnimator.ofFloat(target, "rotation", 0f, -5f, 3f, -3f, 2f, -1f, 0f)
        )
    }
}