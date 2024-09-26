package com.example.atividadexp2

data class Personagem(
    var name: String,
    private var _level: Int,
    private var _bonus: Int,
    private var _modificadores: Int
) {
    // Propriedades públicas para os atributos
    var level: Int
        get() = _level
        set(value) {
            _level = value
            updatePoder()
        }

    var bonus: Int
        get() = _bonus
        set(value) {
            _bonus = value
            updatePoder()
        }

    var modificadores: Int
        get() = _modificadores
        set(value) {
            _modificadores = value
            updatePoder()
        }

    var poder: Int = 0
        private set

    private fun updatePoder() {
        poder = _level + _bonus + _modificadores
    }

    init {
        updatePoder()
    }
}
