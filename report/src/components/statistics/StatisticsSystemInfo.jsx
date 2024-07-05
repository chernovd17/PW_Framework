import {Row} from "../common/Row.jsx";
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
            <Row name="Operation System" value={operationSystem}/>
            <Row name="Browser" value={browser}/>
            <Row name="Threads" value={countOfThreads}/>
            <Row name="Start Date Time" value={convertDateTime(startDateTime)}/>
            <Row name="End Date Time" value={convertDateTime(endDateTime)}/>
            <Row name="Suite Duration" value={convertSecondsToTime(seconds)}/>
            <Row name="Avg. Test Duration" value={convertSecondsToTime(seconds / allTestsCount)}/>
            <Row name="The longest test" value="need to implement"/>
            <Row name="Count of bugs" value="need to implement"/>
            <Row name="All linked bugs" value="need to implement"/>
        </div>
    )
}
