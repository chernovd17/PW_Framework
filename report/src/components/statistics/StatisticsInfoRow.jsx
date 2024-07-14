export const StatisticsInfoRow = ({title, message}) => {
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
            <div className="col fw-bold fs-10" align="left">
                <span align="center" className={"large_text " + textColorClass}>{title}</span>
            </div>
            <div className="col fw-bold fs-10">
                <span align="left" className={"large_text " + textColorClass}>{message}</span>
            </div>
        </div>
    )
}
