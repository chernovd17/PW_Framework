import React from "react";
import {TestListButton} from "./TestListButton.jsx";

export const TestsListButtons = ({tests}) => {

    return <div className="w-25">
        <h1 className="text-center">Tests</h1>
        <ul className="list-group mx-5">
            {tests.map((test, index) => <TestListButton test={test} index={index} />)}
        </ul>
    </div>
}
