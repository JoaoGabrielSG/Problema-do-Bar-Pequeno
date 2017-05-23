package barproblem

/**
 * Created by matheusmartins on 5/17/17.
 */

import com.almasb.fxgl.ecs.*
import com.almasb.fxgl.ecs.component.*
import com.almasb.fxgl.entity.component.*
import com.almasb.fxgl.entity.*
import javafx.geometry.*
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class ClientControl(name: String, bar: Bar, sittingDuration: Long, sleepingDuration: Long): AbstractControl() {

    val thread = ClientThread(name, bar, sittingDuration, sleepingDuration)

    val client get() = this.entity as Client

    var chair = -1

    var sit_point: Point2D = Point2D.ZERO
    var wait_point: Point2D = Point2D.ZERO
    var sleep_point: Point2D = Point2D.ZERO

    override fun onAdded(entity: Entity?) {

        thread.onEnterBar = {

            print(thread.name + " entrou no bar.\n")

            var x = ThreadLocalRandom.current().nextDouble(-100.0, 100.0)
            var y = ThreadLocalRandom.current().nextDouble(-100.0, 100.0)

            wait_point = Point2D(100.0 + x, 400.0 + y)

        }

        thread.onSit = {
            print(thread.name + " sentou.\n")

            var x = ThreadLocalRandom.current().nextDouble(-100.0, 100.0)
            var y = ThreadLocalRandom.current().nextDouble(-100.0, 100.0)

            sit_point = Point2D(400.0 + x, 200.0 + y)
        }

        thread.onLeaveBar = {
            print(thread.name + " saiu do bar.\n")

            var x = ThreadLocalRandom.current().nextDouble(-100.0, 100.0)
            var y = ThreadLocalRandom.current().nextDouble(-100.0, 100.0)

            sleep_point = Point2D(700.0 + x, 400.0 + y)
        }

        thread.start()
    }

    override fun onUpdate(p0: Entity?, p1: Double) {

        client.nameLabel.text = thread.name

        if (thread.state == ClientThread.State.Waiting) {
            if(client.position.value.distance(wait_point) > 20.0) {
                client.position.translateTowards(wait_point, p1 * 1000.0)
            }
            client.stateLabel.text = "Esperando"
        }
        else if (thread.state == ClientThread.State.Seated) {
            if(client.position.value.distance(sit_point) > 20.0) {
                client.position.translateTowards(sit_point, p1 * 1000.0)
            }
            client.stateLabel.text = "Sentado"
        }
        else if (thread.state == ClientThread.State.Left) {
            if(client.position.value.distance(sleep_point) > 20.0) {
                client.position.translateTowards(sleep_point, p1 * 1000.0)
            }
            client.stateLabel.text = "Em casa"
        }

        client.timeLabel.text = thread.scratchTime.toString()
    }
}