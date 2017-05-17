package barproblem

/**
 * Created by matheusmartins on 5/15/17.
 */

class ClientThread(name: String, sittingDuration: Long): Thread(name) {

    enum class State {
        Outside,
        Inside,
        Seated
    }

    var bar: Bar? = null
        private set

    var state: State = State.Outside
        private set

    var sittingDuration = sittingDuration

    var onEnterBar: ((bar: Bar) -> Unit)? = null
    fun enterBar(bar: Bar) {
        this.bar = bar
        state = State.Inside
        onEnterBar?.invoke(bar)
    }

    var onLeaveBar: (() -> Unit)? = null
    fun leaveBar() {
        bar?.chairs?.release()

        if(bar?.isEmpty() == true && bar?.isReserved() == true) {
            bar?.reserve?.release()
            print("bar liberado\n")
        }

        bar = null
        state = State.Outside
        onLeaveBar?.invoke()
    }

    var onSit: (() -> Unit)? = null
    private fun waitForSeat(callback: (() -> Unit)? = null) {

        bar?.reserve?.acquire()
        bar?.chairs?.acquire()

        if(bar?.isFull() == false)
            bar?.reserve?.release()
        else
            print("bar reservado\n")

        state = State.Seated
        onSit?.invoke()
        callback?.invoke()
    }

    override fun run() {
        while(bar == null) { }
        waitForSeat {
            sleep(sittingDuration)
            leaveBar()
        }
    }
}