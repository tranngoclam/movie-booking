package io.github.lamtran.moviebooking2

sealed class Seat {

    object EmptySeat : Seat()

    object ReservedSeat : Seat()

    object ChosenSeat : Seat()

    object UnchosenSeat : Seat()
}
