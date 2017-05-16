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

class ViewController {
    @FXML
    private lateinit var myButton: Button

    @FXML
    private lateinit var test: Button

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
}