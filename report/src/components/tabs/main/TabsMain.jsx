import {TabsMainButton} from "./TabsMainButton.jsx";

export const TabsMain = () =>
    (
        <div className="nav nav-tabs w-100 my-3 text-uppercase">
            <TabsMainButton tab="tab1" title="All"/>
            <TabsMainButton tab="tab2" title="Passed"/>
            <TabsMainButton tab="tab3" title="Failed"/>
            <TabsMainButton tab="tab4" title="Fatal"/>
        </div>
    )
