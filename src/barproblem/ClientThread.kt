package barproblem

/**
 * Created by matheusmartins on 5/15/17.
 */

import java.util.*

class ClientThread(name: String, bar: Bar, sittingDuration: Long, sleepingDuration: Long): Thread(name) {

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

    var sittingDuration = sittingDuration
    var sleepingDuration = sleepingDuration

    var scratchTime = 0L

    fun scratch(duration: Long) {
        val time = Date().time
        while (Date().time < time + duration) {
            scratchTime = (time + duration) - Date().time
        }
    }

    var onEnterBar: (() -> Unit)? = null

    var onLeaveBar: (() -> Unit)? = null

    var onSit: (() -> Unit)? = null

    override fun run() {

        while (true) {
            bar.mutex.acquire()

            if (bar.full) {
                bar.waiting += 1

                state = State.Waiting
                onEnterBar?.invoke()
                bar.mutex.release()
                bar.block.acquire()
            } else {
                bar.sitting += 1
                bar.full = bar.sitting == 5
                bar.mutex.release()
            }

            this.state = State.Seated
            onSit?.invoke()
            scratch(sittingDuration)

            bar.mutex.acquire()

            bar.sitting -= 1
            if (bar.sitting == 0) {
                val n = if (5 < bar.waiting) 5 else bar.waiting
                bar.waiting -= n
                bar.sitting += n

                bar.full = bar.sitting == 5
                bar.block.release(n)
            }
            bar.mutex.release()

            this.state = State.Left
            onLeaveBar?.invoke()
            scratch(sleepingDuration)
        }
    }
}