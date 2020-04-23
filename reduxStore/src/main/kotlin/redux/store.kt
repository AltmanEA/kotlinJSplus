package redux

import data.State

fun store(): Store<FullState, RAction, WrapperAction> = createStore(
    ::rootReducer,
    initialState(),
    compose(
        rThunk(),
        rEnhancer(),
        js("if(window.__REDUX_DEVTOOLS_EXTENSION__ )window.__REDUX_DEVTOOLS_EXTENSION__ ();else(function(f){return f;});")
    )
)

fun <T> Map<Int, T>.newId() =
    (this.maxBy { it.key }?.key ?: -1) + 1

fun State.presentsStudent(idStudent: Int) =
    presents.map {
        it.key to (it.value[idStudent] ?: false)
    }.toMap()
