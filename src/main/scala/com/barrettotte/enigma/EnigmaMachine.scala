package com.barrettotte.enigma

class Rotor(wiring: String, ringSetting: Int = 0) {
  private val rotorSize: Int = 26
  private var position: Int = ringSetting
  private val notchPosition: Int = wiring.indexOf('Q')

  def passthrough(index: Int): Int = {
    val shifted = (index + position) % rotorSize
    (wiring(shifted) - 'A' - position + rotorSize) % rotorSize
  }

  def passthroughRev(index: Int): Int = {
    val shifted = (index + position) % rotorSize
    (wiring.indexOf((shifted + 'A').toChar) - position + rotorSize) % rotorSize
  }

  def step(): Unit = {
    position = (position + 1) % rotorSize
  }

  def atNotch(): Boolean = {
    position == notchPosition
  }

  override def toString: String = {
    s"Rotor{wiring: $wiring, ringSetting: $ringSetting, " +
      s"position: rotorSize: $rotorSize, position: $position, notchPosition: $notchPosition}"
  }
}

class Plugboard(wiring: Map[Char, Char]) {
  def process(c: Char): Char = wiring.getOrElse(c, c)

  override def toString: String = s"Plugboard{wiring: $wiring}"
}

class EnigmaMachine(rotorI: String, rotorII: String, rotorIII: String, reflector: String, plugboardWiring: Map[Char, Char]) {
  var rotorLeft: Rotor = new Rotor(rotorI)
  var rotorMiddle: Rotor = new Rotor(rotorII)
  var rotorRight: Rotor = new Rotor(rotorIII)
  var reflectorRotor: Rotor = new Rotor(reflector)
  var plugboard: Plugboard = new Plugboard(plugboardWiring)

  def encrypt(input: String): String = {
    var result = ""
    for (c <- input) {
      if (c.isLetter) {
        stepRotors()
        result += encrypt(c)
      } else {
        result += c
      }
    }
    result
  }

  private def encrypt(c: Char): Char = {
    val in = plugboard.process(c.toUpper)
    val idx = in - 'A'
    val outIdx = passthroughRotors(idx)
    val reflected = reflectorRotor.passthrough(outIdx)
    val revIdx = passthroughRotorsRev(reflected)

    plugboard.process((revIdx + 'A').toChar)
  }

  private def passthroughRotors(in: Int): Int = {
    val r1 = rotorRight.passthrough(in)
    val r2 = rotorMiddle.passthrough(r1)
    rotorLeft.passthrough(r2)
  }

  private def passthroughRotorsRev(in: Int): Int = {
    val r1 = rotorLeft.passthroughRev(in)
    val r2 = rotorMiddle.passthroughRev(r1)
    rotorRight.passthroughRev(r2)
  }

  private def stepRotors(): Unit = {
    rotorRight.step()
    if (rotorRight.atNotch()) {
      rotorMiddle.step()
      if (rotorMiddle.atNotch()) {
        rotorLeft.step()
      }
    }
  }

  override def toString: String = {
    s"""
    |EnigmaMachine{
    |  rotorLeft: $rotorLeft,
    |  rotorMiddle: $rotorMiddle,
    |  rotorRight: $rotorRight,
    |  reflectorRotor: $reflectorRotor,
    |  plugboard: $plugboard
    |}""".stripMargin
  }
}
