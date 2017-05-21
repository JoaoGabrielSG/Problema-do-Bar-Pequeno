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
import javafx.scene.control.*
import javafx.scene.layout.Pane
import javax.swing.plaf.RootPaneUI
import javafx.scene.paint.*
import com.almasb.fxgl.app.*
import com.almasb.fxgl.settings.*
import com.almasb.fxgl.input.*
import com.almasb.fxgl.entity.*
import javafx.application.Application
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode



class Game : GameApplication() {

    val bar = Bar(5)

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

        initBackground()
        initCreateButton()

        /*al controls = listOf(ClientControl("Matheus", 10000),
                ClientControl("Gabi", 15000),
                ClientControl("João Gonça", 20000),
                ClientControl("Paul Harris", 15000),
                ClientControl("Parente", 10000),
                ClientControl("Random", 5000))

        controls.map { control ->
            val client = Client()
            client.addControl(control)
            gameWorld.addEntity(client)
            control.enterBar(bar)
        }*/
    }

    fun initBackground() {
        val view = assetLoader.loadTexture("background.png", 800.0, 600.0)

        val bg = Entities.builder()
                .at(0.0, 0.0)
                .viewFromNode(view)
                .buildAndAttach(gameWorld)
    }

    var client_number = 0
    var client_name = "client"
    var client_sittingTime = 10000
    fun initCreateButton() {

        val nameTextField = TextField("Joselito")
        nameTextField.setOnAction {
            client_name = nameTextField.text
        }

        val sittingTimeTextField = TextField("10000")
        sittingTimeTextField.setOnAction {
            if(Int.)
        }

        val button = Button("criar")
        button.setOnAction {
            val control = ClientControl(nameTextField.text, bar, 10000L + client_number++*1000)

            val client = Client()
            client.addControl(control)
            gameWorld.addEntity(client)
        }

        Entities.builder()
                .at(500.0, 500.0)
                .viewFromNode(button)
                .buildAndAttach(gameWorld)

        Entities.builder()
                .at(500.0, 480.0)
                .viewFromNode(textField)
                .buildAndAttach(gameWorld)
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