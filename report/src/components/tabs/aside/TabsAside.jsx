import {TabsAsideButton} from "./TabsAsideButton.jsx";
import {useSelector} from "react-redux";

export const TabsAside = () => {
    let fileInfo = useSelector(state => state.file.fileInfo)
    let isReportSelected = Object.keys(fileInfo).length === 0
    return (
        <div className="d-flex align-items-start ">
            <div className="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <TabsAsideButton tab="tab1" title="Statistic" disabled={isReportSelected}/>
                <TabsAsideButton tab="tab2" title="Tests" disabled={isReportSelected}/>
                <TabsAsideButton tab="tab3" title="Reports"/>
            </div>
        </div>
    )
}
