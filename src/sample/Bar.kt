package barproblem
/**
 * Created by matheusmartins on 5/15/17.
 */

import java.util.*
import java.util.concurrent.Semaphore

class Bar(chairCount: Int) {
    var chairs = Semaphore(chairCount)
}