import { useDispatch, useSelector } from 'react-redux'
import { setTabsAisde } from '../redux/tabs-slice'

export function TabsAside() {
  const dispatch = useDispatch()
  const activeTab = useSelector(state => state.tabs.tabAside)

  function handleToggleTab(event) {
    const { id } = event.target.dataset
    dispatch(setTabsAisde(id))
  }

  return (
    <>
      <div className="d-flex align-items-start ">
        <div className="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
          <button data-id="tab1" className={`nav-link fw-bold text-uppercase fs-8 p-4 ${activeTab === 'tab1' ? 'active' : ''}`} onClick={handleToggleTab}>
            Statistic
          </button>
          <button data-id="tab2" className={`nav-link fw-bold text-uppercase fs-8 p-4 ${activeTab === 'tab2' ? 'active' : ''}`} onClick={handleToggleTab}>
            Tests
          </button>
          <button data-id="tab3" className={`nav-link fw-bold text-uppercase fs-8 p-4 ${activeTab === 'tab3' ? 'active' : ''}`} onClick={handleToggleTab}>
            Reports
          </button>
        </div>
      </div>
    </>
  )
}
