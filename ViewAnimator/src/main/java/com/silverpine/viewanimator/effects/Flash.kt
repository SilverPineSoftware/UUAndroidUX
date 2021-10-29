package com.silverpine.viewanimator.effects

import android.animation.ObjectAnimator
import android.view.View

import com.silverpine.viewanimator.BaseViewAnimator

class Flash : BaseViewAnimator() {
    override fun prepare(target: View) {
        getAnimatorAgent().playTogether(
            ObjectAnimator.ofFloat(target, View.ALPHA, 1f, 0f, 1f, 0f, 1f)
        )
    }
}