import {Diagram} from "./Diagram.jsx"
import {useSelector} from 'react-redux'
import {calculatePercentage, convertDateTime, convertSecondsToTime, getAllSkippedTests} from "./Functions.jsx";

//possible move this to explicit component
function renderTestsStatRow(title, message) {
    const textColorMap = {
        Passed: "success_text_color",
        Failed: "fail_text_color",
        Fatal: "fatal_text_color",
        Skipped: "skipped_text_color",
        Unknown: "unknown_text_color",
    }

    let textColorClass = textColorMap[title] || "text-black"
    return (
        <div className="row ">
            <div className="col fw-bold fs-10 " align="left">
                <span align="center" className={"large_text " + textColorClass}>{title}</span>
            </div>
            <div className="col  fw-bold fs-10 ">
                <span align="left" className={"large_text " + textColorClass}>{message}</span>
            </div>
        </div>
    )
}

function renderOptionalTestsStatRow(title, message) {
    if (message !== 0) {
        return renderTestsStatRow(title, message);
    }
}

export function Statistic() {
    const info = useSelector(state => state.file.fileInfo)
    const {
        countOfPassedTests,
        countOfFailedTests,
        countOfFatalTests,
        countOfUnknownTests,
        allTestsCount,
        duration,
        operationSystem,
        browser,
        countOfThreads,
        startDateTime,
        endDateTime
    } = info
    const skipped = getAllSkippedTests(info);
    const seconds = duration ? duration.seconds : null

    function countPercentages(countOfRequiredTests, allTests = allTestsCount) {
        return countOfRequiredTests === 0 ?
            0 : countOfRequiredTests + "(" + calculatePercentage(countOfRequiredTests, allTests) + " %)"
    }

    return (
        <div className="w-100 vh-100">
            <div className="d-flex w-100 mb-3">
                <div className="w-50">
                    <h3 className="text-center text-uppercase">General stat</h3>
                    <div className="w-75 h-100 mx-auto">
                        <Diagram/>
                    </div>
                </div>

                <div className="w-50 ">

                    <div className="w-50">
                        <h1 className="text-center text-uppercase">Tests Info</h1>
                        {renderTestsStatRow("Total", allTestsCount)}
                        {renderTestsStatRow("Passed", countPercentages(countOfPassedTests))}
                        {renderTestsStatRow("Failed", countPercentages(countOfFailedTests))}
                        {renderTestsStatRow("Fatal", countPercentages(countOfFatalTests))}
                        {renderOptionalTestsStatRow("Skipped", countPercentages(skipped))}
                        {renderOptionalTestsStatRow("Unknown", countPercentages(countOfUnknownTests))}
                    </div>
                    <div className="w-50">
                        <h1 className="text-center text-uppercase">System Info</h1>
                        <div className="row ">
                            <div className="col fw-bold fs-10 " align="left">
                                <span align="center">Operation System</span>
                            </div>
                            <div className="col  fw-bold fs-10 ">
                                <span align="left">{operationSystem}</span>
                            </div>
                        </div>

                        {/*
                        looks like we can create small component with two props
                        for example </StatisticRow name="Browser" value = {browser}>
                        pros: it will increase readability, decrease duplication
                         and make it more flexible for possible changes
                        */}
                        <div className="row ">
                            <div className="col fw-bold fs-10 " align="left">
                                <span align="center">Browser</span>
                            </div>
                            <div className="col  fw-bold fs-10">
                                <span align="left">{browser}</span>
                            </div>
                        </div>

                        <div className="row ">
                            <div className="col fw-bold fs-10 " align="left">
                                <span align="center">Threads</span>
                            </div>
                            <div className="col  fw-bold fs-10">
                                <span align="left">{countOfThreads}</span>
                            </div>
                        </div>

                        <div className="row ">
                            <div className="col fw-bold fs-10 " align="left">
                                <span align="center">Start Date-Time</span>
                            </div>

                            <div className="col  fw-bold fs-10 ">
                                <span align="left">{convertDateTime(startDateTime)}</span>
                            </div>
                        </div>

                        <div className="row ">
                            <div className="col fw-bold fs-10 " align="left">
                                <span align="center">End Date Time</span>
                            </div>
                            <div className="col  fw-bold fs-10 ">
                                <span align="left">{convertDateTime(endDateTime)}</span>
                            </div>
                        </div>

                        <div className="row ">
                            <div className="col fw-bold fs-10 " align="left">
                                <span align="center">Suite Duration</span>
                            </div>
                            <div className="col  fw-bold fs-10">
                                <span align="left">{convertSecondsToTime(seconds)}</span>
                            </div>
                        </div>

                        <div className="row ">
                            <div className="col fw-bold fs-10 " align="left">
                                <span align="center">Avg. test duration</span>
                            </div>
                            <div className="col  fw-bold fs-10 ">
                                <span align="left">{convertSecondsToTime(seconds / allTestsCount)}</span>
                            </div>
                        </div>
                        <div className="row ">
                            <div className="col fw-bold fs-10 " align="left">
                                <span align="center">The longest test:</span>
                            </div>
                            <div className="col  fw-bold fs-10 ">
                                <span align="left">need to implement</span>
                            </div>
                        </div>
                        <div className="row ">
                            <div className="col fw-bold fs-10 " align="left">
                                <span align="center">Count of bugs:</span>
                            </div>
                            <div className="col  fw-bold fs-10 ">
                                <span align="left">need to implement</span>
                            </div>
                        </div>
                        <div className="row ">
                            <div className="col fw-bold fs-10 " align="left">
                                <span>All Linked bugs:</span>
                            </div>
                            <div className="col  fw-bold fs-10 ">
                                <span align="left">need to implement</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
