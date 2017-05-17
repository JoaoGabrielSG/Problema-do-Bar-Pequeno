package barproblem

/**
 * Created by matheusmartins on 5/17/17.
 */

import com.almasb.fxgl.ecs.*
import com.almasb.fxgl.ecs.component.*
import com.almasb.fxgl.entity.component.*
import com.almasb.fxgl.entity.*
import javafx.geometry.*

class ClientControl(name: String, sittingDuration: Long): AbstractControl() {

    val thread = ClientThread(name, sittingDuration)

    val client get() = this.entity as Client

    override fun onAdded(entity: Entity?) {
        thread.start()

        thread.onEnterBar = {
            print(thread.name + " entrou no bar.\n")
            //position.translateTowards(Point2D(512.0, 400.0), 10.0)
        }

        thread.onSit = {
            print(thread.name + " sentou.\n")
            //position.translateTowards(Point2D(512.0, 712.0), 10.0)
        }

        thread.onLeaveBar = {
            print(thread.name + " saiu do bar.\n")
        }
    }

    override fun onUpdate(p0: Entity?, p1: Double) {

    }

    fun enterBar(bar: Bar) {
        thread.enterBar(bar)
    }
}