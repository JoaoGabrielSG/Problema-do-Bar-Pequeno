package barproblem

/**
 * Created by matheusmartins on 5/15/17.
 */

import com.sun.deploy.util.SessionState
import java.net.URL
import java.util.ResourceBundle
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.Pane
import javax.swing.plaf.RootPaneUI
import javafx.scene.paint.*
import com.almasb.fxgl.app.*
import com.almasb.fxgl.settings.*
import com.almasb.fxgl.input.*
import javafx.application.Application
import javafx.scene.input.KeyCode



class Game : GameApplication() {

    override fun initSettings(p0: GameSettings?) {
        p0?.width = 800
        p0?.height = 600
        p0?.title = "pitombeira simulator 2017"
        p0?.version = "0.1.0 [alpha] "
        p0?.isProfilingEnabled = false
        p0?.isIntroEnabled = false
        p0?.isMenuEnabled = false
        p0?.isFullScreen = false
        p0?.applicationMode = ApplicationMode.DEVELOPER
    }

    override fun initInput() {
        input.addAction(object : UserAction("Jump") {
            override fun onActionBegin() {
                print("jump")
            }
        }, KeyCode.SPACE)
    }

    override fun initGameVars(vars: MutableMap<String, Any>?) {
        vars?.put("stageColor", Color.BLACK)
    }

    override fun initGame() {
        val bar = Bar(5)

        val controls = listOf(ClientControl("Matheus", 10000),
                             ClientControl("Gabi", 15000),
                             ClientControl("João Gonça", 20000),
                             ClientControl("Paul Harris", 15000),
                             ClientControl("Parente", 10000),
                             ClientControl("Random", 15000))

        controls.map { control ->
            val client = Client()
            client.addControl(control)
            gameWorld.addEntity(client)
            control.enterBar(bar)
        }
    }

    override fun initUI() {
        val uiScore = uiFactory.newText("", 72.0)
        uiScore.translateX = (width - 200.0)
        uiScore.translateY = 50.0
        //uiScore.fillProperty().bind(gameState.objectProperty<T>("stageColor"))
        //uiScore.textProperty().bind(getMasterTimer().tickProperty().asString());

        gameScene.addUINode(uiScore)
    }

    override fun onPostUpdate(tpf: Double) {

    }

    fun main(args: Array<String>) {
        Application.launch(*args)
    }
}