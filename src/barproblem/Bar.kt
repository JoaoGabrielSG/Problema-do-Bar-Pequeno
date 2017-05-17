package barproblem
/**
 * Created by matheusmartins on 5/15/17.
 */

import java.util.*
import java.util.concurrent.Semaphore

class Bar(chairCount: Int) {
    var chairCount = chairCount
    var chairs = Semaphore(chairCount)
    var reserve = Semaphore(1)

    fun isFull(): Boolean {
        return chairs.availablePermits() == 0
    }

    fun isEmpty(): Boolean {
        return chairs.availablePermits() == chairCount
    }

    fun isReserved(): Boolean {
        return reserve.availablePermits() == 0
    }
}