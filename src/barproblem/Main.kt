package barproblem

/**
 * Created by matheusmartins on 5/17/17.
 */

fun main(args : Array<String>) {
    val viewController = Game()
    viewController.main(args)

    /*val bar = Bar(5)
    val clients = listOf(ClientThread("Matheus", 2000),
            ClientThread("Gabi", 3000),
            ClientThread("João Gonça", 4000),
            ClientThread("Paul Harris", 3000),
            ClientThread("Parente", 2000),
            ClientThread("Random", 1000))

    clients.forEach { c ->
        c.onEnterBar = {
            print(c.name + " entrou no bar.\n")
            //position.translateTowards(Point2D(512.0, 400.0), 10.0)
        }

        c.willSit = {
            print(c.name + " está tentando sentar.\n")
        }

        c.onSit = { chair ->
            print(c.name + " sentou na cadeira + " + chair + ".\n")
            //position.translateTowards(Point2D(512.0, 712.0), 10.0)
        }

        c.onLeaveBar = {
            print(c.name + " saiu do bar.\n")
        }
    }

    clients.forEach { c -> c.start(); c.enterBar(bar) }*/
}