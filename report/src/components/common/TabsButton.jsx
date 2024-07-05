import {useDispatch} from "react-redux";

export const TabsButton = ({tab, title, selector, handleFunction, style, disabled = false}) => {
    const dispatch = useDispatch()
    const activeTabClass = (selector === tab) ? 'active' : ''

    function handleToggleTab(event) {
        const {id} = event.target.dataset
        dispatch(handleFunction(id))
    }

    return (
        <button data-id={tab}
                className={`nav-link fw-bold text-uppercase ${style} ${activeTabClass}`}
                onClick={handleToggleTab}
                disabled={disabled}>
            {title}
        </button>
    )
}
