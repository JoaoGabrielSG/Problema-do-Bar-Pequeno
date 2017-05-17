package barproblem
/**
 * Created by matheusmartins on 5/15/17.
 */

import java.util.*
import java.util.concurrent.Semaphore

class Bar(chairCount: Int) {
    val chairCount = chairCount
    var chairs = Semaphore(chairCount)
    var reserve = Semaphore(1)
    var check = Semaphore(1)

    val nextChair get() = chairCount - chairs.availablePermits()

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