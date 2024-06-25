import {setFile} from "../../redux/file-slice.js";
import {useDispatch, useSelector} from "react-redux";

export const ReportFileButton = ({ file, index }) => {
    const dispatch = useDispatch()
    const active = useSelector(state => state.file.fileInfo.title)

    const { title } = file
    const className = active === title ? 'active' : ''

    function handleClickFile() {
        dispatch(setFile(file))
    }

    return (
        <button data-id={title} key={index}
                className={`btn my-1 fw-semibold fs-5 btn-outline-primary ${className}`}
                onClick={handleClickFile}>
            {title}
        </button>
    )
}
