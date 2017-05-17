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

    var chair = -1

    override fun onAdded(entity: Entity?) {
        thread.start()

        thread.onEnterBar = {
            print(thread.name + " entrou no bar.\n")
            //position.translateTowards(Point2D(512.0, 400.0), 10.0)
        }

        thread.willSit = {
            print(thread.name + " está tentando sentar.\n")
        }

        thread.onSit = { chair ->
            this.chair = chair
            print(thread.name + " sentou na cadeira + " + chair + ".\n")
            //position.translateTowards(Point2D(512.0, 712.0), 10.0)
        }

        thread.onLeaveBar = {
            print(thread.name + " saiu do bar.\n")
        }
    }

    override fun onUpdate(p0: Entity?, p1: Double) {
        if(thread.state == ClientThread.State.Seated) {
            client.position.translateTowards(Point2D(200.0 + chair * 64.0, 200.0), p1 * 100.0)
        } else if(thread.state == ClientThread.State.Left) {
            client.position.translateTowards(Point2D(2000.0, 2000.0), p1 * 100.0)
        }
    }

    fun enterBar(bar: Bar) {
        thread.enterBar(bar)
    }
}