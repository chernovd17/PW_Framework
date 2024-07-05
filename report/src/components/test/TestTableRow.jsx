import ModalImage from "react-modal-image";
import {
    addLevelColor,
    convertTime,
    dateFormatter,
    removeRootFolderFromPath,
    splitByNewLine
} from "../../functions/Functions.jsx";

export const TestTableRow = ({tests}) => {

    return (tests.map((item, index) => <div className='row row-cols-4 border ' key={index}>
        <div className="col-2 border fw-medium fw-light align-items-center fs-6" align="center">
            <div className='row row-cols-1 border'>
                <div className={`align-bottom ${addLevelColor(item.logLevel)}`}
                     align="center">{item.logLevel}</div>
            </div>
            <div className="small_text">
                <div>{dateFormatter(item.dateTime)}</div>
                <div>{convertTime(item.dateTime)}</div>
            </div>
        </div>
        <div className={`col-8 border fw-medium fs-5 ${addLevelColor(item.logLevel)}`}>
            {
                splitByNewLine(item.info).map((inf, i) =>
                    (<div className="g-0" key={i}>{inf}</div>)
                )
            }
        </div>
        <div className="col-2 border" style={{maxHeight: "100px", overflow: "hidden"}}>
            <ModalImage className="modal-image max_pic_height"
                        small={removeRootFolderFromPath(item.screenshotPath)}
                        medium={removeRootFolderFromPath(item.screenshotPath)}
                        hideDownload={true}/>
        </div>
    </div>))
}

