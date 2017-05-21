[![](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=tranngoclam288%40gmail%2ecom&lc=VN&item_name=Lam%20Tran&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted)

# Movie Booking

This is a simple example for booking movie seats in general movie/film mobile application. The concept is simply combined of `State Design Pattern`, `Android Data Binding` and `Multibinding + Autofactory` below.

In the future, we can extend this concept by using `RxJava` to handle behavior in only a stream, also chain any stream to process our logic.

Hope it will be useful for anyone who are building this context.

# Concept
### State Design Pattern

A seat has many states, such as:
* **EMPTY**: this is not a seat, just a blank space, it does not have any action.
* **RESERVED**: this is a reserved seat, which can not be booked anymore.
* **AVAILABLE**: this seat can be book by selecting.
* **SELECTED**: this seat is being selected, it can be deselected to be available.

To manage these states, we use [State Design Pattern](https://github.com/iluwatar/java-design-patterns/tree/master/state), also for the definition of state action, current state and switching between different states.
* An `available seat` can be select to be a `selected seat`, its state changes from `AVAILABLE` to `SELECTED`.
* A `selected seat` can be deselected to be an `available seat`, its state changes from `SELECTED` to `AVAILABLE`

### Android Data Binding
For any state changes, we update the UI of the seat correspondingly by using `@BindingAdapter` and `@Bindable` annotations defined in [Android Data Binding](https://developer.android.com/topic/libraries/data-binding/index.html). Example:
* In a layout of a seat, we bind a state value to UI by: `app:state="@{data.state}"`.
* A state defined in Java code is: `@Bindable State mState;` and must have getter `public State getState() { return mState; }`.
* If we want to seatChange the state and update to the UI, we set `mState = new NewState();` then `notifyPropertyChanged(BR.state);`, noted that `BR.state` is generated after building our application.

### Multibinding and Autofactory
For the Dependency Injection, we use [Dagger2](https://github.com/google/dagger) to setup. Dagger2 also supports Multibinding, it means that we can inject any `ViewHolder` into our adapter to render the corresponding layout of an item in a list.

By using [Autofactory](https://github.com/google/auto/tree/master/factory), we construct some ViewHolders and provide these factory in a module. These factory must go with a key defined by `@IntKey`, which is an item view type. Example:
``` java
@Provides
@IntoMap
@IntKey(Seat.Type.AVAILABLE)
SeatViewHolderFactory provideAvailableSeatViewHolder() {
  return new AvailableSeatViewHolderFactory();
}
```

``` java
public interface SeatViewHolderFactory {
  SeatViewHolder createViewHolder(ViewDataBinding binding);
}
```

``` java
@Override
public SeatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
  ItemSeatBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_seat, parent, false);
  return mSeatViewHolderFactoryMap.get(viewType).createViewHolder(binding);
}
```

# TODO
* Write Unit Test to verify any action from the views.
* Support selecting many seats and handle exception if the selection reaches the maximum.
* Try to use RxJava to extend the concept.

# References
* http://frogermcs.github.io/inject-everything-viewholder-and-dagger-2-example/
* https://github.com/google/android-ui-toolkit-demos/tree/master/DataBinding/DataBoundRecyclerView

# License
```
MIT License

Copyright (c) 2017 Lam Tran (tranngoclam288@gmail.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
