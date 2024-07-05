import { Provider } from 'react-redux'
import { store } from './redux/store'
import { Main } from './pages/Main'

export function App() {
  return (
    <>
      <Provider store={store}>
        <Main />
      </Provider>
    </>
  )
}

