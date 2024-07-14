import {Diagram} from "./Diagram.jsx"
import {StatisticsTestInfo} from "./StatisticsTestInfo.jsx";
import {StatisticsSystemInfo} from "./StatisticsSystemInfo.jsx";

export function Statistics({info}) {
    return (
        <div className="w-100 vh-100">
            <div className="d-flex w-100 mb-3">
                <div className="w-50">
                    <Diagram info={info}/>
                </div>
                <div className="w-50">
                    <StatisticsTestInfo info={info}/>
                    <StatisticsSystemInfo info={info}/>
                </div>
            </div>
        </div>
    )
}
