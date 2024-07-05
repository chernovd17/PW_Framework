import {setTabsAside} from "../../../redux/tabs-slice.js";
import {TabsButton} from "../../common/TabsButton.jsx";
import {useSelector} from "react-redux";

export const TabsAsideButton = ({tab, title, disabled}) => {

    const selector = useSelector(state => state.tabs.tabAside)
    return (
        <TabsButton
            tab={tab}
            title={title}
            selector={selector}
            handleFunction={setTabsAside}
            style="fs-8 p-4"
            disabled={disabled}
        />
    )
}

