package com.example.myapplication.db

import com.example.myapplication.model.PlayerGroup

interface DatabaseService {
    fun connect() : DatabaseService
    fun getPlayerGroups() : MutableList<PlayerGroup>
    fun getPlayerGroupNames() : MutableList<String>
    fun addPlayerGroup (playerGroup: PlayerGroup)
    fun getPlayerGroup (name: String)
    fun removePlayerGroup (playerGroup: PlayerGroup)
    fun dataInit()
}