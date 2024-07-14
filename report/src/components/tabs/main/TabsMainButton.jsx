import {setTabsMain} from "../../../redux/tabs-slice.js";
import {TabsButton} from "../../common/TabsButton.jsx";
import {useSelector} from "react-redux";

export const TabsMainButton = ({tab, title}) => {

    const selector = useSelector(state => state.tabs.tabMain)
    return (
        <TabsButton
            tab={tab}
            title={title}
            selector={selector}
            handleFunction={setTabsMain}
            style="fs-3"
        />
    )
}
