export const StatisticsRow = ({name, value}) => {
    return (
        <div className="row ">
            <div className="col fw-bold fs-10" align="left">
                <span align="center">{name}</span>
            </div>
            <div className="col fw-bold fs-10">
                <span align="left">{value}</span>
            </div>
        </div>
    )
}
