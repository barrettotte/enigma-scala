package com.barrettotte.enigma

class Rotor(wiring: String, startPosition: Int, notch: Char) {
  private val rotorSize: Int = 26
  private var position: Int = startPosition

  def this(wiring: String) = this(wiring, 0, 'Q')

  def passForward(idx: Int): Int = {
    val shifted = (idx + position) % rotorSize
    (wiring(shifted) - 'A' - position + rotorSize) % rotorSize
  }

  def passBackward(idx: Int): Int = {
    val shifted = (idx + position) % rotorSize
    (wiring.indexOf((shifted + 'A').toChar) - position + rotorSize) % rotorSize
  }

  def reset(): Unit = position = 0

  def rotate(): Unit = position = (position + 1) % rotorSize

  def atNotch(): Boolean = position == wiring.indexOf(notch)

  override def toString: String = {
    s"Rotor{wiring: $wiring, position: $position, startPosition: $startPosition, notch: $notch}"
  }
}
