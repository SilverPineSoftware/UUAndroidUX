package com.silverpine.uu.ux.viewanimator

import com.silverpine.uu.ux.viewanimator.effects.*
import java.lang.Error
import java.lang.Exception

enum class AnimationEffect(private val animatorClass: Class<*>) {
    Flash(FlashEffect::class.java),
    Pulse(PulseEffect::class.java),
    Shake(ShakeEffect::class.java),
    Wobble(WobbleEffect::class.java),
    Bounce(BounceEffect::class.java),
    Tada(TadaEffect::class.java),
    ZoomIn(ZoomInEffect::class.java);

    val animator: BaseViewAnimator
        get() = try {
            animatorClass.newInstance() as BaseViewAnimator
        } catch (e: Exception) {
            throw Error("Cannot init animatorClass instance")
        }
}