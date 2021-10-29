package com.silverpine.viewanimator

import java.lang.Error
import java.lang.Exception

enum class AnimationEffect(private val animatorClass: Class<*>) {
    Flash(Flash::class.java),
    Pulse(Pulse::class.java),
    Shake(Shake::class.java),
    Swing(Swing::class.java),
    Wobble(Wobble::class.java),
    Bounce(Bounce::class.java),
    Tada(Tada::class.java),
    ZoomIn(ZoomIn::class.java);

    val animator: BaseViewAnimator
        get() = try {
            animatorClass.newInstance() as BaseViewAnimator
        } catch (e: Exception) {
            throw Error("Cannot init animatorClass instance")
        }
}