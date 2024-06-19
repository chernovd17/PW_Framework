import { useSelector, useDispatch } from 'react-redux'
import { setFile } from "../redux/file-slice";

export function Reports() {
  const dispatch = useDispatch()
  const fileList = useSelector(state => state.file.fileList)
  const active = useSelector(state => state.file.fileInfo.title)

  function handleClickFile(event) {
    fileList.filter(file => file.title === event.target.dataset).forEach(file => dispatch(setFile(file)))
  }

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
                <button data-id={fileName.title} key={index} className={`btn my-1 fw-semibold fs-5 btn-outline-primary ${active === fileName.title ? 'active' : ''}`} onClick={handleClickFile}>{fileName.title}</button>
              ))
            }
          </ul>
          </div>
        </div>
      </div>
    </div>
  )
}
