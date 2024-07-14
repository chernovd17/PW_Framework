import {TabsTestButton} from "./TabsTestButton.jsx";

export const TabsTest = () =>
    (
        <div className="nav nav-tabs w-100 my-5 text-uppercase">
            <TabsTestButton tab="tab1" title="All"/>
            <TabsTestButton tab="tab2" title="Before"/>
            <TabsTestButton tab="tab3" title="Test"/>
            <TabsTestButton tab="tab4" title="After"/>
        </div>
    )

