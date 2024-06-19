import {useSelector} from 'react-redux'
import {ArcElement, Chart as ChartJS, Legend, Tooltip} from "chart.js";
import {Doughnut} from "react-chartjs-2";
import {calculatePercentage, getAllSkippedTests} from "./Functions";

ChartJS.register(ArcElement, Tooltip, Legend);

export function Diagram() {
    const fileInfo = useSelector(state => state.file.fileInfo)
    const {countOfPassedTests, countOfFailedTests, countOfFatalTests, countOfUnknownTests, allTestsCount} = fileInfo
    let skipped = getAllSkippedTests(fileInfo);

    const passedPer = calculatePercentage(countOfPassedTests, allTestsCount);
    const failedPer = calculatePercentage(countOfFailedTests, allTestsCount);
    const fatalPer = calculatePercentage(countOfFatalTests, allTestsCount)
    const skippedPer = calculatePercentage(skipped, allTestsCount);
    const unknownPer = calculatePercentage(countOfUnknownTests, allTestsCount);

    let colors = ["#01a901", "#fce251", "#f61717"];
    let labels = ["PASSED " + countOfPassedTests, "FAILED " + countOfFailedTests, "FATAL " + countOfFatalTests];
    let data = [countOfPassedTests, countOfFailedTests, countOfFatalTests];

    if (skipped > 0) {
        labels.push("SKIPPED " + skipped);
        data.push(skipped);
        colors.push("#C0C0C0");
    }
    if (countOfUnknownTests > 0) {
        labels.push("UNKNOWN " + countOfUnknownTests);
        data.push(countOfUnknownTests);
        colors.push("#bdb76b");
    }

    const pieChartData = {
        labels: labels,
        datasets: [{
            data: data,
            label: "Tests ",
            backgroundColor: colors,
        }]
    };

    return (
        <Doughnut
            data={pieChartData}
        />
    );
}
