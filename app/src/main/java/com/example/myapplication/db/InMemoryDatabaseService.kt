package com.example.myapplication.db

import com.example.myapplication.model.Player
import com.example.myapplication.model.PlayerGroup

private var firstTimeIn: Boolean = true

class InMemoryDatabaseService : DatabaseService {

    var playerGroupList : MutableList<PlayerGroup> = mutableListOf()

    override fun connect(): DatabaseService {
        return this
    }

    override fun getPlayerGroups(): MutableList<PlayerGroup> {
        return this.playerGroupList
    }

    override fun getPlayerGroupNames(): MutableList<String> {
        var pom : MutableList<String> = mutableListOf()
        for (plGroup in this.playerGroupList)
            pom.add(plGroup.getPGName())
        return pom
    }

    override fun addPlayerGroup(playerGroup: PlayerGroup) {
        playerGroupList.add(playerGroup)
        println("AddPlayerGroup function: ${playerGroup.getPGName()}, ${playerGroup.getPGList()}" )
    }

    override fun getPlayerGroup(name: String) : PlayerGroup {
        return this.playerGroupList.single { it.getPGName() == name }
    }

    override fun removePlayerGroup(playerGroup: PlayerGroup) {
        TODO("Not yet implemented")
    }

    override fun dataInit() {
        if(firstTimeIn) {

            var playerGroupAsocijacije = PlayerGroup("Asocijacije")
            var tanja = Player("tanja")
            var anja = Player("anja")
            var vaske = Player("vaske")
            var zile = Player("zile")
            playerGroupAsocijacije.add(tanja)
            playerGroupAsocijacije.add(anja)
            playerGroupAsocijacije.add(vaske)
            playerGroupAsocijacije.add(zile)

            var playerGroupMatf = PlayerGroup("Matf")
            var mila = Player("mila")
            var irina = Player("irina")
            var milos = Player("milos")
            var tina = Player("tina")
            playerGroupMatf.add(mila)
            playerGroupMatf.add(irina)
            playerGroupMatf.add(milos)
            playerGroupMatf.add(tina)
            playerGroupMatf.add(tanja)
            playerGroupMatf.add(anja)
            playerGroupMatf.add(vaske)
            playerGroupMatf.add(zile)

            DatabaseServiceProvider.db.addPlayerGroup(playerGroupMatf)
            DatabaseServiceProvider.db.addPlayerGroup(playerGroupAsocijacije)

            firstTimeIn = false
        }
    }
}