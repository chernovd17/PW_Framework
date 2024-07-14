import {useSelector} from 'react-redux'
import React from 'react'
import '../../styles/Styles.css';
import {TestsListButtons} from "./TestsListButtons.jsx";
import {TestInfo} from "./TestInfo.jsx";

export function Tests({tests}) {
    const testName = useSelector(state => state.file.testName)
    let testTitle = tests.find(test => test.title === testName)

    return (
        <div className="d-flex">
            <TestsListButtons tests={tests}/>
            <TestInfo test={testTitle}/>
        </div>
    )
}
