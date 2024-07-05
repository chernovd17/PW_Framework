import {TabsButton} from "../../common/TabsButton.jsx";
import {useSelector} from "react-redux";
import {setTabsTest} from "../../../redux/tabs-slice.js";

export const TabsTestButton = ({tab, title}) => {

    const selector = useSelector(state => state.tabs.tabTest)
    return (
        <TabsButton
            tab={tab}
            title={title}
            selector={selector}
            handleFunction={setTabsTest}
            style="fs-3"
        />
    )
}
