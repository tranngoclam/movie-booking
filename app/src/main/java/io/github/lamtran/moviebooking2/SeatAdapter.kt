package io.github.lamtran.moviebooking2

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SeatAdapter<VH : SeatAdapter.SeatViewHolder> : RecyclerView.Adapter<VH>() {

    private val seats: List<Seat> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = seats.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    open class SeatViewHolder(open val view: View) : RecyclerView.ViewHolder(view) {

    }

    class EmptySeatViewHolder(override val view: View) : SeatViewHolder(view) {

    }

    class ReservedSeatViewHolder(override val view: View) : SeatViewHolder(view) {

    }

    class ChosenSeatViewHolder(override val view: View) : SeatViewHolder(view) {

    }

    class AvailableSeatViewHolder(override val view: View) : SeatViewHolder(view) {

    }
}
