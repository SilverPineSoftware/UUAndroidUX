package com.silverpine.uu.ux.viewanimator

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Interpolator

const val NO_DELAY: Long = 0
const val INFINITE = -1
const val CENTER_PIVOT = Float.MAX_VALUE

class UUViewAnimator(animationComposer: AnimationComposer) {
    private var animator: BaseViewAnimator
    private var target:  View? = null
    private var callbacks: List<Animator.AnimatorListener>
    private var interpolator: Interpolator? = null
    private var duration: Long = 0
    private var delay: Long = 0
    private var repeat = false
    private var repeatTimes = 0
    private var repeatMode = 0
    private var pivotX = 0f
    private var pivotY = 0f

    init {
        animator = animationComposer.animator
        target = animationComposer.target
        callbacks = animationComposer.callbacks
        interpolator = animationComposer.interpolator
        duration = animationComposer.duration
        delay = animationComposer.delay
        repeat = animationComposer.repeat
        repeatTimes = animationComposer.repeatTimes
        repeatMode = animationComposer.repeatMode
        pivotX = animationComposer.pivotX
        pivotY = animationComposer.pivotY
    }

    interface AnimatorCallback {
        fun call(animator: Animator)
    }

    private open class EmptyAnimatorListener : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {}
        override fun onAnimationEnd(animation: Animator) {}
        override fun onAnimationCancel(animation: Animator) {}
        override fun onAnimationRepeat(animation: Animator) {}
    }

    class AnimationComposer {
        val callbacks: MutableList<Animator.AnimatorListener> = ArrayList()
        var animator: BaseViewAnimator
        var duration: Long = DURATION
        var delay: Long = NO_DELAY
        var repeat = false
        var repeatTimes = 0
        var repeatMode = ValueAnimator.RESTART
        var pivotX: Float = Float.MAX_VALUE
        var pivotY: Float = Float.MAX_VALUE
        var interpolator: Interpolator? = null
        var target: View? = null

        constructor(techniques: AnimationEffect) {
            animator = techniques.animator
        }

        constructor(animator: BaseViewAnimator) {
            this.animator = animator
        }

        fun duration(duration: Long): AnimationComposer {
            this.duration = duration
            return this
        }

        fun delay(delay: Long): AnimationComposer {
            this.delay = delay
            return this
        }

        fun interpolate(interpolator: Interpolator): AnimationComposer {
            this.interpolator = interpolator
            return this
        }

        fun pivot(pivotX: Float, pivotY: Float): AnimationComposer {
            this.pivotX = pivotX
            this.pivotY = pivotY
            return this
        }

        fun pivotX(pivotX: Float): AnimationComposer {
            this.pivotX = pivotX
            return this
        }

        fun pivotY(pivotY: Float): AnimationComposer {
            this.pivotY = pivotY
            return this
        }

        fun repeat(times: Int): AnimationComposer {
            if (times < -1) {
                throw RuntimeException("Infinite Loop! Repeat value cannot be less than -1.")
            }
            repeat = times != 0
            repeatTimes = times
            return this
        }

        fun repeatMode(mode: Int): AnimationComposer {
            repeatMode = mode
            return this
        }

        fun withListener(listener: Animator.AnimatorListener): AnimationComposer {
            callbacks.add(listener)
            return this
        }

        fun onStart(callback: AnimatorCallback): AnimationComposer {
            callbacks.add(object : EmptyAnimatorListener() {
                override fun onAnimationStart(animation: Animator) {
                    callback.call(animation)
                }
            })
            return this
        }

        fun onEnd(callback: AnimatorCallback): AnimationComposer {
            callbacks.add(object : EmptyAnimatorListener() {
                override fun onAnimationEnd(animation: Animator) {
                    callback.call(animation)
                }
            })
            return this
        }

        fun onCancel(callback: AnimatorCallback): AnimationComposer {
            callbacks.add(object : EmptyAnimatorListener() {
                override fun onAnimationCancel(animation: Animator) {
                    callback.call(animation)
                }
            })
            return this
        }

        fun onRepeat(callback: AnimatorCallback): AnimationComposer {
            callbacks.add(object : EmptyAnimatorListener() {
                override fun onAnimationRepeat(animation: Animator) {
                    callback.call(animation)
                }
            })
            return this
        }

        fun playOn(target: View): AnimatorString {
            this.target = target
            return AnimatorString(UUViewAnimator(this).play(), target)
        }
    }

    class AnimatorString(private val animator: BaseViewAnimator, private val target: View) {
        val isStarted: Boolean
            get() = animator.isStarted()
        val isRunning: Boolean
            get() = animator.isRunning()

        @JvmOverloads
        fun stop(reset: Boolean = true) {
            animator.cancel()
            if (reset) animator.reset(target)
        }
    }

    private fun play(): BaseViewAnimator {
        target?.let { animator.setTarget(it)
            if (pivotX == Float.MAX_VALUE) {
                it.pivotX = it.measuredWidth / 2.0f
            } else {
                it.pivotX = pivotX
            }

            if (pivotY == Float.MAX_VALUE) {
                it.pivotY = it.measuredHeight / 2.0f
            } else {
                it.pivotY = pivotY
            }
        }

        with(animator) {
            setDuration(duration)
            setRepeatTimes(repeatTimes)
            setRepeatMode(repeatMode)
            setInterpolator(interpolator)
            setStartDelay(delay)
        }

        if (callbacks.isNotEmpty()) {
            for (callback in callbacks) {
                animator.addAnimatorListener(callback)
            }
        }

        animator.animate()
        return animator
    }

    companion object {
        fun getComposer(effect: AnimationEffect): AnimationComposer {
            return AnimationComposer(effect)
        }

        fun getComposer(animator: BaseViewAnimator): AnimationComposer {
            return AnimationComposer(animator)
        }
    }
}