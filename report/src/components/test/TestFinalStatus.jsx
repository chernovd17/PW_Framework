import {splitByNewLine} from "../../functions/Functions.jsx";
import React from "react";

export const TestFinalStatus = ({test}) => {
   return <div className="w-50">
        <div className="fw-bold">
            <div className="fs-4" align="center">Final Status</div>
            <div className="fw-semibold">{
                splitByNewLine(test.finalStatusString).map((item, i) => (
                    <div className="pt-0 pb-0" key={i}>{item}</div>
                ))}
            </div>
        </div>
    </div>
}
