package barproblem

import javafx.geometry.*
import javafx.scene.paint.*
import javafx.scene.shape.*

import com.almasb.fxgl.ecs.*
import com.almasb.fxgl.entity.component.*
import javafx.scene.control.*
import javax.swing.text.ViewFactory

/**
 * Created by matheusmartins on 5/17/17.
 */
class Client: Entity() {
    val position = PositionComponent()
    val rotation = RotationComponent()
    val sprite = ViewComponent()

    val nameLabel = Label("Oi")
    val stateLabel = Label("Em casa")
    val timeLabel = Label("10000")

    init {
        this.addComponent(position)
        this.addComponent(rotation)
        this.addComponent(sprite)

        sprite.view.addNode(Rectangle(32.0, 32.0, Color.ALICEBLUE))

        nameLabel.layoutY = -40.0
        nameLabel.textFill = Color.WHITE
        sprite.view.addNode(nameLabel)

        stateLabel.layoutX = 40.0
        stateLabel.textFill = Color.WHITE
        sprite.view.addNode(stateLabel)

        timeLabel.layoutX = 40.0
        timeLabel.layoutY = 20.0
        timeLabel.textFill = Color.WHITE
        sprite.view.addNode(timeLabel)
    }
}