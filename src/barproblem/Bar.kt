package barproblem
/**
 * Created by matheusmartins on 5/15/17.
 */

import java.util.*
import java.util.concurrent.Semaphore

class Bar(chairCount: Int) {
    val chairCount = chairCount

    var mutex = Semaphore(1)
    var block = Semaphore(0)

    var sitting = 0
    var waiting = 0

    fun enter(callback: (willWait: Boolean) -> Unit) {
        mutex.acquire()
        if (sitting == chairCount) {
            waiting += 1
            mutex.release()
            callback?.invoke(true)
            block.acquire()
        } else {
            sitting += 1
            callback?.invoke(false)
            mutex.release()
        }
    }

    fun leave() {
        mutex.acquire()
        sitting -= 1
        if (sitting == 0) {
            val n = minOf(5, waiting)
            waiting -= n
            sitting += n
            block.release()
        }
        mutex.release()
    }
}