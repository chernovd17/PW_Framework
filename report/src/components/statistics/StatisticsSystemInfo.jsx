import {StatisticsRow} from "./StatisticsRow.jsx";
import {convertDateTime, convertSecondsToTime} from "../../functions/Functions.jsx";

export const StatisticsSystemInfo = ({info}) => {
    const {
        allTestsCount,
        duration,
        operationSystem,
        browser,
        countOfThreads,
        startDateTime,
        endDateTime
    } = info

    const seconds = duration ? duration.seconds : null

    return (
        <div className="w-50">
            <h1 className="text-center text-uppercase">System Info</h1>
            <StatisticsRow name="Operation System" value={operationSystem}/>
            <StatisticsRow name="Browser" value={browser}/>
            <StatisticsRow name="Threads" value={countOfThreads}/>
            <StatisticsRow name="Start Date Time" value={convertDateTime(startDateTime)}/>
            <StatisticsRow name="End Date Time" value={convertDateTime(endDateTime)}/>
            <StatisticsRow name="Suite Duration" value={convertSecondsToTime(seconds)}/>
            <StatisticsRow name="Avg. Test Duration" value={convertSecondsToTime(seconds / allTestsCount)}/>
            <StatisticsRow name="The longest test" value="need to implement"/>
            <StatisticsRow name="Count of bugs" value="need to implement"/>
            <StatisticsRow name="All linked bugs" value="need to implement"/>
        </div>
    )
}
