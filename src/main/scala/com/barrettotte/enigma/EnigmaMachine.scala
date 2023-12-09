package com.barrettotte.enigma

class EnigmaMachine(rotorLeft: Rotor, rotorMiddle: Rotor, rotorRight: Rotor, reflector: Rotor, plugboard: Map[Char, Char]) {

  def encrypt(input: String): String = input.map(encrypt).mkString

  def resetRotors(): Unit = {
    rotorLeft.reset()
    rotorMiddle.reset()
    rotorRight.reset()
  }

  private def plug(c: Char): Char = plugboard.getOrElse(c, c)

  private def encrypt(c: Char): Char = {
    if (!c.isLetter) {
      return c
    }
    rotateRotors()

    val plugged = plug(c.toUpper)
    val forward = passForward(plugged - 'A')
    val reflected = reflector.passForward(forward)
    val backward = passBackward(reflected)

    plug(('A' + backward).toChar)
  }

  private def passForward(idx: Int): Int = {
    val right = rotorRight.passForward(idx)
    val mid = rotorMiddle.passForward(right)
    rotorLeft.passForward(mid)
  }

  private def passBackward(idx: Int): Int = {
    val left = rotorLeft.passBackward(idx)
    val mid = rotorMiddle.passBackward(left)
    rotorRight.passBackward(mid)
  }

  private def rotateRotors(): Unit = {
    rotorRight.rotate()
    if (rotorRight.atNotch()) {
      rotorMiddle.rotate()
      if (rotorMiddle.atNotch()) {
        rotorLeft.rotate()
      }
    }
  }

  override def toString: String = {
    s"""EnigmaMachine{
    |  rotorLeft: $rotorLeft,
    |  rotorMiddle: $rotorMiddle,
    |  rotorRight: $rotorRight,
    |  reflector: $reflector,
    |  plugboard: $plugboard
    |}""".stripMargin
  }
}
