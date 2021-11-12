package com.silverpine.uu.ux.viewanimator

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Interpolator

const val DURATION: Long = 1000

abstract class BaseViewAnimator {
    var animatorSet: AnimatorSet = AnimatorSet()
    private var duration = DURATION
    private var repeatTimes = 0
    private var repeatMode = ValueAnimator.RESTART

    protected abstract fun prepare(target: View)

    fun setTarget(target: View): BaseViewAnimator {
        reset(target)
        prepare(target)
        return this
    }

    fun animate() {
        start()
    }

    fun restart() {
        animatorSet = animatorSet.clone()
        start()
    }

    fun reset(target: View?) {
        if(target != null) {
            with(target) {
                alpha = 1f
                scaleX = 1f
                scaleY = 1f
                translationX = 0f
                translationY = 0f
                rotation = 0f
                rotationY = 0f
                rotationX = 0f
            }
        }
    }

    fun start() {
        for (animator in animatorSet.childAnimations) {
            if (animator is ValueAnimator) {
                animator.repeatCount = repeatTimes
                animator.repeatMode = repeatMode
            }
        }
        animatorSet.duration = duration
        animatorSet.start()
    }

    fun setDuration(duration: Long) {
        this.duration = duration
    }

    fun setStartDelay(delay: Long): BaseViewAnimator {
        getAnimatorAgent().startDelay = delay
        return this
    }

    fun getStartDelay(): Long {
        return animatorSet.startDelay
    }

    fun addAnimatorListener(l: Animator.AnimatorListener): BaseViewAnimator {
        animatorSet.addListener(l)
        return this
    }

    fun cancel() {
        animatorSet.cancel()
    }

    fun isRunning(): Boolean {
        return animatorSet.isRunning
    }

    fun isStarted(): Boolean {
        return animatorSet.isStarted
    }

    fun removeAnimatorListener(l: Animator.AnimatorListener?) {
        animatorSet.removeListener(l)
    }

    fun removeAllListener() {
        animatorSet.removeAllListeners()
    }

    fun setInterpolator(interpolator: Interpolator?): BaseViewAnimator {
        animatorSet.interpolator = interpolator
        return this
    }

    fun getAnimatorAgent(): AnimatorSet {
        return animatorSet
    }

    fun setRepeatTimes(repeatTimes: Int): BaseViewAnimator {
        this.repeatTimes = repeatTimes
        return this
    }

    fun setRepeatMode(repeatMode: Int): BaseViewAnimator {
        this.repeatMode = repeatMode
        return this
    }
}