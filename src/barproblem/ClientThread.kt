package barproblem

/**
 * Created by matheusmartins on 5/15/17.
 */

import java.util.*

class ClientThread(name: String, sittingDuration: Long): Thread(name) {

    enum class State {
        Outside,
        Inside,
        Seated,
        Left
    }

    var bar: Bar? = null
        private set

    var state: State = State.Outside
        private set

    var satTime = -1L
    var sittingDuration = sittingDuration

    var onEnterBar: ((bar: Bar) -> Unit)? = null
    fun enterBar(bar: Bar) {
        this.bar = bar
        state = State.Inside
        onEnterBar?.invoke(bar)
    }

    var onLeaveBar: (() -> Unit)? = null
    fun leaveBar() {
        val bar = this.bar!!

        bar.chairs.release()

        bar.check.acquire()
        val isEmpty = bar.isEmpty()
        bar.check.release()

        bar.check.acquire()
        val isReserved = bar.isReserved()
        bar.check.release()

        if(isEmpty == true && isReserved == true) {
            bar.reserve.release()
            print("bar liberado\n")
        }

        this.bar = null
        state = State.Left
        onLeaveBar?.invoke()
    }

    var willSit: (() -> Unit)? = null
    var onSit: ((chair: Int) -> Unit)? = null
    private fun waitForSeat(callback: (() -> Unit)? = null) {

        val bar = this.bar!!

        willSit?.invoke()

        bar.reserve.acquire()
        bar.chairs.acquire()

        bar.check.acquire()
        val isFull = bar.isFull()
        val nextChair = bar.nextChair
        bar.check.release()

        if(isFull == false)
            bar?.reserve?.release()
        else
            print("bar reservado\n")

        satTime = Date().time
        state = State.Seated

        onSit?.invoke(nextChair)
        callback?.invoke()
    }

    override fun run() {
        while(bar == null) { ; }
        waitForSeat {
            while(Date().time < satTime + sittingDuration) { ; }
            sleep(sittingDuration)
            leaveBar()
        }
    }
}