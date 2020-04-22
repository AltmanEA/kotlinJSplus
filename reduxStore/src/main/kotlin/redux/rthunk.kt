package redux
import kotlinext.js.js
import kotlin.reflect.KFunction0

interface RThunk : RAction {
    operator fun invoke(
        dispatch: (RAction) -> WrapperAction,
        getState: KFunction0<*>
    ): WrapperAction
}

fun <S> rThunk() =
    applyMiddleware<S, RAction, WrapperAction, RAction, WrapperAction>(
        {store ->
            {next ->
                {action ->
                    if(action is RThunk)
                        action(store::dispatch, store::getState)
                    else
                        next(action)
                }
            }
        }
    )

val nullAction = js {}.unsafeCast<WrapperAction>()