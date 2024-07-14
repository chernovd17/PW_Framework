import { useSelector } from 'react-redux'
import {ReportFileButton} from "./ReportFileButton.jsx";

export function Reports() {
  const fileList = useSelector(state => state.file.fileList)

  return (
    <div className="w-100">
      <div className="d-flex">
      <div className="w-25">
      </div>
        <div className="w-75">
          <div className="w-50">
          <ul className="list-group mx-">
            {
              fileList.map((fileName, index) => (
                  <ReportFileButton file={fileName} key={index}/>
              ))
            }
          </ul>
          </div>
        </div>
      </div>
    </div>
  )
}
