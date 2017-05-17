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



class ViewController: GameApplication() {
    @FXML
    private lateinit var myButton: Button

    @FXML
    private lateinit var test: Button

    override fun initSettings(p0: GameSettings?) {
        p0?.width = 1280
        p0?.height = 720
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
                print("jumo")
            }
        }, KeyCode.SPACE)
    }

    override fun initGameVars(vars: MutableMap<String, Any>?) {
        vars?.put("stageColor", Color.BLACK)
    }

    override fun initGame() {
        //initBackground();
        //initPlayer();

        //initBackgroundMusic();
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




    @FXML
    fun initialize() {

        val bar = Bar(5)

        val clients = listOf(Client("Matheus"),
                             Client("Gabi"),
                             Client("João Gonça"),
                             Client("Paul Harris"),
                             Client("Parente"),
                             Client("Random"))

        clients[0].sittingDuration = 10000
        clients[1].sittingDuration = 10000
        clients[2].sittingDuration = 15000
        clients[3].sittingDuration = 20000
        clients[4].sittingDuration = 25000
        clients[5].sittingDuration = 10000

        clients.forEach { c ->
            c.onEnterBar = {
                print(c.name + " entrou no bar.\n")
            }

            c.onSit = {
                print(c.name + " sentou.\n")
            }

            c.onLeaveBar = {
                print(c.name + " saiu do bar.\n")
            }
        }

        fun runClients() {
            clients.forEach { c -> c.start(); c.enterBar(bar) }
        }

        myButton.setOnAction { runClients() }
    }

    fun main(args: Array<String>) {
        Application.launch(*args)
    }
}