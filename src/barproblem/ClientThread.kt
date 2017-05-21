package barproblem

/**
 * Created by matheusmartins on 5/15/17.
 */

import java.util.*

class ClientThread(name: String, bar: Bar, sittingDuration: Long): Thread(name) {

    enum class State {
        Outside,
        Waiting,
        Seated,
        Left
    }

    var bar: Bar = bar
        private set

    var state: State = State.Outside
        private set

    var satTime = -1L
        private set

    var sittingDuration = sittingDuration

    fun scratch(duration: Long) {
        val time = Date().time
        while (Date().time < time + duration) { ; }
    }

    var onEnterBar: (() -> Unit)? = null
    fun enterBar() {
        state = State.Waiting
        onEnterBar?.invoke()
    }

    var onLeaveBar: (() -> Unit)? = null
    fun leaveBar() {
        bar.leave()
        this.state = State.Left
        onLeaveBar?.invoke()
    }

    var willSit: (() -> Unit)? = null
    var onSit: ((chair: Int) -> Unit)? = null
    private fun waitForSeat(callback: (() -> Unit)? = null) {

        bar.enter { willWait ->
            if(willWait) {
                this.state = State.Waiting
            }
        }

        this.state = State.Seated
        onSit?.invoke(bar.sitting)

        callback?.invoke()
    }

    override fun run() {
        waitForSeat {
            scratch(sittingDuration)
            leaveBar()
        }
    }
}