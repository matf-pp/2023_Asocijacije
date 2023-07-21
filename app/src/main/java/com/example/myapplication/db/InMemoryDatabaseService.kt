package com.example.myapplication.db

import com.example.myapplication.model.Game
import com.example.myapplication.model.PlayerGroup

class InMemoryDatabaseService : DatabaseService {

    //ovo je sacuvano preko room-a
    private var playerGroupList : MutableList<PlayerGroup> = mutableListOf()
    //ovo moze da ostane ovdje
    private var currentGame : Game = Game()
    override fun connect() : DatabaseService {
        return this
    }
    //ovu funkciju je zamijenila room database
//    override fun getPlayerGroups() : MutableList<PlayerGroup> {
//        return this.playerGroupList
//    }
    override fun getPlayerGroupNames() : MutableList<String> {
        val pom : MutableList<String> = mutableListOf()
        for (plGroup in playerGroupList)
            pom.add(plGroup.getName())
        return pom
    }
    override fun addPlayerGroup(playerGroup : PlayerGroup) {
        playerGroupList.add(playerGroup)
    }
    override fun getPlayerGroup(name : String) : PlayerGroup {
        return playerGroupList.single { it.getName() == name }
    }
    override fun setPlayerGroups(playerGroups : MutableList<PlayerGroup>) {
        playerGroupList = playerGroups
    }
    override fun removePlayerGroup(playerGroup : PlayerGroup) {
        playerGroupList.remove(playerGroup)
    }
    override fun setGame(game : Game) {
        currentGame = game
    }
    override fun getGame() : Game {
        return currentGame
    }
}