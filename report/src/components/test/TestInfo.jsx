import React from "react";
import {useSelector} from "react-redux";
import {Row} from "../common/Row.jsx";
import {TestFinalStatus} from "./TestFinalStatus.jsx";
import {TabsTest} from "../tabs/test/TabsTest.jsx";
import {TestTableHeader} from "./TestTableHeader.jsx";
import {TestTableRow} from "./TestTableRow.jsx";

export const TestInfo = ({test}) => {
    const activeTabTest = useSelector(state => state.tabs.tabTest)
    const testDataFetchers = {
        'tab1': test?.testRows,
        'tab2': test?.beforeTestRows,
        'tab3': test?.testRows,
        'tab4': test?.afterTestRows,
    }
    const testRows = testDataFetchers[activeTabTest]

    return test && (
        <div className="w-100">
            <div className='d-flex'>
                <div className='w-100 '>
                    <div className="fs-4 fw-bold " align="center">Info</div>
                    <Row name="Test Name" value={test.title}/>
                    <Row name="Description" value={test.description}/>
                    <Row name="Link" value="TR/TL Link"/>
                    <Row name="Duration" value="duration"/>
                    <Row name="Bugs" value={test.bug}/>
                    <TestFinalStatus test={test}/>
                </div>
            </div>
            <div>
                <TabsTest/>
                <div className="container text-left border">
                    <TestTableHeader/>
                    <TestTableRow tests={testRows}/>
                </div>
            </div>
        </div>
    )
}
