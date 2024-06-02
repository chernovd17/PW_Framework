import { useDispatch, useSelector } from 'react-redux'
import { setTabsTest } from '../redux/tabs-slice'

export function TabsTest() {
  const dispatch = useDispatch()
  const activeTab = useSelector(state => state.tabs.tabTest)

  function handleToggleTab(event) {
    const { id } = event.target.dataset
    dispatch(setTabsTest(id))
  }

  return (
    <div className="nav nav-tabs w-100 my-5 text-uppercase">
      <button data-id="tab1" className={`nav-link fw-bold text-uppercase fs-3 ${activeTab === 'tab1' ? 'active' : ''}`} onClick={handleToggleTab}>
        All
      </button>
      <button data-id="tab2" className={`nav-link fw-bold text-uppercase fs-3 ${activeTab === 'tab2' ? 'active' : ''}`} onClick={handleToggleTab}>
        Before
      </button>
      <button data-id="tab3" className={`nav-link fw-bold text-uppercase fs-3 ${activeTab === 'tab3' ? 'active' : ''}`} onClick={handleToggleTab}>
        Test
      </button>
      <button data-id="tab4" className={`nav-link fw-bold text-uppercase fs-3 ${activeTab === 'tab4' ? 'active' : ''}`} onClick={handleToggleTab}>
        After
      </button>
    </div>
  )
}
