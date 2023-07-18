package com.example.myapplication.db

import com.example.myapplication.model.Game
import com.example.myapplication.model.Player
import com.example.myapplication.model.PlayerGroup

private var firstTimeIn: Boolean = true

class InMemoryDatabaseService : DatabaseService {

    private var playerGroupList : MutableList<PlayerGroup> = mutableListOf()
    private var currentGame : Game = Game()
    override fun connect(): DatabaseService {
        return this
    }
    override fun getPlayerGroups(): MutableList<PlayerGroup> {
        return this.playerGroupList
    }
    override fun getPlayerGroupNames(): MutableList<String> {
        val pom : MutableList<String> = mutableListOf()
        for (plGroup in playerGroupList)
            pom.add(plGroup.getPGName())
        return pom
    }
    override fun addPlayerGroup(playerGroup: PlayerGroup) {
        playerGroupList.add(playerGroup)
    }
    override fun getPlayerGroup(name: String) : PlayerGroup {
        return playerGroupList.single { it.getPGName() == name }
    }
    override fun removePlayerGroup(playerGroup: PlayerGroup) {
        TODO("Not yet implemented")
    }
    override fun setGame(game: Game) {
        currentGame = game
    }
    override fun getGame(): Game {
        return currentGame
    }
    override fun dataInit() {
        if(firstTimeIn) {
            val playerGroupAsocijacije = PlayerGroup("Asocijacije")
            val tanja = Player("tanja")
            val anja = Player("anja")
            val vaske = Player("vaske")
            val zile = Player("zile")
            playerGroupAsocijacije.add(tanja)
            playerGroupAsocijacije.add(anja)
            playerGroupAsocijacije.add(vaske)
            playerGroupAsocijacije.add(zile)

            //morala sam da napravim nove instance za tanju, anju,.., da im se ne bi sabirale pobjede i iz grupe Asocijacije
            val playerGroupMatf = PlayerGroup("Matf")
            val tanja2 = Player("tanja")
            val anja2 = Player("anja")
            val vaske2 = Player("vaske")
            val zile2 = Player("zile")
            val mila = Player("mila")
            val irina = Player("irina")
            val milos = Player("milos")
            val tina = Player("tina")
            playerGroupMatf.add(mila)
            playerGroupMatf.add(irina)
            playerGroupMatf.add(milos)
            playerGroupMatf.add(tina)
            playerGroupMatf.add(tanja2)
            playerGroupMatf.add(anja2)
            playerGroupMatf.add(vaske2)
            playerGroupMatf.add(zile2)

            DatabaseServiceProvider.db.addPlayerGroup(playerGroupMatf)
            DatabaseServiceProvider.db.addPlayerGroup(playerGroupAsocijacije)

            firstTimeIn = false
        }
    }
}