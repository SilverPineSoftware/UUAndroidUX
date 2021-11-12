package com.silverpine.uu.ux.viewanimator.effects

import android.animation.ObjectAnimator
import android.view.View
import com.silverpine.uu.ux.viewanimator.BaseViewAnimator

class ShakeEffect: BaseViewAnimator() {
    override fun prepare(target: View) {
        getAnimatorAgent().playTogether(
            ObjectAnimator.ofFloat(target, View.TRANSLATION_X, 0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f)
        )
    }
}