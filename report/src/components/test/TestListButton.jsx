import React from "react";
import {setTestName} from "../../redux/file-slice.js";
import {useDispatch, useSelector} from "react-redux";

const colorMap = {
    SUCCESS: 'success_text_color',
    FAIL: 'fail_text_color',
    FATAL: 'fatal_text_color',
}

export const TestListButton = ({test, index}) => {
    const dispatch = useDispatch()
    const testName = useSelector(state => state.file.testName)
    const buttonColor = colorMap[test.testStatus]
    const buttonClass = testName === test.title ? 'btn-primary' : 'btn-outline-primary'

    function handleClickTest(event) {
        const {id} = event.target.dataset
        dispatch(setTestName(id))
    }

    return <button
        data-id={test.title}
        key={index}
        className={`${buttonColor} btn my-1 fw-semibold fs-8 ${buttonClass}`}
        onClick={handleClickTest}>
        {test.title}
    </button>
}
