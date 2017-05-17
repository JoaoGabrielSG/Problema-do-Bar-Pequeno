package barproblem

import javafx.geometry.*
import javafx.scene.paint.*
import javafx.scene.shape.*

import com.almasb.fxgl.ecs.*
import com.almasb.fxgl.entity.component.*
import javax.swing.text.ViewFactory

/**
 * Created by matheusmartins on 5/17/17.
 */
class Client: Entity() {
    val position = PositionComponent()
    val rotation = RotationComponent()
    val sprite = ViewComponent()

    init {
        this.addComponent(position)
        this.addComponent(rotation)
        this.addComponent(sprite)

        sprite.view.addNode(Rectangle(32.0, 32.0, Color.BLACK))
    }
}