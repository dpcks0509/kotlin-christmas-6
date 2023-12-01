package christmas

import christmas.controller.ChristmasController
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val christmasController = ChristmasController(InputView(), OutputView())
    christmasController.run()
}
