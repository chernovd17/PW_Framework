import {calculatePercentage, getAllSkippedTests} from "../../functions/Functions.jsx";
import {StatisticsInfoRow} from "./StatisticsInfoRow.jsx";

export const StatisticsTestInfo = ({ info }) => {

    const {
        countOfPassedTests,
        countOfFailedTests,
        countOfFatalTests,
        countOfUnknownTests,
        allTestsCount
    } = info
    const skipped = getAllSkippedTests(info)

    function countPercentages(countOfRequiredTests) {
        return countOfRequiredTests === 0 ?
            0 : countOfRequiredTests + "(" + calculatePercentage(countOfRequiredTests, allTestsCount) + " %)"
    }

    return (
        <div className="w-50">
            <h1 className="text-center text-uppercase">Tests Info</h1>
            <StatisticsInfoRow title="Total" message={allTestsCount}/>
            <StatisticsInfoRow title="Passed" message={countPercentages(countOfPassedTests)}/>
            <StatisticsInfoRow title="Failed" message={countPercentages(countOfFailedTests)}/>
            <StatisticsInfoRow title="Fatal" message={countPercentages(countOfFatalTests)}/>
            {skipped > 0 && <StatisticsInfoRow title="Skipped" message={countPercentages(skipped)}/>}
            {countOfUnknownTests > 0 && <StatisticsInfoRow title="Unknown" message={countPercentages(countOfUnknownTests)}/>}
        </div>
    )
}
